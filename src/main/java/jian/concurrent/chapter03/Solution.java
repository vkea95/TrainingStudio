package jian.concurrent.chapter03;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] a = new int[]{73, 74, 75, 71, 69, 72, 76, 73};

        int[] b = s.dailyTemperatures(a);
        IntStream.of(b).forEach(i -> System.out.println(i));
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return T;
        }
        int n = T.length;
        int[] B = new int[n];
        List<Integer> l = new LinkedList<>();
        B[n - 1] = 0;
        l.add(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int j = search(l, T[i], T);
            if (j >= l.size()) {
                B[i] = 0;
            } else {
                B[i] = l.get(j) - i;
            }
            update(l, j, i);
        }
        return B;
    }

    private void update(List<Integer> l, int j, int i) {//bug: 括号不正确
        l.add(j, i);
        while (j-- > 0) {
            l.remove(0);
        }
    }

    private int search(List<Integer> l, int val, int[] B) {
        int s = 0;
        int e = l.size() - 1;//bug length -> size
        while (s <= e) {//bug?
            int m = (s + e) >>> 1;
            int i = B[l.get(m)];
            if (val == i) {
                return m + 1;
            } else if (val < i) {//bug: 写反了下面两个坐标的更新
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        return s; //bug:
    }
}

class Solution2 {
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return T;
        }
        int n = T.length;
        int[] B = new int[n];
        Deque<Integer> dq = new ArrayDeque<>();
        B[n - 1] = 0;
        dq.addFirst(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (!dq.isEmpty() && T[dq.peekFirst()] <= T[i]) {
                dq.pollFirst();
            }
            B[i] = dq.size() == 0 ? 0 : dq.peekFirst() - i;
            dq.addFirst(i);
        }
        return B;
    }
}