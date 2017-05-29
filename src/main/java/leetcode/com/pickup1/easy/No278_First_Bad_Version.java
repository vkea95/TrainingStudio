package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 7/12/16.
 * ****************************************************
 * Location:
 * <p>
 * ****************************************************
 * Description:
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version
 * of your product fails the quality check. Since each version is developed based on the previous version, all the
 * versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the
 * following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 * ****************************************************
 * Thoughts:
 * 1.二分法吗?是的
 * 2.在看题目的时候,漏看了一个可以call的函数isBadVersion,这个函数可以被call
 * 3.需要注意的是2分法中,避免(start+end)overflow的方法,就是start+(end-start)/2
 * Time: 20mins
 * Beat: 53%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No278_First_Bad_Version {
    public int firstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid)) {
                //bug1:start要+1,否则会无限循环呢
                start = mid + 1;
            } else {
                end = mid;

            }
        }
        return start;
    }

    boolean isBadVersion(int a) {
        return false;
    }
}
