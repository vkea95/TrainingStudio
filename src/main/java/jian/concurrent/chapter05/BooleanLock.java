package jian.concurrent.chapter05;


import java.util.*;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

public class BooleanLock implements Lock {

    public static void main(String[] args) {
        Solution s = new Solution();
        s.getMoneyAmount(10);
    }

    private Thread crtThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                if (!blockedList.contains(currentThread())) {//bug: 要通过系统获得当前的线程
                    blockedList.add(currentThread());
                }
                this.wait();//bug: 不是让当前的线程wait，而是this.wait,原因是
            }
            blockedList.remove(currentThread());
            this.locked = true;
            this.crtThread = currentThread();
        }
    }

    @Override
    public void lock(long millSeconds) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (millSeconds <= 0) {
                this.lock();//直接调用
            } else {
                long remaining = millSeconds;
                long endMills = currentTimeMillis() + remaining;

                while (locked) {
                    if (remaining <= 0) {
                        throw new TimeoutException("can not get the lock during " + millSeconds + " ms.");
                    }
                    if (!blockedList.contains(currentThread())) {//bug: 要通过系统获得当前的线程
                        blockedList.add(currentThread());
                    }
                    this.wait(remaining);//bug: lock时间需要在wait中使用
                    remaining = endMills - currentTimeMillis();
                }
            }
//            代表此时线程可以享用资源了，然后，要把这个正在消费的家伙给记下来
            blockedList.remove(currentThread());
            this.locked = true;
            this.crtThread = currentThread();
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread() == crtThread) {//bug:此处会使用当前线程，然后进行判断，
                this.locked = false;//bug:
                Optional.of(currentThread().getName() + " release the lock.").ifPresent(System.out::println);
                this.notify();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);//bug:防止list被修改，可以使用
    }
}

class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                for (int piv = start; piv < start + len - 1; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                dp[start][start + len - 1] = minres;
            }
        }
        return dp[1][n];
    }
}