/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberchannelAction.java 
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
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Member;
import com.rbt.model.Memberchannel;
import com.rbt.model.Memberconfig;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberchannelService;
import com.rbt.service.IMemberconfigService;

/**
 * @function 功能 记录会员企业站栏目信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 16:21:41 CST 2011
 */
@Controller
public class MemberchannelAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员企业站栏目信息对象
	 */
	public Memberchannel memberchannel;
	/*
	 * 记录会员企业站栏目信息对象
	 */
	public Memberconfig memberconfig;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberchannelService memberchannelService;
	@Autowired
	public IMemberconfigService memberconfigService;
	@Autowired
	public IMemberService memberService;
	/*
	 * 记录会员企业站栏目信息信息集合
	 */
	public List memberchannelList;

	public String member_memberchannel_id;

	public String member_sort;

	public String member_name;

	public String member_num;
	/*
	 * 记录会员企业站栏目信息信息子段
	 */
	public String ch_name;
	public String ch_code;
	public String ch_type;
	public String is_dis;
	public String sort_no;
	public String ch_num;
	public String meta_keyword;
	public String meta_desc;
	public String ch_content;
	public String ch_id;
	/*
	 * 搜索字段
	 */
	public String ch_name_s;
	public String is_dis_s;
	public String ch_type_s;
	public String is_dis_update;
	//激活企业站跳转页面
	public static final String activate="/member_Memberconfig_add.action";


	
	
	
	//商城获取列表
	public String bmalllist() throws Exception{
		setPlatType();
		String cust_id = this.session_cust_id;
		Map pageMap = new HashMap();
		pageMap.put("cust_id", cust_id);
		pageMap.put("plat_type", mall_type);
		if (ch_name_s != null && !ch_name_s.equals(""))
			pageMap.put("ch_name", ch_name_s);
		if (is_dis_s != null && !is_dis_s.equals(""))
			pageMap.put("is_dis", is_dis_s);
		if (ch_type_s != null && !ch_type_s.equals(""))
			pageMap.put("ch_type", ch_type_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberchannelService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		memberchannelList = this.memberchannelService.getList(pageMap);
		return goUrl(INDEXLIST);
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
		int count = this.memberchannelService.getCount(tmap);
		if(controlMsgNum(count,"memberchannel")){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 方法描述：返回新增记录会员企业站栏目信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员企业站栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		if ("3".equals(this.memberchannel.getCh_type())) {
			this.addFieldError("memberchannel.ch_type", "请选择栏目类型");
		}
		//设置属于B2B 或者B2C
		memberchannel.setPlat_type(mall_type);
		memberchannel.setCust_id(this.session_cust_id);
		memberchannel.setSys_ch("1");
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		//字段验证
		super.commonValidateField(memberchannel);
		if(super.ifvalidatepass){
			return add();
		}
		this.memberchannelService.insert(memberchannel);
		this.addActionMessage("新增企业站栏目成功");
		this.memberchannel = null;
		is_crotorl=true;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		if ("3".equals(this.memberchannel.getCh_type())) {
			this.addFieldError("memberchannel.ch_type", "请选择栏目类型");
		}
		//字段验证
		super.commonValidateField(memberchannel);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberchannelService.update(memberchannel);
		this.addActionMessage("修改企业站栏目成功");
		return list();
	}

	/**
	 * 方法描述：删除记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberchannel.getCh_id();
		id = id.replace(" ", "");
		this.memberchannelService.delete(id);
		this.addActionMessage("删除企业站栏目成功");
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String cust_id = this.session_cust_id;
		Map pageMap = new HashMap();
		pageMap.put("cust_id", cust_id);
		pageMap.put("plat_type", mall_type);
		if (ch_name_s != null && !ch_name_s.equals(""))
			pageMap.put("ch_name", ch_name_s);
		if (is_dis_s != null && !is_dis_s.equals(""))
			pageMap.put("is_dis", is_dis_s);
		if (ch_type_s != null && !ch_type_s.equals(""))
			pageMap.put("ch_type", ch_type_s);

		// 通过用户名找出用户信息
		memberchannelList = this.memberchannelService.getList(pageMap);
		if (memberchannelList != null && memberchannelList.size() == 0&&!mall_type.equals("b2c")) {
			getResponse().sendRedirect(activate);
		}
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberchannelService.getCount(pageMap);

		// 分页插件
		pageMap = super.pageTool(count, pageMap);

		memberchannelList = this.memberchannelService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
    //插入企业站配置
	public String insertinto() throws Exception {
		
		memberconfig=this.memberconfigService.get(this.session_cust_id);
		
		Member member = memberService.get(this.session_cust_id);
		String cust_name = "";
		if(member != null && member.getCust_name()!=null){
			cust_name = member.getCust_name();
		}
		
		if(memberconfig == null){
		    //批量插入系统菜单栏目
			Map map=new HashMap();
			map.put("cust_id", this.session_cust_id);
			map.put("plat_type", mall_type);
			this.memberchannelService.insertintoMemberchannel(map);
			memberconfig = new Memberconfig();
			memberconfig.setCust_id(this.session_cust_id);
			memberconfig.setTemp_code(SysconfigFuc.getSysValue("cfg_memberDefaultWeb"));
			memberconfig.setIs_dis("0");
			memberconfig.setLoc_num("1");
			memberconfig.setWeb_name(cust_name);
			memberconfig.setWeb_title(cust_name);
			
			//插入配置菜单
			this.memberchannelService.insertMemberconfig(memberconfig);
		}
		return list();
	}

	/**
	 * 方法描述：根据主键找出记录会员企业站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(memberchannel.getCust_id()!=null){
			if(accessControl(memberchannel.getCust_id())){
				return list();
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：批量显示/不显示导航
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateisshow() throws Exception {
		String chid = ch_id;
		String is_dis = is_dis_update;
		String chidStr[]=null;
		if(chid!=null&&!"".equals(chid))
		{
			chid = chid.replace(" ", "");
			chidStr = chid.split(",");
		}
		List chList = new ArrayList();
		if (chidStr!=null&&chidStr.length > 0) {
			for (int i = 0; i < chidStr.length; i++) {
				Map linkMap = new HashMap();
				linkMap.put("is_dis", is_dis);
				linkMap.put("ch_id", chidStr[i]);
				chList.add(linkMap);
			}
		}
		this.memberchannelService.updateisdis(chList);
		String tip = "";
		if (is_dis!=null&&is_dis.equals("0")) {
			tip = "显示栏目成功";
		} else if (is_dis!=null&&is_dis.equals("1")) {
			tip = "隐藏栏目成功";
		}
		else {
			tip = "操作失败";
		}
		this.addActionMessage(tip);
		return list();
	}

	/**
	 * 方法描述：批量修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception {
		String id = this.member_memberchannel_id;
		String sort_no = this.member_sort;
		String name = this.member_name;
		String num = this.member_num;
		String idStr[] = null;
		String sortStr[] = null;
		String nameStr[] =null;
		String numStr[] = null;
		if(id!=null&&!"".equals(id))
		{
			id = id.replace(" ", "");
			idStr = id.split(",");
		}
		if(sort_no!=null&&!"".equals(sort_no))
		{
			sort_no = sort_no.replace(" ", "");
			sortStr = sort_no.split(",");
		}
		if(name!=null&&!"".equals(name))
		{
			name = name.replace(" ", "");
			nameStr = name.split(",");
		}
		if(num!=null&&!"".equals(num))
		{
			num = num.replace(" ", "");
			numStr= num.split(",");
		}
		
		List sotrList = new ArrayList();
		if (sortStr!=null&&sortStr.length > 0) {
			for (int i = 0; i < sortStr.length; i++) {
				Map sortMap = new HashMap();
				sortMap.put("ch_id", idStr[i]);
				sortMap.put("sort_no", sortStr[i]);
				sortMap.put("ch_name", nameStr[i]);
				sortMap.put("ch_num", numStr[i]);
				sotrList.add(sortMap);
			}
		}
		this.memberchannelService.updatechannel(sotrList);
		this.addActionMessage("批量修改成功");
		return list();
	}

	public List getMemberchannelList() {
		return memberchannelList;
	}

	public void setMemberchannelList(List memberchannelList) {
		this.memberchannelList = memberchannelList;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getCh_code() {
		return ch_code;
	}

	public void setCh_code(String ch_code) {
		this.ch_code = ch_code;
	}

	public String getCh_type() {
		return ch_type;
	}

	public void setCh_type(String ch_type) {
		this.ch_type = ch_type;
	}

	public String getIs_dis() {
		return is_dis;
	}

	public void setIs_dis(String is_dis) {
		this.is_dis = is_dis;
	}

	public String getSort_no() {
		return sort_no;
	}

	public void setSort_no(String sort_no) {
		this.sort_no = sort_no;
	}

	public String getCh_num() {
		return ch_num;
	}

	public void setCh_num(String ch_num) {
		this.ch_num = ch_num;
	}

	public String getMeta_keyword() {
		return meta_keyword;
	}

	public void setMeta_keyword(String meta_keyword) {
		this.meta_keyword = meta_keyword;
	}

	public String getMeta_desc() {
		return meta_desc;
	}

	public void setMeta_desc(String meta_desc) {
		this.meta_desc = meta_desc;
	}

	public String getCh_content() {
		return ch_content;
	}

	public void setCh_content(String ch_content) {
		this.ch_content = ch_content;
	}

	public String getCh_id() {
		return ch_id;
	}

	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}

	public String getIs_dis_update() {
		return is_dis_update;
	}

	public void setIs_dis_update(String is_dis_update) {
		this.is_dis_update = is_dis_update;
	}


	public String getMember_sort() {
		return member_sort;
	}

	public void setMember_sort(String member_sort) {
		this.member_sort = member_sort;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_num() {
		return member_num;
	}

	public void setMember_num(String member_num) {
		this.member_num = member_num;
	}
	
	/**
	 * @return the memberchannel
	 */
	public Memberchannel getMemberchannel() {
		return memberchannel;
	}

	/**
	 * @param Memberchannel
	 *            the memberchannel to set
	 */
	public void setMemberchannel(Memberchannel memberchannel) {
		this.memberchannel = memberchannel;
	}
	
	

	public String getMember_memberchannel_id() {
		return member_memberchannel_id;
	}
	public void setMember_memberchannel_id(String member_memberchannel_id) {
		this.member_memberchannel_id = member_memberchannel_id;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (memberchannel == null) {
			memberchannel = new Memberchannel();
		}
		String id = memberchannel.getCh_id();
		if (!ValidateUtil.isDigital(id)) {
			memberchannel = this.memberchannelService.get(id);
		}
	}
}
