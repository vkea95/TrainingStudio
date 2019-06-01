package bittiger.io.ProducerConsumerThread;

import java.time.LocalDateTime;
import java.util.*;

public class TaskConsumer implements Runnable {

    private Bucket bucket;

    public TaskConsumer(Bucket bucket) {
        this.bucket = bucket;
    }


    @Override
    public void run() {
        // bug: 不知道该如何implement了 -> 首先这是个生产者消费者模型，而且array是fixed长度的
        int count = 0;
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            System.out.println("GET:" + Thread.currentThread().getName() +
                    " " + now + " From " + bucket.take());
        }
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */


class Solution {
    public int distinctSubseqII(String S) {
        return distinctSubsequence(S.toCharArray(), 1000000007);
    }

    public int distinctSubsequence(char[] a, int mod) {
        int n = a.length;
        int[] bucket = new int[26];
        Arrays.fill(bucket, -1);

        int cum = 0;
        for (int i = 0; i < n; i++) {
            int v = cum;
            int ind = a[i] - 'a';
            if (bucket[ind] == -1) {
                v++;
            } else {
                v += mod - bucket[ind];
            }
            if (v >= mod) v -= mod;
            bucket[ind] = cum;
            cum += v;
            if (cum >= mod) cum -= mod;
        }
        return cum;
    }

    public int minAreaRect(int[][] points) {
        int n = points.length;
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add((long) points[i][0] << 32 | points[i][1]);
        }

        int ret = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long S = Math.abs((long) (points[i][0] - points[j][0]) * (points[i][1] - points[j][1]));
                if (S == 0) {
                    continue;
                }
                long x = (long) points[i][0] << 32 | points[j][1];
                if (!set.contains(x)) {
                    continue;
                }
                x = (long) points[j][0] << 32 | points[i][1];
                if (!set.contains(x)) {
                    continue;
                }
                ret = Math.min(ret, (int) S);
            }
        }
        if (ret == Integer.MAX_VALUE) return 0;
        return ret;
    }
}