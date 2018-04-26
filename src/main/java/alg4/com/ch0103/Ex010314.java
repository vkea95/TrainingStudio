package alg4.com.ch0103;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.EmptyStackException;

/**
 * Created by JianZhang on 5/23/17.
 * Bug:
 * 1. Resize方法里面会操作front,rear和count,但是这三个值在调用本法之前,没有保持同步---即rear++后未进行count++
 */
public class Ex010314 {
    public static void main(String[] args) {
        ResizingArrayQueueOfString<Integer> resizingArrayQueueOfString = new ResizingArrayQueueOfString<>();

        for (int i = 0; i < 100; i++) {
            resizingArrayQueueOfString.enqueue(i);
        }
        for (int i = 0; i < 100; i++) {
            StdOut.println(resizingArrayQueueOfString.dequeue());
        }
        StdOut.println("resizingArrayQueueOfString heights:" + resizingArrayQueueOfString.count);

        for (int i = 0; i < 100; i++) {
            resizingArrayQueueOfString.enqueue(i);
        }
        for (int i = 0; i < 80; i++) {
            StdOut.println(resizingArrayQueueOfString.dequeue());
        }
        StdOut.println("resizingArrayQueueOfString heights:" + resizingArrayQueueOfString.count);
    }
}

class ResizingArrayQueueOfString<Item> {
    Item[] a = (Item[]) new Object[1];
    int count = 0;
    int front = 0;
    int rear = 0;

    public void enqueue(Item item) {
        a[rear++] = item;
        count++;
        if (rear == a.length - 1) {
            if (front == 0) {
                resize(a.length * 2);
//                TODO:resize
            } else {
                rear = 0;

            }
        } else {
            if (rear == front) {
//TODO:resize & move elements
                resize(a.length * 2);
            }

        }
    }

    private void resize(int max) {
        Item[] b = (Item[]) new Object[max];
        int aIndex = front;
        int i = 0;
        if (front < rear) {
            for (i = 0; aIndex < rear; i++) {
                b[i] = a[aIndex++];
            }
        } else {
            for (i = 0; aIndex < a.length; i++) {
                b[i] = a[aIndex++];
            }
            for (aIndex = 0; aIndex < rear; aIndex++) {
                b[i++] = a[aIndex];
            }
        }
        front = 0;
        rear = count;
        a = b;
    }

    public Item dequeue() {
        Item item = null;
        if (rear != front) {
            item = a[front];
            if (front == a.length - 1) {
                a[front] = null;//游离元素
                front = 0;//front后移到head部
            } else {
                a[front++] = null;//游离元素
            }
            count--;
            if (count > 0 && count == a.length / 4) {
                resize(a.length / 2);
            }
//            TODO:resize
        } else {
            throw new EmptyStackException();
        }
        return item;
    }
}
