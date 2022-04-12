package com.example.laba2.figures;

public class Segment extends OpenFigure{
    public double radius;
    private boolean isItDown;
    private Point midCord;
    public Segment(Point startPoint, Point endPoint, double radius, boolean isItDown){
        super(startPoint, endPoint);
        this.radius = radius;
        this.isItDown = isItDown;
        midCord = getMid(startPoint, endPoint, radius, isItDown);
    }

    public boolean getIsItDown(){
        return isItDown;
    }

    public Point getMid(Point pnt1, Point pnt2, double radius, boolean isItDown){
        double d = Math.sqrt(Math.pow(pnt1.x - pnt2.x, 2) + Math.pow(pnt1.y - pnt2.y, 2));
        double h = Math.sqrt(radius*radius - d*d/4);
        Point res = new Point(0,0);
        int ed = isItDown ? 1 : -1;
        res.x = (pnt1.x + pnt2.x)/2 - h * ed * Math.abs(pnt1.y - pnt2.y) / d;
        res.y = (pnt1.y + pnt2.y)/2 + h * ed * Math.abs(pnt1.x - pnt2.x) / d;
        return res;
    }


    @Override
    public double getLength() {
        //Find cosine of the central angle
        double cosAngle = 1 - Math.pow(getDistanceBetweenTwoPoints(startPoint, endPoint)/radius, 2)/2;
        return Math.acos(cosAngle)*radius;
    }

    @Override
    public boolean isItFigurePoint(double x, double y) {
        double precision = 20.0;
        boolean isItInInterval = ((startPoint.x <= x && endPoint.x >= x) ||
                                  (endPoint.x <= x && startPoint.x >= x)) &&
                                 ((startPoint.y <= y && endPoint.y >= y) ||
                                  (endPoint.y <= y && startPoint.y >= y));
        x = Math.pow(x - midCord.x, 2);
        y = Math.pow(y - midCord.y, 2);
        y = Math.abs(radius*radius - x - y);
        return isItInInterval && y <= precision ;
    }
}
