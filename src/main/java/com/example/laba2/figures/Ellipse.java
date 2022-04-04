package com.example.laba2.figures;

public class Ellipse extends SecondOrderFigures{
    public Ellipse(Point startPoint, Point endPoint){
        super(startPoint, endPoint);
    }

    @Override
    public boolean isItFigurePoint(double x, double y){
        double precision = 0.5;
        double a = Math.abs(endPoint.x - startPoint.x)/2;
        double b = Math.abs(endPoint.y - startPoint.y)/2;
        a = (b * Math.sqrt(1 - Math.pow((x - a)/a, 2)));
        return Math.abs(y - (a + b)) <= precision || Math.abs(y - (-a + b)) <= precision;
    }

    public double[] getYCoordinates(double x) {
        double a = Math.abs(endPoint.x - startPoint.x)/2;
        double b = Math.abs(endPoint.y - startPoint.y)/2;
        double y = b * Math.sqrt(1 - Math.pow((x - a)/a, 2));
        double[] res = new double[y == 0.0 ? 1 : 2];
        res[0] = y + b;
        if (y != 0.0) res[1] = -y + b;
        return res;
    }


    @Override
    public double getArea() {
        return Math.PI * Math.abs(startPoint.x - endPoint.x) * Math.abs(startPoint.y - endPoint.y);
    }

    @Override
    public double getLength() {
        double a = Math.abs(startPoint.x - endPoint.x);
        double b = Math.abs(startPoint.y - endPoint.y);
        double ecc = (a*a - b*b)/a/a;
        double res = 0;
        int count = 1;
        double prevRes;
        do{
            prevRes = res;
            res = 0;
            count *= 2;
            for (int i = 0; i < count; i++)
                res += Math.sqrt(1 - ecc * Math.pow(Math.sin(Math.PI/ 2 /count*i),2));
        } while (Math.abs(res - prevRes) >= 10e-5 / 4);
        return res * 4 * a;
    }
}
