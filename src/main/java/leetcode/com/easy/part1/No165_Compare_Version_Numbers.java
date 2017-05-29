package leetcode.com.easy.part1;

/**
 * Created by jason on 2016/4/2.
 * Location：
 * https://leetcode.com/problems/compare-version-numbers/
 * *****************************************************************
 * Description：
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level
 * revision of the second first-level revision.
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 */
public class No165_Compare_Version_Numbers {
    public static void main(String[] args) {
        No165_Compare_Version_Numbers obj = new No165_Compare_Version_Numbers();
        obj.compareVersion("10", "11");
    }

    public int compareVersion(String version1, String version2) {
        //bug1:如果字符串中不含分割符，则切分出的数组长度不为零！，只是把整个字符串当做元素而已！！！！！！！！！！！
        //bug2:根据正则表达式规则，.可以代表任何元素，如果是单独表示句号的话需要用"\."来代替
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        for (i = 0; i < v1.length && i < v2.length; i++) {
            if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])) {
                return 1;
            } else if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
                return -1;
            }
        }
        //bug3:两个的version中含有的.不一样多，所以还需要单独比较是否长度一样
        //bug4:即使长度一样，但是如果零，那么也还是相当于没有
        for (; i < v1.length; i++) {
            if (Integer.parseInt(v1[i]) > 0) {
                return 1;
            }
        }
        for (; i < v2.length; i++) {
            if (Integer.parseInt(v2[i]) > 0) {
                return -1;
            }
        }

        return 0;

    }
}
