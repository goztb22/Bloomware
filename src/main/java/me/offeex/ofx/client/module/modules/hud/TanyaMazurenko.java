package me.offeex.ofx.client.module.modules.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import me.offeex.ofx.client.module.Module;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TanyaMazurenko extends Module {
    public TanyaMazurenko() {
        super("Tanya Mazurenko", "oops :^)", KEY_UNBOUND, Category.HUD, false);
    }

    Identifier tanya = new Identifier("ofx", "ez.gif");

    @Override
    public void draw(MatrixStack stack, int mouseX, int mouseY, float tickDelta) {
        if (isEnabled()) {
            RenderSystem.color4f(1, 1, 1, 1);
            mc.getTextureManager().bindTexture(tanya);
            DrawableHelper.drawTexture(stack, (int) (1.5 + 30), 64, 0, 0, 0, 92, 64, 64, 92);
        }
    }
}