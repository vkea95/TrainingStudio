package jian.practice.tank;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Tank {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private final static int X_SPEED = 5;
    private final static int Y_SPEED = 5;
    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;
    private boolean isLive = true;

    enum Direction {L, LU, U, RU, R, RD, D, LD, STOP}

    TankWar tankWar = null;
    private Direction direction = Direction.STOP;
    private Direction barrelDirection = Direction.D;

    private int xPos = 50;
    private int yPos = 50;

    private int xOldPos = 50;
    private int yOldPos = 50;
    private boolean good;
    private static Random random = new Random();
    private int step = random.nextInt(12) + 3;

    public Tank(int xPos, int yPos, boolean good, Direction direction, TankWar tankWar) {
        this(xPos, yPos, good);
        this.direction = direction;
        this.tankWar = tankWar;
    }

    public Tank(int xPos, int yPos, boolean good) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.good = good;
    }

    public void draw(Graphics g) {
//        直接判断是否活着就好了
        if (!isLive) return;
        Color c = g.getColor();
        if (good) {

            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLUE);
        }
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
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT);
                break;
            case LD:
                g.drawLine(xPos + Tank.WIDTH / 2, yPos + Tank.HEIGHT / 2, xPos, yPos + Tank.HEIGHT);
                break;
            case STOP:
                break;
        }

        if (!good) {
            if (step == 0) {

                Direction[] dirs = Direction.values();
                direction = dirs[random.nextInt(dirs.length)];
                step = random.nextInt(12) + 3;

            }
            step--;
        }
//        Not good we can use old variable to save the position
//        int wallIndex = this.hitWallList(tankWar.getWallList());
//        if (wallIndex >= 0) {
//            Wall wall = tankWar.getWallList().get(wallIndex);
//            if (xPos >= wall.xPos && xPos <= wall.xPos + wall.WIDTH) {
//                xPos = wall.xPos + wall.WIDTH;
//            } else if (xPos + WIDTH >= wall.xPos && xPos + WIDTH <= wall.xPos + wall.WIDTH) {
//                xPos = wall.xPos - WIDTH;
//            } else if (yPos >= wall.yPos && yPos <= wall.yPos + wall.HEIGHT) {
//                yPos = wall.yPos + wall.HEIGHT;
//            } else if (yPos + HEIGHT >= wall.yPos && yPos + HEIGHT <= wall.yPos + wall.HEIGHT) {
//                yPos = wall.yPos - HEIGHT;
//            }
//        } else {
//            move();
//        }
        if (hitWallList(tankWar.getWallList())) {
            stay();
        } else {
            move();
        }

    }

//    tank need to fire action then return a missile

    private void fire(Direction direction) {
        if (!isLive) {
            return;
        }
//        保证子弹从坦克中间发射出来
        int x = this.xPos + Tank.WIDTH / 2 - Missile.WIDTH / 2;
        int y = this.yPos + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
        tankWar.getMissileList().add(new Missile(x, y, good, tankWar, direction));
    }

    private void stay() {
        xPos = xOldPos;
        yPos = yOldPos;
    }

    public void move() {
        xOldPos = xPos;
        yOldPos = yPos;
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
//        控制tank不出边界
        if (xPos < 0) xPos = 0;
        if (yPos < 30) yPos = 30;
        if (xPos + Tank.WIDTH > TankWar.GAME_WIDTH) xPos = TankWar.GAME_WIDTH - WIDTH;
        if (yPos + Tank.HEIGHT > TankWar.GAME_HEIGHT) yPos = TankWar.GAME_HEIGHT - HEIGHT;

        if (!good) {
            if (random.nextInt(100) > 99) {
                fire(barrelDirection);
            }
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
//            case KeyEvent.VK_CONTROL:
//                fire();
//                break;
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
            case KeyEvent.VK_A:
                superFire();
                break;
        }
        locateDirection();
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                fire(barrelDirection);
                break;
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

    public boolean hitWall(Wall wall) {
        return this.isLive() && wall.getRectangle().intersects(getRectangle());
    }

    public boolean hitWallList(java.util.List<Wall> walls) {
        for (int i = 0; i < walls.size(); i++) {
            if (hitWall(walls.get(i))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getRectangle() {
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public boolean isGood() {
        return good;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean collideWithTank(Tank tank) {
        return this.isLive && tank.isLive && this.getRectangle().intersects(tank.getRectangle());
    }

    public boolean collideWithTanks(java.util.List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);
            if (t != this && collideWithTank(t)) {
                t.stay();
                this.stay();
                return true;
            }
        }
        return false;
    }

    private void superFire() {
        Direction[] directions = Direction.values();
        for (Direction dir : directions) {
            if (dir == Direction.STOP) {
                continue;
            }
            fire(dir);
        }
    }
}
