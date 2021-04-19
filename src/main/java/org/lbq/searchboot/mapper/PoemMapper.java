package org.lbq.searchboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lbq.searchboot.bean.Poem;

import java.util.List;

/**
* @Entity org.lbq.searchboot.bean.Poem
*/
@Mapper
public interface PoemMapper {

    //查询所有的诗词信息
    List<Poem> FindPoemList();

    //添加一条poem信息
     int insertPoem(@Param("poem")Poem poem);

    //修改一条信息
     int updatePoem(@Param("poem")Poem poem);

    //删除一条信息
     int deletePoemId(String id);

}
