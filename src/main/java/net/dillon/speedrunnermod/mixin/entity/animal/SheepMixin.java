package net.dillon.speedrunnermod.mixin.entity.animal;

import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public class SheepMixin {

    /**
     * Allows sheep to spawn with certain colors in the Speedrunner's Wasteland biome.

     */
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void speedrunnermod$setWastelandSheepColor(ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir) {
        Sheep sheep = (Sheep)(Object)this;

        if (sheep.getColor().equals(DyeColor.PINK) || !level.getBiome(sheep.blockPosition()).is(ModBiomes.SPEEDRUNNERS_WASTELAND)) {
            return;
        }

        DyeColor[] colors = {
                DyeColor.LIGHT_BLUE,
                DyeColor.CYAN,
                DyeColor.BLUE
        };

        if (level.getRandom().nextFloat() <= 0.25F) {
            sheep.setColor(colors[level.getRandom().nextInt(colors.length)]);
        }
    }

    /**
     * Allows sheep to be sheared with {@code speedrunner shears.}
     */
    @Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    private boolean interactMob(ItemStack stack, Object o) {
        return stack.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}