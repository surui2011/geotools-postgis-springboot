package com.example.postgis_spring.entity;

import lombok.Data;

import java.sql.Date;

/**
 * @ClassName: Style
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Data
public class StyleDO {
    public Integer id;
    public Integer transparent;
    public String fillColor;
    public String outlineColor;
    public Float outlineWidth;
    public Float pointSize;
    public Float lineWidth;
    public String type;
    public Date createTime;
    public Date updateTime;
    public String comment;
}
