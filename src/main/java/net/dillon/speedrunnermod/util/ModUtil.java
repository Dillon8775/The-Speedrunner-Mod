package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.server.ServerStorage;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.entity.projectile.hurtingprojectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.entity.projectile.hurtingprojectile.LargeFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * Helper methods for various different things (ex. items and math calculations)
 */
public class ModUtil {
    public static int errorMessagesSent = 0;
    public static final String CONFIG_FILE_NAME = "speedrunnermod.json";
    public static final String CLIENT_CONFIG_FILE_NAME = "speedrunnermod_client.json";

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

    public static double[] latestDeathCords = new double[]{0, 0, 0};

    /**
     * Locates structures.
     */
    @Author(Authors.KWPUGH)
    public static void findStructureAndShoot(Level world, Player player, ItemStack itemstack, TagKey<Structure> type) {
        BlockPos playerpos = player.blockPosition();
        ServerLevel serverWorld = (ServerLevel)world;
        BlockPos locpos = serverWorld.findNearestMapStructure(type, playerpos, 100, false);

        EyeOfEnder finderentity = new EyeOfEnder(world, player.getX(), player.getY(0.5D), player.getZ());
        finderentity.setItem(itemstack);
        Vec3 vec3d = new Vec3(locpos.getX(), locpos.getY(), locpos.getZ());
        finderentity.signalTo(vec3d);
        world.addFreshEntity(finderentity);

        if (player instanceof ServerPlayer) {
            CriteriaTriggers.USED_ENDER_EYE.trigger((ServerPlayer)player, locpos);
        }

        world.levelEvent(null, 1003, player.blockPosition(), 0);
    }

