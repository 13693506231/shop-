package web.servlet;

import domain.Users;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    public void currentname(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Cookie[] cookies = request.getCookies();
        Object name1 = request.getSession().getAttribute("name");
        if(name1!=null  ){
            success(name1.toString());
            return;
        }
         error("未登录");
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
         Users user = new Users();
         Map<String, String[]> parameterMap = request.getParameterMap();
         BeanUtils.populate(user,parameterMap);
        UserService userService = new UserService();
        Users uu = userService.getUser(user);
         if(uu!=null){
             request.getSession().setAttribute("user",uu);
             request.getSession().setAttribute("name",uu.getName());
             success("登录成功");
        }else{
            error("登录失败");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        request.getSession().invalidate();
        success("退出成功");
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        Users user = new Users();
        Map<String, String[]> parameterMap = request.getParameterMap();
        BeanUtils.populate(user,parameterMap);
        UserService userService = new UserService();
        Boolean register = userService.register(user);
        if(register){
            success("注册成功");
        }else{
            error("注册失败");
        }
    }



}
