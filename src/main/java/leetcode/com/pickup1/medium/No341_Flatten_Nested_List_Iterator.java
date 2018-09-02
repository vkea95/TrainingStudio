package leetcode.com.pickup1.medium;

import leetcode.com.util.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

/**
 * Created by tclresearchamerica on 7/23/16.
 * *****************************************************************************
 * Location:
 * https://leetcode.com/problems/flatten-nested-list-iterator/
 * *****************************************************************************
 * Description:
 * Given a nested indexList of integers, implement an iterator to flatten it.
 * <p>
 * Each element is either an integer, or a indexList -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Given the indexList [[1,1],2,[1,1]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be:[1,1,2,1,1].
 * <p>
 * Example 2:
 * Given the indexList [1,[4,[6]]],
 * <p>
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * *****************************************************************************
 * Thoughts:
 * 1.这是一个递归调用的问题,就是不断地递归调用处理,
 * 2.处理方式有两种,A.初期化的时候,将传入参数拉平,B.在处理过程中拉平
 * 3.感觉B的方法更加靠谱,A的做法还需要分配额外的空间,即在程序初始化的时候,就要新建对象
 * *****************************************************************************
 * Hindsight:
 * 1.根据网解,这样的解法,就是需要Stack这样的数据结构,然后根据这个NestedInteger的数据结构特点,将Iterator压入栈中,
 * 遇到Integer本身的话,直接将其设给next value即可啦
 * 2.找到一个StefanPochmann 的纯Iterator解决方案,实测执行效率略低,Beat约6%左右
 * https://discuss.leetcode.com/topic/41870/real-iterator-in-python-java-c
 * 这个里面用到了ListIterator
 * terator和ListIterator主要区别有：
 * Ref:http://blog.csdn.net/a597926661/article/details/7679765
 * 一、ListIterator有add()方法，可以向List中添加对象，而Iterator不能。
 * 二、ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历。但是ListIterator有hasPrevious()和previous()方法，
 * 可以实现逆向（顺序向前）遍历。Iterator就不可以。
 * 三、ListIterator可以定位当前的索引位置，nextIndex()和previousIndex()可以实现。Iterator 没有此功能。
 * 四、都可实现删除对象，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。
 * 因为ListIterator的这些功能，可以实现对LinkedList等List数据结构的操作。
 * *****************************************************************************
 * Time: 30mins
 * Beat: 64%
 * Bug:1
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 * *****************************************************************************
 */
public class No341_Flatten_Nested_List_Iterator {
}

class NestedIterator_List implements Iterator<Integer> {
    private Stack<ListIterator<NestedInteger>> lists;

    public NestedIterator_List(List<NestedInteger> nestedList) {
        lists = new Stack<>();
        lists.push(nestedList.listIterator());
    }

    public Integer next() {
        hasNext();
        return lists.peek().next().getInteger();
    }

    public boolean hasNext() {
        while (!lists.empty()) {
            if (!lists.peek().hasNext()) {
                lists.pop();
            } else {
                NestedInteger x = lists.peek().next();
                if (x.isInteger())
                    //此处使用了ListIterator的向前遍历方法,当然前提是,该元素必须是Integer
                    //因为在此处,存在一次向前移动的操作,所以时间复杂度直接到2n
                    return lists.peek().previous() == x;
                lists.push(x.getList().listIterator());
            }
        }
        return false;
    }

}

class NestedIterator implements Iterator<Integer> {


    //Point1:需要将Iterator和其属性一起设置给stack
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    NestedInteger curt = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return curt == null ? -1 : curt.getInteger();
    }

    @Override
    public boolean hasNext() {
        //核心处理内容,需要发掘到我们能够取到的下一个有效值啦,这里面会有压栈,出栈和判断整数的操作,
        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop();
                //bug1:只管pop,返回处理自然有while循环接管
//                return false;
            } else {
                //point2:要从stack中取值啦
                NestedInteger object = stack.peek().next();
                if (object.isInteger()) {
                    curt = object;
                    return true;
                } else {
                    stack.push(object.getList().iterator());
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