package core.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataSet implements Iterable<String> {


    private String myData[] = {"1", "2", "3", "4"};

    public static void main(String[] args) {
        List<Integer> rst = new ArrayList<>();
        rst.sort((a, b) -> a - b);
        DataSet dataSet = new DataSet();
//        dataSet.f(test);
        Iterator<String> iterator = dataSet.iterator();
        while (iterator.hasNext()) {
            String nextValue = iterator.next();
            System.out.println("The next value with Iterator is: " + nextValue);
        }

        for (String nextValue : dataSet) {
            System.out.println("The next value with the for Loop is: " + nextValue);
        }
    }

    private void f(int[] a) {
        a = new int[]{12, 3};
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    private class DataSetIterator implements Iterator {
        private int position = 0;

        public boolean hasNext() {
            if (position < myData.length)
                return true;
            else
                return false;
        }

        public String next() {
            if (this.hasNext())
                return myData[position++];
            else
                return null;
        }

        @Override
        public void remove() {

        }
    }
}