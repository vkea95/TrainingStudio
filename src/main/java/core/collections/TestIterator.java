package core.collections;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class TestIterator {

    public static void main(String args[]) {

        int[] test = new int[]{1, 2};
        Solution s = new Solution();
        Solution.printComplementCode(-10);
        Solution.printComplementCode(10);

        System.out.println(s.tickets(new int[]{2, 5}, 4));
        s.productExceptSelf(new int[]{1, 2, 3, 4});
        Arrays.asList(test);
        List<String> animalList = new ArrayList<>();
        animalList.add("Horse");
        animalList.add("Lion");
        animalList.add("Tiger");
        Animal animal = new Animal(animalList);
        Iterator<String> it = animal.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
//        for (String animalObj : animal) {
//            System.out.println(animalObj);
//        }
    }
}

class Solution {
    public static void printComplementCode(int a) {
        for (int i = 0; i < 32; i++) {
            // 0x80000000 是一个首位为1，其余位数为0的整数
            int t = (a & 0x80000000 >>> i) >>> (31 - i);
            System.out.print(t);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printComplementCode(-10); // 11111111 11111111 11111111 11110110
    }

    public int tickets(int[] windows, int m) {
        int rst = 0;
        if (windows == null || windows.length == 0) {
            return rst;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> windows[b] - windows[a]);
        for (int i = 0; i < windows.length; i++) {
            pq.add(i);
        }

        while (!pq.isEmpty() && m > 0) {
            int index = pq.poll();
//            int count = windows[index];
//            if (windows[index] == 0) {
//                continue;
//            }
            while ((pq.isEmpty() || windows[index] >= windows[pq.peek()]) && windows[index] > 0 && m > 0) {
                m--;
                rst += windows[index]--;
            }
            if (windows[index] > 0) {
                pq.add(index);
            }
        }
        return rst;
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] rst = new int[n];
        int[] front = new int[n];
        int[] back = new int[n];
        front[0] = 1;
        for (int i = 1; i < n; i++) {
            front[i] = front[i - 1] * nums[i - 1];
        }
        back[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            back[i] = back[i + 1] * nums[i + 1];
        }
        // rst[0] = back[0];
        for (int i = 0; i < n; i++) {
            back[i] = front[i] * back[i];
        }
        return rst;
    }
}