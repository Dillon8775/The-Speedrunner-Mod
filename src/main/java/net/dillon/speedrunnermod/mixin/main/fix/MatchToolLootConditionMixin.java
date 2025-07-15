package net.dillon.speedrunnermod.mixin.main.fix;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShearsItem;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This mixin class allows the {@link SpeedrunnerShearsItem} to work correctly.
 */
@Author(Authors.CECH12)
@Mixin(MatchToolLootCondition.class)
public class MatchToolLootConditionMixin {
    @Unique
    private static final List<ItemPredicate> ITEM_PREDICATES = new ArrayList<>();

    @Inject(at = @At("RETURN"), method = "<init>")
    private void initProxy(CallbackInfo ci) {
        Optional var10000 = ((MatchToolLootCondition)(Object)this).predicate();
        List var10001 = ITEM_PREDICATES;
        Objects.requireNonNull(var10001);
        var10000.ifPresent(var10001::add);
    }

    static {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            RegistryEntry<Item> ceramicShearsHolder = Registries.ITEM.getEntry(ModItems.SPEEDRUNNER_SHEARS);
            RegistryEntry<Item> shearsHolder = Registries.ITEM.getEntry(Items.SHEARS);

            for(ItemPredicate itemPredicate : ITEM_PREDICATES) {
                itemPredicate.items().ifPresent((holders) -> {
                    if (holders instanceof RegistryEntryList.Direct && holders.contains(shearsHolder) && !holders.contains(ceramicShearsHolder)) {
                        RegistryEntryListDirectAccessor<Item> accessor = (RegistryEntryListDirectAccessor)holders;
                        ArrayList<RegistryEntry<Item>> newList = new ArrayList<>(accessor.getEntries());
                        newList.add(ceramicShearsHolder);
                        accessor.setEntries(ImmutableList.copyOf(newList));
                        accessor.setEntrySet(null);
                    }
                });
            }
        });
    }
}