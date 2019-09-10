import org.geotools.data.ows.HTTPClient;
import org.geotools.data.ows.SimpleHttpClient;
import org.geotools.data.wfs.internal.TransactionRequest;
import org.geotools.data.wfs.internal.TransactionResponse;
import org.geotools.data.wfs.internal.WFSClient;
import org.geotools.data.wfs.internal.WFSConfig;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.List;

/**
 * @ClassName: MyTest
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class MyTest {
    public static void main(String[] args) throws Exception {
        //自定义xml的格式来实现
//        Map connectionParameters = new HashMap();
//        connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", );
        HTTPClient httpClient = new SimpleHttpClient();
        httpClient.setConnectTimeout(10);
        httpClient.setTryGzip(true);
        WFSConfig wfsConfig = new WFSConfig();
//        String url = geoServerConfig.getUrl() + "/ows?service=wfs&version=1.0.0&request=GetCapabilities";
//        String url = "http://localhost:8280/geoserver/ows?service=wfs&version=1.0.0&request=GetCapabilities";
        String url = "http://localhost:8280/geoserver/wfs?request=GetCapabilities";
        WFSClient wfsClient = new WFSClient(new URL(url), httpClient, wfsConfig);
        TransactionRequest transactionRequest = wfsClient.createTransaction();
        transactionRequest.setOutputFormat("json");
        QName typeName = new QName("http://surui.test.com", "qujie");
        transactionRequest.setTypeName(typeName);
        TransactionRequest.Insert insert = transactionRequest.createInsert(typeName);
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
        LineString lineString = geometryFactory.createLineString(new Coordinate[]{new Coordinate(0, 0), new Coordinate(1, 1)});
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.setName("qujie");
        typeBuilder.setNamespaceURI("http://surui.test.com");
        typeBuilder.setSRS("EPSG:4326");
        typeBuilder.setCRS(CRS.decode("EPSG:4326"));
        typeBuilder.add("name", String.class);
        typeBuilder.add("line", LineString.class, CRS.decode("EPSG:4326"));
        SimpleFeatureType simpleFeatureType = typeBuilder.buildFeatureType();
        SimpleFeatureBuilder simpleFeatureBuilder = new SimpleFeatureBuilder(simpleFeatureType);
        simpleFeatureBuilder.set("name", "测试");
        simpleFeatureBuilder.set("line", lineString);
        SimpleFeature feature = simpleFeatureBuilder.buildFeature(null);
        insert.add(feature);
        transactionRequest.add(insert);
        TransactionResponse transactionResponse = wfsClient.issueTransaction(transactionRequest);
        List fids = transactionResponse.getInsertedFids();

    }
}
