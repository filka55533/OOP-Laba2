package com.example.laba2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    protected void initComponents(HelloController controller){
        Canvas canvas = controller.getCanvas();
        canvas.setLayoutY(controller.getVerticalOffset());
        canvas.setLayoutX(0);
        canvas.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        canvas.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        canvas.getGraphicsContext2D().setFill(Color.YELLOW);
        canvas.getGraphicsContext2D().fillRect(0, 0 , canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        HelloController controller = fxmlLoader.getController();
        initComponents(controller);
        Scene scene = new Scene(root, 320, 240);

        stage.setScene(scene);
        stage.setTitle("Laboratory work 2");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}