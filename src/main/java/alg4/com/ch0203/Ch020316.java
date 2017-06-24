package alg4.com.ch0203;

/**
 * Created by JianZhang on 6/24/17.
 * 最佳数组的定义:1.N个不同元素 2.每次切分后,两个子数组的size差值至多为1,
 * 所以,就是对于每个被且的数组,它的中间值应该永远都位于数组的最左侧
 * <p>
 * 算法实现:参考的是本书提供的算法,它的聪明之处在于,用递归的方式进行破解,然后每次都寻找mid值,然后,分别递归子数组,完毕之后再交换
 * 通过操作顺序的改变,事项正确的递归处理
 */
public class Ch020316 {

    public int[] best(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;


        return a;

    }

    private void best(int[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            assert a[i] == i;

        int mid = lo + (hi - lo) / 2;
        best(a, lo, mid - 1);
        best(a, mid + 1, hi);
        swap(a, lo, mid);
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
