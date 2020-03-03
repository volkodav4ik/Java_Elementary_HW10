package com.volkodav4ik;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HW10T1 extends Application {

    private static final int BOARD_WIDTH = 1500;
    private static final int BOARD_HEIGHT = 800;

    public static void main(String[] args) {
        Application.launch(args);
    }


    public void start(Stage stage) {
        Canvas canvas = new Canvas(BOARD_WIDTH, BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group, BOARD_WIDTH, BOARD_HEIGHT, Color.STEELBLUE);
        stage.setScene(scene);
        stage.setTitle("Hello JavaFX");
        stage.show();

        GraphicsContext gs = canvas.getGraphicsContext2D();
        printHouse(gs);

    }

    private void printHouse(GraphicsContext gs) {
//        print sun
        gs.setFill(Color.YELLOW);
        gs.fillOval(1200, 50, 200, 200);
//        print cloud
        gs.setFill(Color.WHITESMOKE);
        gs.fillOval(350, 0, 100, 100);
        gs.fillRoundRect(300, 50, 200, 100, 100, 100);
        gs.fillOval(1125, 75, 100, 100);
        gs.fillRoundRect(1050, 125, 250, 100, 100, 100);
        gs.fillOval(615, 155, 30, 30);
        gs.fillOval(635, 125, 25, 25);
//        print grass
        gs.setFill(Color.LIGHTGREEN);
        gs.fillOval(250, 675, 1000, 300);
//        print house
        gs.setFill(Color.TAN);
        gs.fillRect(550, 325, 400, 400);
        gs.fillRect(600, 200, 30, 70);
//        print roof
        gs.setFill(Color.BLACK);
        gs.fillPolygon(new double[]{475, 1025, 750}, new double[]{340, 340, 170}, 3);
//        print stair
        gs.fillRect(770, 705, 130, 20);
//        print door
        gs.fillOval(855, 630, 5, 5);
        gs.setFill(Color.BROWN);
        gs.fillRect(785, 555, 100, 150);
        gs.setFill(Color.BLACK);
        gs.fillOval(865, 630, 10, 10);
//        print window
        gs.setFill(Color.STEELBLUE);
        gs.fillRect(600, 555, 140, 100);
        gs.fillOval(715, 375, 70, 70);
        gs.setStroke(Color.WHITE);
        gs.setLineWidth(5);
        gs.strokeRect(600, 555, 140, 100);
        gs.strokeLine(670, 555, 670, 655);
        gs.strokeOval(715, 375, 70, 70);
        gs.strokeLine(750, 375, 750, 445);
        gs.strokeLine(715, 410, 785, 410);
//        print signature
        gs.setStroke(Color.BLACK);
        gs.setLineWidth(1);
        gs.strokeText("Artemka, 29 years", 1200, 700, 250);
    }

}
