package com.example.laba2.figures;

public class Rectangle extends Polygon{
    protected double width, height;
    public Rectangle(Point startPoint, double width, double height){
        super(startPoint,
              new Point(startPoint.x + width, startPoint.y),
              new Point(startPoint.x + width, startPoint.y + height),
              new Point(startPoint.x, startPoint.y + height)
        );
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    @Override
    public double getLength() {
        return 2 * (width + height);
    }

    @Override
    public double getArea() {
        return width * height;
    }

}
