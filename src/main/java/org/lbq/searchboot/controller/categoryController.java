package org.lbq.searchboot.controller;

import org.lbq.searchboot.bean.Category;
import org.lbq.searchboot.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
public class categoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    //查询所有的分类
    @GetMapping("/all")
    public List<Category> findCategory(){
        List<Category> categoryAll = categoryService.findCategoryAll();
         return  categoryAll;
    }
}
