package adm.com.ch07;

/**
 * Created by tclresearchamerica on 11/5/16.
 * 对于错排的n个数的排列,需要将他们复位,使用backtrack+pruning(剪枝法)
 * 错排是指,所有的数字都不在器位置上,即pi<>i
 * 所以此处的剪枝,就是剪出pi=i吧
 */
public class Ex_01 {
    public static void main(String[] args) {
        Ex_01 ex_01 =new Ex_01();
        ex_01.generate_permutations(5);
    }

    public int count = 0;

    public void backtrack(int a[], int k, int input) {
        int[] c = new int[input + 1];//candidate会有n个,所以要射成n+1
        int[] ncandidates = new int[1];
        int i;

        //此处判断当前是否是一个Solution
        if (is_a_solution(a, k, input)) {
            //针对solution进行process
            process_solution(a, k);//-> process_solution(a, k, input);
        } else {
            //因为没有满足solution的条件,所以k要进行++处理,
            k = k + 1;
            //此处是动态地为这种情况,定制candidate,因为当前程序简单,所以candidate只有true/false两个选项
            construct_candidates(a, k, input, c, ncandidates);
            for (i = 0; i < ncandidates[0]; i++) {
                //将candidate的值付给a[k]
                //此处进行pruning
                if (c[i] == k) continue;
                a[k] = c[i];
                //此处,可以对input进行额外的处理,但是此处并没有必要
//                make_move(a, k, input);
                backtrack(a, k, input);
//                unmake_move(a, k, input);
//                if (finished) return;
            }
        }
    }

    //for constructing all subsets
    public boolean is_a_solution(int a[], int k, int n) {
        return (k == n);
    }


    public void construct_candidates(int a[], int k, int n, int c[], int[] ncandidates) {
        boolean[] in_perm = new boolean[n + 1]; //who is in the permutation? 默认值为false
        //所以,需要根据当前的a中的值,来标志in_perm的情况
        for (int i = 0; i < k; i++) in_perm[a[i]] = true;//因为已有k个项目被固定,所以只考虑这个k个就好了
        ncandidates[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (in_perm[i] == false) {
                c[ncandidates[0]] = i;//对candidate进行赋值,所以凡是没有被选中的数字,都会被记录在c(candidate)数组中
                ncandidates[0]++;

            }
        }
    }

    public void process_solution(int a[], int k) {
        int i;	/*counter*/
        System.out.print(count + ": ");
        for (i = 1; i <= k; i++)
            System.out.print(a[i] + " ");
        System.out.println();
//        finished = true;
        count++;
    }

    public void generate_permutations(int n) {
        int[] a = new int[n + 1]; /*solution vector*/
        backtrack(a, 0, n);
    }
}
