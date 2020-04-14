package utils;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import domain.user_login;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DataSourceUtil {
    private static DataSource ds = new ComboPooledDataSource();
    private  static Connection conn = null;
     public static DataSource getDataSource(){
        return ds;
    }
    public static Connection getConn()   {
        try {
            conn = ds.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        QueryRunner queryRunner = new QueryRunner(getDataSource());
        String sql = "select * from user_login";
        List<user_login> query = null;
        try {
            query = queryRunner.query(sql, new BeanListHandler<user_login>(user_login.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (user_login user_login : query) {
            System.out.println(user_login);
        }
        System.out.println(1111);

    }
}
