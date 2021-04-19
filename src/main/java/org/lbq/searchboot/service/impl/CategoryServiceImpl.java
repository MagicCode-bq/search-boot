package org.lbq.searchboot.service.impl;


import org.lbq.searchboot.bean.Category;
import org.lbq.searchboot.mapper.CategoryMapper;
import org.lbq.searchboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
*/
@Service
public class CategoryServiceImpl implements CategoryService{


    @Resource
    CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryAll() {
        return categoryMapper.findCategoryAll();
    }
}
