package com.example.postgis_spring.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @ClassName: LayerStyleVO
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Data
public class Categories {
    String id;
    String layer;
    String field;
    String value;
    String styleId;
    String comment;
    Date createTime;
    Date updateTime;
}
