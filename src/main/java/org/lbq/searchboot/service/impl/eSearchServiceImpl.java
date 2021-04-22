package org.lbq.searchboot.service.impl;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.lbq.searchboot.bean.Poem;
import org.lbq.searchboot.service.eSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class eSearchServiceImpl implements eSearchService {

    @Resource
    RestHighLevelClient highLevelClient;

    @Override
    public List<Poem> getSearchList(String content, String type, String author) throws IOException {

        //判断条件
        SearchRequest searchRequest = new SearchRequest("poems");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

       //指定查询条件与查询的字段
        if(StringUtils.isEmpty(content)){
            sourceBuilder.query(QueryBuilders.matchAllQuery());
        }else{
            sourceBuilder.query(QueryBuilders.multiMatchQuery(content,"name","content","author","authordes"));
        }

      //过滤条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(!StringUtils.isEmpty(type)){
            boolQueryBuilder.filter(QueryBuilders.termQuery("type",type));
        }
        if(!StringUtils.isEmpty(author)){
            boolQueryBuilder.filter(QueryBuilders.termQuery("author",author));
        }
        sourceBuilder.postFilter(boolQueryBuilder);

        //高亮显示
        sourceBuilder.highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red'>").postTags("</span>"));

        //返回的数据数量
        sourceBuilder.size(50);

        searchRequest.types("poem").source(sourceBuilder);
        SearchResponse search = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //封装结果
        ArrayList<Poem> poems = new ArrayList();
        SearchHit[] hits = search.getHits().getHits();
        if(hits.length>0){
            for (SearchHit hit : hits) {
                //获取原有的字段
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                //获取高亮字段
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();

                Poem poem = new Poem();

                //id
                poem.setId(hit.getId());
                //名称
                poem.setName((String) sourceAsMap.get("name"));
                if(highlightFields.containsKey("name")){
                    poem.setName(highlightFields.get("name").fragments()[0].toString());
                }
                //作者
                poem.setAuthor((String) sourceAsMap.get("author"));
                if(highlightFields.containsKey("author")){
                  poem.setAuthor(highlightFields.get("author").fragments()[0].toString());
                }
                //类型
                poem.setType((String) sourceAsMap.get("type"));
                if(highlightFields.containsKey("type")){
                    poem.setType(highlightFields.get("type").fragments()[0].toString());
                }
                //内容
                poem.setContent((String) sourceAsMap.get("content"));
                if(highlightFields.containsKey("content")){
                    poem.setContent(highlightFields.get("content").fragments()[0].toString());
                }
                //href
                poem.setHref((String) sourceAsMap.get("href"));

                //人物介绍
                poem.setAuthordes((String) sourceAsMap.get("authordes"));
                if(highlightFields.containsKey("authordes")){
                    poem.setAuthordes(highlightFields.get("authordes").fragments()[0].toString());
                }
                //来源
                poem.setOrigin((String) sourceAsMap.get("origin"));
                //分类ID
                poem.setCategoryid((String) sourceAsMap.get("categoryid"));

                poems.add(poem);
            }
        }
        return poems;
    }
}
