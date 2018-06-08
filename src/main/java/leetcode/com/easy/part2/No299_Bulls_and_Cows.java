package leetcode.com.easy.part2;

/**
 * Created by tclresearchamerica on 5/5/16.
 * ****************************************************
 * Location:
 * https://leetcode.com/problems/bulls-and-cows/
 * ****************************************************
 * Description:
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend
 * to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many
 * digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many
 * digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive
 * guesses and hints to eventually derive the secret number.
 * For example:
 * Secret number:  "1807"
 * Friend's guess: "7810"
 * Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)
 * Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls
 * and B to indicate the cows. In the above example, your function should return "1A3B".
 * <p>
 * Please note that both secret number and friend's guess may contain dupNumber digits, for example:
 * <p>
 * Secret number:  "1123"
 * Friend's guess: "0111"
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should
 * return "1A1B". You may assume that the secret number and your friend's guess only contain digits,
 * and their lengths are always equal.
 * ****************************************************
 * Analysis:
 * 处理cow需要用到2个数组,一个给secret,一个给guess,算cow的时候,取相同下标的2者的最小值累加
 * 按位比较,相等的话bull++,不等的话,
 * ****************************************************
 * ****************************************************
 */
public class No299_Bulls_and_Cows {
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;

        int[] array2 = new int[10];
        int[] array3 = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = Integer.parseInt(secret.substring(i, i + 1));
            int g = Integer.parseInt(guess.substring(i, i + 1));
            if (s == g) {
                bulls++;
            } else {
                array2[s]++;
                array3[g]++;
            }

        }

        for (int i = 0; i < array2.length; i++) {
            cows += Math.min(array2[i], array3[i]);
        }
        return bulls + "A" + cows + "B";
    }
}
