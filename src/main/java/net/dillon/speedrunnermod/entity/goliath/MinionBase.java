package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.util.RandomChance;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

/**
 * A minion of {@link GoliathBase}.
 */
public interface MinionBase {
    void setFireballChargeTime(int time);
    int getFireballChargeTime();
    void setGoliathMinion(boolean value);
    boolean isGoliathMinion();

    /**
     * @return a zombie's fireball.
     */
    static ItemStack zombiesFireball(Item item) {
        ItemStack zombieFireball = new ItemStack(item);
        zombieFireball.set(DataComponents.ITEM_NAME, Component.translatable("item.minecraft.fire_charge.zombie"));
        return zombieFireball;
    }

    /**
     * @return the cooldown for a fireball.
     */
    static int fireballChargeTime(MinionBase minion) {
        if (minion.isGoliathMinion()) {
            return ModConstants.DEFAULT_MINION_FIREBALL_CHARGE_SPEED;
        } else {
            return doomOrDefault(ModConstants.DEFAULT_DOOM_ZOMBIE_FIREBALL_CHARGE_SPEED, ModConstants.DEFAULT_ZOMBIE_FIREBALL_CHARGE_SPEED);
        }
    }

    /**
     * @return the chance for a isZombie to spawn with a fireball.
     */
    static float spawnWithFireballChance() {
        return doomOrDefault(RandomChance.floatInclusive(0.14F, 0.19F), RandomChance.floatInclusive(0.03F, 0.18F));
    }
}