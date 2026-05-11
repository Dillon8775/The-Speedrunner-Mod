package net.dillon.speedrunnermod.client.mixin.fix;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerBowItem;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {

    public AbstractClientPlayerMixin(Level world, GameProfile profile) {
        super(world, profile);
    }

    /**
     * Fixes speedrunner bows not working with FOV multiplier.
     */
    @Redirect(method = "getFieldOfViewModifier", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean redirectToConventionalTag(ItemStack heldItem, Object o) {
        return heldItem.is(ConventionalItemTags.BOW_TOOLS);
    }

    /**
     * Implements faster pullback time for the {@link SpeedrunnerBowItem}.
     */
    @ModifyConstant(method = "getFieldOfViewModifier", constant = @Constant(floatValue = 20.0F))
    private float changePullbackTime(float constant) {
        ItemStack heldItem = this.getUseItem();
        return heldItem.is(ModItems.SPEEDRUNNER_BOW) ? 15.0F : constant;
    }
}