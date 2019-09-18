_Documentation_
=
###### @Author - surui
###### @Email - surui2011@163.com
***
### *Class*
#### entity
* Categories - 用于接收categories数据表
* LayerStyle - 用于接收layer_style数据表
* StyleDO - 用于接收style数据表
#### module
* Ewkt - 用于规范postgis支持的EWKT
* Funtion - 用于规范postgis支持的函数
* Geom - 用于规范postgis支持的geom
* MyList - 继承ArrayList实现连续add，用于添加字段等需求
* Params - 继承HashMap实现连续put，用于mybatis传参
* SqlConstant - sql中的常量，例如统一"1、t、true、TRUE"为"true"
* StyleVO - 用于规范各类style(polygon/line/point)
* Symbology - 用于规范符号化(single/categories)
#### function
* PostgisFunction - 统一管理postgis函数
* GeotoolsFunction - 统一管理geotools函数（尚未使用）
#### service
* PostgisService - 通过整合springboot，实现创建geojson和两种圈选（select及clip）
* StyleService - style相关service
#### mapper
* Categories.xml - 对应categories数据表
* LayerStyle.xml - 对应layer_style数据表
* Postgis.xml - 对应空间数据表梳理部分可复用sql（随需求更新）
* Style.xml - 对应style数据表

### *Examples*
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

### *Update logs*
* 2019-09-10 整合 springboot 和 postgis ，实现空间数据表相关功能
* 2019-09-17 基于 postgresql 增加图层样式功能模块
* 2019-09-18 增加多数据源配置，添加 mysql 从数据源迎合复杂业务场景
