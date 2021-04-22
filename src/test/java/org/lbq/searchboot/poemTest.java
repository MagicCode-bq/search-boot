package org.lbq.searchboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lbq.searchboot.Vo.CountVo;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.config.es.RepositoryDao;
import org.lbq.searchboot.mapper.PoemMapper;
import org.lbq.searchboot.service.impl.PoemServiceImpl;
import org.lbq.searchboot.service.impl.dicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class poemTest {

  @Autowired
  PoemServiceImpl poemService;

  @Autowired
    RepositoryDao repositoryDao;

  @Autowired
    PoemMapper poemMapper;

  @Autowired
  dicServiceImpl dicService;


    @Test
    public void test01(){
        List<Poem> pagePoem = poemService.getPagePoem();
        System.out.println(pagePoem);

    }

     @Test
    public void test02(){
        List<Poem> poems = poemMapper.FindPoemList();
        Iterable<Poem> poems1 = repositoryDao.saveAll(poems);
    }

    @Test
    public void test03() {
        List<CountVo> authorSize = poemMapper.getAuthorSize();
        authorSize.forEach(System.out::println);
    }

    @Test
    public void test04() throws IOException {  //测试读取文件的内容
        ClassPathResource classPathResource = new ClassPathResource("static/init.dic");
        File file = classPathResource.getFile();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> words= new ArrayList();
        while (true){
            String s = bufferedReader.readLine();
            if(s==null) break;
           words.add(s);
        }
        words.forEach(System.out::println);
    }

    @Test
    public void test05() throws IOException { //测试添加
        String word="天使8";

        dicService.addDic(word);
    }


    @Test
    public void  test06 () throws IOException { //测试删除
        String  word= "天使3";
        dicService.deleteDic(word);
    }

}
