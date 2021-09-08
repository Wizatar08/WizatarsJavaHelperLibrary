package com.wizatar08.helperlibrary.time;

public class Clock {
    private static boolean paused = false;
    public static long lastFrame, totalTime;
    public static float d = 0, multiplier = 1;
    public static int SET_FPS;

    /**
     * Get the current time
     * @return
     */
    public static long getTime() {
        return org.lwjgl.Sys.getTime() * 1000 / org.lwjgl.Sys.getTimerResolution();
    }
    public static float getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        if (delta * 0.001f > 0.05f) {
            return 0.05f;
        }
        return delta * 0.001f;
    }

    /**
     * Get the time between the current frame and the previous frame
     * @return
     */
    public static float Delta() {
        if(paused) return 0;
        else return d * multiplier;
    }

    /**
     * Get the total time the program is running
     * @return
     */
    public static float totalTime(){
        return totalTime;
    }

    /**
     * Constant update
     */
    public static void update() {
        d = getDelta();
        totalTime += d;
    }
}