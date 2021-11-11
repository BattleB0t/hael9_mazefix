package me.Danker.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import me.Danker.DankersSkyblockMod;
import me.Danker.handlers.ScoreboardHandler;
import me.Danker.handlers.TextRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import org.lwjgl.opengl.GL11;

public class Utils {
    public static boolean inSkyblock = false;

    public static boolean inDungeons = false;

    public static int[] skillXPPerLevel = new int[] {
            0, 50, 125, 200, 300, 500, 750, 1000, 1500, 2000,
            3500, 5000, 7500, 10000, 15000, 20000, 30000, 50000, 75000, 100000,
            200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000, 1100000,
            1200000, 1300000, 1400000, 1500000, 1600000, 1700000, 1800000, 1900000, 2000000, 2100000,
            2200000, 2300000, 2400000, 2500000, 2600000, 2750000, 2900000, 3100000, 3400000, 3700000,
            4000000, 4300000, 4600000, 4900000, 5200000, 5500000, 5800000, 6100000, 6400000, 6700000,
            7000000 };

    static int[] dungeonsXPPerLevel = new int[] {
            0, 50, 75, 110, 160, 230, 330, 470, 670, 950,
            1340, 1890, 2665, 3760, 5260, 7380, 10300, 14400, 20000, 27600,
            38000, 52500, 71500, 97000, 132000, 180000, 243000, 328000, 445000, 600000,
            800000, 1065000, 1410000, 1900000, 2500000, 3300000, 4300000, 5600000, 7200000, 9200000,
            12000000, 15000000, 19000000, 24000000, 30000000, 38000000, 48000000, 60000000, 75000000, 93000000,
            116250000 };

    static int[] expertiseKills = new int[] { 50, 100, 250, 500, 1000, 2500, 5500, 10000, 15000 };

