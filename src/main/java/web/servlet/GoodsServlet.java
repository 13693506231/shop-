package web.servlet;

import domain.Goods;
import service.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = "/goodsServlet")
public class GoodsServlet extends BaseServlet {
    protected void getNewAndHotList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GoodsService goodsService = new GoodsService();
        List<Goods> gnew = goodsService.getNew();
        List<Goods> ghot = goodsService.getHot();
        HashMap<String, List<Goods>> hashMap = new HashMap<>();
        hashMap.put("new",gnew);
        hashMap.put("hot",ghot);
        if(hashMap!=null){
            success(hashMap);
        }else{
            error();
        }

    }

    protected void getDetail(HttpServletRequest request, HttpServletResponse response){
        String pid = request.getParameter("pid");
        GoodsService goodsService = new GoodsService();
        Goods goods = goodsService.getDetail(pid);
        success(goods);
    }
}
