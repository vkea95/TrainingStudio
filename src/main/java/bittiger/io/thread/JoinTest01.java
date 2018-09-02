package bittiger.io.thread;

import java.util.Calendar;
import java.util.Date;

/*
References  https://blog.csdn.net/hesong1120/article/details/64906716
 */
public class JoinTest01 {

    public static void main(String[] args) {

        ThreadBoy boy = new ThreadBoy();
        boy.start();

    }

    static class ThreadBoy extends Thread {
        @Override
        public void run() {

            System.out.println("男孩和女孩准备出去逛街");
//            Demo demo = new Demo();
            ThreadGirl girl = new ThreadGirl();
            girl.start();
            ThreadParent parent = new ThreadParent();
            parent.start();

            int time = 2000;
            try {
                //设置parent 进程等待子进程的时间，超时则不等待。
//               通过查证得知，原来在线程结束时，JVM会执行该线程的本地exit方法，
                girl.join(time);
                parent.join(time);
                System.out.println("Lalalalalalalalalala");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("男孩等了" + time + ", 不想再等了，去逛街了");
        }
    }

    static class ThreadGirl extends Thread {
        @Override
        public void run() {
            int time = 5000;

            int i = 0;
//            while (i++ < 1000)
            System.out.println("女孩开始化妆,男孩在等待。。。");

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("女孩化妆完成！，耗时" + time);

        }
    }


    static class ThreadParent extends Thread {
        @Override
        public void run() {
            int time = 5000;

            int i = 0;
//            while (i++ <= 1000)
            System.out.println("Parent is cleaning the house 。。。");

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Parent finished the job ！Timing" + time);

        }
    }


    class Demo {
        public synchronized void objectLock_01() {
            try {
                Calendar calendar = Calendar.getInstance();
                System.out.println("objectLock_01: in" + calendar.getTime());
                Thread.sleep(5000);
                System.out.println("objectLock_01: out" + calendar.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public synchronized void objectLock_02() {
            try {
                Calendar calendar = Calendar.getInstance();
                System.out.println("objectLock_02: in" + calendar.getTime());
                Thread.sleep(5000);
                System.out.println("objectLock_02: out" + calendar.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
