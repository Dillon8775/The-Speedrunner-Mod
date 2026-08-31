package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.mixinplugin.MixinPluginUtil;
import net.dillon.dillonlib.mixinplugin.PredicateEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Abstract representation of a mixin plugin utility class.
 */
public abstract class AbstractMixinPluginUtil extends MixinPluginUtil {

    @Override
    public Logger logger() {
        return LoggerFactory.getLogger("SpeedrunnerMod/Mixin");
    }

    @Override
    public String mixinDirectory() {
        return "net.dillon.speedrunnermod.mixin.";
    }

    @Override
    public abstract List<PredicateEntry> entries();
}