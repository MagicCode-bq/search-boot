package org.lbq.searchboot.service.impl;


import org.lbq.searchboot.Vo.CountVo;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.mapper.PoemMapper;
import org.lbq.searchboot.service.PoemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
*
*/
@Service
public class PoemServiceImpl implements PoemService{

    @Resource
    PoemMapper poemMapper;


    @Override
    public List<Poem> getPagePoem() {
        List<Poem> poems = poemMapper.FindPoemList();
        return poems;
    }

    @Override
    public int SavePoem(Poem poem) {
        if(poem.getId()!=null){
            return poemMapper.updatePoem(poem);
        }else{
            String substring = UUID.randomUUID().toString().substring(0, 8);
            poem.setId(substring);
            return  poemMapper.insertPoem(poem);
         }
    }


    @Override
    public int deletePoemId(String id) {
        return poemMapper.deletePoemId(id);
    }


    @Override
    public List<CountVo> queryAuthorPoemSize() {
        List<CountVo> authorSize = poemMapper.getAuthorSize();
        return  authorSize;
    }

}
