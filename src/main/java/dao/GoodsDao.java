package dao;

import constants.Gloable;
import domain.Goods;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtil;

import java.sql.SQLException;
import java.util.List;

public class GoodsDao {
    public List<Goods> getNew() {
        QueryRunner query = new QueryRunner();
        String sql = "select * from product where pflag = ? and is_hot = ? order by pdate limit ?";
        try {
            List<Goods> query1 = query.query(DataSourceUtil.getConn(),sql, new BeanListHandler<>(Goods.class), Gloable.GOODS_FLAG_SUCCESS,Gloable.GOODS_NOT_HOT, 9);
            return query1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Goods> getHot() {
        QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from product where pflag = ? and is_hot = ? order by pdate limit ?";
        try {
            List<Goods> query1 = query.query(sql, new BeanListHandler<>(Goods.class), Gloable.GOODS_FLAG_SUCCESS,Gloable.GOODS_HOT, 9);
            return query1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Goods getDetail(String pid) {
        QueryRunner query = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from product where id = ? ";
        try {
            Goods goods = query.query(sql, new BeanHandler<>(Goods.class), pid);
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
