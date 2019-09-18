package com.example.postgis_spring.dao.postgresql;

import com.example.postgis_spring.entity.LayerStyle;
import com.example.postgis_spring.module.Params;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: LayerStyleDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface LayerStyleDao {

    List<LayerStyle> getLayerStyle(Params params);

    long count(Params params);
}
