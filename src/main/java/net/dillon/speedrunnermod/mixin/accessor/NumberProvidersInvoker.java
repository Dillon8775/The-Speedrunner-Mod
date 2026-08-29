package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(NumberProviders.class)
public interface NumberProvidersInvoker {
    @Invoker("cooking")
    static NumberProvider invokeCooking(
            final HolderGetter<LootItemCondition> predicates,
            final Holder.Reference<NumberProvider> normalBurnTime,
            final Holder.Reference<NumberProvider> fastBurnTime,
            final int timeSeconds
    ) {
        throw new AssertionError();
    }
}