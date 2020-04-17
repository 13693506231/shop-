package dao;

import domain.Goods;
import domain.OrderItem;
import domain.Orders;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtil;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderDao {
    public Boolean addOrder(Orders orders) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "insert into orders values(null,?,?,?,null,null,null,null,?)";
        try {
            int update = queryRunner.update(DataSourceUtil.getConn(), sql, orders.getOid(), new Date(), orders.getTotal(), orders.getUid());
            if(update == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Boolean addOrderItem(OrderItem item) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "insert into orderitem values(null,?,?,?,?)";
        try {
            int update = queryRunner.update(DataSourceUtil.getConn(), sql,item.getCount(),item.getSubtotal(),item.getPid(),item.getOid());
            if(update == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
     }

    public List<OrderItem> getOrderItem(String orderid) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "select * from orderitem where oid = ?";
        try {
            List<OrderItem> items = queryRunner.query(DataSourceUtil.getConn(), sql,new BeanListHandler<>(OrderItem.class),orderid);

             return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Orders getOrders (String orderid) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "select * from orders where oid = ?";
        try {
            Orders items = queryRunner.query(DataSourceUtil.getConn(), sql,new BeanHandler<>(Orders.class),orderid);
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Goods getGoodsById(int pid) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "select * from product where id = ?";
        try {
            Goods goods = queryRunner.query(DataSourceUtil.getConn(), sql,new BeanHandler<>(Goods.class),pid);
            return goods;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean saveOrderAll(HashMap<String, String> orderAll) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "update  orders set name=?,address=?,telephone=? where oid = ?";
        try {
            int res = queryRunner.update(DataSourceUtil.getConn(), sql,orderAll.get("name"),orderAll.get("address"),orderAll.get("telephone"),orderAll.get("orderid"));
            if(res == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean upOrderState(String oid, int orderStateYifukuan) {
        QueryRunner queryRunner = new QueryRunner();
        String  sql= "update  orders set state = ? where oid = ?";
        try {
            int res = queryRunner.update(DataSourceUtil.getConn(), sql,orderStateYifukuan,oid);
            System.out.println(res);
            System.out.println(sql);
            System.out.println(orderStateYifukuan);
            System.out.println(oid);
            if(res == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
