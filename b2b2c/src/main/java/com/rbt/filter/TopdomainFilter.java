/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.filter
 * FileName: TopdomainFilter.java
 */
package com.rbt.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.common.Constants;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.service.IMemberuserService;
import com.rbt.service.ITopdomainService;

/**
 * @function 功能 会员顶级域名绑定过滤器
 * @author 创建人 蔡毅存
 * @date 创建日期 July 6, 2011
 */
public class TopdomainFilter extends CreateSpringContext implements Filter {

	/*
	 * 方法描述：域名绑定页面
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws ServletException, IOException {
		PropertiesUtil pu = new PropertiesUtil(Constants.JDBC_NAME);
		String filterswitch = pu.readValue("filterswitch");
		if (filterswitch.equals("0")) {
			chain.doFilter(req, resp);
		} else if (filterswitch.equals("1")) {
			// 主站域名，如：http://www.ruibaotong.net
			String cfg_basehost = SysconfigFuc.getSysValue("cfg_basehost");

			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) resp;

			// 获取请求域名，如：http://sss.rbtssi.com:8000/
			String reqDomain = getReqDomain(request);

			// 获取请求地址，如下：
			// http://sss.rbtssi.com:8000/
			// http://sss.rbtssi.com:8000/include/css/wrong.css
			// http://sss.rbtssi.com:8000/templets/default/images/logo.gif
			String reqUrl = request.getRequestURL().toString();

			// 判断是否和主站域名一样，一样则不转向
			if (reqDomain.indexOf(cfg_basehost) > -1) {
				chain.doFilter(req, resp);
			} else {
				// 自定义域名，映射的Url地址
				String goUrl = getGourlBySubdomain(reqDomain);

				// 未找到域名映射信息，则pass
				if (goUrl.equals("")) {
					chain.doFilter(req, resp);
				} else {
					System.out.println("========================" + reqUrl
							+ "=============================");
					// 请求地址和子域名一样，则转向，其他资源文件如图片、js、样式不转向
					if (reqUrl.equals(reqDomain)) {
						RequestDispatcher requestdis = request
								.getRequestDispatcher(goUrl);
						requestdis.forward(request, response);
					} else {
						chain.doFilter(req, resp);
					}
				}
			}
		}
	}

	// 获取请求域名
	public String getReqDomain(HttpServletRequest request) {
		String port = String.valueOf(request.getServerPort());
		if (!port.equals("80")) {
			port = ":" + request.getServerPort();
		} else {
			port = "";
		}
		String realPath = request.getServerName()
				+ port
				+ request.getContextPath()
				+ request.getServletPath().substring(0,
						request.getServletPath().lastIndexOf("/") + 1);

		int xiePos = realPath.indexOf("/");
		realPath = realPath.substring(0, xiePos+1);
		realPath = "http://" + realPath;
		return realPath;
	}

	public String getGourlBySubdomain(String subdomain) {
		String gourl = "";
		// 注入topdomainService
		ITopdomainService topdomainService = (ITopdomainService) getContext()
				.getBean("topdomainService");
        gourl=topdomainService.domainRetUrl(subdomain);
		
		return gourl;
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void include(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {

	}

}
