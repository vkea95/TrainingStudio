package jian.concurrent.chapter27;

import edu.princeton.cs.algs4.In;
import jian.concurrent.chapter19.Future;
import jian.concurrent.chapter19.FutureService;

import java.util.concurrent.TimeUnit;

public class OrderServiceImpl implements OrderService {
    @Override
    public Future<String> findOrderDetails(long orderId) {
        return FutureService.<Long, String>newService().submit(input -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("process the orderID ->" + orderId);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return "The order Details Information";
        }, orderId, null);
    }

    @Override
    public void order(String account, long orderId) {
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("process the order for account " + account + ", orderId " + orderId);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
