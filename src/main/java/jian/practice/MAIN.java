package jian.practice;

import leetcode.com.util.TreeNode;

import java.util.*;

public class MAIN {
    public static void main(String[] args) {

        int[] intArr = {1, 2, 3, 4, 5};
        int[] arr = Arrays.copyOf(intArr, 12);
        System.out.println("value with Arrays.copyOf");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }


        byte[] bytes = new byte[3];
        int val = 23;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) i;
        }
//        for (byte b : bytes) {
//            System.out.println(b);
//        }
        Interval[] intervals = new Interval[]{
                new Interval(2, 9),
                new Interval(1, 3),
                new Interval(10, 20),
                new Interval(5, 17),
                new Interval(100, 109),
                new Interval(23, 59),
        };
//        SolutionTest st = new SolutionTest(intervals);
//        System.out.println("23：" + st.searchTarget(23));
//        System.out.println("60：" + st.searchTarget(60));
//        System.out.println("109：" + st.searchTarget(109));
//        System.out.println("59：" + st.searchTarget(59));
//        System.out.println("110：" + st.searchTarget(110));
    }
}

class Interval {
    int start, end;

    public Interval(int s, int e) {
        this.start = s;
        this.end = e;
    }
}