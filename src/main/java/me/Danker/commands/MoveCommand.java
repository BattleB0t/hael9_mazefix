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

public class MoveCommand extends CommandBase {
    public static int[] coordsXY = new int[] { 0, 0 };

    public static int[] displayXY = new int[] { 0, 0 };

    public static int[] dungeonTimerXY = new int[] { 0, 0 };

    public static int[] skill50XY = new int[] { 0, 0 };

    public static int[] lividHpXY = new int[] { 0, 0 };

    public static int[] cakeTimerXY = new int[] { 0, 0 };

    public static int[] skillTrackerXY = new int[] { 0, 0 };

    public static int[] waterAnswerXY = new int[] { 0, 0 };

    public static int[] bonzoTimerXY = new int[] { 0, 0 };

    public String getCommandName() {
        return "move";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <coords/display/dungeontimer/skill50/lividhp/caketimer/skilltracker/wateranswer/bonzotimer> <x> <y>";
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
        switch (arg1[0].toLowerCase()) {
            case "coords":
                coordsXY[0] = Integer.parseInt(arg1[1]);
                coordsXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "coordsX", coordsXY[0]);
                ConfigHandler.writeIntConfig("locations", "coordsY", coordsXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Coords have been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "display":
                displayXY[0] = Integer.parseInt(arg1[1]);
                displayXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "displayX", displayXY[0]);
                ConfigHandler.writeIntConfig("locations", "displayY", displayXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Tracker display has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "dungeontimer":
                dungeonTimerXY[0] = Integer.parseInt(arg1[1]);
                dungeonTimerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "dungeonTimerX", dungeonTimerXY[0]);
                ConfigHandler.writeIntConfig("locations", "dungeonTimerY", dungeonTimerXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Dungeon timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "skill50":
                skill50XY[0] = Integer.parseInt(arg1[1]);
                skill50XY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "skill50X", skill50XY[0]);
                ConfigHandler.writeIntConfig("locations", "skill50Y", skill50XY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill 50 display has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "lividhp":
                lividHpXY[0] = Integer.parseInt(arg1[1]);
                lividHpXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "lividHpX", lividHpXY[0]);
                ConfigHandler.writeIntConfig("locations", "lividHpY", lividHpXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Livid HP has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "caketimer":
                cakeTimerXY[0] = Integer.parseInt(arg1[1]);
                cakeTimerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "cakeTimerX", cakeTimerXY[0]);
                ConfigHandler.writeIntConfig("locations", "cakeTimerY", cakeTimerXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Cake timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "skilltracker":
                skillTrackerXY[0] = Integer.parseInt(arg1[1]);
                skillTrackerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "skillTrackerX", skillTrackerXY[0]);
                ConfigHandler.writeIntConfig("locations", "skillTrackerY", skillTrackerXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "wateranswer":
                waterAnswerXY[0] = Integer.parseInt(arg1[1]);
                waterAnswerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "waterAnswerX", waterAnswerXY[0]);
                ConfigHandler.writeIntConfig("locations", "waterAnswerY", waterAnswerXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Water solver answer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
            case "bonzotimer":
                bonzoTimerXY[0] = Integer.parseInt(arg1[1]);
                bonzoTimerXY[1] = Integer.parseInt(arg1[2]);
                ConfigHandler.writeIntConfig("locations", "bonzoTimerX", bonzoTimerXY[0]);
                ConfigHandler.writeIntConfig("locations", "bonzoTimerX", bonzoTimerXY[1]);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Bonzo's Mask timer has been moved to " + DankersSkyblockMod.SECONDARY_COLOUR + arg1[1] + ", " + arg1[2]));
                return;
        }
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
    }
}
