package net.dillon.speedrunnermod.village;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.entity.ModPotions;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

/**
 * All {@code Retired Speedrunner's} trades and offers.
 */
public class ModTradeOffers {

    /**
     * Registers the {@code Retired Speedrunner's} trade offers.
     */
    public static void registerTradeOffers() {
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 1, factories -> {
            factories.add(new VillagerTrades.ItemsForEmeralds(Items.BOOK, 1, 3, 12, 3));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 1, factories -> {
            factories.add(new EnchantedBookFactory(3, 4, 0.2F, 12, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 2, factories -> {
            factories.add(new EnchantedBookFactory(3, 10, 0.0F, 12, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 2, factories -> {
            factories.add(new SellItemFactorySpeedrunnerIngot(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE, 2, 1, 12, 8));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 3, factories -> {
            factories.add(new VillagerTrades.TippedArrowForItemsAndEmeralds(Items.WATER_BUCKET, 1, Items.SPLASH_POTION, 1, 1, 12, 12));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 3, factories -> {
            factories.add(new EnchantedBookFactory(3, 14, 0.0F, 16, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 3, factories -> {
            factories.add(new EnchantedBookFactory(7, 13, 0.2F, 8, 1, 4, ModEnchantmentTags.WITHERED_ENCHANTMENTS));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 4, factories -> {
            factories.add(new VillagerTrades.ItemsForEmeralds(Items.GOLDEN_APPLE, 4, 3, 16, 18));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 4, factories -> {
            factories.add(new VillagerTrades.ItemsForEmeralds(ModItems.INFINI_PEARL, 24, 1, 1, 24));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 5, factories -> {
            factories.add(new VillagerTrades.ItemsForEmeralds(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 3, 1, 9, 28));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 5, factories -> {
            ItemStack dragonsAura = new ItemStack(Items.POTION);
            dragonsAura.set(DataComponents.POTION_CONTENTS, new PotionContents(ModPotions.DRAGONS_AURA));
            factories.add(new VillagerTrades.ItemsForEmeralds(dragonsAura, 16, 1, 1, 35));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 5, factories -> {
            factories.add(new EnchantedBookFactory(3, 35, 0.0F, 24, ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES));
        });
        TradeOfferHelper.registerVillagerOffers(ModVillagers.RETIRED_SPEEDRUNNER_KEY, 5, factories -> {
            factories.add(new SellMaxedEnchantedNetheriteChestplateFactory(12, 3, 40, 1.35F));
        });

        SpeedrunnerMod.debug("Registered trade offers.");
    }

    public static class SellItemFactorySpeedrunnerIngot implements VillagerTrades.ItemListing {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactorySpeedrunnerIngot(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactorySpeedrunnerIngot(Item item, int price, int count, int maxUses, int experience) {
            this(new ItemStack(item), price, count, maxUses, experience);
        }

        public SellItemFactorySpeedrunnerIngot(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.0F);
        }

        public SellItemFactorySpeedrunnerIngot(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(ServerLevel serverWorld, Entity entity, RandomSource random) {
            return new MerchantOffer(new ItemCost(ModItems.SPEEDRUNNER_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellItemFactoryIronIngot implements VillagerTrades.ItemListing {
        private final ItemStack sell;
        private final int price;
        private final int count;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellItemFactoryIronIngot(Item item, int price, int count, int experience) {
            this(new ItemStack(item), price, count, 12, experience);
        }

        public SellItemFactoryIronIngot(ItemStack stack, int price, int count, int maxUses, int experience) {
            this(stack, price, count, maxUses, experience, 0.0F);
        }

        public SellItemFactoryIronIngot(ItemStack stack, int price, int count, int maxUses, int experience, float multiplier) {
            this.sell = stack;
            this.price = price;
            this.count = count;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(ServerLevel serverWorld, Entity entity, RandomSource random) {
            return new MerchantOffer(new ItemCost(Items.IRON_INGOT, this.price), new ItemStack(this.sell.getItem(), this.count), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class EnchantedBookFactory implements VillagerTrades.ItemListing {
        private final int basePrice;
        private final int experience;
        private final float priceMultiplier;
        private final int maxUses;
        private final TagKey<Enchantment> possibleEnchantments;
        private int minLevel = -1;
        private int maxLevel = -1;

        public EnchantedBookFactory(int basePrice, int experience, float priceMultiplier, int maxUses, TagKey<Enchantment> possibleEnchantments) {
            this.basePrice = basePrice;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
            this.maxUses = maxUses;
            this.possibleEnchantments = possibleEnchantments;
        }

        public EnchantedBookFactory(int basePrice, int experience, float priceMultiplier, int maxUses, int minLevel, int maxLevel, TagKey<Enchantment> possibleEnchantments) {
            this.basePrice = basePrice;
            this.experience = experience;
            this.priceMultiplier = priceMultiplier;
            this.maxUses = maxUses;
            this.possibleEnchantments = possibleEnchantments;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }

        @Override
        public MerchantOffer getOffer(ServerLevel serverWorld, Entity entity, RandomSource random) {
            int l;
            ItemStack itemStack;
            Optional<Holder<Enchantment>> optional = entity.level().registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getRandomElementOf(this.possibleEnchantments, random);
            if (!optional.isEmpty()) {
                Holder<Enchantment> registryEntry = optional.get();
                Enchantment enchantment = registryEntry.value();
                boolean bl = this.minLevel == -1 && this.maxLevel == -1;
                int k = enchantment.getMaxLevel();
                if (!bl) {
                    int i = Math.max(enchantment.getMinLevel(), this.minLevel);
                    int j = Math.min(enchantment.getMaxLevel(), this.maxLevel);
                    k = Mth.nextInt(random, i, j);
                }
                itemStack = EnchantmentHelper.createBook(new EnchantmentInstance(registryEntry, k));
                l = this.basePrice;

                if (l > 10) {
                    l = 10;
                }
            } else {
                l = 1;
                itemStack = new ItemStack(Items.BOOK);
            }
            return new MerchantOffer(new ItemCost(Items.EMERALD, l), Optional.of(new ItemCost(Items.BOOK)), itemStack, this.maxUses, this.experience, this.priceMultiplier);
        }
    }

    public static class SellMaxedEnchantedToolFactory implements VillagerTrades.ItemListing {
        private final ItemStack tool;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;
        private final TagKey<Enchantment> possibleEnchantments;

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience, TagKey<Enchantment> possibleEnchantments) {
            this(item, price, maxUses, experience, 0.05F, possibleEnchantments);
        }

        public SellMaxedEnchantedToolFactory(Item item, int price, int maxUses, int experience, float multiplier, TagKey<Enchantment> possibleEnchantments) {
            this.tool = new ItemStack(item);
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
            this.possibleEnchantments = possibleEnchantments;
        }

        @Override
        public MerchantOffer getOffer(ServerLevel serverWorld, Entity entity, RandomSource random) {
            int i = random.nextInt(4) + 30;
            RegistryAccess dynamicRegistryManager = entity.level().registryAccess();
            Optional<HolderSet.Named<Enchantment>> optional = dynamicRegistryManager.lookupOrThrow(Registries.ENCHANTMENT).get(this.possibleEnchantments);
            ItemStack itemStack = EnchantmentHelper.enchantItem(random, new ItemStack(this.tool.getItem()), i, dynamicRegistryManager, optional);
            return new MerchantOffer(new ItemCost(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellMaxedEnchantedNetheriteChestplateFactory implements VillagerTrades.ItemListing {
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public SellMaxedEnchantedNetheriteChestplateFactory(int price, int maxUses, int experience, float multiplier) {
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = multiplier;
        }

        @Override
        public MerchantOffer getOffer(ServerLevel serverWorld, Entity entity, RandomSource random) {
            int i = random.nextInt(4) + 1;
            ItemStack itemStack = new ItemStack(i == 1 ? Items.NETHERITE_HELMET : i == 2 ? Items.NETHERITE_CHESTPLATE : i == 3 ? Items.NETHERITE_LEGGINGS : Items.NETHERITE_BOOTS);
            itemStack.enchant(ModUtil.enchantment(entity, Enchantments.PROTECTION), random.nextInt(3) + 3);
            itemStack.enchant(ModUtil.enchantment(entity, Enchantments.UNBREAKING), random.nextInt(3) + 3);
            if (random.nextBoolean()) {
                itemStack.enchant(ModUtil.enchantment(entity, Enchantments.MENDING), 1);
            }
            if (random.nextDouble() < 0.35) {
                itemStack.enchant(ModUtil.enchantment(entity, Enchantments.THORNS), random.nextInt(3) + 2);
            }
            if (i == 1 && random.nextDouble() < 0.40) {
                itemStack.enchant(ModUtil.enchantment(entity, Enchantments.RESPIRATION), random.nextInt(2) + 2);
            }
            if (i == 3 && random.nextDouble() < 0.25) {
                itemStack.enchant(ModUtil.enchantment(entity, Enchantments.SWIFT_SNEAK), random.nextInt(3) + 2);
            }
            if (i == 4) {
                if (random.nextDouble() < 0.40) {
                    itemStack.enchant(ModUtil.enchantment(entity, ModEnchantments.DASH), random.nextInt(3) + 2);
                }
                if (random.nextDouble() < 0.35) {
                    itemStack.enchant(ModUtil.enchantment(entity, Enchantments.FEATHER_FALLING), random.nextInt(2) + 3);
                }
            }
            return new MerchantOffer(new ItemCost(Items.EMERALD, this.price), itemStack, this.maxUses, this.experience, this.multiplier);
        }
    }
}