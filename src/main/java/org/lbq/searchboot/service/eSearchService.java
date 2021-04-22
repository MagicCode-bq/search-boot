package org.lbq.searchboot.service;

import org.lbq.searchboot.bean.Poem;

import java.io.IOException;
import java.util.List;

public interface eSearchService {

    //搜索查询
    public List<Poem>  getSearchList(String content,String type,String author) throws IOException;

}
