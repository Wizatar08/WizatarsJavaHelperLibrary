package com.wizatar08.helperlibrary.visuals;

public class AnimatedTex extends com.wizatar08.helperlibrary.visuals.Tex {
    private final int imageHeight, totalFrames;
    private int frame;
    private boolean fade, playAnim;
    private float secondsBetweenFrames;
    private final com.wizatar08.helperlibrary.time.Timer timer;
    private org.newdawn.slick.opengl.Texture texture;
    private String texturePath;
    private AnimationSettings animationManager;

    /**
     * Creates an animated texture with default values
     * @param textureName
     * @param imageHeight
     */
    public AnimatedTex(String textureName, int imageHeight) {
        this(textureName, imageHeight, 1);
    }

    /**
     * Creates a sudden transition animated texture where you can specify seconds between frames
     * @param textureName
     * @param imageHeight
     * @param secondsBetweenFrames
     */
    public AnimatedTex(String textureName, int imageHeight, float secondsBetweenFrames) {
        this(textureName, imageHeight, secondsBetweenFrames, false);
    }

    /**
     * Creates an animated texture with height, seconds and fade options
     * @param textureName
     * @param imageHeight
     * @param secondsBetweenFrames
     * @param fade
     */
    public AnimatedTex(String textureName, int imageHeight, float secondsBetweenFrames, boolean fade) {
        this(textureName, imageHeight, secondsBetweenFrames, fade, AnimationSettings.LOOP_FOREVER);
    }

    /**
     * Create an animated texture with all customization options
     * @param textureName
     * @param imageHeight
     * @param secondsBetweenFrames
     * @param fade
     * @param animationSettings
     */
    public AnimatedTex(String textureName, int imageHeight, float secondsBetweenFrames, boolean fade, AnimationSettings animationSettings) {
        super(textureName);
        this.texture = com.wizatar08.helperlibrary.visuals.Drawer.LoadPNG(textureName);
        this.texturePath = textureName;
        this.animationManager = animationSettings;
        this.imageHeight = imageHeight;
        this.secondsBetweenFrames = secondsBetweenFrames;
        this.fade = fade;
        this.timer = new com.wizatar08.helperlibrary.time.Timer(com.wizatar08.helperlibrary.time.Timer.TimerModes.COUNT_DOWN, secondsBetweenFrames);
        this.totalFrames = (int) Math.floor(texture.getImageHeight() / imageHeight);
        if (!this.animationManager.backwards) {
            this.frame = 0;
        } else {
            this.frame = totalFrames - 1;
        }
        this.timer.unpause();
        this.playAnim = this.animationManager != AnimationSettings.STOP_AT_FIRST_FRAME;
    }

    /**
     * ANimated texture animation settings
     */
    public enum AnimationSettings {
        PLAY_ONCE(false, false),
        LOOP_FOREVER(true, false),
        PLAY_BACKWARDS_ONCE(false, true),
        PLAY_BACKWARDS_FOREVER(true, true),
        STOP_AT_FIRST_FRAME(false, false);

        private boolean runAgain, backwards;

        AnimationSettings(boolean runAgain, boolean backwards) {
            this.runAgain = runAgain;
            this.backwards = backwards;
        }

        public boolean keepsRunning() {
            return runAgain;
        }
        public boolean runsBackwards() {
            return backwards;
        }
    }

    /**
     * Get the next frame number
     * @return
     */
    public int getNextFrameNum() {
        int f = frame;
        f++;
        if (f >= totalFrames) {
            f = 0;
        }
        return f;
    }

    /**
     * Get the previous frame number
     * @return
     */
    public int getBackwardsFrameNum() {
        int f = frame;
        f--;
        if (f < 0) {
            f = totalFrames - 1;
        }
        return f;
    }

    /**
     * Set the animation frame
     * @param frameNumber
     */
    public void goToFrame(int frameNumber) {
        if (frameNumber > 0 && frameNumber <= totalFrames) {
            frame = frameNumber - 1;
        }
    }

    /**
     * Play the animation
     */
    public void startPlayingAnimation() {
        playAnim = true;
    }

    /**
     * Stop the animation
     */
    public void stopPlayingAnimation() {
        playAnim = false;
    }

