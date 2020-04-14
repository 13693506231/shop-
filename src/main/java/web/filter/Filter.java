package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);//动态支持跨域
        response.setHeader("Access-Control-Allow-Credentials","true");
        chain.doFilter(req, resp);
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
