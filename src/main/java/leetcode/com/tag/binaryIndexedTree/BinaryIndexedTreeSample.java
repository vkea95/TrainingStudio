package leetcode.com.tag.binaryIndexedTree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JianZhang on 10/17/17.
 */
public class BinaryIndexedTreeSample {

    public static void main(String[] args) {
        BinaryIndexedTreeSample obj = new BinaryIndexedTreeSample();
        obj.entrance();
    }

    private void entrance() {
        NumArray obj = new NumArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println("obj.sumRange(1, 3): " + obj.sumRange(1, 3));
        obj.update(4, 105);
        System.out.println("(obj.sumRange(1, 6): " + obj.sumRange(1, 6));
    }

    class NumArray {

        private int[] num;
        private int[] bit;

        public NumArray(int[] nums) {
            num = new int[nums.length + 1];
            bit = new int[nums.length + 1];
            for (int i = 0; i < nums.length; ++i) {
                update(i, nums[i]);
            }

        }

        void update(int i, int val) {
            int diff = val - num[i + 1];
            System.out.println("diff: " + diff);
            System.out.print("j: ");
            for (int j = i + 1; j < num.length; j += (j & -j)) {
                System.out.print(j + "=> ");

                bit[j] += diff;
            }
            System.out.println();
            num[i + 1] = val;
        }

        int sumRange(int i, int j) {
            return getSum(j + 1) - getSum(i);
        }

        int getSum(int i) {
            int res = 0;
            System.out.print("getSum: j # res:");

            for (int j = i; j > 0; j -= (j & -j)) {
                res += bit[j];
                System.out.print(j + " # " + res + " => ");
            }
            System.out.println();

            return res;
        }
    }

}
