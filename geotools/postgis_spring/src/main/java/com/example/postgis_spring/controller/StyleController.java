package com.example.postgis_spring.controller;

import com.example.postgis_spring.service.StyleService;
import com.qupdi.sso.client.annonations.NoLogin;
import com.qupdi.sso.client.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName: StyleController
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@RestController
@RequestMapping("/style")
public class StyleController {
    @Resource
    private StyleService styleService;

    @GetMapping("/getLayerStyle")
    @NoLogin
    public Response getLayerStyle(String layer) {
        Map map = styleService.getLayerStyle(layer);
        return Response.builder().data(map).build();
    }
}
