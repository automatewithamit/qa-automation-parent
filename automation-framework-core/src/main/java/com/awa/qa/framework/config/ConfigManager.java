package com.awa.qa.framework.config;

import com.awa.qa.framework.exceptions.FrameworkException;

import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("framework.properties")) {

            if (is == null) {
                throw new FrameworkException("framework.properties not found in classpath");
            }
            PROPS.load(is);
        } catch (Exception e) {
            throw new FrameworkException("Failed to load framework.properties", e);
        }
    }

    private ConfigManager() {
        // prevent instantiation
    }

    /**
     * Get property with optional system property override.
     */
    public static String get(String key) {
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.isBlank()) {
            return sysProp;
        }
        String value = PROPS.getProperty(key);
        if (value == null) {
            throw new FrameworkException("Missing configuration key: " + key);
        }
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }
}
