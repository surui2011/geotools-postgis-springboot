package com.example.postgis_spring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.dao.PostgisDao;
import com.example.postgis_spring.function.PostgisFunction;
import com.example.postgis_spring.module.*;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PostgisService
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Service
public class PostgisService {
    @Resource
    private PostgisDao postgisDao;

    /**
     * @Description 普通操作
     * @Author SR
     * @Param
     */
    public Geom findByid(int id) {
        MyList fields = new MyList().add("geom");
        List<Map> list = postgisDao.getData(new Params()
                .put("table", "qujie").put("fields", fields).put("filter", "gid=" + id));
        Preconditions.checkArgument(list.size() == 1, "结果不唯一");
        return new Geom(list.get(0).get("geom").toString());
    }

    /**
     * @Description 空间操作
     * @Author SR
     * @Param
     */
    // ewkt 字符串转 geom
    public Geom ST_GeomFromEWKT(Ewkt ewkt) {
        Function function = new PostgisFunction().ST_GeometryFromText(ewkt);
        Params params = new Params().put("function", function);
        List list = postgisDao.geometryOperator(params);
        Preconditions.checkArgument(list.size() == 1, "创建geom非唯一");
        String result = (String) list.get(0);
        return new Geom(result);
    }

    // 判断两个geom是否相交
    public boolean ifIntersect(Geom geom1, Geom geom2) {
        Function function = new PostgisFunction().ST_Intersects(geom1, geom2);
        List<String> list = postgisDao.geometryOperator(new Params().put("function", function));
        Preconditions.checkArgument(list.size() == 1, "结果不唯一");
        String result = list.get(0);
        return SqlConstant.TRUE.getValues().contains(result);
    }

    // 圈选（select）[返回GeoJson]
    public JSONObject selectByPolygonWithProperties(Geom polygon, List properties, String table) {
        Function ifIntersect = new PostgisFunction().ST_Intersects_SqlString(polygon.toString(), "t.geom");
        Params params = new Params().put("filter", ifIntersect).put("table", table).put("fields", properties);
        String geoJson = postgisDao.getGeoJson(params);
        return JSON.parseObject(geoJson);
    }

    // 圈选（clip）[返回GeoJson]
    public JSONObject clipByPolygonWithProperties(Geom polygon, List properties, String table) {
        Function ifIntersect = new PostgisFunction().ST_Intersects_SqlString(polygon.toString(), "t.geom");

        Function select = new PostgisFunction().ST_AsGeoJSON(new Geom(new PostgisFunction().ST_Intersection_SqlString("t.geom", polygon.toString())));
        Params params = new Params().put("select", select).put("filter", ifIntersect).put("table", table).put("fields", properties);
        String geoJson = postgisDao.getGeoJson(params);
        return JSON.parseObject(geoJson);
    }
}
