package me.Danker.gui;

import me.Danker.DankersSkyblockMod;
import me.Danker.commands.ToggleCommand;
import me.Danker.handlers.ConfigHandler;
import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ExperimentsGui extends GuiScreen {
    private GuiButton goBack;

    private GuiButton ultrasequencer;

    private GuiButton chronomatron;

    private GuiButton superpairs;

    private GuiButton hideTooltips;

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        this.ultrasequencer = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Ultrasequencer Solver: " + Utils.getColouredBoolean(ToggleCommand.ultrasequencerToggled));
        this.chronomatron = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Chronomatron Solver: " + Utils.getColouredBoolean(ToggleCommand.chronomatronToggled));
        this.superpairs = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Superpairs Solver: " + Utils.getColouredBoolean(ToggleCommand.superpairsToggled));
        this.hideTooltips = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Hide Tooltips in Addons: " + Utils.getColouredBoolean(ToggleCommand.hideTooltipsInExperimentAddonsToggled));
        this.buttonList.add(this.goBack);
        this.buttonList.add(this.ultrasequencer);
        this.buttonList.add(this.chronomatron);
        this.buttonList.add(this.superpairs);
        this.buttonList.add(this.hideTooltips);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.goBack) {
            DankersSkyblockMod.guiToOpen = "dankergui1";
        } else if (button == this.ultrasequencer) {
            ToggleCommand.ultrasequencerToggled = !ToggleCommand.ultrasequencerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "UltraSequencer", ToggleCommand.ultrasequencerToggled);
            this.ultrasequencer.displayString = "Ultrasequencer Solver: " + Utils.getColouredBoolean(ToggleCommand.ultrasequencerToggled);
        } else if (button == this.chronomatron) {
            ToggleCommand.chronomatronToggled = !ToggleCommand.chronomatronToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Chronomatron", ToggleCommand.chronomatronToggled);
            this.chronomatron.displayString = "Chronomatron Solver: " + Utils.getColouredBoolean(ToggleCommand.chronomatronToggled);
        } else if (button == this.superpairs) {
            ToggleCommand.superpairsToggled = !ToggleCommand.superpairsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Superpairs", ToggleCommand.superpairsToggled);
            this.superpairs.displayString = "Superpairs Solver: " + Utils.getColouredBoolean(ToggleCommand.superpairsToggled);
        } else if (button == this.hideTooltips) {
            ToggleCommand.hideTooltipsInExperimentAddonsToggled = !ToggleCommand.hideTooltipsInExperimentAddonsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "HideTooltipsInExperimentAddons", ToggleCommand.hideTooltipsInExperimentAddonsToggled);
            this.hideTooltips.displayString = "Hide Tooltips in Addons: " + Utils.getColouredBoolean(ToggleCommand.hideTooltipsInExperimentAddonsToggled);
        }
    }
}
