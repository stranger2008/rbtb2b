/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberfriendAction.java 
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
import com.rbt.model.Memberfriend;
import com.rbt.service.IMemberService;
import com.rbt.service.IMembercatService;
import com.rbt.service.IMemberfriendService;
/**
 * @function 功能 会员商友表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Thu Aug 04 13:41:00 CST 2011
 */
@Controller
public class MemberfriendAction extends BaseAction  implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员商友表对象
	 */
	public Memberfriend memberfriend;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberfriendService memberfriendService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMembercatService membercatService;
	/*
	 * 会员商友表信息集合
	 */
	public List memberfriendList;
	public List memberList;
	public List membercat;
	/*
	 * 分类类型：0：产品，1：收藏，2：商友
	 */	
	public String search_cat_type="2";
	/*
	 * 搜索页面对搜索条件
	 */
	public String cust_name_s;
	public String name_s;
	public String cate_s;
	public String cellphone_s;
	public String starttime_s;
	public String endtime_s;
	public String sg;
 
	/**
	 * 方法描述：返回新增会员商友表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
        Map map=new HashMap();
        //对会员列表赋于空的列表
        if(memberList==null){
        	memberList = new ArrayList();; 
        }  
        // 获取客户标识
        String cust_id=	this.session_cust_id;
        map.put("cust_id", cust_id);
        map.put("cat_type", search_cat_type);
        membercat=this.membercatService.getList(map);
      //发布信息数量控制
        if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
        	controlInfoNum();
        }
		return goUrl(ADD);
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.memberfriendService.getCount(tmap);
		if(controlMsgNum(count,"memberfriend")){
			return true;
		}else{
			return false;
		}
	}

	
	
	/**
	 * 方法描述：新增会员商友表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		// 获取客户标识
		String cust_id="",f_cust_id="";		
		if(this.session_cust_id!=null){
			cust_id=this.session_cust_id;
			this.memberfriend.setCust_id(cust_id);
		}		
		if(cust_id.equals(this.memberfriend.getF_cust_id())){
			this.addFieldError("memberfriend.cust_name","您不能添加自己为商友");
		}
        //验证公司名称不能为空
        if(!ValidateUtil.isRequired(memberfriend.getCust_name())){        	
        	String cust_name=this.memberfriend.getCust_name();     
        	//找出数据库中是否存在该公司
        	Map memMap=new HashMap();
        	memMap.put("company_name",cust_name);
        	List memList=this.memberService.getList(memMap);     
        	//如果存在
        	if(memList !=null && memList.size()>0){
        		Map listMap=(HashMap)memList.get(0);
        		if(listMap.get("cust_id")!=null){
        			f_cust_id=listMap.get("cust_id").toString();
        			this.memberfriend.setF_cust_id(f_cust_id);
        		}
        	}else{
        		this.addFieldError("memberfriend.cust_name","你所添加的公司名称不存在");
        	}
        	//判断是否已经添加过该公司为商友了
        	Map map=new HashMap();
        	map.put("cust_id",cust_id);
        	map.put("company_name",cust_name);
			List list =this.memberfriendService.getList(map);
			if(list!=null&&list.size()>0){
				this.addFieldError("memberfriend.cust_name","您已添加该公司为商友");
			}
        }	

	    //发布信息数量控制
	    if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
	    	if(controlInfoNum()){
	      		return add();
	    	}
	    }
		//字段验证
		super.commonValidateField(memberfriend);
		if(super.ifvalidatepass){
			return add();
		}
		this.memberfriendService.insert(memberfriend);
		this.addActionMessage("新增会员商友信息成功");
		this.memberfriend = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改会员商友表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		// 获取客户标识
		String cust_id="",f_cust_id="";		
		if(this.session_cust_id!=null){
			cust_id=this.session_cust_id;
			this.memberfriend.setCust_id(cust_id);
		}		
		if(cust_id.equals(this.memberfriend.getF_cust_id())){
			this.addFieldError("memberfriend.cust_name","您不能添加自己为商友");
		}
        //验证公司名称不能为空
        if(!ValidateUtil.isRequired(memberfriend.getCust_name())){        	
        	String cust_name=this.memberfriend.getCust_name();   
        	//找出数据库中是否存在该公司
        	Map memMap=new HashMap();
        	memMap.put("company_name",cust_name);
        	List memList=this.memberService.getList(memMap);     
        	//如果存在
        	if(memList !=null && memList.size()>0){
        		Map listMap=(HashMap)memList.get(0);
        		if(listMap.get("cust_id")!=null){
        			f_cust_id=listMap.get("cust_id").toString();
        			this.memberfriend.setF_cust_id(f_cust_id);
        		}
        	}else{
        		this.addFieldError("memberfriend.cust_name","你所添加的公司名称不存在");
        	}
        	//判断是否已经添加过该公司为商友了
        	Map map=new HashMap();
        	map.put("cust_id",cust_id);
        	map.put("company_name",cust_name);
			List list =this.memberfriendService.getList(map);
			if(list!=null&&list.size()>0){
				Map listMap=new HashMap();
				listMap=(HashMap)list.get(0);
				if(listMap.get("info_id")!=null){
					//判断是否已经加过对方为好友
					if(!memberfriend.getInfo_id().equals(listMap.get("info_id").toString())){
						this.addFieldError("memberfriend.cust_name","您已添加该公司为商友");
					}					
				}				
			}
        }	
        //字段验证
		super.commonValidateField(memberfriend);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberfriendService.update(memberfriend);
		this.addActionMessage("修改会员商友信息成功");
		return list();
	}
	/**
	 * 方法描述：删除会员商友表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberfriend.getInfo_id();
		id = id.replace(" ", "");
		this.memberfriendService.delete(id);
		this.addActionMessage("删除会员商友信息成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
        Map map=new HashMap();
        map.put("cust_id", this.session_cust_id);
        map.put("cat_type", search_cat_type);
        membercat=this.membercatService.getList(map);
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		//搜索姓名
		if(this.name_s!=null&&!this.name_s.equals("")){
			pageMap.put("name", name_s);
		}
		//搜索公司
		if(this.cust_name_s!=null&&!this.cust_name_s.equals("")){
			pageMap.put("cust_name", cust_name_s);
		}
		//搜索类别
		if(this.cate_s!=null&&!this.cate_s.equals("")){
			pageMap.put("cat_id", cate_s);
		}
		//搜索手机
		if(this.cellphone_s!=null&&!this.cellphone_s.equals("")){
			pageMap.put("cellphone", cellphone_s);
		}
		//判断是否加入搜索时间搜索
		if (starttime_s != null && !starttime_s.equals("")&&endtime_s != null && !endtime_s.equals("")){
			pageMap.put("search_starttime", starttime_s);
			pageMap.put("search_endtime", endtime_s);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberfriendService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		// 找出信息列表，放入list
		memberfriendList = this.memberfriendService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员商友表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberfriend.getCust_id()!=null){
			if(accessControl(memberfriend.getCust_id())){
				return list();
			}
		}
        //对会员列表赋于空的列表
        if(memberList==null){
        	memberList=new ArrayList();
        } 
		Map map=new HashMap();
        // 获取客户标识
        map.put("cust_id", this.session_cust_id);
        map.put("cat_type", search_cat_type);
        membercat=this.membercatService.getList(map);
		return goUrl(VIEW);
	}
	/**
	 * @MethodDescribe 方法描述   根据会员名显示会员的资料
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 4, 2011 4:00:45 PM
	 */
	public String showview() throws Exception {
        Map map=new HashMap();
        String cust_name="";
        String contact_qq="",email="",contact_name="",website="",contact_cellphone="",contact_msn="",phone="",skype="",f_cust_id="";
        //判断机构名称是否为空
        if(this.memberfriend.getCust_name()!=null&&!"".equals(this.memberfriend.getCust_name())){
        	cust_name=this.memberfriend.getCust_name();
        	map.put("cust_name",cust_name);
        	//根据公司名称找出相应的列表
            memberList=this.memberService.getList(map); 
         }else{
         	this.addFieldError("memberfriend.cust_name","公司名称不能为空，请重新输入");
         	this.memberfriend=null;
         	return add();
         } 
         if(memberList!=null&&memberList.size()<1){
        	this.addFieldError("memberfriend.cust_name","您所找的公司名称不存在，请查看输入是否有误");
        	//清空然后再实例化对象，把名称保留在页面上
        	this.memberfriend=null;
        	memberfriend=new Memberfriend();
        	this.memberfriend.setCust_name(cust_name);
        	return add();
         }else{
        		Map memuser=new HashMap();       
        		//用来获取循环中，与商友名称一致的索引
        		int in_user=0;
                if(memberList!=null&&memberList.size()>0){
                	Map listMap=new HashMap();
                	for(int i=0;i<memberList.size();i++){
                		listMap=(HashMap)memberList.get(i);              	 
                		if(listMap.get("cust_name")!=null){
                			String name=listMap.get("cust_name").toString();
                			if(name.equals(cust_name)){
                				in_user=i;
                			}
                		}
                	}    	
                	memuser=(HashMap)memberList.get(in_user);
                	if(memuser.get("contact_qq")!=null){
    	        		contact_qq=memuser.get("contact_qq").toString();	               
    	        	}
    	        	if(memuser.get("email")!=null){
    	        		email=memuser.get("email").toString();	                
    	        	}
    	        	if(memuser.get("contact_name")!=null){
    	        		contact_name=memuser.get("contact_name").toString();	                
    	        	}
    	        	if(memuser.get("website")!=null){
    	        		website=memuser.get("website").toString();
    	        	}
    	        	if(memuser.get("contact_cellphone")!=null){
    	        		contact_cellphone=memuser.get("contact_cellphone").toString();
    	        	}
    	        	if(memuser.get("contact_msn")!=null){
    	        		contact_msn=memuser.get("contact_msn").toString();
    	        	}
    	        	if(memuser.get("phone")!=null){
    	        		phone=memuser.get("phone").toString();	        		
    	        	}
    	        	if(memuser.get("cust_id")!=null){
    	        		f_cust_id=memuser.get("cust_id").toString();	        		
    	        	}
	        		//如果根据模糊查找的会员列表大于1的话再执行更精确的搜索
	        		this.memberfriend.setQq(contact_qq);   
	        		this.memberfriend.setEmail(email);
	        		this.memberfriend.setName(contact_name); 
	        		this.memberfriend.setF_cust_id(f_cust_id);
	        		this.memberfriend.setPhone(phone);
		        	this.memberfriend.setMsn(contact_msn);
		        	this.memberfriend.setCellphone(contact_cellphone);
		            this.memberfriend.setWebsite(website);  		
                }
                if(sg==null || sg.equals("1")){
                	return add(); 
                }else{
                	return view();
                }
          }
	 }
	/**
	 * @return the MemberfriendList
	 */
	public List getMemberfriendList() {
		return memberfriendList;
	}
	/**
	 * @param memberfriendList
	 *  the MemberfriendList to set
	 */
	public void setMemberfriendList(List memberfriendList) {
		this.memberfriendList = memberfriendList;
	}
	/**
	 * @return the memberList
	 */
	public List getMemberList() {
		return memberList;
	}
	/**
	 * @param memberList the memberList to set
	 */
	public void setMemberList(List memberList) {
		this.memberList = memberList;
	}
	/**
	 * @return the membercat
	 */
	public List getMembercat() {
		return membercat;
	}
	/**
	 * @param membercat the membercat to set
	 */
	public void setMembercat(List membercat) {
		this.membercat = membercat;
	}
	/**
	 * @return the memberfriend
	 */
	public Memberfriend getMemberfriend() {
		return memberfriend;
	}
	/**
	 * @param Memberfriend
	 *            the memberfriend to set
	 */
	public void setMemberfriend(Memberfriend memberfriend) {
		this.memberfriend = memberfriend;
	}
	
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberfriend == null){
			memberfriend = new Memberfriend();
		}
		String id = memberfriend.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			memberfriend = this.memberfriendService.get(id);
		}
	}
	public String getCust_name_s() {
		return cust_name_s;
	}
	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public String getCate_s() {
		return cate_s;
	}
	public void setCate_s(String cate_s) {
		this.cate_s = cate_s;
	}
	public String getCellphone_s() {
		return cellphone_s;
	}
	public void setCellphone_s(String cellphone_s) {
		this.cellphone_s = cellphone_s;
	}
	public String getStarttime_s() {
		return starttime_s;
	}
	public void setStarttime_s(String starttime_s) {
		this.starttime_s = starttime_s;
	}
	public String getEndtime_s() {
		return endtime_s;
	}
	public void setEndtime_s(String endtime_s) {
		this.endtime_s = endtime_s;
	}
}

