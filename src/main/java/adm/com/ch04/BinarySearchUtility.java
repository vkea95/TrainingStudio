package adm.com.ch04;

/**
 * Created by tclresearchamerica on 10/10/16.
 */
public class BinarySearchUtility {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 3, 3, 3, 3, 3, 6, 6, 6, 6, 6};
        BinarySearchUtility obj = new BinarySearchUtility();
        System.out.print(obj.binarySearch(input, 3, 0, 12));
        System.out.println("low boundary: "+obj.binarySearch(input, 3, 0, 12));
        System.out.print(obj.binarySearch(input, 3, 0, 12));
    }

    int binarySearch(int[] input, int key, int low, int hight) {
        if (low > hight) return (-1);
        int middle = (low + hight) / 2;
        if (input[middle] == key) return middle;
        if (input[middle] > key) {
            return binarySearch(input, key, low, middle - 1);
        } else {
            return binarySearch(input, key, middle + 1, hight);
        }
    }

    int binarySearchCountingOccurentc(int[] input, int key, int low, int hight) {
        if (low > hight) return (low);
        int middle = (low + hight) / 2;
//        if (input[middle] == key) return middle;
        if (input[middle] > key) {
            return binarySearch(input, key, low, middle - 1);
        } else {
            return binarySearch(input, key, middle + 1, hight);
        }
    }
}
