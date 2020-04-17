package service;

import dao.UserDao;
import domain.Users;

public class UserService {
    public Boolean register(Users user) {
        UserDao userDao = new UserDao();
        Boolean res = userDao.register(user);
        return res;
    }
    public Users getUser(Users user) {
        UserDao userDao = new UserDao();
        Users u = userDao.getUser(user);
        return u;
    }
}
