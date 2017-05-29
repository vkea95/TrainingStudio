package leetcode.com.easy.part0;

/**
 * Created by jason on 2016/2/2.
 * Locations:
 * https://leetcode.com/problems/remove-element/
 * ********************************************************
 * Descriptions:
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * ********************************************************
 * Solutions:
 * 长度和index结合在一起，一旦发现目标，则从数组最后移过来一个元素放进去，然后长度减一
 * ********************************************************
 * Tips:
 * 1.题意：移走制定value的元素，并放入将该位置新的元素，然后返回新的数组长度，在该长度之内，数组含有所有留下来的元素
 * 2.由于移过来的元素也有可能需要被remove，所以不能采用for循环这种自动i++的方式，而是应该用while循环，保证元素移动过来后，
 * 还会进行一次判断，如果是删除的目标则继续操作，否则i++
 */
public class No027_Remove_Element {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int pointer = nums.length - 1;
        int i = 0;
        while (i <= pointer) {
            if (nums[i] == val) {
                nums[i] = nums[pointer--];
            } else {
                i++;
            }
        }
        return pointer + 1;
    }
}
