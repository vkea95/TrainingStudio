package jian.practice.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TankWar extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;


    java.util.List<Missile> missileList = new ArrayList<>();

    Image offScreenImage = null;
    private Tank tank = null;
    private java.util.List<Tank> enemyList = new ArrayList<>();
    private java.util.List<Explode> explodeList = new ArrayList<>();
    private java.util.List<Wall> wallList = new ArrayList<>();

    public TankWar() throws HeadlessException {
//        此处可以将this这个引用传给tank
        tank = new Tank(50, 50, true, Tank.Direction.STOP, this);
        for (int i = 0; i < 10; i++) {
            enemyList.add(new Tank(200 + i * 50, 50, false, Tank.Direction.U, this));
        }
        wallList.add(new Wall(200, 300, this));

    }

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
//        System.out.println("the Size of missileList: " + missileList.size());
        g.drawString("Missiles count:" + missileList.size(), 10, 50);
        g.drawString("Explode count:" + explodeList.size(), 10, 70);
        g.drawString("tanks count:" + enemyList.size(), 10, 90);

        Color c = g.getColor();
        for (int i = 0; i < enemyList.size(); i++) {

            enemyList.get(i).hitWallList(wallList);
            enemyList.get(i).collideWithTanks(enemyList);
            enemyList.get(i).draw(g);
        }
        this.tank.draw(g);
        for (int i = 0; i < missileList.size(); i++) {
            missileList.get(i).hitTank(tank);
            missileList.get(i).hitTankList(enemyList);
//            missileList.get(i).hitWallList(wallList);
            missileList.get(i).draw(g);
        }
        for (Wall wall : wallList) {
            wall.draw(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).draw(g);
        }

        g.setColor(c);
        super.paint(g);
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
        g.drawImage(offScreenImage, 50, 50, null);
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
            tank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }
    }

    public List<Missile> getMissileList() {
        return missileList;
    }

    public void setMissileList(List<Missile> missileList) {
        this.missileList = missileList;
    }

    public List<Explode> getExplodeList() {
        return explodeList;
    }

    public void setExplodeList(List<Explode> explodeList) {
        this.explodeList = explodeList;
    }

    public List<Tank> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Tank> enemyList) {
        this.enemyList = enemyList;
    }

    public List<Wall> getWallList() {
        return wallList;
    }

    public void setWallList(List<Wall> wallList) {
        this.wallList = wallList;
    }
}
//Step 04. move the tank
// tank 移动：1. 位置变为变量，2. 需要一个线程来负责重画坦克 3.使用一个内部类，比较容易访问外部包装类，所以就用他了。
// 内部类使用一个implement Runnable的类，里面自带实现调用外部类的repaint方法===确实比较简洁清晰
//等待解决：屏幕闪烁===》需要使用一个双缓冲来消除闪烁现象
//Step 05
// 原因是：重画频率太快了，paint方法还没有完成
//解决方法：1。见所有的东西都画在虚拟图片上（内存中）2。然后再显示到屏幕上
//Step tank的对象化：将draw和keyExpressed都给tank使用，因为是属于tank本身的行为

// step 05:
//tank的八键移动方法：
// 1。需要定义好几个方向，
// 2。获取按键的信息，因为不能直接由按键操作坦克，所以定义了bool 变量来wrap它，由布尔变量来高
// 3。方向的定义，因为方向有限，所以采用了enum方式
// 4。根据几个按键的bool值，重新定义方向，
// 5。根据方向，重绘坦克的位置
// 6。 由于需要擦出按键信息，所以需要继承keyReleased事件，完成刷新操作
// missile这个对象，需要tank和war这两个都持有才可以
// tank的那个fire方法，明确告知这个missile属于哪一个类了
//  坦克停下来，子弹也就停下来了，所以需要定义另外一个方向，
// 如何画炮筒呢
// Step 06
// 将Missile 加入到一个list容器中，不停地重画，问题来了，list中的对象 在飘出屏幕后，应进行销毁操作，否则就永远占用内存了
// 还有一种可能就是打中对方tank
// 如果在Missile中管理missile List的化，会导致多线程异常，因为我用的是forEach Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
//Step 07 missile hits the tank: add method to missile class
// step 08: explode should be a list of objects
// step 09 敌方tank一发射就会把自己干掉，所以需要给missle增加属性，保证同样属性的导弹和tank不产生爆炸
//且 对方发射的导弹会太多了！