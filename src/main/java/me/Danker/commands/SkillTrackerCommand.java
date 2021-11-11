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
import org.apache.commons.lang3.time.StopWatch;

public class SkillTrackerCommand extends CommandBase {
    public static boolean running = false;

    public String getCommandName() {
        return "skilltracker";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <start/stop/reset>";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, new String[] { "start", "resume", "pause", "stop", "reset", "hide", "show" });
        return null;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        (new Thread(() -> {
            EntityPlayer player = (EntityPlayer)arg0;
            if (arg1.length < 1) {
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
                return;
            }
            try {
                Thread.sleep(50L);
                switch (arg1[0].toLowerCase()) {
                    case "start":
                    case "resume":
                        if (DankersSkyblockMod.skillStopwatch.isStarted() && DankersSkyblockMod.skillStopwatch.isSuspended()) {
                            DankersSkyblockMod.skillStopwatch.resume();
                        } else if (!DankersSkyblockMod.skillStopwatch.isStarted()) {
                            DankersSkyblockMod.skillStopwatch.start();
                        }
                        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker started."));
                        return;
                    case "pause":
                    case "stop":
                        if (DankersSkyblockMod.skillStopwatch.isStarted() && !DankersSkyblockMod.skillStopwatch.isSuspended()) {
                            DankersSkyblockMod.skillStopwatch.suspend();
                        } else {
                            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker paused."));
                        }
                        return;
                    case "reset":
                        DankersSkyblockMod.skillStopwatch = new StopWatch();
                        DankersSkyblockMod.farmingXPGained = 0.0D;
                        DankersSkyblockMod.miningXPGained = 0.0D;
                        DankersSkyblockMod.combatXPGained = 0.0D;
                        DankersSkyblockMod.foragingXPGained = 0.0D;
                        DankersSkyblockMod.fishingXPGained = 0.0D;
                        DankersSkyblockMod.enchantingXPGained = 0.0D;
                        DankersSkyblockMod.alchemyXPGained = 0.0D;
                        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker reset."));
                        return;
                    case "hide":
                        DankersSkyblockMod.showSkillTracker = false;
                        ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", false);
                        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker hidden."));
                        return;
                    case "show":
                        DankersSkyblockMod.showSkillTracker = true;
                        ConfigHandler.writeBooleanConfig("misc", "showSkillTracker", true);
                        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Skill tracker shown."));
                        return;
                }
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }
}
