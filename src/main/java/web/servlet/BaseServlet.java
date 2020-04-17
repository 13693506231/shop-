package web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.RRHolder;
import web.vo.ResultVo;

import javax.servlet.ServletException;
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
             Class<? extends BaseServlet> aClass = this.getClass();
            Method method = aClass.getDeclaredMethod(md,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void success(){
        success(null);
    }
    public void error(){
        error(null);
    }
    public void success(Object value){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_SUCCESS);
        resultVo.setData(value);
        ajaxJson(resultVo);
        return;
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
    public void error(Object value ){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(ResultVo.CODE_FAILED);
        resultVo.setData(value);
        ajaxJson(resultVo);
        return;
    }


}
