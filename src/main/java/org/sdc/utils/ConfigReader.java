package org.sdc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utility class for reading configuration from properties file.
 */
public class ConfigReader {
    private Properties properties;

    /**
     * Constructor that loads properties from the default configuration file.
     */
    public ConfigReader() {
        this("config.properties");
    }

    /**
     * Constructor that loads properties from a specified configuration file.
     *
     * @param configFileName The name of the configuration file
     */
    public ConfigReader(String configFileName) {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                throw new IOException("Unable to find " + configFileName);
            }
            properties.load(input);
        } catch (IOException ex) {
            System.err.println("Error reading configuration file: " + ex.getMessage());
        }
    }

    /**
     * Get a property value by key.
     *
     * @param key The property key
     * @return The property value, or null if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get a property value with a default value if the key is not found.
     *
     * @param key The property key
     * @param defaultValue The default value to return if the key is not found
     * @return The property value, or the default value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
