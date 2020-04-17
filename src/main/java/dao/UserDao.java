package dao;

import domain.Users;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.DataSourceUtil;

import java.sql.SQLException;

public class UserDao {

    public Boolean register(Users user) {
         QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
         String sql = "insert into user values(null,?,?,?,?,?,?)";
        try {
            int update = runner.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), user.getBirthday(), user.getGender());
            if(update == 1){
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataSourceUtil.close();
        return false;
    }

    public Users getUser(Users user) {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "select * from user where username=? and password=?";
        try {
            Users uu = runner.query(sql,new BeanHandler<Users>(Users.class),user.getUsername(), user.getPassword());
            if(uu!=null){
                return uu;
            }else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataSourceUtil.close();
        return null;
    }
}
