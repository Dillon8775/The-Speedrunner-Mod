package net.dillon.speedrunnermod.platform.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.platform.client.ClientModPlatform;
import net.dillon.dillonlib.platform.info.PlatformMenuButton;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.player.LocalPlayer;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

public class ClientSpeedrunnerModPlatform extends ClientModPlatform {

    @Override
    public String modId() {
        return ModConstants.MOD_ID;
    }

    @Override
    public List<PlatformMenuButton> menuButtons() {
        return List.of(
                PlatformMenuButton.pauseOnlyEmpty(
                        client().worldCreation().instantWorldCreation,
                        ClientModUtil.createNewWorldButton()
                )
        );
    }

    // Unused
    @Override
    public KeyMapping registerKeyMapping(String name, InputConstants.Type type, KeyMapping.Category category, int value) {
        return null;
    }

    @Override
    public boolean canSendPacket(LocalPlayer localPlayer) {
        return false;
    }
}