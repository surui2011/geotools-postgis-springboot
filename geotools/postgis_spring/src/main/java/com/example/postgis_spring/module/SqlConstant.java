package com.example.postgis_spring.module;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: SqlConstant
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public enum SqlConstant {

    TRUE("1,t,true,TRUE");

    List<String> values;

    SqlConstant(String values) {
        this.values = Arrays.asList(values.split(","));
    }

    public List<String> getValues() {
        return this.values;
    }
}
