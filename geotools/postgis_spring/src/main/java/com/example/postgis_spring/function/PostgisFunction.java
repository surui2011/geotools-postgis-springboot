package com.example.postgis_spring.function;

import com.example.postgis_spring.module.Ewkt;
import com.example.postgis_spring.module.Function;
import com.example.postgis_spring.module.Geom;

/**
 * @ClassName: Function
 * @Auther: SR
 * @Email: surui2011@163.com
 * postgis中的空间函数
 */
public class PostgisFunction {

    /**
     * @Description 8.4 Geometry Constructors
     * @Author SR
     * @Param
     */
    public Function ST_GeometryFromText(Ewkt ewkt) {
        return new Function("ST_GeometryFromText('" + ewkt.getEwkt() + "')");
    }

    /**
     * @Description 8.7Geometry Outputs
     * @Author SR
     * @Param
     */
    public Function ST_AsGeoJSON(Geom geom) {
        String function = "ST_AsGeoJSON(" + geom + ") :: json as \"geometry\"";
        return new Function(function);
    }

    /**
     * @Description 8.9 Spatial Relationships and Measurements
     * @Author SR
     * @Param
     */
    public Function ST_Intersects(Geom geom1, Geom geom2) {
        return new Function("ST_Intersects('" + geom1 + "','" + geom2 + "')");
    }

    public Function ST_Intersects_SqlString(String geomSqlString1, String geomSqlString2) {
        return new Function("ST_Intersects(" + geomSqlString1 + "," + geomSqlString2 + ")");
    }

    /**
     * @Description 8.11 Geometry Processing
     * @Author SR
     * @Param
     */
    public Function ST_Intersection(Geom geom1, Geom geom2) {
        return new Function("ST_Intersection('" + geom1 + "','" + geom2 + "')");
    }

    public Function ST_Intersection_SqlString(String geomSqlString1, String geomSqlString2) {
        return new Function("ST_Intersection(" + geomSqlString1 + "," + geomSqlString2 + ")");
    }

}
