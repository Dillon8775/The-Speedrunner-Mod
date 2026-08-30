package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.item.ModBlockIds;
import net.dillon.speedrunnermod.item.ModBlockItemIds;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.sounds.AmbientLeavesBlockSoundPlayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

/**
 * All Speedrunner Mod {@code blocks.}
 */
public class ModBlocks {

    public static final Block SPEEDRUNNER_LOG = Blocks.register(ModBlockItemIds.SPEEDRUNNER_LOG.block(),
            RotatedPillarBlock::new,
            Block.Properties.of()
                    .strength(1.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    @Deprecated
    public static final Block SPEEDRUNNER_WOOD = Blocks.register(ModBlockItemIds.SPEEDRUNNER_WOOD.block(),
            RotatedPillarBlock::new,
            Block.Properties.of()
                    .strength(1.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_LEAVES = Blocks.register(ModBlockItemIds.SPEEDRUNNER_LEAVES.block(),
            settings ->
                    new UntintedParticleLeavesBlock(
                            0.00F,
                            ColorParticleOption.create(ParticleTypes.TINTED_LEAVES,
                                    ARGB.opaque(0xAED6F1)),
                            AmbientLeavesBlockSoundPlayer.noAmbientSound(),
                            settings),
            Block.Properties.of()
                    .strength(0.1F)
                    .noOcclusion()
                    .randomTicks()
                    .isValidSpawn(Blocks::ocelotOrParrot)
                    .isSuffocating(Blocks::never)
                    .isViewBlocking((state, level, pos, aabb) -> false)
                    .pushReaction(PushReaction.POPPED)
                    .isRedstoneConductor(Blocks::never)
                    .sound(SoundType.GRASS)
    );

    public static final Block SPEEDRUNNER_SAPLING = Blocks.register(ModBlockItemIds.SPEEDRUNNER_SAPLING.block(),
            settings ->
                    new SpeedrunnerSaplingBlock(ModSaplingGenerators.SPEEDRUNNER, settings),
            Block.Properties.of()
                    .instabreak()
                    .randomTicks()
                    .noCollision()
                    .sound(SoundType.GRASS)
    );

    public static final Block DEAD_SPEEDRUNNER_BUSH = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH.block(),
            DryVegetationBlock::new,
            Block.Properties.of()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.GRASS)
    );

    @Deprecated
    public static final Block POTTED_SPEEDRUNNER_SAPLING = Blocks.register(ModBlockIds.POTTED_SPEEDRUNNER_SAPLING,
            settings ->
                    new FlowerPotBlock(ModBlocks.SPEEDRUNNER_SAPLING, settings),
            Block.Properties.of()
                    .instabreak()
                    .noOcclusion()
                    .pushReaction(PushReaction.POPPED)
    );

    @Deprecated
    public static final Block POTTED_DEAD_SPEEDRUNNER_BUSH = Blocks.register(ModBlockIds.DEAD_POTTED_SPEEDRUNNER_BUSH,
            settings ->
                    new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, settings),
            Block.Properties.of()
                    .instabreak()
                    .noCollision()
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.GRASS)
    );

