package com.example.postgis_spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.module.Ewkt;
import com.example.postgis_spring.module.MyList;
import com.example.postgis_spring.service.PostgisService;
import com.google.common.base.Preconditions;
import com.qupdi.sso.client.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PostgisController
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@RestController
@RequestMapping("/postgis")
public class PostgisController {
    @Resource
    private PostgisService postgisService;

    // 点查
    @GetMapping("/getFeatureInfo")
    public Response getFeatureInfo(int id, String layer) {
        List list = postgisService.getFeatureInfo(layer, "gid=" + id);
        Preconditions.checkArgument(list.size() == 1, "id查询结果不唯一");
        return Response.builder().data(list.get(0)).build();
    }

    @GetMapping("/getGeomById")
    public Response getGeomById(String id, String layer) {
        JSONObject geojson = postgisService.selectPolygonById(id, layer);
        return Response.builder().data(geojson).build();

    }

    // 圈选（select）
    @GetMapping("/selectByPolygon")
    public Response selectByPolygon(String WKT, Integer SRID, String layer) {
        Ewkt ewkt = Ewkt.builder().WKT(WKT).SRID(SRID).build();
        MyList properties = new MyList().add("gid").add("name");
        JSONObject geojson = postgisService.selectByPolygonWithProperties(postgisService.ST_GeomFromEWKT(ewkt), properties, layer);
        return Response.builder().data(geojson).build();
    }

    // 圈选（clip）
    @GetMapping("/clipByPolygon")
    public Response clipByPolygon(String WKT, Integer SRID, String layer) {
        Ewkt ewkt = Ewkt.builder().WKT(WKT).SRID(SRID).build();
        MyList properties = new MyList().add("gid").add("name");
        JSONObject geojson = postgisService.clipByPolygonWithProperties(postgisService.ST_GeomFromEWKT(ewkt), properties, layer);
        return Response.builder().data(geojson).build();
    }
}
