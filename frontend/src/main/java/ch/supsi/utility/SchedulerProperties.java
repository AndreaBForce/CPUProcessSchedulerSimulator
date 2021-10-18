package ch.supsi.utility;

import java.io.IOException;
import java.util.Properties;

public class SchedulerProperties {

    public static Properties getAppInfo() {
        final Properties properties = new Properties();
        try {
            properties.load(SchedulerProperties.class.getClassLoader().getResourceAsStream("pom.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
