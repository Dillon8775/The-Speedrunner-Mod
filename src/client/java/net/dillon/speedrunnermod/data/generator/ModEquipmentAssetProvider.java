package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.item.material.ModEquipmentAssetKeys;
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
        consumer.accept(ModEquipmentAssetKeys.SPEEDRUNNER, createHumanoidAndNautilusArmor("speedrunner"));
        consumer.accept(ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, createHumanoidAndNautilusArmor("golden_speedrunner"));
        consumer.accept(ModEquipmentAssetKeys.SPEEDRUNNER_HARDNESS, createHarnessModel("speedrunner_harness"));
        consumer.accept(ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER_HARDNESS, createHarnessModel("golden_speedrunner_harness"));
    }

    /**
     * Creates humanoid only armor models.
     */
    @Deprecated
    private static EquipmentClientInfo createHumanoidOnlyModel(String id) {
        return EquipmentClientInfo.builder().addHumanoidLayers(ofSpeedrunnerMod(id)).build();
    }

    /**
     * Creates humanoid and nautilus armor models.
     */
    private static EquipmentClientInfo createHumanoidAndNautilusArmor(String id) {
        return EquipmentClientInfo.builder()
                .addHumanoidLayers(ofSpeedrunnerMod(id))
                .addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY, EquipmentClientInfo.Layer.leatherDyeable(ofSpeedrunnerMod(id), false))
                .build();
    }

    /**
     * Creates harness models.
     */
    private static EquipmentClientInfo createHarnessModel(String id) {
        return EquipmentClientInfo.builder()
                .addLayers(
                        EquipmentClientInfo.LayerType.HAPPY_GHAST_BODY,
                        EquipmentClientInfo.Layer.onlyIfDyed(ofSpeedrunnerMod(id), false))
                .build();
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