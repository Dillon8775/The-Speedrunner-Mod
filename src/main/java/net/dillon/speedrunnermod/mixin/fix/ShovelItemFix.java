package net.dillon.speedrunnermod.mixin.fix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dillon.speedrunnermod.item.material.ModToolMaterials;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerToolItem;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShovelItem.class)
public class ShovelItemFix {

    /**
     * Gives {@code shovels} their correct attributes.
     */
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;shovel(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;"))
    private static Item.Properties correctShovelProperties(Item.Properties properties, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Operation<Item.Properties> original) {
        Item.Properties result = original.call(properties, material, attackDamageBaseline, attackSpeedBaseline);

        if (!ModToolMaterials.isValidToolMaterialToModify(material)) {
            return result;
        }

        SpeedrunnerMod.debug("Applying modified shovel attributes for speedrunner shovels.");
        result.attributes(
                SpeedrunnerToolItem.createDefaultAttributes(
                        material,
                        attackDamageBaseline,
                        attackSpeedBaseline,
                        false
                )
        );

        return result;
    }
}