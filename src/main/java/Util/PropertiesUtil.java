package Util;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {


    private static PropertiesUtil prop;
    private Properties properties;

    private PropertiesUtil(){
        properties = new Properties();
    }

    public static synchronized PropertiesUtil getInstance() {
        if(prop == null){
            prop = new PropertiesUtil();
        }
        return prop;
    }

    /**
     * Load the Properties file
     *
     * @param file
     * @throws IOException
     */
    public void load(File file) throws IOException{
        InputStream input = new FileInputStream(file);
        properties.load(input);
    }

    /**
     * Return the Value from properties file
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return properties.getProperty(key).trim();
    }
}
