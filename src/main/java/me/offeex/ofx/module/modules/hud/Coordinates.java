package me.offeex.ofx.module.modules.hud;

import me.offeex.ofx.Main;
import me.offeex.ofx.api.util.DimensionUtil;
import me.offeex.ofx.gui.api.ColorUtils;
import me.offeex.ofx.module.Module;
import me.offeex.ofx.module.modules.client.ClickGui;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;

public class Coordinates extends Module {
    public Coordinates() {
        super("Coords", "Shows current coords", KEY_UNBOUND, Category.HUD, false);
    }

    String stringXN, stringZN;

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        assert MinecraftClient.getInstance().player != null;
        String stringX = String.format("%.1f", MinecraftClient.getInstance().player.getX());
        String stringY = String.format("%.1f", MinecraftClient.getInstance().player.getY());
        String stringZ = String.format("%.1f", MinecraftClient.getInstance().player.getZ());

        if (DimensionUtil.getDimension() == 1) {
            stringXN = String.format("%.1f", MinecraftClient.getInstance().player.getX() * 8);
            stringZN = String.format("%.1f", MinecraftClient.getInstance().player.getZ() * 8);
        } else if (DimensionUtil.getDimension() == 0) {
            stringXN = String.format("%.1f", MinecraftClient.getInstance().player.getX() / 8);
            stringZN = String.format("%.1f", MinecraftClient.getInstance().player.getZ() / 8);
        }

        if (isEnabled()) {
            if (DimensionUtil.getDimension() == 2)
                width = Main.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ, Main.sFontRenderer.getFontsize()) + 8;
            else
                width = Main.sFontRenderer.getStringWidth("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", Main.sFontRenderer.getFontsize()) + 8;
        }
        if (ClickGui.getCurrentScreen() == 2)
            Screen.fill(stack, x, y, x + width, y + 16, ColorUtils.withTransparency(ColorUtils.Colors.SECONDARY, 50));
        if (DimensionUtil.getDimension() == 2)
            Main.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ, x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
        else
            Main.sFontRenderer.drawString("XYZ: " + stringX + ", " + stringY + ", " + stringZ + " (" + stringXN + ", " + stringZN + ")", x + 4, y + 4, ColorUtils.Colors.PRIMARY.getRGB(), true);
    }
}