    public static final Block SPEEDRUNNER_PLANKS = Blocks.register(ModBlockItemIds.SPEEDRUNNER_PLANKS.block(),
            Block::new,
            Block.Properties.of()
                    .strength(1.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_SLAB = Blocks.register(ModBlockItemIds.SPEEDRUNNER_SLAB.block(),
            SlabBlock::new,
            Block.Properties.of()
                    .strength(1.0F, 6.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_STAIRS = Blocks.register(ModBlockItemIds.SPEEDRUNNER_STAIRS.block(),
            settings ->
                    new StairBlock(ModBlocks.SPEEDRUNNER_PLANKS.defaultBlockState(), settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_PLANKS)
    );

    public static final Block SPEEDRUNNER_FENCE = Blocks.register(ModBlockItemIds.SPEEDRUNNER_FENCE.block(),
            FenceBlock::new,
            Block.Properties.of()
                    .strength(1.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_FENCE_GATE = Blocks.register(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.block(),
            settings ->
                    new FenceGateBlock(WoodType.OAK, settings),
            Block.Properties.of()
                    .strength(1.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    @Deprecated
    public static final Block SPEEDRUNNER_BUTTON = Blocks.register(ModBlockItemIds.SPEEDRUNNER_BUTTON.block(),
            settings ->
                    new ButtonBlock(BlockSetType.OAK, 30, settings),
            Block.Properties.of()
                    .strength(0.35F)
                    .noCollision()
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.WOOD)
    );

    @Deprecated
    public static final Block SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.block(),
            settings ->
                    new PressurePlateBlock(BlockSetType.OAK, settings),
            Block.Properties.of()
                    .strength(0.35F)
                    .noCollision()
                    .instrument(NoteBlockInstrument.BASS)
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_TRAPDOOR = Blocks.register(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.block(),
            settings ->
                    new TrapDoorBlock(BlockSetType.OAK, settings),
            Block.Properties.of()
                    .strength(1.3F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_DOOR = Blocks.register(ModBlockItemIds.SPEEDRUNNER_DOOR.block(),
            settings ->
                    new DoorBlock(BlockSetType.OAK, settings),
            Block.Properties.of()
                    .strength(1.3F)
                    .noOcclusion()
                    .instrument(NoteBlockInstrument.BASS)
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.WOOD)
    );

    public static final Block DEAD_SPEEDRUNNER_LOG = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG.block(),
            RotatedPillarBlock::new,
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_LOG)
                    .ignitedByLava()
    );

    @Deprecated
    public static final Block DEAD_SPEEDRUNNER_WOOD = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD.block(),
            RotatedPillarBlock::new,
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_WOOD)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_SAPLING = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_SAPLING.block(),
            settings ->
                    new SpeedrunnerSaplingBlock(ModSaplingGenerators.DEAD_SPEEDRUNNER, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_SAPLING)
                    .ignitedByLava()
    );

    @Deprecated
    public static final Block DEAD_POTTED_SPEEDRUNNER_SAPLING = Blocks.register(ModBlockIds.DEAD_POTTED_SPEEDRUNNER_SAPLING,
            settings -> new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, settings),
            Block.Properties.ofFullCopy(ModBlocks.DEAD_SPEEDRUNNER_SAPLING)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_LEAVES = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES.block(),
            settings ->
                    new UntintedParticleLeavesBlock(0.00F, ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, ARGB.opaque(0x8F9EA8)),
                            AmbientLeavesBlockSoundPlayer.noAmbientSound(), settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_LEAVES)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_PLANKS = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.block(),
            Block::new,
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_PLANKS)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_SLAB = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB.block(),
            SlabBlock::new,
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_SLAB)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_STAIRS = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS.block(),
            settings ->
                    new StairBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.defaultBlockState(), settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_STAIRS)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_FENCE = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE.block(),
            FenceBlock::new,
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_FENCE)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_FENCE_GATE = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE.block(),
            settings ->
                    new FenceGateBlock(WoodType.OAK, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_FENCE_GATE)
                    .ignitedByLava()
    );

    @Deprecated
    public static final Block DEAD_SPEEDRUNNER_BUTTON = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON.block(),
            settings -> new ButtonBlock(BlockSetType.OAK, 30, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_BUTTON)
                    .ignitedByLava()
    );

    @Deprecated
    public static final Block DEAD_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE.block(),
            settings ->
                    new PressurePlateBlock(BlockSetType.OAK, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_PRESSURE_PLATE)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_TRAPDOOR = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR.block(),
            settings ->
                    new TrapDoorBlock(BlockSetType.OAK, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_TRAPDOOR)
                    .ignitedByLava()
    );

    public static final Block DEAD_SPEEDRUNNER_DOOR = Blocks.register(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR.block(),
            settings ->
                    new DoorBlock(BlockSetType.OAK, settings),
            Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_DOOR)
                    .ignitedByLava()
    );

    @Deprecated(forRemoval = true)
    public static final Block METAL_SPEEDRUNNER_TRAPDOOR = Blocks.register(ModBlockItemIds.METAL_SPEEDRUNNER_TRAPDOOR.block(),
            settings ->
                    new TrapDoorBlock(BlockSetType.IRON, settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(2.5F)
                    .noOcclusion()
                    .isValidSpawn(Blocks::never)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.METAL)
    );

    @Deprecated(forRemoval = true)
    public static final Block MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(ModBlockItemIds.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE.block(),
            settings ->
                    new WeightedPressurePlateBlock(100, BlockSetType.IRON, settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(0.5F)
                    .noCollision()
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.WOOD)
    );

    @Deprecated(forRemoval = true)
    public static final Block METAL_SPEEDRUNNER_DOOR = Blocks.register(ModBlockItemIds.METAL_SPEEDRUNNER_DOOR.block(),
            settings ->
                    new DoorBlock(BlockSetType.IRON, settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(2.5F)
                    .noOcclusion()
                    .instrument(NoteBlockInstrument.BASS)
                    .pushReaction(PushReaction.POPPED)
                    .sound(SoundType.METAL)
    );

    public static final Block SPEEDRUNNERS_WORKBENCH = Blocks.register(ModBlockItemIds.SPEEDRUNNERS_WORKBENCH.block(),
            SpeedrunnersWorkbenchBlock::new,
            Block.Properties.of()
                    .strength(1.6F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block SPEEDRUNNER_BLOCK = Blocks.register(ModBlockItemIds.SPEEDRUNNER_BLOCK.block(),
            Block::new,
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                    .sound(SoundType.METAL)
    );

    public static final Block RAW_SPEEDRUNNER_BLOCK = Blocks.register(ModBlockItemIds.RAW_SPEEDRUNNER_BLOCK.block(),
            Block::new,
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 6.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STONE)
    );

    public static final Block THRUSTED_BLOCK = Blocks.register(ModBlockItemIds.THRUSTED_BLOCK.block(),
            Block::new,
            Block.Properties.of()
                    .strength(1.5F)
                    .isValidSpawn(Blocks::never)
                    .sound(SoundType.GRASS)
    );

    public static final Block SPEEDRUNNER_ORE = Blocks.register(ModBlockItemIds.SPEEDRUNNER_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STONE)
    );

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = Blocks.register(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 4.5F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE)
    );

    public static final Block NETHER_SPEEDRUNNER_ORE = Blocks.register(ModBlockItemIds.NETHER_SPEEDRUNNER_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.NETHER_GOLD_ORE)
    );

    public static final Block IGNEOUS_ORE = Blocks.register(ModBlockItemIds.IGNEOUS_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STONE)
    );

    public static final Block DEEPSLATE_IGNEOUS_ORE = Blocks.register(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 4.5F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.DEEPSLATE)
    );

    public static final Block NETHER_IGNEOUS_ORE = Blocks.register(ModBlockItemIds.NETHER_IGNEOUS_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.NETHER_ORE)
    );

    public static final Block EXPERIENCE_ORE = Blocks.register(ModBlockItemIds.EXPERIENCE_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(5.0F, 10.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STONE)
    );

    public static final Block DEEPSLATE_EXPERIENCE_ORE = Blocks.register(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(6.0F, 12.5F)
                    .sound(SoundType.DEEPSLATE)
    );

    public static final Block NETHER_EXPERIENCE_ORE = Blocks.register(ModBlockItemIds.NETHER_EXPERIENCE_ORE.block(),
            settings ->
                    new DropExperienceBlock(UniformInt.of(0, 0), settings),
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.NETHER_ORE)
    );

    public static final Block FLESH_BLOCK = Blocks.register(ModBlockItemIds.FLESH_BLOCK.block(),
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(2.0F, 2.0F)
                    .friction(1.01F)
                    .sound(SoundType.HONEY_BLOCK)
    );

    public static final Block DOOM_STONE = Blocks.register(ModBlockItemIds.DOOM_STONE.block(),
            DoomBlock.Default::new,
            Block.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(1.5F, 3600000.0F)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .sound(SoundType.STONE)
    );

    public static final Block DOOM_LOG = Blocks.register(ModBlockItemIds.DOOM_LOG.block(),
            DoomBlock.Pillar::new,
            Block.Properties.of()
                    .strength(1.0F, 3600000.0F)
                    .instrument(NoteBlockInstrument.BASS)
                    .sound(SoundType.WOOD)
    );

    public static final Block DOOM_LEAVES = Blocks.register(ModBlockItemIds.DOOM_LEAVES.block(),
            DoomBlock.Leaves::new,
            Block.Properties.of()
                    .strength(0.1F, 3600000.0F)
                    .noOcclusion()
                    .randomTicks()
                    .isSuffocating(Blocks::never)
                    .isViewBlocking((state, level, pos, aabb) -> false)
                    .pushReaction(PushReaction.POPPED)
                    .isRedstoneConductor(Blocks::never)
                    .sound(SoundType.GRASS)
    );

    /**
     * Initializes all Speedrunner Mod {@code blocks.}
     */
    public static void initializeBlocks() {
        SpeedrunnerMod.debug("Initialized blocks.");
    }
}