package net.dillon.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.OPTIONS_ERROR_MESSAGE;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.error;
import static net.dillon.speedrunnermod.option.ModOptions.isSafe;

/**
 * The base class for registering options on different environment sides.
 */
public abstract class BaseOptions<T> {
    public static final String CURRENT_VALUE = "current_value";
    private final Gson GSON = createGson();
    private final String fileName;
    private File file;
    protected T instance;

    private final String space = " ";
    private final String pertaining = "Pertaining to: ";
    protected final String related = space + pertaining;

    /**
     * Constructor which initializes the file name and the instance of the options.
     */
    protected BaseOptions(String fileName) {
        this.fileName = fileName;
        this.instance = createDefault();
    }

    /**
     * Returns the type to get the options from.
     */
    protected abstract T createDefault();

    /**
     * Returns the class to get the options from.
     */
    protected abstract Class<T> getConfigClass();

    /**
     * Runs a safe check through all options to ensure no issues.
     * <p>Preforms a {@code "safe check"} on all the Speedrunner Mod options, and makes sure that they are valid and safe to run in-game.
     * <p>If an option is broken or invalid, and it is not recommended to run, the user will automatically boot into the Safe boot screen.</p>
     */
    protected abstract void safeCheck();

    /**
     * Sets an option to be broken and logs it.
     */
    public void setBroken(OptionValue<?> option, String value) {
        error(OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options."+value);
        isSafe(false);
        option.setBroken();
    }

    /**
     * Creates the {@code GSON reader,} which reads options correctly.
     */
    public Gson createGson() {
        GsonBuilder builder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .registerTypeAdapter(IntegerOptionValue.class, new TypeAdapter<IntegerOptionValue>() {
                    @Override
                    public void write(JsonWriter out, IntegerOptionValue value) throws IOException {
                        out.beginObject();
                        out.name("minValue").value(value.getMinValue());
                        out.name("maxValue").value(value.getMaxValue());
                        out.name("requires_restart").value(value.requiresRestart());
                        out.name("default_value").value(value.getDefaultValue());
                        out.name("current_value").value(value.getCurrentValue());
                        out.name("broken").value(value.isBroken());
                        out.endObject();
                    }

                    @Override
                    public IntegerOptionValue read(JsonReader in) throws IOException {
                        in.beginObject();
                        int minValue = 0, maxValue = 0, defaultValue = 0, currentValue = 0;
                        boolean requiresRestart = false, broken = false;

                        while (in.hasNext()) {
                            switch (in.nextName()) {
                                case "minValue" -> minValue = in.nextInt();
                                case "maxValue" -> maxValue = in.nextInt();
                                case "requires_restart" -> requiresRestart = in.nextBoolean();
                                case "default_value" -> defaultValue = in.nextInt();
                                case "current_value" -> currentValue = in.nextInt();
                                case "broken" -> broken = in.nextBoolean();
                            }
                        }
                        in.endObject();

                        IntegerOptionValue value = new IntegerOptionValue(defaultValue, requiresRestart, minValue, maxValue);
                        value.set(currentValue);
                        if (broken) {
                            value.setBroken();
                        }
                        return value;
                    }
                });
        return builder.create();
    }

    /**
     * Gets the instance of options.
     */
    public T getInstance() {
        return this.instance;
    }

    /**
     * Resets options to default.
     */
    public void resetToDefault() {
        T defaults = this.createDefault();
        String json = GSON.toJson(defaults);
        this.instance = GSON.fromJson(json, this.getConfigClass());
    }

    /**
     * Throws a new {@link NullPointerException}.
     */
    public void throwNullPointerException(String option, Object[] values) {
        throw new NullPointerException("Option \""+option+"\" is NULL. Most likely, it is set to an invalid value in the \"speedrunnermod-options.config.json\" file. You can either delete the config file to automatically re-generate it correctly upon next launch, or set it to a valid value. Valid values for \""+option+"\" are: "+ Arrays.toString(values));
    }

    public void throwNumberLessThanOneException(String option) {
        throw new IllegalStateException("Option \""+option+"\" cannot be set to a value less than 1.");
    }

    /**
     * Loads {@code this} config.
     */
    public void load() {
        File configFile = getConfigFile();
        for (File oldConfigFile : this.oldConfigFiles()) {
            if (oldConfigFile.exists()) {
                if (oldConfigFile.delete()) {
                    SpeedrunnerMod.warn("Found old speedrunner mod config file, deleting.");
                }
            }
        }
        if (!configFile.exists()) {
            this.instance = createDefault();
        } else {
            try (FileReader reader = new FileReader(configFile)) {
                this.instance = GSON.fromJson(reader, getConfigClass());
            } catch (Exception e) {
                e.printStackTrace();
                this.instance = createDefault();
            }
        }
        this.safeCheck();
        this.save();
    }

    /**
     * Saves {@code this} config.
     */
    public void save() {
        try (FileWriter writer = new FileWriter(getConfigFile())) {
            writer.write(GSON.toJson(this.instance));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Matches client-side options with server-side options.
     */
    public void match(T sentOptions) {
        this.instance = sentOptions;
        save();
    }

    /**
     * Gets {@code this} config file.
     */
    public File getConfigFile() {
        if (this.file == null) {
            this.file = this.ofFile(this.fileName);
        }
        return this.file;
    }

    /**
     * @return the {@link File} in the fabric config directory.
     */
    private File ofFile(String fileName) {
        return new File(FabricLoader.getInstance().getConfigDir().toFile(), fileName);
    }

    /**
     * @return a list of old config files to be deleted.
     */
    private List<File> oldConfigFiles() {
        return List.of(
                this.ofFile("speedrunnermod-client_config_1.11.1.json"),
                this.ofFile("speedrunnermod-config_1.11.1.json"),
                this.ofFile("speedrunnermod-config.json"),
                this.ofFile("speedrunnermod-client_config.json"),
                this.ofFile("speedrunnermod-options.json")
        );
    }
}