/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MembercatAction.java 
 */
package com.rbt.action;

import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Membercat;
import com.rbt.service.IMembercatService;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员自定义分类表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Jul 25 11:13:52 CST 2011
 */
@Controller
public class MembercatAction extends BaseAction  implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 会员自定义分类表对象
	 */
	public Membercat membercat;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMembercatService membercatService;
	/*
	 * 会员自定义分类表信息集合
	 */
	public List membercatList;
	/*
	 * 分类名称
	 */
	public String cat_name_s;
	/*
	 * 分类类型：0：产品，1：收藏，2：商友
	 */
	public String cat_type_s;
	/*
	 * 所有分类ID
	 */
	public String admin_cat_id;
	/*
	 * 所有排序的值
	 */
	public String admin_sort_no;
	/*
	 * 批量更新数据分类ID
	 */
	public String member_cat_id;
	/*
	 * 批量更新数据排序值
	 */
	public String member_sort_no;
	/*
	 * 批量更新数据类型
	 */
	public String member_cat_type;
	/*
	 * 批量更新数据分类名称
	 */
	public String member_cat_name;
	/*
	 * 0：产品 1：收藏 2：商友
	 */
	public String membertype;
	
	//分类级别
	public String cat_level_s;
	//上级分类ID
	public String up_cat_id_s;
	
	/**
	 * 方法描述：返回新增会员自定义分类表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员自定义分类表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		membercat.setCust_id(this.session_cust_id);
		membercat.setCat_type(membertype);
		//字段验证
		super.commonValidateField(membercat);
		if(super.ifvalidatepass){
			return list();
		}
		this.membercatService.insert(membercat);
		if(ValidateUtil.isRequired(membertype)){
			membertype = "0";
		}
		return list();
	}

	/**
	 * 方法描述：修改会员自定义分类表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.membercatService.update(membercat);
		this.addActionMessage("修改自定义分类成功");
		return list();
	}

	/**
	 * 方法描述：删除会员自定义分类表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.membercat.getCat_id();
		id = id.replace(" ", "");
		this.membercatService.delete(id);
		if(ValidateUtil.isRequired(membertype)){
			membertype = "0";
		}
		return list();
	}
	/**
	 * 方法描述：删除会员自定义分类表信息
	 * @return
	 * @throws Exception
	 */
	public String deleteGoodsCat() throws Exception {
		String id = this.membercat.getCat_id();
		id = id.replace(" ", "");
		this.membercatService.delete(id);
		membertype = "3";
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		if (cat_name_s != null && !cat_name_s.equals("")) {
			pageMap.put("cat_name", cat_name_s);
		}
		if (membertype != null && !membertype.equals("")) {
			pageMap.put("cat_type", membertype);
		}else{
			pageMap.put("cat_type", "0");//默认进入产品分类
		}
		//分类级别
		if(cat_level_s!=null&&!"".equals(cat_level_s))
		{
			pageMap.put("cat_level", cat_level_s);
		}
		
		//上级分类ID
		if(up_cat_id_s!=null&&!"".equals(up_cat_id_s))
		{
			pageMap.put("up_cat_id", up_cat_id_s);
		}
		
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.membercatService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		membercatList = this.membercatService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	

	/**
	 * 方法描述：根据主键找出会员自定义分类表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(membercat.getCust_id()!=null){
			if(accessControl(membercat.getCust_id())){
				return list();
			}
		}
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.membercat.getCat_id();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateSortNo() throws Exception {
		String catid = this.admin_cat_id;
		String sortno = this.admin_sort_no;
		catid = catid.replace(" ", "");
		sortno = sortno.replace(" ", "");
		String catidStr[] = catid.split(",");
		String sortnoStr[] = sortno.split(",");
		List membercatsList = new ArrayList();
		if (catidStr.length > 0) {
			for (int i = 0; i < catidStr.length; i++) {
				Map membercatMap = new HashMap();
				membercatMap.put("cat_id", catidStr[i]);
				membercatMap.put("sort_no", sortnoStr[i]);
				membercatMap.put("cust_id", this.session_cust_id);
				membercatsList.add(membercatMap);
			}
		}
		this.membercatService.updateSortNo(membercatsList);
		this.addActionMessage("自定义分类批量排序成功");
		return list();
	}

	/**
	 * 方法描述：批量修改数据信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAllMembercat() throws Exception {
		String catid = this.member_cat_id;
		String sortno = this.member_sort_no;
		String catname = this.member_cat_name;
		catid = catid.replace(" ", "");
		sortno = sortno.replace(" ", "");
		catname = catname.replace(" ", "");
		String catidStr[] = catid.split(",");
		String sortnoStr[] = sortno.split(",");
		String catnameStr[] = catname.split(",");
		List membercatsList = new ArrayList();
		if (catidStr.length > 0) {
			for (int i = 0; i < catidStr.length; i++) {
				Map membercatMap = new HashMap();
				membercatMap.put("cat_id", catidStr[i]);
				membercatMap.put("sort_no", sortnoStr[i]);
				membercatMap.put("cat_name", catnameStr[i]);
				membercatMap.put("cust_id", this.session_cust_id);
				membercatsList.add(membercatMap);
			}
		}
		this.membercatService.updateAllMemberCat(membercatsList);
		this.addActionMessage("自定义分类批量修改成功");
		return list();
	}

	
	/**
	 * 方法描述：异步方式实现地区的无限级级联
	 * 
	 * @return
	 * @throws Exception
	 */
	public String membercatslist() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		// 判断传进来的上一级ID是否为空,为空时把最上级的ID传进去
		if (request.getParameter("up_cat_id") != null) {
			String up_cat_id = request.getParameter("up_cat_id");
			pageMap.put("up_cat_id", up_cat_id);
		} else {
			pageMap.put("up_cat_id", cfg_topareaid);
		}
		pageMap.put("cat_type", "3");
		pageMap.put("cust_id", this.session_cust_id);
		List list = this.membercatService.getList(pageMap);
		StringBuffer sb = new StringBuffer();
		Map map = new HashMap();
		int cat_level =1;
		// 判断查询的列表是否为空
		if (!ValidateUtil.isRequired(request.getParameter("count"))) {
			cat_level=Integer.parseInt(request.getParameter("count"));
		}
		// html标签与代码结合流的输出
		sb.append("<select id='mcatlevel" + cat_level + "' name='membercat_attr' class='select' onchange='onlyshowmembercat(this.value," + cat_level + ")'>");
		sb.append("<option value='0'>请选择</option>");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String cat_id = "", cat_name = "", level = "";
				if (map.get("cat_id") != null) {
					cat_id = map.get("cat_id").toString();
				}
				if (map.get("cat_name") != null) {
					cat_name = map.get("cat_name").toString();
				}
				if (map.get("cat_level") != null) {
					level = map.get("cat_level").toString();
				}
				sb.append("<option value='" + cat_id + "'>");
				sb.append(cat_name);
				sb.append("</option>");
			}
		}
		sb.append("</select>");
		PrintWriter out = response.getWriter();
		if (list != null && list.size() > 0) {
			out.write("" + sb.toString());
		} else {
			out.write("");
		}
		return Action.NONE;
	}
	
	/**
	 * @return the MembercatList
	 */
	public List getMembercatList() {
		return membercatList;
	}

	/**
	 * @param membercatList
	 *  the MembercatList to set
	 */
	public void setMembercatList(List membercatList) {
		this.membercatList = membercatList;
	}

	public String getCat_name_s() {
		return cat_name_s;
	}

	public void setCat_name_s(String cat_name_s) {
		this.cat_name_s = cat_name_s;
	}

	public String getCat_type_s() {
		return cat_type_s;
	}

	public void setCat_type_s(String cat_type_s) {
		this.cat_type_s = cat_type_s;
	}

	public String getAdmin_cat_id() {
		return admin_cat_id;
	}

	public void setAdmin_cat_id(String admin_cat_id) {
		this.admin_cat_id = admin_cat_id;
	}

	public String getAdmin_sort_no() {
		return admin_sort_no;
	}

	public void setAdmin_sort_no(String admin_sort_no) {
		this.admin_sort_no = admin_sort_no;
	}

	public String getMember_cat_id() {
		return member_cat_id;
	}

	public void setMember_cat_id(String member_cat_id) {
		this.member_cat_id = member_cat_id;
	}

	public String getMember_sort_no() {
		return member_sort_no;
	}

	public void setMember_sort_no(String member_sort_no) {
		this.member_sort_no = member_sort_no;
	}

	public String getMember_cat_type() {
		return member_cat_type;
	}

	public void setMember_cat_type(String member_cat_type) {
		this.member_cat_type = member_cat_type;
	}

	public String getMember_cat_name() {
		return member_cat_name;
	}

	public void setMember_cat_name(String member_cat_name) {
		this.member_cat_name = member_cat_name;
	}

	public String getMembertype() {
		return membertype;
	}

	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}
	/**
	 * @return the membercat
	 */
	public Membercat getMembercat() {
		return membercat;
	}

	/**
	 * @param Membercat
	 *            the membercat to set
	 */
	public void setMembercat(Membercat membercat) {
		this.membercat = membercat;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(membercat == null){
			membercat = new Membercat();
		}
		String id = membercat.getCat_id();
		if(!ValidateUtil.isDigital(id)){
			membercat = this.membercatService.get(id);
		}
	}

}
