package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.spider.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Spider.class)
public class SpiderMixin extends Monster {

    public SpiderMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        if (!super.doHurtTarget(world, target)) {
            return false;
        } else {
            if (target instanceof Player player) {
                if (isDoomMode()) {
                     player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TickCalculator.seconds(10), 0));
                }
            }

            return true;
        }
    }
}