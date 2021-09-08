package com.wizatar08.helperlibrary.game.screenentity;

public class Hitbox {
    private float x, y, width, height;
    private com.wizatar08.helperlibrary.game.screenentity.Entity object;

    /**
     * Initialize a Hitbox on an entity
     * @param object - The entity the hitbox should attach to
     * @param x - The x offset from the top left corner of th entity
     * @param y - The y offset from the top left corner of the entity
     * @param width - The width of the hitbox
     * @param height - The height of the hitbox
     */
    public Hitbox(com.wizatar08.helperlibrary.game.screenentity.Entity object, float x, float y, float width, float height) {
        this.object = object;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Detects if this hitbox is touching another entity's hitbox
     * @param entity
     * @return
     */
    public boolean collidesWith(com.wizatar08.helperlibrary.game.screenentity.Entity entity) {
        return checkCollision(entity.getHitbox().getX() + entity.getX(), entity.getHitbox().getY() + entity.getY(), entity.getHitbox().getWidth(), entity.getHitbox().getHeight());
    }

    /**
     * Detects if this hitbox is touching a point
     * @param coordinate
     * @return
     */
    public boolean intersectsWith(com.wizatar08.helperlibrary.screen.Coordinate coordinate) {
        return checkCollision((float) coordinate.getX(), (float) coordinate.getY(), 1, 1);
    }

    /**
     * Detect if this hitbox intersects with a rectangular area
     * @param xPos
     * @param yPos
     * @param areaWidth
     * @param areaHeight
     * @return
     */
    private boolean checkCollision(float xPos, float yPos, float areaWidth, float areaHeight) {
        return x + width > xPos && x < xPos + areaWidth && y + height > yPos && y < yPos + areaHeight;
    }

    /**
     * Get the relative x value of this hitbox
     * @return
     */
    public float getX() {
        return x;
    }

    /**
     * Get the relative y value of this hitbox
     * @return
     */
    public float getY() {
        return y;
    }

    /**
     * Get the width of this hitbox
     * @return
     */
    public float getWidth() {
        return width;
    }

    /**
     * Get the height of this hitbox
     * @return
     */
    public float getHeight() {
        return height;
    }
}