package org.lbq.searchboot.controller.es;

import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.config.es.RepositoryDao;
import org.lbq.searchboot.mapper.PoemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/es")
@RestController
public class EsIndexController {

    @Resource
    RepositoryDao repositoryDao;

    @Resource
    PoemMapper poemMapper;


    @GetMapping("/build")
    @Transactional
    public ResponseEntity buildIndex(){
        //查询所有诗词信息
        List<Poem> poems = poemMapper.FindPoemList();
        //防止索引库没有创建
        repositoryDao.save(poems.get(3));
        //清空索引库
        repositoryDao.deleteAll();
        //添加索引库
        System.out.println(CollectionUtils.isEmpty(poems));
        if(!CollectionUtils.isEmpty(poems)){
            repositoryDao.saveAll(poems);
        }
        return  ResponseEntity.status(200).body("重构成功");
    }

    @GetMapping("/clear")
    @Transactional
    public ResponseEntity clearIndex(){
        //清空索引库
        repositoryDao.deleteAll();
        return  ResponseEntity.status(200).body("清除成功");
    }
}
