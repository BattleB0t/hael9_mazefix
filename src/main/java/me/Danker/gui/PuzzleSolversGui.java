package me.Danker.gui;

import me.Danker.DankersSkyblockMod;
import me.Danker.commands.ToggleCommand;
import me.Danker.handlers.ConfigHandler;
import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class PuzzleSolversGui extends GuiScreen {
    private int page;

    private GuiButton goBack;

    private GuiButton backPage;

    private GuiButton nextPage;

    private GuiButton riddle;

    private GuiButton trivia;

    private GuiButton blaze;

    private GuiButton creeper;

    private GuiButton water;

    private GuiButton ticTacToe;

    private GuiButton startsWith;

    private GuiButton selectAll;

    private GuiButton clickOrder;

    private GuiButton blockClicks;

    private GuiButton itemFrameOnSeaLanterns;

    public PuzzleSolversGui(int page) {
        this.page = page;
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        this.backPage = new GuiButton(0, width / 2 - 100, (int)(height * 0.8D), 80, 20, "< Back");
        this.nextPage = new GuiButton(0, width / 2 + 20, (int)(height * 0.8D), 80, 20, "Next >");
        this.riddle = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Riddle Solver: " + Utils.getColouredBoolean(ToggleCommand.threeManToggled));
        this.trivia = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Trivia Solver: " + Utils.getColouredBoolean(ToggleCommand.oruoToggled));
        this.blaze = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Blaze Solver: " + Utils.getColouredBoolean(ToggleCommand.blazeToggled));
        this.creeper = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Creeper Solver: " + Utils.getColouredBoolean(ToggleCommand.creeperToggled));
        this.water = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Water Solver: " + Utils.getColouredBoolean(ToggleCommand.waterToggled));
        this.ticTacToe = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Tic Tac Toe Solver: " + Utils.getColouredBoolean(ToggleCommand.ticTacToeToggled));
        this.startsWith = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Starts With Letter Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.startsWithToggled));
        this.selectAll = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Select All Color Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.selectAllToggled));
        this.clickOrder = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Click in Order Terminal Helper: " + Utils.getColouredBoolean(ToggleCommand.clickInOrderToggled));
        this.blockClicks = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Block Wrong Clicks on Terminals: " + Utils.getColouredBoolean(ToggleCommand.blockWrongTerminalClicksToggled));
        this.itemFrameOnSeaLanterns = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Ignore Arrows On Sea Lanterns: " + Utils.getColouredBoolean(ToggleCommand.itemFrameOnSeaLanternsToggled));
        switch (this.page) {
            case 1:
                this.buttonList.add(this.riddle);
                this.buttonList.add(this.trivia);
                this.buttonList.add(this.blaze);
                this.buttonList.add(this.creeper);
                this.buttonList.add(this.water);
                this.buttonList.add(this.ticTacToe);
                this.buttonList.add(this.startsWith);
                this.buttonList.add(this.nextPage);
                break;
            case 2:
                this.buttonList.add(this.selectAll);
                this.buttonList.add(this.clickOrder);
                this.buttonList.add(this.blockClicks);
                this.buttonList.add(this.itemFrameOnSeaLanterns);
                this.buttonList.add(this.backPage);
                break;
        }
        this.buttonList.add(this.goBack);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.goBack) {
            DankersSkyblockMod.guiToOpen = "dankergui1";
        } else if (button == this.backPage) {
            Minecraft.getMinecraft().displayGuiScreen(new PuzzleSolversGui(this.page - 1));
        } else if (button == this.nextPage) {
            Minecraft.getMinecraft().displayGuiScreen(new PuzzleSolversGui(this.page + 1));
        } else if (button == this.riddle) {
            ToggleCommand.threeManToggled = !ToggleCommand.threeManToggled;
            ConfigHandler.writeBooleanConfig("toggles", "ThreeManPuzzle", ToggleCommand.threeManToggled);
            this.riddle.displayString = "Riddle Solver: " + Utils.getColouredBoolean(ToggleCommand.threeManToggled);
        } else if (button == this.trivia) {
            ToggleCommand.oruoToggled = !ToggleCommand.oruoToggled;
            ConfigHandler.writeBooleanConfig("toggles", "OruoPuzzle", ToggleCommand.oruoToggled);
            this.trivia.displayString = "Trivia Solver: " + Utils.getColouredBoolean(ToggleCommand.oruoToggled);
        } else if (button == this.blaze) {
            ToggleCommand.blazeToggled = !ToggleCommand.blazeToggled;
            ConfigHandler.writeBooleanConfig("toggles", "BlazePuzzle", ToggleCommand.blazeToggled);
            this.blaze.displayString = "Blaze Solver: " + Utils.getColouredBoolean(ToggleCommand.blazeToggled);
        } else if (button == this.creeper) {
            ToggleCommand.creeperToggled = !ToggleCommand.creeperToggled;
            ConfigHandler.writeBooleanConfig("toggles", "CreeperPuzzle", ToggleCommand.creeperToggled);
            this.creeper.displayString = "Creeper Solver: " + Utils.getColouredBoolean(ToggleCommand.creeperToggled);
        } else if (button == this.water) {
            ToggleCommand.waterToggled = !ToggleCommand.waterToggled;
            ConfigHandler.writeBooleanConfig("toggles", "WaterPuzzle", ToggleCommand.waterToggled);
            this.water.displayString = "Water Solver: " + Utils.getColouredBoolean(ToggleCommand.waterToggled);
        } else if (button == this.ticTacToe) {
            ToggleCommand.ticTacToeToggled = !ToggleCommand.ticTacToeToggled;
            ConfigHandler.writeBooleanConfig("toggles", "TicTacToePuzzle", ToggleCommand.ticTacToeToggled);
            this.ticTacToe.displayString = "Tic Tac Toe Solver: " + Utils.getColouredBoolean(ToggleCommand.ticTacToeToggled);
        } else if (button == this.startsWith) {
            ToggleCommand.startsWithToggled = !ToggleCommand.startsWithToggled;
            ConfigHandler.writeBooleanConfig("toggles", "StartsWithTerminal", ToggleCommand.startsWithToggled);
            this.startsWith.displayString = "Starts With Letter Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.startsWithToggled);
        } else if (button == this.selectAll) {
            ToggleCommand.selectAllToggled = !ToggleCommand.selectAllToggled;
            ConfigHandler.writeBooleanConfig("toggles", "SelectAllTerminal", ToggleCommand.selectAllToggled);
            this.selectAll.displayString = "Select All Color Terminal Solver: " + Utils.getColouredBoolean(ToggleCommand.selectAllToggled);
        } else if (button == this.clickOrder) {
            ToggleCommand.clickInOrderToggled = !ToggleCommand.clickInOrderToggled;
            ConfigHandler.writeBooleanConfig("toggles", "ClickInOrderTerminal", ToggleCommand.clickInOrderToggled);
            this.clickOrder.displayString = "Click in Order Terminal Helper: " + Utils.getColouredBoolean(ToggleCommand.clickInOrderToggled);
        } else if (button == this.blockClicks) {
            ToggleCommand.blockWrongTerminalClicksToggled = !ToggleCommand.blockWrongTerminalClicksToggled;
            ConfigHandler.writeBooleanConfig("toggles", "BlockWrongTerminalClicks", ToggleCommand.blockWrongTerminalClicksToggled);
            this.blockClicks.displayString = "Block Wrong Clicks on Terminals: " + Utils.getColouredBoolean(ToggleCommand.blockWrongTerminalClicksToggled);
        } else if (button == this.itemFrameOnSeaLanterns) {
            ToggleCommand.itemFrameOnSeaLanternsToggled = !ToggleCommand.itemFrameOnSeaLanternsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "IgnoreItemFrameOnSeaLanterns", ToggleCommand.itemFrameOnSeaLanternsToggled);
            this.itemFrameOnSeaLanterns.displayString = "Ignore Arrows On Sea Lanterns: " + Utils.getColouredBoolean(ToggleCommand.itemFrameOnSeaLanternsToggled);
        }
    }
}
