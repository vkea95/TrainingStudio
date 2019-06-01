package jian.corejava.thread.scratch;

public class JianBQTest {
}


class Producer implements Runnable {
    JianBQ<String> q;

    Producer(JianBQ q) {
        this.q = q;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 10; i++) {
            this.q.put("token " + 1);
        }
    }
}

class Consumer implements Runnable {
    JianBQ<String> q;

    Consumer(JianBQ q) {
        this.q = q;
    }

    @Override
    public void run() {

        for (int i = 0; i <= 10; i++) {
            this.q.get();
        }
    }
}