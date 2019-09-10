package com.example.postgis_spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.module.Ewkt;
import com.example.postgis_spring.module.MyList;
import com.example.postgis_spring.service.PostgisService;
import com.qupdi.sso.client.annonations.NoLogin;
import com.qupdi.sso.client.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    // 圈选（select）
    @GetMapping("selectByPolygon")
    @NoLogin
    public Response selectByPolygon(String WKT, Integer SRID, String layer) {
        Ewkt ewkt = Ewkt.builder().WKT(WKT).SRID(SRID).build();
        MyList properties = new MyList().add("gid").add("name");
        JSONObject geojson = postgisService.selectByPolygonWithProperties(postgisService.ST_GeomFromEWKT(ewkt), properties, layer);
        return Response.builder().data(geojson).build();
    }

    // 圈选（clip）
    @GetMapping("clipByPolygon")
    @NoLogin
    public Response clipByPolygon(String WKT, Integer SRID, String layer) {
        Ewkt ewkt = Ewkt.builder().WKT(WKT).SRID(SRID).build();
        MyList properties = new MyList().add("gid").add("name");
        JSONObject geojson = postgisService.clipByPolygonWithProperties(postgisService.ST_GeomFromEWKT(ewkt), properties, layer);
        return Response.builder().data(geojson).build();
    }
}