    /**
     * Registers the {@code Inventory Preservers} function.
     */
    public static void registerInventoryPreserver() {
        ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
            if (alive) {
                return; // Not a death
            }

            if (hasInventoryPreserver(oldPlayer)) {
                copyInventory(oldPlayer, newPlayer);
                ((InventoryPreserver)newPlayer).removeInventoryPreserver();
                TaskScheduler.schedule(1, () -> {
                    newPlayer.level().playSound(null, newPlayer.getX(), newPlayer.getY(), newPlayer.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.PLAYERS, 3.0F, 1.0F);
                    ModCriterions.TRIGGERED_BY_ITEM.trigger(newPlayer, new ItemStack(ModItems.INVENTORY_PRESERVER));
                });
            }
        });
    }

    /**
     * Copies inventory from {@code oldPlayer} to {@code newPlayer.}
     */
    private static void copyInventory(ServerPlayer oldPlayer, ServerPlayer newPlayer) {
        newPlayer.getInventory().replaceWith(oldPlayer.getInventory());
    }

    /**
     * @return true if the oldPlayer's inventory contains a {@code Inventory Preserver.}
     */
    private static boolean hasInventoryPreserver(Player oldPlayer) {
        return ((InventoryPreserver)(oldPlayer)).hadInventoryPreserver();
    }

    /**
     * Returns the oldPlayer's death coordinates as a clickable text to teleport right to it.
     */
    public static Component deathCords(double x, double y, double z) {
        return Component.translatable("speedrunnermod.player_death_cords",
                        ModUtil.roundToNearestTenthsPlace(x),
                        ModUtil.roundToNearestTenthsPlace(y),
                        ModUtil.roundToNearestTenthsPlace(z))
                .setStyle(Style.EMPTY
                        .withHoverEvent(new HoverEvent.ShowText(Component.translatable("speedrunnermod.teleport_to_player_death_cords")))
                        .withClickEvent(new ClickEvent.SuggestCommand("/teleport @s " + x + " " + y + " " + z)));
    }

    /**
     * Sends a message to the oldPlayer with the mod prefix.
     */
    public static void sendWithPrefix(String string, Player player) {
        player.sendSystemMessage((ModTexts.BLANK).copy().append((Component.translatable("speedrunnermod.tutorial_mode.prefix"))).append("").append(Component.translatable(string)));
    }

    /**
     * Returns a specific type of formatting.
     */
    public static ChatFormatting toFormatting(UUID uuid, ChatFormatting actionbar, ChatFormatting chat) {
        return ServerStorage.shouldShowInActionbar(uuid) ? actionbar : chat;
    }

    /**
     * Sends a oldPlayer message with the actionbar preference and formatting.
     */
    public static void sendMessageWithActionbarPref(Player player, Component text) {
        if (ServerStorage.shouldShowInActionbar(player.getUUID())) {
            player.sendOverlayMessage(text);
        } else {
            player.sendSystemMessage(text);
        }
    }

    /**
     * Sends a oldPlayer message with the actionbar preference and formatting with formatting for actionbar on/off.
     */
    public static void sendMessageWithActionbarPref(Player player, Component text, ChatFormatting actionbar, ChatFormatting chat) {
        Component style = text.copy().withStyle(ModUtil.toFormatting(player.getUUID(), actionbar, chat));
        if (ServerStorage.shouldShowInActionbar(player.getUUID())) {
            player.sendOverlayMessage(style);
        } else {
            player.sendSystemMessage(style);
        }
    }

    /**
     * @return true if a dragon is alive, near the ender dragon.
     */
    public static boolean isGiantAlive(EnderDragon dragon) {
        List<Giant> giants = getEntitiesWithinRange(dragon.level(), Giant.class, dragon, options().advanced.dragonImmunityDetectionRadiusForGoliath.getCurrentValue());

        for (Giant giant : giants) {
            if (giant.isAlive()) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return true if a wither is alive, near the ender dragon.
     */
    public static boolean isWitherAlive(EnderDragon dragon) {
        List<WitherBoss> withers = getEntitiesWithinRange(dragon.level(), WitherBoss.class, dragon, options().advanced.dragonImmunityDetectionRadiusForWither.getCurrentValue());

        for (WitherBoss wither : withers) {
            if (wither.isAlive()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a giant and/or a wither are alive.
     * <p>If either are present, the ender dragon {@code cannot die.}</p>
     */
    public static boolean isGiantOrWitherAlive(EnderDragon dragon) {
        return isGiantAlive(dragon) || isWitherAlive(dragon);
    }

    /**
     * @return {@code enchantment} with the use of the {@link Entity} or {@link Level} class.
     * @param entityOrWorld should never be anything other than {@link Entity} or {@link Level}.
     * @param enchantment the enchantment that should be returned.
     */
    @Deprecated
    public static Holder<Enchantment> enchantment(@NotNull Object entityOrWorld, @NotNull ResourceKey<Enchantment> enchantment) {
        try {
            Optional<Holder.Reference<Enchantment>> optional =
                    entityOrWorld instanceof Entity entity ? entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(enchantment.identifier()) :
                            entityOrWorld instanceof Level world ? world.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).get(enchantment.identifier()) :
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
            Level world = null;
            if (entityOrWorld instanceof Entity entity) {
                world = entity.level();
            } else if (entityOrWorld instanceof Level w) {
                world = w;
            }
            if (world != null) {
                return world.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .get(Enchantments.LOOTING.identifier())
                        .orElseThrow();
            }
            throw o;
        }
    }

    /**
     * @return an unbreakable item.
     */
    public static ItemStack ofUnbreakable(Item item) {
        ItemStack stack = new ItemStack(item);
        stack.set(DataComponents.UNBREAKABLE, Unit.INSTANCE);
        if (item == Items.ELYTRA) {
            stack.set(DataComponents.ITEM_NAME, Component.translatable("item.speedrunnermod.icarus_wings"));
        }
        return stack;
    }

    /**
     * Spawns a {@code floating item entity} from the oldPlayer's position.
     */
    public static void spawnFloatingItemEntity(Level world, ItemStack stack, Player player) {
        spawnFloatingItemEntity(world, player.blockPosition(), stack, player, false);
    }

    /**
     * Spawns a {@code floating item entity} from the {@link BlockPos}'s position.
     */
    public static void spawnFloatingItemEntity(Level world, BlockPos pos, ItemStack stack, Player player, boolean playSound) {
        ItemEntity item = new ItemEntity(world, pos.getX() + 0.5F, pos.getY() + 3.0F, pos.getZ() + 0.5F, stack);
        item.setInvulnerable(true);
        item.setGlowingTag(true);
        item.setNoGravity(true);
        item.setUnlimitedLifetime();

        Vec3 itemPos = item.position();
        Vec3 playerPos = player.position();
        Vec3 motion = playerPos.subtract(itemPos).normalize().scale(0.1D);
        item.setDeltaMovement(motion.x, motion.y, motion.z);

        if (playSound) {
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_HIT_PLAYER, SoundSource.NEUTRAL, 3.0F, 1.0F);
        }
        world.addFreshEntity(item);
    }

    /**
     * Returns flight duration 3 firework rockets.
     */
    public static ItemStack fireworkWithFlightDuration(int count) {
        ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, count);
        fireworks.set(DataComponents.FIREWORKS, new Fireworks(3, List.of()));
        return fireworks;
    }

    /**
     * Creates a fireball entity.
     */
    public static boolean createFireball(Item item, Level world, Player player, InteractionHand hand, boolean dragon) {
        ItemStack stack = player.getItemInHand(hand);
        if (!world.isClientSide()) {
            Vec3 lookVec = player.getViewVector(1.0F);
            AbstractHurtingProjectile fireball = new LargeFireball(world, player, lookVec.normalize(), options().advanced.fireballExplosionPower.getCurrentValue());
            if (dragon) {
                fireball = new DragonFireball(world, player, lookVec.normalize());
            } else {
                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayer) player, stack);
            }
            fireball.absSnapTo(player.getX(), player.getEyeY() - 0.235, player.getZ());
            fireball.setOwner(player);
            world.addFreshEntity(fireball);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

            player.getCooldowns().addCooldown(item.getDefaultInstance(), ModUtil.secondsAsTicks(dragon ? 5 : 1));
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            player.swing(hand);

            return true;
        }

        return false;
    }

    /**
     * @return true if dragon aura is found on potion contents.
     */
    public static boolean hasDragonsAura(ItemStack stack) {
        for (MobEffectInstance slotEffect : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
            if (slotEffect.getEffect().is(ofSpeedrunnerMod("dragons_aura"))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a raw {@link List} of entities (excluding {@code named entities}) within a specified range.
     * @param world world reference
     * @param entityListOf the list of entities to return, by class
     * @param startingPoint the entity that the game should start searching from
     * @param xyz an array of the maximum {@code x, y, and z} search radius
     */
    public static List getEntitiesWithinRange(Level world, Class<? extends LivingEntity> entityListOf, LivingEntity startingPoint, List<Integer> xyz) {
        return world.getEntitiesOfClass(entityListOf, startingPoint.getBoundingBox().inflate(
                        xyz.getFirst(),
                        xyz.get(1),
                        xyz.get(2)),
                e -> !e.hasCustomName());
    }

    /**
     * @return the item cooldown with the cooldown enchantment.
     */
    public static int getItemCooldown(Player playerEntity) {
        int coolEnchantment = EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment(playerEntity, ModEnchantments.COOLDOWN), playerEntity);
        return coolEnchantment > 3 ? 0 : coolEnchantment == 3 ? 5 : coolEnchantment == 2 ? 10 : coolEnchantment == 1 ? 15 : 20;
    }

    /**
     * Applies the correct shield cooldown.
     */
    public static void applyItemCooldown(Player playerEntity, ItemStack shield) {
        playerEntity.getCooldowns().addCooldown(shield, getItemCooldown(playerEntity));
    }

    /**
     * Modifies the {@code maximum health} of an entity.
     */
    public static void modifyMaxHealth(LivingEntity entity, double health) {
        if (entity.getAttribute(Attributes.MAX_HEALTH) != null) {
            entity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
        }
        entity.setHealth((float)entity.getAttribute(Attributes.MAX_HEALTH).getBaseValue());
    }

    /**
     * Modifies the {@code generic movement speed} of an entity.
     */
    public static void modifyMovementSpeed(LivingEntity entity, double speed) {
        if (entity.getAttribute(Attributes.MOVEMENT_SPEED) != null) {
            entity.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
        }
        entity.setSpeed((float)entity.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue());
    }

    /**
     * Modifies the {@code follow range} of an entity.
     */
    public static void modifyFollowRange(LivingEntity entity, double range) {
        if (entity.getAttribute(Attributes.FOLLOW_RANGE) != null) {
            entity.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(range);
        }
    }

    /**
     * Modifies the {@code attack damage} of an entity.
     */
    public static void modifyAttackDamage(LivingEntity entity, double attackDamage) {
        if (entity.getAttribute(Attributes.ATTACK_DAMAGE) != null) {
            entity.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
        }
    }

    /**
     * Modifies the {@code attack knockback} of an entity.
     */
    public static void modifyAttackKnockback(LivingEntity entity, double attackKnockback) {
        if (entity.getAttribute(Attributes.ATTACK_KNOCKBACK) != null) {
            entity.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(attackKnockback);
        }
    }

    /**
     * Modifies the {@code knockback resistance} of an entity.
     */
    public static void modifyKnockbackResistance(LivingEntity entity, double resistance) {
        if (entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE) != null) {
            entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE).setBaseValue(resistance);
        }
    }

    /**
     * Modifies the {@code armor attribute} of an entity.
     */
    public static void modifyArmor(LivingEntity entity, double value) {
        if (entity.getAttribute(Attributes.ARMOR) != null) {
            entity.getAttribute(Attributes.ARMOR).setBaseValue(value);
        }
    }

    /**
     * Modifies the {@code experiencePoints} variable in {@link Mob}.
     */
    public static int modifyExperiencePoints(Mob reference, LivingEntity attacker, int base, int multiplier) {
        return base + EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment(reference, Enchantments.LOOTING), attacker) * multiplier;
    }

    /**
     * See {@code ender eye items} for more.
     */
    @Author(Authors.KWPUGH)
    public static float getDistance(int x1, int z1, int x2, int z2) {
        int i = x2 - x1;
        int j = z2 - z1;

        return Mth.sqrt((float) (i * i + j * j));
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
    public static float randomFloatInclusive(float min, float max) {
        Random random = new Random();
        return min + random.nextFloat() * (max - min);
    }

    /**
     * @return a random int, with a minimum and maximum value.
     */
    public static int randomIntInclusive(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
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
    public static float getBedBlockExplosionPower(Level world) {
        if (isDoomMode()) {
            return world.dimension() == Level.END ? 15.0F : 5.0F;
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
     * @return how long it takes for a oldPlayer to lose an air bubble (in seconds).
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
     * @return the follow range for the ender dragon.
     */
    public static double getEnderDragonFollowRange() {
        return isDoomMode() ? 64.0D : 16.0D;
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
        return options().advanced.longerDragonPerchStayTime.getCurrentValue() ? 0.60F : 0.25F;
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