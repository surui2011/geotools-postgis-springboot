package com.example.postgis_spring.service;

import com.example.postgis_spring.dao.postgresql.CategoriesDao;
import com.example.postgis_spring.dao.postgresql.LayerStyleDao;
import com.example.postgis_spring.dao.postgresql.StyleDao;
import com.example.postgis_spring.entity.Categories;
import com.example.postgis_spring.entity.LayerStyle;
import com.example.postgis_spring.entity.StyleDO;
import com.example.postgis_spring.module.Params;
import com.example.postgis_spring.module.StyleVO;
import com.example.postgis_spring.module.Symbology;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: StyleService
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Service
@Slf4j
public class StyleService {
    @Resource
    private StyleDao styleDao;
    @Resource
    private LayerStyleDao layerStyleDao;
    @Resource
    private CategoriesDao categoriesDao;

    /**
     * @Description 获取图层符号化样式
     * @Author SR
     * @Param
     */
    public HashMap getLayerStyle(String layer) {
        HashMap symbology;
        List<LayerStyle> layerStyleList = layerStyleDao.getLayerStyle(new Params().put("filter", "layer='" + layer + "'"));
        Preconditions.checkArgument(layerStyleList.size() == 1, "图层样式查询结果不唯一");
        LayerStyle layerStyle = layerStyleList.get(0);
        String type = layerStyle.getType();
        if (layerStyle.getCategory()) {
            // categories symbology
            List<Categories> categoriesList = categoriesDao.getCategories(new Params().put("filter", "layer='" + layer + "'"));
            List<String> values = new ArrayList();
            List<StyleVO> styleList = new ArrayList<>();
            for (Categories categories : categoriesList) {
                values.add(categories.getValue());
                List<StyleDO> list = styleDao.getStyle(new Params().put("filter", "id='" + categories.getStyleId() + "'"));
                Preconditions.checkArgument(list.size() == 1, "查询结果不唯一");
                StyleVO styleVO = StyleVO.getStyle(list.get(0));
                styleList.add(styleVO);
            }
            symbology = Symbology.getCategoriesSymbology(styleList);
            Preconditions.checkArgument(symbology != null, "getCategoriesSymbology初始化失败");
            symbology.put("values", values);
        } else {
            // single symbology
            List<StyleDO> styleDOList = styleDao.getStyle(new Params().put("filter", "id='" + layerStyle.getStyleId() + "'"));
            Preconditions.checkArgument(styleDOList.size() == 1, "样式查询不唯一");
            StyleVO styleVO = StyleVO.getStyle(styleDOList.get(0));
            symbology = Symbology.getSingleSymbology(styleVO);
            Preconditions.checkArgument(symbology != null, "getSingleSymbology初始化失败");
        }
        return symbology;
    }
}
