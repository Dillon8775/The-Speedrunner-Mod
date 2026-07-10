package net.dillon.speedrunnermod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

/**
 * The dragon's fireball, and you can literally throw them.
 */
public class DragonFireballItem extends Item {

    public DragonFireballItem(Properties settings) {
        super(settings.rarity(Rarity.EPIC));
    }

    /**
     * Creates the fireball entity and shoots it!
     */
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        if (ThrowableFireball.createFireballEntity(true, player)) {
            return InteractionResult.SUCCESS_SERVER;
        }
        return super.use(world, player, hand);
    }
}