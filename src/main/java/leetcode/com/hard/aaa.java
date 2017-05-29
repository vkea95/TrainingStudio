package leetcode.com.hard;

/**
 * Created by jason on 2016/3/2.
 */
public class aaa {
    //方法一:

    public static void main(String[] args) {

        String abc = new String();
        abc = "0123456789";
        System.out.println("1" + abc.substring(0));
        System.out.println("2" + abc.substring(0, 0));
        System.out.println("3" + abc.substring(0, 1));
        System.out.println("4" + abc.substring(0, 2));
        aaa aa = new aaa();
        System.out.println(aa.minWindow("ABCBECODEBANC", "ABC"));
    }

    void initTargetHash(int[] targethash, String Target) {
        for (int i = 0; i < Target.length(); i++) {
            targethash[Target.charAt(i)]++;
        }
    }

    boolean valid(int[] sourcehash, int[] targethash) {

        for (int i = 0; i < 256; i++) {
            if (targethash[i] > sourcehash[i])
                return false;
        }
        return true;
    }

    public String minWindow(String s, String t) {
        String rst = "";
        int[] target = new int[256];
        int[] source = new int[256];

        initTargetHash(target, t);

        int i = 0;
        int j = 0;
        int ans = Integer.MAX_VALUE;

        for (i = 0; i < s.length(); i++) {

            while (!valid(source, target) && j < s.length()) {

                source[s.charAt(j++)]++;
            }
            if (valid(source, target)) {
                if (ans > (j - i)) {
                    ans = j - i;
                    rst = s.substring(i, j);
                }
            }
            source[s.charAt(i)]--;
        }
        return rst;


    }
}
