package jian.practice.tank;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {

    private int xPos = 50;
    private int yPos = 50;

    public Tank(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void draw(Graphics g) {

        g.setColor(Color.RED);

        g.fillOval(xPos, yPos, 30, 30);

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
            case KeyEvent.VK_LEFT:
                xPos -= 5;
                break;
            case KeyEvent.VK_RIGHT:
                xPos += 5;
                break;
            case KeyEvent.VK_DOWN:
                yPos += 5;
                break;
            case KeyEvent.VK_UP:
                yPos -= 5;
                break;
        }
//            super.keyPressed(e);
    }
}
