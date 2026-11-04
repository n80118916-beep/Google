package com.example.watermark;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class WatermarkHUD {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final String TEXT = "Google";

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

        int x = 4; 
        int y = 4; 
        int iconSize = 16; 
        int padding = 4; 
        int textWidth = mc.fontRenderer.getStringWidth(TEXT);
        
        int totalWidth = padding + iconSize + padding + textWidth + padding;
        int totalHeight = padding + iconSize + padding;

        // 1. Полупрозрачный чёрный фон
        Gui.drawRect(x, y, x + totalWidth, y + totalHeight, 0x7F000000);

        // 2. Иконка (звезда)
        int iconCenterX = x + padding + (iconSize / 2);
        int iconCenterY = y + padding + (iconSize / 2);
        drawStarIcon(iconCenterX, iconCenterY, 7);

        // 3. Текст "Google"
        int textX = x + padding + iconSize + padding;
        int textY = y + padding + (iconSize - mc.fontRenderer.FONT_HEIGHT) / 2;
        mc.fontRenderer.drawStringWithShadow(TEXT, textX, textY, 0xFFFFFF);
    }

    private void drawStarIcon(int cx, int cy, int size) {
        GL11.glPushMatrix();
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);

        // Свечение
        for (int i = 0; i < 4; i++) {
            GL11.glLineWidth(6.0f - i); 
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.1f); 
            
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex2f(cx, cy - size);
            GL11.glVertex2f(cx, cy + size);
            GL11.glVertex2f(cx - size, cy);
            GL11.glVertex2f(cx + size, cy);
            GL11.glVertex2f(cx - size * 0.6f, cy - size * 0.6f);
            GL11.glVertex2f(cx + size * 0.6f, cy + size * 0.6f);
            GL11.glVertex2f(cx - size * 0.6f, cy + size * 0.6f);
            GL11.glVertex2f(cx + size * 0.6f, cy - size * 0.6f);
            GL11.glEnd();
        }

        // Яркий крест
        GL11.glLineWidth(2.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f); 
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2f(cx, cy - size);
        GL11.glVertex2f(cx, cy + size);
        GL11.glVertex2f(cx - size, cy);
        GL11.glVertex2f(cx + size, cy);
        GL11.glEnd();

        // Центр
        Gui.drawRect(cx - 2, cy - 2, cx + 2, cy + 2, 0xFFFFFFFF);

        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
  }
