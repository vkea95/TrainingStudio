package adm.com.ch07;

/**
 * Created by tclresearchamerica on 11/2/16.
 */
public class ch07 {
    public static void main(String[] args) {
        Backtracking_All_Subsets backtracking_01 = new Backtracking_All_Subsets();
        backtracking_01.generate_subsets(4);

        Backtracking_permutation backtracking_permutation = new Backtracking_permutation();
        backtracking_permutation.generate_permutations(3);
    }
}


class Backtracking_All_paths {
    public int solution_cout = 0;

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
    public boolean is_a_solution(int a[], int k, int t) {
        return (a[k] == t);
    }


    public void construct_candidates(int a[], int k, int n, int c[], int[] ncandidates) {
        boolean[] in_sol = new boolean[n + 1];
        int last;//last vertex on current path
//        edgenode * p;//temporary pointer

        for (int i = 1; i < k; i++) in_sol[a[i]] = false;//将所有已经找到的k个节点的结果进行标志
        //此处不必标志某节点是否,被使用过
        if (k == 1) {
            c[0] = 1;
            ncandidates[0] = 1;
        } else {
            ncandidates[0] = 0;
            last = a[k - 1];//最后一个节点
//            p = g.edges.[last]; //-->边们
//            while (p != null) {
//                if (!in_sol[p ->]) {
//                    c[ncandidates[0]] = p -> y;
//                    ncandidates[0]++;
//                }
//                p = p -> next;
//            }
        }

    }

    public void process_solution(int a[], int k) {

        solution_cout++;
    }

    public void generate_permutations(int n) {
        int[] a = new int[n + 1]; /*solution vector*/
        backtrack(a, 0, n);
    }
}


class Backtracking_permutation {
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


class Backtracking_All_Subsets {
    boolean finished = false;
    public int MAX = 100;
    public int count = 0;

    public void backtrack(boolean a[], int k, int input) {
        boolean[] c = new boolean[MAX];
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
    public boolean is_a_solution(boolean a[], int k, int n) {
        return (k == n);
    }


    public void construct_candidates(boolean a[], int k, int n, boolean c[], int[] ncandidates) {
        c[0] = true;
        c[1] = false;
        ncandidates[0] = 2;
    }

    public void process_solution(boolean a[], int k) {
        int i;	/*counter*/
        System.out.print(count + ": {");
        for (i = 1; i <= k; i++)
            if (a[i] == true) System.out.print(i + " ");
        System.out.println("}");
//        finished = true;
        count++;
    }

    public void generate_subsets(int n) {
        boolean[] a = new boolean[n + 1]; /*solution vector*/
        backtrack(a, 0, n);
    }
}