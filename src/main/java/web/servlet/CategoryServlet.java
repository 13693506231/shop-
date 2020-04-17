package web.servlet;

import domain.CategoryBean;
import service.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    protected void getCateGory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = new Category();
        List<CategoryBean> cateList = category.getAll();
        success(cateList);

    }

    protected void xxxx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
