package com.example.postgis_spring.dao.postgresql;

import com.example.postgis_spring.module.Params;
import com.example.postgis_spring.entity.StyleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: StyleDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface StyleDao {

    List<StyleDO> getStyle(Params params);
}