    public static int getItems(String item) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP entityPlayerSP = mc.thePlayer;
        double x = ((EntityPlayer)entityPlayerSP).posX;
        double y = ((EntityPlayer)entityPlayerSP).posY;
        double z = ((EntityPlayer)entityPlayerSP).posZ;
        AxisAlignedBB scan = new AxisAlignedBB(x - 6.0D, y - 6.0D, z - 6.0D, x + 6.0D, y + 6.0D, z + 6.0D);
        List<EntityItem> items = mc.theWorld.getEntitiesWithinAABB(EntityItem.class, scan);
        for (EntityItem i : items) {
            String itemName = StringUtils.stripControlCodes(i.getEntityItem().getDisplayName());
            if (itemName.equals(item))
                return (i.getEntityItem()).stackSize;
        }
        return 0;
    }

    public static String returnGoldenEnchants(String line) {
        Matcher matcher = DankersSkyblockMod.t6EnchantPattern.matcher(line);
        StringBuffer out = new StringBuffer();
        while (matcher.find())
            matcher.appendReplacement(out, (String)DankersSkyblockMod.t6Enchants.get(matcher.group(1)));
        matcher.appendTail(out);
        return out.toString();
    }

    public static List<String> getMatchingPlayers(String arg) {
        List<String> matchingPlayers = new ArrayList<>();
        Collection<NetworkPlayerInfo> players = Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap();
        for (NetworkPlayerInfo player : players) {
            String playerName = player.getGameProfile().getName();
            if (!playerName.startsWith("!") &&
                    playerName.toLowerCase().startsWith(arg.toLowerCase()))
                matchingPlayers.add(playerName);
        }
        return matchingPlayers;
    }

    public static void createTitle(String text, int seconds) {
        (Minecraft.getMinecraft()).thePlayer.playSound("random.orb", 1.0F, 0.5F);
        DankersSkyblockMod.titleTimer = seconds * 20;
        DankersSkyblockMod.showTitle = true;
        DankersSkyblockMod.titleText = text;
    }

    public static void drawTitle(String text) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        int height = scaledResolution.getScaledHeight();
        int width = scaledResolution.getScaledWidth();
        int drawHeight = 0;
        String[] splitText = text.split("\n");
        for (String title : splitText) {
            int textLength = mc.fontRendererObj.getStringWidth(title);
            double scale = 4.0D;
            if (textLength * scale > (width * 0.9F))
                scale = (width * 0.9F / textLength);
            int titleX = (int)((width / 2) - textLength * scale / 2.0D);
            int titleY = (int)(height * 0.45D / scale) + (int)(drawHeight * scale);
            new TextRenderer(mc, title, titleX, titleY, scale);
            drawHeight += mc.fontRendererObj.FONT_HEIGHT;
        }
    }

    public static boolean isOnHypixel() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.theWorld != null && !mc.isSingleplayer())
            return (mc.getCurrentServerData()).serverIP.toLowerCase().contains("hypixel");
        return false;
    }

    public static void checkForSkyblock() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.theWorld != null && !mc.isSingleplayer()) {
            ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            if (scoreboardObj != null) {
                String scObjName = ScoreboardHandler.cleanSB(scoreboardObj.getDisplayName());
                if (scObjName.contains("SKYBLOCK")) {
                    inSkyblock = true;
                    return;
                }
            }
        }
        inSkyblock = false;
    }

    public static void checkForDungeons() {
        if (inSkyblock) {
            List<String> scoreboard = ScoreboardHandler.getSidebarLines();
            for (String s : scoreboard) {
                String sCleaned = ScoreboardHandler.cleanSB(s);
                if (sCleaned.contains("The Catacombs")) {
                    inDungeons = true;
                    return;
                }
            }
        }
        inDungeons = false;
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String capitalizeString(String string) {
        String[] words = string.split("_");
        for (int i = 0; i < words.length; i++)
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        return String.join(" ", (CharSequence[])words);
    }

    public static double getPercentage(int num1, int num2) {
        if (num2 == 0)
            return 0.0D;
        double result = num1 * 100.0D / num2;
        result = Math.round(result * 100.0D) / 100.0D;
        return result;
    }

    public static void drawOnSlot(int size, int xSlotPos, int ySlotPos, int colour) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        int guiLeft = (sr.getScaledWidth() - 176) / 2;
        int guiTop = (sr.getScaledHeight() - 222) / 2;
        int x = guiLeft + xSlotPos;
        int y = guiTop + ySlotPos;
        if (size != 90)
            y += (6 - (size - 36) / 9) * 9;
        GL11.glTranslated(0.0D, 0.0D, 1.0D);
        Gui.drawRect(x, y, x + 16, y + 16, colour);
        GL11.glTranslated(0.0D, 0.0D, -1.0D);
    }

    public static String getTimeBetween(double timeOne, double timeTwo) {
        String timeFormatted;
        double secondsBetween = Math.floor(timeTwo - timeOne);
        if (secondsBetween > 86400.0D) {
            int days = (int)(secondsBetween / 86400.0D);
            int hours = (int)(secondsBetween % 86400.0D / 3600.0D);
            timeFormatted = days + "d" + hours + "h";
        } else if (secondsBetween > 3600.0D) {
            int hours = (int)(secondsBetween / 3600.0D);
            int minutes = (int)(secondsBetween % 3600.0D / 60.0D);
            timeFormatted = hours + "h" + minutes + "m";
        } else {
            int minutes = (int)(secondsBetween / 60.0D);
            int seconds = (int)(secondsBetween % 60.0D);
            timeFormatted = minutes + "m" + seconds + "s";
        }
        return timeFormatted;
    }

    public static String getMoneySpent(double coins) {
        double coinsSpentMillions = coins / 1000000.0D;
        coinsSpentMillions = Math.floor(coinsSpentMillions * 100.0D) / 100.0D;
        return coinsSpentMillions + "M";
    }

    public static double xpToSkillLevel(double xp, int limit) {
        for (int i = 0, xpAdded = 0; i < limit + 1; i++) {
            xpAdded += skillXPPerLevel[i];
            if (xp < xpAdded)
                return (i - 1) + (xp - (xpAdded - skillXPPerLevel[i])) / skillXPPerLevel[i];
        }
        return limit;
    }

    public static double xpToDungeonsLevel(double xp) {
        for (int i = 0, xpAdded = 0; i < dungeonsXPPerLevel.length; i++) {
            xpAdded += dungeonsXPPerLevel[i];
            if (xp < xpAdded) {
                double level = (i - 1) + (xp - (xpAdded - dungeonsXPPerLevel[i])) / dungeonsXPPerLevel[i];
                return Math.round(level * 100.0D) / 100.0D;
            }
        }
        return 50.0D;
    }

    public static int expertiseKillsLeft(int kills) {
        for (int i = 0; i < expertiseKills.length; i++) {
            if (kills < expertiseKills[i])
                return expertiseKills[i] - kills;
        }
        return -1;
    }

    public static int getPastXpEarned(int currentLevelXp, int limit) {
        if (currentLevelXp == 0) {
            int j = 0;
            for (int k = 1; k <= limit; k++)
                j += skillXPPerLevel[k];
            return j;
        }
        for (int i = 1, xpAdded = 0; i <= limit; i++) {
            xpAdded += skillXPPerLevel[i - 1];
            if (currentLevelXp == skillXPPerLevel[i])
                return xpAdded;
        }
        return 0;
    }

    public static String getColouredBoolean(boolean bool) {
        return bool ? (EnumChatFormatting.GREEN + "On") : (EnumChatFormatting.RED + "Off");
    }

    public static List<String> getItemLore(ItemStack itemStack) {
        int NBT_INTEGER = 3;
        int NBT_STRING = 8;
        int NBT_LIST = 9;
        int NBT_COMPOUND = 10;
        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("display", 10)) {
            NBTTagCompound display = itemStack.getTagCompound().getCompoundTag("display");
            if (display.hasKey("Lore", 9)) {
                NBTTagList lore = display.getTagList("Lore", 8);
                List<String> loreAsList = new ArrayList<>();
                for (int lineNumber = 0; lineNumber < lore.tagCount(); lineNumber++)
                    loreAsList.add(lore.getStringTagAt(lineNumber));
                return Collections.unmodifiableList(loreAsList);
            }
        }
        return Collections.emptyList();
    }

    public static boolean hasRightClickAbility(ItemStack itemStack) {
        return getItemLore(itemStack).stream().anyMatch(line -> {
            String stripped = StringUtils.stripControlCodes(line);
            return (stripped.startsWith("Item Ability:") && stripped.endsWith("RIGHT CLICK"));
        });
    }

    public static void draw3DLine(Vec3 pos1, Vec3 pos2, int colourInt, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        Color colour = new Color(colourInt);
        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-realX, -realY, -realZ);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(2.0F);
        GlStateManager.color(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(pos1.xCoord, pos1.yCoord, pos1.zCoord).endVertex();
        worldRenderer.pos(pos2.xCoord, pos2.yCoord, pos2.zCoord).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public static void draw3DString(BlockPos pos, String text, int colour, float partialTicks) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP entityPlayerSP = mc.thePlayer;
        double x = pos.getX() - ((EntityPlayer)entityPlayerSP).lastTickPosX + (pos.getX() - ((EntityPlayer)entityPlayerSP).posX - pos.getX() - ((EntityPlayer)entityPlayerSP).lastTickPosX) * partialTicks;
        double y = pos.getY() - ((EntityPlayer)entityPlayerSP).lastTickPosY + (pos.getY() - ((EntityPlayer)entityPlayerSP).posY - pos.getY() - ((EntityPlayer)entityPlayerSP).lastTickPosY) * partialTicks;
        double z = pos.getZ() - ((EntityPlayer)entityPlayerSP).lastTickPosZ + (pos.getZ() - ((EntityPlayer)entityPlayerSP).posZ - pos.getZ() - ((EntityPlayer)entityPlayerSP).lastTickPosZ) * partialTicks;
        RenderManager renderManager = mc.getRenderManager();
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        int width = mc.fontRendererObj.getStringWidth(text) / 2;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.scale(-f1, -f1, -f1);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        mc.fontRendererObj.drawString(text, -width, 0, colour);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void draw3DBox(AxisAlignedBB aabb, int colourInt, float partialTicks) {
        Entity render = Minecraft.getMinecraft().getRenderViewEntity();
        WorldRenderer worldRenderer = Tessellator.getInstance().getWorldRenderer();
        Color colour = new Color(colourInt);
        double realX = render.lastTickPosX + (render.posX - render.lastTickPosX) * partialTicks;
        double realY = render.lastTickPosY + (render.posY - render.lastTickPosY) * partialTicks;
        double realZ = render.lastTickPosZ + (render.posZ - render.lastTickPosZ) * partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-realX, -realY, -realZ);
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glLineWidth(2.0F);
        GlStateManager.color(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.minY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.minY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.minY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        Tessellator.getInstance().draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.maxY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.maxY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.maxY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        Tessellator.getInstance().draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aabb.minX, aabb.minY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.maxY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.minY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.maxY, aabb.minZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.minY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.maxX, aabb.maxY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.minY, aabb.maxZ).endVertex();
        worldRenderer.pos(aabb.minX, aabb.maxY, aabb.maxZ).endVertex();
        Tessellator.getInstance().draw();
        GlStateManager.translate(realX, realY, realZ);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.popMatrix();
    }

    public static void renderItem(ItemStack item, float x, float y, float z) {
        GlStateManager.enableRescaleNormal();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableDepth();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(item, 0, 0);
        GlStateManager.popMatrix();
        GlStateManager.disableDepth();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
    }

    public static BlockPos getFirstBlockPosAfterVectors(Minecraft mc, Vec3 pos1, Vec3 pos2, int strength, int distance) {
        double x = pos2.xCoord - pos1.xCoord;
        double y = pos2.yCoord - pos1.yCoord;
        double z = pos2.zCoord - pos1.zCoord;
        for (int i = strength; i < distance * strength; i++) {
            double newX = pos1.xCoord + x / strength * i;
            double newY = pos1.yCoord + y / strength * i;
            double newZ = pos1.zCoord + z / strength * i;
            BlockPos newBlock = new BlockPos(newX, newY, newZ);
            if (mc.theWorld.getBlockState(newBlock).getBlock() != Blocks.air)
                return newBlock;
        }
        return null;
    }

    public static BlockPos getNearbyBlock(Minecraft mc, BlockPos pos, Block... blockTypes) {
        if (pos == null)
            return null;
        BlockPos pos1 = new BlockPos(pos.getX() - 2, pos.getY() - 3, pos.getZ() - 2);
        BlockPos pos2 = new BlockPos(pos.getX() + 2, pos.getY() + 3, pos.getZ() + 2);
        BlockPos closestBlock = null;
        double closestBlockDistance = 99.0D;
        Iterable<BlockPos> blocks = BlockPos.getAllInBox(pos1, pos2);
        for (BlockPos block : blocks) {
            for (Block blockType : blockTypes) {
                if (mc.theWorld.getBlockState(block).getBlock() == blockType && block.distanceSq((Vec3i)pos) < closestBlockDistance) {
                    closestBlock = block;
                    closestBlockDistance = block.distanceSq((Vec3i)pos);
                }
            }
        }
        return closestBlock;
    }

    public static BlockPos getBlockUnderItemFrame(EntityItemFrame itemFrame) {
        switch (itemFrame.facingDirection) {
            case NORTH:
                return new BlockPos(itemFrame.posX, itemFrame.posY, itemFrame.posZ + 1.0D);
            case EAST:
                return new BlockPos(itemFrame.posX - 1.0D, itemFrame.posY, itemFrame.posZ - 0.5D);
            case SOUTH:
                return new BlockPos(itemFrame.posX, itemFrame.posY, itemFrame.posZ - 1.0D);
            case WEST:
                return new BlockPos(itemFrame.posX + 1.0D, itemFrame.posY, itemFrame.posZ - 0.5D);
        }
        return null;
    }
}
