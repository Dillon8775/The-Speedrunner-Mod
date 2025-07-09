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
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * Helper methods for various different things (ex. items and math calculations)
 */
public class ModUtil {
    public static int errorMessagesSent = 0;
    public static final String CONFIG_FILE_NAME = "speedrunnermod-config_1.11.1.json";
    public static final String CLIENT_CONFIG_FILE_NAME = "speedrunnermod-client_config_1.11.1.json";

    public static final int SPEEDRUNNER_WATER_COLOR = 0x85C1E9;
    public static final int SPEEDRUNNER_WATER_FOG_COLOR = 0x85C1E9;
    public static final int DOLPHIN_RANGE = 200;
    public static final int TREES_PLAINS_COUNT = 1;
    public static final int DIAMOND_ORE_SPAWN_CHANCE = 8;
    public static final int BURIED_DIAMOND_ORE_SPAWN_CHANCE = 9;
    public static final int LARGE_DIAMOND_ORE_SPAWN_CHANCE = 5;
    public static final int LAPIS_LAZULI_ORE_SPAWN_CHANCE = 3;
    public static final int BURIED_LAPIS_LAZULI_ORE_SPAWN_CHANCE = 4;
    public static final float LAVA_BOAT_VELOCITY_MULTIPLIER = 0.95F;
    public static final float FAST_BOAT_VELOCITY_MULTIPLIER = 1.035F;
    public static final double DOLPHIN_PREDICATE_RANGE = 20.0D;

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
     * Returns a specific type of formatting.
     */
    public static Formatting toFormatting(UUID uuid, Formatting actionbar, Formatting chat) {
        return ServerSyncedClientOptions.shouldShowInActionbar(uuid) ? actionbar : chat;
    }

    /**
     * Sends a player message with the actionbar preference and formatting.
     */
    public static void sendMessageWithActionbarPref(PlayerEntity player, Text text) {
        player.sendMessage(text, ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
    }

    /**
     * Sends a player message with the actionbar preference and formatting with formatting for actionbar on/off.
     */
    public static void sendMessageWithActionbarPref(PlayerEntity player, Text text, Formatting actionbar, Formatting chat) {
        player.sendMessage(text.copy().formatted(ModUtil.toFormatting(player.getUuid(), actionbar, chat)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
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
     * @return {@code enchantment} with the use of the {@link Entity} or {@link World} class.
     * @param entityOrWorld should never be anything other than {@link Entity} or {@link World}.
     * @param enchantment the enchantment that should be returned.
     */
    @Deprecated
    public static RegistryEntry<Enchantment> enchantment(@NotNull Object entityOrWorld, @NotNull RegistryKey<Enchantment> enchantment) {
        try {
            Optional<RegistryEntry.Reference<Enchantment>> optional =
                    entityOrWorld instanceof Entity entity ? entity.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue()) :
                            entityOrWorld instanceof World world ? world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getEntry(enchantment.getValue()) :
                                    Optional.empty();
            return optional.orElseThrow();
        } catch (Exception o) {
            if (!(errorMessagesSent > 100)) {
                SpeedrunnerMod.error("(" + errorMessagesSent + ") Error with Speedrunner Mod! Likely caused due to the server you joined doesn't have the speedrunner mod installed.");
            }
            if (errorMessagesSent == 101) {
                SpeedrunnerMod.error("(" + errorMessagesSent + ") Returning LOOTING enchantment.");
                o.printStackTrace();
                SpeedrunnerMod.error("(" + errorMessagesSent + ") This Speedrunner Mod error is continuous, but handled. Messages will stop now due to prevent overflow errors.");
            }
            errorMessagesSent++;
            World world = null;
            if (entityOrWorld instanceof Entity entity) {
                world = entity.getWorld();
            } else if (entityOrWorld instanceof World w) {
                world = w;
            }
            if (world != null) {
                return world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getEntry(Enchantments.LOOTING.getValue())
                        .orElseThrow();
            }
            throw o;
        }
    }

    /**
     * Creates an unbreakable item.
     */
    public static ItemStack createUnbreakableItem(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.set(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE);
        return stack;
    }

    /**
     * Spawns a {@code floating item entity} from the player's position and follows them.
     */
    public static void spawnFloatingItemEntity(World world, ItemStack stack, PlayerEntity player) {
        spawnFloatingItemEntity(world, player.getBlockPos(), stack, player, false);
    }

    /**
     * Spawns a {@code floating item entity} from the {@link BlockPos}'s position.
     */
    public static void spawnFloatingItemEntity(World world, BlockPos pos, ItemStack stack, PlayerEntity player, boolean playSound) {
        ItemEntity item = new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 3.0F, pos.getZ() + 0.5F, stack);
        item.setInvulnerable(true);
        item.setGlowing(true);
        item.setNoGravity(true);
        item.setNeverDespawn();

        Vec3d itemPos = item.getPos();
        Vec3d playerPos = player.getPos();
        Vec3d motion = playerPos.subtract(itemPos).normalize().multiply(0.1D);
        item.setVelocity(motion.x, motion.y, motion.z);

        if (playSound) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.NEUTRAL, 3.0F, 1.0F);
        }
        world.spawnEntity(item);
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
     * Modifies the {@code maximum health} of an entity.
     */
    public static void modifyMaxHealth(LivingEntity entity, double health) {
        if (entity.getAttributeInstance(EntityAttributes.MAX_HEALTH) != null) {
            entity.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(health);
        }
        entity.setHealth((float)entity.getAttributeInstance(EntityAttributes.MAX_HEALTH).getBaseValue());
    }

    /**
     * Modifies the {@code generic movement speed} of an entity.
     */
    public static void modifyMovementSpeed(LivingEntity entity, double speed) {
        if (entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED) != null) {
            entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(speed);
        }
        entity.setMovementSpeed((float)entity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getBaseValue());
    }