    /**
     * Change the animation settings
     * @param settings
     */
    public void setAnimationSettings(AnimationSettings settings) {
        playAnim = true;
        animationManager = settings;
        animationManager.backwards = settings.backwards;
        animationManager.runAgain = settings.runAgain;
    }

    /**
     * Draw the texture
     * @param x - X position
     * @param y - Y position
     */
    @Override
    public void draw(float x, float y) {
        float calculatedTopY = super.getCropTopY() * (1.0f / totalFrames);
        float calculatedBottomY = super.getCropBottomY() * (1.0f / totalFrames);
        if (playAnim) {
            timer.update();
        }
        if (animationManager == AnimationSettings.STOP_AT_FIRST_FRAME) {
            frame = 0;
            playAnim = false;
            timer.setTime(timer.getStartingSeconds());
        }
        if (timer.getTotalSeconds() <= 0 || timer.getTotalSeconds() > 256) {
            timer.setTime(timer.getStartingSeconds());
            if (!animationManager.backwards) {
                frame = getNextFrameNum();
            } else {
                frame = getBackwardsFrameNum();
            }
        }
        com.wizatar08.helperlibrary.visuals.Drawer.drawQuadTex(texture, x, y, super.getCustomStretchWidth(), imageHeight * (super.getCropBottomY() - super.getCropTopY()), super.getAngle(), calculatedTopY + ((1.0f / totalFrames) * frame - 1), calculatedBottomY + ((1.0f / totalFrames) * frame - 1), super.getCropLeftX(), super.getCropRightX(), super.getColor().r, super.getColor().g, super.getColor().b, super.getColor().a);
        if (fade) {
            if (!animationManager.backwards) {
                com.wizatar08.helperlibrary.visuals.Drawer.drawQuadTex(texture, x, y, super.getCustomStretchWidth(), imageHeight * (super.getCropBottomY() - super.getCropTopY()), super.getAngle(), calculatedTopY + ((1.0f / totalFrames) * getNextFrameNum()), calculatedBottomY + ((1.0f / totalFrames) * getNextFrameNum()), super.getCropLeftX(), super.getCropRightX(), super.getColor().r, super.getColor().g, super.getColor().b, super.getColor().a * ((timer.getStartingSeconds() - timer.getTotalSeconds()) / timer.getStartingSeconds()));
            } else {
                com.wizatar08.helperlibrary.visuals.Drawer.drawQuadTex(texture, x, y, super.getCustomStretchWidth(), imageHeight * (super.getCropBottomY() - super.getCropTopY()), super.getAngle(), calculatedTopY + ((1.0f / totalFrames) * getBackwardsFrameNum()), calculatedBottomY + ((1.0f / totalFrames) * getBackwardsFrameNum()), super.getCropLeftX(), super.getCropRightX(), super.getColor().r, super.getColor().g, super.getColor().b, super.getColor().a * ((timer.getStartingSeconds() - timer.getTotalSeconds()) / timer.getStartingSeconds()));
            }
        }
        if (!animationManager.runAgain) {
            if ((frame + 1) >= totalFrames && !animationManager.backwards) {
                stopPlayingAnimation();
            } else if ((frame + 1) <= 1 && animationManager.backwards) {
                stopPlayingAnimation();
            }
        }
    }

    /**
     * Get the height of the image
     * @return
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * Get the calculated seconds between frames
     * @return
     */
    public float getSecondsBetweenFrames() {
        return secondsBetweenFrames;
    }

    /**
     * Specify whether the animation fades between frames
     * @return
     */
    public boolean isFading() {
        return fade;
    }

    /**
     * Get the current frame
     * @return
     */
    public int getFrame() {
        return frame + 1;
    }

    /**
     * Get the total number of frames
     * @return
     */
    public int getTotalFrames() {
        return totalFrames;
    }

    /**
     * Specify whether the animation is playing
     * @return
     */
    public boolean isPlaying() {
        return playAnim;
    }

    /**
     * Get the current animation settings
     * @return
     */
    public AnimationSettings getSettings() {
        return animationManager;
    }

    /**
     * Create a new instance of this AnimatedTex()
     * @return
     */
    @Override
    public AnimatedTex copy() {
        return new AnimatedTex(texturePath, imageHeight, secondsBetweenFrames, fade, animationManager);
    }
}