package org.lbq.searchboot.controller.es;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lbq.searchboot.Vo.CountVo;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.service.impl.PoemServiceImpl;
import org.lbq.searchboot.service.impl.dicServiceImpl;
import org.lbq.searchboot.service.impl.eSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RequestMapping("/search")
@RestController
public class SearchController {

    @Autowired
    PoemServiceImpl poemService;

    @Autowired
    eSearchServiceImpl eSearchService;

    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    dicServiceImpl dicService;

   //默认搜索全部,并分页
    @GetMapping("/index")
    public String searchIndex(String context,String type,String author ) throws IOException {
        System.out.println(context+"_"+type+"_"+author);

      //Redis 存储热词
        if(!StringUtils.isEmpty(context.trim())){ //没有则添加,有则加分
          redisTemplate.opsForZSet().incrementScore("KeyWord",context,0.5);
        }else{
             context=null;
        }

        if("所有".equals(type)) type=null;
        if("所有".equals(author)) author=null;
        //ES查询
        List<Poem> searchList = eSearchService.getSearchList(context, type, author);

        String s = new ObjectMapper().writeValueAsString(searchList);
        return s;
    };



    //返回热门的作者
    @GetMapping("/author")
    public ResponseEntity getAuthorSize() throws JsonProcessingException {
        List<CountVo> countVos = poemService.queryAuthorPoemSize();
        String s = new ObjectMapper().writeValueAsString(countVos);
        return  ResponseEntity.ok(s);
    };

    //返回所有的热词
    @GetMapping("/hotWord")
    public String getHotWord() throws JsonProcessingException {
        Set keyWord = redisTemplate.opsForZSet().rangeByScore("KeyWord", 3, Integer.MAX_VALUE);
        return  new ObjectMapper().writeValueAsString(keyWord);
    }

    //返回所有词典的内容
    @GetMapping("/dic")
    public String  getDicAll() throws IOException {
        List<String> dic = dicService.getDic();
        String s = new ObjectMapper().writeValueAsString(dic);
         return  s;
    }

    //添加的词典
    @PostMapping("/dic/add")
    public ResponseEntity insertDic(String word) throws IOException {
         dicService.addDic(word);
        return  ResponseEntity.ok("添加成功");
    }

    //删除词典的内容
    @GetMapping("/dic/delete")
    public ResponseEntity deleteDic(String word) throws IOException {
        dicService.deleteDic(word);
        return  ResponseEntity.ok("删除成功");
    }

}
