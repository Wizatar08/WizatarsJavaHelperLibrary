package com.wizatar08.helperlibrary.screen;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
    public static int WIDTH, HEIGHT;
    private static float stretchedMultiplierW;
    private static float stretchedMultiplierH;
    public static float stretchedMultiplierTotal;
    private static boolean shouldResizeWindow = true;

    public static void openScreen(String windowTitle, int windowWidth, int windowHeight) {
        com.wizatar08.helperlibrary.control.Control.WINDOW_OPEN = true;

        System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/lib/wizatarhelperlibs");

        WIDTH = windowWidth;
        HEIGHT = windowHeight;

        try {
            org.lwjgl.opengl.Display.setTitle(windowTitle);
            org.lwjgl.opengl.Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(WIDTH,HEIGHT));
            org.lwjgl.opengl.Display.setResizable(true);
            org.lwjgl.opengl.Display.create();
            org.lwjgl.opengl.Display.setVSyncEnabled(true);
        } catch (org.lwjgl.LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        glViewport(0, 0, WIDTH, HEIGHT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void shouldResizeWindow(boolean bool) {
        shouldResizeWindow = bool;
    }

    public static void update() {
        if (shouldResizeWindow) {
            stretchedMultiplierW = (org.lwjgl.opengl.Display.getWidth() / (float) WIDTH);
            stretchedMultiplierH = (org.lwjgl.opengl.Display.getHeight() / (float) HEIGHT);
            stretchedMultiplierTotal = Math.min(stretchedMultiplierW, stretchedMultiplierH);
        } else {
            stretchedMultiplierTotal = 1;
        }
        glViewport(0, 0, Math.round(WIDTH * stretchedMultiplierTotal), Math.round(HEIGHT * stretchedMultiplierTotal));
    }
}