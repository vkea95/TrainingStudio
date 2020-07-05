package jian.practice.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.TimeUnit;

public class TankWar extends Frame {

    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    private int xPos = 50;
    private int yPos = 50;

    Image offScreenImage = null;

    public void launchFrame() {
        this.setLocation(100, 300);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
        this.addKeyListener(new KeyMonitory());
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
//        xPos += 5;
//        yPos += 5;
    }

    @Override
    public void update(Graphics g) {
//        update 截住paint方法
//        create image
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
//        需要图片的画笔，进行作画，
        Graphics graphicsOsn = offScreenImage.getGraphics();
        Color c = graphicsOsn.getColor();
        graphicsOsn.setColor(Color.GREEN);
        graphicsOsn.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphicsOsn.setColor(c);
        paint(graphicsOsn);

//        observer 先设置为null
        g.drawImage(offScreenImage, xPos, yPos, null);
        super.update(g);
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

    private class KeyMonitory extends KeyAdapter {

        @Override
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
}

//Step 04. move the tank
// tank 移动：1. 位置变为变量，2. 需要一个线程来负责重画坦克 3.使用一个内部类，比较容易访问外部包装类，所以就用他了。
// 内部类使用一个implement Runnable的类，里面自带实现调用外部类的repaint方法===确实比较简洁清晰
//等待解决：屏幕闪烁===》需要使用一个双缓冲来消除闪烁现象
//Step 05
// 原因是：重画频率太快了，paint方法还没有完成
//解决方法：1。见所有的东西都画在虚拟图片上（内存中）2。然后再显示到屏幕上
