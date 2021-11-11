package me.Danker.gui;

import me.Danker.handlers.TextRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class WarningGui extends GuiScreen {
    private GuiButton close;

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.close = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Close");
        this.buttonList.add(this.close);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        String text0 = "";
        int text0Width = this.mc.fontRendererObj.getStringWidth(text0);
        new TextRenderer(this.mc, text0, (int)(this.width * 0.45D) - text0Width / 2, (int)(this.height * 0.1D), 2.0D);
        String text1 = "You are using SpiderFrog's Old Animations mod.";
        int text1Width = this.mc.fontRendererObj.getStringWidth(text1);
        new TextRenderer(this.mc, text1, this.width / 2 - text1Width / 2, (int)(this.height * 0.3D), 1.0D);
        String text2 = "This mod breaks Danker's Skyblock Mod.";
        int text2Width = this.mc.fontRendererObj.getStringWidth(text2);
        new TextRenderer(this.mc, text2, this.width / 2 - text2Width / 2, (int)(this.height * 0.4D), 1.0D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.close)
            Minecraft.getMinecraft().displayGuiScreen(null);
    }
}
