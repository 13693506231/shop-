package web.servlet;

import constants.Common;
import domain.Cart;
import domain.CartItem;
import domain.Goods;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends BaseServlet {
    protected void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println(cart);
        success(cart);
    }

    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        String goods_num = request.getParameter("goods_num");
        GoodsService goodsService = new GoodsService();
        Goods detail = goodsService.getDetail(pid);
        Common common = new Common();
        int gnum = common.mustInt(goods_num);
        CartItem cartItem = new CartItem(detail, gnum);
         HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        cart.addItem(cartItem);
        session.setAttribute("cart",cart);
        session.setMaxInactiveInterval(3600);
        success("加入购物车成功");
    }

     protected void delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         String pid = request.getParameter("pid");
         Cart cart = (Cart) session.getAttribute("cart");
         if(cart!=null){
             cart.delItem(pid);
         }
         success("删除成功");
    }

     protected void delAllCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          request.getSession().setAttribute("cart",null);
          success("清空成功");
     }
}
