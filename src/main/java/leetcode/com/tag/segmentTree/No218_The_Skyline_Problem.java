package leetcode.com.tag.segmentTree;

import edu.princeton.cs.algs4.In;

import java.util.*;

/**
 * Created by JianZhang on 10/8/17.
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Now suppose you are given the locations and height of all the buildings
 * as shown on a cityscape photo (Figure A), write a program to output the skyline formed
 * by these buildings collectively (Figure B).
 * <p>
 * Buildings  Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
 * where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
 * and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
 * You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as:
 * [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a indexList of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
 * Note that the last key point, where the rightmost building ends,
 * is merely used to mark the termination of the skyline, and always has zero height.
 * Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input indexList is guaranteed to be in the range [0, 10000].
 * The input indexList is already sorted in ascending order by the left x position Li.
 * The output indexList must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline.
 * For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
 * the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 * Solutions:
 * 1. 考虑使用stack,每次入栈的时候,就比较
 * 2. 看了下答案,发现需要使用到priority队列,然后要对每个building的节点进行排序,--这个倒是也想到了,只是需要注意的是,需要区分左右边,
 * 此处用的是高度的正负值来进行的区分
 * 3. 然后处理的时候,用到priority的逆序--->为高度所用
 * 4. priorityQueue用到的实现方式是:new PriorityQueue<>(Collections.reverseOrder());
 */
public class No218_The_Skyline_Problem {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
//        Stack<int[]> stack = new Stack<>();
//        for (int[] building : buildings) {
//            if (stack.isEmpty()) {
//                stack.push(building);
//            } else {
//                int[] peek = stack.peek();
//
//                if ()
//            }
//
//        }
        List<int[]> nodeList = new ArrayList<>();
        for (int[] building : buildings) {
            nodeList.add(new int[]{building[0], -building[2]});
            nodeList.add(new int[]{building[1], building[2]});
        }
        Collections.sort(nodeList, new Comparator<int[]>() {

                    @Override
                    public int compare(int[] o1, int[] o2) {
                        if (o1[0] != o2[0]) return o1[0] - o2[0];
                        else return o1[1] - o2[1];
                    }
                }
        );

        Queue<Integer> heightQueue = new PriorityQueue<>(Collections.reverseOrder());
        heightQueue.offer(0);//将最后的那个节点高度给入栈啦
        int prevHeight = 0;
        for (int[] node : nodeList) {
            if (node[1] < 0) {
                //building的左侧边缘
                //然后队列自动排序
                heightQueue.offer(-node[1]);
            } else {
                //building的右侧边缘
                heightQueue.remove(node[1]);
            }
            int curtHeight = heightQueue.peek();
            if (curtHeight != prevHeight) {
                prevHeight = curtHeight;
                result.add(new int[]{node[0], curtHeight});
            }
        }
        return result;
    }
}
