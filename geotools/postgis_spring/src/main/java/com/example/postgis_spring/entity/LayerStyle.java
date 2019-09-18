package com.example.postgis_spring.entity;

import com.example.postgis_spring.module.StyleVO;
import lombok.Data;

import java.util.Date;


/**
 * @ClassName: LayerStyle
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Data
public class LayerStyle {
    String id;
    String layer;
    String transparent;
    Boolean category;
    String comment;
    StyleVO style;
    Date createTime;
    Date updateTime;
    String styleId;
    String type;
}
