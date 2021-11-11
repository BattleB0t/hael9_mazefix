package me.Danker.gui;

import me.Danker.DankersSkyblockMod;
import me.Danker.handlers.ConfigHandler;
import me.Danker.handlers.TextRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.commons.lang3.time.StopWatch;

public class SkillTrackerGui extends GuiScreen {
    private GuiButton goBack;

    private GuiButton start;

    private GuiButton stop;

    private GuiButton reset;

    private GuiButton hide;

    private GuiButton show;

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        this.start = new GuiButton(0, width / 2 - 140, (int)(height * 0.45D), 80, 20, "Start");
        this.stop = new GuiButton(0, width / 2 - 40, (int)(height * 0.45D), 80, 20, "Stop");
        this.reset = new GuiButton(0, width / 2 + 60, (int)(height * 0.45D), 80, 20, "Reset");
        this.hide = new GuiButton(0, width / 2 - 70, (int)(height * 0.55D), 60, 20, "Hide");
        this.show = new GuiButton(0, width / 2 + 10, (int)(height * 0.55D), 60, 20, "Show");
        this.buttonList.add(this.start);
        this.buttonList.add(this.stop);
        this.buttonList.add(this.reset);
        this.buttonList.add(this.hide);
        this.buttonList.add(this.show);
        this.buttonList.add(this.goBack);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        String stateText = "";
        if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended()) {
            stateText = "Timer: Running";
        } else if (!DankersSkyblockMod.skillStopwatch.isStarted() || DankersSkyblockMod.skillStopwatch.isSuspended()) {
            stateText = "Timer: Paused";
        }
        if (!DankersSkyblockMod.showSkillTracker)
            stateText = stateText + " (Hidden)";
        int stateTextWidth = this.mc.fontRendererObj.getStringWidth(stateText);
        new TextRenderer(this.mc, stateText, this.width / 2 - stateTextWidth / 2, 10, 1.0D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.goBack) {
            DankersSkyblockMod.guiToOpen = "dankergui1";
        } else if (button == this.start) {
            if (DankersSkyblockMod.skillStopwatch.isStarted() && DankersSkyblockMod.skillStopwatch.isSuspended()) {
                DankersSkyblockMod.skillStopwatch.resume();
            } else if (!DankersSkyblockMod.skillStopwatch.isStarted()) {
                DankersSkyblockMod.skillStopwatch.start();
            }
        } else if (button == this.stop) {
            if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended())
                DankersSkyblockMod.skillStopwatch.suspend();
        } else if (button == this.reset) {
            DankersSkyblockMod.skillStopwatch = new StopWatch();
            DankersSkyblockMod.farmingXPGained = 0.0D;
            DankersSkyblockMod.miningXPGained = 0.0D;
            DankersSkyblockMod.combatXPGained = 0.0D;
            DankersSkyblockMod.foragingXPGained = 0.0D;
            DankersSkyblockMod.fishingXPGained = 0.0D;
            DankersSkyblockMod.enchantingXPGained = 0.0D;
            DankersSkyblockMod.alchemyXPGained = 0.0D;
        } else if (button == this.hide) {
            DankersSkyblockMod.showSkillTracker = false;
            ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", false);
        } else if (button == this.show) {
            DankersSkyblockMod.showSkillTracker = true;
            ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", true);
        }
    }
}
