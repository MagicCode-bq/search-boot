package org.lbq.searchboot.service;


import org.apache.ibatis.annotations.Param;
import org.lbq.searchboot.Vo.CountVo;
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

    //返回作者的诗词数量在库中大于10的作者
    List<CountVo> queryAuthorPoemSize();

}
