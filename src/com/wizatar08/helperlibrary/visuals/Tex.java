package com.wizatar08.helperlibrary.visuals;

public class Tex {
    private org.newdawn.slick.opengl.Texture texture;
    private String texturePath;
    private float angle;
    private float leftX, rightX, topY, bottomY;
    private int customWidth;
    private org.newdawn.slick.Color color;

    /**
     * Creates a texture;
     * @param textureName
     */
    public Tex(String textureName) {
        this.texture = com.wizatar08.helperlibrary.visuals.Drawer.LoadPNG(textureName);
        this.texturePath = textureName;
        this.angle = 0;
        this.leftX = 0;
        this.rightX = 1;
        this.topY = 0;
        this.bottomY = 1;
        this.customWidth = texture.getImageWidth();
        this.color = new org.newdawn.slick.Color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    /**
     * Draw the texture in a certain area
     * @param x X position
     * @param y Y position
     */
    public void draw(float x, float y) {
        com.wizatar08.helperlibrary.visuals.Drawer.drawQuadTex(texture, x, y, customWidth, texture.getImageHeight() * (bottomY - topY), angle, topY, bottomY, leftX, rightX, color.r, color.g, color.b, color.a);
    }

    public Tex setAngle(float angle) {
        this.angle = angle;
        return this;
    }

    public Tex setImageCrop(float startXPercent, float endXPercent, float startYPercent, float endYPercent) {
        this.leftX = startXPercent;
        this.rightX = endXPercent;
        this.topY = startYPercent;
        this.bottomY = endYPercent;
        return this;
    }

    public Tex setCustomStretchingWidth(int width) {
        this.customWidth = width;
        return this;
    }

    public Tex setColorTint(org.newdawn.slick.Color color) {
        this.color = color;
        return this;
    }

    public float getAngle() {
        return angle;
    }

    public float getCropLeftX() {
        return leftX;
    }

    public float getCropRightX() {
        return rightX;
    }

    public float getCropTopY() {
        return topY;
    }

    public float getCropBottomY() {
        return bottomY;
    }

    public int getCustomStretchWidth() {
        return customWidth;
    }

    public org.newdawn.slick.Color getColor() {
        return color;
    }

    /**
     * Get the SLick Util texture
     * @return
     */
    public org.newdawn.slick.opengl.Texture getOpenGLTex() {
        return texture;
    }

    /**
     * Get the texture path
     * @return
     */
    public String getTexturePath() {
        return texturePath;
    }

    /**
     * Create a new instance of this Tex()
     * @return
     */
    public com.wizatar08.helperlibrary.visuals.Tex copy() {
        return new com.wizatar08.helperlibrary.visuals.Tex(texturePath);
    }
}