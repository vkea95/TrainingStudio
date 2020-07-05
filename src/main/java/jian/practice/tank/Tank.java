package jian.practice.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private final static int X_SPEED = 5;
    private final static int Y_SPEED = 5;
    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    TankWar tankWar = null;
    private Direction direction = Direction.STOP;
    private Direction barrelDirection = Direction.D;

    private int xPos = 50;
    private int yPos = 50;

    public Tank(int xPos, int yPos, TankWar tankWar) {
        this(xPos, yPos);
        this.tankWar = tankWar;
    }

    public Tank(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(xPos, yPos, WIDTH, HEIGHT);
        g.setColor(c);


        switch (barrelDirection) {
            case L:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos, yPos + Tank.HEIGHT / 2);
                break;
            case LU:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos, yPos);
                break;
            case U:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos + Tank.WIDTH / 2, yPos);
                break;
            case RU:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos + Tank.WIDTH, yPos);
                break;
            case R:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos + Tank.HEIGHT, yPos + Tank.HEIGHT / 2);
                break;
            case RD:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos + Tank.HEIGHT, yPos + Tank.HEIGHT);
                break;
            case D:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos+ Tank.WIDTH / 2, yPos + Tank.HEIGHT);
                break;
            case LD:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos, yPos + Tank.HEIGHT);
                break;
            case STOP:
                break;
        }

        movie();


    }

//    tank need to fire action then return a missile

    private Missile fire() {
//        保证子弹从坦克中间发射出来
        int x = this.xPos + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.yPos + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        return new Missile(x, y, barrelDirection);
    }


    public void movie() {
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
            case STOP:
                break;
        }
        if (direction != Direction.STOP) {
            barrelDirection = direction;
        }
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                tankWar.missile = fire();
                break;
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
        }
        locateDirection();
    }

    public void locateDirection() {
        if (bL && !bU && !bR && !bD) direction = Direction.L;
        else if (bL && bU && !bR && !bD) direction = Direction.LU;
        else if (!bL && bU && !bR && !bD) direction = Direction.U;
        else if (!bL && bU && bR && !bD) direction = Direction.RU;
        else if (!bL && !bU && bR && !bD) direction = Direction.R;
        else if (!bL && !bU && bR && bD) direction = Direction.RD;
        else if (!bL && !bU && !bR && bD) direction = Direction.D;
        else if (bL && !bU && !bR && bD) direction = Direction.LD;
        else if (!bL && !bU && !bR && !bD) direction = Direction.STOP;
    }

}
