package leetcode.com.medium.part22;

import java.util.*;

/**
 * Created by tclresearchamerica on 5/3/16.
 * *******************************************
 * Description:
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * Here is an example. Assume that the iterator is initialized to the beginning of the indexList: [1, 2, 3].
 * Call next() gets you 1, the first element in the indexList.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 * *******************************************
 * Analysis:
 * 考虑可以使用队列,配着iterator使用
 * *******************************************
 * Bug1:
 * 设计思路上面出现了问题,当next()之后,需要对peek设个null,这样比较安全,保证在取peek的时候,可以返回null-->这个是没有想到的设计问题
 * Bug2:
 * 在peek()和next(),hasNext()的关系,还需要进一步细化关系
 * *******************************************
 */
public class No284_Peeking_Iterator {
}


class PeekingIterator implements Iterator<Integer> {

    private Integer peek = 0;
    private boolean peekFlg;
    private Iterator<Integer> it;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        peekFlg = false;

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (!peekFlg) {
            //疑惑:感觉加上hasNext()的判断,会更好一些.
            // if (it.hasNext())
            peek = it.next();
            peekFlg = true;
        }
        return peek;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peekFlg) {
            peekFlg = false;
            Integer result =peek;
            //bug1:
            peek=null;
            return result;
        }

        return it.next();
    }

    @Override
    public boolean hasNext() {
//        if (peekFlg) {
//            peekFlg = false;
//            return true;
//        }
//        return it.hasNext();
        //bug3: 上面的被注释的代码,会导致结果不正确,但不知为何
        return  peekFlg || it.hasNext();
    }
}