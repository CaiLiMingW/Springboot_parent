package org.clm.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.clm.Bean.Article;
import org.clm.Dao.ArticleMapper;
import org.clm.Service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.clm.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import result.ServerResponse;

import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-17
 */
@Service

public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public ServerResponse selectKeyWithPage(Integer page, Integer size, Article article) {
        if (page!=null&&size!=null){
            PageHelper.startPage(page,size);
            List<Article> list = articleMapper.selectByKey(article);
            PageInfo pageInfo = new PageInfo(list);
            return ServerResponse.CreateBySuccessMessage(pageInfo);
        }
        return ServerResponse.CreateBySuccessMessage(articleMapper.selectByKey(article));
    }

    @Override
    public List<Article> selectTop() {
        List<Article> articles = articleMapper.selectTop();
        return articles;
    }

    @Override
    public void examineArticle(String id) {
        articleMapper.examineArticle(id);
    }
}
