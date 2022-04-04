package com.example.laba2.figures;

public class Circle extends Ellipse{
    public Circle(Point startPoint, double radius){
        super(startPoint, new Point(startPoint.x + radius, startPoint.y + radius));
    }

    public double getRadius(){ return Math.abs(startPoint.x - endPoint.x); }

    @Override
    public double getLength() {
        return 2 * Math.PI * getRadius();
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }

}
