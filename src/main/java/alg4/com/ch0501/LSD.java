package alg4.com.ch0501;

/**
 * Created by JianZhang on 8/27/17.
 */
public class LSD {
    public static void sort(String[] a, int w) {
        //通过前w个字符,对a进行排序

        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        //前w个字符的话,就是从0到w-1
        for (int d = w - 1; d >= 0; d--) {
            //根据第d个字符,进行键索引计数法排序
            int[] count = new int[R + 1];//--->此处,要用R+1,防止爆表
            for (int i = 0; i < N; i++) {  //计算出现频率
                //此处要+1------------------->!!!
                count[a[i].charAt(d) + 1]++;
            }
            for (int r = 0; r < R; r++) {  //频率转为索引
                count[r + 1] = count[r];
            }

            for (int i = 0; i < N; i++) {//元素分类
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for (int i = 0; i < N; i++)//回写
                a[i] = aux[i];
        }
    }
}
