package net.dillon.speedrunnermod.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;

/**
 * The used item criterion.
 */
public class TriggeredByItemCriterion extends SimpleCriterionTrigger<TriggeredByItemCriterion.Conditions> {
    @Override
    public Codec<TriggeredByItemCriterion.Conditions> codec() {
        return TriggeredByItemCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack) {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public record Conditions(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleCriterionTrigger.SimpleInstance {
        public static final Codec<TriggeredByItemCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(TriggeredByItemCriterion.Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(TriggeredByItemCriterion.Conditions::item)
                        )
                        .apply(instance, TriggeredByItemCriterion.Conditions::new)
        );

        public static Criterion<TriggeredByItemCriterion.Conditions> item(HolderGetter<Item> itemRegistry, ItemLike item) {
            return ModCriterions.TRIGGERED_BY_ITEM
                    .createCriterion(new TriggeredByItemCriterion.Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(itemRegistry, item).build())));
        }

        public boolean matches(ItemStack stack) {
            return this.item.isEmpty() || this.item.get().test(stack);
        }
    }
}