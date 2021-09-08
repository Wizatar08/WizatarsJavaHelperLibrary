package com.wizatar08.helperlibrary.time;

public class Timer {
    private TimerModes mode;
    private float totalSeconds;
    private final float startingSeconds;
    private boolean paused;

    /**
     * Create a stopwatch
     */
    public Timer() {
        this(TimerModes.COUNT_UP);
    }

    /**
     * Create a timer at zero seconds
     * @param mode
     */
    public Timer(TimerModes mode) {
        this(mode, 0);
    }

    /**
     * Create a count down timer specifying the hours, minutes and seconds
     * @param hours
     * @param minutes
     * @param seconds
     */
    public Timer(int hours, int minutes, int seconds) {
        this(TimerModes.COUNT_DOWN, calculateTotalSeconds(hours, minutes, seconds));
    }

    /**
     * Create a count down timer specifying the total seconds
     * @param totalSeconds
     */
    public Timer(float totalSeconds) {
        this (TimerModes.COUNT_DOWN, totalSeconds);
    }

    /**
     * Create any timer specifying the starting hours, minutes and seconds
     * @param mode
     * @param hours
     * @param minutes
     * @param seconds
     */
    public Timer(TimerModes mode, int hours, int minutes, int seconds) {
        this(mode, calculateTotalSeconds(hours, minutes, seconds));
    }

    /**
     * Create any timer specifying the total seconds
     * @param mode
     * @param totalSeconds
     */
    public Timer(TimerModes mode, float totalSeconds) {
        this.mode = mode;
        this.totalSeconds = totalSeconds;
        this.startingSeconds = totalSeconds;
        this.paused = true;
    }

    /**
     * Timer modes
     */
    public enum TimerModes {
        COUNT_UP,
        COUNT_DOWN,
    }

    /**
     * Get the total seconds in a timer
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public static int calculateTotalSeconds(int hours, int minutes, int seconds) {
        int hoursToSeconds = hours * 3600;
        int minutesToSeconds = minutes * 60;
        return hoursToSeconds + minutesToSeconds + seconds;
    }

    /**
     * Get the total hours (rounded down) remaining on a timer
     * @param totalSeconds
     * @return
     */
    public int totalHours(int totalSeconds) {
        return (int) Math.floor(totalSeconds / 3600);
    }

    /**
     * Get the total minutes (rounded down) remaining on a timer
     * @param totalSeconds
     * @return
     */
    public int totalMinutes(int totalSeconds) {
        int m = (int) Math.floor(totalSeconds / 60);
        while (m >= 60) {
            m -= 60;
        }
        return m;
    }

    /**
     * Get the timer displayed as a string
     * @return
     */
    public String getString() {
        float seconds;
        int h = totalHours((int) totalSeconds);
        seconds = totalSeconds % 3600;
        int m = totalMinutes((int) totalSeconds);
        seconds = seconds % 60;
        return h + ":" + toTwoDigits(m) + ":" + toTwoDigits((int) Math.floor(seconds));
    }


    private String toTwoDigits(int num) {
        String numAsString = String.valueOf(num);
        if (numAsString.length() == 1) {
            numAsString = "0" + numAsString;
        }
        return numAsString;
    }

    /**
     * Update the timer
     */
    public void update() {
        if (!paused) {
            if (mode == TimerModes.COUNT_DOWN) {
                totalSeconds -= com.wizatar08.helperlibrary.time.Clock.Delta();
            } else if (mode == TimerModes.COUNT_UP) {
                totalSeconds += com.wizatar08.helperlibrary.time.Clock.Delta();
            }
        }
    }

    /**
     * Pause the timer
     */
    public void pause() {
        paused = true;
    }

    /**
     * Unpause the timer
     */
    public void unpause() {
        paused = false;
    }

    /**
     * Inverse the current state of the timer
     */
    public void flipPause() {
        paused = !paused;
    }

    /**
     * Set the timer's total seconds
     * @param totalSeconds
     */
    public void setTime(float totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    /**
     * Set the timer using hours, minutes and seconds
     * @param hours
     * @param minutes
     * @param seconds
     */
    public void setTime(int hours, int minutes, int seconds) {
        this.totalSeconds = calculateTotalSeconds(hours, minutes, seconds);
    }

    /**
     * Reset the timer and pause it. If the timer is counting up (a stropwatch), the time will set to 0. If the timer is counting down, the time will set to its starting time
     */
    public void reset() {
        this.paused = true;
        if (mode == TimerModes.COUNT_DOWN) {
            this.totalSeconds = startingSeconds;
        } else if (mode == TimerModes.COUNT_UP) {
            this.totalSeconds = 0;
        }
    }

    /**
     * Detect if the specified total seconds is equal to the timer's total seconds
     * @param totalSeconds
     * @return
     */
    public boolean isAtTime(int totalSeconds) {
        return Math.floor(this.totalSeconds) == totalSeconds;
    }

    /**
     * Detect if the specified time is equal to the timer's total seconds
     * @param hours
     * @param minutes
     * @param seconds
     * @return
     */
    public boolean isAtTime(int hours, int minutes, int seconds) {
        return isAtTime(calculateTotalSeconds(hours, minutes, seconds));
    }

    /**
     * Get the total number of seconds in the timer
     * @return
     */
    public float getTotalSeconds() {
        return totalSeconds;
    }

    /**
     * Get the starting time
     * @return
     */
    public float getStartingSeconds() {
        return startingSeconds;
    }

    /**
     * Check if the timer is paused
     * @return
     */
    public boolean isPaused() {
        return paused;
    }
}