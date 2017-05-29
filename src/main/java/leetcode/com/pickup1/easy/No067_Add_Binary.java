package leetcode.com.pickup1.easy;

/**
 * Created by jason on 2016/7/21.
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 * ****************************************************
 */
public class No067_Add_Binary {
    public String addBinary(String a, String b) {
        if(a==null) return b;
        if(b==null) return a;
        int i=a.length()-1;
        int j=b.length()-1;
        int carry=0;
        String result ="";
        while(i>=0||j>=0){
            int temp =carry;
            if(i>=0){
                temp+=a.charAt(i--)-'0';
            }
            if(j>=0){
                temp+=b.charAt(j--)-'0';

            }
            result =String.valueOf(temp%2)+result;
            carry=temp/2;
        }
        if(carry!=0){
            result=String.valueOf(carry) + result;

        }
        return result;
    }

}
