package com.example.laba2.figures;

public abstract class Polygon extends ClosedFigure{
    Point[] points;
    Polygon(Point ... points) throws NullPointerException{
        super(points[0]);
        this.points = points.clone();
    }

    @Override
    public boolean isItFigurePoint(double x, double y) {
        int len = points.length;
        double precision = 10e-3;
        for (int i = 0; i < len; i++) {
            boolean  isYEnter;
            boolean isItInInterval;
            if (Math.abs(points[i].x - points[(i + 1) % len].x) <= precision ||
                Math.abs(points[i].y - points[(i + 1) % len].y) <= precision) {
                isYEnter = true;
                if (Math.abs(points[i].x - points[(i + 1) % len].x) <= precision)
                    isItInInterval = ((points[i].y <= y && points[(i + 1) % len].y >= y) ||
                                     (points[(i + 1) % len].y <= y && points[i].y >= y)) &&
                                     (Math.abs(x - points[i].x) <= precision);
                else
                    isItInInterval = ((points[i].x <= x && points[(i + 1) % len].x >= x) ||
                                     (points[(i + 1) % len].x <= x && points[i].x >= x)) &&
                                     (Math.abs(y - points[i].y) <= precision);
            }
            else {
                double dividedX = (x - points[i].x) / (points[i].x - points[(i + 1) % len].x);
                double dividedY = (y - points[i].y) / (points[i].y - points[(i + 1) % len].y);
                isYEnter = Math.abs(dividedX - dividedY) <= precision;

                isItInInterval = ((points[i].x <= x && points[(i + 1) % len].x >= x) ||
                        (points[(i + 1) % len].x <= x && points[i].x >= x)) &&
                        ((points[i].y <= y && points[(i + 1) % len].y >= y) ||
                                (points[(i + 1) % len].y <= y && points[i].y >= y));

            }
            if (isItInInterval && isYEnter)
                return true;
        }
        return false;
    }
}
