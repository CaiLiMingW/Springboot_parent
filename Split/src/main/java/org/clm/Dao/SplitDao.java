package org.clm.Dao;

import org.clm.Bean.Split;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Ccc
 * @date 2018/11/17 0017 下午 4:31
 */
public interface SplitDao extends MongoRepository<Split,String> {
}
