package net.dillon.speedrunnermod.event;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.world.entity.player.Player;

@Author(Authors.SERILUM)
public class ModEventCallbacks {

    /**
     * Registers custom events.
     */
    public static void registerEventCallbacks() {
        SpeedrunnersTotemEvent.EVENT.register(((entity, stack, source) -> {}));

        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, source, damage) -> {
            if (entity instanceof Player player) {
                return SpeedrunnersTotemEvent.canUseSpeedrunnersTotem(player, source);
            }
            return true;
        });

        SpeedrunnerMod.LOGGER.debug("Registered event callbacks.");
    }
}