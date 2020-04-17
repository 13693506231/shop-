package service.impl;

import dao.OrderDao;
import domain.Goods;
import domain.OrderItem;
import domain.Orders;
import org.apache.xpath.operations.Bool;
import service.OrderService;

import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl  implements OrderService {
    public Boolean createOrder(Orders orders) {
        List<OrderItem> orderItem = orders.getOrderItem();
        OrderDao orderDao = new OrderDao();
        Boolean orderres = orderDao.addOrder(orders);
        for (OrderItem item : orderItem) {
            Boolean orderIteRes = orderDao.addOrderItem(item);
            if(!orderIteRes){
                return false;
            }
        }
        if(orderres){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Orders getOrderByOid(String orderid) {
        OrderDao orderDao = new OrderDao();

        Orders  orders = orderDao.getOrders(orderid);
        List<OrderItem>  orderItem  = orderDao.getOrderItem(orderid);
        for (OrderItem item : orderItem) {
            Goods goods = orderDao.getGoodsById(item.getPid());
            item.setGoods(goods);
        }
        orders.setOrderItem(orderItem);
        return orders;
    }

    @Override
    public Boolean saveOrderAddress(HashMap<String, String> orderAll) {
        OrderDao orderDao = new OrderDao();
        Boolean res = orderDao.saveOrderAll(orderAll);
        return res;
    }

    @Override
    public Boolean updateState(String oid, int orderStateYifukuan) {
        OrderDao orderDao = new OrderDao();
        Boolean res = orderDao.upOrderState(oid,orderStateYifukuan);
        return res;
    }
}
