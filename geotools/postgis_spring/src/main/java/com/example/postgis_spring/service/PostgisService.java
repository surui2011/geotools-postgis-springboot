package com.example.postgis_spring.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.dao.postgresql.PostgisDao;
import com.example.postgis_spring.function.PostgisFunction;
import com.example.postgis_spring.module.*;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PostgisService
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Service
@Slf4j
public class PostgisService {
    @Resource
    private PostgisDao postgisDao;

    /**
     * @Description dao层的封装
     * @Author SR
     * @Param
     */
    // 获取表字段（去除geom）
    public List<String> findFieldsWithoutGeom(String table) {
        String sql = "select array_to_string(array_agg(s.column_name),',') as fields FROM information_schema.COLUMNS s WHERE s.table_name = '" +
                table + "' AND s.column_name <> 'geom'";
        List<Map> list = postgisDao.sqlRun(sql);
        Preconditions.checkArgument(list.size() > 0, "表不存在");
        return Arrays.asList(list.get(0).get("fields").toString().split(","));
    }

    /**
     * @Description 空间postgresql操作
     * @Author SR
     * @Param
     */
    // 查属性
    public List<Map> getFeatureInfo(String table, String filter) {
        List fields = findFieldsWithoutGeom(table);
        List<Map> list = getFeatureInfo(table, fields, filter);
        return list;
    }

    public List<Map> getFeatureInfo(String table, List fields, String filter) {
        Params params = new Params().put("table", table).put("filter", filter).put("fields", fields);
        List<Map> list = postgisDao.getFeatureInfo(params);
        return list;
    }

    /**
     * @Description 空间操作
     * @Author SR
     * @Param
     */
    // ewkt 字符串转 geom
    public Geom ST_GeomFromEWKT(Ewkt ewkt) {
        Function function = new PostgisFunction().ST_GeometryFromText(ewkt);
        List list = postgisDao.geometryOperator(function);
        Preconditions.checkArgument(list.size() == 1, "创建geom非唯一");
        String result = (String) list.get(0);
        return new Geom(result);
    }

    // 判断两个geom是否相交
    public boolean ifIntersect(Geom geom1, Geom geom2) {
        Function function = new PostgisFunction().ST_Intersects(geom1, geom2);
        List<String> list = postgisDao.geometryOperator(function);
        Preconditions.checkArgument(list.size() == 1, "结果不唯一");
        String result = list.get(0);
        return SqlConstant.TRUE.getValues().contains(result);
    }

    // 返回geom的GeoJson
    public JSONObject geom2Geojson(Geom geom) {
        String geoJson = postgisDao.getGeoJson(new Params().put("geom", geom));
        return JSON.parseObject(geoJson);
    }

    // 根据id查询返回GeoJson
    public JSONObject selectGeojsonById(int id, String table) {
        String geoJson = postgisDao.getGeoJson(new Params()
                .put("table", table)
                .put("filter", "gid=" + id));
        return JSON.parseObject(geoJson);
    }

    // 圈选（select）[返回GeoJson]
    public JSONObject selectByPolygonWithProperties(Geom polygon, List properties, String table) {
        Function ifIntersect = new PostgisFunction().ST_Intersects(polygon, new Geom(new Function("t.geom")));
        Params params = new Params().put("filter", ifIntersect).put("table", table).put("fields", properties);
        String geoJson = postgisDao.getGeoJson(params);
        return JSON.parseObject(geoJson);
    }

    // 圈选（clip）[返回GeoJson]
    public JSONObject clipByPolygonWithProperties(Geom polygon, List properties, String table) {
        Function ifIntersect = new PostgisFunction().ST_Intersects(polygon, new Geom(new Function("t.geom")));
        Function select = new PostgisFunction().ST_AsGeoJSON(new Geom(new PostgisFunction().ST_Intersection_SqlString("t.geom", polygon.toString())));
        Params params = new Params().put("geom", select).put("filter", ifIntersect).put("table", table).put("fields", properties);
        String geoJson = postgisDao.getGeoJson(params);
        return JSON.parseObject(geoJson);
    }

    // 点选[返回GeoJson]
    public JSONObject selectPolygonById(String id, String table) {
        String geoJson = postgisDao.getGeoJson(new Params().put("filter", "gid=" + id).put("table", table));
        return JSON.parseObject(geoJson);
    }
}
