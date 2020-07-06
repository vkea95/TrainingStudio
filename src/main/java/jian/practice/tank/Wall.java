package jian.practice.tank;

import java.awt.*;

public class Wall {
    public int xPos;
    public int yPos;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 60;
    TankWar tankWar;

    public Wall(int xPos, int yPos, TankWar tankWar) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tankWar = tankWar;
    }

    public void draw(Graphics g) {
//        直接判断是否活着就好了
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.fillRect(xPos, yPos, WIDTH, HEIGHT);
        g.setColor(c);
    }

    public Rectangle getRectangle() {
        return new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

//    public boolean hitMissile(Missile missile) {
//        if (missile.isLive() && missile.getRectangle().intersects(getRectangle())) {
//            missile.setLive(false);
//            tankWar.getExplodeList().add(new Explode(missile.getxPos(), missile.getyPos(), tankWar));
//            return true;
//        }
//        return false;
//    }
//
//    public void hitMissileList(java.util.List<Missile> missileList) {
//
//        for (int i = 0; i < missileList.size(); i++) {
//            if (hitMissile(missileList.get(i))) {
//                missileList.remove(i);
//            }
//        }
//    }

//    public void hitTankList(java.util.List<Tank> tankList) {
//
//        for (int i = 0; i < tankList.size(); i++) {
//            hitTank(tankList.get(i));
//        }
//    }
//
//    public boolean hitTank(Tank tank) {
//        if (tank.isLive() && tank.getRectangle().intersects(getRectangle())) {
//            tank.setDirection(Tank.Direction.STOP);
//            return true;
//        }
//        return false;
//    }
}
