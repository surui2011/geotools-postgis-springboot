package com.example.postgis_spring.module;

import com.example.postgis_spring.utils.Tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Symbology
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class Symbology extends HashMap {

    public static HashMap getSingleSymbology(StyleVO styleVO) {
        return Tools.object2Map(styleVO);
    }

    public static HashMap getCategoriesSymbology(List<StyleVO> styleVOList) {
        return Tools.objectList2MapList(styleVOList);
    }
}
