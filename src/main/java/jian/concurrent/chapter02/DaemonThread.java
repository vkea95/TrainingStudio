package jian.concurrent.chapter02;

import java.util.concurrent.TimeUnit;
// 测试setDaemon这个方法，如果注释掉设置为true的代码，则程序不会停止
// reason: 守护线程具备自动结束生命周期的特征
public class DaemonThread {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
//
        thread.setDaemon(true);//----------> very important!!!
        thread.start();
        try {

            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread finished lifecycle.");

    }
}
