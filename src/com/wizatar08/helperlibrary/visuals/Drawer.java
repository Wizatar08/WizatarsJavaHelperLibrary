package com.wizatar08.helperlibrary.visuals;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

public class Drawer {
    public static java.awt.Font FONT;
    public static org.newdawn.slick.TrueTypeFont TRUE_TYPE_FONT;
    public static String defaultImagePath;

    /**
     * Set the font. Must be a .ttf file type
     * @param tffFontFilePath
     */
    public static void setFont(String tffFontFilePath) {
        try {
            InputStream inputStream = org.newdawn.slick.util.ResourceLoader.getResourceAsStream(tffFontFilePath);

            FONT = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            FONT = FONT.deriveFont(24f); // set font size

            TRUE_TYPE_FONT = new org.newdawn.slick.TrueTypeFont(FONT, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Draw a slick util texture
     * @param tex
     * @param x
     * @param y
     * @param width
     * @param height
     * @param angle
     * @param topY
     * @param bottomY
     */
    public static void drawQuadTex(org.newdawn.slick.opengl.Texture tex, float x, float y, float width, float height, float angle, float topY, float bottomY) {
        drawQuadTex(tex, x, y, width, height, angle, topY, bottomY, 1.0f);
    }

    /**
     * Draw a slick util texture
     * @param tex Texture
     * @param x X position
     * @param y Y position
     * @param width Width of texture
     * @param height Height of texture
     * @param angle Rotation
     * @param topY Top offset
     * @param bottomY Bottom offset
     * @param alpha Transparency
     */
    public static void drawQuadTex(org.newdawn.slick.opengl.Texture tex, float x, float y, float width, float height, float angle, float topY, float bottomY, float alpha) {
        drawQuadTex(tex, x, y, width, height, angle, topY, bottomY, 1.0f, 1.0f, 1.0f, alpha);
    }

    /**
     * Draw a slick util texture
     * @param tex Texture
     * @param x X position
     * @param y Y position
     * @param width Width of texture
     * @param height Height of texture
     * @param angle Rotation
     * @param topY Top offset
     * @param bottomY Bottom offset
     * @param r Red tint
     * @param g Green tint
     * @param b Blue tint
     * @param alpha Transparency
     */
    public static void drawQuadTex(org.newdawn.slick.opengl.Texture tex, float x, float y, float width, float height, float angle, float topY, float bottomY, float r, float g, float b, float alpha) {
        drawQuadTex(tex, x, y, width, height, angle, topY, bottomY, 0, 1, r, g, b, alpha);
    }

    /**
     * Draw a slick util texture
     * @param tex Texture
     * @param x X position
     * @param y Y position
     * @param width Width of texture
     * @param height Height of texture
     * @param angle Rotation
     * @param topY Top offset
     * @param bottomY Bottom offset
     * @param leftX Image left offset
     * @param rightX Image right offset
     * @param r Red tint
     * @param g Green tint
     * @param b Blue tint
     * @param alpha Transparency
     */
    public static void drawQuadTex(org.newdawn.slick.opengl.Texture tex, float x, float y, float width, float height, float angle, float topY, float bottomY, float leftX, float rightX, float r, float g, float b, float alpha) {
        tex.bind();
        glTranslatef(x + width / 2, y + height / 2, 0);
        glColor4f(r, g, b, alpha);
        glRotatef(angle, 0, 0, 1);
        glScalef(1, -1, 0); // For some reason the new way of using textures in this game flips all the textures vertically, so we have to flip it back.
        glTranslatef(-width / 2, -height / 2, 0);
        glBegin(GL_QUADS);
        glClear(GL_COLOR_BUFFER_BIT);
        glTexCoord2f(leftX, bottomY); glVertex2f(0, 0);
        glTexCoord2f(rightX, bottomY); glVertex2f(width, 0);
        glTexCoord2f(rightX, topY); glVertex2f(width, height);
        glTexCoord2f(leftX, topY); glVertex2f(0, height);
        glEnd();
        glLoadIdentity();
    }

    /**
     * Get a Slick Util Texture() based on the file location and file type
     * @param path
     * @param fileType
     * @return
     */
    public static org.newdawn.slick.opengl.Texture LoadTexture(String path, String fileType) {
        org.newdawn.slick.opengl.Texture tex = null;
        InputStream in;
        try {
            in = org.newdawn.slick.util.ResourceLoader.getResourceAsStream(path);
        } catch (RuntimeException e) {
            in = null;
        }
        try {
            tex = org.newdawn.slick.opengl.TextureLoader.getTexture(fileType.toUpperCase(), in);
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
        }
        return tex;
    }

    /**
     * Load a PNG file
     * @param name
     * @return
     */
    public static org.newdawn.slick.opengl.Texture LoadPNG(String name) {
        if (name != null) {
            org.newdawn.slick.opengl.Texture tex = null;
            tex = LoadTexture(defaultImagePath + name + ".png", "png");
            return tex;
        }
        return null;
    }

    /**
     * Set the default image path
     * @param path
     */
    public static void setDefaultImagePath(String path) {
        defaultImagePath = path;
    }
}