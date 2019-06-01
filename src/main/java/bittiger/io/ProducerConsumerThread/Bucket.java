package bittiger.io.ProducerConsumerThread;

import edu.princeton.cs.algs4.In;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;

public class Bucket {
    private BlockingQueue<Task> blockingQueue;  //bug: forget the interface name BlockingQueue
    private int size; // bug: 这个参数是queue的长度，控制producer和consumer的等待
    public Bucket(int size) {

//        LinkedTransferQueue, ArrayBlockingQueue
//        bug: ArrayBlockingQueue 的构造函数体必须要有size作为参数
        this.blockingQueue = new ArrayBlockingQueue<>(size); // forget the implemented class name
        this.size = size;

    }

    public Task take() {
        try {
            return blockingQueue.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null; // bug: 没有对异常处理下的返回值进行处理
        }
    }

    public void put(Task task) {
        try {
            blockingQueue.put(task);
//            bug: 没有想到put 也要处理Interrupted Exception
//            In this implementation, as this method is an explicit
//     * interruption point, preference is given to responding to the
//     * interrupt over normal or reentrant acquisition of the lock.
//            lock.lockInterruptibly() 方法导致的异常
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
