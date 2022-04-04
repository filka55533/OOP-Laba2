package com.example.laba2.figures;

public abstract class SecondOrderFigures extends ClosedFigure{
    Point endPoint;
    SecondOrderFigures(Point startPoint, Point endPoint){
        super(startPoint);
        this.endPoint = endPoint;
    }
}
