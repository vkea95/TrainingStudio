package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 6/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/compare-version-numbers/
 * ****************************************************
 * Description:
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of
 * the second first-level revision.
 * <p>
 * Here is an example of version numbers ordering:
 * 0.1 < 1.1 < 1.2 < 13.37
 * ****************************************************
 * Thoughts:
 * 1.按照.切分字符串至数组,但是好像.在regular expression中有特殊意思吧
 * 果然:
 * .匹配除 "\n" 之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用象 '[.\n]' 的模式。
 * 但是可以利用"\\.",进行split
 * ****************************************************
 * Time: 20 mins
 * Beats: 90%
 * Bug: 2
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No165_Compare_Version_Numbers {

    public static void main(String[] args) {
        String version1 = "1.2.";
        String[] v1 = version1.split(".");//wrong
        No165_Compare_Version_Numbers obj = new No165_Compare_Version_Numbers();
        obj.compareVersion("01", "1");
    }

    public int compareVersion_reg(String version1, String version2) {

        String[] levels1 = version1.split("\\.");
        String[] levels2 = version2.split("\\.");

        int length = Math.max(levels1.length, levels2.length);
        for (int i = 0; i < length; i++) {
            Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
            Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
            int compare = v1.compareTo(v2);
            if (compare != 0) {
                return compare;
            }
        }

        return 0;
    }


    public int compareVersion(String version1, String version2) {

        //opt2:完全相等时的比较
        if (version1.equals(version2)) return 0;
        int v1 = 0, v2 = 0;


        int index1 = 0, index2 = 0;
        while (index1 < version1.length() || index2 < version2.length()) {
            v1 = 0;
            v2 = 0;
            for (; index1 < version1.length(); index1++) {
                if (version1.charAt(index1) == '.') {
                    index1++;
                    break;
                } else {
                    //bug1:char -> number还是转错了
//                    v1 = v1 * 10 + (int) version1.charAt(index1);
                    v1 = v1 * 10 + version1.charAt(index1) - '0';
                }
            }

            for (; index2 < version2.length(); index2++) {
                if (version2.charAt(index2) == '.') {
                    index2++;
                    break;
                } else {
                    //bug1:char -> number还是转错了
                    v2 = v2 * 10 + version2.charAt(index2) - '0';
                }
            }
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }

        return 0;
    }

    public int compareVersion_simply(String version1, String version2) {
        int temp1 = 0, temp2 = 0;
        int len1 = version1.length(), len2 = version2.length();
        int i = 0, j = 0;
        while (i < len1 || j < len2) {
            temp1 = 0;
            temp2 = 0;
            while (i < len1 && version1.charAt(i) != '.') {
                temp1 = temp1 * 10 + version1.charAt(i++) - '0';

            }
            while (j < len2 && version2.charAt(j) != '.') {
                temp2 = temp2 * 10 + version2.charAt(j++) - '0';

            }
            if (temp1 > temp2) return 1;
            else if (temp1 < temp2) return -1;
            else {
                i++;
                j++;

            }

        }
        return 0;

    }

}
