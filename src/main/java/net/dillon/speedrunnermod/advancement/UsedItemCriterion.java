package net.dillon.speedrunnermod.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

/**
 * The used item criterion.
 */
public class UsedItemCriterion extends AbstractCriterion<UsedItemCriterion.Conditions> {
    @Override
    public Codec<UsedItemCriterion.Conditions> getConditionsCodec() {
        return UsedItemCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public record Conditions(Optional<LootContextPredicate> player, Optional<ItemPredicate> item) implements AbstractCriterion.Conditions {
        public static final Codec<UsedItemCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(UsedItemCriterion.Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(UsedItemCriterion.Conditions::item)
                        )
                        .apply(instance, UsedItemCriterion.Conditions::new)
        );

        public static AdvancementCriterion<UsedItemCriterion.Conditions> item(RegistryEntryLookup<Item> itemRegistry, ItemConvertible item) {
            return ModCriterions.USED_ITEM
                    .create(new UsedItemCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.create().items(itemRegistry, item).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().test(stack);
        }
    }
}