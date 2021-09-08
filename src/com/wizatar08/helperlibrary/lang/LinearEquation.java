package com.wizatar08.helperlibrary.lang;

public class LinearEquation {
    private double slope, yIntersect;

    public LinearEquation(double slope, double yIntersect) {
        this.slope = slope;
        this.yIntersect = yIntersect;
    }

    public double getY(double x) {
        return (x * slope) + yIntersect;
    }

    public double getX(double y) {
        return (y - yIntersect) / slope;
    }

    public double getYDifferenceBetween(double x1, double x2) {
        double y1 = getY(x1);
        double y2 = getY(x2);
        return -(y1 - y2);
    }

    public double getXIntersect() {
        return getX(0);
    }

    public boolean intersectsWith(com.wizatar08.helperlibrary.screen.Coordinate coordinate) {
        return
                (getX(coordinate.getY()) == coordinate.getX()) &&
                (getY(coordinate.getX()) == coordinate.getY());
    }

    public double getSlope() {
        return slope;
    }

    public double getYIntersect() {
        return yIntersect;
    }
}
