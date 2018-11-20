package org.clm.Bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author Ccc
 * @date 2018/11/19 0019 下午 1:10
 */
@Data
@Document(indexName = "clm",type = "article")
public class Atricle implements Serializable {
    @Id
    private String id;

    @Field(analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    private String title;
    /**索引默认为true
     * 是否索引：该域是否被搜索
     * 是否分词: 表示搜索是整体匹配还是单词匹配
     * 是否存储: 是否在搜索页面上显示
     *
     * analyzer：分词
     * searchAnalyzer:搜索
     * */
    @Field(index= true,analyzer="ik_max_word",searchAnalyzer="ik_max_word")
    /**文章正文*/
    private String content;

    /**审核状态*/
    private String state;

}
