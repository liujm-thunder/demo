//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appchina.collect.utils;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CommonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private static Log LOG = LogFactory.getLog(CommonPropertyPlaceholderConfigurer.class);

    public CommonPropertyPlaceholderConfigurer() {
    }

    protected String resolvePlaceholder(String placeholder, Properties props) {
        String value = PreferenceService.getCommonConfiguration().getString(placeholder);
        return value == null ? super.resolvePlaceholder(placeholder, props) : value;
    }
}
