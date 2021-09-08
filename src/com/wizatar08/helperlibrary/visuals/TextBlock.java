package com.wizatar08.helperlibrary.visuals;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import static com.wizatar08.helperlibrary.visuals.Drawer.*;

public class TextBlock {
    private String text, name;
    private float x, y;
    private float fontSize;
    private org.newdawn.slick.Color textColor;
    private boolean show;
    private Font awtFont;
    private org.newdawn.slick.TrueTypeFont trueTypeFont;

    public TextBlock(String name, String text, int x, int y) {
        this(name, text, x, y, 24f, org.newdawn.slick.Color.white);
    }

    public TextBlock(String name, String text, int x, int y, float fontSize) {
        this(name, text, x, y, fontSize, org.newdawn.slick.Color.white);
    }

    public TextBlock(String name, String text, int x, int y, org.newdawn.slick.Color color) {
        this(name, text, x, y, 24f, color);
    }

    public TextBlock(String name, String text, int x, int y, float fontSize, org.newdawn.slick.Color color) {
        this.name = name;
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.textColor = color;
        this.awtFont = FONT;
        this.trueTypeFont = TRUE_TYPE_FONT;
        changeFontSize(fontSize);
        show = true;
    }

    public void draw() {
        if (show) {
            trueTypeFont.drawString(x, y, text, textColor);
        }
    }

    public void changeFontSize(float size) {
        java.awt.Font newFont = awtFont.deriveFont(size);
        trueTypeFont = new org.newdawn.slick.TrueTypeFont(newFont, true);
        awtFont = newFont;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getChars() {
        return text;
    }

    public float getFontSize() {
        return fontSize;
    }

    public org.newdawn.slick.Color getColor() {
        return textColor;
    }

    public String getName() {
        return name;
    }

    public float getWidth() {
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        return (float) (awtFont.getStringBounds(text, frc).getWidth());
    }
    public float getHeight() {
        FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
        return (float)(awtFont.getStringBounds(text, frc).getHeight());
    }

    public void setCoords(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setChars(String text) {
        this.text = text;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setTextColor(org.newdawn.slick.Color color) {
        this.textColor = color;
    }

    public boolean isShown() {
        return show;
    }

    public void show() {
        show = true;
    }

    public void hide() {
        show = false;
    }

    public org.newdawn.slick.TrueTypeFont getTrueTypeFont() {
        return trueTypeFont;
    }
}