/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.interceptor
 * FileName: RightIterceptor.java 
 */

package com.rbt.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.rbt.common.Constants;
import com.rbt.service.IRolerightService;

/**
 * @function 功能 用户操作权限控制
 * @author  创建人 邱景岩
 * @date  创建日期 Sep 9, 2011 12:16:30 PM
 */
public class RightIterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 5436397213271026801L;
	/*
	 * 无操作权限所跳转的页面地址
	 */ 
	private static final String NO_OPERATE_PAGE = "/include/rightControl.jsp";
	/*
	 *  操作权限业务接口
	 */
	@Autowired
	private IRolerightService rolerightService;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.interceptor.AbstractInterceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = req.getSession();
		//user_type: 1：会员管理员 2：会员子账号 3：运营商管理员 4：运营商子账号
		String user_type = "";
		if (session.getAttribute(Constants.USER_TYPE) != null) {
			user_type = session.getAttribute(Constants.USER_TYPE).toString();
		}
		//运营商和会员的管理员跟角色没有关系
		//所以就不需要向下执行了
		//运营商的管理员可以查看全部的操作，会员的管理员是按照会员级别来判断权限的，所以：
		if(user_type.equals("1") || user_type.equals("3")){
			return actionInvocation.invoke();
		}else{
			
			//当用户是子账户时，实行控制
			
			//获取平台所有的操作权限
			Map operateMap = new HashMap();
			List operateList = this.rolerightService.getList(operateMap);

			//获取当前用户的操作权限
			String operateUrl = operateUrl(operateList);
			
			//获取action的名称
			String actionName = actionInvocation.getInvocationContext().getName();
			
			//实施控制
			StringBuffer sb = new StringBuffer();
			Map urlMap = new HashMap();
			if (operateList != null && operateList.size() > 0) {
				for (int i = 0; i < operateList.size(); i++) {
					urlMap = (Map) operateList.get(i);
					if (urlMap.get("url") != null) {
						sb.append(urlMap.get("url").toString() + ",");
					}
				}
				// 如果此时url在操作权限集合中，则进入角色操作权限的判断
				if (sb.indexOf(actionName) > -1) {
					// 以下代码为操作权限控制，根据用户名找所属角色
					//在根据角色找出对应的操作权限，在按照当前的URL判断该用户是否有此权限
					if (operateUrl.indexOf(actionName) == -1) {
						response.sendRedirect(NO_OPERATE_PAGE);
						return "";
					}else{
						return actionInvocation.invoke();
					}
				}else{
					return actionInvocation.invoke();
				}
			}else{
				return actionInvocation.invoke();
			}
		}
		
		
		
	}

	/**
	 * @function 功能 从数据库获取操作权限表的所有运行商url，根据角色拥有的权限匹配完后，返回
	 * @author 创建人 邱景岩
	 * @date 创建日期 Sep 7, 2011 10:32:55 AM
	 */
	public String operateUrl(List operateList) {
		
		HttpServletRequest req = ServletActionContext.getRequest();
		HttpSession session = req.getSession();
		
		//操作权限，登陆的时候就放进去了
		String oper_right = "";
		if (session.getAttribute("oper_right") != null) {
			oper_right = session.getAttribute("oper_right").toString();
		}
		
		StringBuffer sb = new StringBuffer();
		Map listMap = new HashMap();
		if (operateList != null && operateList.size() > 0) {
			for (int i = 0; i < operateList.size(); i++) {
				listMap = (Map) operateList.get(i);
				String url = "",right_id="";
				if (listMap.get("url") != null) {
					url = listMap.get("url").toString();
				}
				if (listMap.get("right_id") != null) {
					right_id = listMap.get("right_id").toString();
				}
				//当前角色的操作串匹配从数据库获取操作权限集合
				if (oper_right.indexOf(right_id) > -1) {
					sb.append(url + ",");
				}
			}
		}
		return sb.toString();
	}

}
