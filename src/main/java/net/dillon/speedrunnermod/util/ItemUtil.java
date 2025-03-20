package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.List;
import java.util.Optional;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * Helper methods used for other items.
 */
public class ItemUtil {

    /**
     * Locates structures.
     */
    @Author(Authors.KWPUGH)
    public static void findStructureAndShoot(World world, PlayerEntity player, ItemStack itemstack, TagKey<Structure> type) {
        BlockPos playerpos = player.getBlockPos();
        ServerWorld serverWorld = (ServerWorld)world;
        BlockPos locpos = serverWorld.locateStructure(type, playerpos, 100, false);

        EyeOfEnderEntity finderentity = new EyeOfEnderEntity(world, player.getX(), player.getBodyY(0.5D), player.getZ());
        finderentity.setItem(itemstack);
        finderentity.initTargetPos(locpos);
        world.spawnEntity(finderentity);

        if (player instanceof ServerPlayerEntity) {
            Criteria.USED_ENDER_EYE.trigger((ServerPlayerEntity)player, locpos);
        }

        world.syncWorldEvent(null, 1003, player.getBlockPos(), 0);
    }

    /**
     * Adds the tooltips for {@code State-Of-The-Art} items.
     */
    public static void stateOfTheArtItem(List<Text> tooltip) {
        if (options().main.playingMode.easy()) {
            tooltip.add(Text.translatable("item.speedrunnermod.state_of_the_art.tooltip.enabled").formatted(Formatting.GREEN).formatted(Formatting.ITALIC));
        } else {
            tooltip.add(Text.translatable("item.speedrunnermod.state_of_the_art.tooltip.disabled").formatted(Formatting.RED).formatted(Formatting.ITALIC));
        }
    }

    /**
     * Returns an enchantment using the {@code Entity} class.
     */
    public static RegistryEntry<Enchantment> entityEnchantment(Entity entity, RegistryKey<Enchantment> enchantment) {
        Optional<RegistryEntry.Reference<Enchantment>> optional = entity.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
        return optional.get();
    }

    /**
     * Returns an enchantment using the {@code World} class.
     */
    public static RegistryEntry<Enchantment> worldEnchantment(World world, RegistryKey<Enchantment> enchantment) {
        Optional<RegistryEntry.Reference<Enchantment>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
        return optional.get();
    }

    /**
     * Returns an unbreakable elytra.
     */
    public static ItemStack unbreakableComponentItem() {
        ItemStack elytra = new ItemStack(Items.ELYTRA);
        elytra.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));
        return elytra;
    }

    /**
     * Returns flight duration 3 firework rockets.
     */
    public static ItemStack flightDurationComponentItem(int count) {
        ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, count);
        fireworks.set(DataComponentTypes.FIREWORKS, new FireworksComponent(3, List.of()));
        return fireworks;
    }

    /**
     * Returns an infini pearl that is breakable.
     */
    public static ItemStack infiniPearl() {
        ItemStack infiniPearl = new ItemStack(ModItems.INFINI_PEARL);
        infiniPearl.set(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true));
        return infiniPearl;
    }

    /**
     * See {@code ender eye items} for more.
     */
    @Author(Authors.KWPUGH)
    public static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;

        return MathHelper.sqrt((float) (i * i + j * j));
    }

    /**
     * Returns a specific type of formatting.
     */
    public static Formatting toFormatting(Formatting actionbarOn, Formatting actionbarOff) {
        return options().client.itemMessages.isActionbar() ? actionbarOn : actionbarOff;
    }
}