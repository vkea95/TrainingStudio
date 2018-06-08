package leetcode.com.pickup1.easy;

/**
 * Created by tclresearchamerica on 8/26/16.
 * ***************************************************************
 * Location:
 * https://leetcode.com/problems/bulls-and-cows/
 * ***************************************************************
 * Description:
 * You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to
 * guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in
 * said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match
 * the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and
 * hints to eventually derive the secret number.
 * <p>
 * For example:
 * <p>
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
 * In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return
 * "1A1B".
 * You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal
 * ***************************************************************
 * Thought:
 * 题意没有一下搞明白。最终表示的意识就是,猜中的就是bull,否则就是cow,
 * 但是对于cow而言,算法不一样。还要看我们提出的数字里面,有多少是和对方的数字有交集的,要算出交集数字的量,然后当做cow提交
 * 在这个算法里面,用了不断对数组对应元素进行累加来完成的。算的时候又要取最小值
 * ***************************************************************
 * Time:20 mins
 * Beat: 84%
 * Bug: -
 * ***************************************************************
 * Hindsight:
 * 1.题意理解过程出现了问题,最后还是理解了
 * 2.算集合的方式,还不错,可以考虑保留下来
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 * ***************************************************************
 */
public class No299_Bulls_and_Cows {
    public static void main(String[] args) {
        No299_Bulls_and_Cows obj = new No299_Bulls_and_Cows();
        obj.getHint("1807", "9877");
    }

    public String getHint(String secret, String guess) {

        int bulls = 0, cows = 0;
        int[] array2 = new int[10];
        int[] array3 = new int[10];
        for (int i = 0; i < guess.length(); i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';
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
