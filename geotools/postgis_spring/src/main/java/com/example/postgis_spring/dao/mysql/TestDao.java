package com.example.postgis_spring.dao.mysql;

import com.example.postgis_spring.entity.StyleDO;
import com.example.postgis_spring.module.Params;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName: StyleDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface TestDao {

    List<Map> getData(Params params);
}
