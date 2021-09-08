package com.wizatar08.helperlibrary.screen;

public class Coordinate {
    private double x, y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceBetween(Coordinate otherCoord) {
        double a = this.x - otherCoord.getX();
        double b = this.y - otherCoord.getY();
        return Math.floor((a * a) + (b * b));
    }

    public Coordinate getVectorCoordinate(double angle, double distance) {
        double xMultiplyer = Math.cos(angle);
        double yMultiplyer = Math.sin(angle);
        return new Coordinate(x + (xMultiplyer * distance), y + (yMultiplyer * distance));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
