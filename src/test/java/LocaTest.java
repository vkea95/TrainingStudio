public class LocaTest {
    public static void main(String[] args) throws Exception {
//        主动引用
//        System.out.println(A.width);
//        new A();
//        Class.forName("A");

//        被动引用
//        System.out.println(A.MAX);
//        A[] as = new A[10];
        System.out.println(A_Child.width);
    }
}

class A_Father {
    static {
        System.out.println("A_Father static block");
    }
}

class A extends A_Father {
    static int width = 100;
    public static final int MAX = 100;// 常量

    static {
        System.out.println("A static block");
    }

    public A() {
        System.out.println("creating object of A");
    }

}

class A_Child extends A {

    static {
        System.out.println("A_Child static block");
    }
}