    /**
     * Modifies the {@code follow range} of an entity.
     */
    public static void modifyFollowRange(LivingEntity entity, double range) {
        if (entity.getAttributeInstance(EntityAttributes.FOLLOW_RANGE) != null) {
            entity.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(range);
        }
    }

    /**
     * Modifies the {@code attack damage} of an entity.
     */
    public static void modifyAttackDamage(LivingEntity entity, double attackDamage) {
        if (entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE) != null) {
            entity.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        }
    }

    /**
     * Modifies the {@code attack knockback} of an entity.
     */
    public static void modifyAttackKnockback(LivingEntity entity, double attackKnockback) {
        if (entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK) != null) {
            entity.getAttributeInstance(EntityAttributes.ATTACK_KNOCKBACK).setBaseValue(attackKnockback);
        }
    }

    /**
     * Modifies the {@code knockback resistance} of an entity.
     */
    public static void modifyKnockbackResistance(LivingEntity entity, double resistance) {
        if (entity.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE) != null) {
            entity.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(resistance);
        }
    }

    /**
     * Modifies the {@code armor attribute} of an entity.
     */
    public static void modifyArmor(LivingEntity entity, double value) {
        if (entity.getAttributeInstance(EntityAttributes.ARMOR) != null) {
            entity.getAttributeInstance(EntityAttributes.ARMOR).setBaseValue(value);
        }
    }

    /**
     * Modifies the {@code experiencePoints} variable in {@link MobEntity}.
     */
    public static int modifyExperiencePoints(MobEntity reference, LivingEntity attacker, int base, int multiplier) {
        return base + EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment(reference, Enchantments.LOOTING), attacker) * multiplier;
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
     * Returns a {@code percent percentChance.}
     */
    public static boolean percentChance(net.minecraft.util.math.random.Random random, int percentChance) {
        return random.nextInt(100) < percentChance;
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
    public static int secondsAsTicks(int seconds) {
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
            return minutesAsTicks(seconds / 60);
        }
    }

    /**
     * Converts minutes to ticks.
     */
    public static int minutesAsTicks(int minutes) {
        return (minutes * 60) * 20;
    }

    /**
     * Creates an {@code integer list option,} with {@code positive} coordinate values.
     */
    public static List<Integer> createListOption(int posX, int posY, int posZ) {
        return List.of(posX, posY, posZ);
    }

    /**
     * Creates an {@code integer list option,} with {@code negative} and {@code positive} coordinate values.
     */
    public static List<Integer> createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return List.of(negX, negY, negZ, posX, posY, posZ);
    }

    /**
     * Creates a new {@code structure spawn rate option.}
     */
    public static List<Integer> createStructureSpawnRateOption(int spacing, int separation) {
        return List.of(spacing, separation);
    }

    /**
     * @return the bed block explosion power based.
     */
    public static float getBedBlockExplosionPower(World world) {
        if (isDoomMode()) {
            return world.getRegistryKey() == World.END ? 15.0F : 5.0F;
        } else {
            return 5.0F;
        }
    }

    /**
     * @return how long an entity should be set on fire for from lava (in seconds).
     */
    public static int getFireDamageFromLavaDuration() {
        return isDoomMode() ? 15 : 7;
    }

    /**
     * @return how long an entity should be set on fire for from a fireball (in seconds).
     */
    public static int getFireballFireDamageTime() {
        return isDoomMode() ? 6 : 3;
    }

    /**
     * @return the blaze's fireball shooting cooldown (in ticks).
     */
    public static int getBlazeFireballCooldown() {
        return isDoomMode() ? 60 : 180;
    }

    /**
     * @return the ghast's fireball shooting cooldown (in ticks).
     * <p>I don't know why, but these values have to be negative.</p>
     */
    public static int getGhastFireballCooldown() {
        return isDoomMode() ? -5 : -40;
    }

    /**
     * @return how long it takes for a slime to make it's next jump (in ticks).
     */
    public static int getSlimeJumpTime() {
        return isDoomMode() ? 20 : 100;
    }

    /**
     * @return how long it takes for a player to lose an air bubble (in seconds).
     */
    public static int getPlayerBreathTime() {
        return options().advanced.higherBreathTime.getCurrentValue() ? 8 : 4;
    }

    /**
     * @return how long it takes for a silverfish to call for more backup (in ticks).
     */
    public static int getSilverfishCallForHelpDelay() {
        return isDoomMode() ? 20 : 100;
    }

    /**
     * @return how long a wither skeleton inflicts the wither effect for (in ticks).
     */
    public static int getWitherSkeletonWitherEffectDuration() {
        return isDoomMode() ? 200 : 60;
    }

    /**
     * @return the minimum y-level that a stronghold can generate at.
     */
    public static int getStrongholdMinY() {
        return isDoomMode() ? -48 : 27;
    }

    /**
     * @return the maximum y-level that a stronghold can generate at.
     */
    public static int getStrongholdMaxY() {
        int seaLevel = 63;
        return isDoomMode() ? 0 : seaLevel;
    }

    /**
     * @return the percentChance of an ender eye being pre-filled in an end portal frame block.
     * <p>The higher the value, the less the percentChance.</p>
     */
    public static float getPrefilledEnderEyeChance() {
        return isDoomMode() ? 0.99F : isEasyMode() ? 0.6F : 0.9F;
    }

    /**
     * @return how much damage lava does to an entity (each 0.5 = half a heart).
     */
    public static float getLavaDamageValue() {
        return isDoomMode() ? 4.0F : 2.0F;
    }

    /**
     * @return how much damage a fireball does when hitting an entity (each 0.5 = half a heart).
     */
    public static float getFireballDamageValue() {
        return isDoomMode() ? 5.0F : 1.0F;
    }

    /**
     * @return how much damage a slime does when attacking.
     */
    public static float getSlimeDamageMultiplier() {
        return isDoomMode() ? 2.2F : 1.5F;
    }

    /**
     * @return how much damage a vex takes each time it takes damage from decaying.
     */
    public static float getVexDecayDamageValue() {
        return isDoomMode() ? 100.0F : 1.0F;
    }

    /**
     * @return how much damage the ender dragon does.
     */
    public static float getEnderDragonDamageValue() {
        return isDoomMode() ? 12.0F : 3.0F;
    }

    /**
     * @return the maximum health for the ender dragon.
     */
    public static double getEnderDragonMaxHealth() {
        return isDoomMode() ? 500.0D : 100.0D;
    }

    /**
     * @return the amplifier for the instant damage effect upon the ender dragon shooting a fireball from it's mouth.
     */
    public static int getEnderDragonFireballInstantDamageAmplifier() {
        return isDoomMode() ? 1 : 0;
    }

    /**
     * @return how much the ender dragon heals when connecting to an end crystal.
     */
    public static float getEnderDragonEndCrystalHealingValue() {
        return isDoomMode() ? 1.7F : 0.1F;
    }

    /**
     * @return how much damage the ender dragon takes when connected to an end crystal and that end crystal is destroyed.
     */
    public static float getEnderDragonDestroyedEndCrystalDamageValue() {
        return isDoomMode() ? 3.0F : 20.0F;
    }

    /**
     * @return how long the ender dragon should stay sitting.
     */
    public static float getEnderDragonSittingTime() {
        if (options().advanced.longerDragonPerchStayTime.getCurrentValue()) {
            return isDoomMode() ? 0.18F : 0.60F;
        } else {
            return 0.25F;
        }
    }

    /**
     * @return the damage that an ender pearl does to the thrower when landing (each 0.5 = half a heart).
     */
    public static float getEnderPearlDamageValue() {
        return isDoomMode() ? 5.0F : 2.0F;
    }

    /**
     * @return the maximum health for the wither.
     */
    public static double getWitherMaxHealth() {
        return isDoomMode() ? 250.0D : 100.0D;
    }

    /**
     * @return the distance in blocks that a zombified piglin must be in from a piglin in order to get scared and run away.
     */
    public static double getZombifiedPiglinRunawayDistance() {
        return options().advanced.decreasedZombifiedPiglinScareDistance.getCurrentValue() ? 2.0D : 6.0D;
    }
}