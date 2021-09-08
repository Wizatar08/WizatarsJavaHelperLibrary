package com.wizatar08.helperlibrary.ui;

import java.util.ArrayList;
import static com.wizatar08.helperlibrary.screen.Renderer.*;

public class UI {
    private ArrayList<com.wizatar08.helperlibrary.ui.Button> buttonList;
    private ArrayList<Menu> menuList;
    private ArrayList<com.wizatar08.helperlibrary.visuals.TextBlock> textList;
    private com.wizatar08.helperlibrary.visuals.TextBlock blank;

    public UI() {
        buttonList = new ArrayList<>();
        menuList = new ArrayList<>();
        textList = new ArrayList<>();
        blank = new com.wizatar08.helperlibrary.visuals.TextBlock("-blank-", "", 0, 0, 24f, org.newdawn.slick.Color.white);
    }

    public void drawString(com.wizatar08.helperlibrary.visuals.TextBlock text) {
        textList.add(text);
    }

    public void eraseString(String name) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                textList.remove(text);
                return;
            }
        }
        System.err.println("eraseString - TextBlock not found: " + name);
    }

    public void showString(String name, boolean show) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                if (show) {
                    text.show();
                } else {
                    text.hide();
                }
                return;
            }
        }
        System.err.println("showString - TextBlock not found: " + name);
    }

    public void setStringPos(String name, float x, float y) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                text.setCoords(x, y);
                return;
            }
        }
        System.err.println("setStringPos - TextBlock not found: " + name);
    }

    public void changeString(String name, String newText) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                text.setChars(newText);
                return;
            }
        }
        System.err.println("changeString - TextBlock not found: " + name);
    }

    public com.wizatar08.helperlibrary.visuals.TextBlock getString(String name) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                return text;
            }
        }
        System.err.println("getString - TextBlock not found: " + name);
        return null;
    }

    public void changeStringColor(String name, org.newdawn.slick.Color color) {
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            if (text.getName().equals(name)) {
                text.setTextColor(color);
                return;
            }
        }
        System.err.println("changeStringColor - TextBlock not found: " + name);
    }

    public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] textureNames, int x, int y) {
        int[] rots = new int[textureNames.length];
        for (int i = 0; i < textureNames.length; i++) {
            rots[i] = 0;
        }
        addButton(name, textureNames, x, y, rots);
    }

    public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] textureNames, int x, int y, int[] rots) {
        buttonList.add(new com.wizatar08.helperlibrary.ui.Button(name, textureNames, x, y, rots));
    }

    public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] textureNames, int x, int y, com.wizatar08.helperlibrary.visuals.TextBlock text) {
        addButton(name, textureNames, x, y, text, false, false);
    }

    public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] textureNames, int x, int y, com.wizatar08.helperlibrary.visuals.TextBlock text, boolean centerW, boolean centerH) {
        com.wizatar08.helperlibrary.ui.Button b;
        int[] rots = new int[textureNames.length];
        for (int i = 0; i < textureNames.length; i++) {
            rots[i] = 0;
        }
        buttonList.add(b = new com.wizatar08.helperlibrary.ui.Button(name, textureNames, x, y, rots, text));
        if (b.getText() != null) {
            if (!centerW) {
                b.getText().setX(x + b.getText().getX());
            } else {
                b.getText().setX(b.getX() + (float) b.getWidth() / 2 - (b.getText().getWidth() / 2));
            }
            if (!centerH) {
                b.getText().setY(y + b.getText().getY());
            } else {
                b.getText().setY(b.getY() + (float) b.getHeight() / 2 - (b.getText().getHeight() / 2));
            }
        }
    }

    public boolean isButtonClicked(String buttonName) {
        if (org.lwjgl.input.Mouse.isButtonDown(0)) {
            return isButtonHovered(buttonName);
        }
        return false;
    }

    public boolean isButtonHovered(String buttonName) {
        com.wizatar08.helperlibrary.ui.Button b = getButton(buttonName);
        float mouseY = (HEIGHT - org.lwjgl.input.Mouse.getY() - 1) - (org.lwjgl.opengl.Display.getHeight() - ((float) HEIGHT * stretchedMultiplierTotal) - (org.lwjgl.opengl.Display.getHeight() - HEIGHT));
        return
                org.lwjgl.input.Mouse.getX() > ((float) b.getX() * stretchedMultiplierTotal) &&
                        org.lwjgl.input.Mouse.getX() < (((float) b.getX() + b.getWidth()) * stretchedMultiplierTotal) &&
                        mouseY > ((float) b.getY() * stretchedMultiplierTotal) &&
                        mouseY < (((float) b.getY() + b.getHeight()) * stretchedMultiplierTotal);
    }

    public com.wizatar08.helperlibrary.ui.Button getButton(String buttonName) {
        for (com.wizatar08.helperlibrary.ui.Button b: buttonList) {
            if (b.getName().equals(buttonName)) {
                return b;
            }
        }
        return null;
    }

    public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight, com.wizatar08.helperlibrary.visuals.Tex background) {
        menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight, background));
    }

    public void createMenu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight) {
        menuList.add(new Menu(name, x, y, width, height, optionsWidth, optionsHeight, null));
    }

    public void removeMenu(String name) {
        for (Menu menu : menuList) {
            if (menu.getName().equals(name)) {
                menuList.remove(menu);
                break;
            }
        }
    }

    public void clearAllMenus() {
        menuList.clear();
    }

    public Menu getMenu(String name) {
        for (Menu m: menuList) {
            if (name.equals(m.getName())) {
                return m;
            }
        }
        return null;
    }

    public void update() {
        for (com.wizatar08.helperlibrary.ui.Button b: buttonList) {
            for (int i = 0; i < b.getTextures().length; i++) {
                b.getTextures()[i].draw(b.getX(), b.getY());
            }
            if (b.getText() != null) {
                b.getText().draw();
            }
        }
        for (Menu m: menuList) {
            m.draw();
        }
        for (com.wizatar08.helperlibrary.visuals.TextBlock text : textList) {
            text.draw();
        }
        blank.draw();
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public class Menu {
        String name;
        private ArrayList<com.wizatar08.helperlibrary.ui.Button> menuButtons;
        private int x, y, width, height, buttonAmount, optionsWidth, yDist;
        private boolean show;
        private com.wizatar08.helperlibrary.visuals.Tex background;
        private com.wizatar08.helperlibrary.visuals.TextBlock blank;

        public Menu(String name, int x, int y, int width, int height, int optionsWidth, int optionsHeight, com.wizatar08.helperlibrary.visuals.Tex background) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.optionsWidth = optionsWidth;
            this.background = background;
            this.buttonAmount = 0;
            this.menuButtons = new ArrayList<Button>();
            this.show = true;
            this.blank = new com.wizatar08.helperlibrary.visuals.TextBlock("-blank-", "", 0, 0, 24f, org.newdawn.slick.Color.white);
            this.yDist = height / optionsHeight;
        }

        public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] buttonTextures) {
            addButton(name, buttonTextures, (com.wizatar08.helperlibrary.visuals.TextBlock) null, false, false);
        }

        public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] buttonTextures, com.wizatar08.helperlibrary.visuals.TextBlock text, boolean centerW, boolean centerH) {
            int[] rots = new int[buttonTextures.length];
            for (int i = 0; i < buttonTextures.length; i++) {
                rots[i] = 0;
            }
            com.wizatar08.helperlibrary.ui.Button b = new com.wizatar08.helperlibrary.ui.Button(name, buttonTextures, 0, 0, rots, text);
            setButton(b);
            if (b.getText() != null) {
                if (!centerW) {
                    b.getText().setX(b.getX() + b.getText().getX());
                } else {
                    b.getText().setX(b.getX() + (float) b.getWidth() / 2 - (b.getText().getWidth() / 2));
                }
                if (!centerH) {
                    b.getText().setY(b.getY() + b.getText().getY());
                } else {
                    b.getText().setY(b.getY() + (float) b.getHeight() / 2 - (b.getText().getHeight() / 2));
                }
            }
        }

        /**
         * Editor UI
         * @param name
         * @param buttonTexture
         * @param rot
         */
        public void addButton(String name, com.wizatar08.helperlibrary.visuals.Tex[] buttonTexture, int[] rot) {
            com.wizatar08.helperlibrary.ui.Button b = new Button(name, buttonTexture, 0, 0, rot);
            setButton(b);
        }

        private void setButton(com.wizatar08.helperlibrary.ui.Button b) {
            if (optionsWidth != 0) {
                b.setY(y + (buttonAmount / optionsWidth) * yDist);
            }
            b.setX(x + (buttonAmount % optionsWidth) * (width / optionsWidth));
            buttonAmount++;
            menuButtons.add(b);
        }

        public boolean isButtonClicked(String buttonName) {
            if (org.lwjgl.input.Mouse.isButtonDown(0)) {
                return isButtonHovered(buttonName);
            }
            return false;
        }

        public boolean isButtonHovered(String buttonName) {
            try {
                com.wizatar08.helperlibrary.ui.Button b = getButton(buttonName);
                float mouseY = (HEIGHT - org.lwjgl.input.Mouse.getY() - 1) - (org.lwjgl.opengl.Display.getHeight() - ((float) HEIGHT * stretchedMultiplierTotal) - (org.lwjgl.opengl.Display.getHeight() - HEIGHT));
                return
                        (
                                org.lwjgl.input.Mouse.getX() > ((float) b.getX() * stretchedMultiplierTotal) &&
                                        org.lwjgl.input.Mouse.getX() < (((float) b.getX() + b.getWidth()) * stretchedMultiplierTotal) &&
                                        mouseY > ((float) b.getY() * stretchedMultiplierTotal) &&
                                        mouseY < (((float) b.getY() + b.getHeight()) * stretchedMultiplierTotal)
                        ) && show;
            } catch (NullPointerException e) {
                return false;
            }
        }

        private com.wizatar08.helperlibrary.ui.Button getButton(String buttonName) {
            for (com.wizatar08.helperlibrary.ui.Button b: menuButtons) {
                if (b.getName().equals(buttonName)) {
                    return b;
                }
            }
            return null;
        }

        public void draw() {
            if (show) {
                for (com.wizatar08.helperlibrary.ui.Button b : menuButtons) {
                    for (int i = 0; i < b.getTextures().length; i++) {
                        if (i == 0) {
                            b.getTextures()[0].draw(b.getX(), b.getY());
                        } else {
                            b.getTextures()[i].setAngle(b.getRots()[i - 1]).draw(b.getX(), b.getY());
                        }
                    }
                    if (b.getText() != null) {
                        b.getText().draw();
                        blank.draw();
                    }
                }
            }
        }

        public String getName() {
            return name;
        }

        public void showMenu(boolean showMenu) {
            show = showMenu;
        }

        public ArrayList<com.wizatar08.helperlibrary.ui.Button> getButtons() {
            return menuButtons;
        }

        public ArrayList<com.wizatar08.helperlibrary.visuals.TextBlock> getTextBlocks() {
            return textList;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}