package alg4.com.ch0202;

/**
 * Created by JianZhang on 6/4/17.
 * 自底向上的排序
 * 图解: English version Pg278
 *      Chinese Version Pg176
 * Comments:
 * 1. 引入的变量 sz(子数组大小-1) 和 子数组索引 lo from zero to
 * 第一重循环: sz=1 until sz <N step sz=2*sz
 * 第二重循环: lo=0 until lo < N - sz; step lo=2*sz
 *      merge(a,lo,lo+sz-1, Math.min(lo+sz+sz—1, N-1))
 * 在理解步长值的问题上,还是有点小问题的,两次循环的步长的2*sz,
 *
 *
 * 呼叫merge方法是,中点的value是lo+sz-1,因为lo+sz+sz-1可能会突破数组的边界,所以要用到Math.min()
 */
public class Jian_MergeBU extends Jian_Merge {
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];

//      sz ==>我认为是数组的1/2, 这样每次子数组的长度都是2的幂次2,4,8,16
        for (int sz = 1; sz < N; sz = sz + sz) {//sz:子数组大小  ->Math.min(lo + sz + sz - 1, N - 1)
//lo+=2*sz 代表开始从下一个子数组开始了,lo的上限是由于
            for (int lo = 0; lo < N - sz; lo += sz + sz)//lo:子数组索引  ->  此处限制了lo的边界
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
        }

    }


}
