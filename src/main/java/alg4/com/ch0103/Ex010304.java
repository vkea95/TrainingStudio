package alg4.com.ch0103;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by JianZhang on 5/22/17.
 */
public class Ex010304 {

    public static void main(String[] args) {
//        read parentheses from in
        String parentheses = null;
        while (!StdIn.isEmpty()) {
            parentheses = StdIn.readString();
        }

        //1.左括号就入栈
//        2.右括号,
// 2.1 栈为空则出错
// 2.2 检查栈顶符号是否和当前括号匹配,
// 2.2.1 不匹配就出错
// 2.2.2 匹配就出栈
//        字符串读入完毕后,若栈为空,则OK,反之 则NG
    }
}
