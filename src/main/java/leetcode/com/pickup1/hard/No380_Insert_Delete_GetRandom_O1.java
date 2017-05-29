package leetcode.com.pickup1.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tclresearchamerica on 8/14/16.
 * ****************************************************
 * Location:
 * ****************************************************
 * Description:
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of
 * being returned.
 * Example:
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 1 is the only number in the set, getRandom always return 1.
 * randomSet.getRandom();
 * ****************************************************
 * Thought:
 * 1.考虑使用Set,但是这个的问题是不能够使用getRandom,即不可以实现通过下标访问元素的能力
 * 2.如果,要是使用下标的话,那就要用List
 * ****************************************************
 * Hindsight:
 * 1.按照用ArrayList的方式来进行实现,发现插入重复元素的时候,不会返回false
 * 2.Java的random使用方法不熟悉
 */
public class No380_Insert_Delete_GetRandom_O1 {
}

class RandomizedSet {
    Set<Integer> set;
    List<Integer> list;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        set = new HashSet<>();
        list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
//        return set.add(val);
        if (list.contains(Integer.valueOf(val)))
            return false;
        list.add(val);
        return true;

    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
//        return set.remove(val);
        return list.remove(Integer.valueOf(val));
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        //bug1:应该是random和size进行乘法之后,再进行强制类型转换到int
        return list.get((int)( Math.random() * list.size()));
    }
}
/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */