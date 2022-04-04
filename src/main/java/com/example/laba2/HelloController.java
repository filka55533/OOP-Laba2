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

interface Drawing{
    void run();
}


public class HelloController {
    Drawing drawingMethod;
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
        setMbFigureText(miCircle.getText());
        clearPoints();
        pointsCount = 2;
        drawingMethod = this::initCircle;

    }
    @FXML
    public void onEllipseAction() {
        setMbFigureText(miEllipse.getText());
        clearPoints();
        pointsCount = 2;
        drawingMethod = this::initEllipse;

    }
    @FXML
    public void onTriangleAction() {
        setMbFigureText(miTriangle.getText());
        clearPoints();
        pointsCount = 3;
        drawingMethod = this::initTriangle;
    }
    @FXML
    public void onSquareAction() {
        setMbFigureText(miSquare.getText());
        clearPoints();
        pointsCount = 2;
        drawingMethod = this::initSquare;
    }
    @FXML
    public void onPolyLineAction() {
        setMbFigureText(miPolyLine.getText());
        clearPoints();
        isWaitRightClick = true;
        drawingMethod = this::initPolyLine;

    }
    @FXML
    public void onRectangleAction() {
        setMbFigureText(miRectangle.getText());
        clearPoints();
        pointsCount = 2;
        drawingMethod = this::initRectangle;
    }
    @FXML
    public void onSegmentAction() {
        setMbFigureText(miSegment.getText());
        clearPoints();
        pointsCount = 3;
        drawingMethod = this::initSegment;

    }
    @FXML
    public void onCanvasClick(MouseEvent mouseEvent) {
        Point pnt = new Point(mouseEvent.getX(), mouseEvent.getY());
        points.add(pnt);
        if (isWaitRightClick){
            if (mouseEvent.getButton() != MouseButton.SECONDARY) return;
            isWaitRightClick = false;
            drawingMethod.run();
            drawFigure();
            clearPoints();
        }
        else if(mbFigure.getText() != "")  {
            if (pointsCount <= points.toArray().length) {
                drawingMethod.run();
                drawFigure();
                clearPoints();
            }
        }
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
        boolean isItDown = points.get(1).y < points.get(2).y ? true : false;
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
                if (figure.isItFigurePoint(x, y)) {
                    gc.moveTo(x, y);
                    gc.lineTo(x, y);
                }
        }
        gc.stroke();
    }

}