package org.clm.Dao;

import org.clm.Bean.Atricle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ccc
 * @date 2018/11/19 0019 下午 1:16
 */
public interface AtricleSearchDao extends ElasticsearchRepository<Atricle,String> {
    /**
     *
     * @return
     */
}
