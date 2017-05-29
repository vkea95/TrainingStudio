package leetcode.com.pickup1.hard;

import leetcode.com.util.ListNode;
import org.omg.CORBA.INTERNAL;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by tclresearchamerica on 6/4/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/3sum/
 * ****************************************************
 * Thoughts:
 * 1.ask for if i can change order of the input array
 * 2.if i can resort the array, then try to use leftBoundar & rightBoundary concepts
 * 3.then how remove the duplicate solution
 * 4.看了一眼答案后,才记起来如何解决问题,
 * 5.问题的关键在于,
 * 首先,要排序,
 * 其次,要从两侧开始进行答案寻找,单纯的从一侧寻找是不正确的
 * 再次,去重处理,需要比较谨慎
 * ****************************************************
 * Time: 25 mins
 * Beats: 60%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i <= nums.length - 3; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) continue;
            int result = 0 - nums[i];
            int leftBound = i + 1;
            int rightBound = nums.length - 1;
            while (leftBound < rightBound) {

                if (nums[leftBound] + nums[rightBound] == result) {
                    List<Integer> solution = new ArrayList<>();
                    solution.add(nums[i]);
                    solution.add(nums[leftBound]);
                    solution.add(nums[rightBound]);
                    resultList.add(solution);
                    //bug1:不能简单地对边界进行处理，这里可能会有重复的数字再次构成正确答案，
                    //其实只要对一个边界进行处理就好了
                    leftBound++;
                    while(leftBound<rightBound && nums[leftBound-1]==nums[leftBound]){
                        leftBound++;
                    }
                    // if(leftBound>=righBound) continue;

                } else if (nums[leftBound] + nums[rightBound] < result) {
                    leftBound++;
                } else {
                    rightBound--;
                }
            }

        }

        return resultList;
    }
}
