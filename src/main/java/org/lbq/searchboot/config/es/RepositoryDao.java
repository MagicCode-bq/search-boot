package org.lbq.searchboot.config.es;

import org.lbq.searchboot.bean.Poem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface RepositoryDao extends ElasticsearchRepository<Poem,String> {
}
