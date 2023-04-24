package main.java.com.uhealth.controllers;

import main.java.com.uhealth.models.Category;
import main.java.com.uhealth.service.CategoryService;

import java.util.List;

public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(){
        categoryService =  new CategoryService();
    }


    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

}
