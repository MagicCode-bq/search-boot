package org.lbq.searchboot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.service.impl.PoemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/poem")
@RestController
public class PoemController {

   @Autowired
    PoemServiceImpl poemService;


    @GetMapping("/page")
    public String findPagePoem(  @RequestParam(name ="num" ,required=true,defaultValue = "1") Integer num,
                                 @RequestParam(name ="size" ,required = true,defaultValue = "10")  Integer size) throws JsonProcessingException {
      System.out.println(size+"-"+num);
        PageHelper.startPage(num,size);
        List<Poem> pagePoem = poemService.getPagePoem();
        PageInfo<Poem> poemPageInfo = new PageInfo<>(pagePoem);
        String s = new ObjectMapper().writeValueAsString(poemPageInfo);
         return  s;
    }

    @PostMapping("/save")
    public String SavePoem(@RequestBody Poem poem){
        int i = poemService.SavePoem(poem);
        return  i>0?"成功":"失败";
    }

    @GetMapping("/delete")
    public String delete(String id){
        int i = poemService.deletePoemId(id);
        return  i>0?"成功":"失败";
    }

}
