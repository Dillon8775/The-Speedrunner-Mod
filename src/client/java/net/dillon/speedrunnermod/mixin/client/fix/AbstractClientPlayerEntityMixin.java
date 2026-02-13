package net.dillon.speedrunnermod.mixin.client.fix;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerBowItem;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    /**
     * Fixes speedrunner bows not working with FOV multiplier.
     */
    @Redirect(method = "getFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean redirectToConventionalTag(ItemStack heldItem, Item item) {
        return heldItem.isIn(ConventionalItemTags.BOW_TOOLS);
    }

    /**
     * Implements faster pullback time for the {@link SpeedrunnerBowItem}.
     */
    @ModifyConstant(method = "getFovMultiplier", constant = @Constant(floatValue = 20.0F))
    private float changePullbackTime(float constant) {
        ItemStack heldItem = this.getActiveItem();
        return heldItem.isOf(ModItems.SPEEDRUNNER_BOW) ? 15.0F : constant;
    }
}