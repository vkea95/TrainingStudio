package design.com.tinyURL;

public class test {
    public static void main(String[] args) {
        System.out.println(test.convertTo62(39));
        System.out.println(test.convertTo62(15));
        System.out.println(test.convertTo62(16));
        System.out.println(test.convertTo62(17));
        System.out.println(test.convertTo62(18));
    }

    public static String convertTo62(int size) {
//        how many url it supports? same with base 10,
//      but it is short log 62 (number of Url)
        char[] Encode = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'd', 'e', 'f', 'g'};
        int len = Encode.length;
        String result = "";
        while (size > 0) {
            result = Encode[size % len] + result;
            size /= len;
        }

        return result;
    }
}
