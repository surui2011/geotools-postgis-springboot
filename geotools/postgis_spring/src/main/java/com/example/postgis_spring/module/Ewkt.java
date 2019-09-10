package com.example.postgis_spring.module;

import lombok.Builder;

/**
 * @ClassName: Ewkt
 * @Auther: SR
 * @Email: surui2011@163.com
 */
@Builder
public class Ewkt {
    private String WKT;
    private Integer SRID;

    public String getEwkt() {
        String sridPart = SRID == null ? "" : "SRID=" + SRID + ";";
        return sridPart + WKT;
    }
}
