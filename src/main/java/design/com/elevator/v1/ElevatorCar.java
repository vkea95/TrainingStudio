package design.com.elevator.v1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tclresearchamerica on 9/18/16.
 */
public class ElevatorCar {
    public String direction = "up";
    public Boolean idle = Boolean.TRUE;
    public int curFloor = 1;
    public String door = "closed";
    public List<Request> upQueue = new ArrayList<>();
    public List<Request> downQueue = new ArrayList<>();
    public List<Request> curQueue = new ArrayList<>();
    private Thread processingThread;


    //冒泡排序,楼层号小的往前挪
    private void addRequestToUpQueue(Request request) {
        upQueue.add(request);
        int n = upQueue.size();
        for (int i = 0; i < n; i++) {
            Request task1 = upQueue.get(i);
            Request task2 = upQueue.get(n - 1);
            if (task2.floor < task1.floor) {
                upQueue.set(i, task2);
                upQueue.set(n - 1, task1);
            }
        }
    }

    //冒泡排序,楼层号大的往前挪
    private void addRequestToDownQueue(Request request) {
        upQueue.add(request);
        int n = downQueue.size();
        for (int i = 0; i < n; i++) {
            Request task1 = downQueue.get(i);
            Request task2 = downQueue.get(n - 1);
            if (task2.floor > task1.floor) {
                downQueue.set(i, task2);
                downQueue.set(n - 1, task1);
            }
        }
    }

    //冒泡排序,楼层号大的往前挪
    private void addRequestToCurQueue(Request request) {
        upQueue.add(request);
        int n = curQueue.size();
        for (int i = 0; i < n; i++) {
            Request task1 = curQueue.get(i);
            Request task2 = curQueue.get(n - 1);
            if (task2.floor > task1.floor) {
                curQueue.set(i, task2);
                curQueue.set(n - 1, task1);
            }
        }
    }



}
