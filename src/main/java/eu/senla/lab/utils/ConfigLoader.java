package eu.senla.lab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    public ConfigLoader() throws IOException {
        InputStream input = Files.newInputStream(Paths.get("src/test/resources/config.properties"));
        properties = new Properties();
        properties.load(input);
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null ){
            try {
                configLoader = new ConfigLoader();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return configLoader;
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop != null) return prop;
        else throw new RuntimeException("property username is not specified in the config.properties file: ");
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the config.properties file: ");
    }

    public String getBaseUri(){
        String prop = properties.getProperty("baseUri");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUri is not specified in the config.properties file: ");
    }
}
