package net.dillon.speedrunnermod.mixin.entity.animal;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractNautilus.class)
public abstract class AbstractNautilusMixin extends TamableAnimal {

    public AbstractNautilusMixin(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    /**
     * Gives nautiluses the movement speed attribute.
     */
    @Inject(method = "createAttributes", at = @At("RETURN"), cancellable = true)
    private static void addNautilusSpeedAttribute(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue()
                .add(ModAttributes.BONUS_NAUTILUS_MOVEMENT_SPEED)
                .add(ModAttributes.BONUS_NAUTILUS_DASH_SPEED)
        );
    }

    /**
     * Makes nautiluses dash faster with speedrunner nautilus armor in water.
     */
    @ModifyConstant(method = "executeRidersJump", constant = @Constant(floatValue = 1.2F))
    private float nautilusDashFasterSpeedrunnerArmorInWater(float original) {
        if (!(this.getDashSpeed() > 0.0F)) {
            return original;
        }

        return this.getDashSpeed() + 0.7F;
    }

    /**
     * Makes nautiluses dash faster with speedrunner nautilus armor on land.
     */
    @ModifyConstant(method = "executeRidersJump", constant = @Constant(floatValue = 0.5F))
    private float nautilusDashFasterSpeedrunnerArmorOnLand(float original) {
        if (!(this.getDashSpeed() > 0.0F)) {
            return original;
        }

        return this.getDashSpeed();
    }

    /**
     * Makes nautiluses swim faster with speedrunner nautilus armor.
     */
    @ModifyConstant(method = "getRiddenSpeed", constant = @Constant(floatValue = 0.0325F))
    private float nautilusGoFasterSpeedrunnerArmor(float original) {
        if (!(this.getMoveSpeed() > 0.0F)) {
            return original;
        }

        return this.getMoveSpeed();
    }

    /**
     * @return the nautiluses move speed.
     */
    @Unique
    private float getMoveSpeed() {
        return (float)this.getAttributeValue(ModAttributes.BONUS_NAUTILUS_MOVEMENT_SPEED) / 10;
    }

    /**
     * @return the nautiluses dash speed.
     */
    @Unique
    private float getDashSpeed() {
        return (float)this.getAttributeValue(ModAttributes.BONUS_NAUTILUS_DASH_SPEED);
    }
}