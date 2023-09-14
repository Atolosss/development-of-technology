package http.utils;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class ReadPropertiesUtils {
    public String getProperty(final String key) {
            try (InputStream inputStream = ReadPropertiesUtils.class.getClassLoader()
                    .getResourceAsStream("application.properties")) {
                Properties properties = new Properties();
                properties.load(inputStream);
                return properties.getProperty(key);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

