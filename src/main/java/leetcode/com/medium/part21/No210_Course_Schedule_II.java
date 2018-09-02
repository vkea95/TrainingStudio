package leetcode.com.medium.part21;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/16/16.
 * **************************************************************
 * Location:
 * https://leetcode.com/problems/course-schedule-ii/
 * **************************************************************
 * Description:
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a indexList of prerequisite pairs,
 * return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * <p>
 * For example:
 * <p>
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1]
 * <p>
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 * **************************************************************
 * Analysis:
 * 1. 脑海闪现:hashMap的数据结构,每个元素都会有一个list,存着它的dependency元素.
 * 2. 刚刚没想到应该有哪个课程入手,现在看了答案,明白了,入度为零的课程就是我们要的开始课程
 * **************************************************************
 * Solution: BFS:
 * https://leetcode.com/discuss/96527/simple-basic-bfs-java-solution-beats-73%25
 * 1.选择入度为零的课程,然后再从其set中,抹去它的next节点,同时该节点的degree--
 * **************************************************************
 */
public class No210_Course_Schedule_II {
    public static void main(String[] args) {
        No210_Course_Schedule_II obj = new No210_Course_Schedule_II();
        int[][] prerequisites = {{5, 8}, {3, 5}, {1, 9}, {4, 5}, {0, 2}, {1, 9}, {7, 8}, {4, 9}};
        //{{1,0}};
        obj.findOrder(10, prerequisites);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];

        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < result.length; i++)
                result[i] = i;
            return result;
        }

        int[] degree = new int[numCourses];
        List<HashSet<Integer>> preCourseList = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        //initialization
        for (int i = 0; i < result.length; i++) {
            preCourseList.add(new HashSet<>());
        }


        for (int i = 0; i < prerequisites.length; i++) {

            //bug3:有重复的数据被输入
            if (!preCourseList.get(prerequisites[i][1]).contains(prerequisites[i][0])) {
                preCourseList.get(prerequisites[i][1]).add(prerequisites[i][0]);
                degree[prerequisites[i][0]]++;

            }
        }


        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.offer(i);
        }


        int index = 0;
        while (!queue.isEmpty()) {
            int preCourse = queue.poll();
            //bug1:index要++
            result[index++] = preCourse;

            Iterator<Integer> it = preCourseList.get(preCourse).iterator();
            while (it.hasNext()) {
                int course = it.next();
                it.remove();
                degree[course]--;
                if (degree[course] == 0) {
                    queue.offer(course);
                }
            }
        }

        //bug2:如果没有合适的课程,则返回空的数组
        return index == numCourses ? result : new int[0];

    }
}
