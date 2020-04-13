package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String md = request.getParameter("md");
        Method declaringClass = null;
        try {
             declaringClass = this.getClass().getDeclaredMethod(md,request.getClass(),response.getClass());
            declaringClass.setAccessible(true);
            declaringClass.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
