package org.clm.Service.impl;

import org.clm.Bean.Atricle;
import org.clm.Dao.AtricleSearchDao;
import org.clm.Service.IArticleSearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utils.IdWorker;

/**
 * @author Ccc
 * @date 2018/11/19 0019 下午 1:19
 */
@Service
public class IArticleSearchServiceImpl implements IArticleSearchService {
    @Autowired
    private AtricleSearchDao atricleSearchDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    public void addArticleSeatch(Atricle atricle){
        atricle.setId(String.valueOf(idWorker.nextId()));
        atricleSearchDao.save(atricle);
    }

    @Override
    public void deleteArticle(String id) {
        atricleSearchDao.deleteById(id);
    }

    @Override
    public Atricle findById(String id) {
        return atricleSearchDao.findById(id).get();
    }

    @Override
    public Iterable<Atricle> fingAll() {
        return atricleSearchDao.findAll();
    }

    @Override
    public void updateArticle(Atricle atricle) {
        atricleSearchDao.save(atricle);
    }

    @Override
    public Page<Atricle> findByTitleOrContent(String keywords, int page, int size) {
        //todo 关键词查询修改
        QueryBuilder queryBuilder = null;
        PageRequest pageRequest = PageRequest.of(page,size);
        return  atricleSearchDao.search(queryBuilder.queryName(keywords),pageRequest);
    }

}
