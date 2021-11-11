package me.Danker.commands;

import java.util.List;
import me.Danker.DankersSkyblockMod;
import me.Danker.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ScaleCommand extends CommandBase {
    public static double coordsScale;

    public static double displayScale;

    public static double dungeonTimerScale;

    public static double skill50Scale;

    public static double lividHpScale;

    public static double cakeTimerScale;

    public static double skillTrackerScale;

    public static double waterAnswerScale;

    public static double bonzoTimerScale;

    public String getCommandName() {
        return "scale";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer> <size (0.1 - 10)>";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, new String[] { "coords", "display", "dungeontimer", "skill50", "lividhp", "caketimer", "skilltracker", "wateranswer", "bonzotimer" });
        return null;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer)arg0;
        if (arg1.length < 2) {
            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        double scaleAmount = Math.floor(Double.parseDouble(arg1[1]) * 100.0D) / 100.0D;
        if (scaleAmount < 0.1D || scaleAmount > 10.0D) {
            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Scale multipler can only be between 0.1x and 10x."));
            return;
        }
        switch (arg1[0].toLowerCase()) {
            case "coords":
                coordsScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "coordsScale", coordsScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Coords have been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + coordsScale + "x"));
                return;
            case "display":
                displayScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "displayScale", displayScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + displayScale + "x"));
                return;
            case "dungeontimer":
                dungeonTimerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "dungeonTimerScale", dungeonTimerScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Dungeon timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + dungeonTimerScale + "x"));
                return;
            case "skill50":
                skill50Scale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "skill50Scale", skill50Scale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill 50 display has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + skill50Scale + "x"));
                return;
            case "lividhp":
                lividHpScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "lividHpScale", lividHpScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Livid HP has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + lividHpScale + "x"));
                return;
            case "caketimer":
                cakeTimerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "cakeTimerScale", cakeTimerScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Cake timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + cakeTimerScale + "x"));
                return;
            case "skilltracker":
                skillTrackerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "skillTrackerScale", skillTrackerScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + skillTrackerScale + "x"));
                return;
            case "wateranswer":
                waterAnswerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "waterAnswerScale", waterAnswerScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Water solver answer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + waterAnswerScale + "x"));
                return;
            case "bonzotimer":
                bonzoTimerScale = scaleAmount;
                ConfigHandler.writeDoubleConfig("scales", "bonzoTimerScale", bonzoTimerScale);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Bonzo's Mask timer has been scaled to " + DankersSkyblockMod.SECONDARY_COLOUR + bonzoTimerScale + "x"));
                return;
        }
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
    }
}
