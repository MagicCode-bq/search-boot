package org.lbq.searchboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lbq.searchboot.bean.Category;

import java.util.List;

/**
* @Entity org.lbq.searchboot.bean.Category
*/
@Mapper
public interface CategoryMapper {

    //查询所有的分类信息
    List<Category> findCategoryAll();

}
