package leetcode.com.medium;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/11/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/reconstruct-itinerary/
 * ****************************************************
 * Description:
 * Given a indexList of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest
 * lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller
 * lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 * ****************************************************
 * Analysis:
 * 牵扯到遍历的问题,考虑有向图的遍历,
 * 牵扯的数据结构:
 * hashMap:存着点到点的信息
 * stack:存着遍历信息
 * ****************************************************
 * ****************************************************
 * 技术弱点:
 * 1. Collections会对一个list进行排序: Collections.sort(indexList)
 * 2.stack 变list.用subList(0,size())
 * ****************************************************
 */
public class No332_Reconstruct_Itinerary {

    public static void main(String[] args) {
        String[][] trips = {{"AXA", "EZE"}, {"EZE", "AUA"}, {"ADL", "JFK"}, {"ADL", "TIA"}, {"AUA", "AXA"},
                {"EZE", "TIA"}, {"EZE", "TIA"}, {"AXA", "EZE"}, {"EZE", "ADL"}, {"ANU", "EZE"}, {"TIA", "EZE"},
                {"JFK", "ADL"}, {"AUA", "JFK"}, {"JFK", "EZE"}, {"EZE", "ANU"}, {"ADL", "AUA"}, {"ANU", "AXA"},
                {"AXA", "ADL"}, {"AUA", "JFK"}, {"EZE", "ADL"}, {"ANU", "TIA"}, {"AUA", "JFK"}, {"TIA", "JFK"},
                {"EZE", "AUA"}, {"AXA", "EZE"}, {"AUA", "ANU"}, {"ADL", "AXA"}, {"EZE", "ADL"}, {"AUA", "ANU"},
                {"AXA", "EZE"}, {"TIA", "AUA"}, {"AXA", "EZE"}, {"AUA", "SYD"}, {"ADL", "JFK"}, {"EZE", "AUA"},
                {"ADL", "ANU"}, {"AUA", "TIA"}, {"ADL", "EZE"}, {"TIA", "JFK"}, {"AXA", "ANU"}, {"JFK", "AXA"},
                {"JFK", "ADL"}, {"ADL", "EZE"}, {"AXA", "TIA"}, {"JFK", "AUA"}, {"ADL", "EZE"}, {"JFK", "ADL"},
                {"ADL", "AXA"}, {"TIA", "AUA"}, {"AXA", "JFK"}, {"ADL", "AUA"}, {"TIA", "JFK"}, {"JFK", "ADL"},
                {"JFK", "ADL"}, {"ANU", "AXA"}, {"TIA", "AXA"}, {"EZE", "JFK"}, {"EZE", "AXA"}, {"ADL", "TIA"},
                {"JFK", "AUA"}, {"TIA", "EZE"}, {"EZE", "ADL"}, {"JFK", "ANU"}, {"TIA", "AUA"}, {"EZE", "ADL"},
                {"ADL", "JFK"}, {"ANU", "AXA"}, {"AUA", "AXA"}, {"ANU", "EZE"}, {"ADL", "AXA"}, {"ANU", "AXA"},
                {"TIA", "ADL"}, {"JFK", "ADL"}, {"JFK", "TIA"}, {"AUA", "ADL"}, {"AUA", "TIA"}, {"TIA", "JFK"},
                {"EZE", "JFK"}, {"AUA", "ADL"}, {"ADL", "AUA"}, {"EZE", "ANU"}, {"ADL", "ANU"}, {"AUA", "AXA"},
                {"AXA", "TIA"}, {"AXA", "TIA"}, {"ADL", "AXA"}, {"EZE", "AXA"}, {"AXA", "JFK"}, {"JFK", "AUA"},
                {"ANU", "ADL"}, {"AXA", "TIA"}, {"ANU", "AUA"}, {"JFK", "EZE"}, {"AXA", "ADL"}, {"TIA", "EZE"},
                {"JFK", "AXA"}, {"AXA", "ADL"}, {"EZE", "AUA"}, {"AXA", "ANU"}, {"ADL", "EZE"}, {"AUA", "EZE"}};
        String[][] trip2 = {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        No332_Reconstruct_Itinerary obj = new No332_Reconstruct_Itinerary();
        obj.findItinerary(trip2);
    }

    public List<String> findItinerary(String[][] tickets) {

        //bug2:
        int tripLength = tickets.length + 1;

        HashMap<String, List<String>> tripMap = new HashMap<>();
        Stack<String> stack = new Stack<>();
        for (String[] trip : tickets) {
            if (!tripMap.containsKey(trip[0])) {
                tripMap.put(trip[0], new ArrayList<>());
            }
            tripMap.get(trip[0]).add(trip[1]);
        }

        //排序,进而保证出来的第一个答案就是最佳答案
        for (List<String> value : tripMap.values()) {
            //bug1:don't know the collectins.sort(indexList)
            Collections.sort(value);
        }
        stack.push("JFK");
        DFSSearch("JFK", tripMap, stack, tripLength);

        return stack.subList(0, stack.size());
    }

    private boolean DFSSearch(String start,
                              HashMap<String, List<String>> tripMap,
                              Stack<String> stack,
                              int tripLenth) {
        //队列已满
        if (stack.size() == tripLenth)
            return true;

        List<String> list = tripMap.get(start);

        if (list != null) {

            //bug7:indexList 可能为null
            for (int i = 0; i < list.size(); i++) {
                //
                String end = list.get(i);
                stack.push(end);
                //bug3:临时删除已经被使用的地点名称
                list.remove(list.get(i));
                if (DFSSearch(end, tripMap, stack, tripLenth)) {
                    //bug5:查找成功就返回好了
                    return true;
                } else {
                    stack.pop();
                    //bug4:如果查找失败,还要恢复list的现场
                    list.add(i, end);
                }
            }
            return false;
        }

        return false;


    }
}

