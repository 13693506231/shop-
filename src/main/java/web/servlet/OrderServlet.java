package web.servlet;

import domain.*;
import service.OrderService;
import utils.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = BeanFactory.newInstance(OrderService.class);
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Users user = (Users)session.getAttribute("user");
        System.out.println(cart);
        String oid = UUIDUtil.getId();
        List<CartItem> item = cart.getItem();
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setOrdertime(new Date());
        orders.setTotal(cart.getItem_total());
        orders.setUid(user.getId());
        List<OrderItem> orderhash = new ArrayList<>();

        for (CartItem cartItem : item) {
//             orderhash.add(cartItem);

        }

        String orderid = orderService.createOrder();
        if(orderid != null){
            success(orderid);
        }else{
            error("保存订单失败");
        }


    }

    protected void xxxx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
