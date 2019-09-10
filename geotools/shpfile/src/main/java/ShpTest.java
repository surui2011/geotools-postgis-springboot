import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.util.factory.GeoTools;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

import java.io.File;
import java.util.Collection;

/**
 * @ClassName: ShpTest
 * @Auther: SR
 * @Email: surui2011@163.com
 */
public class ShpTest {

    public static void main(String[] args) throws Exception {
        String shp_file = "E:/my_code/test/qujie.shp";
        FileDataStore fileDataStore = FileDataStoreFinder.getDataStore(new File(shp_file));
        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
        Filter filter = ff.id(new FeatureIdImpl("qujie.3"));
        SimpleFeatureIterator iterator = fileDataStore.getFeatureSource().getFeatures(filter).features();
        while (iterator.hasNext()) {
            SimpleFeature simpleFeature = iterator.next();
            Collection<Property> collection = simpleFeature.getProperties();
            for (Property property : collection) {
                System.out.println(property.getName());
            }
        }
    }
}
