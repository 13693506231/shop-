package service;

import dao.UserDao;
import domain.User;

public class UserService {
    public Boolean register(User user) {
        UserDao userDao = new UserDao();
        Boolean res = userDao.register(user);
        return res;
    }
    public User getUser(User user) {
        UserDao userDao = new UserDao();
        User u = userDao.getUser(user);
        return u;
    }
}
