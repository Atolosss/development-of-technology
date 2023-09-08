package http;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//TODO: implementation
@UtilityClass
public class ReadPropertiesUtils {
    public String getProperty(String key) {
            try (InputStream inputStream = http.ReadPropertiesUtils.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties.getProperty(key)
                        ;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

