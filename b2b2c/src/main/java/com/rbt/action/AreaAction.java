/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: AreaAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.model.Area;
import com.rbt.service.IAreaService;
import com.rbt.service.ICommparaService;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;

/**
 * @function 功能 地区管理action类
 * @author 创建人 胡惜坤   
 * @date 创建日期 Jun 28, 2011
 */   
@Controller
public class AreaAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = -573203663791615674L;
	/*
	 * 地区对象 
	 */
	public Area area;
	/*
	 * 业务层接口
	 */
	
	@Autowired
	public IAreaService areaService;
	@Autowired
	public ICommparaService commparaService;

	/*
	 * 地区信息集合
	 */
	public List areaList;
	/*
	 * 排序ID集合
	 */
	public String admin_Sort_id;
	/*
	 * 定义上一级地区的ID
	 */
	public String area_sort_id;
	/*
	 * 定义地区排序
	 */
	public String area_sort_Num;
	/*
	 * 定义地区级别ID
	 */
	public String lel_level;
	
	//大区域划分
	public List areahave_List;

	/**
	 * 方法描述：返回新增地区页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map  areamap = new HashMap();
		areamap.put("para_code","area_type");
		areahave_List=commparaService.getList(areamap);		
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增地区
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		area.setArea_id( RandomStrUtil.getNumberRand());
		
		//area.area_name
		if(area.getArea_name()!=null && !"".equals(area.getArea_name())){
			HashMap map =new HashMap();
			map.put("area_name", area.getArea_name());
			areaList = areaService.getList(map);
			if(areaList!=null && areaList.size()>0){
				this.addFieldError("area.area_name", "地区已存在");
			}
		}
		//字段验证
		super.commonValidateField(area);
		if(super.ifvalidatepass){
			return add();
		}
		this.areaService.insert(area);
		this.addActionMessage("新增地区成功");
		return INPUT;
	}

	/**
	 * 方法描述：修改地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id =area.getArea_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if (ValidateUtil.isRequired(id)) {
			return list();
		}
		if (ValidateUtil.isRequired(area.getArea_id())) {
			id = this.area.getArea_id();
		}
		//字段验证
		super.commonValidateField(area);
		if(super.ifvalidatepass){
			return view();
		}
		this.areaService.update(area);
		this.addActionMessage("修改地区成功");
		return list();
	}


	/**
	 * 方法描述：删除地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		// 定义request对象
		HttpServletRequest request = getRequest();
		request.setCharacterEncoding("UTF-8");
		// 获取前台传过来的ID值
		if (request.getParameter("id") != null) {
			String id = request.getParameter("id");
			// 进入循环删除ID
			Recursive(id);
		}
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map areaMap= new HashMap();
		areaList = this.areaService.getList(areaMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：异步方式实现地区的无限级级联
	 * 
	 * @return
	 * @throws Exception
	 */
	public String arealist() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		// 判断传进来的上一级ID是否为空,为空时把最上级的ID传进去
		if (request.getParameter("up_area_id") != null) {
			String up_area_id = request.getParameter("up_area_id");
			pageMap.put("up_area_id", up_area_id);
		} else {
			pageMap.put("up_area_id", cfg_topareaid);
		}
		List list = this.areaService.getList(pageMap);
		StringBuffer sb = new StringBuffer();
		Map map = new HashMap();
		int area_level =1;
		// 判断查询的列表是否为空
		if (!ValidateUtil.isRequired(request.getParameter("count"))) {
			area_level=Integer.parseInt(request.getParameter("count"));
		}
		// html标签与代码结合流的输出
		sb.append("<select id='arealevel" + area_level + "' name='area_attr' class='select' onchange='onlyshowarea(this," + area_level + ")'>");
		sb.append("<option value='0'>请选择</option>");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String area_id = "", area_name = "", level = "";
				if (map.get("area_id") != null) {
					area_id = map.get("area_id").toString();
				}
				if (map.get("area_name") != null) {
					area_name = map.get("area_name").toString();
				}
				if (map.get("area_level") != null) {
					level = map.get("area_level").toString();
				}
				sb.append("<option value='" + area_id + "'>");
				sb.append(area_name);
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
	 * @Method Description : 获取地区的列表
	 * @author : 林俊钦
	 * @date : Nov 7, 2011 1:49:27 PM
	 */
	public void getList() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response =getResponse();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Map pageMap = new HashMap();
		// 判断传进来的上一级ID是否为空,为空时把最上级的ID传进去
		String up_area_id = "";
		if (request.getParameter("up_area_id") != null) {
			up_area_id = request.getParameter("up_area_id");
			pageMap.put("up_area_id", up_area_id);
		} else {
			pageMap.put("up_area_id", cfg_topareaid);
		}
		List list = this.areaService.getList(pageMap);
		StringBuffer sb = new StringBuffer();
		Map map = new HashMap();
		String area_level = "";
		// 判断查询的列表是否为空
		if (list != null && list.size() > 0) {
			map = (HashMap) list.get(0);
			if (map.get("area_level") != null) {
				area_level = map.get("area_level").toString();
			}
			// 地区DIV容器
			sb.append("<div class='areadiv' id=arealevel" + area_level + ">");
			sb.append("<h3 class='areatitle'>");
			sb.append("<div id=menutitle" + area_level + " class='spantitle'>一级地区</div></h3>" + "<input id=menuvalue" + area_level
					+ " type='hidden' value='"+cfg_topareaid+"'>");
			sb.append("<div class='areacontent'><ul>");
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String area_id = "", area_name = "", level = "",sort_no="";
				if (map.get("area_id") != null) {
					area_id = map.get("area_id").toString();
				}
				if (map.get("area_name") != null) {
					area_name = map.get("area_name").toString();
				}
				if (map.get("area_level") != null) {
					level = map.get("area_level").toString();
				}
				if (map.get("sort_no") != null) {
					sort_no = map.get("sort_no").toString();
				}
				sb.append("<li id=li"+area_id+">");
				sb.append("<input type='checkbox' style='display:none;' value="+ area_id + ">");
				sb.append("<input type='text' name='sort_no'   onkeyup='checkNum(this);'  class='textsort' value="+ sort_no + ">");
				sb.append("<span id='areali" + area_id + "' class='areaspan' onclick='showarea(\"" + area_id + "\"," + level + ");'>" + area_name
						+ "</span>");
				sb.append("<img class='edit' title='编辑地区' src='/include/images/bj.gif' onclick='updatearea(\"" + area_id + "\"," + area_level+ ");'/>");
				sb.append("<img class='delete' title='删除地区' src='/include/images/admin/delete.png' onclick='deletearea(\"" + area_id + "\"," + area_level
						+ ");'/>");
				sb.append("</li>");
			}
			sb.append("</ul></div>");
			if (up_area_id.equals("")) {
				up_area_id = cfg_topareaid;
			}
			sb.append("<div class='areaadd'><img class='add' titile='新增地区' onclick='insertarea(\"" + up_area_id + "\"," + area_level+ ")' src='/include/images/add.png'/>");
			sb.append("<img class='addclass' title='排序' onclick=\"updateSort('/admin_Area_updateSort.action');\" src='/include/images/sort.png'/>");		
			sb.append("</div>");
			sb.append("</div>");
		}
		if (list != null && list.size() > 0) {
			out.write(sb.toString());
		} else {
			out.write("");
		}
	}

	/**
	 * @Method Description : 地区的批量排序
	 * @author : 林俊钦
	 * @date : Dec 7, 2011 5:22:19 PM
	 */
	public String updateSort() throws Exception{
		if(ValidateUtil.isRequired(this.area_sort_id)){
			return list();
		}
     	String rule_id = this.area_sort_id;
		String rule_num = this.area_sort_Num;		
		rule_id=rule_id.replace(" ","");
		String ruleStr[]=rule_id.split(",");
		String ruleNumStr[]=rule_num.split(",");
		List ruleList=new ArrayList();
		if(ruleStr.length>0){			
			for(int i=0;i<ruleStr.length;i++){
				Map ruleMap = new HashMap();
				ruleMap.put("area_id", ruleStr[i]);
				//如果文本框为空则返回
				if(ValidateUtil.isRequired(ruleNumStr[i])){
					return list();
				}
				ruleMap.put("sort_no", ruleNumStr[i].trim());
				ruleList.add(ruleMap);
			}
		}
		System.out.println(ruleList+"============");
		areaService.updateAreaSortNo(ruleList);
		this.addActionMessage("地区排序成功");
		return list();
		
	}
	
	
	/**
	 * @MethodDescribe 方法描述 创建一个递归方法用于批量删除
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 15, 2011 4:33:29 PM
	 */
	public boolean Recursive(String id) {
		String area_id = "";
		Map map = new HashMap();
		map.put("up_area_id", id);
		// 删除传进来的ID行
		areaService.delete(id);
		// 通过对传进来的ID进行查询
		areaList = areaService.getList(map);
		// 若存在有子ID
		if (areaList.size() > 0) {
			Map upmap  = new HashMap();
			for (int i = 0; i < areaList.size(); i++) {
				upmap  = (HashMap) areaList.get(i);
				// 如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除
				if (upmap.get("area_id") != null && !upmap.get("area_id").equals("")) {
					area_id = upmap.get("area_id").toString();
					// 进入递归循环
					Recursive(area_id);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法描述：根据主键找出地区信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		Map  amap = new HashMap();
		amap.put("para_code","area_type");
		areahave_List=commparaService.getList(amap);	
		lel_level=area.getArea_level();
		return goUrl(VIEW);
	}


	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (area == null) {
			area = new Area();
		}
		String id = this.area.getArea_id();
		if (!ValidateUtil.isRequired(id)) {
			area = this.areaService.get(id);
		}
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
