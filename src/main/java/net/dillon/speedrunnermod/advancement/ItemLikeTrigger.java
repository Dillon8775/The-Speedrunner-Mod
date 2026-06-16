package net.dillon.speedrunnermod.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.entity.EntityPredicate;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.SimpleCriterionTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;

/**
 * The used item criterion.
 */
public class ItemLikeTrigger extends SimpleCriterionTrigger<ItemLikeTrigger.Conditions> {
    @Override
    public Codec<ItemLikeTrigger.Conditions> codec() {
        return ItemLikeTrigger.Conditions.CODEC;
    }

    /**
     * Triggers the item-like function.
     */
    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    /**
     * The conditions for the item like trigger.
     */
    public record Conditions(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<ItemLikeTrigger.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(ItemLikeTrigger.Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(ItemLikeTrigger.Conditions::item)
                        )
                        .apply(instance, ItemLikeTrigger.Conditions::new)
        );

        public static Criterion<Conditions> item(HolderGetter<Item> itemRegistry, ItemLike item) {
            return ModPredicates.TRIGGERED_BY_ITEMLIKE
                    .createCriterion(new ItemLikeTrigger.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(itemRegistry, item).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().test(stack);
        }
    }
}