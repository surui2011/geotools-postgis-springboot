import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureStore;
import org.geotools.data.FeatureWriter;
import org.geotools.data.postgis.PostgisNGDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.FilterFactoryImpl;
import org.geotools.filter.identity.FeatureIdImpl;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: PostGisTest
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class PostGisTest {
    static DataStore pgDatastore = null;

    // 是否需要自建连接池：待定

    public static void connectPostGis(String dbtype, String host, int port, String database, String schema, String user, String password) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(PostgisNGDataStoreFactory.DBTYPE.key, dbtype); //需要连接何种数据库，postgis or mysql
        params.put(PostgisNGDataStoreFactory.HOST.key, host);//ip地址
        params.put(PostgisNGDataStoreFactory.PORT.key, port);//端口号
        params.put(PostgisNGDataStoreFactory.DATABASE.key, database);//需要连接的数据库
        params.put(PostgisNGDataStoreFactory.SCHEMA.key, schema);//架构
        params.put(PostgisNGDataStoreFactory.USER.key, user);//需要连接数据库的名称
        params.put(PostgisNGDataStoreFactory.PASSWD.key, password);//数据库的密码
        try {
            //获取存储空间
            pgDatastore = DataStoreFinder.getDataStore(params);
            if (pgDatastore != null) {
                System.out.println("系统连接到位于：" + host + "的空间数据库" + database
                        + "成功！");

            } else {
                System.out.println("系统连接到位于：" + host + "的空间数据库" + database
                        + "失败！请检查相关参数");

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("系统连接到位于：" + host + "的空间数据库" + database
                    + "失败！请检查相关参数");
        }
    }

    public static void main(String[] args) throws Exception {

        Map<String, Object> params = new HashMap<String, Object>();
        String dbtype = "postgis";
        String host = "172.16.248.128";
        int port = 5432;
        String database = "wgs_84_test";
        String schema = "public";
        String user = "postgres";
        String password = "111111";
        // 连接数据库
        connectPostGis(dbtype, host, port, database, schema, user, password);

        SimpleFeatureSource fSource = null;
        //根据表名获取source
        String table_name = "china";
        fSource = pgDatastore.getFeatureSource(table_name);
        FilterFactory2 ff = new FilterFactoryImpl();
        Filter filter = ff.id(new FeatureIdImpl("7"));
        /**
         * @Description 修改
         * @Author SR
         * @Param
         */
        System.out.println("==========================修改==========================");
        FeatureWriter fWriter = pgDatastore.getFeatureWriter(table_name, filter, ((FeatureStore) fSource).getTransaction());
        while (fWriter.hasNext()) {
            SimpleFeature feature = (SimpleFeature) fWriter.next();
            String field = "name";
            String value = "河北省11";
            String before = (String) feature.getAttribute(field);
            feature.setAttribute(field, value);
            fWriter.write();
            System.out.println("修改字段：" + field);
            System.out.println(before + " --> " + value);
        }
        fWriter.close();
        /**
         * @Description 查
         * @Author SR
         * @Param
         */
        System.out.println("==========================查询==========================");
        SimpleFeatureIterator iterator = fSource.getFeatures(filter).features();
        while (iterator.hasNext()) {
            SimpleFeature simpleFeature = iterator.next();
            System.out.println("name: " + simpleFeature.getAttribute("name"));
        }

    }
}
