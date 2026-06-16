package net.dillon.speedrunnermod.mixin.entity.animal;

import net.dillon.speedrunnermod.item.SpeedrunnerHarnessItem;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.happyghast.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.util.ModUtil.getBodyItem;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal {

    public HappyGhastMixin(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    /**
     * Makes happy ghasts faster with a speedrunner's nautilus.
     */
    @ModifyConstant(method = "travel", constant = @Constant(floatValue = 3.0F))
    private float increaseFlyingSpeedWithSpeedrunnerHarness(float original) {
        if (!(getBodyItem(this) instanceof SpeedrunnerHarnessItem speedrunnerHarnessItem)) {
            return original;
        }

        return speedrunnerHarnessItem.getFlyingSpeedDivider();
    }
}