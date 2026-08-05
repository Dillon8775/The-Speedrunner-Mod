package net.dillon.speedrunnermod.helper;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.entity.goliath.Minion;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.network.DedicatedServerStorage;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.triggers.CriteriaTriggers;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Helper methods for various different things (ex. items and math calculations)
 */
public class ModHelper {
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
            if (alive || newPlayer.level().getGameRules().get(GameRules.KEEP_INVENTORY)) {
                return; // Not a death
            }

            if (hasInventoryPreserver(oldPlayer)) {
                copyInventory(oldPlayer, newPlayer);
                ((InventoryPreserver)newPlayer).removeInventoryPreserver();
                TaskScheduler.schedule(1, () -> {
                    newPlayer.level().playSound(null, newPlayer.getX(), newPlayer.getY(), newPlayer.getZ(), SoundEvents.RESPAWN_ANCHOR_DEPLETE.value(), SoundSource.PLAYERS, 3.0F, 1.0F);
                    ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(newPlayer, new ItemStack(ModItems.INVENTORY_PRESERVER));
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
                        ModHelper.roundToNearestTenthsPlace(x),
                        ModHelper.roundToNearestTenthsPlace(y),
                        ModHelper.roundToNearestTenthsPlace(z))
                .setStyle(Style.EMPTY
                        .withHoverEvent(new HoverEvent.ShowText(Component.translatable("speedrunnermod.teleport_to_player_death_cords")))
                        .withClickEvent(new ClickEvent.SuggestCommand("/teleport @s " + x + " " + y + " " + z)));
    }

    /**
     * Returns a specific type of formatting.
     */
    public static ChatFormatting toFormatting(UUID uuid, ChatFormatting actionbar, ChatFormatting chat) {
        return DedicatedServerStorage.shouldShowInActionbar(uuid) ? actionbar : chat;
    }

    /**
     * Sends a warning message to the player, that the eye item is calculating.
     */
    public static void sendCalculatingMessage(Player player) {
        if (DedicatedServerStorage.shouldReceiveWarningMessages(player.getUUID())) {
            player.sendSystemMessage(ModTexts.CALCULATING);
        }
    }

    /**
     * Sends a oldPlayer message with the actionbar preference and formatting.
     */
    public static void sendMessageWithActionbarPref(Player player, Component text) {
        if (DedicatedServerStorage.shouldShowInActionbar(player.getUUID())) {
            player.sendOverlayMessage(text);
        } else {
            player.sendSystemMessage(text);
        }
    }

    /**
     * Sends a oldPlayer message with the actionbar preference and formatting with formatting for actionbar on/off.
     */
    public static void sendMessageWithActionbarPref(Player player, Component text, ChatFormatting actionbar, ChatFormatting chat) {
        Component style = text.copy().withStyle(ModHelper.toFormatting(player.getUUID(), actionbar, chat));
        if (DedicatedServerStorage.shouldShowInActionbar(player.getUUID())) {
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
     * @return true if a zombie {@link Minion}, near the ender dragon.
     */
    public static boolean isZombieMinionAlive(EnderDragon dragon) {
        List<Zombie> zombies = getEntitiesWithinRange(dragon.level(), Zombie.class, dragon, options().advanced.dragonImmunityDetectionRadiusForGoliath.getCurrentValue());

        for (Zombie zombie : zombies) {
            if (zombie instanceof Minion minion && minion.isGoliathMinion()) {
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
     * @return an unbreakable stack.
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
     * Returns flight duration 3 firework rockets.
     */
    public static ItemStack fireworkWithFlightDuration(int count) {
        ItemStack fireworks = new ItemStack(Items.FIREWORK_ROCKET, count);
        fireworks.set(DataComponents.FIREWORKS, new Fireworks(3, List.of()));
        return fireworks;
    }

    /**
     * @return a raw {@link List} of entities (excluding {@code named entities}) within a specified range.
     */
    public static List getEntitiesWithinRange(Level world, Class<? extends LivingEntity> entityListOf, LivingEntity startingPoint, List<Integer> xyz) {
        return getEntitiesWithinRange(world, entityListOf, startingPoint, xyz, e -> !e.hasCustomName());
    }

    /**
     * @return a raw {@link List} of entities (excluding {@code named entities}) within a specified range, and a custom predicate.
     * @param world level reference
     * @param entityListOf the list of entities to return, by class
     * @param startingPoint the entity that the game should start searching from
     * @param xyz an array of the maximum {@code x, y, and z} search radius
     */
    public static List getEntitiesWithinRange(Level world, Class<? extends LivingEntity> entityListOf, LivingEntity startingPoint, List<Integer> xyz, Predicate<? super LivingEntity> selector) {
        return world.getEntitiesOfClass(entityListOf, startingPoint.getBoundingBox().inflate(
                        xyz.getFirst(),
                        xyz.get(1),
                        xyz.get(2)),
                selector);
    }

    /**
     * Modifies the {@code experiencePoints} variable in {@link Mob}.
     */
    public static int modifyDroppedExperiencePoints(Mob reference, LivingEntity attacker, int base, int multiplier) {
        return base + EnchantmentHelper.getEnchantmentLevel(ModHelper.enchantment(reference, Enchantments.LOOTING), attacker) * multiplier;
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
     * @return a value at minimum.
     */
    public static int atLeast(int value, int min) {
        if (value < min) {
            value = min;
        }

        return value;
    }

    /**
     * @return a value at maximum.
     */
    public static int atMost(int value, int max) {
        if (value > max) {
            value = max;
        }

        return value;
    }

    /**
     * @return a value at a clamped value.
     */
    public static int clamp(int value, int min, int max) {
        if (value < min) {
            value = min;
        }

        if (value > max) {
            value = max;
        }

        return value;
    }

    /**
     * Rounds the inputted number to the nearest one decimal place (or nearest tenths place)
     */
    public static double roundToNearestTenthsPlace(double number) {
        return Math.round(number * 10.0D) / 10.0D;
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
    @Deprecated
    public static List<Integer> createListOption(int negX, int negY, int negZ, int posX, int posY, int posZ) {
        return List.of(negX, negY, negZ, posX, posY, posZ);
    }

    /**
     * Creates a new {@code structure spawn rate option.}
     */
    public static List<Integer> createStructureSpawnRateOption(int spacing, int separation) {
        return List.of(spacing, separation);
    }
}