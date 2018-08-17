
package com.appchina.collect.utils;

import java.util.Iterator;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PreferenceService {
    private static Log log = LogFactory.getLog(PreferenceService.class);
    private static CompositeConfiguration configuration;

    public PreferenceService() {
    }

    public static String getConfigBasePath() {
        String configPath = CommonPropertyPlaceholderConfigurer.class.getClassLoader().getResource("").getPath() + "config/";
        return configPath;
    }

    public static synchronized void init() {
        if (configuration == null) {
            configuration = new CompositeConfiguration();
            String proppath = getConfigBasePath() + SystemInfoParser.getHostName() + ".properties";

            PropertiesConfiguration propertiesConfiguration;
            Iterator iterator;
            String key;
            try {
                propertiesConfiguration = new PropertiesConfiguration(proppath);
                iterator = propertiesConfiguration.getKeys();

                while(iterator.hasNext()) {
                    key = (String)iterator.next();
                    log.debug("Init property from: " + SystemInfoParser.getHostName() + ".properties : " + key + " = " + propertiesConfiguration.getProperty(key));
                }

                configuration.addConfiguration(propertiesConfiguration);
            } catch (ConfigurationException var5) {
                log.error(var5.getMessage(), var5);
            }

            proppath = getConfigBasePath() + "common/common.properties";

            try {
                propertiesConfiguration = new PropertiesConfiguration(proppath);
                iterator = propertiesConfiguration.getKeys();

                while(iterator.hasNext()) {
                    key = (String)iterator.next();
                    log.debug("Init property from common.properties : " + key + " = " + propertiesConfiguration.getProperty(key));
                }

                configuration.addConfiguration(propertiesConfiguration);
            } catch (ConfigurationException var4) {
                log.error(var4.getMessage(), var4);
            }
        }

    }

    public static Configuration getCommonConfiguration() {
        if (configuration != null) {
            return configuration;
        } else {
            init();
            return getCommonConfiguration();
        }
    }

    public Configuration getConfiguration() {
        return getCommonConfiguration();
    }

    public static void main(String[] args) {
        String accessSecret=PreferenceService.getCommonConfiguration().getString("accessSecret");
        System.out.println(accessSecret);
    }
}
