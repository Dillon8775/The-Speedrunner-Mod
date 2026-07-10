package net.dillon.speedrunnermod.mixin.client.fix;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerBowItem;
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

import static net.dillon.speedrunnermod.helper.ModHelper.isQualityOfQuesoLoaded;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerFix extends Player {

    public AbstractClientPlayerFix(Level world, GameProfile profile) {
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
     * Makes {@link SpeedrunnerBowItem} pullback time appear faster.
     */
    @ModifyConstant(method = "getFieldOfViewModifier", constant = @Constant(floatValue = 20.0F))
    private float changePullbackTime(float constant) {
        ItemStack heldItem = this.getUseItem();
        return !isQualityOfQuesoLoaded() && heldItem.getItem() instanceof SpeedrunnerBowItem ? SpeedrunnerBowItem.MAX_DRAW_DURATION + 2.0F : constant;
    }
}