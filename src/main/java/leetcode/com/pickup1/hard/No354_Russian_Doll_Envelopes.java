package leetcode.com.pickup1.hard;

import java.util.*;

/**
 * Created by tclresearchamerica on 9/23/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/russian-doll-envelopes/
 * ****************************************************
 * Description:
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than
 * the width and height of the other envelope.
 * <p>
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * <p>
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes
 * you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 * ****************************************************
 * Thought & process
 * 1.将套娃写在白板上,按递增顺序拍好后,发现可以用dp来实现,每个套娃都向左寻找第一个可以放到自己里面的娃娃
 * 2.排序的接口如何安装呢
 * ****************************************************
 * Time: 33 mins
 * Bug: 1.漏掉了设计中的break
 *      2.在回溯过程中没有考虑,娃娃们会没有交集的情况
 * Beat:40%
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
public class No354_Russian_Doll_Envelopes {
    public static void main(String[] args) {

        int[][] dolls = new int[5][2];
        dolls[0][0] = 46;
        dolls[0][1] = 89;

        dolls[1][0] = 50;
        dolls[1][1] = 53;

        dolls[2][0] = 52;
        dolls[2][1] = 68;

        dolls[3][0] = 72;
        dolls[3][1] = 45;

        dolls[4][0] = 77;
        dolls[4][1] = 81;
        No354_Russian_Doll_Envelopes obj = new No354_Russian_Doll_Envelopes();
        System.out.print(obj.maxEnvelopes(dolls));
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;

        List<RussianDoll> list = new ArrayList<>();
        for (int[] envelope : envelopes) {
            list.add(new RussianDoll(envelope));
        }

        Collections.sort(list, new Comparator<RussianDoll>() {
            @Override
            public int compare(RussianDoll o1, RussianDoll o2) {
                if (o1.w < o2.w) return -1;
                else if ((o1.w == o2.w && o1.h < o2.h)) return -1;
                return 1;
            }
        });

        int max = 1;
        for (int i = 1; i < list.size(); i++) {
            RussianDoll curDoll = list.get(i);
            int internMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(j).w < curDoll.w && list.get(j).h < curDoll.h) {
                    //bug2:没有考虑娃娃大小不一致的问题,它们不一致导致大的娃娃可以分别装下它们,或是可以一起装下它们
//                    curDoll.dollNum += list.get(j).dollNum;
                    internMax = internMax > list.get(j).dollNum ? internMax : list.get(j).dollNum;
                    //bug:forget to break; then get the wrong answer
//                    break;
                }
            }
            curDoll.dollNum += internMax;
            max = max > curDoll.dollNum ? max : curDoll.dollNum;
        }
        return max;
    }
}

class RussianDoll {
    public int w, h, dollNum;


    public RussianDoll(int[] size) {
        w = size[0];
        h = size[1];
        dollNum = 1;
    }
}