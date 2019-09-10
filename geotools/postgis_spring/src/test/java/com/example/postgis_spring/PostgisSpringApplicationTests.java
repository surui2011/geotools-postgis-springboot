package com.example.postgis_spring;

import com.alibaba.fastjson.JSONObject;
import com.example.postgis_spring.dao.PostgisDao;
import com.example.postgis_spring.function.PostgisFunction;
import com.example.postgis_spring.module.*;
import com.example.postgis_spring.service.PostgisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgisSpringApplicationTests {
    @Resource
    PostgisDao postgisDao;
    @Resource
    PostgisService postgisService;

    @Test
    public void test() throws Exception {
        Ewkt ewkt = Ewkt.builder()
                .WKT("POLYGON((40527326 3992110, 40527489 3992114, 40527555 3991986, 40527326 3992110))")
                .SRID(4528)
                .build();
        Geom createGeom = postgisService.ST_GeomFromEWKT(ewkt);
        Params params = new Params().put("select", new PostgisFunction().ST_AsGeoJSON(createGeom));
        System.out.println(postgisDao.getGeoJson(params));
//        Geom geom = postgisService.findByid(5);
//        boolean result = postgisService.ifIntersect(createGeom, geom);
        MyList properties = new MyList().add("gid").add("name");
        JSONObject result = postgisService.clipByPolygonWithProperties(createGeom, properties, "qujie");
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        PostgisFunction postgisFunction = new PostgisFunction();
        List list = SqlConstant.TRUE.getValues();
        System.out.println(list.contains("4"));
    }

}
