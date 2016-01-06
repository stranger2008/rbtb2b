/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.util
 * FileName: IpSeekerInit.java
 */
package com.rbt.common.util;

import javax.servlet.http.HttpServletRequest;

import com.lean.ipsearch.IPSeeker;

/**
 * IP获取
 * @author 李良林
 */
public class IpSeekerInit {
	
	/*
	 * 单例
	 */
	private static IPSeeker seeker;
	
	public static IPSeeker getIpSeeker(){
		if(seeker == null){
			seeker = IPSeeker.getInstance();
		}
		return seeker;
	}
	
	public static String getAreaName(String ip){
		IPSeeker seeker = getIpSeeker();
        return seeker.getCountry(ip);
	}
	
	/**
	 * @function 功能 获取IP地址，在通过了Apache,Squid等反向代理软件也可以获取到客户端的真实IP地址
	 * @author 创建人 邱景岩
	 * @date 创建日期 Jul 26, 2011 4:27:37 PM
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
