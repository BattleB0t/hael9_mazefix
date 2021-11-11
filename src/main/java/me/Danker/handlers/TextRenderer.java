package me.Danker.handlers;

import me.Danker.commands.ToggleCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;

public class TextRenderer extends Gui {
    public TextRenderer(Minecraft mc, String text, int x, int y, double scale) {
        double scaleReset = Math.pow(scale, -1.0D);
        GL11.glScaled(scale, scale, scale);
        y -= mc.fontRendererObj.FONT_HEIGHT;
        for (String line : text.split("\n")) {
            y = (int)(y + mc.fontRendererObj.FONT_HEIGHT * scale);
            if (ToggleCommand.outlineTextToggled) {
                String noColorLine = StringUtils.stripControlCodes(line);
                mc.fontRendererObj.drawString(noColorLine, ((int)Math.round(x / scale) - 1), (int)Math.round(y / scale), 0, false);
                mc.fontRendererObj.drawString(noColorLine, ((int)Math.round(x / scale) + 1), (int)Math.round(y / scale), 0, false);
                mc.fontRendererObj.drawString(noColorLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) - 1), 0, false);
                mc.fontRendererObj.drawString(noColorLine, (int)Math.round(x / scale), ((int)Math.round(y / scale) + 1), 0, false);
                mc.fontRendererObj.drawString(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, false);
            } else {
                mc.fontRendererObj.drawString(line, (int)Math.round(x / scale), (int)Math.round(y / scale), 16777215, true);
            }
        }
        GL11.glScaled(scaleReset, scaleReset, scaleReset);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
