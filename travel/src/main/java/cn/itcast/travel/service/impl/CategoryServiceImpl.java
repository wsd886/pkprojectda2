package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;

import java.util.ArrayList;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao cateGory=new CategoryDaoImpl();

    @Override
    public ArrayList<Category> findAll() {
        return (ArrayList<Category>) cateGory.findAll();
    }
}
