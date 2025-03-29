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
public class TriggeredByItemCriterion extends AbstractCriterion<TriggeredByItemCriterion.Conditions> {
    @Override
    public Codec<TriggeredByItemCriterion.Conditions> getConditionsCodec() {
        return TriggeredByItemCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public record Conditions(Optional<LootContextPredicate> player, Optional<ItemPredicate> item) implements AbstractCriterion.Conditions {
        public static final Codec<TriggeredByItemCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(TriggeredByItemCriterion.Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggeredByItemCriterion.Conditions::item)
                        )
                        .apply(instance, TriggeredByItemCriterion.Conditions::new)
        );

        public static AdvancementCriterion<TriggeredByItemCriterion.Conditions> item(RegistryEntryLookup<Item> itemRegistry, ItemConvertible item) {
            return ModCriterions.TRIGGERED_BY_ITEM
                    .create(new TriggeredByItemCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.create().items(itemRegistry, item).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().test(stack);
        }
    }
}