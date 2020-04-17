package dao;

import domain.CategoryBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.DataSourceUtil;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    public List<CategoryBean> getAll() {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from category";
        try {
            List<CategoryBean> query = runner.query(sql, new BeanListHandler<>(CategoryBean.class));
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
