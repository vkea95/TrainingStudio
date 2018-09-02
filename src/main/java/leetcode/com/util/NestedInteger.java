package leetcode.com.util;

import java.util.List;

/**
 * Created by tclresearchamerica on 5/13/16.
 */
public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a nested indexList.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested indexList
    public Integer getInteger();

    // @return the nested indexList that this NestedInteger holds, if it holds a nested indexList
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}