package ctc.com.viii.ch13.p05;

import java.util.*;

public class Question {
    public static void insertAndPrint(AbstractMap<Integer, String> map) {
        int[] array = {1, -1, 0};
        for (int x : array) {
            map.put(x, Integer.toString(x));
        }

        for (int k : map.keySet()) {
            System.out.print(k + ", ");
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyDaemon();
        thread.start();

        TreeMap<Integer, String> treeMap = new TreeMap();
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();

        System.out.println("\nHashMap - Arbitrary Order:");
        insertAndPrint(hashMap);

        System.out.println("\nLinkedHashMap - Insertion Order:");
        insertAndPrint(linkedHashMap);

        System.out.println("\nTreeMap - Natural Order:");
        insertAndPrint(treeMap);
    }

    public static class MyDaemon extends Thread {
        public MyDaemon(){
            setDaemon(true);
        }

        @Override
        public void run(){
            while (true) {
                try {
                    Thread.sleep(1);
                    System.out.println("111");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }

}
