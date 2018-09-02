package leetcode.com.medium;

import leetcode.com.util.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by tclresearchamerica on 5/13/16.
 * ****************************************************
 * location:
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * ****************************************************
 * Description:
 * Given a nested indexList of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a indexList -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Given the indexList [[1,1],2,[1,1]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * <p>
 * Example 2:
 * Given the indexList [1,[4,[6]]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * ****************************************************
 * Analysis:
 * NestedInteger:内部还是有可能是nestedInteger,所以就是一个不断内嵌循环的结构
 * 1.网络答案是这样的,用一个stack存储每个List的iterator,
 * HasNext:第一次不必判断,此后,需要判断是否为Integer,若本层循环完毕,则继续循环,至全部判断完毕
 * next:直接返回在hasnext中存下的数据
 * ****************************************************
 * 事后诸葛亮:
 * 1.选择合适的数据结构Stack,没准就可以帮助我建立起来正确的思路了,因为它可以动态的处理Iterator循环枯竭的问题,且是按顺序处理的
 * 2.Iterator也可以当做对象进行处理的呢!!!
 * 3.这样内嵌的循环结构,就可以考虑用Iterator,和stack/queue配合使用了,其中没有牵扯到修改嘛
 * ****************************************************
 * ****************************************************
 */
public class No341_Flatten_Nested_List_Iterator {
}


class NestedIterator implements Iterator<Integer> {

    private Stack<Iterator<NestedInteger>> nestedStack;
    private Integer nextInteger;

    public NestedIterator(List<NestedInteger> nestedList) {
        nestedStack = new Stack<>();
        nestedStack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextInteger;
    }

    @Override
    public boolean hasNext() {
        nextInteger = null;
        while (!nestedStack.isEmpty()) {
            //pop the finished Iterator
            if (!nestedStack.peek().hasNext()) nestedStack.pop();
            else {
                //get the next element from the Iterator
                NestedInteger nestedInteger = nestedStack.peek().next();
                //check if the nestedInteger type is integer
                if (nestedInteger.isInteger()) {
                    nextInteger = nestedInteger.getInteger();
                    return true;
                } else {
                    //push the newest iterator, wait for the loop operation
                    nestedStack.push(nestedInteger.getList().iterator());
                }
            }
        }
        return false;

    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */