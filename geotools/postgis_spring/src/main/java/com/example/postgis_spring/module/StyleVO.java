package com.example.postgis_spring.module;

import com.example.postgis_spring.entity.StyleDO;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName: StyleVO
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Data
public class StyleVO {
    String msg;

    @Builder
    @Data
    public static class PolygonStyle extends StyleVO {
        public String fillColor;
        public String outlineColor;
        public Float outlineWidth;
    }

    @Builder
    @Data
    public static class PointStyle extends StyleVO {
        public String fillColor;
        public Float pointSize;
        public String outlineColor;
        public Float outlineWidth;
    }

    @Builder
    @Data
    public static class LineStyle extends StyleVO {
        public String fillColor;
        public Float lineWidth;
    }

    public static StyleVO getStyle(StyleDO styleDO) {
        String type = styleDO.type;
        StyleVO styleVO = null;
        if ("polygon".equals(type)) {
            styleVO = PolygonStyle.builder().fillColor(styleDO.fillColor)
                    .outlineColor(styleDO.outlineColor).outlineWidth(styleDO.outlineWidth).build();
        } else if ("point".equals(type)) {
            styleVO = PointStyle.builder().fillColor(styleDO.fillColor)
                    .pointSize(styleDO.pointSize).outlineColor(styleDO.outlineColor).outlineWidth(styleDO.outlineWidth).build();
        } else if ("line".equals(type)) {
            styleVO = LineStyle.builder().fillColor(styleDO.fillColor).lineWidth(styleDO.lineWidth).build();
        } else {
            styleVO = new StyleVO();
            styleVO.setMsg("要素类型不支持: " + type);
        }
        return styleVO;
    }

    public static StyleVO createStyle(String type) {
        StyleVO styleVO = null;
        if ("polygon".equals(type)) {
            styleVO = PolygonStyle.builder().build();
        } else if ("point".equals(type)) {
            styleVO = PointStyle.builder().build();
        } else if ("line".equals(type)) {
            styleVO = LineStyle.builder().build();
        } else {
            styleVO = new StyleVO();
            styleVO.setMsg("要素类型不支持: " + type);
        }
        return styleVO;
    }
}
