/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberlevelAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Memberlevel;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberlevelService;
import com.rbt.service.IRolerightService;
import com.rbt.service.ISysmenuService;
import com.rbt.service.ISysmoduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 会员级别配置action类
 * @author 创建人 邱景岩
 * @date 创建日期 Wed Jul 13 15:08:05 CST 2011
 */
@Controller
public class MemberlevelAction extends BaseAction implements Preparable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8483880765428924712L;
	/*
	 * 搜索字段 level_name_s：级别名称
	 */
	public String level_name_s;
	/*
	 * 会员级别配置对象
	 */
	public Memberlevel memberlevel;
	/*
	 * 菜单业务层接口
	 */
	@Autowired
	public ISysmenuService sysmenuService;
	@Autowired
	public IRolerightService rolerightService;
	@Autowired
	public IMemberlevelService memberlevelService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	@Autowired
	private ICommparaService commparaService;
	
	/*
	 * 会员级别配置信息集合
	 */
	public List memberlevelList;
	public List moduleList;
	public List controlList;
	/*
	 * 菜单信息集合
	 */
	public List menuList;
	public String nameright;
	public String troname;
	public String troval;
	public String trokey;
	

	/**
	 * 方法描述：返回新增会员级别配置页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		setcode();//取得会员后台菜单	
		getModattr();
		if(troname==null || troname.equals("")){
			getcontrolList();
		}
		//控制条数发布的条数的回选
		backcontrolList();
		return goUrl(ADD);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 12, 2012 10:58:45 AM
	 * @Method Description :获取需要控制条数发布的条数
	 */
	public void getcontrolList(){
		Map map=new HashMap();
		map.put("para_code","num_control");
		controlList=this.commparaService.getList(map);
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 12, 2012 1:24:52 PM
	 * @Method Description :控制条数发布的条数的回选
	 */
	private String backcontrolList(){
		String ctlStr="";
		//判断是否重新构造控制列表
		if(troname==null || troname.equals("")){
			return ctlStr;
		}
		controlList=new ArrayList();
		String[] cname=this.troname.split(",");	
		String[] ckey=this.trokey.split(",");	
		String[] cval=this.troval.split(",");
		for(int i=0;i<cname.length;i++){
			Map listMap=new HashMap();
			listMap.put("para_value", cname[i].trim());
			listMap.put("para_key", ckey[i].trim());
			listMap.put("troval", cval[i].trim());
			controlList.add(listMap);
			if(i!=cname.length-1){
				ctlStr+=cname[i].trim()+"-"+cval[i].trim()+",";
			}else{
				ctlStr+=cname[i].trim()+"-"+cval[i].trim();
			}
		}		
		return ctlStr;
	}
	

	/**
	 * 方法描述：获取模块的串
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getModattr() {
		Map pageMap = new HashMap();
		pageMap.put("state_code", "0");
		pageMap.put("is_mem", "0");
		moduleList = this.sysmoduleService.getList(pageMap);
	}

	/**
	 * 方法描述：新增会员级别配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if (!ValidateUtil.isRequired(memberlevel.getMenu_right())) {
			String menu_right = this.memberlevel.getMenu_right();
			menu_right = menu_right.replace(" ", "");
			memberlevel.setMenu_right(menu_right);
		}
		if (memberlevel.getOper_right() != null && !memberlevel.getOper_right().equals("")) {
			String oper_right = this.memberlevel.getOper_right();
			oper_right = oper_right.replace(" ", "");
			memberlevel.setOper_right(oper_right);
		}
		if (memberlevel.getModule_attr() != null && !memberlevel.getModule_attr().equals("")) {
			String mod_attr=memberlevel.getModule_attr();
			mod_attr=mod_attr.replace(" ","");
			memberlevel.setModule_attr(mod_attr);
		}
		memberlevel.setSyslevel("1");//1：会员自定义级别
		//发布条数信息控制串
		String troStr=backcontrolList();
		this.memberlevel.setNum_control(troStr);
		//字段验证
		super.commonValidateField(memberlevel);
		if (super.ifvalidatepass) {
			return add();
		}
		this.memberlevelService.insert(memberlevel);
		this.addActionMessage("新增会员级别成功");
		return add();
	}

	/**
	 * 方法描述：修改会员级别配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String mlid = memberlevel.getLevel_id();
		if (ValidateUtil.isDigital(mlid)) {
			return list();
		}
		if (!ValidateUtil.isRequired(nameright)) {
			String menu_right = nameright;
			menu_right = menu_right.replace(" ", "");
			memberlevel.setMenu_right(menu_right);
		} else {
			memberlevel.setMenu_right("");
		}
		String oper_right = this.memberlevel.getOper_right();
		if (oper_right != null && !oper_right.equals("")) {
			oper_right = oper_right.replace(" ", "");
			memberlevel.setOper_right(oper_right);
		}
		if (memberlevel.getModule_attr() != null && !memberlevel.getModule_attr().equals("")) {
			String mod_attr=memberlevel.getModule_attr();
			mod_attr=mod_attr.replace(" ","");
			memberlevel.setModule_attr(mod_attr);
		}
		//发布条数信息控制串
		String troStr=backcontrolList();
		this.memberlevel.setNum_control(troStr);
		//字段验证
		super.commonValidateField(memberlevel);
		if (super.ifvalidatepass) {
			return view();
		}
		this.memberlevelService.update(memberlevel);
		this.addActionMessage("修改会员级别成功");
		return list();
	}

	/**
	 * 方法描述：删除会员级别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberlevel.getLevel_id();
		id = id.replace(" ", "");
		this.memberlevelService.delete(id);
		this.addActionMessage("删除会员级别成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// 页面搜索提交的参数
		Map pageMap = new HashMap();
		if (level_name_s != null && !level_name_s.equals("")) {
			pageMap.put("level_name", level_name_s);
		}
		//过滤地区
		pageMap = super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberlevelService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count, pageMap);
		memberlevelList = this.memberlevelService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出会员级别
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		setcode();//取得会员后台菜单
		getModattr();
		if(troname==null){
			getcontrolList();
			String troStr="";
			if(memberlevel.getNum_control()!=null){
				troStr=memberlevel.getNum_control();
			}
			for(int i=0;i<controlList.size();i++){
				Map listMap=new HashMap();
				listMap=(HashMap)controlList.get(i);
				if(listMap.get("para_value")!=null){
					String troname=listMap.get("para_value").toString();
					//找出该模块是否有被设置过发过条数
					if(troStr!=null && !troStr.equals("")||troStr.indexOf(troname+"-")>-1){
						int len=troStr.indexOf(troname);
						//截取单个模块的键值对
						if(len>-1){
							if(len<troStr.length()){
								String tros=troStr.substring(len,troStr.length());
								int posLen=tros.indexOf(",");
								String tro="";
								if(posLen>-1){
									tro=tros.substring(0,posLen);
								}else{
									tro=tros.substring(0,tros.length());
								}
								//tro得到  模块名-值
								int lineLen=tro.indexOf("-");
								String cval="";
								if(lineLen>-1 &&lineLen+1<tro.length()){
									cval= tro.substring(lineLen+1,tro.length());
								}
								listMap.put("troval", cval);
								controlList.set(i, listMap);
							}
						}
					}
				}
			}
		}
		return goUrl(VIEW);
	}

	

	
	/**
	 * 方法描述：获取会员后台的菜单
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setcode() {
		Map treeMap = new HashMap();
		treeMap.put("syscode", "com");
		// 找出会员后台菜单树，放入list
		menuList = this.sysmenuService.getList(treeMap);
	}

	/**
	 * @return the MemberlevelList
	 */
	public List getMemberlevelList() {
		return memberlevelList;
	}

	/**
	 * @param memberlevelList
	 *            the MemberlevelList to set
	 */
	public void setMemberlevelList(List memberlevelList) {
		this.memberlevelList = memberlevelList;
	}

	/**
	 * @return the menuList
	 */
	public List getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList
	 *            the menuList to set
	 */
	public void setMenuList(List menuList) {
		this.menuList = menuList;
	}

	/**
	 * @return the level_name_s
	 */
	public String getLevel_name_s() {
		return level_name_s;
	}

	/**
	 * @param level_name_s
	 *            the level_name_s to set
	 */
	public void setLevel_name_s(String level_name_s) {
		this.level_name_s = level_name_s;
	}

	public String getNameright() {
		return nameright;
	}

	public void setNameright(String nameright) {
		this.nameright = nameright;
	}

	/**
	 * @return the memberlevel
	 */
	public Memberlevel getMemberlevel() {
		return memberlevel;
	}

	/**
	 * @param Memberlevel
	 *            the memberlevel to set
	 */
	public void setMemberlevel(Memberlevel memberlevel) {
		this.memberlevel = memberlevel;
	}

	public void prepare() throws Exception {
		super.saveRequestParameter();
		if (memberlevel == null) {
			memberlevel = new Memberlevel();
		}
		String id = this.memberlevel.getLevel_id();
		if (!ValidateUtil.isDigital(id)) {
			memberlevel = this.memberlevelService.get(id);
		}
	}

	public List getControlList() {
		return controlList;
	}

	public void setControlList(List controlList) {
		this.controlList = controlList;
	}

}
