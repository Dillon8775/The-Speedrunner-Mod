package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.SpeedrunnersTotemItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

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

            entity.setHealth(1.0F);
            entity.getEntityWorld().sendEntityStatus(entity, ModStatuses.ADD_SPEEDRUNNER_TOTEM_PARTICLES);
            if (entity instanceof ServerPlayerEntity player) {
                Criteria.USED_TOTEM.trigger(player, new ItemStack(Items.TOTEM_OF_UNDYING));
            }
        }
    });
}