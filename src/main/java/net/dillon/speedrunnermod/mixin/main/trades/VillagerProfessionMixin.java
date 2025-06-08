package net.dillon.speedrunnermod.mixin.main.trades;

import net.dillon.speedrunnermod.village.ModPointOfInterestTypes;
import net.dillon.speedrunnermod.village.ModVillagers;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Registers speedrunner mod villager professions.
 */
@Mixin(VillagerProfession.class)
public class VillagerProfessionMixin {

    @Inject(method = "registerAndGetDefault", at = @At("TAIL"))
    private static void registerModVillagers(Registry<VillagerProfession> registry, CallbackInfoReturnable<VillagerProfession> cir) {
        VillagerProfession.register(registry, ModVillagers.RETIRED_SPEEDRUNNER, ModPointOfInterestTypes.RETIRED_SPEEDRUNNER_POI, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
    }
}