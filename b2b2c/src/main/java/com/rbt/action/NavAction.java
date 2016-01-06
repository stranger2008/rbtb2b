/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: NavAction.java 
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
import com.rbt.model.Nav;
import com.rbt.service.ICommparaService;
import com.rbt.service.INavService;
import com.rbt.service.ISysmoduleService;

/**
 * @function 功能  导航链接action类
 * @author  创建人 蔡毅存
 * @date  创建日期  July 5, 2011
 */
@Controller
public class NavAction extends BaseAction implements Preparable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -852060267211940416L;
	/*
	 * 列表页导航链接对象
	 */
	public Nav nav;
	/*
	 * 注入navService
	 */
	@Autowired
	public INavService navService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	/*
	 * 列表页导航链接对象
	 */
    public List navList;
    /*
	 * 参数列表
	 */
    public List commparalist;
    /*
	 * 搜索字段
	 */
	public String nav_name_s;
	public String link_url_s;
    public String isshow_s;
    public String nav_post_s;
    public String admin_nav_id;
    public String isort_no;
    

    public void prepare() throws Exception { super.saveRequestParameter();
		if(nav == null){
			nav = new Nav();
		}
		String id = nav.getNav_id();
		if(!ValidateUtil.isDigital(id)){
			nav = this.navService.get(id);
		}
	}
	/**
	 * 方法描述：根据主键找出导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//获取参数列表
		getCommpara();
		return goUrl(VIEW);
	}
	
	/**
	 * 方法描述：修改导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String navid=nav.getNav_id();
		if(ValidateUtil.isDigital(navid))
		{
			return list();
		}
		if(nav.getNav_post().equals("0")){
			this.addFieldError("nav.nav_post", "请选择导航放置位置");
		}
		if(nav.getIsopen().equals("0")){
			this.addFieldError("nav.isopen", "请选择导航链接类型");
		}
		//判断高亮编码
		if(ValidateUtil.isRequired(this.nav.getNav_code())){
			this.addFieldError("nav.nav_code", "请选择导航高亮编码或自定义添加高亮代码，但必须链接地址中出现高亮编码");
		}
		nav.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(nav);
		if(super.ifvalidatepass){
			return view();
		}
		this.navService.update(nav);
		this.addActionMessage("修改导航信息成功");
		return list();
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
    //商城更新显示与不显示
	public String mallupdateisshow()throws Exception
	{
		setPlatType();
		return updateisshow();
	}
	 //商城排序信息
	public String mallupdatesortno()throws Exception
	{
		setPlatType();
		return updatesortno();
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
		if(nav_name_s!=null && !nav_name_s.equals("")) pageMap.put("nav_name", nav_name_s);
		if(isshow_s!=null && !isshow_s.equals("")) pageMap.put("isshow", isshow_s);
		if(nav_post_s!=null && !nav_post_s.equals("")) pageMap.put("nav_post", nav_post_s);
		if(link_url_s!=null && !link_url_s.equals("")) pageMap.put("link_url", link_url_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.navService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		//找出信息列表，放入list
		navList=this.navService.getList(pageMap);
		return goUrl(INDEXLIST);
	} 
	
	/**
	 * 方法描述：删除导航链接信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String navid = this.nav.getNav_id();
		navid = navid.replace(" ", "");
		this.navService.delete(navid);
		this.addActionMessage("删除导航链接成功");
		return list();
	}
	
	/**
	 * 方法描述：返回新增页面
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//获取参数列表
		getCommpara();
		return goUrl(ADD);
	}
	
	/**
	 * 方法描述：新增友情链接信息
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {		
		if(this.nav.getNav_post().equals("0")){
			this.addFieldError("nav.nav_pos", "请选择位置");
		}
		//判断高亮编码
		if(ValidateUtil.isRequired(this.nav.getNav_code())){
			this.addFieldError("nav.nav_code", "请选择导航高亮编码或自定义添加高亮代码，但必须链接地址中出现高亮编码");
		}else{
			String nav_code="";
			if(this.nav.getNav_code()!=null){
				nav_code=this.nav.getNav_code();
			}		    
			//判断高亮编码是否存在数据库
			Map map=new HashMap();
			map.put("nav_code", nav_code);
			List list=this.navService.getList(map);
			if(list!=null&&list.size()>0){
				this.addFieldError("nav.nav_code", "您所输入的高亮代码已存在，请重新输入或选择");
			}else{
				Map paraMap=new HashMap();
				paraMap.put("para_value", nav_code);
				List paralist=this.commparaService.getList(paraMap);
				if(list!=null&&paralist.size()>0){
					this.addFieldError("nav.nav_code", "您所输入的高亮代码已存在，请重新输入或选择");

				}
			}
		}
		if(nav.getIsopen().equals("0")){
			this.addFieldError("nav.isopen", "请选择导航链接类型");
		}
		nav.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(nav);
		if(super.ifvalidatepass){
			return add();
		}
		//插入数据库
		this.navService.insert(nav);
		this.addActionMessage("新增导航链接成功");
		this.nav = null;
		return add();
	}
	
	//获取参数列表
	public void getCommpara(){
    	Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commparalist = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * 方法描述：批量显示/不显示导航
	 * @return
	 * @throws Exception
	 */
	public String updateisshow() throws Exception {
		String navid = this.nav.getNav_id();
		String isshow = this.nav.getIsshow();
		navid = navid.replace(" ", "");
		String navidStr[] = navid.split(",");
		List navList = new ArrayList();
		if(navidStr.length>0){
			for(int i=0;i<navidStr.length;i++){
				Map linkMap = new HashMap();
				linkMap.put("isshow", isshow);
				linkMap.put("nav_id", navidStr[i]);
				navList.add(linkMap);
			}
		}
		this.navService.updateisshow(navList);
		String tip = "";
		if(isshow.equals("0")){
			tip = "显示导航成功";
		}else if(isshow.equals("1")){
			tip = "不显示导航成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	/**
	 * 方法描述：批量修改导航
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.admin_nav_id;
		String sort_no =isort_no;
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String navidStr[] = id.split(",");
		String navsortStr[] = sort_no.split(",");
		List sotrList = new ArrayList();
		if(navidStr.length>0){
			for(int i=0;i<navidStr.length;i++){
				HashMap sortMap = new HashMap();
				sortMap.put("sort_no", navsortStr[i]);
				sortMap.put("nav_id", navidStr[i]);
				sotrList.add(sortMap);
			}
		}
		this.navService.updatesort_no(sotrList);
		this.addActionMessage("批量排序修改成功");
		return list();
	}

	public Nav getNav() {
		return nav;
	}

	public void setNav(Nav nav) {
		this.nav = nav;
	}
	
	public List getNavList() {
		return navList;
	}

	public void setNavList(List navList) {
		this.navList = navList;
	}
	
	public String getNav_name_s() {
		return nav_name_s;
	}

	public void setNav_name_s(String nav_name_s) {
		this.nav_name_s = nav_name_s;
	}
	
	public String getIsshow_s() {
		return isshow_s;
	}

	public void setIsshow_s(String isshow_s) {
		this.isshow_s = isshow_s;
	}

	public String getNav_post_s() {
		return nav_post_s;
	}

	public void setNav_post_s(String nav_post_s) {
		this.nav_post_s = nav_post_s;
	}

	public String getLink_url_s() {
		return link_url_s;
	}

	public void setLink_url_s(String link_url_s) {
		this.link_url_s = link_url_s;
	}

	public String getAdmin_nav_id() {
		return admin_nav_id;
	}

	public void setAdmin_nav_id(String admin_nav_id) {
		this.admin_nav_id = admin_nav_id;
	}
	
	public String getIsort_no() {
		return isort_no;
	}

	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}
}
