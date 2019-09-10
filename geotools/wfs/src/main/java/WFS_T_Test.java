import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.factory.CommonFactoryFinder;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.feature.simple.SimpleFeatureImpl;
import org.geotools.filter.identity.FeatureIdImpl;
import org.geotools.util.factory.GeoTools;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;

import java.util.HashMap;
import java.util.Map;

public class WFS_T_Test {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String getCapabilities = "http://localhost:8080/geoserver/wfs?REQUEST=GetCapabilities";
        Map connectionParameters = new HashMap();
        connectionParameters.put("WFSDataStoreFactory:GET_CAPABILITIES_URL", getCapabilities);

// Step 2 - connection
        DataStore data = DataStoreFinder.getDataStore(connectionParameters);

// Step 3 - discouvery
        String typeName = "surui_test:qujie";
        SimpleFeatureType schema = data.getSchema(typeName);
        System.out.println(schema.getUserData());
        System.out.println("schema: " + schema.getAttributeCount());


//        //创建新模式
//        SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();
//        builder.setName(schema.getName());
//        builder.setSuperType((SimpleFeatureType) schema.getSuper());
//        builder.addAll(schema.getAttributeDescriptors());
//        //添加新属性
//        builder.add("name", String.class);
//        //构建新架构
//        SimpleFeatureType nSchema = builder.buildFeatureType();
//        System.out.println("nschema: " + nSchema.getAttributeCount());
//        //循环功能添加新属性
//        List<SimpleFeature> features1 = new ArrayList<>();
//        try (SimpleFeatureIterator itr = data.getFeatureSource(typeName).getFeatures().features()) {
//            while (itr.hasNext()) {
//                SimpleFeature f = itr.next();
//                SimpleFeature f2 = DataUtilities.reType(nSchema, f);
//                f2.setAttribute("name", "new_name");
//                //System.out.println(f2);
//                features1.add(f2);
//            }
//        }


// Step 4 - target
        FeatureSource<SimpleFeatureType, SimpleFeature> source = data.getFeatureSource(typeName);
//        SimpleFeatureSource source = data.getFeatureSource(typeName);
        System.out.println(source.getSchema().getAttributeCount());

// Step 5 - query
//        String geomName = schema.getDefaultGeometry().getLocalName();
        String geomName = schema.getGeometryDescriptor().getLocalName();

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2(GeoTools.getDefaultHints());
//        Intersects filter = ff.intersects(ff.property(geomName), ff.literal(polygon));
        Filter filter = ff.id(new FeatureIdImpl("qujie_test.2"));
//        Filter filter = CQL.toFilter("fid='qujie_test.13'");
//        Filter filter = ff.equal(ff.property("name"), ff.literal("15"));

        Query query = new Query(typeName, filter, new String[]{geomName});
        FeatureCollection features = source.getFeatures(filter);
//        SimpleFeatureCollection features = source.getFeatures(filter);
//        FeatureCollectionTableModel model = new FeatureCollectionTableModel(features);
//        System.out.println(model.getRowCount());
        FeatureIterator iterator = features.features();
//        try {
        while (iterator.hasNext()) {
            SimpleFeatureImpl feature = (SimpleFeatureImpl) iterator.next();
//                bounds.include(feature.getBounds());
//            Collection collections = feature.getProperties();
//            Collection attributes = feature.getAttributes();
//            System.out.println(collections);
//            System.out.println(collections.size());
//            System.out.println(attributes);
//            System.out.println(attributes.size());
        }
//        } finally {
//            features.close( iterator );
//            features.features().close();
//        }
    }
}
