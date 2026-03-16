package net.dillon.speedrunnermod.mixin.main.trades;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.village.ModTradeOffers;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.Util;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.entity.npc.villager.VillagerType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.saveddata.maps.MapDecorationTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * <p>Makes default Minecraft villager trades, better!</p>
 * <p>Turns out, Minecraft's code is broken, so I had to fix it.</p>
 * Note: {@link VillagerTrades.EmeraldsForVillagerTypeItem} wasn't working correctly, so I had to cast the {@link ImmutableMap#builder()} to a default {@link Map} in order for it to work.
 */
@Mixin(VillagerTrades.class)
public class VillagerTradesMixin {
    @Shadow
    public static final Map<ResourceKey<VillagerProfession>, Int2ObjectMap<VillagerTrades.ItemListing[]>> TRADES = options().main.betterVillagerTrades.getCurrentValue() ? Util.make(Maps.newHashMap(), map -> {
        map.put(VillagerProfession.FARMER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.WHEAT, 2, 24, 2),
                                new VillagerTrades.EmeraldForItems(Items.POTATO, 3, 24, 2),
                                new VillagerTrades.EmeraldForItems(Items.CARROT, 3, 24, 2),
                                new VillagerTrades.EmeraldForItems(Items.BEETROOT, 2, 24, 2),
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(Items.EMERALD, 3, 16, 3),
                                new VillagerTrades.ItemsForEmeralds(Items.BREAD, 1, 6, 24, 3)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Blocks.PUMPKIN, 2, 16, 10),
                                new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN_PIE, 1, 6, 6),
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(Items.APPLE, 1, 4, 24, 7)},
                        3, new VillagerTrades.ItemListing[]{
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(Items.COOKIE, 1, 24, 11),
                                new VillagerTrades.EmeraldForItems(Blocks.MELON, 2, 16, 20)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Blocks.CAKE, 1, 1, 16, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.NIGHT_VISION, 210, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.JUMP_BOOST, 230, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.SPEED, 220, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.STRENGTH, 200, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.HASTE, 320, 15),
                                new VillagerTrades.SuspiciousStewForEmerald(MobEffects.SATURATION, 9, 15)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Items.GOLDEN_CARROT, 1, 4, 30),
                                new VillagerTrades.ItemsForEmeralds(Items.GLISTERING_MELON_SLICE, 1, 4, 30)})));

        map.put(VillagerProfession.FISHERMAN, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.STRING, 2, 32, 2),
                                new VillagerTrades.EmeraldForItems(Items.COAL, 1, 32, 2),
                                new VillagerTrades.ItemsAndEmeraldsToItems(Items.BEEF, 4, 1, Items.COOKED_BEEF, 4, 6, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.COD_BUCKET, 1, 1, 18, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.BEEF, 2, 32, 10),
                                new VillagerTrades.ItemsAndEmeraldsToItems(Items.APPLE, 4, 1, Items.GOLDEN_APPLE, 4, 6, 16, 5),
                                new VillagerTrades.ItemsForEmeralds(Items.CAMPFIRE, 1, 1, 5),
                                new VillagerTrades.ItemsForEmeralds(ModItems.SPEEDRUNNER_BULK, 9, 1, 8, 10)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.PORKCHOP, 2, 32, 20),
                                new ModTradeOffers.SellMaxedEnchantedToolFactory(Items.FISHING_ROD, 4, 4, 16, EnchantmentTags.TRADEABLE)},
                        4, new VillagerTrades.ItemListing[]{
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(Items.EMERALD, 3, 24, 30)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.PUFFERFISH, 2, 16, 30),
                                new VillagerTrades.EmeraldsForVillagerTypeItem(1, 12, 30,
                                        (Map) ImmutableMap.builder()
                                                .put(VillagerType.PLAINS, Items.OAK_BOAT)
                                                .put(VillagerType.TAIGA, Items.SPRUCE_BOAT)
                                                .put(VillagerType.SNOW, Items.SPRUCE_BOAT)
                                                .put(VillagerType.DESERT, Items.JUNGLE_BOAT)
                                                .put(VillagerType.JUNGLE, Items.JUNGLE_BOAT)
                                                .put(VillagerType.SAVANNA, Items.ACACIA_BOAT)
                                                .put(VillagerType.SWAMP, Items.DARK_OAK_BOAT).build())})));

        map.put(VillagerProfession.SHEPHERD, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Blocks.WHITE_WOOL, 4, 24, 2),
                                new VillagerTrades.EmeraldForItems(Blocks.BROWN_WOOL, 4, 24, 2),
                                new VillagerTrades.EmeraldForItems(Blocks.BLACK_WOOL, 4, 24, 2),
                                new VillagerTrades.EmeraldForItems(Blocks.GRAY_WOOL, 4, 24, 2),
                                new ModTradeOffers.SellItemFactoryIronIngot(Items.SHEARS, 1, 1, 1),
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(ModItems.SPEEDRUNNER_SHEARS, 1, 1, 2)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.WHITE_DYE, 2, 24, 10),
                                new VillagerTrades.EmeraldForItems(Items.GRAY_DYE, 2, 24, 10),
                                new VillagerTrades.EmeraldForItems(Items.BLACK_DYE, 2, 24, 10),
                                new VillagerTrades.EmeraldForItems(Items.LIGHT_BLUE_DYE, 2, 24, 10),
                                new VillagerTrades.EmeraldForItems(Items.LIME_DYE, 2, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIME_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PINK_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.RED_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_WOOL, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIME_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PINK_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.RED_CARPET, 1, 4, 24, 5),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_CARPET, 1, 4, 24, 5)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.YELLOW_DYE, 1, 24, 20),
                                new VillagerTrades.EmeraldForItems(Items.LIGHT_GRAY_DYE, 1, 24, 20),
                                new VillagerTrades.EmeraldForItems(Items.ORANGE_DYE, 1, 24, 20),
                                new VillagerTrades.EmeraldForItems(Items.RED_DYE, 1, 24, 20),
                                new VillagerTrades.EmeraldForItems(Items.PINK_DYE, 1, 24, 20),
                                new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.RED_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIME_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PINK_BED, 1, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_BED, 1, 1, 24, 10)},

                        4,new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.BROWN_DYE, 1, 32, 30),
                                new VillagerTrades.EmeraldForItems(Items.PURPLE_DYE, 1, 32, 30),
                                new VillagerTrades.EmeraldForItems(Items.BLUE_DYE, 1, 32, 30),
                                new VillagerTrades.EmeraldForItems(Items.GREEN_DYE, 1, 32, 30),
                                new VillagerTrades.EmeraldForItems(Items.MAGENTA_DYE, 1, 32, 30),
                                new VillagerTrades.EmeraldForItems(Items.CYAN_DYE, 1, 32, 30),
                                new VillagerTrades.ItemsForEmeralds(Items.WHITE_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BLUE_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.RED_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.PINK_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.GREEN_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIME_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.GRAY_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BLACK_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.PURPLE_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.CYAN_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BROWN_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.YELLOW_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.ORANGE_BANNER, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_BANNER, 1, 1, 24, 15)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Items.PAINTING, 2, 4, 32, 30)})));

        map.put(VillagerProfession.FLETCHER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.STICK, 4, 32, 3),
                                new VillagerTrades.ItemsForEmeralds(Items.ARROW, 1, 32, 2),
                                new VillagerTrades.ItemsAndEmeraldsToItems(Blocks.GRAVEL, 1, 1, Items.FLINT, 1, 1, 24, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.FLINT, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Items.BOW, 1, 1, 5),
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(ModItems.SPEEDRUNNER_BOW, 3, 1, 6)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.STRING, 14, 16, 20),
                                new VillagerTrades.ItemsForEmeralds(Items.CROSSBOW, 1, 1, 10),
                                new ModTradeOffers.SellItemFactorySpeedrunnerIngot(ModItems.SPEEDRUNNER_CROSSBOW, 3, 1, 11)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.FEATHER, 24, 16, 30),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.BOW, 1, 6, 15),
                                new VillagerTrades.EnchantedItemForEmeralds(ModItems.SPEEDRUNNER_BOW, 1, 6, 20)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.TRIPWIRE_HOOK, 2, 24, 30),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.CROSSBOW, 1, 6, 15),
                                new VillagerTrades.EnchantedItemForEmeralds(ModItems.SPEEDRUNNER_CROSSBOW, 1, 6, 25),
                                new VillagerTrades.TippedArrowForItemsAndEmeralds(Items.ARROW, 1, Items.TIPPED_ARROW, 4, 1, 32, 40)})));

        map.put(VillagerProfession.LIBRARIAN, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.PAPER, 3, 32, 2),
                                new ModTradeOffers.EnchantedBookFactory(2, 3, 0.01F, 12, EnchantmentTags.TRADEABLE),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BOOKSHELF, 3, 1, 24, 2)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.BOOK, 4, 32, 10),
                                new ModTradeOffers.EnchantedBookFactory(1, 12, 0.01F, 12, EnchantmentTags.TRADEABLE),
                                new VillagerTrades.ItemsForEmeralds(Items.LANTERN, 1, 4, 24, 5)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.INK_SAC, 6, 24, 20),
                                new ModTradeOffers.EnchantedBookFactory(1, 20, 0.01F, 12, EnchantmentTags.TRADEABLE),
                                new VillagerTrades.ItemsForEmeralds(Items.GLASS, 1, 4, 64,10)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.WRITABLE_BOOK, 1, 32, 30),
                                new ModTradeOffers.EnchantedBookFactory(1, 32, 0.01F, 12, EnchantmentTags.TRADEABLE),
                                new VillagerTrades.ItemsForEmeralds(Items.CLOCK, 5, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.COMPASS, 4, 1, 15)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Items.NAME_TAG, 1, 2, 40)})));

        map.put(VillagerProfession.CARTOGRAPHER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.PAPER, 2, 24, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.MAP, 1, 1, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.GLASS_PANE, 1, 32, 10),
                                new VillagerTrades.TreasureMapForEmeralds(2, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.OCEAN_MONUMENT, 12, 5)},
                        3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COMPASS, 1, 12, 20),
                                new VillagerTrades.TreasureMapForEmeralds(3, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.WOODLAND_MANSION, 12, 10),
                                new VillagerTrades.ItemsForEmeralds(ModItems.SPEEDRUNNERS_EYE, 2, 1, 20)},
                        4, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.ITEM_FRAME, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.WHITE_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BLUE_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.RED_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.PINK_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.GREEN_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIME_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.GRAY_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BLACK_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.PURPLE_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.CYAN_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.BROWN_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.YELLOW_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.ORANGE_BANNER, 1, 1, 15),
                                new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_BANNER, 1, 1, 15)},
                        5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.GLOBE_BANNER_PATTERN, 2, 1, 30)})));

        map.put(VillagerProfession.CLERIC, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.ROTTEN_FLESH, 3, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.REDSTONE, 1, 4, 32, 21)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 2, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(Items.LAPIS_LAZULI, 1, 2, 8)},
                        3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.RABBIT_FOOT, 2, 12, 20),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GLOWSTONE, 1, 4, 32, 10)},
                        4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.TURTLE_SCUTE, 1, 16, 30),
                                new VillagerTrades.EmeraldForItems(Items.GLASS_BOTTLE, 3, 32, 30),
                                new VillagerTrades.ItemsForEmeralds(Items.ENDER_PEARL, 1, 1, 8, 15)},
                        5, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.NETHER_WART, 4, 32, 30),
                                new VillagerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 2, 1, 32, 30)})));

        map.put(VillagerProfession.ARMORER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.COAL, 2, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_LEGGINGS), 2, 1, 16, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_BOOTS), 1, 1, 16, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_HELMET), 1, 1, 16, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_CHESTPLATE), 2, 1, 16, 1, 0.01F)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 1, 32, 10),
                                new VillagerTrades.EmeraldForItems(ModItems.SPEEDRUNNER_INGOT, 1, 32, 12),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 4, 1, 24, 5, 0.1F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 24, 5, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_LEGGINGS), 2, 1, 24, 5, 0.01F)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.LAVA_BUCKET, 1, 32, 20),
                                new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 24, 20),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 24, 10, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 2, 1, 24, 10, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.SHIELD), 1, 1, 24, 10, 0.1F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(ModItems.SPEEDRUNNER_SHIELD), 1, 1, 32, 12, 0.15F)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_LEGGINGS, 4, 16, 15, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_BOOTS, 3, 16, 15, 0.01F)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_HELMET, 3, 16, 50, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_CHESTPLATE, 5, 16, 50, 0.1F)})));

        map.put(VillagerProfession.WEAPONSMITH, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.COAL, 2, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_AXE), 1, 1, 24, 2, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SWORD, 2, 24, 2)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 1, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 4, 1, 24, 5, 0.01F)},
                        3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FLINT, 1, 32, 20)},
                        4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 24, 30),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_AXE, 4, 16, 30, 0.01F)},
                        5, new VillagerTrades.ItemListing[]{new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_SWORD, 3, 16, 30, 0.01F)})));

        map.put(VillagerProfession.TOOLSMITH, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.COAL, 2, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_AXE), 1, 1, 24, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_SHOVEL), 1, 1, 24, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_PICKAXE), 1, 1, 24, 1, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_HOE), 1, 1, 24, 1, 0.01F)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 1, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 4, 1, 24, 5, 0.01F)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.FLINT, 1, 32, 20),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_AXE, 2, 24, 10, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SHOVEL, 1, 24, 10, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_PICKAXE, 1, 24, 10, 0.01F),
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.DIAMOND_HOE), 1, 1, 24, 10, 0.1F)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 24, 30),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_AXE, 2, 24, 15, 0.01F),
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_SHOVEL, 1, 24, 15, 0.01F)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_PICKAXE, 2, 24, 50, 0.01F)})));

        map.put(VillagerProfession.BUTCHER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Items.COOKED_CHICKEN, 1, 8, 32,2),
                                new VillagerTrades.ItemsForEmeralds(Items.COOKED_PORKCHOP, 1, 8, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.COOKED_RABBIT, 1, 6, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.RABBIT_STEW, 1, 1, 24, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.COAL, 2, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.COOKED_BEEF, 1, 8, 32, 5),
                                new VillagerTrades.ItemsForEmeralds(Items.GOLDEN_CARROT, 2, 8, 24, 5)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Items.MUTTON, 3, 8, 32, 20)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.DRIED_KELP_BLOCK, 1, 24, 30)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.SWEET_BERRIES, 1, 32, 30)})));

        map.put(VillagerProfession.LEATHERWORKER, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.LEATHER, 2, 32, 2),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_LEGGINGS, 1),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_CHESTPLATE, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.FLINT, 1, 32, 10),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HELMET, 1, 24, 5),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_BOOTS, 1, 24, 5)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.RABBIT_HIDE, 1, 32, 20),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_CHESTPLATE, 1)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.TURTLE_SCUTE, 1, 24, 30),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HORSE_ARMOR, 1, 24, 15)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.SADDLE), 1, 1, 32, 30, 0.01F),
                                new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HELMET, 1, 24, 30)})));

        map.put(VillagerProfession.MASON, VillagerTrades.toIntMap(
                ImmutableMap.of(
                        1, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.CLAY_BALL, 2, 32, 2),
                                new VillagerTrades.ItemsForEmeralds(Items.BRICK, 1, 16, 32, 1)},
                        2, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Blocks.STONE, 1, 24, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CHISELED_STONE_BRICKS, 1, 4, 64, 5)},
                        3, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Blocks.GRANITE, 4, 32, 20),
                                new VillagerTrades.EmeraldForItems(Blocks.ANDESITE, 4, 32, 20),
                                new VillagerTrades.EmeraldForItems(Blocks.DIORITE, 4, 32, 20),
                                new VillagerTrades.ItemsForEmeralds(Blocks.DRIPSTONE_BLOCK, 1, 4, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_ANDESITE, 1, 4, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_DIORITE, 1, 4, 32, 10),
                                new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_GRANITE, 1, 4, 32, 10)},
                        4, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.EmeraldForItems(Items.QUARTZ, 3, 32, 30),
                                new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.RED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PINK_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIME_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 24, 15),
                                new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 24, 15)},
                        5, new VillagerTrades.ItemListing[]{
                                new VillagerTrades.ItemsForEmeralds(Blocks.QUARTZ_PILLAR, 1, 4, 32, 30),
                                new VillagerTrades.ItemsForEmeralds(Blocks.QUARTZ_BLOCK, 1, 4, 32, 30)})));

    }) : /* If Better trades AREN't enabled, switch to default code, or default trades. */
            Util.make(Maps.newHashMap(), map -> {
        map.put(VillagerProfession.FARMER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.WHEAT, 20, 16, 2), new VillagerTrades.EmeraldForItems(Items.POTATO, 26, 16, 2), new VillagerTrades.EmeraldForItems(Items.CARROT, 22, 16, 2), new VillagerTrades.EmeraldForItems(Items.BEETROOT, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.BREAD, 1, 6, 16, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Blocks.PUMPKIN, 6, 12, 10), new VillagerTrades.ItemsForEmeralds(Items.PUMPKIN_PIE, 1, 4, 5), new VillagerTrades.ItemsForEmeralds(Items.APPLE, 1, 4, 16, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.COOKIE, 3, 18, 10), new VillagerTrades.EmeraldForItems(Blocks.MELON, 4, 12, 20)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Blocks.CAKE, 1, 1, 12, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.NIGHT_VISION, 100, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.JUMP_BOOST, 160, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.WEAKNESS, 140, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.BLINDNESS, 120, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.POISON, 280, 15), new VillagerTrades.SuspiciousStewForEmerald(MobEffects.SATURATION, 7, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.GOLDEN_CARROT, 3, 3, 30), new VillagerTrades.ItemsForEmeralds(Items.GLISTERING_MELON_SLICE, 4, 3, 30)})));
        map.put(VillagerProfession.FISHERMAN, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.STRING, 20, 16, 2), new VillagerTrades.EmeraldForItems(Items.COAL, 10, 16, 2), new VillagerTrades.ItemsAndEmeraldsToItems(Items.COD, 6, 1, Items.COOKED_COD, 6, 6, 16, 1), new VillagerTrades.ItemsForEmeralds(Items.COD_BUCKET, 3, 1, 16, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COD, 15, 16, 10), new VillagerTrades.ItemsAndEmeraldsToItems(Items.SALMON, 6, 1, Items.COOKED_SALMON, 6, 6, 16, 5), new VillagerTrades.ItemsForEmeralds(Items.CAMPFIRE, 2, 1, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.SALMON, 13, 16, 20), new VillagerTrades.EnchantedItemForEmeralds(Items.FISHING_ROD, 3, 3, 10, 0.2f)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.TROPICAL_FISH, 6, 12, 30)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.PUFFERFISH, 4, 12, 30), new VillagerTrades.EmeraldsForVillagerTypeItem(1, 12, 30, (Map)ImmutableMap.builder().put(VillagerType.PLAINS, Items.OAK_BOAT).put(VillagerType.TAIGA, Items.SPRUCE_BOAT).put(VillagerType.SNOW, Items.SPRUCE_BOAT).put(VillagerType.DESERT, Items.JUNGLE_BOAT).put(VillagerType.JUNGLE, Items.JUNGLE_BOAT).put(VillagerType.SAVANNA, Items.ACACIA_BOAT).put(VillagerType.SWAMP, Items.DARK_OAK_BOAT).build())})));
        map.put(VillagerProfession.SHEPHERD, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Blocks.WHITE_WOOL, 18, 16, 2), new VillagerTrades.EmeraldForItems(Blocks.BROWN_WOOL, 18, 16, 2), new VillagerTrades.EmeraldForItems(Blocks.BLACK_WOOL, 18, 16, 2), new VillagerTrades.EmeraldForItems(Blocks.GRAY_WOOL, 18, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.SHEARS, 2, 1, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.WHITE_DYE, 12, 16, 10), new VillagerTrades.EmeraldForItems(Items.GRAY_DYE, 12, 16, 10), new VillagerTrades.EmeraldForItems(Items.BLACK_DYE, 12, 16, 10), new VillagerTrades.EmeraldForItems(Items.LIGHT_BLUE_DYE, 12, 16, 10), new VillagerTrades.EmeraldForItems(Items.LIME_DYE, 12, 16, 10), new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIME_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.PINK_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.RED_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_WOOL, 1, 1, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIME_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.PINK_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.RED_CARPET, 1, 4, 16, 5), new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_CARPET, 1, 4, 16, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.YELLOW_DYE, 12, 16, 20), new VillagerTrades.EmeraldForItems(Items.LIGHT_GRAY_DYE, 12, 16, 20), new VillagerTrades.EmeraldForItems(Items.ORANGE_DYE, 12, 16, 20), new VillagerTrades.EmeraldForItems(Items.RED_DYE, 12, 16, 20), new VillagerTrades.EmeraldForItems(Items.PINK_DYE, 12, 16, 20), new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.RED_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.LIME_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.PINK_BED, 3, 1, 12, 10), new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_BED, 3, 1, 12, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.BROWN_DYE, 12, 16, 30), new VillagerTrades.EmeraldForItems(Items.PURPLE_DYE, 12, 16, 30), new VillagerTrades.EmeraldForItems(Items.BLUE_DYE, 12, 16, 30), new VillagerTrades.EmeraldForItems(Items.GREEN_DYE, 12, 16, 30), new VillagerTrades.EmeraldForItems(Items.MAGENTA_DYE, 12, 16, 30), new VillagerTrades.EmeraldForItems(Items.CYAN_DYE, 12, 16, 30), new VillagerTrades.ItemsForEmeralds(Items.WHITE_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.BLUE_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.RED_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.PINK_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.GREEN_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.LIME_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.GRAY_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.BLACK_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.PURPLE_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.CYAN_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.BROWN_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.YELLOW_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.ORANGE_BANNER, 3, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_BANNER, 3, 1, 12, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.PAINTING, 2, 3, 30)})));
        map.put(VillagerProfession.FLETCHER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.STICK, 32, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.ARROW, 1, 16, 1), new VillagerTrades.ItemsAndEmeraldsToItems(Blocks.GRAVEL, 10, 1, Items.FLINT, 10, 10, 12, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FLINT, 26, 12, 10), new VillagerTrades.ItemsForEmeralds(Items.BOW, 2, 1, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.STRING, 14, 16, 20), new VillagerTrades.ItemsForEmeralds(Items.CROSSBOW, 3, 1, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FEATHER, 24, 16, 30), new VillagerTrades.EnchantedItemForEmeralds(Items.BOW, 2, 3, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.TRIPWIRE_HOOK, 8, 12, 30), new VillagerTrades.EnchantedItemForEmeralds(Items.CROSSBOW, 3, 3, 15), new VillagerTrades.TippedArrowForItemsAndEmeralds(Items.ARROW, 5, Items.TIPPED_ARROW, 5, 2, 12, 30)})));
        map.put(VillagerProfession.LIBRARIAN, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.PAPER, 24, 16, 2), new VillagerTrades.EnchantBookForEmeralds(1, EnchantmentTags.TRADEABLE), new VillagerTrades.ItemsForEmeralds(Blocks.BOOKSHELF, 9, 1, 12, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.BOOK, 4, 12, 10), new VillagerTrades.EnchantBookForEmeralds(5, EnchantmentTags.TRADEABLE), new VillagerTrades.ItemsForEmeralds(Items.LANTERN, 1, 1, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.INK_SAC, 5, 12, 20), new VillagerTrades.EnchantBookForEmeralds(10, EnchantmentTags.TRADEABLE), new VillagerTrades.ItemsForEmeralds(Items.GLASS, 1, 4, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.WRITABLE_BOOK, 2, 12, 30), new VillagerTrades.EnchantBookForEmeralds(15, EnchantmentTags.TRADEABLE), new VillagerTrades.ItemsForEmeralds(Items.CLOCK, 5, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.COMPASS, 4, 1, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.NAME_TAG, 20, 1, 30)})));
        map.put(VillagerProfession.CARTOGRAPHER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.PAPER, 24, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.MAP, 7, 1, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.GLASS_PANE, 11, 16, 10), new VillagerTrades.TreasureMapForEmeralds(13, StructureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapDecorationTypes.OCEAN_MONUMENT, 12, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COMPASS, 1, 12, 20), new VillagerTrades.TreasureMapForEmeralds(14, StructureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapDecorationTypes.WOODLAND_MANSION, 12, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.ITEM_FRAME, 7, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.WHITE_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.BLUE_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.LIGHT_BLUE_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.RED_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.PINK_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.GREEN_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.LIME_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.GRAY_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.BLACK_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.PURPLE_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.MAGENTA_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.CYAN_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.BROWN_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.YELLOW_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.ORANGE_BANNER, 3, 1, 15), new VillagerTrades.ItemsForEmeralds(Items.LIGHT_GRAY_BANNER, 3, 1, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Items.GLOBE_BANNER_PATTERN, 8, 1, 30)})));
        map.put(VillagerProfession.CLERIC, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.ROTTEN_FLESH, 32, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.REDSTONE, 1, 2, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.GOLD_INGOT, 3, 12, 10), new VillagerTrades.ItemsForEmeralds(Items.LAPIS_LAZULI, 1, 1, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.RABBIT_FOOT, 2, 12, 20), new VillagerTrades.ItemsForEmeralds(Blocks.GLOWSTONE, 4, 1, 12, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.TURTLE_SCUTE, 4, 12, 30), new VillagerTrades.EmeraldForItems(Items.GLASS_BOTTLE, 9, 12, 30), new VillagerTrades.ItemsForEmeralds(Items.ENDER_PEARL, 5, 1, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.NETHER_WART, 22, 12, 30), new VillagerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 3, 1, 30)})));
        map.put(VillagerProfession.ARMORER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_LEGGINGS), 7, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_BOOTS), 4, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_HELMET), 5, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_CHESTPLATE), 9, 1, 12, 1, 0.2f)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_BOOTS), 1, 1, 12, 5, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_LEGGINGS), 3, 1, 12, 5, 0.2f)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.LAVA_BUCKET, 1, 12, 20), new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 20), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_HELMET), 1, 1, 12, 10, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.CHAINMAIL_CHESTPLATE), 4, 1, 12, 10, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.SHIELD), 5, 1, 12, 10, 0.2f)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_LEGGINGS, 14, 3, 15, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_BOOTS, 8, 3, 15, 0.2f)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_HELMET, 8, 3, 30, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_CHESTPLATE, 16, 3, 30, 0.2f)})));
        map.put(VillagerProfession.WEAPONSMITH, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SWORD, 2, 3, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FLINT, 24, 12, 20)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 30), new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_AXE, 12, 3, 15, 0.2f)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_SWORD, 8, 3, 30, 0.2f)})));
        map.put(VillagerProfession.TOOLSMITH, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_AXE), 1, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_SHOVEL), 1, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_PICKAXE), 1, 1, 12, 1, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.STONE_HOE), 1, 1, 12, 1, 0.2f)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.IRON_INGOT, 4, 12, 10), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2f)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FLINT, 30, 12, 20), new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_AXE, 1, 3, 10, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_SHOVEL, 2, 3, 10, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.IRON_PICKAXE, 3, 3, 10, 0.2f), new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.DIAMOND_HOE), 4, 1, 3, 10, 0.2f)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.DIAMOND, 1, 12, 30), new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_AXE, 12, 3, 15, 0.2f), new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_SHOVEL, 5, 3, 15, 0.2f)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EnchantedItemForEmeralds(Items.DIAMOND_PICKAXE, 13, 3, 30, 0.2f)})));
        map.put(VillagerProfession.BUTCHER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.CHICKEN, 14, 16, 2), new VillagerTrades.EmeraldForItems(Items.PORKCHOP, 7, 16, 2), new VillagerTrades.EmeraldForItems(Items.RABBIT, 4, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.RABBIT_STEW, 1, 1, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.COAL, 15, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.COOKED_PORKCHOP, 1, 5, 16, 5), new VillagerTrades.ItemsForEmeralds(Items.COOKED_CHICKEN, 1, 8, 16, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.MUTTON, 7, 16, 20), new VillagerTrades.EmeraldForItems(Items.BEEF, 10, 16, 20)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.DRIED_KELP_BLOCK, 10, 12, 30)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.SWEET_BERRIES, 10, 12, 30)})));
        map.put(VillagerProfession.LEATHERWORKER, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.LEATHER, 6, 16, 2), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_LEGGINGS, 3), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_CHESTPLATE, 7)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.FLINT, 26, 12, 10), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HELMET, 5, 12, 5), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_BOOTS, 4, 12, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.RABBIT_HIDE, 9, 12, 20), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_CHESTPLATE, 7)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.TURTLE_SCUTE, 4, 12, 30), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HORSE_ARMOR, 6, 12, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(new ItemStack(Items.SADDLE), 6, 1, 12, 30, 0.2f), new VillagerTrades.DyedArmorForEmeralds(Items.LEATHER_HELMET, 5, 12, 30)})));
        map.put(VillagerProfession.MASON, VillagerTrades.toIntMap(ImmutableMap.of(1, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.CLAY_BALL, 10, 16, 2), new VillagerTrades.ItemsForEmeralds(Items.BRICK, 1, 10, 16, 1)}, 2, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Blocks.STONE, 20, 16, 10), new VillagerTrades.ItemsForEmeralds(Blocks.CHISELED_STONE_BRICKS, 1, 4, 16, 5)}, 3, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Blocks.GRANITE, 16, 16, 20), new VillagerTrades.EmeraldForItems(Blocks.ANDESITE, 16, 16, 20), new VillagerTrades.EmeraldForItems(Blocks.DIORITE, 16, 16, 20), new VillagerTrades.ItemsForEmeralds(Blocks.DRIPSTONE_BLOCK, 1, 4, 16, 10), new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_ANDESITE, 1, 4, 16, 10), new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_DIORITE, 1, 4, 16, 10), new VillagerTrades.ItemsForEmeralds(Blocks.POLISHED_GRANITE, 1, 4, 16, 10)}, 4, new VillagerTrades.ItemListing[]{new VillagerTrades.EmeraldForItems(Items.QUARTZ, 12, 12, 30), new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.RED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.PINK_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIME_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.ORANGE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.WHITE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BLACK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.RED_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.PINK_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.MAGENTA_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.LIME_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.GREEN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.CYAN_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.PURPLE_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.YELLOW_GLAZED_TERRACOTTA, 1, 1, 12, 15), new VillagerTrades.ItemsForEmeralds(Blocks.BROWN_GLAZED_TERRACOTTA, 1, 1, 12, 15)}, 5, new VillagerTrades.ItemListing[]{new VillagerTrades.ItemsForEmeralds(Blocks.QUARTZ_PILLAR, 1, 1, 12, 30), new VillagerTrades.ItemsForEmeralds(Blocks.QUARTZ_BLOCK, 1, 1, 12, 30)})));
    });
}