package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.packet.client.CompleteTutorialStepS2CPacket;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FireworksComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;

import java.util.*;

/**
 * Helper methods for various different things (ex. items and math calculations)
 */
public class ModUtil {
    public static final String CONFIG_FILE_NAME = "speedrunnermod-config.json";
    public static final String CLIENT_CONFIG_FILE_NAME = "speedrunnermod-client_config.json";

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
     * Returns the player's death coordinates as a clickable text to teleport right to it.
     */
    public static Text deathCords(double x, double y, double z) {
        return Text.translatable("speedrunnermod.player_death_cords",
                        ModUtil.roundToNearestTenthsPlace(x),
                        ModUtil.roundToNearestTenthsPlace(y),
                        ModUtil.roundToNearestTenthsPlace(z))
                .setStyle(Style.EMPTY
                        .withHoverEvent(new HoverEvent.ShowText(Text.translatable("speedrunnermod.teleport_to_player_death_cords")))
                        .withClickEvent(new ClickEvent.SuggestCommand("/teleport @s " + x + " " + y + " " + z)));
    }

    /**
     * Sends a message to the player with the mod prefix.
     */
    public static void sendWithPrefix(String string, PlayerEntity player) {
        player.sendMessage((ModTexts.BLANK).copy().append((Text.translatable("speedrunnermod.tutorial_mode.prefix"))).append("").append(Text.translatable(string)), false);
    }

    /**
     * Sends the new {@link TutorialStep} boolean over to the client-side.
     */
    public static void completeStepS2C(TutorialStep step, PlayerEntity player, String... messageKeys) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            List<String> messageKeysList = new ArrayList<>(Arrays.asList(messageKeys));
            ServerPlayNetworking.send(serverPlayer, new CompleteTutorialStepS2CPacket(step, messageKeysList));
        }
    }

    /**
     * Returns an enchantment using the {@code Entity} class.
     */
    public static RegistryEntry<Enchantment> entityEnchantment(Entity entity, RegistryKey<Enchantment> enchantment) {
        try {
            Optional<RegistryEntry.Reference<Enchantment>> optional = entity.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
            return optional.orElseThrow();
        } catch (Exception o) {
            return noSuchElementExceptionCrash(o);
        }
    }

    /**
     * Returns an enchantment using the {@code World} class.
     */
    public static RegistryEntry<Enchantment> worldEnchantment(World world, RegistryKey<Enchantment> enchantment) {
        try {
            Optional<RegistryEntry.Reference<Enchantment>> optional = world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue());
            return optional.orElseThrow();
        } catch (Exception o) {
            return noSuchElementExceptionCrash(o);
        }
    }

    /**
     * @return {@code null,} crashes the game accordingly when you join a server that doesn't have the speedrunner mod installed.
     */
    private static RegistryEntry<Enchantment> noSuchElementExceptionCrash(Exception o) {
        SpeedrunnerMod.error("Speedrunner Mod Crashed! Likely caused due to the server you joined doesn't have the speedrunner mod installed.");
        o.printStackTrace();
        return null;
    }

    /**
     */
    public static ItemStack createUnbreakableItem(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        return stack;
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
    public static Formatting toFormatting(UUID uuid, Formatting actionbar, Formatting chat) {
        return ServerSyncedClientOptions.shouldShowInActionbar(uuid) ? actionbar : chat;
    }

    /**
     * Rounds the inputted number to the nearest one decimal place (or nearest tenths place)
     */
    public static double roundToNearestTenthsPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
    }

    /**
     * Returns a random float, with a minimum and maximum value.
     */
    public static float randomFloat(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * Converts seconds to milliseconds.
     */
    public static int millisecondsAsSeconds(int seconds) {
        return seconds * 1000;
    }

    /**
     * Converts seconds to ticks.
     */
    public static int secondsInTicks(int seconds) {
        try {
            int testSeconds = 0;
            while (testSeconds < 525600) {
                if (seconds == testSeconds) {
                    throw new NumberFormatException();
                }
                testSeconds += 60;
            }
            return seconds * 20;
        } catch (NumberFormatException o) {
            SpeedrunnerMod.error("Use method minutesInTicks(int) if you're inputting an exact minute.");
            o.printStackTrace();
            return minutesInTicks(seconds / 60);
        }
    }

    /**
     * Converts minutes to ticks.
     */
    public static int minutesInTicks(int minutes) {
        return (minutes * 60) * 20;
    }

    /**
     * Creates an {@code integer list option,} with {@code positive} coordinate values.
     */
    public static double[] createListOption(double posX, double posY, double posZ) {
        return new double[]{posX, posY, posZ};
    }

    /**
     * Creates an {@code integer list option,} with {@code negative} and {@code positive} coordinate values.
     */
    public static int[] createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return new int[]{negX, negY, negZ, posX, posY, posZ};
    }

    /**
     * Creates a new {@code structure spawn rate option.}
     */
    public static int[] createStructureSpawnRateOption(int spacing, int separation) {
        return new int[]{spacing, separation};
    }
}