package com.example.postgis_spring.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.module.Params;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: PostgisDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface PostgisDao {

    List<Map> getData(Params params);

    List<String> geometryOperator(Params params);

    String getGeoJson(Params params);
}
