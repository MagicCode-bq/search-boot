package org.lbq.searchboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lbq.searchboot.Vo.CountVo;
import org.lbq.searchboot.bean.Poem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

     //查询作者诗词数大于10篇位作者
    public List<CountVo> getAuthorSize();

}
