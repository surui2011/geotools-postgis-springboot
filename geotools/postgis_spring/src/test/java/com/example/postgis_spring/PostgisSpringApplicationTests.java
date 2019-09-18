package com.example.postgis_spring;

import com.example.postgis_spring.dao.mysql.TestDao;
import com.example.postgis_spring.dao.postgresql.LayerStyleDao;
import com.example.postgis_spring.dao.postgresql.PostgisDao;
import com.example.postgis_spring.dao.postgresql.StyleDao;
import com.example.postgis_spring.service.PostgisService;
import com.example.postgis_spring.service.StyleService;
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
    @Resource
    StyleDao styleDao;
    @Resource
    LayerStyleDao layerStyleDao;
    @Resource
    StyleService styleService;
    @Resource
    TestDao testDao;

    @Test
    public void test() throws Exception {
        List styleList = styleDao.getStyle(null);
        List mysqlList = testDao.getData(null);
        System.out.println(styleList);
        System.out.println(mysqlList);
    }

    @Test
    public void test1() {
    }

    public static void main(String[] args) throws Exception {
    }

}
