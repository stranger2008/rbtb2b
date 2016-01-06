/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.filter
 * FileName: AutoLoginFilter.java 
 */
package com.rbt.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rbt.common.Constants;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.MemberuserFuc;
import com.rbt.model.Memberlevel;
import com.rbt.model.Role;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IRoleService;

/**
 * @function 功能 用户自动登陆过滤器，主要是对登录过的用户，将其用户名和密码从cookis取出，根据cookis生命周期，进行自动登录
 * @author  创建人 邱景岩
 * @date  创建日期 Mar 31, 2012 4:57:54 PM
 */

public class AutoLoginFilter extends CreateSpringContext implements Filter {

	public void destroy() {  
        // TODO Auto-generated method stub  
  
    }  
  
    public void doFilter(ServletRequest arg0, ServletResponse arg1,  
            FilterChain arg2) throws IOException, ServletException {  
        HttpServletRequest request = (HttpServletRequest) arg0;  
        HttpServletResponse response = (HttpServletResponse) arg1;
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies(); 
        String[] cooks = null;  
        String username = null;  
        String password = null;  
        if (cookies != null) {  
            for (Cookie coo : cookies) {  
                String cvalue = coo.getValue(); 
                System.out.println(cvalue);
                cooks = cvalue.split("==");
                if (cooks.length == 2) {  
                    username = cooks[0];  
                    password = cooks[1];  
                }  
            }  
        }  
        if (username != null && password != null && MemberuserFuc.login(username, password)) {
        	
        	Map cookiesMap = new HashMap();
        	cookiesMap.put("user_name", username);
        	cookiesMap.put("password", password);
            List loginuserList = MemberuserFuc.getMemberuserObj().getList(cookiesMap);
            String user_id = "", cust_id = "", user_type = "", role_code = "";
    		// mem_type 0：企业 1：个人
    		String mem_type = "";
    		// 会员级别
    		String level_code = "";
    		if (loginuserList != null && loginuserList.size() > 0) {
    			Map loginMap = (HashMap) loginuserList.get(0);
    			if (loginMap.get("user_id") != null) {
    				user_id = loginMap.get("user_id").toString();
    			}
    			if (loginMap.get("cust_id") != null) {
    				cust_id = loginMap.get("cust_id").toString();
    			}
    			if (loginMap.get("user_type") != null) {
    				user_type = loginMap.get("user_type").toString();
    			}
    			if (loginMap.get("role_code") != null) {
    				role_code = loginMap.get("role_code").toString();
    			}
    			if (loginMap.get("mem_type") != null) {
    				mem_type = loginMap.get("mem_type").toString();
    			}
    			if (loginMap.get("level_code") != null) {
    				level_code = loginMap.get("level_code").toString();
    			}
    		}

    		// 根据会员级别level_code找出该级别下的菜单权限和操作权限
    		String menu_right = "", oper_right = "";
    		if (!level_code.equals("")) {
    			IMemberlevelService memberlevelService = (IMemberlevelService) getContext().getBean("memberlevelService");
    			Memberlevel memberlevel = memberlevelService.get(level_code);
    			if (memberlevel != null && memberlevel.getMenu_right() != null) {
    				menu_right = memberlevel.getMenu_right();
    			}
    			if (memberlevel != null && memberlevel.getOper_right() != null) {
    				oper_right = memberlevel.getOper_right();
    			}
    		}

    		// user_type 1：会员管理员 2：会员子账号
    		if (user_type.equals("2") && !role_code.equals("")) {
    			// 找出当前角色拥有哪些权限
    			String role_menu_right = "", role_oper_right = "";
    			IRoleService roleService = (IRoleService) getContext().getBean("roleService");
    			Role role = roleService.get(role_code);
    			if (role != null && role.getMenu_right() != null) {
    				role_menu_right = role.getMenu_right();
    			}
    			if (role != null && role.getOper_right() != null) {
    				role_oper_right = role.getOper_right();
    			}

    			// 会员子账号的菜单权限和操作权限都是根据会员级别来的
    			// 如果会员的级别被降下来了，想要的菜单权限和操作权限肯定也会少很多
    			// 可子账号还保留了之前的权限
    			// 所以下面就要过滤一下了，如果当前的角色权限的长度大于级别权限的长度的话，就要过滤喽

    			// 菜单权限过滤
    			if (role_menu_right.length() > menu_right.length()) {
    				String[] menu = role_menu_right.split(",");
    				StringBuffer sb = new StringBuffer();
    				for (int i = 0; i < menu.length; i++) {
    					if (!menu[i].equals("") && menu_right.indexOf(menu[i]) > -1) {
    						sb.append(menu[i]);
    						sb.append(",");
    					}
    				}
    				menu_right = sb.toString();
    			}

    			// 操作权限过滤
    			if (role_oper_right.length() > oper_right.length()) {
    				String[] menu = role_oper_right.split(",");
    				StringBuffer sb = new StringBuffer();
    				for (int i = 0; i < menu.length; i++) {
    					if (!menu[i].equals("") && oper_right.indexOf(menu[i]) > -1) {
    						sb.append(menu[i]);
    						sb.append(",");
    					}
    				}
    				oper_right = sb.toString();
    			}

    		}

    		// 将值存入session中
    		session.setAttribute(Constants.CUST_ID, cust_id);
    		session.setAttribute(Constants.CUST_TYPE, Constants.MEMBER_TYPE);
    		session.setAttribute(Constants.USER_NAME, username);
    		session.setAttribute(Constants.USER_ID, user_id);

    		session.setAttribute(Constants.MEM_TYPE, mem_type);
    		// 会员用户类型 1：管理员 2：子账户
    		session.setAttribute(Constants.USER_TYPE, user_type);
    		session.setAttribute(Constants.ROLE_ID, role_code);
    		// syscode sys:运营商 com:会员
    		session.setAttribute("syscode", "com");

    		// 会员级别
    		session.setAttribute("level_code", level_code);

    		// 会员的菜单权限和操作权限
    		session.setAttribute("menu_right", menu_right);
    		session.setAttribute("oper_right", oper_right);
            response.sendRedirect("/bmallindex.action");  
//            request.getRequestDispatcher("/bmallindex.action").forward(request, response);  
        }else{  
            arg2.doFilter(request,response);  
        }  
      
  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  
        // TODO Auto-generated method stub  
  
    }  

}
