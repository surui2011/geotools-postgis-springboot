package com.example.postgis_spring.dao.postgresql;

import com.example.postgis_spring.module.Function;
import com.example.postgis_spring.module.Params;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: PostgisDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface PostgisDao {

    List<Map> getFeatureInfo(Params params);

    @Select("select ${function}")
    List<String> geometryOperator(Function function);

    String getGeoJson(Params params);

    @Select("${_parameter}")
    List<Map> sqlRun(String sql);
}
