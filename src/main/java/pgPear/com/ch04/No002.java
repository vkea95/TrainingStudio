package pgPear.com.ch04;

/**
 * Created by tclresearchamerica on 9/25/16.
 * ****************************************************
 * Description:
 * 用对数次比价,完成在数组总寻找第一个出现的target
 * ****************************************************
 * Reading:
 * Pg88: 给出了解释和推理过程,果然简单明了,当做是整形的最佳材料了呢
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No002 {
    public static void main(String[] args) {
        No002 obj = new No002();
        int index = obj.findIndex(new int[]{2, 2, 2, 2, 2, 2, 2, 2}, 2);
        System.out.println(index);
        index = obj.standardBF(new int[]{2, 3, 4}, 2);
        System.out.println(index);
    }

    public int findIndex(int[] array, int target) {
        if (array == null || array.length == 0) return -1;

        //此处是确保 precondition
        if (array[0] == target) return 0;
        if (array[0] < target) return -1;

        int start = 0, end = array.length - 1;
        int mid = 0;
        while (start + 1 < end) {
//            /*invariant:array[start]< target && array[end] >= target && start< end */   -->precondition
            mid = start + (end - start) / 2;
            if (array[mid] < target) {
                start = mid;
//            } else if (array[mid] > target) {
//                end = mid - 1;
            } else {
                //bug1:为了保证找到第一个target,此处需要将mid->start,而不是mid+1 -> start
//                start = mid + 1;
                start = mid + 1;
            }
        }
        /* assert start + start =end && array[start]<target && array[end]>= target*/
        if (array[end] == target) {
            return end;
        }
        return -1;
    }


    public int standardBF(int[] array, int target) {
        if (array == null || array.length == 0) return -1;
        int start = 0;
        int end = array.length - 1;
        /*assert start < end && array[0]<=array[1]...<=array[end] &&target musbbe (start,end)*/
        if (array[start] > target) return -1;
        if (array[end] < target) return -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == target) return mid;
            else if (array[mid] < target) start = mid + 1;
            else end = mid - 1;

        }
        //因为前面保证了数字要么在数组中,要么不在,此时就不需要做额外的判断了呢 -->推论是不正确的,
        //正确的理解是,如果没在程序的while循环中,将答案择出来,那么答案必然在start或end中,否则前面的assert就不正确啦
        if (array[start] == target) return start;
        else if (array[end] == target) return end;
        return -1;

    }
}
