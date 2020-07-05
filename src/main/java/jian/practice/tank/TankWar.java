package jian.practice.tank;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.TimeUnit;

public class TankWar extends Frame {

    private int xPos = 50;
    private int yPos = 50;

    public void launchFrame() {
        this.setLocation(100, 300);
        this.setSize(800, 600);
//        step2: 关闭窗口 匿名类，短小精悍，不涉及到将来的扩展
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setTitle("Tank war");
        setVisible(true);
        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
        tankWar.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);

        g.fillOval(xPos, yPos, 30, 30);
        g.setColor(c);
        super.paint(g);
        xPos += 5;
        yPos += 5;
    }

    private class PaintThread implements Runnable {

        @Override
        public void run() {
            while (true) {

                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

//Step 04. move the tank
// tank 移动：1. 位置变为变量，2. 需要一个线程来负责重画坦克 3.使用一个内部类，比较容易访问外部包装类，所以就用他了。
// 内部类使用一个implement Runnable的类，里面自带实现调用外部类的repaint方法===确实比较简洁清晰
//等待解决：屏幕闪烁===》需要使用一个双缓冲来消除闪烁现象