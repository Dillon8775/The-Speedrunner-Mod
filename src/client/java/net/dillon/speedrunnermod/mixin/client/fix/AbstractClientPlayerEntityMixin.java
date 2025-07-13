package net.dillon.speedrunnermod.mixin.client.fix;

import com.mojang.authlib.GameProfile;
import net.dillon.speedrunnermod.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin extends PlayerEntity {

    public AbstractClientPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    /**
     * @author Dillon8775
     * @reason Fixes the speedrunner bow not working with the FOV multiplier, and also changes it because it pulls faster.
     */
    @Overwrite
    public float getFovMultiplier(boolean firstPerson, float fovEffectScale) {
        float f = 1.0F;
        if (this.getAbilities().flying) {
            f *= 1.1F;
        }

        float g = this.getAbilities().getWalkSpeed();
        if (g != 0.0F) {
            float h = (float)this.getAttributeValue(EntityAttributes.MOVEMENT_SPEED) / g;
            f *= (h + 1.0F) / 2.0F;
        }

        if (this.isUsingItem()) {
            ItemStack itemStack = this.getActiveItem();
            if (itemStack.isIn(ConventionalItemTags.BOW_TOOLS)) {
                float dividedBy = itemStack.isOf(ModItems.SPEEDRUNNER_BOW) ? 15.0F : 20.0F;
                float h = Math.min((float)this.getItemUseTime() / dividedBy, 1.0F);
                f *= 1.0F - MathHelper.square(h) * 0.15F;
            } else if (firstPerson && this.isUsingSpyglass()) {
                return 0.1F;
            }
        }

        return MathHelper.lerp(fovEffectScale, 1.0F, f);
    }
}