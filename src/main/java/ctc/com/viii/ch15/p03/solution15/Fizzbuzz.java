package ctc.com.viii.ch15.p03.solution15;

public class Fizzbuzz {
    private static int MAX_NUMBER = 1000 * 1000;

    public static void main(String[] args) {
        Runnable t1 = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("thread 1 ");
                for (int i = 1; i < Fizzbuzz.MAX_NUMBER; i++) {
                    if (i % 3 == 0) {
                        sb.append(" ");
                        sb.append(i);
                    }
                }
                System.out.println(sb.toString());
            }
        };
        Runnable t2 = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("thread 2 ");
                for (int i = 1; i < Fizzbuzz.MAX_NUMBER; i++) {
                    if (i % 5 == 0) {
                        sb.append(" ");
                        sb.append(i);
                    }
                }
                System.out.println(sb.toString());
            }
        };
        Runnable t3 = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("thread 3 ");
                for (int i = 1; i < Fizzbuzz.MAX_NUMBER; i++) {
                    if (i % 3 == 0 && i % 5 == 0) {
                        sb.append(" ");
                        sb.append(i);
                    }
                }
                System.out.println(sb.toString());
            }
        };
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}

class Thread1 implements Runnable {

    @Override
    public void run() {

    }
}