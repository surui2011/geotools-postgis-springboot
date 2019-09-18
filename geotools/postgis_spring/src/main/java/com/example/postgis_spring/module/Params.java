package com.example.postgis_spring.module;

import java.util.HashMap;

/**
 * @ClassName: Params
 * @Auther: SR
 * @Email: surui2011@163.com
 * <p>
 * 用于 sql 查询（相关 key 说明如下）
 * <p>
 * function：函数部分
 * table：   表名
 * fields：  要查的字段list（默认为*）
 * geom：    postgis中的geom
 */
public class Params extends HashMap {

    public Params put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Params() {
    }
}
