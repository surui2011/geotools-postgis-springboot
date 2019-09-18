package com.example.postgis_spring.module;

/**
 * @ClassName: Geom
 * @Auther: SR
 * @Email: surui2011@163.com
 *
 * T 允许为 String 或 Function
 * （推荐Function，可以是postgis函数也可以是"t.geom"等sql函数; String的使用场景日后改用geotools解决）
 */
public class Geom<T> {
    private T value;

    public String toString() {
        String result = "";
        if (this.value instanceof String) {
            result = "'" + this.value + "'";
        } else if (this.value instanceof Function) {
            result = this.value.toString();
        } else {
            result = "类型不支持";
        }
        return result;
    }

    public Geom(T value) {
        this.value = value;
    }
}
