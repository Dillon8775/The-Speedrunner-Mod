package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnersTotemItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;

@Author(Authors.YELEEFFF)
@FunctionalInterface
public interface SpeedrunnersTotemUsedCallback {
    void invoke(LivingEntity entity, ItemStack stack, DamageSource source);

    Event<SpeedrunnersTotemUsedCallback> EVENT = EventFactory.createArrayBacked(SpeedrunnersTotemUsedCallback.class,
            (listeners) -> (LivingEntity entity, ItemStack stack, DamageSource source) -> {
        if (!(stack.getItem() instanceof SpeedrunnersTotemItem)) {
            return;
        }

        for (SpeedrunnersTotemUsedCallback listener : listeners) {
            listener.invoke(entity, stack, source);

            if (stack.isOf(ModItems.SPEEDRUNNERS_TOTEM)) {
                entity.setHealth(1.0F);
                entity.getWorld().sendEntityStatus(entity, SpeedrunnersTotemItem.use());
            }
        }
            });
}