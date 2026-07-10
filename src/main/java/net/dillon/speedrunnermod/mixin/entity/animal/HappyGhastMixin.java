package net.dillon.speedrunnermod.mixin.entity.animal;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal {

    public HappyGhastMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    /**
     * Gives happy ghasts the flying speed attribute.
     */
    @Inject(method = "createAttributes", at = @At("RETURN"), cancellable = true)
    private static void addFlyingSpeedAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue()
                .add(ModAttributes.BONUS_HAPPY_GHAST_FLYING_SPEED)
        );
    }

    /**
     * Makes happy ghasts fly faster.
     */
    @ModifyConstant(method = "travel", constant = @Constant(floatValue = 3.0F))
    private float increaseFlyingSpeedWithSpeedrunnerHarness(float original) {
        float flyingSpeed = (float)this.getAttributeValue(ModAttributes.BONUS_HAPPY_GHAST_FLYING_SPEED);
        if (!(flyingSpeed > 0.0F)) {
            return original;
        }

        if (flyingSpeed == 0.5F) {
            return 0.7F;
        } else if (flyingSpeed == 0.7F) {
            return 0.5F;
        }
        return flyingSpeed;
    }
}