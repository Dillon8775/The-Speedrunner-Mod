package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.block.sign.CustomSignBlock;
import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.dillon.speedrunnermod.block.sign.hanging.CustomHangingSignBlock;
import net.dillon.speedrunnermod.block.sign.hanging.wall.CustomWallHangingSignBlock;
import net.dillon.speedrunnermod.block.sign.wall.CustomWallSignBlock;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code blocks.}
 */
public class ModBlocks {
    public static final Identifier SPEEDRUNNER_SIGN_TEXTURE = ofSpeedrunnerMod("entity/signs/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_SIGN_TEXTURE = ofSpeedrunnerMod("entity/signs/hanging/speedrunner");
    public static final Identifier SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = ofSpeedrunnerMod("textures/gui/hanging_signs/speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_SIGN_TEXTURE = ofSpeedrunnerMod("entity/signs/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_SIGN_TEXTURE = ofSpeedrunnerMod("entity/signs/hanging/dead_speedrunner");
    public static final Identifier DEAD_SPEEDRUNNER_HANGING_GUI_SIGN_TEXTURE = ofSpeedrunnerMod("textures/gui/hanging_signs/dead_speedrunner");

    public static final Block SPEEDRUNNER_LOG = Blocks.register(of("speedrunner_log"), PillarBlock::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_LOG = Blocks.register(of("stripped_speedrunner_log"), PillarBlock::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WOOD = Blocks.register(of("speedrunner_wood"), PillarBlock::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_SPEEDRUNNER_WOOD = Blocks.register(of("stripped_speedrunner_wood"), PillarBlock::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_LEAVES = Blocks.register(of("speedrunner_leaves"), settings -> new TintedParticleLeavesBlock(0.01F, settings), Block.Settings.create()
            .strength(0.1F)
            .nonOpaque()
            .ticksRandomly()
            .allowsSpawning(Blocks::canSpawnOnLeaves)
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_SAPLING = Blocks.register(of("speedrunner_sapling"), settings -> new SpeedrunnerSaplingBlock(ModSaplingGenerators.SPEEDRUNNER, settings), Block.Settings.create()
            .breakInstantly()
            .ticksRandomly()
            .noCollision()
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_SPEEDRUNNER_SAPLING = Blocks.register(of("potted_speedrunner_sapling"), settings -> new FlowerPotBlock(ModBlocks.SPEEDRUNNER_SAPLING, settings), Block.Settings.create()
            .breakInstantly()
            .nonOpaque()
            .pistonBehavior(PistonBehavior.DESTROY));

    public static final Block SPEEDRUNNER_PLANKS = Blocks.register(of("speedrunner_planks"), Block::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SLAB = Blocks.register(of("speedrunner_slab"), SlabBlock::new, Block.Settings.create()
            .strength(1.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_STAIRS = Blocks.register(of("speedrunner_stairs"), settings -> new StairsBlock(ModBlocks.SPEEDRUNNER_PLANKS.getDefaultState(), settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_PLANKS));

    public static final Block SPEEDRUNNER_FENCE = Blocks.register(of("speedrunner_fence"), FenceBlock::new, Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_FENCE_GATE = Blocks.register(of("speedrunner_fence_gate"), settings -> new FenceGateBlock(WoodType.OAK, settings), Block.Settings.create()
            .strength(1.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_TRAPDOOR = Blocks.register(of("wooden_speedrunner_trapdoor"), settings -> new TrapdoorBlock(BlockSetType.OAK, settings), Block.Settings.create()
            .strength(1.3F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_BUTTON = Blocks.register(of("wooden_speedrunner_button"), settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), Block.Settings.create()
            .strength(0.35F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(of("wooden_speedrunner_pressure_plate"), settings -> new PressurePlateBlock(BlockSetType.OAK, settings), Block.Settings.create()
            .strength(0.35F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block WOODEN_SPEEDRUNNER_DOOR = Blocks.register(of("wooden_speedrunner_door"), settings -> new DoorBlock(BlockSetType.OAK, settings), Block.Settings.create()
            .strength(1.3F)
            .nonOpaque()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_sign", settings -> new CustomSignBlock("speedrunner", settings), Block.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_wall_sign", settings -> new CustomWallSignBlock("speedrunner", settings), Block.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_hanging_sign", settings -> new CustomHangingSignBlock("speedrunner", settings), Block.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block SPEEDRUNNER_HANGING_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("speedrunner_hanging_wall_sign", settings -> new CustomWallHangingSignBlock("speedrunner", settings), Block.Settings.create()
            .strength(1.0F)
            .noCollision()
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.HANGING_SIGN));

    public static final Block DEAD_SPEEDRUNNER_LOG = Blocks.register(of("dead_speedrunner_log"), PillarBlock::new, Block.Settings.copy(ModBlocks.SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_LOG = Blocks.register(of("dead_stripped_speedrunner_log"), PillarBlock::new, Block.Settings.copy(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).burnable());
    public static final Block DEAD_SPEEDRUNNER_WOOD = Blocks.register(of("dead_speedrunner_wood"), PillarBlock::new, Block.Settings.copy(ModBlocks.SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_STRIPPED_SPEEDRUNNER_WOOD = Blocks.register(of("dead_stripped_speedrunner_wood"), PillarBlock::new, Block.Settings.copy(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD).burnable());
    public static final Block DEAD_SPEEDRUNNER_LEAVES = Blocks.register(of("dead_speedrunner_leaves"), settings -> new TintedParticleLeavesBlock(0.01F, settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_LEAVES).burnable());
    public static final Block DEAD_SPEEDRUNNER_SAPLING = Blocks.register(of("dead_speedrunner_sapling"), settings -> new SpeedrunnerSaplingBlock(ModSaplingGenerators.DEAD_SPEEDRUNNER, settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_POTTED_SPEEDRUNNER_SAPLING = Blocks.register(of("dead_potted_speedrunner_sapling"), settings -> new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, settings), Block.Settings.copy(ModBlocks.DEAD_SPEEDRUNNER_SAPLING).burnable());
    public static final Block DEAD_SPEEDRUNNER_PLANKS = Blocks.register(of("dead_speedrunner_planks"), Block::new, Block.Settings.copy(ModBlocks.SPEEDRUNNER_PLANKS).burnable());
    public static final Block DEAD_SPEEDRUNNER_SLAB = Blocks.register(of("dead_speedrunner_slab"), SlabBlock::new, Block.Settings.copy(ModBlocks.SPEEDRUNNER_SLAB).burnable());
    public static final Block DEAD_SPEEDRUNNER_STAIRS = Blocks.register(of("dead_speedrunner_stairs"), settings -> new StairsBlock(ModBlocks.DEAD_SPEEDRUNNER_PLANKS.getDefaultState(), settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_STAIRS).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE = Blocks.register(of("dead_speedrunner_fence"), FenceBlock::new, Block.Settings.copy(ModBlocks.SPEEDRUNNER_FENCE).burnable());
    public static final Block DEAD_SPEEDRUNNER_FENCE_GATE = Blocks.register(of("dead_speedrunner_fence_gate"), settings -> new FenceGateBlock(WoodType.OAK, settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_FENCE_GATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR = Blocks.register(of("dead_wooden_speedrunner_trapdoor"), settings -> new TrapdoorBlock(BlockSetType.OAK, settings), Block.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_BUTTON = Blocks.register(of("dead_wooden_speedrunner_button"), settings -> new ButtonBlock(BlockSetType.OAK, 30, settings), Block.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE = Blocks.register(of("dead_wooden_speedrunner_pressure_plate"), settings -> new PressurePlateBlock(BlockSetType.OAK, settings), Block.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE).burnable());
    public static final Block DEAD_WOODEN_SPEEDRUNNER_DOOR = Blocks.register(of("dead_wooden_speedrunner_door"), settings -> new DoorBlock(BlockSetType.OAK, settings), Block.Settings.copy(ModBlocks.WOODEN_SPEEDRUNNER_DOOR).burnable());
    public static final Block DEAD_SPEEDRUNNER_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_sign", settings -> new CustomSignBlock("dead_speedrunner", settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_wall_sign", settings -> new CustomWallSignBlock("dead_speedrunner", settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_WALL_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_hanging_sign", settings -> new CustomHangingSignBlock("dead_speedrunner", settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_HANGING_SIGN).burnable());
    public static final Block DEAD_SPEEDRUNNER_HANGING_WALL_SIGN = TerraformSignBlockHelper.registerSignBlock("dead_speedrunner_hanging_wall_sign", settings -> new CustomWallHangingSignBlock("dead_speedrunner", settings), Block.Settings.copy(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN).burnable());

    public static final Block SPEEDRUNNER_TRAPDOOR = Blocks.register(of("speedrunner_trapdoor"), settings -> new TrapdoorBlock(BlockSetType.IRON, settings), Block.Settings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .allowsSpawning(Blocks::never)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.METAL));

    public static final Block SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE = Blocks.register(of("speedrunner_weighted_pressure_plate"), settings -> new WeightedPressurePlateBlock(100, BlockSetType.IRON, settings), Block.Settings.create()
            .requiresTool()
            .strength(0.5F)
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_DOOR = Blocks.register(of("speedrunner_door"), settings -> new DoorBlock(BlockSetType.IRON, settings), Block.Settings.create()
            .requiresTool()
            .strength(2.5F)
            .nonOpaque()
            .instrument(NoteBlockInstrument.BASS)
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.METAL));

    public static final Block DEAD_SPEEDRUNNER_BUSH = Blocks.register(of("dead_speedrunner_bush"), DryVegetationBlock::new, Block.Settings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block POTTED_DEAD_SPEEDRUNNER_BUSH = Blocks.register(of("potted_dead_speedrunner_bush"), settings -> new FlowerPotBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, settings), Block.Settings.create()
            .breakInstantly()
            .noCollision()
            .pistonBehavior(PistonBehavior.DESTROY)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNERS_WORKBENCH = Blocks.register(of("speedrunners_workbench"), SpeedrunnersWorkbenchBlock::new, Block.Settings.create()
            .strength(1.6F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block SPEEDRUNNER_BLOCK = Blocks.register(of("speedrunner_block"), Block::new, Block.Settings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.IRON_XYLOPHONE)
            .sounds(BlockSoundGroup.METAL));

    public static final Block RAW_SPEEDRUNNER_BLOCK = Blocks.register(of("raw_speedrunner_block"), Block::new, Block.Settings.create()
            .requiresTool()
            .strength(5.0F, 6.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block THRUSTED_BLOCK = Blocks.register(of("thrusted_block"), Block::new, Block.Settings.create()
            .strength(0.5F)
            .allowsSpawning(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    public static final Block SPEEDRUNNER_ORE = Blocks.register(of("speedrunner_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_SPEEDRUNNER_ORE = Blocks.register(of("deepslate_speedrunner_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_SPEEDRUNNER_ORE = Blocks.register(of("nether_speedrunner_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_GOLD_ORE));

    public static final Block IGNEOUS_ORE = Blocks.register(of("igneous_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_IGNEOUS_ORE = Blocks.register(of("deepslate_igneous_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(4.5F, 4.5F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_IGNEOUS_ORE = Blocks.register(of("nether_igneous_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block EXPERIENCE_ORE = Blocks.register(of("experience_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(5.0F, 10.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DEEPSLATE_EXPERIENCE_ORE = Blocks.register(of("deepslate_experience_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(6.0F, 12.5F)
            .sounds(BlockSoundGroup.DEEPSLATE));

    public static final Block NETHER_EXPERIENCE_ORE = Blocks.register(of("nether_experience_ore"), settings -> new ExperienceDroppingBlock(UniformIntProvider.create(0, 0), settings), Block.Settings.create()
            .requiresTool()
            .strength(3.0F, 3.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.NETHER_ORE));

    public static final Block FLESH_BLOCK = Blocks.register(of("flesh_block"), Block::new, AbstractBlock.Settings.create()
            .strength(3.0F, 3.0F)
            .slipperiness(1.01F)
            .sounds(BlockSoundGroup.HONEY));

    public static final Block DOOM_STONE = Blocks.register(of("doom_stone"), DoomBlock.Default::new, Block.Settings.create()
            .requiresTool()
            .strength(1.5F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sounds(BlockSoundGroup.STONE));

    public static final Block DOOM_LOG = Blocks.register(of("doom_log"), DoomBlock.Pillar::new, Block.Settings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_DOOM_LOG = Blocks.register(of("stripped_doom_log"), DoomBlock.Pillar::new, Block.Settings.create()
            .strength(1.0F, 3600000.0F)
            .instrument(NoteBlockInstrument.BASS)
            .sounds(BlockSoundGroup.WOOD));

    public static final Block DOOM_LEAVES = Blocks.register(of("doom_leaves"), DoomBlock.Leaves::new, Block.Settings.create()
            .strength(0.1F, 3600000.0F)
            .nonOpaque()
            .ticksRandomly()
            .suffocates(Blocks::never)
            .blockVision(Blocks::never)
            .pistonBehavior(PistonBehavior.DESTROY)
            .solidBlock(Blocks::never)
            .sounds(BlockSoundGroup.GRASS));

    /**
     * Creates and registers a {@code block.}
     */
    private static RegistryKey<Block> of(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, ofSpeedrunnerMod(id));
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