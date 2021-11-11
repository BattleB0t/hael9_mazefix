package me.Danker.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.Danker.DankersSkyblockMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class RepartyCommand extends CommandBase implements ICommand {
    public static boolean gettingParty = false;

    public static int Delimiter = 0;

    public static boolean disbanding = false;

    public static boolean inviting = false;

    public static boolean failInviting = false;

    public static List<String> party = new ArrayList<>();

    public static List<String> repartyFailList = new ArrayList<>();

    public static Thread partyThread = null;

    public String getCommandName() {
        return "reparty";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    public List<String> getCommandAliases() {
        return Collections.singletonList("rp");
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0 && (args[0].startsWith("fail") || args[0].equals("f"))) {
            partyThread = new Thread(() -> {
                EntityPlayerSP player = (Minecraft.getMinecraft()).thePlayer;
                GuiNewChat chat = (Minecraft.getMinecraft()).ingameGUI.getChatGUI();
                try {
                    player.sendChatMessage("/p " + String.join(" ", (Iterable)repartyFailList));
                    String members = String.join(EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW, (Iterable)repartyFailList);
                    player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Partying:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW + members + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
                    failInviting = true;
                    while (failInviting)
                        Thread.sleep(10L);
                    if (repartyFailList.size() > 0) {
                        String repartyFails = String.join("\n- " + EnumChatFormatting.RED, (Iterable)repartyFailList);
                        player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Failed to invite:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.RED + repartyFails + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            partyThread.start();
            return;
        }
        party.clear();
        repartyFailList.clear();
        partyThread = new Thread(() -> {
            EntityPlayerSP player = (Minecraft.getMinecraft()).thePlayer;
            try {
                player.sendChatMessage("/pl");
                gettingParty = true;
                while (gettingParty)
                    Thread.sleep(10L);
                if (party.size() == 0)
                    return;
                player.sendChatMessage("/p disband");
                disbanding = true;
                while (disbanding)
                    Thread.sleep(10L);
                String members = String.join(EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW, (Iterable)party);
                player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Repartying:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.YELLOW + members + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
                repartyFailList = new ArrayList<>(party);
                for (String invitee : party) {
                    player.sendChatMessage("/p " + invitee);
                    inviting = true;
                    while (inviting)
                        Thread.sleep(10L);
                    Thread.sleep(100L);
                }
                while (inviting)
                    Thread.sleep(10L);
                if (repartyFailList.size() > 0) {
                    String repartyFails = String.join("\n- " + EnumChatFormatting.RED, (Iterable)repartyFailList);
                    player.addChatMessage((IChatComponent)new ChatComponentText(DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------\n" + DankersSkyblockMod.MAIN_COLOUR + "Failed to invite:" + EnumChatFormatting.WHITE + "\n- " + EnumChatFormatting.RED + repartyFails + "\n" + DankersSkyblockMod.DELIMITER_COLOUR + "-----------------------------"));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        partyThread.start();
    }
}
