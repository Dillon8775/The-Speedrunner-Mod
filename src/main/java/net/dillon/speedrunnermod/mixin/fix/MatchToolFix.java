package net.dillon.speedrunnermod.mixin.fix;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.tool.SpeedrunnerShearsItem;
import net.dillon.speedrunnermod.mixin.accessor.RegistryEntryListDirectAccessor;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the {@link SpeedrunnerShearsItem} to work correctly.
 */
@Author(Authors.CECH12)
@Mixin(MatchTool.class)
public class MatchToolFix {
    @Unique
    private static final List<ItemPredicate> ITEM_PREDICATES = new ArrayList<>();

    /**
     * Collects all {@link MatchTool} predicates.
     */
    @Inject(at = @At("RETURN"), method = "<init>")
    private void initProxy(CallbackInfo ci) {
        ((MatchTool)(Object)this).predicate().ifPresent(ITEM_PREDICATES::add);
    }

    // Allows both types of teaches to work.
    static {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            Holder<Item> speedrunnerShearsHolder = BuiltInRegistries.ITEM.wrapAsHolder(ModItems.SPEEDRUNNER_SHEARS);
            Holder<Item> shearsHolder = BuiltInRegistries.ITEM.wrapAsHolder(Items.SHEARS);

            for(ItemPredicate itemPredicate : ITEM_PREDICATES) {
                itemPredicate.items().ifPresent((holders) -> {
                    if (holders instanceof HolderSet.Direct && holders.contains(shearsHolder) && !holders.contains(speedrunnerShearsHolder)) {
                        RegistryEntryListDirectAccessor<Item> accessor = (RegistryEntryListDirectAccessor)holders;
                        ArrayList<Holder<Item>> newList = new ArrayList<>(accessor.getContents());
                        newList.add(speedrunnerShearsHolder);
                        accessor.setContents(ImmutableList.copyOf(newList));
                        accessor.setContentsSet(null);
                    }
                });
            }
        });
    }
}