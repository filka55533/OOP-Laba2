package com.example.laba2.figures;

public abstract class OpenFigure extends Figure{
    protected Point endPoint;
    OpenFigure(Point startPoint, Point endPoint){
        super(startPoint);
        this.endPoint = endPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}
