package com.wizatar08.helperlibrary.control;

import com.sun.istack.internal.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Control {
    public static boolean WINDOW_OPEN = false;

    public static void beginGameLoop(@NotNull Object classToInvoke, Method updateMethod) {
        if (!WINDOW_OPEN) {
            com.wizatar08.helperlibrary.screen.Renderer.openScreen("Window", 1900, 1200);
        }
        while(!org.lwjgl.opengl.Display.isCloseRequested()) { // While the program has not been requested to close
            com.wizatar08.helperlibrary.time.Clock.update();
            com.wizatar08.helperlibrary.screen.Renderer.update();
            com.wizatar08.helperlibrary.time.Clock.SET_FPS = 60;
            org.lwjgl.opengl.Display.update();
            org.lwjgl.opengl.Display.sync(com.wizatar08.helperlibrary.time.Clock.SET_FPS); // Set FPS
            try {
                updateMethod.invoke(classToInvoke);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        org.lwjgl.opengl.Display.destroy(); // Destroy (close) display
    }

    public static void quitProgram() {
        org.lwjgl.opengl.Display.destroy();
        System.exit(0);
    }

    public static boolean keyDown(int lwjglKeyCode) {
        return (org.lwjgl.input.Keyboard.getEventKey() == lwjglKeyCode) && (org.lwjgl.input.Keyboard.getEventKeyState());
    }
}
