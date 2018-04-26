package leetcode.com.medium.part01;

/**
 * Created by JianZhang on 2/21/18.
 */
public class No033_Search_in_Roatated_Array {
}

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int len = nums.length;

        int mid = findMid(nums);
        // if (nums[mid] == target) {
        //   return mid;
        // }

        int start = (target <= nums[len - 1]) ? mid : 0;
        int end = (target <= nums[len - 1]) ? (len - 1) : (mid - 1);


        int index = findPos(nums, start, end, target);

        return nums[index] == target ? index : -1;
    }

    private int findPos(int[] nums, int start, int end, int target) {
        while (start < end) {
            int mid = (start + end) >>> 1;
            // System.out.println("mid: " + mid);

            if (nums[mid] == target) {
                start = mid;
                break;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }

        return start;
    }


    private int findMid(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = (start + end) >>> 1;

            if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

//   // look for the first index of the 2nd subArray
//   private int findMid(int[] nums) {

//     int start = 0;
//     int end = nums.length - 1;
//     if (nums[start] < nums[end]) {
//         return 0;
//     }
//     while (start < end) {
//       int mid = (start + end) >>> 1;

//       System.out.println(mid);
//       if (nums[mid] >= nums[start]) {
//         // mid in the first half --> keep in mind if the array in inorder, so start = mid
//         start = mid ;
//       // } else if (nums[mid] == nums[start]) {
//       //   break;
//       } else {
//         // mid in the 2nd half
//         end = mid-1;
//       }
//       System.out.println("start: "+start);
//       System.out.println("end: "+end);
//     }
//       System.out.println("start: "+start);
//     return start;
//   }

//   public int search(int[] nums, int target) {
//     if (nums == null || nums.length  == 0) {
//       return -1;
//     }

//     int mid = findMid(nums);
//     int len = nums.length - 1;
//     int start = target <= nums[len]?mid:0;
//     int end = target <= nums[len]?len:mid - 1;

//     int result = findTargetPos(nums, start, end, target);
//     result = nums[result] == target?result:-1;
//     return result;
//   }

//   private int findPos(int[] nums, int start, int end, int target) {
//     while (start < end) {

//       int mid = (start + end) >>> 1;

//       if (nums[mid] == target) {
//         start = mid;
//         break;
//       } else if (nums[mid] < target) {
//         start = mid + 1;
//       } else {
//         end = end - 1;
//       }
//     }

//     return start;
//   }

}