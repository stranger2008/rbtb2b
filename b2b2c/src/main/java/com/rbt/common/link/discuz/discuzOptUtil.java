/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.link.discuz
 * FileName: discuzOptUtil.java 
 */
package com.rbt.common.link.discuz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.rbt.common.Constants;

/**
 * 本类实现在如何实现在登入/登出，以及注册 作者：胡惜坤 创建时间：2012-08-21
 */
public class discuzOptUtil {

	/**
	 * @param args
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {

	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 同步论坛，登录论坛操作
	 * 
	 * @param user_name
	 *            用户名
	 * @param discuzpwd
	 *            密码
	 * @param cfg_discuz_open
	 *            系统设置是否启用同步论坛，如果值为：0表示同步，1：不同步
	 * @throws java.io.IOException
	 */
	public void userLoginDiscuz(String user_name, String discuzpwd,
			String cfg_discuz_open) throws IOException {

		// 同步登录Discuz论坛 如果：cfg_discuz_open的值为0：开始同步，如果为1：不开启同步
		if (cfg_discuz_open.equals("0")) {
			if (getSession().getAttribute(Constants.DISCUZ_STATE) != null
					&& getSession().getAttribute(Constants.DISCUZ_STATE)
							.equals("0")) {
				String getreturnJS = "";// 获取登录操作，返回的可执行的JS代码
				// 登录先，退出原来论坛的帐号
				userloginout(cfg_discuz_open);
				// 登录操作
				getreturnJS = DiscuzLogin(user_name, discuzpwd);
				// 判断返回值是不是包含可执行的JS脚本
				if (getreturnJS.contains("script")) {

					// 将获得可执行的JS串存入session中
					getSession().setAttribute(Constants.DISCUZ_LOGIN_JS,
							getreturnJS);
					// discuz_state 的值为1：表示已经登录论坛，0：表示还没有登录论坛
					getSession().setAttribute(Constants.DISCUZ_STATE, "0");
				}
			}
		}
	}

	/**
	 * 同步论坛，退出论坛登录
	 * 
	 * @param cfg_discuz_open
	 *            系统设置是否启用同步论坛，如果值为：0表示同步，1：不同步
	 */
	public void userloginout(String cfg_discuz_open) {

		String retJS = "";
		// 同步登录Discuz论坛 如果：cfg_discuz_open的值为0：开始同步，如果为1：不开启同步
		if (cfg_discuz_open.equals("0")) {
			retJS = DiscuzLogout();
			// 判断返回值是不是包含可执行的JS脚本
			if (retJS.contains("script")) {
				// 将获得可执行的JS串存入session中
				getSession().setAttribute(Constants.DISCUZ_LOGINOUT_JS, retJS);
			}
		}
	}

