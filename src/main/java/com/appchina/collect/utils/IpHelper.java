package com.appchina.collect.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class IpHelper {
    public IpHelper() {
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip == null || "0:0:0:0:0:0:0:1".equals(ip) || "::1".equals(ip)) {
            ip = "127.0.0.1";
        }

        if(ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        return ip;
    }

    public static long getIpNum(HttpServletRequest req) {
        return addrToNum(getIpAddr(req));
    }

    public static long addrToNum(String ip) {
        if(ip != null && ip.trim().length() > 0) {
            if(!isIp(ip)) {
                return 0L;
            } else {
                long[] ipNum = new long[4];

                try {
                    int position1 = ip.indexOf(".");
                    int position2 = ip.indexOf(".", position1 + 1);
                    int position3 = ip.indexOf(".", position2 + 1);
                    ipNum[0] = Long.parseLong(ip.substring(0, position1));
                    ipNum[1] = Long.parseLong(ip.substring(position1 + 1, position2));
                    ipNum[2] = Long.parseLong(ip.substring(position2 + 1, position3));
                    ipNum[3] = Long.parseLong(ip.substring(position3 + 1));
                } catch (Exception var5) {
                    Arrays.fill(ipNum, 0L);
                }

                return (ipNum[0] << 24) + (ipNum[1] << 16) + (ipNum[2] << 8) + ipNum[3];
            }
        } else {
            return 0L;
        }
    }

    public static String numToAddr(long ipNum) {
        StringBuffer ip = new StringBuffer("");

        try {
            ip.append(String.valueOf(ipNum >>> 24));
            ip.append(".");
            ip.append(String.valueOf((ipNum & 16777215L) >>> 16));
            ip.append(".");
            ip.append(String.valueOf((ipNum & 65535L) >>> 8));
            ip.append(".");
            ip.append(String.valueOf(ipNum & 255L));
        } catch (Exception var4) {
            ip.append("");
        }

        return ip.toString();
    }

    private static boolean isIp(String ip) {
        return ip.matches("^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$");
    }
}
