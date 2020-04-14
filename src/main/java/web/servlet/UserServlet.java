package web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import utils.RRHolder;
import web.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
         User user = new User();
         Map<String, String[]> parameterMap = request.getParameterMap();
         BeanUtils.populate(user,parameterMap);
        UserService userService = new UserService();
        User uu = userService.getUser(user);
         if(uu!=null){
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
        User user = new User();
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

    public void success(){
        success(null);
    }
    public void error(){
        error(null);
    }
    public void success(String value){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_SUCCESS);
        resultVo.setData(value);
        ajaxJson(resultVo);
    }
    public void ajaxJson(Object resultVo){
        String jsonStr = null;
        try {
            ObjectMapper mappper = new ObjectMapper();
            jsonStr = mappper.writeValueAsString(resultVo);
            HttpServletResponse response = RRHolder.getResponse();
            response.getWriter().print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void error(String value ){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_FAILED);
        resultVo.setData(value);
        ajaxJson(resultVo);
    }

}
