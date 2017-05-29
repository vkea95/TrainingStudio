package ctc.com.viii.ch01.arrays;

import java.util.Arrays;

/**
 * Created by tclresearchamerica on 5/15/16.
 * **************************************************************
 * Location:
 * Pg73@CTC
 * **************************************************************
 * No.02:
 * Question:
 * Implement a function void reverse(char* str) in C or C++ which reverses a null-terminated string.
 * Answer:
 * **************************************************************
 * No.03:
 * Question:
 * Given two strings, write a method to decide if one is permutation of the other.
 * Answer:
 * String -> char[] -> sort -> compare
 * O(n)
 * **************************************************************
 * No.04:
 * Write a method to replace all spaces in a strin with '%20'. You may assume that the string has sufficient space at the end of the string to hold
 * the additional characters, and that you are given the "true" length of the string.(in Java, please use a character array so tha you ca perform
 * this operation in place)
 * **************************************************************
 * No.05:
 * Question:
 * Implement a method to perform basic string compression using the counts of repeated characters. For example,
 * the string aabcccccaaa would become a2b1c5a2. if the "coompressed" string would not become smaller than the original string,
 * your method should return the original string.
 * **************************************************************
 * No.06:
 * Question:
 * Given an image represented by an N*N matrix, where each pixel in the image is 4 bytes, write a method to
 * rotate the image by 90 degrees. Can you do this in place
 * Analysis:
 * 1.没看懂题意, 不知道什么是每个pixel是4byte
 * **************************************************************
 * No.07:
 * Question:
 * Write an algorithm such that if an element in N*N matrix is 0,its entire row and column are set to 0;
 * Analysis:
 * 循环处理咯
 * **************************************************************
 * No.08:
 * Question:
 * Assume that you have a method isSubString which checks if one word is a substring of another. Given 2 string,
 * s1 & s2, write code to check if s2 is a rotation of s1 using onely call to is SubString(eg: waterbottle is
 * rotation of "erbottlewat"
 * **************************************************************
 * **************************************************************
 * **************************************************************
 * **************************************************************
 */
public class No0102_IQ {
    public static void main(String[] args) {
        No0102_IQ obj = new No0102_IQ();
        System.out.print(obj.no04("Mr John Smith     "));
    }

    public boolean no03(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) return false;
        }
        return true;
    }

    public String no04(String s) {
        StringBuffer sb = new StringBuffer();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String no05(String origin) {
        if (origin == null || origin.length() <= 2) return origin;
        char[] chars = origin.toCharArray();
        int count = 1;
        char c = chars[0];
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                sb.append(c);
                sb.append(count);
                count = 1;
                c = chars[i];
            }
        }
        sb.append(c);
        sb.append(count);
        return sb.length() < origin.length() ? sb.toString() : origin;

    }
    /**
     *这是一个矩阵旋转的问题,
     */

    public void no06_rotate(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; ++i) {
                int offset = i - first;
                //save top
                int top = matrix[first][i];

                //left->top
                matrix[first][i] = matrix[last - offset][first];

                //bottom->left
                matrix[last - offset][first] = matrix[last][last - offset];

                //right->bottom
                matrix[last][last - offset] = matrix[i][last];

                //top -> right
                matrix[i][last] = top;
                matrix[i][last] = top;
            }
        }
    }
}
