package org.clm.Service;

import org.clm.Bean.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import result.ServerResponse;

import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author Ccc
 * @since 2018-11-17
 */
public interface IArticleService extends IService<Article> {

    ServerResponse selectKeyWithPage(Integer page, Integer size, Article article);

    List<Article> selectTop();

    void examineArticle(String id);
}
