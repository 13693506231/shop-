package web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
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
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name!=null && name.equals("name")){
                String value = cookie.getValue();
                success(value,response);
                return;
            }
        }
        error("未登录",response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
         User user = new User();
         Map<String, String[]> parameterMap = request.getParameterMap();
         BeanUtils.populate(user,parameterMap);
        UserService userService = new UserService();
        User uu = userService.getUser(user);
         if(uu!=null){
            Cookie cookie = new Cookie("name",uu.getName());
            response.addCookie(cookie);
            success("登录成功",response);
        }else{
            error("登录失败",response);
        }
        
    }

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        BeanUtils.populate(user,parameterMap);
        UserService userService = new UserService();
        Boolean register = userService.register(user);
        if(register){
            success("注册成功",response);
        }else{
            error("注册失败",response);
        }
    }

    public void success(HttpServletResponse response){
        success(null ,response);
    }
    public void error(HttpServletResponse response){
        error(null ,response);
    }
    public void success(String value  , HttpServletResponse response){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_SUCCESS);
        resultVo.setData(value);
        ObjectMapper mappper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mappper.writeValueAsString(resultVo);
            response.getWriter().print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void error(String value,HttpServletResponse response){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_FAILED);
        resultVo.setData(value);
        ObjectMapper mappper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mappper.writeValueAsString(resultVo);
            response.getWriter().print(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
