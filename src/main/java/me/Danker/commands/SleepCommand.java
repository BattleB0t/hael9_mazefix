package me.Danker.commands;

import me.Danker.DankersSkyblockMod;
import me.Danker.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class SleepCommand extends CommandBase implements ICommand {
    public static int waitAmount;

    public String getCommandName() {
        return "sleep";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <ms>";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer)arg0;
        if (arg1.length == 0) {
            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        waitAmount = Integer.parseInt(arg1[0]);
        ConfigHandler.writeIntConfig("macro", "terminal", waitAmount);
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Delay set to " + Integer.parseInt(arg1[0])));
    }
}
