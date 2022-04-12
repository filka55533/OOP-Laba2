package com.example.laba2;


import com.example.laba2.figures.*;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

interface Initializing{
    void run();
}

interface Drawing{
    void draw();
}

public class HelloController {

    private final double STEP = 10e-3;
    Initializing initMethod;
    Drawing drawMethod;
    private int pointsCount;
    private boolean isWaitRightClick = false;
    private Figure figure;
    private ArrayList<Point> points;
    private void clearPoints(){
        points = new ArrayList<>();
    }

    @FXML
    private Canvas canvas;
    public Canvas getCanvas() {
        return canvas;
    }

    public double getVerticalOffset(){
        return mbFigure.getLayoutY() + mbFigure.getHeight() + 50.0;
    }

    @FXML
    private Pane paneRoot;
    public Pane getPaneRoot() {
        return paneRoot;
    }

    @FXML
    private MenuButton mbFigure;
    public MenuButton getMbFigure() {
        return mbFigure;
    }

    @FXML
    private MenuItem miCircle, miEllipse, miPolyLine, miRectangle, miSegment, miSquare, miTriangle;


    public void setMbFigureText(String s){
        mbFigure.setText(s);
    }

    @FXML
    public void onCircleAction() {
        isWaitRightClick = false;
        setMbFigureText(miCircle.getText());
        clearPoints();
        pointsCount = 2;
        initMethod = this::initCircle;
        drawMethod = this::drawCircle;

    }
    @FXML
    public void onEllipseAction() {
        isWaitRightClick = false;
        setMbFigureText(miEllipse.getText());
        clearPoints();
        pointsCount = 2;
        initMethod = this::initEllipse;
        drawMethod = this::drawEllipse;
    }
    @FXML
    public void onTriangleAction() {
        isWaitRightClick = false;
        setMbFigureText(miTriangle.getText());
        clearPoints();
        pointsCount = 3;
        initMethod = this::initTriangle;
        drawMethod = this::drawTriangle;
    }
    @FXML
    public void onSquareAction() {
        isWaitRightClick = false;
        setMbFigureText(miSquare.getText());
        clearPoints();
        pointsCount = 2;
        initMethod = this::initSquare;
        drawMethod = this::drawRectangle;
    }
    @FXML
    public void onPolyLineAction() {
        setMbFigureText(miPolyLine.getText());
        clearPoints();
        isWaitRightClick = true;
        initMethod = this::initPolyLine;
        drawMethod = this::drawPolyLine;
    }
    @FXML
    public void onRectangleAction() {
        setMbFigureText(miRectangle.getText());
        clearPoints();
        pointsCount = 2;
        initMethod = this::initRectangle;
        drawMethod = this::drawRectangle;
    }
    @FXML
    public void onSegmentAction() {
        isWaitRightClick = false;
        setMbFigureText(miSegment.getText());
        clearPoints();
        pointsCount = 3;
        initMethod = this::initSegment;
        drawMethod = this::drawSegment;
    }
    @FXML
    public void onCanvasClick(MouseEvent mouseEvent) {
        Point pnt = new Point(mouseEvent.getX(), mouseEvent.getY());
        points.add(pnt);
        if (isWaitRightClick){
            if (mouseEvent.getButton() != MouseButton.SECONDARY) return;
            initMethod.run();
//            drawFigure();
            drawMethod.draw();
            clearPoints();
        }
        else if(mbFigure.getText() != "")  {
            if (pointsCount <= points.toArray().length) {
                initMethod.run();
//                drawFigure();
                drawMethod.draw();
                clearPoints();
            }
        }
    }

    private void drawTriangle(){
        Triangle trg = (Triangle) figure;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        Point[] points = trg.getPoints();
        gc.moveTo(points[0].x, points[0].y);
        for (int i = 0; i <= points.length; i++)
            gc.lineTo(points[i % points.length].x, points[i % points.length].y);

        gc.stroke();
    }

    private void drawCircle(){
        double rad = ((Circle)figure).getRadius();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        gc.moveTo(figure.startPoint.x + rad, figure.startPoint.y );
        for (double i = 0; i < 2 * Math.PI; i += STEP / rad){
            double x = rad * Math.cos(i) + figure.startPoint.x;
            double y = rad * Math.sin(i) + figure.startPoint.y;
            gc.lineTo(x, y);
        }
        gc.stroke();
    }

