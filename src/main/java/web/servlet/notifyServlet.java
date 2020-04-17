package web.servlet;

import constants.Gloable;
import service.OrderService;
import utils.AlipayUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/notify")
public class notifyServlet extends HttpServlet {
    private OrderService orderService = BeanFactory.newInstance(OrderService.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        System.out.println("notifyServlet.doGet");
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
