package com.example.postgis_spring.module;

/**
 * @ClassName: Geom
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class Geom {
    private String value;

    public String toString() {
        return this.value;
    }

    public Geom(String value) {
        this.value = "'" + value + "'";

    }

    public Geom(Function function) {
        this.value = function.toString();
    }
}