    private void drawEllipse(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        double a = Math.abs(figure.startPoint.x - ((Ellipse)figure).getEndPoint().x) / 2.0;
        double b = Math.abs(figure.startPoint.y - ((Ellipse)figure).getEndPoint().y) / 2.0;
        gc.moveTo( figure.startPoint.x + 2 * a,  figure.startPoint.y + b);
        for (double i = 0; i < 2 * Math.PI; i += STEP){
            double x = a * Math.cos(i) + figure.startPoint.x + a;
            double y = b * Math.sin(i) + figure.startPoint.y + b;
            gc.lineTo(x, y);
        }
        gc.stroke();
    }

    private void drawPolyLine(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        gc.moveTo(points.get(0).x, points.get(0).y);
        for (int i = 1; i < points.toArray().length; i++){
            Point point = points.get(i);
            gc.lineTo(point.x, point.y);
        }
        gc.stroke();
    }

    private void drawRectangle(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(2);
        gc.setFill(Color.BLACK);
        Rectangle rect = (Rectangle)figure;
        double width = rect.getWidth(), height = rect.getHeight();
        double x = rect.startPoint.x, y = rect.startPoint.y;
        gc.moveTo(x, y);
        for (int i = 0; i < 4; i++){
            x = (i <= 1) ? rect.startPoint.x + width : rect.startPoint.x;
            y = (i >= 1 && i <= 2) ? rect.startPoint.y + height : rect.startPoint.y;
            gc.lineTo(x, y);
        }
        gc.stroke();
    }

    private void drawSegment(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        Segment seg = (Segment)figure;
        Point midPnt = seg.getMid(seg.startPoint, seg.getEndPoint(), seg.radius, seg.getIsItDown());
        double startAngle = Math.atan((midPnt.y - seg.startPoint.y)/(midPnt.x - seg.startPoint.x));
        double endAngle = Math.atan((midPnt.y - seg.getEndPoint().y)/(midPnt.x - seg.getEndPoint().x));

        if (endAngle < startAngle) {
            startAngle += endAngle;
            endAngle = startAngle - endAngle;
            startAngle = startAngle - endAngle;
        }
        for (double i = startAngle; i < endAngle; i += STEP){
            double x = midPnt.x + seg.radius * Math.cos(i);
            double y = midPnt.y + seg.radius * Math.sin(i);
            if (i - startAngle < STEP)
                gc.moveTo(x, y);
            gc.lineTo(x, y);
        }

        gc.stroke();
    }

    private void initCircle(){
        double radius = Figure.getDistanceBetweenTwoPoints(points.get(0), points.get(1));
        figure = new Circle(points.get(0), radius);
    }

    private void initEllipse(){
        figure = new Ellipse(points.get(0), points.get(1));
    }

    private void initPolyLine(){
        Point[] pnt = new Point[points.toArray().length];
        pnt = points.toArray(pnt);
        figure = new PolyLine(pnt);
    }

    private void initRectangle(){
        double width = Math.abs(points.get(0).x - points.get(1).x);
        double height = Math.abs(points.get(0).y - points.get(1).y);
        figure = new Rectangle(points.get(0), width, height);
    }

    private void initSegment(){
        boolean isItDown = points.get(1).y < points.get(2).y ? false : true;
        double y = Math.abs(points.get(1).y - points.get(0).y)/2.0;
        double x = Math.abs(points.get(1).x - points.get(0).x)/2.0;
        Point midPnt = new Point(x, y);
        double radius = Figure.getDistanceBetweenTwoPoints(midPnt, points.get(2));
        figure = new Segment(points.get(0), points.get(1), radius, isItDown);
    }

    private void initSquare(){
        double width = Figure.getDistanceBetweenTwoPoints(points.get(1), points.get(0));
        figure = new Square(points.get(0), width);
    }

    private void initTriangle(){
        figure = new Triangle(points.get(0), points.get(1), points.get(2));
    }

    public void drawFigure(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setLineWidth(2);
        double step = 10e-2;
        for (double x = 0; x < canvas.getWidth(); x += step) {
            for (double y = 0; y < canvas.getHeight(); y += step)
                if (figure.isItFigurePoint(x, y)){
                    gc.moveTo(x, y);
                    gc.lineTo(x, y);
                }
        }
        gc.stroke();
    }

}