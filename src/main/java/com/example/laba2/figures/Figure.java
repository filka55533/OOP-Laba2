package com.example.laba2.figures;

public abstract class Figure {
    public Point startPoint;
    public abstract double getLength();
    public abstract boolean isItFigurePoint(double x, double y);

    Figure(Point point) {
        this.startPoint = point;
    }

    public static double getDistanceBetweenTwoPoints(Point startPoint, Point endPoint){
        double x = startPoint.x - endPoint.x;
        double y = startPoint.y - endPoint.y;
        return Math.sqrt(x*x + y*y);
    }
}
