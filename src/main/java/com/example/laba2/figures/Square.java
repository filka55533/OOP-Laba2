package com.example.laba2.figures;

public class Square extends Rectangle{

    public Square(Point startPoint, double width){
        super(startPoint, width, width);
    }

    @Override
    public double getLength() {
        return 4 * width;
    }

    @Override
    public double getArea() {
        return width * width;
    }
}
