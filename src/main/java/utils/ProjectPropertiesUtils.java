package utils;

import java.io.InputStream;
import java.util.Properties;

public class ProjectPropertiesUtils {
    public static final String PROJECT_PROPERTIES_FILE = "project.properties";

    private static InputStream is;
    private static Properties properties = null;

    private static ProjectPropertiesUtils instance = null;

    private static ProjectProperties projectProperties;

    public static ProjectProperties getProjectProperties() {
        if (instance == null) {
            initialize();
        }
        return projectProperties;
        //needed to exclude duplicate initializations
    }

    public static void initialize() {
        properties = new Properties();
        projectProperties = new ProjectProperties();
        try {
            is = ProjectPropertiesUtils.class.getClassLoader().getResourceAsStream(PROJECT_PROPERTIES_FILE);
            //TODO FIX Properties for Parallel run
            properties.load(is);
            projectProperties.setSelectedHub(properties.getProperty("selectRunHub"));
            projectProperties.setDefaultTimeout(properties.getProperty("defaultTimeout"));
            //add other properties here...
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
