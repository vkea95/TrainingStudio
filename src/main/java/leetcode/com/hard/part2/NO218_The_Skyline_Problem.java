package leetcode.com.hard.part2;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/24/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/the-skyline-problem/
 * ***************************************************************
 * Description:
 * ***************************************************************
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed
 * from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape
 * photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Buildings  Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri
 * are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 * It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings
 * are perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12],
 * [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the termination of the skyline,
 * and always has zero height. Also, the ground in between any two adjacent buildings should be considered
 * part of the skyline contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8],
 * [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5],
 * [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final
 * output as such: [...[2 3], [4 5], [12 7], ...]
 * ***************************************************************
 * Analysis:
 * 1.这道题就和原来的一道用到stack的问题一样, 只是需要回忆下思路啦
 * 2.画了一张图,将图像拆成(x,y)的方式推演,得到了一个类似于PriorityQueue的数据结构,只是添加和删除元素的算法还需要优化下,
 * 3.数据结构,数据结构要怎样呢?
 * 倒着存数据结构,用高度当做index,同样的高度就用list来处理数据,
 * 最好就经过一趟比较来处理并输出数据
 * 在处理新进来的矩形的时候,出现了问题,不知该如何处理是否会断档的问题
 * 可不可以引入长度的概念啊,这样每次只要有新的building进来,就会将旧的building消灭或是斩为2半
 * ***************************************************************
 * Solution:
 * 网络答案:用heap来实现的
 * ***************************************************************
 * Beat:50%
 * ***************************************************************
 * ***************************************************************
 */
public class NO218_The_Skyline_Problem {

    public List<int[]> getSkyline(int[][] buildings) {

        List<int[]> result = new ArrayList<>();
        //倒序
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        //
        List<int[]> pointList = new ArrayList<>();

        for (int[] building : buildings) {
            //bug1:初期化数组的方式 记清楚,
            pointList.add(new int[]{building[0], -building[2]});
            pointList.add(new int[]{building[1], building[2]});
        }
        Collections.sort(pointList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 根据横坐标对列表排序，相同横坐标的点纵坐标小的排在前面
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });

        //Bug2:将地平线推入队列
        queue.offer(0);
        //Bug3:记录上次的值
        int prev = 0;

        for (int[] point : pointList) {
            if (point[1] < 0) {
                //将左顶点推入队列
                queue.offer(-point[1]);
            } else {
                //将左顶点移出队列
                queue.remove(point[1]);
            }
            int cur = queue.peek();
            // 如果堆的新顶部和上个keypoint高度不一样，则加入一个新的keypoint
            if (cur != prev) {
                result.add(new int[]{point[0], cur});
                prev = cur;
            }
        }

        return result;
    }

    public List<int[]> getSkyline_failed(int[][] buildings) {

        List<int[]> result = new ArrayList<>();
        PriorityQueue<Integer> queueHeight = new PriorityQueue<>(Collections.reverseOrder());
        HashMap<Integer, Integer> map = new HashMap<>();

        PriorityQueue<MySquare> queue = new PriorityQueue<>();
        Stack<Integer> stack = new Stack<>();
        int x1 = buildings[0][0];
        int x2 = buildings[0][1];
        int h = buildings[0][2];
        int[] solution = new int[2];
        solution[0] = x1;
        solution[1] = h;
        result.add(solution);
        queueHeight.offer(h);
        map.put(h, 0);

        queue.offer(new MySquare(buildings[0][2], 0));

        result.add(solution);
        for (int i = 1; i < buildings.length; i++) {
            int ix1 = buildings[i][0];
            int ix2 = buildings[i][1];
            int ixy = buildings[i][2];
            int maxHeight = queueHeight.peek();
            int index = -1;
            if (maxHeight > ixy) {
                index = map.get(maxHeight);
                if (buildings[index][1] >= ix2) {
                    continue;
                } else if (buildings[index][1] <= ix1) {
                    //弹出
                    if (queue.size() == 1) {
                        solution = new int[2];

                    }

                } else {
                    solution = new int[2];
                    solution[0] = x1;
                    solution[1] = h;
                    result.add(solution);

                }
            }

        }
        return null;
    }

    class MySquare {
        int height, index;

        public MySquare(int height, int index) {
            this.index = index;
            this.height = height;
        }
    }

    //加这个的原因 是为了 向队列添加元素的时候,按照高低顺序自动排序
    class SquareCompare implements Comparator<MySquare> {


        @Override
        public int compare(MySquare o1, MySquare o2) {
            if (o1.height < o2.height) return -1;
            return 1;
        }
    }

}
