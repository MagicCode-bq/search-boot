package org.lbq.searchboot.service;

import java.io.IOException;
import java.util.List;

public interface DicService {


    //查询远程词典下所有内容
     List<String> getDic() throws IOException;

    //远程词典添加内容
     void addDic(String word) throws IOException;

    //远程词典删除内容
     void deleteDic(String word) throws IOException;
}
