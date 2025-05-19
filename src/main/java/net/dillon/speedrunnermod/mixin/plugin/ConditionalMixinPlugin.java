package net.dillon.speedrunnermod.mixin.plugin;

import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@ChatGPT(Credit.FULL_CREDIT)
public class ConditionalMixinPlugin implements IMixinConfigPlugin {

    /**
     * Disables certain mixins from loading if the {@code apply fog mixin} advanced option is disabled.
     */
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return !shouldNotApply(mixinClassName);
    }

    // Other methods...
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    /**
     * Returns mixins that should not apply based on certain conditions.
     */
    private boolean shouldNotApply(String mixinClassName) {
        return !options().mixins.terraBlenderSurfaceRuleDataMixin && mixinClassName.equals("net.dillon.speedrunnermod.mixin.main.world.TBSurfaceRuleDataMixin");
    }
}