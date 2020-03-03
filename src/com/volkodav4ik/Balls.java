package com.volkodav4ik;

import javafx.scene.paint.Color;

public class Balls {
    private double x;
    private double y;
    private double step;
    private double diameter;
    private DirectionX directionX;
    private DirectionY directionY;
    private Color colorFill;
    private Color colorStroke;

    public Balls(double x, double y, double step, double diameter, DirectionX directionX
            , DirectionY directionY, Color colorFill, Color colorStroke) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.diameter = diameter;
        this.directionX = directionX;
        this.directionY = directionY;
        this.colorFill = colorFill;
        this.colorStroke = colorStroke;
    }

    public void move(){
        if (this.getDirectionX() == DirectionX.LEFT){
            x -= this.getStep();
        } else {
            x += this.getStep();
        }
        if (this.getDirectionY() == DirectionY.UP){
            y -= this.getStep();
        } else {
            y += this.getStep();
        }

    }

    public void changeDirectionIfTouchEdge() {
        if (diameter/2 >= BouncingBalls.BOARD_WIDTH - this.getXOfCenter()){
            this.setDirectionX(DirectionX.LEFT);
        }
        if (diameter/2 >= this.getXOfCenter()){
            this.setDirectionX(DirectionX.RIGHT);
        }
        if (diameter/2 >= BouncingBalls.BOARD_HEIGHT - this.getYOfCenter()){
            this.setDirectionY(DirectionY.UP);
        }
        if (diameter/2 >= this.getYOfCenter()){
            this.setDirectionY(DirectionY.DOWN);
        }
    }

    public double getXOfCenter(){
        return x + diameter/2;
    }

    public double getYOfCenter(){
        return y + diameter/2;
    }

    public void setDirectionX(DirectionX directionX) {
        this.directionX = directionX;
    }

    public void setDirectionY(DirectionY directionY) {
        this.directionY = directionY;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getStep() {
        return step;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public DirectionX getDirectionX() {
        return directionX;
    }

    public DirectionY getDirectionY() {
        return directionY;
    }

    public Color getColorFill() {
        return colorFill;
    }

    public Color getColorStroke() {
        return colorStroke;
    }
}
