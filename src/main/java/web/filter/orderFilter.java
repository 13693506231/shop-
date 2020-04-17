package web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Users;
import utils.RRHolder;
import web.vo.ResultVo;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/order")
public class orderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        String user = (String) request.getSession().getAttribute("name");
        if(user == null){
            error("请登录");
            return;
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

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
        resultVo.setCode(ResultVo.CODE_NOLOGIN);
        resultVo.setData(value);
        ajaxJson(resultVo);
     }

}
