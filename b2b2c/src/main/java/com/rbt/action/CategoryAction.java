/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CategoryAction.java 
 */
package com.rbt.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.model.Category;
import com.rbt.model.Sysmodule;
import com.rbt.service.IAttrvalueService;
import com.rbt.service.ICategoryService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;
import com.rbt.common.Constants;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.common.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 分类信息表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Tue Jul 12 13:04:58 CST 2011
 */
@Controller
public class CategoryAction extends BaseAction implements Preparable{

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -2244333279321571040L;
	/*
	 * 分类信息表对象
	 */
	public Category category;
	/*
	 * 业务层接口
	 */
	@Autowired
	public ICategoryService categoryService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;

	
	/*
	 * 分类信息表信息集合
	 */
	public List categoryList;
	public List commparaList;
	/*
	 * 定义上级分类的名称
	 */
	public String catename;
	/*
	 * 所属模块字段定义
	 */
	public String modules;
    /*
	 * 所属模块字段的代号,用来回选
	 */
	public String type;
    /*
	 * 定义所属模块的类型
	 */
    private String para_code="module_type";
    /*
	 * 获取上一级分类的名称
	 */
    public String up_cat_name;
    public String cate_sort_id;
    public String cate_sort_Num;
    public String cate_attr_str;
    public String cat_select_moudle;
    /*
	 * 获取参数列表
	 */
    public void getCommparaLists()
    {
    	Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
		commparaList = this.sysmoduleService.getList(pageMap);
    }
	/**
	 * 方法描述：返回新增分类信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 设置页面所需的返回值，用来对返回列表的使用
		getCommparaLists();		
		// 设置标题的所属模块名称
		if(type!=null){
			Sysmodule sysmodule=this.sysmoduleService.get(type);
			if(sysmodule!=null){
				modules = sysmodule.getModule_name();
			}
		}				
		this.category.setModule_type(type);		
		//寻找上一级ID转换成名称
		if (!ValidateUtil.isRequired(category.getUp_cat_id())) {
			String cat_id = this.category.getUp_cat_id().toString();
			if (cat_id.equals(cfg_topcatid)) {
				// 设置每个选框title的名称
				up_cat_name = modules + "分类";
			} else {
				// 获取所属分类的对象
				Category cate_class = this.categoryService.get(cat_id);
				if (cate_class != null) {
					up_cat_name = cate_class.getCat_name();					
				}
			}
		}
		//cat_attr用来分类的回选，这时加上cat_attr需回选加上顶级ID cfg_topcatid
		cat_attr=cfg_topcatid+","+cat_attr;
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增分类信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		//生成10位的随机数ID
		String cat_id = RandomStrUtil.getNumberRand();
		this.category.setCat_id(cat_id);		
		Map<String ,String> map=new HashMap<String ,String>();
		String en_name=category.getEn_name();
		map.put("en_name",en_name);
		map.put("module_type", this.category.getModule_type());
		List list=this.categoryService.getList(map);
		if(list!=null&&list.size()>0){
			this.addFieldError("category.en_name", "分类拼音名已存在，请重新选择输入");
		}
		//字段验证
		super.commonValidateField(category);
		if(super.ifvalidatepass){
			return add();
		}	
		//插入数据库
		this.categoryService.insert(category);
		this.addActionMessage("新增分类信息成功");
		return add();
	}
	

	
	
	/**
	 * 方法描述：修改分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = category.getCat_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isRequired(id)){
			return list();
		}
		Map map=new HashMap();
		String en_name=category.getEn_name();
		map.put("en_name",en_name);
        List list=this.categoryService.getList(map);
		if(list!=null&&list.size()>0){
			Map listMap=(HashMap)list.get(0);
			if(listMap!=null&&listMap.get("cat_id")!=null){
				if(!id.equals(listMap.get("cat_id").toString())){
					this.addFieldError("category.en_name", "分类拼音名已存在，请重新选择输入");
				}
			}
		}
		//字段验证
		super.commonValidateField(category);
		if(super.ifvalidatepass){
			return view();
		}	
		//更新数据库
		this.categoryService.update(category);
		this.addActionMessage("修改分类信息成功");
		return list();
	}
	/**
	 * 方法描述：删除分类信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String catdelete() throws Exception {
		//定义request对象
		HttpServletRequest request =getRequest();
		request.setCharacterEncoding("UTF-8");
		//获取前台传过来的ID值
		if(request.getParameter("ids")!=null){
			String ids = request.getParameter("ids");
			String[] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				if (Recursive(id[i])) {
					this.addActionMessage("删除分类信息成功");
				} else {
					this.addActionMessage("删除分类信息失败");
				}
			}
		}
		return list();
	}

	/**
	 * @MethodDescribe 方法描述 创建一个递归方法用于批量删除
	 * @author 创建人 林俊钦
	 * @date 创建日期 Jul 15, 2011 4:33:29 PM
	 */
	public boolean Recursive(String id) {
		String cat_id = "";
		Map map = new HashMap();
		map.put("up_cat_id", id);
		//删除传进来的ID行
		categoryService.delete(id);
		//通过对传进来的ID进行查询
		categoryList = categoryService.getList(map);
		//若存在有子ID
		if (categoryList!=null&&categoryList.size() > 0) {
			for (int i = 0; i < categoryList.size(); i++) {
				Map upmap = (HashMap) categoryList.get(i);
				//如果通过上一级获取到本级的ID不为空，则进入递归继续查找并删除 
				if (upmap!=null&&upmap.get("cat_id") != null&& !upmap.get("cat_id").equals("")) {
					cat_id = upmap.get("cat_id").toString();
					//进入递归循环
					Recursive(cat_id);
				}
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {		
		//清空对象
		this.category=null;
		categoryList = this.categoryService.getList(new HashMap());	
		//通过传相应的参数值找出相应的系统参数类型
		getCommparaLists();
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：根据主键找出分类信息表信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//获取所属模块中的类型值
		type = category.getModule_type();
		getCommparaLists();
		if(type!=null){
			Sysmodule sysmodule=this.sysmoduleService.get(type);
			if(sysmodule!=null){
				modules = sysmodule.getModule_name();
			}
		}				
		//获取上一级分类ID
		String cat_id=category.getCat_id();
		if(cat_id.equals(cfg_topcatid)){
			up_cat_name=modules+"分类";
		}else{
			Category cate_class=this.categoryService.get(cat_id);			
			if(cate_class!=null){
				String up_cat_id=cate_class.getUp_cat_id();
				this.category.setUp_cat_id(up_cat_id);//上一级ID
				this.category.setCat_level(cate_class.getCat_level());//本级的等级
				Category up_cate_class=this.categoryService.get(up_cat_id);
				if(up_cate_class!=null){
					up_cat_name=up_cate_class.getCat_name();//取出上一级分类ID的名称
				}else{
					up_cat_name=modules+"分类"; //否则是话为顶级ID         
				}
			}
		}
		return goUrl(VIEW);		
	}

	/**
	 * @MethodDescribe 方法描述 通过异步传输读取数据供应分类的管理
	 * @author 创建人 林俊钦
	 * @throws java.io.IOException
	 * @date 创建日期 Jul 13, 2011 10:03:31 AM
	 */
	public String getlist() throws IOException {
		//定义所属的模块类型,上一级分类ID
		String M_type="",up_cat_id = "";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取上一级的分类ID，如果为空，则赋于常量
		if(request.getParameter("cid")!=null){
			up_cat_id = request.getParameter("cid");		
		}
		if (up_cat_id == null || up_cat_id.equals("")) {
			up_cat_id = cfg_topcatid;
		}
		Map pageMap = new HashMap();
		pageMap.put("up_cat_id", up_cat_id);
		//获取所属模块的参数类型
		if(this.category.getModule_type()!=null&&!this.category.getModule_type().equals("")){
			M_type=this.category.getModule_type();
			pageMap.put("module_type",M_type);
		}
		//获取对应模块的是否支持分类属性的状态
		Sysmodule sysmodule=this.sysmoduleService.get(M_type);
		String is_catattr="";
		if(sysmodule!=null){
			is_catattr=sysmodule.getIs_catattr();
		}		
		//通过条件查找出相应的的列表数据
		List list = categoryService.getList(pageMap);
		Map map = new HashMap();
		String cat_level = "";
		StringBuffer sb = new StringBuffer();
		//判断列表是否为空，如果为空则把等级赋给定义的cat_level
		if (list != null && list.size() > 0) {
			map = (HashMap) list.get(0);
			if (map!=null&&map.get("cat_level") != null) {
				cat_level = map.get("cat_level").toString();
			}
		}
		//重组HTML代码字符串
		sb.append("<div id=level" + cat_level + " class='parentdiv'>");		
		sb.append("<h3 >");
		sb.append("<div id=menutitle" + cat_level+ " class='spantitle'>一级分类</div></h3><input id=menuvalue" + cat_level+ " type='hidden' value='1111111111'>");
		sb.append("<div class=\"catelist\">");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = (HashMap) list.get(i);
				String cat_id = "", cat_name = "", level = "",sort_no="";
				if (map.get("cat_id") != null) {
					cat_id = map.get("cat_id").toString();
				}
				if (map.get("cat_name") != null) {
					cat_name = map.get("cat_name").toString();
				}
				if (map.get("cat_level") != null) {
					level = map.get("cat_level").toString();
				}
				if (map.get("sort_no") != null) {
					sort_no = map.get("sort_no").toString();
				}
				sb.append("<li class='cateli' id=li" + cat_id + ">");
				sb.append("<input type='checkbox' style='display:none;' value="+ cat_id + ">");
				sb.append("<input type='text' name='sort_no'  onkeyup='checkNum(this)'  class='textsort' value="+ sort_no + ">");
				sb.append("<div id=cate"+ cat_id+ " class='cat_name' name='cat_name' onclick='showcate("+ cat_id + "," + level +",\""+M_type+"\");'>" + cat_name+ "</div>");
				sb.append("<span class='spanoper'>");	
				//0:表示支持分类属性
				if(is_catattr.equals("0")){
					sb.append("<img id=\"imgattr\" class='attr' title='查看所有属性' src='/include/images/admin/cateattr.png' style='width:16px;height:16px;' onclick='attr("+ cat_id + "," + cat_level + ")'/>");
				}
				sb.append("<img class='del' title='删除' src='/include/images/admin/delete.png' onclick='del("+ cat_id + ")'/>");
				sb.append("<img class='look' title='修改' src='/include/images/bj.gif' onclick='look("+ cat_id + "," + cat_level + ")'/>");
				sb.append("</span>");
				sb.append("</li>");
			}
		}
		sb.append("</div>");
		sb.append("<h3><img class='addclass' title='添加当前列表的子分类' onclick='addcate(" + cat_level+ ")' src='/include/images/add.png'/>");
		sb.append("<img class='addclass' title='排序' onclick=\"updateSort('/admin_Category_updateSort.action');\" src='/include/images/sort.png'/>");		
		sb.append("</h3>");
		sb.append("</div>");
		PrintWriter out = response.getWriter();
		//如果列表存在数据则输出，否则则输出空
		if (list!=null && list.size() > 0) {
			out.write("" + sb.toString());
		} else {
			out.write("");
		}
		return Action.NONE;
	}
	
	/**
	 * @Method Description : 分类批量排序
	 * @author : 林俊钦
	 * @date : Nov 16, 2011 4:11:10 PM
	 */
	public String updateSort() throws Exception{
     	String rule_id = this.cate_sort_id;
		String rule_num = this.cate_sort_Num;	
		rule_id=rule_id.replace(" ","");
		String ruleStr[]=rule_id.split(",");
		String ruleNumStr[]=rule_num.split(",");
		List ruleList=new ArrayList();
		if(ruleStr.length>0){
			for(int i=0;i<ruleStr.length;i++){
				Map ruleMap = new HashMap();
				ruleMap.put("cat_id", ruleStr[i]);
				//如果文本框为空则返回
				if(ValidateUtil.isRequired(ruleNumStr[i])){
					return list();
				}
				ruleMap.put("sort_no", ruleNumStr[i]);
				ruleList.add(ruleMap);
			}
		}
		this.categoryService.updateSort(ruleList);
		this.addActionMessage("模块排序成功");
		return list();		
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 11, 2012 3:56:26 PM
	 * @Method Description : 获取对应分类的提示信息
	 */
	public String getTip() throws IOException {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String id=request.getParameter("cid");
		Category cg=this.categoryService.get(id);
		String outStr="";
		if(cg!=null&&cg.getCat_simple()!=null){
			outStr=cg.getCat_simple().toString();
		}
		PrintWriter out = response.getWriter();
		out.write(outStr);
		return Action.NONE;
	}
	
	
	/**
	 * @MethodDescribe 方法描述    实现异步方式的所属分类的级联加载
	 * @author  创建人  林俊钦
	 * @date  创建日期  Jul 26, 2011 9:16:54 AM
	 */
	public String viewlist() throws IOException {
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("member_add", "0");
		}
		// 找出当前登录会员是企业还是个人
		String mem_type = super.getSessionFieldValue(Constants.MEM_TYPE);
		if(mem_type.equals(Constants.COMPANY_MEM_TYPE)){
			pageMap.put("mem_type", "0,2");
		}else if(mem_type.equals(Constants.PERSONAL_MEM_TYPE)){
			pageMap.put("mem_type", "1,2");
		}
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获取上一级的分类ID,所属模块类型
		String up_cat_id = "",M_type="";
		if(request.getParameter("cid")!=null){
			up_cat_id=request.getParameter("cid").toString();
		}
		//获取分类的所属模块
		if(request.getParameter("type")!=null){
			M_type= request.getParameter("type");
		}
		//判断从页面传过来的ID是否为空，如果为空则把up_cat常量赋给上一级分类ID
		if (up_cat_id == null || up_cat_id.equals("")) {
			up_cat_id = cfg_topcatid;
		}
		//判断所属模块的类型是否为空，不为空则把模块类型值加入搜索参数
		if(M_type!=null&&!M_type.equals("")){
			pageMap.put("module_type",M_type);
		}
	    //去掉首尾的空格	
		pageMap.put("up_cat_id", up_cat_id.trim());
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if (this.session_mem_type.equals(Constants.COMPANY_MEM_TYPE)) {
				pageMap.put("mem_type", Constants.COMPANY_MEM_TYPE+",'2'");
			}else{
				pageMap.put("mem_type", Constants.PERSONAL_MEM_TYPE+",'2'");
			}
		}
		
		List list = categoryService.getList(pageMap);	
		Map map = new HashMap();
		//定义级数
		String cat_level = "";
		StringBuffer sb = new StringBuffer();
		//判断列表是否为空，如果为空则把等级赋给定义的cat_level
		if (list != null && list.size() > 0) {
			map = (HashMap) list.get(0);
			if (map!=null&&map.get("cat_level") != null) {
				cat_level = map.get("cat_level").toString();
			}
		}
		//重组HTML代码字符串
		sb.append("<select id='level" + cat_level + "' name='cat_attr' class='select cat_sel' onchange='onlyshowcate(this.value,"+cat_level+")'>");
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
				if(select_cat_id!=null && select_cat_id.equals(cat_id)){
					sb.append("<option value='"+cat_id+"' selected >");
				}else{
					sb.append("<option value='"+cat_id+"'>");
				}
				sb.append(cat_name);
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
	 * @MethodDescribe 方法描述   供应分类只读的操作
	 * @author  创建人  林俊钦
	 * @date  创建日期  Jul 19, 2011 9:59:37 AM
	 */

	public String onlyreadcate(){
           return INPUT;

	}	
	
	/**
	 * @return the CategoryList
	 */
	public List getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryList
	 *            the CategoryList to set
	 */
	public void setCategoryList(List categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the catename
	 */
	public String getCatename() {
		return catename;
	}

	/**
	 * @param catename
	 *            the catename to set
	 */
	public void setCatename(String catename) {
		this.catename = catename;
	}
	
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param Category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @param categoryService
	 *            the categoryService to set
	 */
	public void setCategoryService(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}
    
	/**
	 * @MethodDescribe 方法描述   对供应分类只能只读的操作
	 * @author  创建人  林俊钦
	 * @date  创建日期  Jul 19, 2011 9:54:53 AM
	 */
	
	private String select_cat_id;
	
	
	
	/**
	 * @return the select_cat_id
	 */
	public String getSelect_cat_id() {
		return select_cat_id;
	}
	/**
	 * @param select_cat_id the select_cat_id to set
	 */
	public void setSelect_cat_id(String select_cat_id) {
		this.select_cat_id = select_cat_id;
	}

	/**
	 * @return the modules
	 */
	public String getModules() {
		return modules;
	}

	/**
	 * @param modules the modules to set
	 */
	public void setModules(String modules) {
		this.modules = modules;
	}
	/**
	 * @return the up_cat_name
	 */
	public String getUp_cat_name() {
		return up_cat_name;
	}

	/**
	 * @param up_cat_name the up_cat_name to set
	 */
	public void setUp_cat_name(String up_cat_name) {
		this.up_cat_name = up_cat_name;
	}
	
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(category == null){
			category = new Category();
		}
		String id = this.category.getCat_id();
		if(!ValidateUtil.isRequired(id)){
			category = this.categoryService.get(id);
		}
	}
}
