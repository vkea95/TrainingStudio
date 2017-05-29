package alg4.com.ch0103;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Stack;

/**
 * Created by JianZhang on 5/22/17.
 */
public class Ex010305 {

    public static void main(String[] args) {
        Stack<Integer> stack  = new Stack<Integer>();
        int N=50;
        while (N>0){
            stack.push(N%2);
            N/=2;
        }
        for (int d:stack) StdOut.print(d);
//        应该是打印50的2进制表示110010
//        是否可以理解为不断地整除,N/2+N/4+N/8+N/16....
        StdOut.println();
    }
}
