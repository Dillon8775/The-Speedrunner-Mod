package net.dillon.speedrunnermod.mixin.block.nether_portal;

import net.dillon.speedrunnermod.tag.ModBlockItemTags;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PortalShape.class)
public class PortalShapeMixin {

    /**
     * Allows nether portals to be built with any block in the {@code "nether_portal_base_blocks"} tag.
     */
    @Shadow
    private static final BlockBehaviour.StatePredicate FRAME = (state, world, pos) -> {
        return state.is(ModBlockItemTags.NETHER_PORTAL_BASE_BLOCKS.block());
    };
}