package com.example.laba2.figures;

public class PolyLine extends OpenFigure {
    protected Point[] points;
    public PolyLine(Point ... points){
        super(points[0], points[points.length - 1]);
        this.points = points.clone();
    }

    @Override
    public double getLength() {
        double res = 0.0;
        for (int i = 0; i < points.length - 1; i++)
            res += getDistanceBetweenTwoPoints(points[i], points[i + 1]);

        return res;
    }

    @Override
    public boolean isItFigurePoint(double x, double y) {
        double precision = 10e-3;
        for (int i = 0; i < points.length - 1; i++){
            double dividedX = (x - points[i].x)/(points[i].x - points[i + 1].x);
            double dividedY = (y - points[i].y)/(points[i].y - points[i + 1].y);
            boolean isItInInterval = ((points[i].x <= x && points[i + 1].x >= x) ||
                                      (points[i + 1].x <= x && points[i].x >= x)) &&
                                     ((points[i].y <= y && points[i + 1].y >= y) ||
                                      (points[i + 1].y <= y && points[i].y >= y));
            if (isItInInterval && (Math.abs(dividedX - dividedY) <= precision))
                return true;
        }
        return false;
    }
}

