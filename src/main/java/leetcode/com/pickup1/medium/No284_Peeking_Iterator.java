package leetcode.com.pickup1.medium;

import java.util.Iterator;

/**
 * Created by tclresearchamerica on 6/13/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/peeking-iterator/
 * ****************************************************
 * Description:
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator
 * that support the peek() operation -- it essentially peek() at the element that will be returned
 * by the next call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * ****************************************************
 * Thoughts:
 * 1.这个是在Iterator基础上追加的新功能,那么就要看看需要怎样的修改或控制
 * 首先列出功能特征
 * 本质上就是确认iterator中连续2个元素的关系
 * ****************************************************
 * Time: 20mins
 * Beat: 90%
 * Bug: 1
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No284_Peeking_Iterator {
}

class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> it;
    boolean peekFlg;
    Integer peekInteger;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.it = iterator;
        this.peekFlg = false;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!peekFlg && hasNext()) {
            peekInteger = it.next();
            peekFlg = true;
        }
        return peekInteger;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        // Integer next;
        if (peekFlg) {
            // next = peekInteger;
            peekFlg = false;
            return peekInteger;
        } else {
            return it.next();
        }
    }

    @Override
    public boolean hasNext() {
        //bug1:在peek中用到了 hasNext() 所以需要 追加一个判断 避免返回错误的值
        return peekFlg ? peekFlg : it.hasNext();
    }
}