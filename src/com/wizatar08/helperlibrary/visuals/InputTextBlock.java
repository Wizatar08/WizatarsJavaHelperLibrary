//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.wizatar08.helperlibrary.visuals;

import com.wizatar08.helperlibrary.screen.Renderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import com.wizatar08.helperlibrary.visuals.Tex;
import com.wizatar08.helperlibrary.visuals.TextBlock;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class InputTextBlock extends TextBlock {
    private float width;
    private float height;
    private boolean isSelected;
    private boolean hasPlacedChar;
    private boolean enter;
    private String noTextHover;
    private Tex cursorTex;
    private static ArrayList<Integer> NO_CHAR_BUTTONS = new ArrayList(Arrays.asList(42, 54, 196, 29, 157, 183, 183, 205, 203, 200, 208, 1, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 87, 88, 100, 101, 102, 103, 104, 105, 113, 56, 184, 219, 220));
    private static ArrayList<InputTextBlock> ALL_TEXT_BLOCKS = new ArrayList();
    public static boolean TEXT_BLOCK_SELECTED = false;

    public InputTextBlock(String name, String text, int x, int y, int width, int height) {
        this(name, text, x, y, width, height, 24.0F, Color.white);
    }

    public InputTextBlock(String name, String text, int x, int y, int width, int height, float fontSize) {
        this(name, text, x, y, width, height, fontSize, Color.white);
    }

    public InputTextBlock(String name, String text, int x, int y, int width, int height, Color color) {
        this(name, text, x, y, width, height, 24.0F, color);
    }

    public InputTextBlock(String name, String text, int x, int y, int width, int height, float fontSize, Color color) {
        this(name, text, x, y, width, height, fontSize, color, "");
    }

    public InputTextBlock(String name, String text, int x, int y, int width, int height, float fontSize, Color color, String noTextHover) {
        super(name, text, x, y, fontSize, color);
        this.width = (float)width;
        this.height = (float)height;
        this.cursorTex = new Tex("ui/cursor");
        this.noTextHover = noTextHover;
        ALL_TEXT_BLOCKS.add(this);
    }

    public boolean isClicked() {
        return Mouse.isButtonDown(0) ? this.isHovered() : false;
    }

    public boolean isHovered() {
        float mouseY = (float)(Renderer.HEIGHT - Mouse.getY() - 1) - ((float)Display.getHeight() - (float)Renderer.HEIGHT * Renderer.stretchedMultiplierTotal - (float)(Display.getHeight() - Renderer.HEIGHT));
        return (float)Mouse.getX() > this.getX() * Renderer.stretchedMultiplierTotal && (float)Mouse.getX() < (this.getX() + this.width) * Renderer.stretchedMultiplierTotal && mouseY > this.getY() * Renderer.stretchedMultiplierTotal && mouseY < (this.getY() + this.height) * Renderer.stretchedMultiplierTotal;
    }

    public void update() {
        this.isSelected = this.isHovered();
        if (isHovered()) {
            this.enter = false;
            if (this.isSelected) {
                this.inputText();
            }

            while (Mouse.next()) {
                if (Mouse.getEventButtonState()) {
                    //System.out.println("CLICK: " + this.isSelected + ", " + TEXT_BLOCK_SELECTED);
                    if (this.isSelected) {
                        Iterator var1 = ALL_TEXT_BLOCKS.iterator();

                        while (var1.hasNext()) {
                            InputTextBlock text = (InputTextBlock) var1.next();
                            if (text != this) {
                                text.select(false);
                            }
                        }

                        return;
                    }
                }
            }

        }
        boolean oneIsSelected = false;
        Iterator textBlockIterator = ALL_TEXT_BLOCKS.iterator();
        while (textBlockIterator.hasNext()) {
            InputTextBlock text = (InputTextBlock) textBlockIterator.next();
            if (text.isSelected()) {
                oneIsSelected = true;
            }
        }
        TEXT_BLOCK_SELECTED = oneIsSelected;
    }

    public void draw() {
        if (super.isShown()) {
            if (!this.getChars().equals("")) {
                super.getTrueTypeFont().drawString(this.getX(), this.getY(), this.getChars(), this.getColor());
            } else {
                super.getTrueTypeFont().drawString(this.getX(), this.getY(), this.noTextHover, this.getColor().darker(0.35F));
            }
        }

        if (this.isSelected) {
            this.cursorTex.setColorTint(this.getColor()).setImageCrop(0.0F, 1.0F, 0.0F, this.getFontSize() / (float)this.cursorTex.getOpenGLTex().getImageHeight()).draw(this.getX() + this.getWidth(), this.getY());
        }

    }

    public void inputText() {
        Keyboard.next();
        if (!this.hasPlacedChar && Keyboard.getEventKeyState()) {
            if (Keyboard.getEventKey() != 14 && Keyboard.getEventKey() != 211) {
                if (Keyboard.getEventKey() != 28 && Keyboard.getEventKey() != 156) {
                    boolean hasChar = true;
                    Iterator var2 = NO_CHAR_BUTTONS.iterator();

                    while(var2.hasNext()) {
                        int charNum = (Integer)var2.next();
                        if (Keyboard.getEventKey() == charNum) {
                            hasChar = false;
                        }
                    }

                    if (hasChar) {
                        this.setChars(this.getChars() + Keyboard.getEventCharacter());
                    }
                } else {
                    this.enter = true;
                    this.select(false);
                }
            } else if (this.getChars().length() != 0) {
                this.setChars(this.getChars().substring(0, this.getChars().length() - 1));
            }

            this.hasPlacedChar = true;
        }

        if (!Keyboard.getEventKeyState()) {
            this.hasPlacedChar = false;
        }

    }

    public boolean activated() {
        return this.enter;
    }

    public void select(boolean setSelect) {
        this.isSelected = setSelect;
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
