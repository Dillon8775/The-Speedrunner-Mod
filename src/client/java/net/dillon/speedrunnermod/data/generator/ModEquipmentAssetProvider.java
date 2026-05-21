package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.equipment.ModEquipmentAssetKeys;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.data.models.EquipmentAssetProvider;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

@Author(Authors.BLOCKLEGEND001)
public class ModEquipmentAssetProvider extends EquipmentAssetProvider {
    protected final PackOutput.PathProvider pathResolver;

    public ModEquipmentAssetProvider(PackOutput output) {
        super(output);
        this.pathResolver = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    public static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> consumer) {
        consumer.accept(ModEquipmentAssetKeys.SPEEDRUNNER, createHumanoidOnlyModel("speedrunner"));
        consumer.accept(ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, createHumanoidOnlyModel("golden_speedrunner"));
    }

    private static EquipmentClientInfo createHumanoidOnlyModel(String id) {
        return EquipmentClientInfo.builder().addHumanoidLayers(ofSpeedrunnerMod(id)).build();
    }

    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> map = new HashMap<>();
        bootstrap((key, model) -> {
            if (map.putIfAbsent(key, model) != null) {
                throw new IllegalStateException("Tried to register equipment asset twice for id: " + key);
            }
        });
        return DataProvider.saveAll(writer, EquipmentClientInfo.CODEC, this.pathResolver::json, map);
    }
}