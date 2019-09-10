package com.example.postgis_spring.module;

import java.util.ArrayList;

/**
 * @ClassName: Fields
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class MyList extends ArrayList {
    public MyList add(String field) {
        super.add(field);
        return this;
    }

    public MyList() {
    }
}
