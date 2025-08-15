package utils;

import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "src" + File.separator +
            "test" + File.separator +
            "resources" + File.separator +
            "configFiles" + File.separator;
    private String fileName;

    public ConfigFileReader(String configFileName) {
        this.fileName = configFileName;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath + fileName));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(fileName + " not found at " + propertyFilePath);
        }
    }

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("url is not available in properties files:" + fileName);
        }
    }

    public String getBrowser() {
        String browser = properties.getProperty("browser");
        if (browser != null) {
            return browser;
        } else {
            throw new RuntimeException("browser is not available in properties files:" + fileName);
        }
    }

    public String getPropertyValue (String key) {
        String value = properties.getProperty(key);
        if (value!=null){
            return value;
        } else {
            throw new RuntimeException(key+" can not be found in "+propertyFilePath+fileName);
        }
    }
}
