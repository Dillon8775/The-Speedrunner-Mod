package net.dillon.speedrunnermod.mixin.fix;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShearsItem;
import net.dillon.speedrunnermod.mixin.accessor.RegistryEntryListDirectAccessor;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents;
import net.minecraft.advancements.criterion.ItemPredicate;
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
import java.util.Objects;
import java.util.Optional;

/**
 * This mixin class allows the {@link SpeedrunnerShearsItem} to work correctly.
 */
@Author(Authors.CECH12)
@Deprecated
@Mixin(MatchTool.class)
public class MatchToolMixin {
    @Unique
    private static final List<ItemPredicate> ITEM_PREDICATES = new ArrayList<>();

    @Inject(at = @At("RETURN"), method = "<init>")
    private void initProxy(CallbackInfo ci) {
        Optional var10000 = ((MatchTool)(Object)this).predicate();
        List var10001 = ITEM_PREDICATES;
        Objects.requireNonNull(var10001);
        var10000.ifPresent(var10001::add);
    }

    static {
        CommonLifecycleEvents.TAGS_LOADED.register((registries, client) -> {
            Holder<Item> ceramicShearsHolder = BuiltInRegistries.ITEM.wrapAsHolder(ModItems.SPEEDRUNNER_SHEARS);
            Holder<Item> shearsHolder = BuiltInRegistries.ITEM.wrapAsHolder(Items.SHEARS);

            for(ItemPredicate itemPredicate : ITEM_PREDICATES) {
                itemPredicate.items().ifPresent((holders) -> {
                    if (holders instanceof HolderSet.Direct && holders.contains(shearsHolder) && !holders.contains(ceramicShearsHolder)) {
                        RegistryEntryListDirectAccessor<Item> accessor = (RegistryEntryListDirectAccessor)holders;
                        ArrayList<Holder<Item>> newList = new ArrayList<>(accessor.getContents());
                        newList.add(ceramicShearsHolder);
                        accessor.setContents(ImmutableList.copyOf(newList));
                        accessor.setContentsSet(null);
                    }
                });
            }
        });
    }
}