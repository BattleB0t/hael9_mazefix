package me.Danker.gui;

import me.Danker.DankersSkyblockMod;
import me.Danker.commands.BlockSlayerCommand;
import me.Danker.handlers.ConfigHandler;
import me.Danker.handlers.TextRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class OnlySlayerGui extends GuiScreen {
    private int onlyNumberInt = 4;

    private String onlyName = "Revenant Horror";

    private GuiButton goBack;

    private GuiButton off;

    private GuiButton zombie;

    private GuiButton spider;

    private GuiButton wolf;

    private GuiButton one;

    private GuiButton two;

    private GuiButton three;

    private GuiButton four;

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.onlyName = BlockSlayerCommand.onlySlayerName;
        switch (BlockSlayerCommand.onlySlayerNumber) {
            case "I":
                this.onlyNumberInt = 1;
                break;
            case "II":
                this.onlyNumberInt = 2;
                break;
            case "III":
                this.onlyNumberInt = 3;
                break;
            default:
                this.onlyNumberInt = 4;
                break;
        }
        this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        this.off = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Off");
        this.zombie = new GuiButton(0, width / 2 - 200, (int)(height * 0.4D), 120, 20, "Zombie");
        this.spider = new GuiButton(0, width / 2 - 60, (int)(height * 0.4D), 120, 20, "Spider");
        this.wolf = new GuiButton(0, width / 2 + 80, (int)(height * 0.4D), 120, 20, "Wolf");
        this.one = new GuiButton(0, width / 2 - 190, (int)(height * 0.6D), 85, 20, "I");
        this.two = new GuiButton(0, width / 2 - 95, (int)(height * 0.6D), 85, 20, "II");
        this.three = new GuiButton(0, width / 2 + 10, (int)(height * 0.6D), 85, 20, "III");
        this.four = new GuiButton(0, width / 2 + 115, (int)(height * 0.6D), 85, 20, "IV");
        this.buttonList.add(this.off);
        this.buttonList.add(this.zombie);
        this.buttonList.add(this.spider);
        this.buttonList.add(this.wolf);
        this.buttonList.add(this.one);
        this.buttonList.add(this.two);
        this.buttonList.add(this.three);
        this.buttonList.add(this.four);
        this.buttonList.add(this.goBack);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String displayText;
        drawDefaultBackground();
        Minecraft mc = Minecraft.getMinecraft();
        if (BlockSlayerCommand.onlySlayerName.equals("")) {
            displayText = "Only Allow Slayer: Off";
        } else {
            displayText = "Only Allow Slayer: " + BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber;
        }
        int displayWidth = mc.fontRendererObj.getStringWidth(displayText);
        new TextRenderer(mc, displayText, this.width / 2 - displayWidth / 2, 10, 1.0D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        String onlyNumber;
        if (button == this.goBack) {
            DankersSkyblockMod.guiToOpen = "dankergui1";
        } else {
            if (button == this.off) {
                BlockSlayerCommand.onlySlayerName = "";
                BlockSlayerCommand.onlySlayerNumber = "";
                ConfigHandler.writeStringConfig("toggles", "BlockSlayer", "");
                return;
            }
            if (button == this.zombie) {
                this.onlyName = "Revenant Horror";
            } else if (button == this.spider) {
                this.onlyName = "Tarantula Broodfather";
            } else if (button == this.wolf) {
                this.onlyName = "Sven Packmaster";
            } else if (button == this.one) {
                this.onlyNumberInt = 1;
            } else if (button == this.two) {
                this.onlyNumberInt = 2;
            } else if (button == this.three) {
                this.onlyNumberInt = 3;
            } else if (button == this.four) {
                this.onlyNumberInt = 4;
            }
        }
        switch (this.onlyNumberInt) {
            case 1:
                onlyNumber = "I";
                break;
            case 2:
                onlyNumber = "II";
                break;
            case 3:
                onlyNumber = "III";
                break;
            default:
                onlyNumber = "IV";
                break;
        }
        BlockSlayerCommand.onlySlayerName = this.onlyName;
        BlockSlayerCommand.onlySlayerNumber = onlyNumber;
        ConfigHandler.writeStringConfig("toggles", "BlockSlayer", BlockSlayerCommand.onlySlayerName + " " + BlockSlayerCommand.onlySlayerNumber);
    }
}
