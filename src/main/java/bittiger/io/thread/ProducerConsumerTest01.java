package bittiger.io.thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerTest01 {

    public static void main(String[] args) {
        Bucket bucket = new Bucket(20, 500);
        PutExecuter putExecuter = new PutExecuter(bucket);
        GetExecuter getExecuter = new GetExecuter(bucket, 6);

        List<Thread> putThreads = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            putThreads.add(new Thread(putExecuter));
        }


        List<Thread> getThreads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            getThreads.add(new Thread(getExecuter));
        }

        putThreads.forEach(item -> item.start());

        getThreads.forEach(item -> item.start());

    }


}

class Token {
    private int val;
    private String name;

    public Token(String name, int val) {
        this.val = val;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Token{" +
                "val=" + val +
                ", name='" + name + '\'' +
                '}';
    }
}

class Bucket {
    private BlockingQueue<Token> queue;

    private int rate;

    public Bucket(int size, int rate) {
        this.queue = new ArrayBlockingQueue<>(size);
        this.rate = rate;
    }

    public void putToken(Token token) {
        try {
            Thread.sleep(this.rate);
            queue.put(token);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Token getToken() {
        try {
            Thread.sleep(100);
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}

class PutExecuter implements Runnable {

    private Bucket bucket;

    public PutExecuter(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < 10) {
            LocalDateTime now = LocalDateTime.now();
            bucket.putToken(new Token(Thread.currentThread().getName() +
                    " " + now, counter));
            System.out.println("PUT:" + Thread.currentThread().getName() +
                    " " + counter + " " + now);
            counter++;
        }
    }
}


class GetExecuter implements Runnable {

    private Bucket bucket;
    private int num;

    public GetExecuter(Bucket bucket, int num) {
        this.bucket = bucket;
        this.num = num;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < this.num) {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("GET:" + Thread.currentThread().getName() +
                    " " + now + " From " + bucket.getToken());
            counter++;
        }
    }
}