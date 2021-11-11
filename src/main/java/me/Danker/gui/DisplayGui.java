package me.Danker.gui;

import me.Danker.DankersSkyblockMod;
import me.Danker.commands.DisplayCommand;
import me.Danker.handlers.ConfigHandler;
import me.Danker.handlers.TextRenderer;
import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class DisplayGui extends GuiScreen {
    private boolean addSession = false;

    private GuiButton goBack;

    private GuiButton off;

    private GuiButton showSession;

    private GuiButton zombie;

    private GuiButton spider;

    private GuiButton wolf;

    private GuiButton auto;

    private GuiButton fishing;

    private GuiButton fishingWinter;

    private GuiButton fishingFestival;

    private GuiButton fishingSpooky;

    private GuiButton mythological;

    private GuiButton catacombsF1;

    private GuiButton catacombsF2;

    private GuiButton catacombsF3;

    private GuiButton catacombsF4;

    private GuiButton catacombsF5;

    private GuiButton catacombsF6;

    private GuiButton catacombsF7;

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        super.initGui();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int height = sr.getScaledHeight();
        int width = sr.getScaledWidth();
        this.goBack = new GuiButton(0, 2, height - 30, 100, 20, "Go Back");
        this.showSession = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Current Session Only: " + Utils.getColouredBoolean(this.addSession));
        this.off = new GuiButton(0, width / 2 - 210, (int)(height * 0.2D), "Off");
        this.auto = new GuiButton(0, width / 2 + 10, (int)(height * 0.2D), "Auto");
        this.zombie = new GuiButton(0, width / 2 - 190, (int)(height * 0.3D), 110, 20, "Zombie");
        this.spider = new GuiButton(0, width / 2 - 55, (int)(height * 0.3D), 110, 20, "Spider");
        this.wolf = new GuiButton(0, width / 2 + 75, (int)(height * 0.3D), 110, 20, "Wolf");
        this.fishing = new GuiButton(0, width / 2 - 230, (int)(height * 0.4D), 100, 20, "Fishing");
        this.fishingWinter = new GuiButton(0, width / 2 - 110, (int)(height * 0.4D), 100, 20, "Fishing Winter");
        this.fishingFestival = new GuiButton(0, width / 2 + 10, (int)(height * 0.4D), 100, 20, "Fishing Festival");
        this.fishingSpooky = new GuiButton(0, width / 2 + 130, (int)(height * 0.4D), 100, 20, "Fishing Spooky");
        this.mythological = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), 200, 20, "Mythological");
        this.catacombsF1 = new GuiButton(0, width / 2 - 205, (int)(height * 0.65D), 50, 20, "F1");
        this.catacombsF2 = new GuiButton(0, width / 2 - 145, (int)(height * 0.65D), 50, 20, "F2");
        this.catacombsF3 = new GuiButton(0, width / 2 - 85, (int)(height * 0.65D), 50, 20, "F3");
        this.catacombsF4 = new GuiButton(0, width / 2 - 25, (int)(height * 0.65D), 50, 20, "F4");
        this.catacombsF5 = new GuiButton(0, width / 2 + 35, (int)(height * 0.65D), 50, 20, "F5");
        this.catacombsF6 = new GuiButton(0, width / 2 + 95, (int)(height * 0.65D), 50, 20, "F6");
        this.catacombsF7 = new GuiButton(0, width / 2 + 155, (int)(height * 0.65D), 50, 20, "F7");
        this.buttonList.add(this.showSession);
        this.buttonList.add(this.off);
        this.buttonList.add(this.auto);
        this.buttonList.add(this.zombie);
        this.buttonList.add(this.spider);
        this.buttonList.add(this.wolf);
        this.buttonList.add(this.fishing);
        this.buttonList.add(this.fishingWinter);
        this.buttonList.add(this.fishingFestival);
        this.buttonList.add(this.fishingSpooky);
        this.buttonList.add(this.mythological);
        this.buttonList.add(this.catacombsF1);
        this.buttonList.add(this.catacombsF2);
        this.buttonList.add(this.catacombsF3);
        this.buttonList.add(this.catacombsF4);
        this.buttonList.add(this.catacombsF5);
        this.buttonList.add(this.catacombsF6);
        this.buttonList.add(this.catacombsF7);
        this.buttonList.add(this.goBack);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        String displayText;
        drawDefaultBackground();
        Minecraft mc = Minecraft.getMinecraft();
        if (DisplayCommand.auto) {
            displayText = "Current Display: auto";
        } else {
            displayText = "Current Display: " + DisplayCommand.display;
        }
        int displayWidth = mc.fontRendererObj.getStringWidth(displayText);
        new TextRenderer(mc, displayText, this.width / 2 - displayWidth / 2, 10, 1.0D);
        String catacombsTitleText = "Catacombs Dungeon";
        int catacombsTitleWidth = mc.fontRendererObj.getStringWidth(catacombsTitleText);
        new TextRenderer(mc, catacombsTitleText, this.width / 2 - catacombsTitleWidth / 2, (int)(this.height * 0.6D), 1.0D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.goBack) {
            DankersSkyblockMod.guiToOpen = "dankergui1";
        } else if (button == this.showSession) {
            this.addSession = !this.addSession;
            this.showSession.displayString = "Current Session Only: " + Utils.getColouredBoolean(this.addSession);
        } else if (button == this.off) {
            setDisplay("off", true);
        } else if (button == this.zombie) {
            setDisplay("zombie", false);
        } else if (button == this.spider) {
            setDisplay("spider", false);
        } else if (button == this.wolf) {
            setDisplay("wolf", false);
        } else if (button == this.auto) {
            DisplayCommand.auto = true;
            ConfigHandler.writeBooleanConfig("misc", "autoDisplay", true);
        } else if (button == this.fishing) {
            setDisplay("fishing", false);
        } else if (button == this.fishingWinter) {
            setDisplay("fishing_winter", false);
        } else if (button == this.fishingFestival) {
            setDisplay("fishing_festival", false);
        } else if (button == this.fishingSpooky) {
            setDisplay("fishing_spooky", false);
        } else if (button == this.mythological) {
            setDisplay("mythological", false);
        } else if (button == this.catacombsF1) {
            setDisplay("catacombs_floor_one", false);
        } else if (button == this.catacombsF2) {
            setDisplay("catacombs_floor_two", false);
        } else if (button == this.catacombsF3) {
            setDisplay("catacombs_floor_three", false);
        } else if (button == this.catacombsF4) {
            setDisplay("catacombs_floor_four", false);
        } else if (button == this.catacombsF5) {
            setDisplay("catacombs_floor_five", false);
        } else if (button == this.catacombsF6) {
            setDisplay("catacombs_floor_six", false);
        } else if (button == this.catacombsF7) {
            setDisplay("catacombs_floor_seven", false);
        }
    }

    public void setDisplay(String display, boolean forceNoSession) {
        if (!forceNoSession && this.addSession)
            display = display + "_session";
        DisplayCommand.auto = false;
        DisplayCommand.display = display;
        ConfigHandler.writeBooleanConfig("misc", "autoDisplay", false);
        ConfigHandler.writeStringConfig("misc", "display", display);
    }
}
