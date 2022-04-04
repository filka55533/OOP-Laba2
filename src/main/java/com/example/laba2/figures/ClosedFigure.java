package com.example.laba2.figures;

public abstract class ClosedFigure extends Figure{
    ClosedFigure(Point startPoint){
        super(startPoint);
    }
    public abstract double getArea();
}
