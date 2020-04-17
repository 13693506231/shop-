package web.servlet;

import com.alipay.api.AlipayApiException;
import constants.Gloable;
import dao.OrderDao;
import domain.*;
import org.apache.xpath.operations.Bool;
import service.OrderService;
import utils.AlipayUtil;
import utils.UUIDUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = BeanFactory.newInstance(OrderService.class);
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            error();
        }
        Users user = (Users)session.getAttribute("user");
        System.out.println(cart);
        String oid = UUIDUtil.getId();
        List<CartItem> items = cart.getItem();
        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setOrdertime(new Date());
        orders.setTotal(cart.getItem_total());
        orders.setUid(user.getId());
        List<OrderItem> orderitems = new ArrayList<>();
        for (CartItem cartItem : items) {
//            int count, double subtotal, int pid, int oid
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(cartItem.getGoods_num());
            orderItem.setOid(oid);
            orderItem.setPid(cartItem.getGoods().getId());
            orderItem.setSubtotal(cartItem.getItem_total());
            orderitems.add(orderItem);
         }
        orders.setOrderItem(orderitems);
        Boolean insertRes = orderService.createOrder(orders);
        if(insertRes){
            success(oid);
        }else{
            error("保存订单失败");
        }


    }

    protected void getOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderid = request.getParameter("orderid");
        Orders orders = orderService.getOrderByOid(orderid);

        success(orders);

    }

     protected void topay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HashMap<String,String> orderAll = new HashMap<>();
         String orderid = request.getParameter("orderid");
         orderAll.put("name",request.getParameter("name"));
         orderAll.put("address",request.getParameter("address"));
         orderAll.put("telephone",request.getParameter("telephone"));
         orderAll.put("orderid",orderid);
         Boolean res = orderService.saveOrderAddress(orderAll);
         if(!res){
             error();
             return;
         }
         System.out.println("OrderServlet.topay");
         try {
//             String  orderid2 = UUIDUtil.getId();
             String s = AlipayUtil.generateAlipayTradePagePayRequestForm(orderid, "这是我的订单", 0.01);
//             System.out.println(s);
             response.getWriter().print(s);

         } catch (AlipayApiException e) {
             e.printStackTrace();
         }
     }
/**
 * Description :     同步支付返回
 * @param：
 * @return String：
 * @Author: ghc
 * @Create Date: 2008-07-02
 */
     protected void callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Map<String,String[]> parameterMap = request.getParameterMap();
         boolean check = AlipayUtil.check(parameterMap);
         if(check){
             String oid = request.getParameter("out_trade_no");
             Boolean bol = orderService.updateState(oid, Gloable.ORDER_STATE_YIFUKUAN);
             response.sendRedirect("http://web.shop.com:8020/webcode/view/order/info.html?orderid="+oid);
         }else{
             response.getWriter().print("error log ");
         }
    }
/**
 * Description :    异步接口返回
 * @param：
 * @return String：
 * @Author: ghc
 * @Create Date: 2008-07-02
 */
     protected void notify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         charset    :     utf-8
//         out_trade_no    :     E3EEF311D28B4ADB9514F926DF986569
//         method    :     alipay.trade.page.pay.return
//                 total_amount    :     0.01
//         sign:         j7vLhRnrmXfEoIQwHonQjw38LIPBauesMb98PaGGdi8vshAsrBcCkGQIYCuDWCVrZ6Xn8JvxSCkWt3pL1F3OXiMJe0kzAKtl3zW9USStvDlMrmiGYMiLFOjPgr8TSn5msun2UAnR3w9wEfe6IRr0PEnQZu61KuI6+8ii2N1eQcaH4wlOJi42/1Eregt89DCj5s2yAdwlJ8rVhjbgWILlQhmg37+PVeMTR1Ms+iIeXYI8ikVIo0Rg0kBiFjibh62GmPytEIFwqSvRbfisX75eW7f2MAFr+yu4Ka1q3KRqRjJ4Z8i8FcHbNqHdemklSxRz8sbsX48xpcvwaLNIPXk92Q==
//                 trade_no    :     2020041722001489840501189473
//         auth_app_id    :     2016102400750128
//         version    :     1.0
//         app_id    :     2016102400750128
//         sign_type    :     RSA2
//         seller_id    :     2088102180864471
//         timestamp    :     2020-04-17 16:16:43
         Map<String,String[]> parameterMap = request.getParameterMap();
         boolean check = AlipayUtil.check(parameterMap);
         if(check){
//         if(1 == 1){
             String oid = request.getParameter("out_trade_no");
              Boolean bol = orderService.updateState(oid, Gloable.ORDER_STATE_YIFUKUAN);
             System.out.println(bol);
//             response.sendRedirect("http://web.shop.com:8020/webcode/view/order/info.html?oid="+oid);
        }else{
//             response.getWriter().print("error log ");
         }

     }
}
