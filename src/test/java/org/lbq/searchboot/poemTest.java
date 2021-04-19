package org.lbq.searchboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.service.impl.PoemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class poemTest {

  @Autowired
  PoemServiceImpl poemService;


    @Test
    public void test01(){
        List<Poem> pagePoem = poemService.getPagePoem();
        System.out.println(pagePoem);

    }
}
