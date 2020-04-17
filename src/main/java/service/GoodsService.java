package service;

import dao.GoodsDao;
import domain.Goods;

import java.util.List;

public class GoodsService {

    public List<Goods> getNew() {
        GoodsDao goodsDao = new GoodsDao();
        List<Goods> lgoods = goodsDao.getNew();
        return lgoods;
    }
    public List<Goods> getHot() {
        GoodsDao goodsDao = new GoodsDao();
        List<Goods> lgoods = goodsDao.getHot();
        return lgoods;
    }

    public Goods getDetail(String pid) {
        Goods goods = new GoodsDao().getDetail(pid);
        return goods;
    }
}
