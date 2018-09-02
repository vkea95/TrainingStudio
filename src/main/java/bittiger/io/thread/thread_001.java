package bittiger.io.thread;

import java.util.*;

/*
 There are two ways to implement Thread in Java
 */
public class thread_001 {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1234);
        list.add(68);
        for (int i : list) {
            System.out.println(i);
        }
        String _userDir = System.getProperty("user.dir");
        System.out.println(_userDir);
        SubThread subThread = new SubThread();

        Thread myThread_002 = new Thread(new RunnableThread());
        Thread myThread_003 = new Thread(new RunnableThread());
        Thread myThread_004 = new Thread(new RunnableThread());

        // nothing happens, if we call run method

//        subThread.start();
//        myThread_002.run();
//        myThread_003.run();
//        myThread_004.run();
        myThread_002.start();//execute the thread
        myThread_003.start();//execute the thread
        myThread_004.start();//execute the thread

    }
}

/**
 * run method: no new thread is created.
 * start method: a new thread will be created
 */
class SubThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "@Normal Thread " +
                    Thread.currentThread().getId() +
                    " is running");
        }

    }

    public static int test() {
        return 1;
    }
}


class RunnableThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "@Runnable Thread " +
                    Thread.currentThread().getId() +
                    " is running");
        }
    }
}

class Solution {
    public int hIndex(int[] citations) {

        int n = citations.length;

        int s = 0;
        int e = n - 1;

        while (s <= e) {
            int mid = (s + e) >>> 1;
            if (citations[mid] == (n - mid)) {
                return n - mid;
            } else if (citations[mid] > (n - mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }

        return n - s;
    }
}