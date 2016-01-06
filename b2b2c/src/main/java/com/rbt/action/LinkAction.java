/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: LinkAction.java 
 */
package com.rbt.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.AreaFuc;
import com.rbt.model.Link;
import com.rbt.service.ILinkService;
import com.rbt.service.ILink_groupService;

/**
 * @function 功能  友情链接action类
 * @author  创建人 蔡毅存
 * @date  创建日期  Jun 28, 2011
 */
@Controller
public class LinkAction extends BaseAction  implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -5366624086700003990L;
	/*
	 * 列表页友情链接对象
	 */
	public Link link;
	/*
	 * 注入linkService
	 */
	@Autowired
	public ILinkService linkService;
	/*
	 * 注入link_groupService
	 */
	@Autowired
	public ILink_groupService link_groupService;
	
	/*
	 * 列表页友情链接集合
	 */
	public List linkList;
	
	/*
	 * 列表页友情链接分组集合
	 */
	public List link_groupList;

	/*
	 * 搜索字段
	 * link_name_s:友情链接名称
	 */
	public String link_name_s;
	public String link_name_ss;
	public String name_s;
	public String url_s;
	public String link_group;
	public String is_display_s;
	public String area_attr_s;
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
    //商城更新显示与不显示
	public String mallupdateState()throws Exception
	{
		setPlatType();
		return updateState();
	}
	
	
	/**
	 * 方法描述：默认执行方法
	 * @return
	 * @throws Exception
	 */
	public String excute() throws Exception {
		//获取友情链接分组
		getlinkgroup();
		return goUrl(INDEXLIST);
	}
    
	/**
	 * 方法描述：根据主键找出友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.link.getLink_id();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		//获取友情链接分组
		getlinkgroup();
		this.setHidden_area_value(link.getArea_attr());
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {		
		// 找出地区字段的存入隐藏值中
		backArea(link.getArea_attr()); 
		link.setArea_attr(area_attr);
		link.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(link);
		if(super.ifvalidatepass){
			return view();
		}
		this.linkService.update(link);
		this.addActionMessage("修改友情链接成功");
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
		if(link_name_s!=null && !link_name_s.equals("")) pageMap.put("link_name", link_name_s);
		if(link_name_ss!=null && !link_name_ss.equals("")) pageMap.put("link_name", link_name_ss);
		if(link_group!=null&& !link_group.equals(""))pageMap.put("link_group", link_group);
		if(name_s!=null&&!name_s.equals(""))pageMap.put("name", name_s);
		if(url_s!=null&&!url_s.equals(""))pageMap.put("url", url_s);
		if(is_display_s!=null&&!is_display_s.equals(""))pageMap.put("is_display", is_display_s);
		if(area_attr_s!=null&&!area_attr_s.equals(""))
			pageMap.put("area_attr", area_attr_s);
		//商城地区过滤
		pageMap=super.shopareafilter(pageMap);
		//过滤地区
		pageMap=super.areafilter(pageMap); 
		//根据页面提交的条件找出信息总数
		int count = this.linkService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		linkList = this.linkService.getList(pageMap);
		linkList=com.rbt.function.CategoryFuc.replaceList(linkList,"");
		return goUrl(INDEXLIST);
	} 
	
	
	
	/**
	 * 方法描述：删除友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String linkid = this.link.getLink_id();
		linkid = linkid.replace(" ", "");
		this.linkService.delete(linkid);
		this.addActionMessage("删除友情链接成功");
		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取友情链接分组
		getlinkgroup();
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 用于所属地区的回选开始
		loadArea();		
		if("0".equals(link.getLink_group())){
			this.addFieldError("link.link_group", "请选择友情链接分组");
		}
		link.setArea_attr(area_attr);
		link.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(link);
		if(super.ifvalidatepass){
			return add();
		}
		this.linkService.insert(link);
		this.addActionMessage("新增友情链接成功");
		this.link = null;
		return add();
	}
	
	/**
	 * 方法描述：批量显示/不显示友情链接
	 * @return
	 * @throws Exception
	 */
	public String updateState() throws Exception {
		String linkid = this.link.getLink_id();
		String is_display = this.link.getIs_display();
		linkid = linkid.replace(" ", "");
		String linkidStr[] = linkid.split(",");
		List linkList = new ArrayList();
		if(linkidStr.length>0){
			for(int i=0;i<linkidStr.length;i++){
				HashMap linkMap = new HashMap();
				linkMap.put("is_display", is_display);
				linkMap.put("link_id", linkidStr[i]);
				linkList.add(linkMap);
			}
		}
		this.linkService.updateLinkState(linkList);
		String tip = "";
		if(is_display.equals("0")){
			tip = "显示友情链接成功";
		}else if(is_display.equals("1")){
			tip = "不显示友情链接成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
    public void getlinkgroup(){
    	Map map = new HashMap();
		link_groupList=link_groupService.getList(map);
    }
	
	/**
	 * 方法描述：get/set方法
	 * @return
	 * @throws Exception
	 */
		public String getLink_name_s() {
			return link_name_s;
		}
		public void setLink_name_s(String link_name_s) {
			this.link_name_s = link_name_s;
		}
		public String getName_s() {
			return name_s;
		}
		public void setName_s(String name_s) {
			this.name_s = name_s;
		}
		public String getLink_name_ss() {
			return link_name_ss;
		}
		public void setLink_name_ss(String link_name_ss) {
			this.link_name_ss = link_name_ss;
		}
		public String getIs_display_s() {
			return is_display_s;
		}
		public void setIs_display_s(String is_display_s) {
			this.is_display_s = is_display_s;
		}

		public String getLink_group(){
			return link_group;
		}

		public String getUrl_s() {
			return url_s;
		}

		public void setUrl_s(String url_s) {
			this.url_s = url_s;
		}

		public ILinkService getLinkService() {
			return linkService;
		}
		
		public List getLinkList(){
			return linkList;
		}
		
		public void setLinkList(List linkList){
			this.linkList=linkList;
		}

		public ILink_groupService getLink_groupService() {
			return link_groupService;
		}

		public void setLink_group(String link_group) {
			this.link_group = link_group;
		}
        
		public Link getLink(){
			return link;
		}
		
		public void setLink(Link link){
			this.link=link;
		}

		public List getLink_groupList(){
			return link_groupList;
		}
		
		public void setLink_groupList(List link_groupList){
			this.link_groupList=link_groupList;
		}
		
		public String getArea_attr_s() {
			return area_attr_s;
		}

		public void setArea_attr_s(String area_attr_s) {
			this.area_attr_s = area_attr_s;
		}

		public String getArea_attr() {
			return area_attr;
		}

		public void setArea_attr(String area_attr) {
			this.area_attr = area_attr;
		}

		public String getHidden_area_value() {
			return hidden_area_value;
		}

		public void setHidden_area_value(String hidden_area_value) {
			this.hidden_area_value = hidden_area_value;
		}
		/**
		 * @Method Description : 当进入方法后初始化对象
		 * @author : 林俊钦
		 * @date : Nov 8, 2011 2:36:50 PM
		 */
		public void prepare() throws Exception { super.saveRequestParameter();
			if(link == null){
				link = new Link();
			}
			String id = link.getLink_id();
			if(!ValidateUtil.isDigital(id)){
				link = this.linkService.get(id);
			}
		}
}
