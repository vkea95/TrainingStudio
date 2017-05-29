package adm.com.ch07;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 11/5/16.
 * Multisets问题,set中含有重复元素,求所有不重复的元素排列
 * KeyPoint:如何处理重复元素
 * ->
 * 1.pruning:  在backtrack的主循环中,重复的元素不进行处理 -->剪枝法
 * 2. presort the multisets
 *
 * 反思:就是a数组的第零项上不要存储value,否则会在construct的时候出错,因为k=0这个case并不会对a进行任何的处理啊
 */
public class Ex_02 {
    public static void main(String[] args) {
        Ex_02 ex_02 = new Ex_02();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        ex_02.generate_permutations(list);
    }

    public int solution_cout = 0;

    public void backtrack(int a[], int k, List<Integer> input) {
        int[] c = new int[input.size() + 1];//candidate会有n个,所以要射成n+1
        int[] ncandidates = new int[1];//用来保存候选者的计数
        int i;

        //此处判断当前是否是一个Solution
        if (is_a_solution(a, k, input)) {
            //针对solution进行process
            process_solution(a, k, input);//-> process_solution(a, k, input);
        } else {
            //因为没有满足solution的条件,所以k要进行++处理,
            k = k + 1;
            //此处是动态地为这种情况,定制candidate,因为当前程序简单,所以candidate只有true/false两个选项
            construct_candidates(a, k, input, c, ncandidates);
            for (i = 0; i < ncandidates[0]; i++) {
                a[k] = c[i];
                backtrack(a, k, input);
            }
        }
    }

    //for constructing all subsets
    public boolean is_a_solution(int a[], int k, List<Integer> input) {
        return (k == input.size());
    }


    public void construct_candidates(int a[], int k, List<Integer> input, int c[], int[] ncandidates) {
        boolean[] in_sol = new boolean[input.size() + 1];
        int last;//last vertex on current path

        //将所有已经找到的k个节点的结果进行标志
        //bug 第0个项目应该放弃
        for (int i = 0; i < k; i++) {
//            bug 1: 此处应该记录的是list的下标的值呢
            in_sol[a[i]] = true;
        }
        ncandidates[0] = 0;
        for (int i = 0; i < input.size(); i++) {
            if (in_sol[i + 1] == false) {
                //bug 3:pruning 在此处进行处理,一旦发现候选人名单内所对应的value和当前value相同,则不进行处理
                if (ncandidates[0] > 0 && input.get(i) == input.get(c[ncandidates[0] - 1]-1))//最后的bug就是下标操作除了问题,c[]还需要-1啊!!!
                    continue;
                //bug1:比较难想的地方,就是简单地按照candidate,顺序地将候选者放入数组中,然后计数+1
//                bug 2:候选者名单内还是记录下标会更准确,记录值会导致,不好翻译
                c[ncandidates[0]] = i + 1;
                ncandidates[0]++;
            }
        }
        for (int i = 0; i < ncandidates[0]; i++) {
//            System.out.print(c[i] + " ");
        }
//        System.out.println();
    }

    public void process_solution(int a[], int k, List<Integer> input) {
        solution_cout++;
        System.out.print(solution_cout + ": ");
        for (int i = 1; i < a.length; i++) {
            System.out.print(input.get(a[i] - 1) + " ");
//            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public void generate_permutations(List<Integer> input) {
        int[] a = new int[input.size() + 1]; /*solution vector*/
        backtrack(a, 0, input);
    }
}
