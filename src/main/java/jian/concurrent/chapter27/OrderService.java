package jian.concurrent.chapter27;

import jian.concurrent.chapter19.Future;

public interface OrderService {
    Future<String> findOrderDetails(long orderId);

    void order(String account, long orderId);
}