	/**
	 * 同步论坛,同步论坛注册
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param email
	 *            电子邮箱
	 * @param cfg_discuz_open
	 *            系统设置是否启用同步论坛，如果值为：0表示同步，1：不同步
	 */
	public void userregister(String username, String password, String email,
			String cfg_discuz_open) {

		// 同步登录Discuz论坛 如果：cfg_discuz_open的值为0：开始同步，如果为1：不开启同步
		if (cfg_discuz_open.equals("0")) {
			DiscuzRegister(username, password, email);
		}
	}
	
	
	public void usermodifypwd(String username, String oldpw, String newpw,String cfg_discuz_open) throws IOException{
		
		// 同步登录Discuz论坛 如果：cfg_discuz_open的值为0：开始同步，如果为1：不开启同步
		if (cfg_discuz_open.equals("0")) {
			//先修改密码，在重新登录
			DiscuzEditPassword(username, oldpw, newpw);
			//修改密码之后，先修改状态为未登录
			getSession().setAttribute(Constants.DISCUZ_STATE,"0");
			//重新登录系统
			userLoginDiscuz(username,newpw,cfg_discuz_open);
			
		}
		
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 修改密码
	 * 
	 * @param string
	 *            username 用户名
	 * @param string
	 *            oldpw 旧密码
	 * @param string
	 *            newpw 新密码
	 *            
	 * @return int 1 : 修改成功 0 : 没有任何修改 -1 : 旧密码不正确 -4 : email 格式有误 -5 : email
	 *         不允许注册 -6 : 该 email 已经被注册 -7 : 没有做任何修改 -8 : 受保护的用户，没有权限修改
	 */
	public String DiscuzEditPassword(String username, String oldpw, String newpw){
		
		Client e = new Client();
		String retinfo="";
		String result = e.uc_user_edit_password( username,  oldpw,  newpw);
		int $uid = Integer.parseInt(result);
		if($uid==1){
			System.out.println("修改成功");
			retinfo="修改成功";
		}else if($uid==0){
			System.out.println("没有任何修改");
			retinfo="没有任何修改";
		}else if($uid==-1){
			System.out.println("旧密码不正确");
			retinfo="旧密码不正确";
		}else if($uid==-4){
			System.out.println("email 格式有误");
			retinfo="email 格式有误";
		}else if($uid==-5){
			System.out.println("email不允许注册");
			retinfo="email不允许注册";
		}else if($uid==-6){
			System.out.println("该 email 已经被注册");
			retinfo="该 email 已经被注册";
		}else if($uid==-7){
			System.out.println("没有做任何修改");
			retinfo="没有做任何修改";
		}else if($uid==-8){
			System.out.println("受保护的用户，没有权限修改");
			retinfo="受保护的用户，没有权限修改";
		}
		
		return retinfo;
	}

	/**
	 * Discuz登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 可执行的JS串
	 * @throws java.io.IOException
	 */
	public String DiscuzLogin(String username, String password)
			throws IOException {

		Client e = new Client();
		String result = e.uc_user_login(username, password);
		String retresult = "";
		LinkedList<String> rs = XMLHelper.uc_unserialize(result);
		if (rs.size() > 0) {
			int $uid = Integer.parseInt(rs.get(0));
			String $username = rs.get(1);
			String $password = rs.get(2);
			String $email = rs.get(3);
			if ($uid > 0) {
				System.out.println("登录成功");
				System.out.println($username);
				System.out.println($password);
				System.out.println($email);
				String $ucsynlogin = e.uc_user_synlogin($uid);
				System.out.println("登录成功" + $ucsynlogin);
				retresult = $ucsynlogin;
				// 本地登陆代码
				// TODO ... ....
			} else if ($uid == -1) {
				System.out.println("用户不存在,或者被删除");
				retresult = "用户不存在,或者用户被删除";
			} else if ($uid == -2) {
				System.out.println("密码错误");
				retresult = "密码错误";
			} else {
				System.out.println("未定义");
				retresult = "未定义";
			}
		} else {
			System.out.println("登录失败");
			System.out.println(result);
			retresult = "登录失败";
		}

		return retresult;
	}

	/**
	 * Discuz退出登录
	 * 
	 * @return 可执行的JS串
	 */
	public String DiscuzLogout() {

		Client uc = new Client();

		// setcookie('Example_auth', '', -86400);
		// 生成同步退出的代码
		String $ucsynlogout = uc.uc_user_synlogout();
		System.out.println("退出成功" + $ucsynlogout);
		return $ucsynlogout;

	}

	/**
	 * Discuz同步注册
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return 注册会员的ID编号
	 */
	public String DiscuzRegister(String username, String password, String email) {

		Client uc = new Client();
		// setcookie('Example_auth', '', -86400);
		// 生成同步退出的代码
		String $returns = uc.uc_user_register(username, password, email);
		int $uid = Integer.parseInt($returns);
		String returnRegInfo = "";
		if ($uid <= 0) {
			if ($uid == -1) {
				System.out.print("用户名不合法");
				returnRegInfo = "用户名不合法";
			} else if ($uid == -2) {
				System.out.print("包含要允许注册的词语");
				returnRegInfo = "包含要允许注册的词语";
			} else if ($uid == -3) {
				System.out.print("用户名已经存在");
				returnRegInfo = "用户名已经存在";
			} else if ($uid == -4) {
				System.out.print("Email 格式有误");
				returnRegInfo = "Email 格式有误";
			} else if ($uid == -5) {
				System.out.print("Email 不允许注册");
				returnRegInfo = "Email 不允许注册";
			} else if ($uid == -6) {
				System.out.print("该 Email 已经被注册");
				returnRegInfo = "该 Email 已经被注册";
			} else {
				System.out.print("未定义");
				returnRegInfo = "未定义";
			}
		} else {
			System.out.println("OK:" + $returns);
			returnRegInfo = $returns;
		}
		return returnRegInfo;

	}

}
