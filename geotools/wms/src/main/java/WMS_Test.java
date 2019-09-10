import com.alibaba.fastjson.JSON;
import org.geotools.ows.ServiceException;
import org.geotools.ows.wms.Layer;
import org.geotools.ows.wms.WMSCapabilities;
import org.geotools.ows.wms.WMSUtils;
import org.geotools.ows.wms.WebMapServer;
import org.geotools.ows.wms.request.GetFeatureInfoRequest;
import org.geotools.ows.wms.request.GetMapRequest;
import org.geotools.ows.wms.response.GetFeatureInfoResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @ClassName: WMS_Test
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class WMS_Test {
    public static void main(String[] args) throws Exception {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/geoserver/ows?service=wms&version=1.1.1&request=GetCapabilities");
        } catch (MalformedURLException e) {
            //will not happen
        }

        WebMapServer wms = null;
        try {
            wms = new WebMapServer(url);

            WMSCapabilities capabilities = wms.getCapabilities();

            /**
             * @Description GetMapRequest
             * @Author SR
             * @Param
             */
            GetMapRequest mapRequest = wms.createGetMapRequest();
            mapRequest.setFormat("image/png");
            mapRequest.setDimensions("583", "420"); //sets the dimensions of the image to be returned from the server
            mapRequest.setTransparent(true);
            mapRequest.setSRS("EPSG:4528");
//            mapRequest.setBBox("40526515.58992193,3991446.7963293483,40527180.46004606,3995040.0869588656");
//            mapRequest.setBBox("40526516,3991446.7,40527180.4,3995040.0");
            mapRequest.setBBox("4.0526716E7,3991643.5,4.0529976E7,3994840.5");

            for (Layer layer : WMSUtils.getNamedLayers(capabilities)) {
                mapRequest.addLayer(layer);
            }
//            GetMapResponse mapResponse = (GetMapResponse) wms.issueRequest(mapRequest);
//            InputStream inputStream = mapResponse.getInputStream();
//            File imageFile = new File("E:/my_code/test/image/getMap1.png");
//            FileUtils.saveFile(inputStream, imageFile);
            /**
             * @Description GetFeatureInfoRequest
             * @Author SR
             * @Param
             */
            GetFeatureInfoRequest featureInfoRequest = wms.createGetFeatureInfoRequest(mapRequest);
            featureInfoRequest.setInfoFormat("application/json");
            featureInfoRequest.setQueryPoint(200, 200);
            for (Layer layer : WMSUtils.getNamedLayers(capabilities)) {
                featureInfoRequest.addQueryLayer(layer);
            }
            GetFeatureInfoResponse featureInfoResponse = wms.issueRequest(featureInfoRequest);

            InputStream featureInputStream = featureInfoResponse.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(featureInputStream, "UTF-8"));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            if ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println(JSON.parseObject(stringBuilder.toString()).getJSONArray("features").get(0));

        } catch (IOException e) {
            e.printStackTrace();
            //There was an error communicating with the server
            //For example, the server is down
        } catch (ServiceException e) {
            e.printStackTrace();
            //The server returned a ServiceException (unusual in this case)
        }
    }
}
