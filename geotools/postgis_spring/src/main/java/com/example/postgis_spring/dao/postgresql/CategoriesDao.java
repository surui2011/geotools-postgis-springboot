package com.example.postgis_spring.dao.postgresql;

import com.example.postgis_spring.entity.Categories;
import com.example.postgis_spring.module.Params;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @InterfaceName: CategoriesDao
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Mapper
public interface CategoriesDao {

    List<Categories> getCategories(Params params);
}
