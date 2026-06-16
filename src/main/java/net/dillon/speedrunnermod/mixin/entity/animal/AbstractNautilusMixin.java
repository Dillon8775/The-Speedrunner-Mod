package net.dillon.speedrunnermod.mixin.entity.animal;

import net.dillon.speedrunnermod.item.SpeedrunnerNautilusArmorItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.nautilus.AbstractNautilus;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.util.ModUtil.getBodyItem;

@Mixin(AbstractNautilus.class)
public abstract class AbstractNautilusMixin extends TamableAnimal {

    public AbstractNautilusMixin(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    /**
     * Makes nautiluses dash faster with speedrunner nautilus armor in water.
     */
    @ModifyConstant(method = "executeRidersJump", constant = @Constant(floatValue = 1.2F))
    private float nautilusDashFasterSpeedrunnerArmorInWater(float original) {
        if (!(getBodyItem(this) instanceof SpeedrunnerNautilusArmorItem speedrunnerNautilusItem)) {
            return original;
        }

        return speedrunnerNautilusItem.getDashSpeed();
    }

    /**
     * Makes nautiluses dash faster with speedrunner nautilus armor on land.
     */
    @ModifyConstant(method = "executeRidersJump", constant = @Constant(floatValue = 0.5F))
    private float nautilusDashFasterSpeedrunnerArmorOnLand(float original) {
        if (!(getBodyItem(this) instanceof SpeedrunnerNautilusArmorItem speedrunnerNautilusItem)) {
            return original;
        }

        return speedrunnerNautilusItem.getDashSpeed() - 0.7F;
    }

    /**
     * Makes nautiluses swim faster with speedrunner nautilus armor.
     */
    @ModifyConstant(method = "getRiddenSpeed", constant = @Constant(floatValue = 0.0325F))
    private float nautilusGoFasterSpeedrunnerArmor(float original) {
        if (!(getBodyItem(this) instanceof SpeedrunnerNautilusArmorItem speedrunnerNautilusItem)) {
            return original;
        }

        return speedrunnerNautilusItem.getMoveSpeed();
    }
}