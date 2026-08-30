package net.dillon.speedrunnermod.option;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.dillon.dillonlib.util.BaseOptions;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;

import java.io.IOException;
import java.util.Arrays;

import static net.dillon.speedrunnermod.option.CommonModOptions.isSafe;

/**
 * The base class for registering options on different environment sides.
 */
public abstract class ModBaseOptionsHandler<T> extends BaseOptions<T> {
    public static final String CURRENT_VALUE = "current_value";
    private final Gson GSON = createGson();
    protected T instance;

    private final String space = " ";
    private final String pertaining = "Pertaining to: ";
    protected final String related = space + pertaining;

    public ModBaseOptionsHandler(String fileName) {
        super(fileName);
    }

    /**
     * Creates the {@code GSON reader,} which reads options correctly.
     */
    @Override
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
     * Sets an option to be broken and logs it.
     */
    public void setBroken(OptionValue<?> option, String value) {
        SpeedrunnerMod.LOGGER.error(ModConstants.OPTIONS_ERROR_MESSAGE + related + "speedrunnermod.options.{}", value);
        isSafe(false);
        option.setBroken();
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
     * Matches client-side options with server-side options.
     */
    public void match(T sentOptions) {
        this.instance = sentOptions;
        save();
    }
}