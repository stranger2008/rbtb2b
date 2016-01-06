/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: LinkgroupAction.java 
 */
package com.rbt.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.util.ValidateUtil;
import com.rbt.model.Link_group;
import com.rbt.service.ILink_groupService;
/**
 * @function 功能  友情链接分组action类
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
 */
@Controller
public class LinkgroupAction extends BaseAction  implements Preparable{
    /**
	 * 序列化
	 */
	private static final long serialVersionUID = -7579538367032484148L;
	/*
	 * 友情链接分组对象
	 */
	public Link_group link_group;
	 /*
	 * 业务接口
	 */
    @Autowired
	public ILink_groupService link_groupService;
    /*
	 * 列表对象
	 * link_groupList
	 */
	public List link_groupList;
	/*
	 * 字段获取全选id
	 */
    public String admin_link_group_id;
    public String name;
   
	public String excute() throws Exception {
		return goUrl(INDEXLIST);
	}
   
	
	//商城获取列表
	public String malllist() throws Exception{
		setPlatType();
		return list();
	}
	//商城添加信息
    public String malladd()throws Exception
    {
    	setPlatType();
    	return add();
    }
    //商城查看信息
    public String  mallview()throws Exception
    {
    	setPlatType();
    	return view();
    }
    //商城删除信息
    public String  malldelete() throws Exception
    {
    	setPlatType();
    	return delete();
    }
    //商城修改友情链接分组名称
	public String mallupdateGroupname()throws Exception
	{
		setPlatType();
		return updateGroupname();
	}
	
	/**
	 * 方法描述：根据主键找出友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.link_group.getId();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {		
		link_group.setName(name);
		link_group.setPlat_type(mall_type);
		this.link_groupService.update(link_group);
		this.addActionMessage("修改友情链接分组成功");
		return list();
	}
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//页面搜索提交的参数
		Map pageMap = new HashMap();
		pageMap.put("plat_type", mall_type);
		if(name!=null && !name.equals("")) pageMap.put("name", name);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count = this.link_groupService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		getlinkgroup(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.link_group.getId();
		id = id.replace(" ", "");
		this.link_groupService.delete(id);
		this.addActionMessage("删除友情链接分组成功");
		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		Map map = new HashMap();
		getlinkgroup(map);
		return goUrl(ADD);
	}
	
	//绑定友情链接
	public void getlinkgroup(Map map){
		link_groupList=link_groupService.getList(map);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		link_group.setPlat_type(mall_type);
		this.link_groupService.insert(link_group);
		this.addActionMessage("新增友情链接分组成功");
		this.link_group = null;
		return list();
	}
	
	/**
	 * 方法描述：批量修改友情链接分组
	 * @return
	 * @throws Exception
	 */
	public String updateGroupname() throws Exception {
		String id = this.admin_link_group_id;
		String name = this.link_group.getName();
		id = id.replace(" ", "");
		name=name.replace(" ", "");
		String linkidStr[] = id.split(",");
		String linkNameStr[] = name.split(",");
		List linkgroupList = new ArrayList();
		if(linkidStr.length>0){
			for(int i=0;i<linkidStr.length;i++){
				Map linkMap = new HashMap();
				linkMap.put("name", linkNameStr[i]);
				linkMap.put("id", linkidStr[i]);
				linkgroupList.add(linkMap);
			}
		}
		this.link_groupService.updateLinkgroup_name(linkgroupList);
		this.addActionMessage("友情链接分组成功");
		return list();
	}
	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */
	public Link_group getLink_group(){
		return link_group;
	}
	
	public void setLink_group(Link_group link_group){
		this.link_group=link_group;
	}
	
	public String getAdmin_link_group_id() {
		return admin_link_group_id;
	}

	public void setAdmin_link_group_id(String admin_link_group_id) {
		this.admin_link_group_id = admin_link_group_id;
	}
	
	public List getLink_groupList(){
		return link_groupList;
	}
	
	public void setLink_groupList(List link_groupList){
		this.link_groupList=link_groupList;
	}
	
	public String getName(){
    	return name;
    }
	
    public void setName(String name){
    	this.name=name;
    }
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(link_group == null){
			link_group = new Link_group();
		}
		String id = link_group.getId();
		if(!ValidateUtil.isDigital(id)){
			link_group = this.link_groupService.get(id);
		}
	}
}
