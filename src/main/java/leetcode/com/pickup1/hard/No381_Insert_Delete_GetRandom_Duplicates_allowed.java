package leetcode.com.pickup1.hard;

import java.util.*;

/**
 * Created by tclresearchamerica on 9/26/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * ****************************************************
 * Description:
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each element
 * being returned is linearly related to the number of same value the collection contains.
 * Example:
 * <p>
 * // Init an empty collection.
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // Inserts 1 to the collection. Returns true as the collection did not contain 1.
 * collection.insert(1);
 * <p>
 * // Inserts another 1 to the collection. Returns false as the collection contained 1. Collection now contains [1,1].
 * collection.insert(1);
 * <p>
 * // Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
 * collection.insert(2);
 * <p>
 * // getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
 * collection.getRandom();
 * <p>
 * // Removes 1 from the collection, returns true. Collection now contains [1,2].
 * collection.remove(1);
 * <p>
 * // getRandom should return 1 and 2 both equally likely.
 * collection.getRandom();
 * ****************************************************
 * Thoughts:
 * <p>
 * ****************************************************
 * Ref:
 * http://blog.csdn.net/wisimer/article/details/52198718
 * 题目的意思是要你设计一个数据结构，使得它的插入和删除时间复杂度都是O(1)，并且能够随机返回一个元素，
 * 而返回这个元素的概率也是和这个元素的个数是线性相关的。允许插入相同的元素。
 * <p>
 * 要说的是这个数据结构内部可以使用HashMap，而不必自己从0开始写一个。
 * <p>
 * 来说一下大概的思路吧，这个数据结构暂且就叫 RandomizedCollection ，它有一个 Map<Integer, List<Integer>>类型的成员变量 data ，
 * 用于保存当前已经插入的元素。每次插入元素val，首先判断data里是否存在val这个key，如果不存在，则首先插入key为val，
 * 值为ArrayList对象，再将val保存到对应的ArrayList中去，这样就保证来可以插入重复的元素，并且时间复杂度为O(1)。
 * 同理，如果要删除一个元素val，则首先找到以val为key的ArrayList，再从其中移除一个val。
 * <p>
 * <p>
 * 而对于随机返回一个元素，我们可以用一个List<Integer>类型的成员变量 list 来保存每个val，用mSize来保存所有元素的总个数，
 * 生成一个介于0到mSize之间的随机数 random 。由于List可以保存重复的元素，所以可以直接通过list.get(random)来返回一个随机元素。
 * 看一下Java代码实现：
 * ****************************************************
 * Hindsight:
 * 1.要仔细地读题,需要注意的是,insert和remove都会有返回bool值的
 * 2.因为都要求Linear时间复杂度,考虑HashMap来解决问题,又因为要getrandom的index,所以要有个维持index的ds来处理,那么就用ArrayList了
 * -->
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No381_Insert_Delete_GetRandom_Duplicates_allowed {
}

class RandomizedCollection {
    Random r = new Random();

    Map<Integer, List<Integer>> indexMap;
    List<Integer> valList;

    /**
     * Initialize your data structure here.
     */
    public RandomizedCollection() {
        indexMap = new HashMap<>();
        valList = new ArrayList<>();
    }

    /**
     * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (!indexMap.containsKey(val)) {
            //因为含有相同的数值,而返回false
            indexMap.put(val, new ArrayList<>());


        }
        //因为要把val存入list中,所以要把index存入Map中
        indexMap.get(val).add(val);
        //新的元素永远放在末尾处
        valList.add(val);

        return true;
    }

    /**
     * Removes a value from the collection. Returns true if the collection contained the specified element.
     */
    public boolean remove(int val) {
        if (indexMap.containsKey(val)) {
            if (indexMap.get(val).size() > 0) {
                indexMap.get(val).remove(Integer.valueOf(val));
                valList.remove(Integer.valueOf(val));
                return true;
            }
        }
        return false;
//
//        if (!indexMap.containsKey(val)) {
//            //因为不含有数值,而返回false
//            return false;
//        } else {
//
////            //因为要把val在list中的位置,挪给其他的元素使用,所以要把index记忆下来
////            int index = indexMap.get(val);
////            indexMap.remove(val);
////            valList.set(index, valList.get(valList.size() - 1));
////            valList.remove(valList.size() - 1);
//            return true;
//        }

    }

    /**
     * Get a random element from the collection.
     */
    public int getRandom() {
        //Important:取random值的方法
        return valList.get(r.nextInt(valList.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */