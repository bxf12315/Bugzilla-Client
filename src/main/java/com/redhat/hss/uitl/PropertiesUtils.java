package com.redhat.hss.uitl;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * read Maitai.properties
 * 
 * @author cdou@redhat.com
 * 
 */
public class PropertiesUtils {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
    public static final String BUGZILLA_PROPERTIES = "bugzilla.properties";
    private static Configuration configuration;

    private PropertiesUtils() {

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public static String getValue(String key) {
        if (empty()) {
            initProperties();
        }
        return configuration.getString(key);
    }

    public static int getIntValue(String key) {
        if (empty()) {
            initProperties();
        }
        return configuration.getInt(key);
    }

    public static boolean empty() {
        return null == configuration || configuration.isEmpty();
    }

    public static void initProperties() {
        try {
            configuration = new PropertiesConfiguration(BUGZILLA_PROPERTIES);
        } catch (ConfigurationException e) {
            log.error("read Maitai.properties error!", e);
        }
    }

}
