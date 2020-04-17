package service;

import dao.CategoryDao;
import domain.CategoryBean;

import java.util.List;

public class Category {

    public List<CategoryBean> getAll() {
        List<CategoryBean> cateList = new CategoryDao().getAll();
        return cateList;
    }
}
