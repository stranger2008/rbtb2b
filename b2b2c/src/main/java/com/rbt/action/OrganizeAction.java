/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: OrganizeAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Organize;
import com.rbt.service.IOrganizeService;

/**
 * @function 功能 记录组织部门action类
 * @author 创建人 林俊钦
 * @date 创建日期 Mon Nov 07 13:37:36 CST 2011
 */
@Controller
public class OrganizeAction extends BaseAction implements Preparable {
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录组织部门对象
	 */
	public Organize organize;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IOrganizeService organizeService;
	/*
	 * 记录组织部门信息集合
	 */
	public List organizeList;
	
	
	/**
	 * 方法描述：返回新增记录组织部门页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录组织部门
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		//生成随机数
		String org_id = RandomStrUtil.getNumberRand();
		String up_org_id=organize.getUp_org_id();
		organize.setOrg_id(org_id);
		//验证所属地区是否有选择,验证第一级时是否未选择
		if (hidden_area_value.indexOf("0")>-1) {
			organize.setArea_attr(Constants.UP_ORG_ID);
		}else{
			if(area_attr!=null){
				organize.setArea_attr(area_attr.replace(",0",""));//替换掉,0
			}			
		}		
		//'0：业务部门 1：系统部门'
		organize.setSys_org("0");		
		//字段验证
		super.commonValidateField(organize);
		if(super.ifvalidatepass){
			return add();
		}	
		this.organizeService.insert(organize);
		this.addActionMessage("新增组织部门信息成功");
		//保留部门等级
		String org_lel=organize.getOrg_level();
		this.organize=null;
		this.hidden_area_value=null;
		//重新赋值
		organize=new Organize();
		organize.setOrg_level(org_lel);
		organize.setUp_org_id(up_org_id);
		return INPUT;
	}

	/**
	 * 方法描述：修改记录组织部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 用于所属地区的回选开始
		loadArea();
		//验证所属地区是否有选择,验证第一级时是否未选择
		if (hidden_area_value.indexOf("0")>-1) {
			organize.setArea_attr(Constants.UP_ORG_ID);
		}else{
			if(area_attr!=null){
				organize.setArea_attr(area_attr.replace(",0",""));//替换掉,0
			}
		}
		//字段验证
		super.commonValidateField(organize);
		if(super.ifvalidatepass){
			return view();
		}	
		this.organizeService.update(organize);
		this.addActionMessage("修改组织部门信息成功");		
		return list();
	}
	/**
	 * 方法描述：删除记录组织部门信息
	 * @return
	 * @throws Exception
	 */
	public String org_delete() throws Exception {
		//定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		//获取前台传过来的ID值
		if(request.getParameter("id")!=null){
			String id = request.getParameter("id");
			//进入循环删除ID
			Recursive(id);			
		}
		this.addActionMessage("删除组织部门信息成功");
		return list();
	}
	/**
	 * @MethodDescribe 方法描述 创建一个递归方法用于批量删除
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 15, 2011 4:33:29 PM
	 */
	public boolean Recursive(String id) {
		String org_id = "";
		Map map = new HashMap();
		map.put("up_org_id", id);
		//删除传进来的ID行
		this.organizeService.delete(id);
		//通过对传进来的ID进行查询
		List orgaList = this.organizeService.getList(map);
		//若存在有子ID
		if (orgaList.size() > 0) {
			for (int i = 0; i < orgaList.size(); i++) {
				Map upmap = new HashMap();
				upmap = (HashMap) orgaList.get(i);
				//如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除 
				if (upmap.get("org_id") != null && !upmap.get("org_id").equals("")) {
					org_id = upmap.get("org_id").toString();
					//进入递归循环
					Recursive(org_id);
				}
			}
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();	
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.organizeService.getCount(pageMap);		
		//分页插件
		pageMap = super.pageTool(count,pageMap);		
		organizeList = this.organizeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * @Method Description : 获取组织部门的列表
	 * @author : 林俊钦
	 * @date : Nov 7, 2011 1:49:27 PM
	 */
	public void getList() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap=new HashMap();
		//判断传进来的上一级ID是否为空,为空时把最上级的ID传进去
		String up_org_id="";
		if(request.getParameter("up_org_id")!=null){
			up_org_id = request.getParameter("up_org_id");
			pageMap.put("up_org_id", up_org_id);
		}else{
			pageMap.put("up_org_id", Constants.UP_ORG_ID);
		}
		List list = this.organizeService.getList(pageMap);	
		StringBuffer sb=new StringBuffer();
		Map map=new HashMap();
        String orga_level="";
        //判断查询的列表是否为空
		if(list!=null&&list.size()>0){
              map=(HashMap)list.get(0);
              if (map.get("org_level") != null) {
            	  orga_level = map.get("org_level").toString();
  			  }
              //地区DIV容器
              sb.append("<div class='areadiv' id=arealevel"+orga_level+">");
              sb.append("<h3 class='areatitle'>");
      	      sb.append("<div id=menutitle" + orga_level+ " class='spantitle'>一级部门</div></h3>" +
      	      		"<input id=menuvalue" + orga_level+ " type='hidden' value='1111111111'>");
              sb.append("<div class='areacontent'><ul>");
              for (int i = 0; i < list.size(); i++){
	  				map = (HashMap) list.get(i);
	  				@SuppressWarnings("unused")
	  				String orga_id = "", orga_name = "", level = "",sys_org="";
	  				if (map.get("org_id") != null) {
	  					orga_id = map.get("org_id").toString();
	  				}
	  				if (map.get("org_name") != null) {
	  					orga_name = map.get("org_name").toString();
	  				}
	  				if (map.get("org_level") != null) {
	  					level = map.get("org_level").toString();
	  				}
	  				if (map.get("sys_org") != null) {
	  					sys_org = map.get("sys_org").toString();
	  				}
	  				sb.append("<li>");
	      			sb.append("<span id='areali"+orga_id+"' class='areaspan' onclick='showorga(\""+orga_id+"\","+orga_level+");'>"+orga_name+"</span>");
	      			sb.append("<img class='edit' title='编辑部门' src='/include/images/bj.gif' onclick='updateorga(\""+orga_id+"\");'/>");
	      			if(sys_org.equals("0")){
	      			    sb.append("<img class='delete' title='删除部门' src='/include/images/admin/delete.png' onclick='deleteorga(\""+orga_id+"\","+orga_level+");'/>");
	      			}
	  			    sb.append("</li>");  			    
  			  }
              sb.append("</ul></div>");
              if(up_org_id.equals("")){
            	  up_org_id=Constants.UP_ORG_ID;
              }
      		  sb.append("<div class='areaadd'><img class='add' title='新增部门' onclick='insertorga(\""+up_org_id+"\","+ orga_level+ ")' src='/include/images/add.png'/>");		
    		  sb.append("</div>");
              sb.append("</div>");
		}	
		if(list!=null&&list.size()>0){
			out.write(sb.toString());
		}else{
			out.write("");
		}		
	}
	
	/**
	 * @MethodDescribe 方法描述    实现异步方式的所属部门的级联加载
	 * @author  创建人  林俊钦
	 * @date  创建日期  Jul 26, 2011 9:16:54 AM
	 */
	public String viewlist() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		//获取上一级的分类ID,所属模块类型
		String up_org_id = "";
		if(request.getParameter("cid")!=null){
			up_org_id=request.getParameter("cid").toString();
		}
		//判断从页面传过来的ID是否为空，如果为空则把up_cat常量赋给上一级分类ID
		if (up_org_id == null || up_org_id.equals("")) {
			up_org_id = Constants.UP_ORG_ID;
		}
	    //去掉首尾的空格	
		pageMap.put("up_org_id", up_org_id.trim());
		List list = this.organizeService.getList(pageMap);
		Map map = new HashMap();
		//定义级数
		String org_level = "";
		StringBuffer sb = new StringBuffer();
		//判断列表是否为空，如果为空则把等级赋给定义的cat_level
		if (list != null && list.size() > 0) {
			map = (HashMap) list.get(0);
			if (map.get("org_level") != null) {
				org_level = map.get("org_level").toString();
			}

		}
		//重组HTML代码字符串
		sb.append("<select id='level" + org_level + "' name='org_attr' class='select' onchange='onlyshoworg(this.value,"+org_level+")'>");
	  	sb.append("<option value='0'>请选择</option>");	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String  org_name = "",org_id="";			
				if (map.get("org_id") != null) {
					org_id = map.get("org_id").toString();
				}
				sb.append("<option value="+org_id+">");
				if (map.get("org_name") != null) {
					org_name = map.get("org_name").toString();
				}		
				sb.append(org_name);
				sb.append("</option>");
			}
		}
		sb.append("</select >");
		PrintWriter out = response.getWriter();
		//判断列表的数据是否为空，如果是则输出空，否则则输出字符串
		if (list!=null && list.size() > 0) {
			out.write("" + sb.toString());
		} else {
			out.write("");
		}
		return Action.NONE;
	}
	
	
	
	/**
	 * 方法描述：根据主键找出记录组织部门信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(organize!=null&&organize.getArea_attr()!=null){
			String area_Str=organize.getArea_attr();
			if(area_Str.indexOf(Constants.UP_ORG_ID)>-1){
				backArea("");
			}else{
				// 找出地区字段的存入隐藏值中
				backArea(organize.getArea_attr()); 
			}			
		}		
		return goUrl(VIEW);
	}
	/**
	 * @return the OrganizeList
	 */
	public List getOrganizeList() {
		return organizeList;
	}
	/**
	 * @param organizeList
	 *  the OrganizeList to set
	 */
	public void setOrganizeList(List organizeList) {
		this.organizeList = organizeList;
	}

	/**
	 * @return the organize
	 */
	public Organize getOrganize() {
		return organize;
	}
	/**
	 * @param Organize
	 *            the organize to set
	 */
	public void setOrganize(Organize organize) {
		this.organize = organize;
	}
	
	
	public void prepare() throws Exception { super.saveRequestParameter();
	if(organize == null){
		organize = new Organize();
	}
	String id = organize.getOrg_id();
	if(!ValidateUtil.isRequired(id)){
		organize = this.organizeService.get(id);
	}
}
	
}

