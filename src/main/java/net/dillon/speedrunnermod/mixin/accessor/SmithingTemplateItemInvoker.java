package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.SmithingTemplateItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(SmithingTemplateItem.class)
public interface SmithingTemplateItemInvoker {
    @Invoker("createNetheriteUpgradeIconList")
    static List<Identifier> invokeNetheriteUpgradeIconList() {
        throw new AssertionError();
    }
    @Invoker("createNetheriteUpgradeMaterialList")
    static List<Identifier> invokeNetheriteUpgradeMaterialList() {
        throw new AssertionError();
    }
}