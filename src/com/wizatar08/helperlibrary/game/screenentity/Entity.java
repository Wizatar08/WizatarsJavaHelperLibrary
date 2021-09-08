package com.wizatar08.helperlibrary.game.screenentity;

public class Entity {
    private float x, y, width, height;
    private com.wizatar08.helperlibrary.visuals.Tex texture;
    private com.wizatar08.helperlibrary.game.screenentity.Hitbox hitbox;

    public Entity(float x, float y, float width, float height, com.wizatar08.helperlibrary.visuals.Tex texture) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.hitbox = new Hitbox(this, x - (width / 2), y - (width / 2), width, height);
    }

    /**
     * Get X position
     * @return
     */
    public float getX() {
        return x;
    };

    /**
     * Get Y position
     */
    public float getY() {
        return y;
    };

    /**
     * Get width of entity
     */
    public float getWidth() {
        return width;
    };

    /**
     * Get height of entity
     */
    public float getHeight() {
        return height;
    };

    /**
     * Set X position of entity
     */
    public void setX(float x) {
        this.x = x;
    };

    /**
     * Set Y position of entity
     */
    public void setY(float y) {
        this.y = y;
    };

    /**
     * Set width of entity
     */
    public void setWidth(float width) {
        this.width = width;
    };

    /**
     * Set height of entity
     */
    public void setHeight(float height) {
        this.height = height;
    };

    /**
     * Draw the entity
     */
    public void draw() {
        texture.draw(x - ((float) texture.getOpenGLTex().getImageWidth() / 2), y - ((float) texture.getOpenGLTex().getImageHeight() / 2));
    };

    /**
     * Update the entity
     */
    public void update() {
        draw();
    };

    /**
     * Gets the hitbox of the entity
     */
    public com.wizatar08.helperlibrary.game.screenentity.Hitbox getHitbox() {
        return hitbox;
    };

    public com.wizatar08.helperlibrary.visuals.Tex getTex() {
        return texture;
    }
}