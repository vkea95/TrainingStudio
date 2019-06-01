import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
/*
Read-write 锁后面的具体操作，就交给JVM来处理
 */
public class ReadWriteLock {
    public static void main(String[] args) {
//        类的初始化，方法调用
        ReadWriteLock readWriteLock = new ReadWriteLock();

        Adder adder = readWriteLock.new Adder(readWriteLock);
        Setter setter = readWriteLock.new Setter(readWriteLock);

        List<Thread> writeThread = new ArrayList<>();

        List<Thread> readThread = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            writeThread.add(new Thread(adder));
        }
        for (int i = 0; i < 3; i++) {
            readThread.add(new Thread(setter));
        }

        writeThread.forEach(item -> item.start());
        readThread.forEach(item -> item.start());

    }


    //    声明ReentrantReadWriteLock锁
    public ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //    分别声明读写锁
    public Lock readLock = readWriteLock.readLock();
    public Lock writeLock = readWriteLock.writeLock();

    public List<Integer> list = new ArrayList<>();

    public void addElement(int element) {
        // write操作 --> add writeLock
        writeLock.lock();

        try {
            list.add(element);
            System.out.println(Thread.currentThread().getName() + "  Adding... " + element +
                    " Current Running ThreadNumber: " + Thread.activeCount());
            Thread.sleep(100);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public void getElement(int index) {
        // read操作 --> add readLock
        readLock.lock();
        try {
            int element = list.get(index);
            System.out.println(Thread.currentThread().getName() + "  Geting... " + element +
                    " Current Running ThreadNumber: " + Thread.activeCount());

            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public class Setter implements Runnable {

        ReadWriteLock readWriteLock;
        int size;

        public Setter(ReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
            this.size = readWriteLock.list.size();
        }

        @Override
        public void run() {
            readWriteLock.getElement(0);
        }
    }

    public class Adder implements Runnable {

        ReadWriteLock readWriteLock;

        public Adder(ReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void run() {
            readWriteLock.addElement(new Random().nextInt(10));
        }
    }

}