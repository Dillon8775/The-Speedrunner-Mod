package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PiglinAi.class)
public interface PiglinAiInvoker {
    @Invoker("isAdmiringItem")
    static boolean invokeIsAdmiringItem(final Piglin body) {
        throw new AssertionError();
    }
    @Invoker("isBarterCurrency")
    static boolean invokeIsBarterCurrency(final ItemStack itemStack) {
        throw new AssertionError();
    }
}