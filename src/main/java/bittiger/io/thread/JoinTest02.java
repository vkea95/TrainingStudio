package bittiger.io.thread;

/*
References  https://blog.csdn.net/hesong1120/article/details/64906716
 */
public class JoinTest02 {
    public static void main(String[] args) {

        ThreadBoy boy = new ThreadBoy();
        boy.start();

    }

    static class ThreadBoy extends Thread {
        @Override
        public void run() {

            System.out.println("男孩和女孩准备出去逛街");

            ThreadGirl girl = new ThreadGirl();
            girl.start();

            try {
//                此处没有使用时间参数
                girl.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("男孩和女孩开始去逛街了");
        }
    }

    static class ThreadGirl extends Thread {
        @Override
        public void run() {
            int time = 5000;

            System.out.println("女孩开始化妆,男孩在等待。。。");

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("女孩化妆完成！，耗时" + time);

        }
    }
}