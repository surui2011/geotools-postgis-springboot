_Documentation_
=
###*Class*
####module
* Ewkt - 用于规范postgis支持的EWKT
* Funtion - 用于规范postgis支持的函数
* Geom - 用于规范postgis支持的geom
* MyList - 继承ArrayList实现连续add，用于添加字段等需求
* Params - 继承HashMap实现连续put，用于mybatis传参
* SqlConstant - sql中的常量，例如统一[1、t、true、TRUE]为[true]
####function
* PostgisFunction - 统一管理postgis函数
* GeotoolsFunction - 统一管理geotools函数（尚未使用）
####service
* PostgisService - 通过整合springboot，实现创建geojson和两种圈选（select及clip）
###*xml*
####mapper
* Postgis.xml - 梳理部分可复用sql（随需求更新）

###*Examples*
* 圈选（select）
***
(GET)http://localhost:9999/postgis/selectByPolygon
***
参数：WKT、SRID、layer
* 圈选（clip）
***
(GET)http://localhost:9999/postgis/clipByPolygon
***
参数：WKT、SRID、layer