package jian.practice.tank;

import java.awt.*;

public class Missile {

    private int xPos;
    private int yPos;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int X_SPEED = 10;
    public static final int Y_SPEED = 10;
    Tank.Direction direction;

    public Missile(int x, int y, Tank.Direction direction) {
        this.xPos = x;
        this.yPos = y;
        this.direction = direction;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
//        set the color for the missile object
        g.setColor(Color.BLACK);
        g.fillOval(xPos, yPos, WIDTH, HEIGHT);
        g.setColor(c);
        move();
    }

    private void move() {
        switch (direction) {
            case L:
                xPos -= X_SPEED;
                break;
            case D:
                yPos += Y_SPEED;
                break;
            case LU:
                xPos -= X_SPEED;
                yPos -= Y_SPEED;
                break;
            case U:
                yPos -= Y_SPEED;
                break;
            case RU:
                xPos += X_SPEED;
                yPos -= Y_SPEED;
                break;
            case R:
                xPos += X_SPEED;
                break;
            case RD:
                xPos += X_SPEED;
                yPos += Y_SPEED;
                break;
            case LD:
                xPos -= X_SPEED;
                yPos += Y_SPEED;
                break;
        }

    }

}
