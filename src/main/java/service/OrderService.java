package service;

import domain.Orders;

import java.util.HashMap;

public interface OrderService {
    Boolean createOrder(Orders order);

    Orders getOrderByOid(String orderid);

    Boolean saveOrderAddress(HashMap<String, String> orderAll);

    Boolean updateState(String oid, int orderStateYifukuan);
}
