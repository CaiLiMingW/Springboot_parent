package org.clm.Dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.clm.Bean.Article;

import java.util.List;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author Ccc
 * @since 2018-11-17
 */
public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> selectByKey(Article article);

    @Update("update tb_article set thumbup = thumbup + 1 where id = #{id}")
    int thumbupArticle(@Param("id")String id);

    @Select("select * from tb_article where istop = '1'")
    List<Article> selectTop();

    @Update("update tb_article set state = 1 where id = #{id}")
    int examineArticle(@Param("id")String id);

}
