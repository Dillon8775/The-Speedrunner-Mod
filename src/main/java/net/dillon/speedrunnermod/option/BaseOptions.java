package net.dillon.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * The base class for registering options on different environment sides.
 */
public abstract class BaseOptions<T> {
    private final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
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
     * Gets the instance of options.
     */
    public T getInstance() {
        return this.instance;
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
    public void matchWithServer(T serverOptions) {
        this.instance = serverOptions;
        save();
    }

    /**
     * Gets {@code this} config file.
     */
    public File getConfigFile() {
        if (this.file == null) {
            this.file = new File(FabricLoader.getInstance().getConfigDir().toFile(), this.fileName);
        }
        return this.file;
    }
}