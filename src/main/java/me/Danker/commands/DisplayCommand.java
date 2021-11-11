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

public class DisplayCommand extends CommandBase {
    public static String display;

    public static boolean auto;

    public String getCommandName() {
        return "display";
    }

    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName() + " <zombie/spider/wolf/fishing/catacombs/mythological/auto/off> [winter/festival/spooky/session/f(1-7)]";
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, new String[] { "wolf", "spider", "zombie", "fishing", "catacombs", "mythological", "auto", "off" });
        if (args.length == 2 && args[0].equalsIgnoreCase("fishing"))
            return getListOfStringsMatchingLastWord(args, new String[] { "winter", "festival", "spooky", "session" });
        if (args.length == 2 && args[0].equalsIgnoreCase("catacombs"))
            return getListOfStringsMatchingLastWord(args, new String[] {
                    "f1", "floor1", "f2", "floor2", "f3", "floor3", "f4", "floor4", "f5", "floor5",
                    "f6", "floor6", "f7", "floor7" });
        if (args.length > 1)
            return getListOfStringsMatchingLastWord(args, new String[] { "session" });
        return null;
    }

    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        EntityPlayer player = (EntityPlayer)arg0;
        if (arg1.length == 0) {
            player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
            return;
        }
        boolean showSession = false;
        if (arg1[arg1.length - 1].equalsIgnoreCase("session"))
            showSession = true;
        switch (arg1[0].toLowerCase()) {
            case "wolf":
                if (showSession) {
                    display = "wolf_session";
                    break;
                }
                display = "wolf";
                break;
            case "spider":
                if (showSession) {
                    display = "spider_session";
                    break;
                }
                display = "spider";
                break;
            case "zombie":
                if (showSession) {
                    display = "zombie_session";
                    break;
                }
                display = "zombie";
                break;
            case "fishing":
                if (arg1.length > 1) {
                    switch (arg1[1].toLowerCase()) {
                        case "winter":
                            if (showSession) {
                                display = "fishing_winter_session";
                                break;
                            }
                            display = "fishing_winter";
                            break;
                        case "festival":
                            if (showSession) {
                                display = "fishing_festival_session";
                                break;
                            }
                            display = "fishing_festival";
                            break;
                        case "spooky":
                            if (showSession) {
                                display = "fishing_spooky_session";
                                break;
                            }
                            display = "fishing_spooky";
                            break;
                    }
                    if (showSession) {
                        display = "fishing_session";
                        break;
                    }
                    display = "fishing";
                    break;
                }
                if (showSession) {
                    display = "fishing_session";
                    break;
                }
                display = "fishing";
                break;
            case "mythological":
                if (showSession) {
                    display = "mythological_session";
                    break;
                }
                display = "mythological";
                break;
            case "catacombs":
                if (arg1.length == 1) {
                    player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /display catacombs <f1/f2/f3/f4f5/f6/f7>"));
                    return;
                }
                switch (arg1[1].toLowerCase()) {
                    case "f1":
                    case "floor1":
                        if (showSession) {
                            display = "catacombs_floor_one_session";
                            break;
                        }
                        display = "catacombs_floor_one";
                        break;
                    case "f2":
                    case "floor2":
                        if (showSession) {
                            display = "catacombs_floor_two_session";
                            break;
                        }
                        display = "catacombs_floor_two";
                        break;
                    case "f3":
                    case "floor3":
                        if (showSession) {
                            display = "catacombs_floor_three_session";
                            break;
                        }
                        display = "catacombs_floor_three";
                        break;
                    case "f4":
                    case "floor4":
                        if (showSession) {
                            display = "catacombs_floor_four_session";
                            break;
                        }
                        display = "catacombs_floor_four";
                        break;
                    case "f5":
                    case "floor5":
                        if (showSession) {
                            display = "catacombs_floor_five_session";
                            break;
                        }
                        display = "catacombs_floor_five";
                        break;
                    case "f6":
                    case "floor6":
                        if (showSession) {
                            display = "catacombs_floor_six_session";
                            break;
                        }
                        display = "catacombs_floor_six";
                        break;
                    case "f7":
                    case "floor7":
                        if (showSession) {
                            display = "catacombs_floor_seven_session";
                            break;
                        }
                        display = "catacombs_floor_seven";
                        break;
                }
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: /display catacombs <f1/f2/f3/f4/f5/f6/f7>"));
                return;
            case "auto":
                auto = true;
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display set to " + DankersSkyblockMod.SECONDARY_COLOUR + "auto" + DankersSkyblockMod.MAIN_COLOUR + "."));
                ConfigHandler.writeBooleanConfig("misc", "autoDisplay", true);
                return;
            case "off":
                display = "off";
                break;
            default:
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.ERROR_COLOUR + "Usage: " + getCommandUsage(arg0)));
                return;
        }
        auto = false;
        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.MAIN_COLOUR + "Display set to " + DankersSkyblockMod.SECONDARY_COLOUR + display + DankersSkyblockMod.MAIN_COLOUR + "."));
        ConfigHandler.writeBooleanConfig("misc", "autoDisplay", false);
        ConfigHandler.writeStringConfig("misc", "display", display);
    }
}
