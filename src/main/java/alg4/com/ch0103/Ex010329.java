package alg4.com.ch0103;

import com.sun.tools.javac.util.Assert;

import java.util.EmptyStackException;

/**
 * Created by JianZhang on 5/24/17.
 * 环形链表实现queue,
 * 它是没有任何节点链表为空,且只要链表非空,则last.next的值为first。只能使用一个Node类型的实例变量(last)
 */
public class Ex010329 {
    public static void main(String[] args) {
        RingQueue<Integer> ringQueue = new RingQueue<Integer>();
        for (int i = 0; i < 100; i++) {
            ringQueue.enQueue(i);
        }
        for (int i = 0; i < 100; i++) {
//            Assert(i == ringQueue.deQueue());
        }
    }
}
