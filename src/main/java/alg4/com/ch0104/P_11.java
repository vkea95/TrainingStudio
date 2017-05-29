package alg4.com.ch0104;

import edu.princeton.cs.algs4.StaticSETofInts;

import java.util.Arrays;

/**
 * Created by JianZhang on 12/20/16.
 * AddaninstancemethodhowMany()toStaticSETofInts(page99)thatfindsthe number of occurrences of a given key
 * in time proportional to log N in the worst case.
 */
public class P_11 {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 2, 2, 2, 3, 3, 3, 5, 6, 6, 7};
        MyStaticSETofInts_II myStaticSETofInts = new MyStaticSETofInts_II(a);
        System.out.println("Number of  1 is " + myStaticSETofInts.howMany(1));
        System.out.println("Number of  2 is " + myStaticSETofInts.howMany(2));
        System.out.println("Number of  3 is " + myStaticSETofInts.howMany(3));
        System.out.println("Number of  5 is " + myStaticSETofInts.howMany(5));
        System.out.println("Number of  6 is " + myStaticSETofInts.howMany(6));
        System.out.println("Number of  7 is " + myStaticSETofInts.howMany(7));
        System.out.println("Number of  8 is " + myStaticSETofInts.howMany(8));

    }
}

/*
* 参照 http://www.voidcn.com/blog/WalkingInTheWind/article/p-485297.html#topic2
* 对相同数值的元素的下标取上限和下限的方法进行了调整,
* 要注意的就是去上限的时候,算mid便宜量要(hi-lo+1)/2,
* 这个做法和我的区别在while的循环跳出条件,不一样,hi和lo的赋值方式会对mid进行+/-1处理
* 这样implement方法内部的逻辑处理条件更简洁
* */
class MyStaticSETofInts_II {
    private int[] a;

    public MyStaticSETofInts_II(int[] keys) {

        // defensive copy
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];

        // sort the integers
        Arrays.sort(a);
    }

    private int lowerRank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] < key) lo = mid + 1;
            else hi = mid;
        }
        if (a[lo] != key) return -1;
        else return lo;
    }

    private int upperRank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;
            if (a[mid] > key) hi = mid - 1;
            else lo = mid;
        }
        if (a[hi] != key)
            return -1;
        else return hi;
    }

    public int howMany(int key) {
        int left = lowerRank(key);
        int right = upperRank(key);
        if (left == -1) return 0;
        else return (right - left + 1);
    }

}

class MyStaticSETofInts {
    private int[] a;

    public MyStaticSETofInts(int[] keys) {

        // defensive copy
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++)
            a[i] = keys[i];

        // sort the integers
        Arrays.sort(a);
    }

    private int lowerRank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (key <= a[mid]) hi = mid;
            else lo = mid;
        }
        if (a[lo] == key) return lo;
        else if (a[hi] == key) return hi;
        else return -1;
    }

    private int upperRank(int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (key >= a[mid]) lo = mid;
            else hi = mid;
        }
        if (a[hi] == key)
            return hi;
        else if (a[lo] == key) return lo;
        else return -1;
    }

    public int howMany(int key) {
        int left = lowerRank(key);
        int right = upperRank(key);
        if (left == -1) return 0;
        else return (right - left + 1);
    }

}
