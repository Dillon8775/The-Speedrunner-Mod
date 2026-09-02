package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ContextIntProviders.class)
public interface ContextIntProvidersInvoker {
    @Invoker("cooking")
    static ContextIntProvider invokeCooking(
            final HolderGetter<LootItemCondition> predicates,
            final Holder.Reference<ContextIntProvider> normalBurnTime,
            final Holder.Reference<ContextIntProvider> fastBurnTime,
            final int timeSeconds
    ) {
        throw new AssertionError();
    }
}