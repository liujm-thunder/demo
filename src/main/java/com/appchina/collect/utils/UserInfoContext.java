package com.appchina.collect.utils;

public final class UserInfoContext {
	
	private UserInfoContext() {}
	
	private static ThreadLocal<String> guid = new ThreadLocal<String>();
	private static ThreadLocal<String> ip = new ThreadLocal<String>();


	public static void clear() {
		guid.remove();
		ip.remove();
	}
	
	public static String getGUID() {
		return guid.get();
	}
	
	public static String getIp() {
		return ip.get();
	}

	public static void setGUID(String guidString) {
		guid.set(guidString);
	}

	public static void setIp(String ipString) {
		ip.set(ipString);
	}

}
