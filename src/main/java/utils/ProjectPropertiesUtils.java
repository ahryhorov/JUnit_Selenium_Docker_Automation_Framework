package utils;

import java.io.InputStream;
import java.util.Properties;

public class ProjectPropertiesUtils {
    public static final String PROJECT_PROPERTIES_FILE = "project.properties";

    private static InputStream is;
    private static Properties properties = null;

    private static ProjectPropertiesUtils instance = null;

    private static ProjectProperties projectProperties;

    private static ThreadLocal<ProjectProperties> projectPropertiesThreadLocal = ThreadLocal.withInitial(() -> {
        Properties properties = new Properties();
        ProjectProperties projectProperties = new ProjectProperties();
        try (InputStream is = ProjectPropertiesUtils.class.getClassLoader().getResourceAsStream(PROJECT_PROPERTIES_FILE)) {
            properties.load(is);
            projectProperties.setSelectedHub(properties.getProperty("selectRunHub"));
            projectProperties.setDefaultTimeout(properties.getProperty("defaultTimeout"));
            // add other properties here...
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectProperties;
    });

    public static ProjectProperties getProjectProperties() {
        return projectPropertiesThreadLocal.get();
    }

    //    public static ProjectProperties getProjectProperties() {
//        if (instance == null) {
//            initialize();
//        }
//        return projectProperties;
//        //needed to exclude duplicate initializations
//    }
//
//    public static void initialize() {
//        properties = new Properties();
//        projectProperties = new ProjectProperties();
//        try {
//            is = ProjectPropertiesUtils.class.getClassLoader().getResourceAsStream(PROJECT_PROPERTIES_FILE);
//            properties.load(is);
//            projectProperties.setSelectedHub(properties.getProperty("selectRunHub"));
//            projectProperties.setDefaultTimeout(properties.getProperty("defaultTimeout"));
//            //add other properties here...
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (Exception e2) {
//                    e2.printStackTrace();
//                }
//            }
//        }
//    }
}
