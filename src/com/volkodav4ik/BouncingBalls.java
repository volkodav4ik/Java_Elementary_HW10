package com.volkodav4ik;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BouncingBalls extends Application {

    public static final double BOARD_WIDTH = 800;
    public static final double BOARD_HEIGHT = 800;
    private static final int FPS = 60;
    private static final int PAUSE = 1000 / FPS;
    private static final int LINE_WIDTH = 3;
    static List<Balls> balls = new ArrayList<Balls>();
    private boolean closed;
    private GraphicsContext gc;


    public static void main(String[] args) {

        balls.add(new Balls(82.6, 50.1, 5, 150,
                DirectionX.LEFT, DirectionY.UP, Color.WHITE, Color.BLACK));
        balls.add(new Balls(556.2, 150.5, 5, 100,
                DirectionX.RIGHT, DirectionY.DOWN, Color.GREEN, Color.BLACK));
        balls.add(new Balls(677.2, 520.3, 5, 80,
                DirectionX.RIGHT, DirectionY.DOWN, Color.BLUE, Color.BLACK));
        balls.add(new Balls(100.1, 500.6, 5, 50,
                DirectionX.LEFT, DirectionY.UP, Color.RED, Color.BLACK));
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Canvas canvas = new Canvas(BOARD_WIDTH, BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        stage.setScene(scene);
        stage.setTitle("Bouncing Balls");
        stage.show();
        gc = canvas.getGraphicsContext2D();
        new Thread(this::flyingBallHitWalls).start();
    }

    @Override
    public void stop() {
        closed = true;
    }

    private void flyingBallHitWalls() {
        while (!closed) {
            Platform.runLater(this::drawFrame);
            try {
                Thread.sleep(PAUSE);
            } catch (InterruptedException e) {
                break;
            }
            for (Balls ball : balls) {
                ball.changeDirectionIfTouchEdge();
            }
            for (int i = 0; i < balls.size(); i++) {
                for (int j = i + 1; j < balls.size(); j++) {
                    checkIfBallsHit(balls.get(i), balls.get(j));
                }
            }
            for (Balls ball : balls) {
                ball.move();
            }
        }
    }

    private void checkIfBallsHit(Balls firstBall, Balls secondBall) {
        double xDelta = firstBall.getXOfCenter() - secondBall.getXOfCenter();
        double yDelta = firstBall.getYOfCenter() - secondBall.getYOfCenter();
        double hypotenuse = Math.hypot(Math.abs(xDelta), Math.abs(yDelta));
        double diameterDelta = (firstBall.getDiameter() + secondBall.getDiameter()) / 2;
        if (hypotenuse <= diameterDelta) {
            if (Math.abs(xDelta) > Math.abs(yDelta)) {
                if (firstBall.getXOfCenter() < secondBall.getXOfCenter()) {
                    firstBall.setDirectionX(DirectionX.LEFT);
                    secondBall.setDirectionX(DirectionX.RIGHT);
                } else {
                    firstBall.setDirectionX(DirectionX.RIGHT);
                    secondBall.setDirectionX(DirectionX.LEFT);
                }
            }
            if (Math.abs(xDelta) < Math.abs(yDelta)) {
                if (firstBall.getYOfCenter() < secondBall.getYOfCenter()) {
                    firstBall.setDirectionY(DirectionY.UP);
                    secondBall.setDirectionY(DirectionY.DOWN);
                } else {
                    firstBall.setDirectionY(DirectionY.DOWN);
                    secondBall.setDirectionY(DirectionY.UP);
                }
            }
        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        gc.setLineWidth(LINE_WIDTH);
        for (Balls ball : balls) {
            gc.setFill(ball.getColorFill());
            gc.setStroke(ball.getColorStroke());
            gc.fillOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
            gc.strokeOval(ball.getX(), ball.getY(), ball.getDiameter(), ball.getDiameter());
        }
    }
}