package leetcode.com.pickup1.hard;

/**
 * Created by tclresearchamerica on 7/22/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/integer-to-english-words/
 * ****************************************************
 * Description:
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 231 - 1.
 * <p>
 * For example,
 * 123 -> "One Hundred Twenty Three"
 * 12345 -> "Twelve Thousand Three Hundred Forty Five"
 * 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * ****************************************************
 * Thought:
 * 1.感觉这道题的难点在于处理不同的位数数值的英文表示方式,这样的话就需要各种位数所对应的英语表达方式,然后表示出来
 * 2.刚刚,看了答案,其实这里面还是有些tricky的地方的。
 * 3.比如需要以1000为单位处理数字,
 * 4.美国的数字表示,是以1000倍为划分的,所以我们也要这样划分,比较吻合整除1000的处理过程,而不用额外的定义
 * ****************************************************
 * Time: 25mins
 * Beat: 40%
 * Bug: 3
 * ****************************************************
 * Hindsight:
 * 1.这道题的解答里面,将不同的数值范围分别放到3个不同的数组中,然后再分别处理-->这个要记住呢。
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No273_Integer_to_English_Words {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};


    public String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                //bug3:在处理单词简单的空格时出现问题,我这次的处理是在前面补空格,所以需要修改下
                words = helper(num % 1000) + " " + THOUSANDS[i] + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    //只处理1000范畴内的数字,简化处理啦
    private String helper(int number) {
        StringBuilder sb = new StringBuilder();
        int n = number / 100;
        int mod = number % 100;
        sb.append(n == 0 ? "" : " " + LESS_THAN_20[n] + " Hundred");
        //Bug1:Less 20,所以极值只能到19
        if (mod <= 19) {
            sb.append(mod == 0 ? "" : " " + LESS_THAN_20[mod]);
        } else {
            int n2 = mod / 10;
            //bug2:应该用传进来的数值进行计算,而不是刚刚得到的商进行计算n%10 -> mod%10
            int mod2 = mod % 10;
            sb.append(" " + TENS[n2]);
            sb.append(mod2 == 0 ? "" : " " + LESS_THAN_20[mod2]);

        }
        return sb.toString();
    }

}
