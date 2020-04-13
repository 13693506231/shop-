package web.servlet;

import web.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class Servlet extends HttpServlet {
    protected void currentname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name!=null && name=="name"){
                String value = cookie.getValue();
                ResultVo resultVo = new ResultVo();
                response.getWriter().print(value);
            }
        }


    }

}
