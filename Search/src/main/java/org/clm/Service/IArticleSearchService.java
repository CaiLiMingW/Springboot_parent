package org.clm.Service;

import org.clm.Bean.Atricle;
import org.springframework.data.domain.Page;

/**
 * @author Ccc
 * @date 2018/11/19 0019 下午 1:18
 */
public interface IArticleSearchService {
    void addArticleSeatch(Atricle atricle);

    void deleteArticle(String ID);

    Atricle findById(String id);

    Iterable<Atricle> fingAll();

    void updateArticle(Atricle atricle);

    Page<Atricle> findByTitleOrContent(String keywords, int page ,int size);
}
