package me.Danker.commands;

import me.Danker.DankersSkyblockMod;
import me.Danker.handlers.ConfigHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class ReloadConfigCommand extends CommandBase {
    public String getCommandName() {
        return "reloadconfig";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer)arg0;
        ConfigHandler.reloadConfig();
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Reloaded config."));
    }
}
