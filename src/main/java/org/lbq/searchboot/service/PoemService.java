package org.lbq.searchboot.service;


import org.apache.ibatis.annotations.Param;
import org.lbq.searchboot.bean.Poem;

import java.util.List;

/**
*
*/
public interface PoemService {

    //查询所有诗词信息
    public List<Poem> getPagePoem();

    //添加一条poem信息
    int SavePoem(Poem poem);

    //删除一条信息
    int deletePoemId(String id);

}
