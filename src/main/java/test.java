import java.util.*;

public class test {

    public static void main(String[] args) {

        Scanner xx = new Scanner(System.in);
        System.out.print("enter a number: ");//println换行；print不换行
        while (xx.hasNextInt()) {

            // 判断输入的是否是整数
            int i = xx.nextInt();
            // 接收整数
            System.out.println("整数数据i：" + i);
        }

        System.out.println("please 输入整数！");
        xx.next();
        if (xx.hasNextInt()) {
            System.out.println("输入的不是整数！");
        } else {
            // 输入错误的信息
            System.out.println("输入的不是整数！");
        }


//        while (xx.hasNext()) {
//            System.out.println(xx.next());
//        }
//
//        int number = xx.nextInt();//数据类型为int
//        int number2 = 2 * number;
//        System.out.println("number = " + number);
//        System.out.println("number2 = " + number2);

        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        set.removeAll(list);
        test tests = new test();
        tests.getInvCount(new int[]{8, 4, 2, 1});
        System.out.println(tests.count);
        tests.count = 0;
        tests.getInvCount(new int[]{1, 20, 6, 4, 5});
        System.out.println(tests.count);
//        tests.numTrees(3);
    }


    int[] f;


    public int numTrees(int n) {
        f = new int[n + 1];
        Arrays.fill(f, -1);
        ans(n);
        return f[n];
    }

    private int ans(int n) {
        if (f[n] != -1) {
            return f[n];
        }
        if (n <= 1) {
            f[1] = 1;
            return f[1];
        }
        if (n == 2) {
            f[2] = 2;
            return f[2];
        }
        f[n] = 0;
        // bug: i>=1 -> i>=2
        for (int i = n - 1; i >= 0; i--) {
            f[n] += ans(i) * ans(n - i - 1);
        }

        return f[n];
    }

    int[] bk;
    int count;

    void getInvCount(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int i = 0;
        int n = nums.length;
        bk = new int[n];
        sort(0, n - 1, nums);

    }

    void sort(int s, int e, int[] nums) {
        if (s >= e) {
            return;
        }
        int mid = (s + e) >>> 1;
        sort(s, mid, nums);
        sort(mid + 1, e, nums);
        merge(s, mid, e, nums);
    }

    void merge(int s, int m, int e, int[] nums) {
        for (int i = s; i <= e; i++) {
            bk[i] = nums[i];
        }
        int l = s;
        int r = m + 1;
        int cnt = 0;
        for (int i = s; i <= e; i++) {
            if (l > m) {
                cnt++;// bug
                nums[i] = bk[r++];
            } else if (r > e) {
                this.count += cnt;
                nums[i] = bk[l++];
            } else if (bk[l] <= bk[r]) {
                this.count += cnt;
                nums[i] = bk[l++];
            } else {
                cnt++;// bug
                nums[i] = bk[r++];
            }
        }
    }

}