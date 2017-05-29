package leetcode.com.medium;

/**
 * Created by tclresearchamerica on 5/6/16.
 * ****************************************************
 * Description:
 * Additive number is a string whose digits can form additive sequence.
 * <p>
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 * each subsequent number in the sequence must be the sum of the preceding two.
 * <p>
 * For example:
 * "112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 * <p>
 * 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * "199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199.
 * 1 + 99 = 100, 99 + 100 = 199
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 * <p>
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 * <p>
 * Follow up:
 * How would you handle overflow for very large input integers?
 * ****************************************************
 * Analysis:
 * 1.问题:那么这个所谓的前两个数,是不是明确是前两位,而不是可能有不同位数的数构成的两个数呢? 我的理解是,可能为多位  例子不是写了嘛
 * 2.看着这个答案,判断用dynamic programing来解决问题.
 * 3.关于follow up,超大整数的话,从尾部用减法实现?或者有别的巧妙解决方式
 * 4.我的想法是逆向思维,从sum入手,sum的属性有index和length,第二个数的length小于等于sum的length,然后还可以用string的find方法
 * sum的length是变量,会变化,变化的不是index  -->实现起来好麻烦,放弃
 * 5.网络上有个类似DFS的答案哦
 * ****************************************************
 * Solution:
 * https://leetcode.com/discuss/101075/1ms-java-solution-beats-96%25-two-tricks-stop-search-early-stage
 * 先对String进行数组化,再使用DFS的方式,对数字进行深度挖掘,数字的处理也是根据数组的循环来进行计算的.
 * ****************************************************
 */
public class No306_Additive_Number {
    public static void main(String[] args) {
        String numbers = new String("1234567890");
        System.out.println("index:9-10:" + numbers.substring(9, 10));
        System.out.println("index:1:2:" + numbers.substring(1, 2));

        String num = "199100199299";
        No306_Additive_Number obj = new No306_Additive_Number();
        System.out.println(obj.isAdditiveNumber(num));
//        numbers.l
    }

    public boolean isAdditiveNumber(String num) {
        return dfs(num.toCharArray(), 0, -1, -1, 1, 1, false);
    }

    private boolean dfs(char[] num, int index, long first, long second, int firstLength, int secondLength,
                        boolean isSummed) {
        if (index < num.length) {
            boolean isAdditive = false;
            long crt = 0;
            int crtLength = 1;

            //bug2:未对crtLength++
            for (int i = index; i < num.length; i++, crtLength++) {
                //bug3:未对'0'进行判断,此处保证只对数值0,进行计算,02,03这样的数值都是非法...
                if (num[index] == '0' && i != index) {
                    return false;
                }
                //bug1:don't know how to calculate the number
                crt = crt * 10 + num[i] - '0';
                int maxLength = Math.max(firstLength, secondLength);
                // If the current number's length is greater than left char number, stop the search
                if (maxLength > num.length - index) {
                    return false;
                }
                if (first == -1) {
                    isAdditive = dfs(num, i + 1, crt, second, crtLength, secondLength, isSummed);
                    if (isAdditive) {
                        return true;
                    }
                } else if (second == -1) {
                    isAdditive = dfs(num, i + 1, first, crt, firstLength, crtLength, isSummed);
                    if (isAdditive) {
                        return true;
                    }
                } else {
                    if (crtLength < maxLength) {
                        continue;
                    }
                    long sum = first + second;
                    // if the sum is already smaller than current value, there is no need to continue the search,
                    // because current value is inscreasing as it gets more chars.
                    if (sum < crt) {
                        return false;
                    } else if (sum == crt) {
                        isAdditive = dfs(num, i + 1, second, sum, secondLength, maxLength, true);
                        if (isAdditive) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else {
            //bug4:处理是否累加,在String的长度范围内没有解决的问题的.的情况的,
            return isSummed;
        }
    }


}
