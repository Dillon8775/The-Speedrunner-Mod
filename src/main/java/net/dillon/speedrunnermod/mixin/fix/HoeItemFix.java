package net.dillon.speedrunnermod.mixin.fix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dillon.speedrunnermod.item.material.ModToolMaterials;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerToolItem;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HoeItem.class)
public class HoeItemFix {

    /**
     * Gives {@code hoes} their correct attributes.
     */
    @WrapOperation(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;hoe(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;"))
    private static Item.Properties correctHoeProperties(Item.Properties properties, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Operation<Item.Properties> original) {
        Item.Properties result = original.call(properties, material, attackDamageBaseline, attackSpeedBaseline);

        if (!ModToolMaterials.isValidToolMaterialToModify(material)) {
            return result;
        }

        SpeedrunnerMod.debug("Applying modified axe attributes for speedrunner hoes.");
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