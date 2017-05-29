package leetcode.com.hard.part0;

import java.util.*;

/**
 * Created by jason on 2016/2/2.
 * Locations：
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * ************************************************************************
 * Descriptions:
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 * and without any intervening characters.
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * You should return the indices: [0,9].
 * (order does not matter).
 * *************************************************************************
 * Solutions:
 * *************************************************************************
 * Analysis:
 * 1.因为所有的word长度都一致,且顺序没有关系,那么就可以将他们看成是同样的字符咯,
 * 那么将他们都替换成同样的字符#,进行替换的话,也没有关系,没有想到替换错误的情景,将替换后的字符串,按照该这个东东进行切割数组? 还是循环find?
 * 后者似乎更好些..
 * 2.换个思路呗,将所有的word都存入hashMap中,那么循环这个map去匹配字符,当然开始的index要放进去的,中途如果没有匹配上,那么就直接break返回
 * 一整套hashMap都循环成功后,将value搞成数组,并排序,算算开始和终了的index值中间,是不是满足长度倍增的关系
 * 3.这个又回到了那个点,就是一个key,对应一个value,但是要求value之间的值是等差的.今晨的想法每次寻找到最远距离的index,算出理性的开始index
 * 然后在寻找其他string时候,检查他们的index是否在这个理想的区间范围内,如果不在,就要寻找第二次,但是
 * 设想用PriorityQueue,每次放我们自己定义的数据结构,同时排序,只要算最后一个和第一个差值是否满足条件即可,不满足条件的话就用最后一个来搞
 * 知道满足条件,
 * 实践证明,依然TLE,因为复杂度是(n*log(n)),原因在处理大批量重复的word时候,会发生超时,
 * *************************************************************************
 * 网络答案:
 * 1.活动窗口,这个是来源TCP/IP的一个算法,自己没有搞定也不算丢人吧... 这个算法和它的核心思想要背下来啊啊啊啊啊
 * *************************************************************************
 * Bug:
 * 1.因为结果集可以重复,所以startIndex在第一次循环的时候都是从零开始,第二次就从index+len开始了
 * 2.还是需要处理并考虑重复word的问题
 *
 * *************************************************************************
 */
public class No030_Substring_with_Concatenation_of_All_Words {
    public static void main(String[] args) {
        No030_Substring_with_Concatenation_of_All_Words obj = new No030_Substring_with_Concatenation_of_All_Words();
        String[] words = {"a", "a", "a", "a", "a", "a", "a", "a", "a", "a"};
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String[] words2 = {"word", "good", "best", "good"};
        s = "1wordgoodgoodgoodbestword";

//        obj.findSubstring(s, words2);
    }


    public List<Integer> findSubstring_TLE_II(String s, String[] words) {

        if (s == null || words == null || words.length == 0)
            return null;

        PriorityQueue<MyEntry> queue = new PriorityQueue<>(new MyEntryCompare());
        List<Integer> rst = new ArrayList<>();
        int idealStartIndex = 0;
        int wordDistance = words[0].length() * (words.length - 1);
        int endIndex = wordDistance;


        //initialize queue
        for (String word : words) {
            queue.offer(new MyEntry(word, -1));
        }

        //
        int index = 0;
        while (index != -1) {
            //bug3:如果不向队列插入元素,队列里面的元素顺序是不会改变的
            MyEntry entry = queue.poll();
            String word = entry.key;
            int oldWordIndex = entry.value;
            index = s.indexOf(word, Math.max(oldWordIndex + 1, idealStartIndex));
            if (index > endIndex) {
                //bug1:改用新的较大的值区间wordDistance
                idealStartIndex = index - wordDistance;
                endIndex = index;
            }
            queue.offer(new MyEntry(word, index));
            if ((endIndex - queue.peek().value) == wordDistance) {
                rst.add(queue.peek().value);
                idealStartIndex = queue.peek().value + words[0].length();
            }
        }


        return rst;

    }


    class MyEntry {
        String key;
        Integer value;

        public MyEntry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

    }

    class MyEntryCompare implements Comparator<MyEntry> {

        @Override
        public int compare(MyEntry o1, MyEntry o2) {
            //bug2:升序的话:o1.value>o2.value return -1
            if (o1.value > o2.value) return 1;
            return -1;
        }
    }

    public List<Integer> findSubstring_NotDone(String s, String[] words) {
        if (s == null || words == null || words.length == 0) return null;
        List<Integer> rst = new ArrayList<>();
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String word : words) {
            hashMap.put(word, -1);
        }
        int len = words[0].length();
        int startIndex = 0;
        int endIndex = s.length();
        int searchStartIndex = 0;
        int idealDist = (words.length - 1) * len;
        while (startIndex < endIndex) {
            if (compareLoop(hashMap, rst, s, startIndex, len)) {
                do {
                    List<Integer> list = new ArrayList<>(hashMap.values());
                    Collections.sort(list);
                    int distance = list.get(list.size() - 1) - list.get(0);
                    if (distance == idealDist) {
                        rst.add(list.get(0));
                        startIndex += len;
                    } else {
                        int idealStartIndex = list.get(list.size() - 1) - idealDist;
                        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
                            if (entry.getValue() < idealStartIndex) {
                                int index = s.indexOf(entry.getKey(), startIndex + entry.getValue());
                                if (index == -1) {
                                    return rst;
                                } else {
                                    hashMap.put(entry.getKey(), index);
                                    //we got the index that is bigger than we look for
                                    if (index > list.get(list.size() - 1)) {

                                    }
                                }
                            }
                        }
                        for (int i = 0; i < list.size() && list.get(i) < idealStartIndex; i--) {

                        }
                    }

                } while (true);
            }
            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {

                int index = s.indexOf(entry.getKey(), searchStartIndex);
                if (index == -1) {
                    return rst;
                } else {
                    hashMap.put(entry.getKey(), index);
                }
            }
            List<Integer> list = new ArrayList<>(hashMap.values());
            Collections.sort(list);
            int distance = list.get(list.size() - 1) - list.get(0);
            if (distance == idealDist) {
                rst.add(list.get(0));
            } else {

            }
            searchStartIndex += len;

        }


        return rst;
    }

    private boolean compareLoop(HashMap<String, Integer> hashMap, List<Integer> list, String s, int startIndex, int len) {
        int endIndex = s.length();
        while (startIndex < endIndex) {

            for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {

                int index = s.indexOf(entry.getKey(), startIndex);
                if (index == -1) {
                    return false;
                } else {
                    hashMap.put(entry.getKey(), index);
                }
            }
        }
        return true;
    }

    public List<Integer> findSubstring_TLE(String s, String[] words) {
        String temp = null;
        StringBuilder sb = new StringBuilder();
        List<Integer> rst = new ArrayList<>();
        int wordsLength = words.length;
        int wordLength = words[0].length();
        String sharpStr = null;
        //Bug1: TLE 估计是在这个大量操作String对象的时候,开始超时的,因为如果数组words的长度很大,且存在于s中,那么就会比较耗时的处理...
        for (String word : words) {

            temp = s.replaceAll(word, "#");
            sb.append("#");
        }
        //Bug1: TLE 结束
        sharpStr = sb.toString();

        int startIndex = 0;
        int times = 0;
        while (true) {

            int index = temp.indexOf(sharpStr, startIndex);
            if (index == -1) {
                break;
            } else {
                rst.add(index + wordLength * wordLength * times);
                times++;
                startIndex = index + wordLength * wordLength * times;
            }
        }
        return rst;

    }
}
