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

public class BlockSlayerCommand extends CommandBase {
    public static String onlySlayerName = "";

    public static String onlySlayerNumber = "";

    public String getCommandName() {
        return "onlyslayer";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <zombie/spider/wolf> <1/2/3/4>";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, new String[] { "zombie", "spider", "wolf" });
        if (args.length == 2)
            return getListOfStringsMatchingLastWord(args, new String[] { "1", "2", "3", "4" });
        return null;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer)arg0;
        if (arg1.length == 0 || (arg1.length == 1 && !arg1[0].equalsIgnoreCase("off"))) {
            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        switch (arg1[0].toLowerCase()) {
            case "zombie":
                onlySlayerName = "Revenant Horror";
                break;
            case "spider":
                onlySlayerName = "Tarantula Broodfather";
                break;
            case "wolf":
                onlySlayerName = "Sven Packmaster";
                break;
            case "off":
                onlySlayerName = "";
                onlySlayerNumber = "";
                ConfigHandler.writeStringConfig("toggles", "BlockSlayer", "");
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Slayer blocking turned off."));
                return;
            default:
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
                return;
        }
        int slayerNumber = Integer.parseInt(arg1[1]);
        switch (slayerNumber) {
            case 1:
                onlySlayerNumber = "I";
                break;
            case 2:
                onlySlayerNumber = "II";
                break;
            case 3:
                onlySlayerNumber = "III";
                break;
            case 4:
                onlySlayerNumber = "IV";
                break;
            default:
                onlySlayerName = "";
                onlySlayerNumber = "";
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
                return;
        }
        ConfigHandler.writeStringConfig("toggles", "BlockSlayer", onlySlayerName + " " + onlySlayerNumber);
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Slayer blocking set to " + DankersSkyblockMod.SECONDARY_COLOUR + onlySlayerName + " " + onlySlayerNumber));
    }
}
