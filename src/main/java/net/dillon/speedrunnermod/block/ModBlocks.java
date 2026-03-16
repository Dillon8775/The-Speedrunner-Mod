package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.block.sign.CustomSignBlock;
import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.dillon.speedrunnermod.block.sign.hanging.CustomHangingSignBlock;
import net.dillon.speedrunnermod.block.sign.hanging.wall.CustomWallHangingSignBlock;
import net.dillon.speedrunnermod.block.sign.wall.CustomWallSignBlock;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.PushReaction;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code blocks.}
 */
public class ModBlocks {

    public static final Block SPEEDRUNNER_LOG = Blocks.register(of("speedrunner_log"), RotatedPillarBlock::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_LOG = Blocks.register(of("stripped_speedrunner_log"), RotatedPillarBlock::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_WOOD = Blocks.register(of("speedrunner_wood"), RotatedPillarBlock::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_WOOD = Blocks.register(of("stripped_speedrunner_wood"), RotatedPillarBlock::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_LEAVES = Blocks.register(of("speedrunner_leaves"), settings -> new TintedParticleLeavesBlock(0.01F, settings), Block.Properties.of()
            .strength(0.1F)
            .noOcclusion()
            .randomTicks()
            .isValidSpawn(Blocks::ocelotOrParrot)
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)
            .sound(SoundType.GRASS));

    public static final Block SPEEDRUNNER_SAPLING = Blocks.register(of("speedrunner_sapling"), settings -> new SpeedrunnerSaplingBlock(ModSaplingGenerators.SPEEDRUNNER, settings), Block.Properties.of()
            .instabreak()
            .randomTicks()
            .noCollision()
            .sound(SoundType.GRASS));

    public static final Block POTTED_SPEEDRUNNER_SAPLING = Blocks.register(of("potted_speedrunner_sapling"), settings -> new FlowerPotBlock(ModBlocks.SPEEDRUNNER_SAPLING, settings), Block.Properties.of()
            .instabreak()
            .noOcclusion()
            .pushReaction(PushReaction.DESTROY));

    public static final Block SPEEDRUNNER_PLANKS = Blocks.register(of("speedrunner_planks"), Block::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_SLAB = Blocks.register(of("speedrunner_slab"), SlabBlock::new, Block.Properties.of()
            .strength(1.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_STAIRS = Blocks.register(of("speedrunner_stairs"), settings -> new StairBlock(ModBlocks.SPEEDRUNNER_PLANKS.defaultBlockState(), settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_PLANKS));

    public static final Block SPEEDRUNNER_FENCE = Blocks.register(of("speedrunner_fence"), FenceBlock::new, Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_FENCE_GATE = Blocks.register(of("speedrunner_fence_gate"), settings -> new FenceGateBlock(WoodType.OAK, settings), Block.Properties.of()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_TRAPDOOR = Blocks.register(of("wooden_speedrunner_trapdoor"), settings -> new TrapDoorBlock(BlockSetType.OAK, settings), Block.Properties.of()
            .strength(1.3F)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_BUTTON = Blocks.register(of("wooden_speedrunner_button"), settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), Block.Properties.of()
            .strength(0.35F)
            .noCollision()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(of("wooden_speedrunner_pressure_plate"), settings -> new PressurePlateBlock(BlockSetType.OAK, settings), Block.Properties.of()
            .strength(0.35F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_DOOR = Blocks.register(of("wooden_speedrunner_door"), settings -> new DoorBlock(BlockSetType.OAK, settings), Block.Properties.of()
            .strength(1.3F)
            .noOcclusion()
            .instrument(NoteBlockInstrument.BASS)
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_sign", settings -> new CustomSignBlock("speedrunner", settings), Block.Properties.of()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_wall_sign", settings -> new CustomWallSignBlock("speedrunner", settings), Block.Properties.of()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_hanging_sign", settings -> new CustomHangingSignBlock("speedrunner", settings), Block.Properties.of()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.HANGING_SIGN));

    public static final Block SPEEDRUNNER_HANGING_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_hanging_wall_sign", settings -> new CustomWallHangingSignBlock("speedrunner", settings), Block.Properties.of()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.HANGING_SIGN));

    public static final Block DEAD_SPEEDRUNNER_LOG = Blocks.register(of("dead_speedrunner_log"), RotatedPillarBlock::new, Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_LOG).ignitedByLava());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_LOG = Blocks.register(of("dead_stripped_speedrunner_log"), RotatedPillarBlock::new, Block.Properties.ofFullCopy(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_WOOD = Blocks.register(of("dead_speedrunner_wood"), RotatedPillarBlock::new, Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_WOOD).ignitedByLava());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_WOOD = Blocks.register(of("dead_stripped_speedrunner_wood"), RotatedPillarBlock::new, Block.Properties.ofFullCopy(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_LEAVES = Blocks.register(of("dead_speedrunner_leaves"), settings -> new TintedParticleLeavesBlock(0.01F, settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_LEAVES).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_SAPLING = Blocks.register(of("dead_speedrunner_sapling"), settings -> new SpeedrunnerSaplingBlock(ModSaplingGenerators.DEAD_SPEEDRUNNER, settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_SAPLING).ignitedByLava());
    public static final Block DEAD_POTTED_SPEEDRUNNER_SAPLING = Blocks.register(of("dead_potted_speedrunner_sapling"), settings -> new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, settings), Block.Properties.ofFullCopy(ModBlocks.DEAD_SPEEDRUNNER_SAPLING).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_PLANKS = Blocks.register(of("dead_speedrunner_planks"), Block::new, Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_PLANKS).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_SLAB = Blocks.register(of("dead_speedrunner_slab"), SlabBlock::new, Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_SLAB).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_STAIRS = Blocks.register(of("dead_speedrunner_stairs"), settings -> new StairBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.defaultBlockState(), settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_STAIRS).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_FENCE = Blocks.register(of("dead_speedrunner_fence"), FenceBlock::new, Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_FENCE).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_FENCE_GATE = Blocks.register(of("dead_speedrunner_fence_gate"), settings -> new FenceGateBlock(WoodType.OAK, settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_FENCE_GATE).ignitedByLava());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = Blocks.register(of("dead_wooden_speedrunner_trapdoor"), settings -> new TrapDoorBlock(BlockSetType.OAK, settings), Block.Properties.ofFullCopy(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR).ignitedByLava());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_BUTTON = Blocks.register(of("dead_wooden_speedrunner_button"), settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), Block.Properties.ofFullCopy(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON).ignitedByLava());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(of("dead_wooden_speedrunner_pressure_plate"), settings -> new PressurePlateBlock(BlockSetType.OAK, settings), Block.Properties.ofFullCopy(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE).ignitedByLava());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_DOOR = Blocks.register(of("dead_wooden_speedrunner_door"), settings -> new DoorBlock(BlockSetType.OAK, settings), Block.Properties.ofFullCopy(ModBlocks.WOODEN_SPEEDRUNNER_DOOR).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_sign", settings -> new CustomSignBlock("dead_speedrunner", settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_SIGN).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_wall_sign", settings -> new CustomWallSignBlock("dead_speedrunner", settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_WALL_SIGN).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_hanging_sign", settings -> new CustomHangingSignBlock("dead_speedrunner", settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_HANGING_SIGN).ignitedByLava());
    public static final Block DEAD_SPEEDRUNNER_HANGING_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_hanging_wall_sign", settings -> new CustomWallHangingSignBlock("dead_speedrunner", settings), Block.Properties.ofFullCopy(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN).ignitedByLava());

    public static final Block SPEEDRUNNER_TRAPDOOR = Blocks.register(of("speedrunner_trapdoor"), settings -> new TrapDoorBlock(BlockSetType.IRON, settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(2.5F)
            .noOcclusion()
            .isValidSpawn(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.METAL));

    public static final Block SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = Blocks.register(of("speedrunner_weighted_pressure_plate"), settings -> new WeightedPressurePlateBlock(100, BlockSetType.IRON, settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(0.5F)
            .noCollision()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_DOOR = Blocks.register(of("speedrunner_door"), settings -> new DoorBlock(BlockSetType.IRON, settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(2.5F)
            .noOcclusion()
            .instrument(NoteBlockInstrument.BASS)
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.METAL));

    public static final Block DEAD_SPEEDRUNNER_BUSH = Blocks.register(of("dead_speedrunner_bush"), DryVegetationBlock::new, Block.Properties.of()
            .instabreak()
            .noCollision()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.GRASS));

    public static final Block POTTED_DEAD_SPEEDRUNNER_BUSH = Blocks.register(of("potted_dead_speedrunner_bush"), settings -> new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, settings), Block.Properties.of()
            .instabreak()
            .noCollision()
            .pushReaction(PushReaction.DESTROY)
            .sound(SoundType.GRASS));

    public static final Block SPEEDRUNNERS_WORKBENCH = Blocks.register(of("speedrunners_workbench"), SpeedrunnersWorkbenchBlock::new, Block.Properties.of()
            .strength(1.6F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block SPEEDRUNNER_BLOCK = Blocks.register(of("speedrunner_block"), Block::new, Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .sound(SoundType.METAL));

    public static final Block RAW_SPEEDRUNNER_BLOCK = Blocks.register(of("raw_speedrunner_block"), Block::new, Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE));

    public static final Block THRUSTED_BLOCK = Blocks.register(of("thrusted_block"), Block::new, Block.Properties.of()
            .strength(1.5F)
            .isValidSpawn(Blocks::never)
            .sound(SoundType.GRASS));

    public static final Block SPEEDRUNNER_ORE = Blocks.register(of("speedrunner_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = Blocks.register(of("deepslate_speedrunner_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.DEEPSLATE));

    public static final Block NETHER_SPEEDRUNNER_ORE = Blocks.register(of("nether_speedrunner_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = Blocks.register(of("igneous_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE));

    public static final Block DEEPSLATE_IGNEOUS_ORE = Blocks.register(of("deepslate_igneous_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.DEEPSLATE));

    public static final Block NETHER_IGNEOUS_ORE = Blocks.register(of("nether_igneous_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.NETHER_ORE));

    public static final Block EXPERIENCE_ORE = Blocks.register(of("experience_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(5.0F, 10.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE));

    public static final Block DEEPSLATE_EXPERIENCE_ORE = Blocks.register(of("deepslate_experience_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(6.0F, 12.5F)
            .sound(SoundType.DEEPSLATE));

    public static final Block NETHER_EXPERIENCE_ORE = Blocks.register(of("nether_experience_ore"), settings -> new DropExperienceBlock(UniformInt.of(0, 0), settings), Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.NETHER_ORE));

    public static final Block FLESH_BLOCK = Blocks.register(of("flesh_block"), Block::new, BlockBehaviour.Properties.of()
            .strength(2.0F, 2.0F)
            .friction(1.01F)
            .sound(SoundType.HONEY_BLOCK));

    public static final Block DOOM_STONE = Blocks.register(of("doom_stone"), DoomBlock.Default::new, Block.Properties.of()
            .requiresCorrectToolForDrops()
            .strength(1.5F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE));

    public static final Block DOOM_LOG = Blocks.register(of("doom_log"), DoomBlock.Pillar::new, Block.Properties.of()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block STRIPPED_DOOM_LOG = Blocks.register(of("stripped_doom_log"), DoomBlock.Pillar::new, Block.Properties.of()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD));

    public static final Block DOOM_LEAVES = Blocks.register(of("doom_leaves"), DoomBlock.Leaves::new, Block.Properties.of()
            .strength(0.1F, 3600000.0F)
            .noOcclusion()
            .randomTicks()
            .isSuffocating(Blocks::never)
            .isViewBlocking(Blocks::never)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor(Blocks::never)
            .sound(SoundType.GRASS));

    /**
     * Creates and registers a {@code block.}
     */
    private static ResourceKey<Block> of(String id) {
        return ResourceKey.create(Registries.BLOCK, ofSpeedrunnerMod(id));
    }

    /**
     * Makes certain blocks {@code strippable}.
     */
    private static void registerStrippables() {
        StrippableBlockRegistry.register(SPEEDRUNNER_LOG, STRIPPED_SPEEDRUNNER_LOG);
        StrippableBlockRegistry.register(SPEEDRUNNER_WOOD, STRIPPED_SPEEDRUNNER_WOOD);
        StrippableBlockRegistry.register(DOOM_LOG, STRIPPED_DOOM_LOG);
    }

    /**
     * Initializes all Speedrunner Mod {@code blocks.}
     */
    public static void initializeBlocks() {
        registerStrippables();

        SpeedrunnerMod.debug("Initialized blocks.");
    }
}