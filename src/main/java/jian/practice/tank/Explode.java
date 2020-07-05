package jian.practice.tank;

import java.awt.*;

public class Explode {

    private int xPos;
    private int yPos;
    private int step;
    private boolean isLive = true;
    TankWar tankWar;

    public final static int[] dimension = {6, 12, 18, 36, 48, 30, 25, 18, 8};

    public Explode(int xPos, int yPos, TankWar tankWar) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.tankWar = tankWar;
    }

    public void draw(Graphics g) {
        if (!isLive) {
            tankWar.getExplodeList().remove(this);
            return;
        }
        Color c = g.getColor();
        if (step == dimension.length) {
            step = 0;
            isLive = false;
            return;
        }
        g.setColor(Color.ORANGE);
        g.fillOval(xPos, yPos, dimension[step], dimension[step]);
        step++;
        g.setColor(c);
    }

}
