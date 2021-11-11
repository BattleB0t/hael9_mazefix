package me.Danker.gui.buttons;

import me.Danker.handlers.TextRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;

public class LocationButton extends GuiButton {
    private int x;

    private int y;

    private double scale;

    private String text;

    private String text2;

    private Integer text2Offset;

    public LocationButton(int buttonId, int x, int y, double width, double height, double scale, String text, String text2, Integer text2Offset) {
        super(buttonId, x, y, text);
        this.x = x;
        this.y = y;
        this.width = (int)width;
        this.height = (int)height;
        this.scale = scale;
        this.text = text;
        this.text2 = text2;
        this.text2Offset = text2Offset;
    }

    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        String[] splitText;
        if (this.text2 == null) {
            splitText = this.text.split("\n");
        } else {
            splitText = this.text2.split("\n");
        }
        int longestText = -1;
        for (String s : splitText) {
            int stringLength = mc.fontRendererObj.getStringWidth(s);
            if (stringLength > longestText)
                longestText = stringLength;
        }
        if (this.text2 == null) {
            drawRect(this.x - 2, this.y - 2, (int)(this.x + longestText * this.scale + 3.0D), (int)(this.y + (splitText.length * 9 + 3) * this.scale), 1087624147);
        } else {
            drawRect(this.x - 2, this.y - 2, (int)(this.x + (longestText + this.text2Offset.intValue()) * this.scale + 3.0D), (int)(this.y + (splitText.length * 9 + 3) * this.scale), 1087624147);
            new TextRenderer(mc, this.text2, (int)(this.x + this.text2Offset.intValue() * this.scale), this.y, this.scale);
        }
        new TextRenderer(mc, this.text, this.x, this.y, this.scale);
    }

    public void playPressSound(SoundHandler soundHandler) {}
}
