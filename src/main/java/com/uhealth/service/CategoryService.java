package main.java.com.uhealth.service;

import main.java.com.uhealth.dao.CategoryDao;
import main.java.com.uhealth.models.Category;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao;

    public CategoryService(){
        categoryDao = new CategoryDao();
    }

    public List<Category> getCategories(){
        return  categoryDao.get();
    }
}
