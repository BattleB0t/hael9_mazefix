package me.Danker.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import me.Danker.DankersSkyblockMod;
import me.Danker.commands.ToggleCommand;
import me.Danker.handlers.ConfigHandler;
import me.Danker.handlers.TextRenderer;
import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class DankerGui extends GuiScreen {
    private int page;

    private GuiButton closeGUI;

    private GuiButton backPage;

    private GuiButton nextPage;

    private GuiButton githubLink;

    private GuiButton discordLink;

    private GuiButton editLocations;

    private GuiButton changeDisplay;

    private GuiButton onlySlayer;

    private GuiButton puzzleSolvers;

    private GuiButton experimentationTableSolvers;

    private GuiButton skillTracker;

    private GuiButton gparty;

    private GuiButton coords;

    private GuiButton goldenEnch;

    private GuiButton slayerCount;

    private GuiButton rngesusAlert;

    private GuiButton splitFishing;

    private GuiButton chatMaddox;

    private GuiButton spiritBearAlert;

    private GuiButton aotd;

    private GuiButton petColours;

    private GuiButton golemAlerts;

    private GuiButton expertiseLore;

    private GuiButton skill50Display;

    private GuiButton outlineText;

    private GuiButton cakeTimer;

    private GuiButton lividDagger;

    private GuiButton sceptreMessages;

    private GuiButton midasStaffMessages;

    private GuiButton implosionMessages;

    private GuiButton healMessages;

    private GuiButton cooldownMessages;

    private GuiButton manaMessages;

    private GuiButton dungeonTimer;

    private GuiButton lowHealthNotify;

    private GuiButton lividSolver;

    private GuiButton stopSalvageStarred;

    private GuiButton watcherReadyMessage;

    private GuiButton flowerWeapons;

    private GuiButton pickBlock;

    private GuiButton notifySlayerSlain;

    private GuiButton necronNotifications;

    private GuiButton bonzoTimer;

    private GuiButton blockBreakingFarms;

    private GuiButton autoSkillTracker;

    public DankerGui(int page) {
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
        this.closeGUI = new GuiButton(0, width / 2 - 100, (int)(height * 0.9D), "Close");
        this.backPage = new GuiButton(0, width / 2 - 100, (int)(height * 0.8D), 80, 20, "< Back");
        this.nextPage = new GuiButton(0, width / 2 + 20, (int)(height * 0.8D), 80, 20, "Next >");
        this.githubLink = new GuiButton(0, 2, height - 50, 80, 20, "GitHub");
        this.discordLink = new GuiButton(0, 2, height - 30, 80, 20, "Discord");
        this.editLocations = new GuiButton(0, 2, 5, 100, 20, "Edit Locations");
        this.changeDisplay = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Change Display Settings");
        this.onlySlayer = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Set Slayer Quest");
        this.puzzleSolvers = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Toggle Dungeons Puzzle Solvers");
        this.experimentationTableSolvers = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Toggle Experimentation Table Solvers");
        this.skillTracker = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Toggle Skill XP/Hour Tracking");
        this.outlineText = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled));
        this.pickBlock = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Auto-Swap to Pick Block: " + Utils.getColouredBoolean(ToggleCommand.swapToPickBlockToggled));
        this.coords = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled));
        this.chatMaddox = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled));
        this.cakeTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Cake Timer: " + Utils.getColouredBoolean(ToggleCommand.cakeTimerToggled));
        this.skill50Display = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Display Progress To Skill Level 50: " + Utils.getColouredBoolean(ToggleCommand.skill50DisplayToggled));
        this.slayerCount = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Count Total 20% Drops: " + Utils.getColouredBoolean(ToggleCommand.slayerCountTotal));
        this.aotd = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Disable AOTD Ability: " + Utils.getColouredBoolean(ToggleCommand.aotdToggled));
        this.lividDagger = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Disable Livid Dagger Ability: " + Utils.getColouredBoolean(ToggleCommand.lividDaggerToggled));
        this.spiritBearAlert = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Spirit Bear Spawn Alerts: " + Utils.getColouredBoolean(ToggleCommand.spiritBearAlerts));
        this.sceptreMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Spirit Sceptre Messages: " + Utils.getColouredBoolean(ToggleCommand.sceptreMessages));
        this.midasStaffMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Midas Staff Messages: " + Utils.getColouredBoolean(ToggleCommand.midasStaffMessages));
        this.implosionMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Implosion Messages: " + Utils.getColouredBoolean(ToggleCommand.implosionMessages));
        this.healMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Heal Messages: " + Utils.getColouredBoolean(ToggleCommand.healMessages));
        this.cooldownMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Cooldown Messages: " + Utils.getColouredBoolean(ToggleCommand.cooldownMessages));
        this.manaMessages = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Mana Messages: " + Utils.getColouredBoolean(ToggleCommand.manaMessages));
        this.goldenEnch = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Golden T10/T6/T4 Enchantments: " + Utils.getColouredBoolean(ToggleCommand.goldenToggled));
        this.petColours = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Colour Pet Backgrounds: " + Utils.getColouredBoolean(ToggleCommand.petColoursToggled));
        this.expertiseLore = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Expertise Kills In Lore: " + Utils.getColouredBoolean(ToggleCommand.expertiseLoreToggled));
        this.gparty = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Guild Party Notifications: " + Utils.getColouredBoolean(ToggleCommand.gpartyToggled));
        this.golemAlerts = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Alert When Golem Spawns: " + Utils.getColouredBoolean(ToggleCommand.golemAlertToggled));
        this.rngesusAlert = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts));
        this.splitFishing = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Split Fishing Display: " + Utils.getColouredBoolean(ToggleCommand.splitFishing));
        this.lowHealthNotify = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Low Health Notifications: " + Utils.getColouredBoolean(ToggleCommand.lowHealthNotifyToggled));
        this.lividSolver = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Find Correct Livid: " + Utils.getColouredBoolean(ToggleCommand.lividSolverToggled));
        this.dungeonTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Display Dungeon Timers: " + Utils.getColouredBoolean(ToggleCommand.dungeonTimerToggled));
        this.stopSalvageStarred = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Stop Salvaging Starred Items: " + Utils.getColouredBoolean(ToggleCommand.stopSalvageStarredToggled));
        this.watcherReadyMessage = new GuiButton(0, width / 2 - 100, (int)(height * 0.5D), "Display Watcher Ready Message: " + Utils.getColouredBoolean(ToggleCommand.watcherReadyToggled));
        this.flowerWeapons = new GuiButton(0, width / 2 - 100, (int)(height * 0.6D), "Prevent Placing FoT/Spirit Sceptre: " + Utils.getColouredBoolean(ToggleCommand.flowerWeaponsToggled));
        this.notifySlayerSlain = new GuiButton(0, width / 2 - 100, (int)(height * 0.7D), "Notify when Slayer Slain: " + Utils.getColouredBoolean(ToggleCommand.notifySlayerSlainToggled));
        this.necronNotifications = new GuiButton(0, width / 2 - 100, (int)(height * 0.1D), "Necron Phase Notifications: " + Utils.getColouredBoolean(ToggleCommand.necronNotificationsToggled));
        this.bonzoTimer = new GuiButton(0, width / 2 - 100, (int)(height * 0.2D), "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.bonzoTimerToggled));
        this.blockBreakingFarms = new GuiButton(0, width / 2 - 100, (int)(height * 0.3D), "Prevent Breaking Farms: " + Utils.getColouredBoolean(ToggleCommand.blockBreakingFarmsToggled));
        this.autoSkillTracker = new GuiButton(0, width / 2 - 100, (int)(height * 0.4D), "Auto Start/Stop Skill Tracker: " + Utils.getColouredBoolean(ToggleCommand.autoSkillTrackerToggled));
        switch (this.page) {
            case 1:
                this.buttonList.add(this.changeDisplay);
                this.buttonList.add(this.onlySlayer);
                this.buttonList.add(this.puzzleSolvers);
                this.buttonList.add(this.experimentationTableSolvers);
                this.buttonList.add(this.skillTracker);
                this.buttonList.add(this.outlineText);
                this.buttonList.add(this.pickBlock);
                this.buttonList.add(this.nextPage);
                break;
            case 2:
                this.buttonList.add(this.coords);
                this.buttonList.add(this.chatMaddox);
                this.buttonList.add(this.cakeTimer);
                this.buttonList.add(this.skill50Display);
                this.buttonList.add(this.slayerCount);
                this.buttonList.add(this.aotd);
                this.buttonList.add(this.lividDagger);
                this.buttonList.add(this.nextPage);
                this.buttonList.add(this.backPage);
                break;
            case 3:
                this.buttonList.add(this.spiritBearAlert);
                this.buttonList.add(this.sceptreMessages);
                this.buttonList.add(this.midasStaffMessages);
                this.buttonList.add(this.implosionMessages);
                this.buttonList.add(this.healMessages);
                this.buttonList.add(this.cooldownMessages);
                this.buttonList.add(this.manaMessages);
                this.buttonList.add(this.nextPage);
                this.buttonList.add(this.backPage);
                break;
            case 4:
                this.buttonList.add(this.goldenEnch);
                this.buttonList.add(this.petColours);
                this.buttonList.add(this.expertiseLore);
                this.buttonList.add(this.gparty);
                this.buttonList.add(this.golemAlerts);
                this.buttonList.add(this.rngesusAlert);
                this.buttonList.add(this.splitFishing);
                this.buttonList.add(this.nextPage);
                this.buttonList.add(this.backPage);
                break;
            case 5:
                this.buttonList.add(this.lowHealthNotify);
                this.buttonList.add(this.lividSolver);
                this.buttonList.add(this.dungeonTimer);
                this.buttonList.add(this.stopSalvageStarred);
                this.buttonList.add(this.watcherReadyMessage);
                this.buttonList.add(this.flowerWeapons);
                this.buttonList.add(this.notifySlayerSlain);
                this.buttonList.add(this.nextPage);
                this.buttonList.add(this.backPage);
                break;
            case 6:
                this.buttonList.add(this.necronNotifications);
                this.buttonList.add(this.bonzoTimer);
                this.buttonList.add(this.blockBreakingFarms);
                this.buttonList.add(this.autoSkillTracker);
                this.buttonList.add(this.backPage);
                break;
        }
        this.buttonList.add(this.githubLink);
        this.buttonList.add(this.discordLink);
        this.buttonList.add(this.closeGUI);
        this.buttonList.add(this.editLocations);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        String pageText = "Page: " + this.page + "/6";
        int pageWidth = this.mc.fontRendererObj.getStringWidth(pageText);
        new TextRenderer(this.mc, pageText, this.width / 2 - pageWidth / 2, 10, 1.0D);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void actionPerformed(GuiButton button) {
        if (button == this.closeGUI) {
            (Minecraft.getMinecraft()).thePlayer.closeScreen();
        } else if (button == this.nextPage) {
            DankersSkyblockMod.guiToOpen = "dankergui" + (this.page + 1);
        } else if (button == this.backPage) {
            DankersSkyblockMod.guiToOpen = "dankergui" + (this.page - 1);
        } else if (button == this.editLocations) {
            DankersSkyblockMod.guiToOpen = "editlocations";
        } else if (button == this.githubLink) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/bowser0000/SkyblockMod"));
            } catch (IOException|java.net.URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else if (button == this.discordLink) {
            try {
                Desktop.getDesktop().browse(new URI("https://discord.gg/QsEkNQS"));
            } catch (IOException|java.net.URISyntaxException ex) {
                ex.printStackTrace();
            }
        } else if (button == this.changeDisplay) {
            DankersSkyblockMod.guiToOpen = "displaygui";
        } else if (button == this.onlySlayer) {
            DankersSkyblockMod.guiToOpen = "onlyslayergui";
        } else if (button == this.puzzleSolvers) {
            DankersSkyblockMod.guiToOpen = "puzzlesolvers";
        } else if (button == this.experimentationTableSolvers) {
            DankersSkyblockMod.guiToOpen = "experimentsolvers";
        } else if (button == this.skillTracker) {
            DankersSkyblockMod.guiToOpen = "skilltracker";
        } else if (button == this.outlineText) {
            ToggleCommand.outlineTextToggled = !ToggleCommand.outlineTextToggled;
            ConfigHandler.writeBooleanConfig("toggles", "OutlineText", ToggleCommand.outlineTextToggled);
            this.outlineText.displayString = "Outline Displayed Text: " + Utils.getColouredBoolean(ToggleCommand.outlineTextToggled);
        } else if (button == this.gparty) {
            ToggleCommand.gpartyToggled = !ToggleCommand.gpartyToggled;
            ConfigHandler.writeBooleanConfig("toggles", "GParty", ToggleCommand.gpartyToggled);
            this.gparty.displayString = "Guild Party Notifications: " + Utils.getColouredBoolean(ToggleCommand.gpartyToggled);
        } else if (button == this.coords) {
            ToggleCommand.coordsToggled = !ToggleCommand.coordsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Coords", ToggleCommand.coordsToggled);
            this.coords.displayString = "Coordinate/Angle Display: " + Utils.getColouredBoolean(ToggleCommand.coordsToggled);
        } else if (button == this.goldenEnch) {
            ToggleCommand.goldenToggled = !ToggleCommand.goldenToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Golden", ToggleCommand.goldenToggled);
            this.goldenEnch.displayString = "Golden T10/T6/T4 Enchantments: " + Utils.getColouredBoolean(ToggleCommand.goldenToggled);
        } else if (button == this.slayerCount) {
            ToggleCommand.slayerCountTotal = !ToggleCommand.slayerCountTotal;
            ConfigHandler.writeBooleanConfig("toggles", "SlayerCount", ToggleCommand.slayerCountTotal);
            this.slayerCount.displayString = "Count Total 20% Drops: " + Utils.getColouredBoolean(ToggleCommand.slayerCountTotal);
        } else if (button == this.aotd) {
            ToggleCommand.aotdToggled = !ToggleCommand.aotdToggled;
            ConfigHandler.writeBooleanConfig("toggles", "AOTD", ToggleCommand.aotdToggled);
            this.aotd.displayString = "Disable AOTD Ability: " + Utils.getColouredBoolean(ToggleCommand.aotdToggled);
        } else if (button == this.lividDagger) {
            ToggleCommand.lividDaggerToggled = !ToggleCommand.lividDaggerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "LividDagger", ToggleCommand.lividDaggerToggled);
            this.lividDagger.displayString = "Disable Livid Dagger Ability: " + Utils.getColouredBoolean(ToggleCommand.lividDaggerToggled);
        } else if (button == this.sceptreMessages) {
            ToggleCommand.sceptreMessages = !ToggleCommand.sceptreMessages;
            ConfigHandler.writeBooleanConfig("toggles", "SceptreMessages", ToggleCommand.sceptreMessages);
            this.sceptreMessages.displayString = "Spirit Sceptre Messages: " + Utils.getColouredBoolean(ToggleCommand.sceptreMessages);
        } else if (button == this.petColours) {
            ToggleCommand.petColoursToggled = !ToggleCommand.petColoursToggled;
            ConfigHandler.writeBooleanConfig("toggles", "PetColors", ToggleCommand.petColoursToggled);
            this.petColours.displayString = "Colour Pet Backgrounds: " + Utils.getColouredBoolean(ToggleCommand.petColoursToggled);
        } else if (button == this.dungeonTimer) {
            ToggleCommand.dungeonTimerToggled = !ToggleCommand.dungeonTimerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "DungeonTimer", ToggleCommand.dungeonTimerToggled);
            this.dungeonTimer.displayString = "Display Dungeon Timers: " + Utils.getColouredBoolean(ToggleCommand.dungeonTimerToggled);
        } else if (button == this.golemAlerts) {
            ToggleCommand.golemAlertToggled = !ToggleCommand.golemAlertToggled;
            ConfigHandler.writeBooleanConfig("toggles", "GolemAlerts", ToggleCommand.golemAlertToggled);
            this.golemAlerts.displayString = "Alert When Golem Spawns: " + Utils.getColouredBoolean(ToggleCommand.golemAlertToggled);
        } else if (button == this.expertiseLore) {
            ToggleCommand.expertiseLoreToggled = !ToggleCommand.expertiseLoreToggled;
            ConfigHandler.writeBooleanConfig("toggles", "ExpertiseLore", ToggleCommand.expertiseLoreToggled);
            this.expertiseLore.displayString = "Expertise Kills In Lore: " + Utils.getColouredBoolean(ToggleCommand.expertiseLoreToggled);
        } else if (button == this.skill50Display) {
            ToggleCommand.skill50DisplayToggled = !ToggleCommand.skill50DisplayToggled;
            ConfigHandler.writeBooleanConfig("toggles", "Skill50Display", ToggleCommand.skill50DisplayToggled);
            this.skill50Display.displayString = "Display Progress To Skill Level 50: " + Utils.getColouredBoolean(ToggleCommand.skill50DisplayToggled);
        } else if (button == this.splitFishing) {
            ToggleCommand.splitFishing = !ToggleCommand.splitFishing;
            ConfigHandler.writeBooleanConfig("toggles", "SplitFishing", ToggleCommand.splitFishing);
            this.splitFishing.displayString = "Split Fishing Display: " + Utils.getColouredBoolean(ToggleCommand.splitFishing);
        } else if (button == this.chatMaddox) {
            ToggleCommand.chatMaddoxToggled = !ToggleCommand.chatMaddoxToggled;
            ConfigHandler.writeBooleanConfig("toggles", "ChatMaddox", ToggleCommand.chatMaddoxToggled);
            this.chatMaddox.displayString = "Click On-Screen to Open Maddox: " + Utils.getColouredBoolean(ToggleCommand.chatMaddoxToggled);
        } else if (button == this.spiritBearAlert) {
            ToggleCommand.spiritBearAlerts = !ToggleCommand.spiritBearAlerts;
            ConfigHandler.writeBooleanConfig("toggles", "SpiritBearAlerts", ToggleCommand.spiritBearAlerts);
            this.spiritBearAlert.displayString = "Spirit Bear Spawn Alerts: " + Utils.getColouredBoolean(ToggleCommand.spiritBearAlerts);
        } else if (button == this.midasStaffMessages) {
            ToggleCommand.midasStaffMessages = !ToggleCommand.midasStaffMessages;
            ConfigHandler.writeBooleanConfig("toggles", "MidasStaffMessages", ToggleCommand.midasStaffMessages);
            this.midasStaffMessages.displayString = "Midas Staff Messages: " + Utils.getColouredBoolean(ToggleCommand.midasStaffMessages);
        } else if (button == this.lividSolver) {
            ToggleCommand.lividSolverToggled = !ToggleCommand.lividSolverToggled;
            ConfigHandler.writeBooleanConfig("toggles", "LividSolver", ToggleCommand.lividSolverToggled);
            this.lividSolver.displayString = "Find Correct Livid: " + Utils.getColouredBoolean(ToggleCommand.lividSolverToggled);
        } else if (button == this.rngesusAlert) {
            ToggleCommand.rngesusAlerts = !ToggleCommand.rngesusAlerts;
            ConfigHandler.writeBooleanConfig("toggles", "RNGesusAlerts", ToggleCommand.rngesusAlerts);
            this.rngesusAlert.displayString = "RNGesus Alerts: " + Utils.getColouredBoolean(ToggleCommand.rngesusAlerts);
        } else if (button == this.cakeTimer) {
            ToggleCommand.cakeTimerToggled = !ToggleCommand.cakeTimerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "CakeTimer", ToggleCommand.cakeTimerToggled);
            this.cakeTimer.displayString = "Cake Timer: " + Utils.getColouredBoolean(ToggleCommand.cakeTimerToggled);
        } else if (button == this.healMessages) {
            ToggleCommand.healMessages = !ToggleCommand.healMessages;
            ConfigHandler.writeBooleanConfig("toggles", "HealMessages", ToggleCommand.healMessages);
            this.healMessages.displayString = "Heal Messages: " + Utils.getColouredBoolean(ToggleCommand.healMessages);
        } else if (button == this.cooldownMessages) {
            ToggleCommand.cooldownMessages = !ToggleCommand.cooldownMessages;
            ConfigHandler.writeBooleanConfig("toggles", "CooldownMessages", ToggleCommand.cooldownMessages);
            this.cooldownMessages.displayString = "Cooldown Messages: " + Utils.getColouredBoolean(ToggleCommand.cooldownMessages);
        } else if (button == this.manaMessages) {
            ToggleCommand.manaMessages = !ToggleCommand.manaMessages;
            ConfigHandler.writeBooleanConfig("toggles", "ManaMessages", ToggleCommand.manaMessages);
            this.manaMessages.displayString = "Mana Messages: " + Utils.getColouredBoolean(ToggleCommand.manaMessages);
        } else if (button == this.lowHealthNotify) {
            ToggleCommand.lowHealthNotifyToggled = !ToggleCommand.lowHealthNotifyToggled;
            ConfigHandler.writeBooleanConfig("toggles", "LowHealthNotify", ToggleCommand.lowHealthNotifyToggled);
            this.lowHealthNotify.displayString = "Low Health Notifications: " + Utils.getColouredBoolean(ToggleCommand.lowHealthNotifyToggled);
        } else if (button == this.implosionMessages) {
            ToggleCommand.implosionMessages = !ToggleCommand.implosionMessages;
            ConfigHandler.writeBooleanConfig("toggles", "ImplosionMessages", ToggleCommand.implosionMessages);
            this.implosionMessages.displayString = "Implosion Messages: " + Utils.getColouredBoolean(ToggleCommand.implosionMessages);
        } else if (button == this.stopSalvageStarred) {
            ToggleCommand.stopSalvageStarredToggled = !ToggleCommand.stopSalvageStarredToggled;
            ConfigHandler.writeBooleanConfig("toggles", "StopSalvageStarred", ToggleCommand.stopSalvageStarredToggled);
            this.stopSalvageStarred.displayString = "Stop Salvaging Starred Items: " + Utils.getColouredBoolean(ToggleCommand.stopSalvageStarredToggled);
        } else if (button == this.watcherReadyMessage) {
            ToggleCommand.watcherReadyToggled = !ToggleCommand.watcherReadyToggled;
            ConfigHandler.writeBooleanConfig("toggles", "WatcherReadyMessage", ToggleCommand.watcherReadyToggled);
            this.watcherReadyMessage.displayString = "Display Watcher Ready Message: " + Utils.getColouredBoolean(ToggleCommand.watcherReadyToggled);
        } else if (button == this.notifySlayerSlain) {
            ToggleCommand.notifySlayerSlainToggled = !ToggleCommand.notifySlayerSlainToggled;
            ConfigHandler.writeBooleanConfig("toggles", "NotifySlayerSlain", ToggleCommand.notifySlayerSlainToggled);
            this.notifySlayerSlain.displayString = "Notify when Slayer Slain: " + Utils.getColouredBoolean(ToggleCommand.notifySlayerSlainToggled);
        } else if (button == this.necronNotifications) {
            ToggleCommand.necronNotificationsToggled = !ToggleCommand.necronNotificationsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "NecronNotifications", ToggleCommand.necronNotificationsToggled);
            this.necronNotifications.displayString = "Necron Phase Notifications: " + Utils.getColouredBoolean(ToggleCommand.necronNotificationsToggled);
        } else if (button == this.bonzoTimer) {
            ToggleCommand.bonzoTimerToggled = !ToggleCommand.bonzoTimerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "BonzoTimer", ToggleCommand.bonzoTimerToggled);
            this.bonzoTimer.displayString = "Bonzo's Mask Timer: " + Utils.getColouredBoolean(ToggleCommand.bonzoTimerToggled);
        } else if (button == this.blockBreakingFarms) {
            ToggleCommand.blockBreakingFarmsToggled = !ToggleCommand.blockBreakingFarmsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "BlockBreakingFarms", ToggleCommand.blockBreakingFarmsToggled);
            this.blockBreakingFarms.displayString = "Prevent Breaking Farms: " + Utils.getColouredBoolean(ToggleCommand.blockBreakingFarmsToggled);
        } else if (button == this.pickBlock) {
            ToggleCommand.swapToPickBlockToggled = !ToggleCommand.swapToPickBlockToggled;
            ConfigHandler.writeBooleanConfig("toggles", "PickBlock", ToggleCommand.swapToPickBlockToggled);
            this.pickBlock.displayString = "Auto-Swap to Pick Block: " + Utils.getColouredBoolean(ToggleCommand.swapToPickBlockToggled);
        } else if (button == this.flowerWeapons) {
            ToggleCommand.flowerWeaponsToggled = !ToggleCommand.flowerWeaponsToggled;
            ConfigHandler.writeBooleanConfig("toggles", "FlowerWeapons", ToggleCommand.flowerWeaponsToggled);
            this.flowerWeapons.displayString = "Prevent Placing FoT/Spirit Sceptre: " + Utils.getColouredBoolean(ToggleCommand.flowerWeaponsToggled);
        } else if (button == this.autoSkillTracker) {
            ToggleCommand.autoSkillTrackerToggled = !ToggleCommand.autoSkillTrackerToggled;
            ConfigHandler.writeBooleanConfig("toggles", "AutoSkillTracker", ToggleCommand.autoSkillTrackerToggled);
            this.autoSkillTracker.displayString = "Auto Start/Stop Skill Tracker: " + Utils.getColouredBoolean(ToggleCommand.autoSkillTrackerToggled);
        }
    }
}
