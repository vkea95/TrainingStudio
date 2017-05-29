package leetcode.com.pickup1.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 8/9/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/course-schedule-ii/
 * ****************************************************
 * Description:
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take
 * to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 * For example:
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 * ****************************************************
 * Thoughts:
 * 1.有向图,就会有in/out degree的概念,
 * ****************************************************
 * Time: 60 mins
 * Beat: 22%
 * Bug: 4
 * ****************************************************
 * Hindsight:
 * 1. 几种基本的bug是,A.需要为所有的节点建立相应的数据结构,B.向结果区间放入可行解的时候,要确保不会重入
 * 2. 要考虑这样的几种case:A.有课程,但是课程关系并没有包含全部课程(如空集) B.课程关系有重复输入的时候,需要做个判断处理,否则会有问题
 * 3. 如果用ArrayList代理HashSet就没有这样的问题啦
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No210_Course_Schedule_II {
    public static void main(String[] args) {
        No210_Course_Schedule_II obj = new No210_Course_Schedule_II();
        obj.findOrder(10, new int[][]{{5,8},{3,5},{1,9},{4,5},{0,2},{1,9},{7,8},{4,9}});
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> outDegree = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> resultList = new ArrayList<>();

        //bug3:没有处理,输入课程对应关系为[]的情况,那么就需要提前根据课程数量搞好各种structure
        for (int i=0;i<numCourses;i++){
            outDegree.put(i, new HashSet<>());
            inDegree.put(i, 0);

        }

        for (int[] prerequisit : prerequisites) {

            //出度节点列表
//            if (!outDegree.containsKey(prerequisit[1])) {
//                outDegree.put(prerequisit[1], new HashSet<>());
//            }
            //bug1:需要为所有节点建表,否则的话,取没有出度的节点的连接关系的时候,就会出null pointer异常
            //出度节点列表
//            if (!outDegree.containsKey(prerequisit[0])) {
//                outDegree.put(prerequisit[0], new HashSet<>());
//            }
//            outDegree.get(prerequisit[1]).add(prerequisit[0]);

//            if (!inDegree.containsKey(prerequisit[1])) {
//                inDegree.put(prerequisit[1], 0);
//            }
//            if (!inDegree.containsKey(prerequisit[0])) {
//                inDegree.put(prerequisit[0], 0);
//            }
            //bug4:输入的课程里面含有重复的数据,所以要做特殊的处理
            if (!outDegree.get(prerequisit[1]).contains(prerequisit[0])){
                outDegree.get(prerequisit[1]).add(prerequisit[0]);
                inDegree.put(prerequisit[0], inDegree.get(prerequisit[0]) + 1);

            }
        }
        for (Integer key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        while (!queue.isEmpty()) {
            Integer key = queue.poll();
            resultList.add(key);
            for (Integer nextCourese : outDegree.get(key)) {
                inDegree.put(nextCourese, inDegree.get(nextCourese) - 1);
                if (inDegree.get(nextCourese) == 0) {
                    //bug2:需要统一resultList的节点增加的位置,某则会将同样的节点多次放入
//                    resultList.add(nextCourese);
                    queue.offer(nextCourese);
                }
            }
        }
        if (resultList.size() == numCourses) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = resultList.get(i);
            }
            return result;
        }
        return new int[0];
    }


    public int[] findOrder_ist(int numCourses, int[][] prerequisites) {
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
