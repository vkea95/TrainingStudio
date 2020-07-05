package jian.practice.tank;

import java.awt.*;

public class Missile {

    private int xPos;
    private int yPos;
    private boolean good;
    private boolean isLive = true;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int X_SPEED = 10;
    public static final int Y_SPEED = 10;
    Tank.Direction direction;
    TankWar tankWar = null;
// 变通的方式，将tank和game的引用传进来，然后可以操作里面的对象，完成remove

    public Missile(int xPos, int yPos, boolean good, TankWar tankWar, Tank.Direction direction) {
        this(xPos, yPos, good, direction);
        this.tankWar = tankWar;
    }

    public Missile(int x, int y, boolean good, Tank.Direction direction) {
        this.xPos = x;
        this.yPos = y;
        this.good = good;
        this.direction = direction;
    }

    public void draw(Graphics g) {
        if (!isLive) {
//            此处进行处理就比较好了，在draw的时候，判断是否
            tankWar.getMissileList().remove(this);
            return;
        }

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
        if (isOutOfBoundary()) {
            isLive = false;
        }
    }

    boolean isOutOfBoundary() {

        return xPos < 0 || yPos < 0 || xPos > TankWar.GAME_WIDTH || yPos > TankWar.GAME_HEIGHT;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public Rectangle getRectangle() {
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public boolean hitTank(Tank tank) {
        if (this.isLive && tank.isLive() && tank.isGood() != this.isGood() && this.getRectangle().intersects(tank.getRectangle())) {
            tank.setLive(false);
            this.setLive(false);
            tankWar.getExplodeList().add(new Explode(xPos, yPos, tankWar));

            return true;
        } else {
            return false;
        }
    }

    public boolean hitTankList(java.util.List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            if (hitTank(tanks.get(i))) {
                //            将tank从list对象中抹除
                tankWar.getEnemyList().remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }
}
