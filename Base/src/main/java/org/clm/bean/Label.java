package org.clm.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Label implements Serializable {


    private String id;
    //标签名字
    private String labelname;
    //状态
    private String state;
    //使用数量
    private Long count;
    //是否推荐
    private String recommend;

    private Long fans;

}