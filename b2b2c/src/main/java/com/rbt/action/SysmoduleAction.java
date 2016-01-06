/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SysmoduleAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Sysmenu;
import com.rbt.model.Sysmodule;
import com.rbt.service.INavService;
import com.rbt.service.ISysmenuService;
import com.rbt.service.ISysmoduleService;
import com.opensymphony.xwork2.Preparable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 系统模块表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Jan 13 12:48:48 CST 2012
 */
@Controller
public class SysmoduleAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 系统模块表对象
	 */
	private Sysmodule sysmodule;
	/*
	 * 系统模块表业务层接口
	 */
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	public INavService navService;
	@Autowired
	public ISysmenuService sysmenuService;
	/*
	 * 系统模块表信息集合
	 */
	public List sysmoduleList;
	public String checkonly;
	public String old_link_menu;
	public String mod_type;
	public String mod_sort_no;

	/**
	 * 方法描述：返回新增系统模块表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增系统模块表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		this.sysmoduleService.insert(sysmodule);
		this.addActionMessage("新增系统模块表成功");
		this.sysmodule = null;
		return INPUT;
	}
	
	/**
	 * 方法描述：批量排序方法
	 * 
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public String updateSort() throws Exception{
		String rule_id = this.mod_type;
		String rule_num = this.mod_sort_no;	
		rule_id=rule_id.replace(" ","");
		String ruleStr[]=rule_id.split(",");
		String ruleNumStr[]=rule_num.split(",");
		List ruleList=new ArrayList();
		if(ruleStr.length>0){
			for(int i=0;i<ruleStr.length;i++){
				Map ruleMap = new HashMap();
				ruleMap.put("module_type", ruleStr[i]);
				//如果文本框为空则返回
				if(ValidateUtil.isRequired(ruleNumStr[i])){
					return list();
				}
				ruleMap.put("sort_no", ruleNumStr[i]);
				ruleList.add(ruleMap);
			}
		}
		this.sysmoduleService.updateSort(ruleList);
		this.addActionMessage("分类排序成功");
		return list();
	}
	
	/**
	 * 方法描述：修改系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		Map map=new HashMap();		
		String module="";
		// 验证模块类型是否存在
	    if(!this.validateFactory.isRequired(sysmodule.getModule_type())){
	    	module=sysmodule.getModule_type();
	    	map.put("module_type",module);	    	
	    }
	    List list =this.sysmoduleService.getList(map);
	    if(list!=null && list.size()>0){	    	
	    	Map modMap=new HashMap();
	    	modMap=(HashMap)list.get(0);
	    	// 验证是不是本条的记录
	    	if(modMap.get("module_type")!=null){
	    		if(!checkonly.equals(modMap.get("module_type").toString())){
	    			this.addFieldError("sysmodule.module_type", "模块类型已存在");
	    		}
	    	}
	    } 
	    // 当模块功能关闭时，导航对应的模块也要关闭
	    Map navMap=new HashMap();
	    navMap.put("nav_code", module);
	    List navList=this.navService.getList(navMap);
	    String id="",isshow="",state_code="";
	    if(navList!=null&&navList.size()>0){
	         Map listMap=new HashMap();
	         listMap=(HashMap)navList.get(0);
	         
	         if(listMap.get("nav_id")!=null){
	        	  id=listMap.get("nav_id").toString();
	         }
	         if(listMap.get("isshow")!=null){
	        	  isshow=listMap.get("isshow").toString();
	         }
	 	     if(!this.validateFactory.isRequired(sysmodule.getState_code())){
	 	    	  state_code=sysmodule.getState_code();
		     }
	    }
	    // 字段验证
		super.commonValidateField(sysmodule);
		if(super.ifvalidatepass){
			return view();
		}
		// 当导航与模块的显示的值不一致时执行操作,控制显示与隐藏
        if(!isshow.equals(state_code)){
       	 List aList = new ArrayList();
			 Map linkMap = new HashMap();
			 linkMap.put("isshow", state_code);
			 linkMap.put("nav_id", id);
			 aList.add(linkMap);
       	 this.navService.updateisshow(aList);
        }
	    // 当模块功能关闭时，对应的菜单功能也要关闭
        if(old_link_menu!=null){
        	String old_menu[]=old_link_menu.split(",");
        	List oldList=new ArrayList();
        	for(int i=0;i<old_menu.length;i++){
                // 当菜单与模块的state_code为禁用时，则把对应的菜单ID中值变为隐藏:0,显示，1,隐藏
        		Map cateMap = new HashMap();
        		cateMap.put("menu_id", old_menu[i]);
               	cateMap.put("enabled", "0");//设置全部为启用
               	oldList.add(cateMap);    		
        	}	
        	//更新菜单
        	this.sysmenuService.updateEnable(oldList);
        }        
        //再实行关闭或启动
    	String link_menu=sysmodule.getLink_menu();
    	String menu[]=link_menu.split(",");    	
    	List menuList=new ArrayList();
    	for(int i=0;i<menu.length;i++){
            // 当菜单与模块的state_code为禁用时，则把对应的菜单ID中值变为隐藏:0,显示，1,隐藏
    		Map cateMap = new HashMap();
    		cateMap.put("menu_id", menu[i]);
           	cateMap.put("enabled", state_code);
           	menuList.add(cateMap);    		
    	}	
    	//更新菜单
    	this.sysmenuService.updateEnable(menuList);
        // 更新系统模块
		this.sysmoduleService.update(sysmodule);
		this.addActionMessage("修改系统模块表成功");
		return list();
	}
	
	/**
	 * 方法描述：删除系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.sysmodule.getModule_type();
		id = id.replace(" ", "");
		this.sysmoduleService.delete(id);
		this.addActionMessage("删除系统模块表成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();		
		// 根据页面提交的条件找出信息总数
		int count=this.sysmoduleService.getCount(pageMap);		
		// 分页插件
		pageMap = super.pageTool(count,pageMap);		
		sysmoduleList = this.sysmoduleService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	
	/**
	 * 方法描述：批量启用与禁用
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisuse() throws Exception {
		String module_type = this.sysmodule.getModule_type();
		String state_code = this.sysmodule.getState_code();
		module_type = module_type.replace(" ", "");
		String downStr[] = module_type.split(",");
		List list = new ArrayList();
		if (downStr.length > 0) {
			for (int i = 0; i < downStr.length; i++) {
				Map galleryMap = new HashMap();
				galleryMap.put("module_type", downStr[i]);
				galleryMap.put("state_code", state_code);
				list.add(galleryMap);
			}
		}
		this.sysmoduleService.updateisuse(list);
		String tip = "";
		if (state_code.equals("0")) {
			tip = "启用成功";
		} else if (state_code.equals("1")) {
			tip = "禁用成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	
	/**
	 * 方法描述：根据主键找出系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：根据主键找出系统模块表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		//把menuid转换成名称
		if(sysmodule.getLink_menu()!=null){
			String menu[]=sysmodule.getLink_menu().split(",");
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<menu.length;i++){
				Sysmenu sysmenu=this.sysmenuService.get(menu[i]);
				if(sysmenu==null){
					sysmenu = new Sysmenu();
				}
				String menuName=sysmenu.getMenu_name();
				sb.append(menuName);
				if(i!=(menu.length-1)){
					sb.append(",");
				}
			}
			sysmodule.setLink_menu(sb.toString());
		}
		return goUrl(AUDIT);
	}
	
	
	/**
	 * @return the SysmoduleList
	 */
	public List getSysmoduleList() {
		return sysmoduleList;
	}
	/**
	 * @param sysmoduleList
	 *            the SysmoduleList to set
	 */
	public void setSysmoduleList(List sysmoduleList) {
		this.sysmoduleList = sysmoduleList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sysmodule == null){
			sysmodule = new Sysmodule();
		}
		String id = this.sysmodule.getModule_type();
		if(!this.validateFactory.isRequired(id)){
			sysmodule = this.sysmoduleService.get(id);
		}
	}

	/**
	 * @return the sysmodule
	 */
	public Sysmodule getSysmodule() {
		return sysmodule;
	}
	/**
	 * @param Sysmodule
	 *            the sysmodule to set
	 */
	public void setSysmodule(Sysmodule sysmodule) {
		this.sysmodule = sysmodule;
	}
	
}

