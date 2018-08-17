//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.appchina.collect.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SystemInfoParser {
    private static final Log log = LogFactory.getLog(SystemInfoParser.class);

    public SystemInfoParser() {
    }

    public static String getHostName() {
        String all = null;
        BufferedReader br = null;
        String os = System.getProperty("os.name");
        if (os != null && !os.startsWith("Windows")) {
            try {
                Process p = Runtime.getRuntime().exec("hostname");
                br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                all = br.readLine();
            } catch (IOException var12) {
                log.error("Error in SystemInfoParser", var12);
            } finally {
                try {
                    br.close();
                } catch (IOException var11) {
                    log.error("Error in SystemInfoParser", var11);
                }

            }
        } else if (os != null) {
            all = System.getenv("COMPUTERNAME");
        }

        if (all == null) {
            throw new RuntimeException("Host Name is Null");
        } else {
            return all;
        }
    }
}
