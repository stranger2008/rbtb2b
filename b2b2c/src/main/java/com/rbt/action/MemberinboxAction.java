/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberinboxAction.java 
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
import com.rbt.model.Member;
import com.rbt.model.Memberinbox;
import com.rbt.model.Membersendbox;
import com.rbt.model.Membertrash;
import com.rbt.service.ICommparaService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberinboxService;
import com.rbt.service.IMembersendboxService;
import com.rbt.service.IMembertrashService;

/**
 * @function 功能 会员收件信息表action类
 * @author 创建人 林俊钦
 * @date 创建日期 Fri Aug 05 14:23:14 CST 2011
 */
@Controller
public class MemberinboxAction extends BaseAction implements Preparable {

	private static final long serialVersionUID = 1L;
	/*
	 * 会员收件信息表对象
	 */
	public Memberinbox memberinbox;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberinboxService memberinboxService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IMembersendboxService membersendboxService;
	@Autowired
	public IMembertrashService membertrashService;
	/*
	 * 会员收件信息表信息集合
	 */
	public List memberinboxList;
	public List membertrashList;
	public List commparalist;
	/*
	 * 定义常量字段,用于从基本参数中找出相应的模块值
	 */
	private String para_code = "memberinbox_type";
	/*
	 * 用于前台的搜索字段
	 */
	public String cate_type_s;
	public String title_s;
	public String send_name_s;
	public String isread_s;
	public String type_s;
	public String starttime_s;
	public String endtime_s;
	public String cust_name_s;
	public String get_name_s;
	public String box_type;
	/*
	 * 用来判断是否是删除的方法，因为列表页跟删除调用的是同一个方法
	 */
	public String trash;
	/*
	 * 用来判断发件箱的数据删除后是否放入回收站的方法，因为列表页跟删除调用的是同一个方法
	 */
	public String sendel;
	/*
	 * 定义是否删除的常量
	 */
	private String isdel = "1";
	/*
	 * 定义已读变量，0未读，1已读
	 */
	private String isnotread = "0";
	private String isread = "1";
	/*
	 * 用于接收我的信箱中的类型,1收件箱，2发件箱，3回收站
	 */
	public String type;
	/*
	 * 公司名称
	 */
	public String cust_name;
	/*
	 * 定义收件人的名称
	 */
	public String send_name;
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 13, 2012 1:42:18 PM
	 * @Method Description : 会员发布条数的判断
	 */
	public boolean controlInfoNum(){
		//查出已发布的信息个数
		Map tmap = new HashMap();
		tmap.put("cust_id", this.session_cust_id);
		int count = this.membersendboxService.getCount(tmap);
		if(controlMsgNum(count,"membersendbox")){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * 方法描述：返回新增会员收件信息表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 根据cust_id 找出相应的名称
		Member member = this.memberService.get(this.session_cust_id);
		if (member != null) {
			if (member.getCust_name() != null) {
				cust_name = member.getCust_name();
			}
		}
		// 加载信件类型
		getcommpara();
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			controlInfoNum();
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员收件信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {		
		Map map = new HashMap();
		Map listMap = new HashMap();
		String company_name_string = this.send_name.toString();
		if(ValidateUtil.isRequired(company_name_string)){
			this.addFieldError("send_name", "收件人名称不能为空");
		}
		// 取出要多发信件的名称
		String[] company_name = company_name_string.split(",");
		// 验证收件人是否存在
		for (int i = 0; i < company_name.length; i++) {
			boolean flag = true;
			if (company_name[i] != null && !company_name[i].equals("")) {
				map.put("company_name", company_name[i].trim());
				List list = this.memberService.getList(map);
				if (list != null && list.size() < 1) {
					flag = false;
				}
			}
			// 如果为假的话则提示信息
			if (flag == false) {
				this.addFieldError("send_name", "收件人[" + company_name[i]+ "]不存在，请正确填写或删除该收件人后重新发送");
				return add();
			}
		}
		//发布信息数量控制
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			if(controlInfoNum()){
				return add();
			}
		}
		// 字段验证
		super.commonValidateField(memberinbox);
		if (super.ifvalidatepass) {
			return add();
		}
		// 保存数据库
		for (int i = 0; i < company_name.length; i++) {
			if (company_name[i] != null && !company_name[i].equals("")) {
				map.put("company_name", company_name[i].trim());
				List list = this.memberService.getList(map);
				if (list != null && list.size() > 0) {
					listMap = (HashMap) list.get(0);
					if (listMap.get("cust_id") != null) {
						String cid = listMap.get("cust_id").toString();
						// 设置收件人的会员ID
						this.memberinbox.setCust_id(cid);
						// 存入登录的ID
						this.memberinbox.setSend_cust_id(this.session_cust_id);
						// 存入未读的信件
						this.memberinbox.setIs_read(isnotread);
						// 把对象存入数据库收件箱表中
						this.memberinboxService.insert(memberinbox);
						// 把对象存入数据库发件箱表中
						Membersendbox sendbox = new Membersendbox();
						sendbox.setCust_id(this.session_cust_id);
						sendbox.setGet_cust_id(cid);
						sendbox.setMess_type(this.memberinbox.getMess_type());
						sendbox.setTitle(this.memberinbox.getTitle());
						sendbox.setContent(this.memberinbox.getContent());
						this.membersendboxService.insert(sendbox);
					}
				}
			}
		}
		this.addActionMessage("发送信件成功");
		this.memberinbox = null;
		is_crotorl=true;
		return add();
	}

	/**
	 * 方法描述：修改会员收件信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		this.memberinboxService.update(memberinbox);
		this.addActionMessage("修改会员收件信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员收件信息表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String ids = this.memberinbox.getInfo_id();
		ids = ids.replace(" ", "");
		String[] id = ids.split(",");
		for (int i = 0; i < id.length; i++) {
			Map map = new HashMap();
			map.put("box_info_id", id[i]);
			// 用来判断该ID是否已经删除
			List boxlist = this.memberinboxService.getList(map);
			if (boxlist != null && boxlist.size() < 1) {
				return list();
			}
		}
		Memberinbox inbox = new Memberinbox();
		for (int i = 0; i < id.length; i++) {
			// 获取收件箱或发件箱中的类型
			inbox = this.memberinboxService.get(id[i]);
			// 实例化垃圾箱对象
			Membertrash memtrash = new Membertrash();
			// 为垃圾箱对象赋值
			memtrash.setBox_id(id[i]);
			memtrash.setCust_id(this.session_cust_id);
			memtrash.setInfo_type("2");// 代表从收件箱中删除的数据
			memtrash.setMess_type(inbox.getMess_type());
			memtrash.setTitle(inbox.getTitle());
			memtrash.setIn_date(inbox.getIn_date());
			memtrash.setSend_cust_id(inbox.getCust_id());
			memtrash.setGet_cust_id(inbox.getSend_cust_id());
			memtrash.setContent(inbox.getContent());
			// 把对象存入数据库回收表中
			this.membertrashService.insert(memtrash);
		}
		// 删除会员收件箱中的表的记录
		this.memberinboxService.delete(ids);
		this.addActionMessage("删除会员收件信息成功");
		return list();
	}

	/**
	 * 方法描述：删除会员收件信息表信息到回收站
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateIsdelete() throws Exception {
		String ids = "";
		if (!ValidateUtil.isRequired(this.memberinbox.getInfo_id())) {
			ids = this.memberinbox.getInfo_id();
		}
		ids = ids.replace(" ", "");
		String[] id = ids.split(",");
		List list = new ArrayList();
		if (id.length > 0) {
			for (int i = 0; i < id.length; i++) {
				Map listMap = new HashMap();
				listMap.put("info_id", id[i]);
				listMap.put("is_del", isdel);
				list.add(listMap);
			}
		}
		this.memberinboxService.updateIsdelete(list);
		return list();
	}

	/**
	 * 方法描述：根据搜索条件列出信息列表 收件箱
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			// 当操作者要查看自己的收件箱时，还需加上send_cust_id是属于自身的
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 当快速搜索信件类型不为空时加入搜索条件————select
		if (this.cate_type_s != null && !this.cate_type_s.equals("")) {
			pageMap.put("mess_type", this.cate_type_s);
		}
		// 当搜索标题不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.title_s)) {
			pageMap.put("title", this.title_s);
		}
		// 当搜索是否已读不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.isread_s)) {
			pageMap.put("is_read", this.isread_s);
		}
		// 当搜索类型不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.type_s)) {
			pageMap.put("mess_type", this.type_s);
		}
		// 当搜索发件人名称不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.cust_name_s)) {
			pageMap.put("cust_name", this.cust_name_s);
		}
		// 当搜索开始时间与结束时间不为空时加入搜索时间
		if (!ValidateUtil.isRequired(this.starttime_s)
				&& !ValidateUtil.isRequired(this.endtime_s)) {
			pageMap.put("search_starttime", this.starttime_s);
			pageMap.put("search_endtime", this.endtime_s);
		}
		// 加载信件类型
		getcommpara();
		//过滤地区
		pageMap=super.areafilter(pageMap);
		// 根据页面提交的条件找出信息总数
		int count = this.memberinboxService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		memberinboxList = this.memberinboxService.getList(pageMap);
		// 定义存放键值对的HASHMAP
		Map key_value_Map = new HashMap();
		// 定义信件属于哪一个模块
		Map paraMap = new HashMap();
		paraMap.put("para_code", para_code);
		// 取出相应模块的参数键值对
		List compara = this.commparaService.getList(paraMap);
		if (compara != null && compara.size() > 0) {
			// 定义存放值
			String para_value = "", para_key = "";
			// 存入系统参数表的HASHMAP
			Map comparaMap = new HashMap();
			for (int i = 0; i < compara.size(); i++) {
				comparaMap = (HashMap) compara.get(i);
				if (comparaMap.get("para_value") != null) {
					para_value = comparaMap.get("para_value").toString();
				}
				if (comparaMap.get("para_key") != null) {
					para_key = comparaMap.get("para_key").toString();
				}
				key_value_Map.put(para_value, para_key);
			}
		}
		// 判断信息列表是否为空,把ID替换成名称
		if (memberinboxList != null && memberinboxList.size() > 0) {
			Map memMap = new HashMap();
			for (int j = 0; j < memberinboxList.size(); j++) {
				memMap = (HashMap) memberinboxList.get(j);
				if (memMap.get("mess_type") != null) {
					String cate_id = memMap.get("mess_type").toString();
					if (key_value_Map.get(cate_id) != null) {
						String cate_name = key_value_Map.get(cate_id).toString();
						memMap.put("mess_type", cate_name);
						memberinboxList.set(j, memMap);
					}
				}
			}
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * @MethodDescribe 方法描述 根据搜索条件列出信息列表 发件箱
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 8, 2011 9:12:08 AM
	 */
	public String sendindex() throws Exception {
		// 判断是否是删除的方法进来
		if (this.sendel != null && !this.sendel.equals("")) {
			String ids = this.memberinbox.getInfo_id();
			ids = ids.replace(" ", "");
			String[] id = ids.split(",");
			Map map = new HashMap();
			for (int i = 0; i < id.length; i++) {
				map.put("box_info_id", id[i]);
				List boxlist = this.membersendboxService.getList(map);
				if (boxlist != null && boxlist.size() > 0 && id != null) {
						Membersendbox sendbox = this.membersendboxService.get(id[i]);
						// 存入回收站的对象
						Membertrash trash = new Membertrash();
						trash.setBox_id(sendbox.getInfo_id());
						trash.setContent(sendbox.getContent());
						trash.setIn_date(sendbox.getIn_date());
						trash.setMess_type(sendbox.getMess_type());
						trash.setInfo_type("1");// 1表示发件箱
						trash.setSend_cust_id(sendbox.getGet_cust_id());
						trash.setCust_id(this.session_cust_id);// 用来判断是谁删除的信息
						trash.setGet_cust_id(sendbox.getCust_id());
						trash.setTitle(sendbox.getTitle());
						// 存入回收站表中
						this.membertrashService.insert(trash);
						// 删除发件箱信息
						this.membersendboxService.delete(id[i]);
				}
			}
			this.addActionMessage("发件箱信息已成功删除");
		}
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 当搜索发件人名称不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.cust_name_s)) {
			pageMap.put("cust_name", this.cust_name_s);
		}
		// 当搜索信件类型不为空时加入搜索条件
		if (this.cate_type_s != null && !this.cate_type_s.equals("")) {
			pageMap.put("mess_type", this.cate_type_s);
		}
		// 当搜索标题不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.title_s)) {
			pageMap.put("title", this.title_s);
		}
		// 当搜索类型不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.type_s)) {
			pageMap.put("mess_type", this.type_s);
		}
		// 当搜索开始时间与结束时间不为空时加入搜索时间
		if (!ValidateUtil.isRequired(this.starttime_s)
				&& !ValidateUtil.isRequired(this.endtime_s)) {
			pageMap.put("search_starttime", this.starttime_s);
			pageMap.put("search_endtime", this.endtime_s);
		}

		// 加载信件类型
		getcommpara();
		// 根据页面提交的条件找出信息总数
		int count = this.membersendboxService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		memberinboxList = this.membersendboxService.getList(pageMap);
		// 定义存放键值对的HASHMAP
		Map key_value_Map = new HashMap();
		// 定义信件属于哪一个模块
		Map paraMap = new HashMap();
		paraMap.put("para_code", para_code);
		// 取出相应模块的参数键值对
		List compara = this.commparaService.getList(paraMap);
		if (compara != null && compara.size() > 0) {
			// 定义存放值
			String para_value = "", para_key = "";
			// 存入系统参数表的HASHMAP
			Map comparaMap = new HashMap();
			for (int i = 0; i < compara.size(); i++) {
				comparaMap = (HashMap) compara.get(i);
				if (comparaMap.get("para_value") != null) {
					para_value = comparaMap.get("para_value").toString();
				}
				if (comparaMap.get("para_key") != null) {
					para_key = comparaMap.get("para_key").toString();
				}
				key_value_Map.put(para_value, para_key);
			}
		}
		// 判断信息列表是否为空
		if (memberinboxList != null && memberinboxList.size() > 0) {
			Map memMap = new HashMap();
			for (int j = 0; j < memberinboxList.size(); j++) {
				memMap = (HashMap) memberinboxList.get(j);
				if (memMap.get("mess_type") != null) {
					String cate_id = memMap.get("mess_type").toString();
					if (key_value_Map.get(cate_id) != null) {
						String cate_name = key_value_Map.get(cate_id).toString();
						memMap.put("mess_type", cate_name);
						memberinboxList.set(j, memMap);
					}
				}
			}
		}
		return INPUT;
	}

	/**
	 * @MethodDescribe 方法描述 根据搜索条件列出信息列表 回收站
	 * @author 创建人 林俊钦
	 * @date 创建日期 Aug 8, 2011 9:24:30 AM
	 */
	public String reindex() throws Exception {
		// 判断用户是不是删除的操作
		if (this.trash != null && !this.trash.equals("")) {
			String id = this.memberinbox.getInfo_id();
			id = id.replace(" ", "");
			this.membertrashService.delete(id);
			this.addActionMessage("删除回收站信息成功");
		}
		Map pageMap = new HashMap();
		// 操作者为会员则默认加入搜索条件
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		// 当搜索信件类型不为空时加入搜索条件
		if (this.cate_type_s != null && !this.cate_type_s.equals("")) {
			pageMap.put("mess_type", this.cate_type_s);
		}
		// 当搜索标题不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.title_s)) {
			pageMap.put("title", this.title_s);
		}

		// 当搜索发件人名称不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.send_name_s)) {
			String name = this.send_name_s;
			Map nameMap = new HashMap();
			nameMap.put("cust_name", name);
			List namelist = this.memberService.getList(nameMap);
			if (namelist != null && namelist.size() > 0) {
				Map listMap = new HashMap();
				listMap = (HashMap) namelist.get(0);
				if (listMap.get("cust_id") != null) {
					pageMap.put("send_cust_id", listMap.get("cust_id")
							.toString());
				}
			}
		}
		// 当搜索收件人名称不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.get_name_s)) {
			String name = this.get_name_s;
			Map nameMap = new HashMap();
			nameMap.put("cust_name", name);
			List namelist = this.memberService.getList(nameMap);
			if (namelist != null && namelist.size() > 0) {
				Map listMap = new HashMap();
				listMap = (HashMap) namelist.get(0);
				if (listMap.get("cust_id") != null) {
					pageMap.put("get_cust_id", listMap.get("cust_id")
							.toString());
				}
			}
		}

		// 当搜索类型不为空时加入搜索条件
		if (!ValidateUtil.isRequired(this.type_s)) {
			pageMap.put("mess_type", this.type_s);
		}
		// 当搜索开始时间与结束时间不为空时加入搜索时间
		if (!ValidateUtil.isRequired(this.starttime_s)
				&& !ValidateUtil.isRequired(this.endtime_s)) {
			pageMap.put("search_starttime", this.starttime_s);
			pageMap.put("search_endtime", this.endtime_s);
		}
		// 加载信件类型
		getcommpara();
		// 根据页面提交的条件找出信息总数
		int count = this.membertrashService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		memberinboxList = this.membertrashService.getList(pageMap);
		// 定义存放键值对的HASHMAP
		Map key_value_Map = new HashMap();
		// 定义信件属于哪一个模块
		Map paraMap = new HashMap();
		paraMap.put("para_code", para_code);
		// 取出相应模块的参数键值对
		List compara = this.commparaService.getList(paraMap);
		if (compara != null && compara.size() > 0) {
			// 定义存放值
			String para_value = "", para_key = "";
			// 存入系统参数表的HASHMAP
			Map comparaMap = new HashMap();
			for (int i = 0; i < compara.size(); i++) {
				comparaMap = (HashMap) compara.get(i);
				if (comparaMap.get("para_value") != null) {
					para_value = comparaMap.get("para_value").toString();
				}
				if (comparaMap.get("para_key") != null) {
					para_key = comparaMap.get("para_key").toString();
				}
				key_value_Map.put(para_value, para_key);
			}
		}
		// 判断信息列表是否为空
		if (memberinboxList != null && memberinboxList.size() > 0) {
			String cate_id = "", cate_name = "", send_cust_id = "", get_cust_id = "";
			Map memMap = new HashMap();
			for (int j = 0; j < memberinboxList.size(); j++) {
				// 更换信件类型名称
				memMap = (HashMap) memberinboxList.get(j);
				if (memMap.get("mess_type") != null) {
					cate_id = memMap.get("mess_type").toString();
					if (key_value_Map.get(cate_id) != null) {
						cate_name = key_value_Map.get(cate_id).toString();
						memMap.put("mess_type", cate_name);
					}
				}
				// 更换发件箱名称
				if (memMap.get("send_cust_id") != null) {
					send_cust_id = memMap.get("send_cust_id").toString();
					Member mem = this.memberService.get(send_cust_id);
					if (mem != null) {
						if (mem.getCust_name() != null) {
							send_cust_id = mem.getCust_name();
							memMap.put("send_cust_id", send_cust_id);
						}
					}
				}
				// 更换发件箱名称
				if (memMap.get("get_cust_id") != null) {
					get_cust_id = memMap.get("get_cust_id").toString();
					Member mem = this.memberService.get(get_cust_id);
					if (mem != null) {
						if (mem.getCust_name() != null) {
							send_cust_id = mem.getCust_name();
							memMap.put("get_cust_id", send_cust_id);
						}
					}
				}
				memberinboxList.set(j, memMap);
			}
		}
		return INPUT;
	}

	/**
	 * 方法描述：根据主键找出会员收件信息表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String type = "", send_name = "", cust_name = "";
		// 重新实例化查看详细信件内容视屏
		// 把读的信件改变成已读状态
		this.memberinbox.setIs_read(isread);
		this.memberinboxService.update(memberinbox);
		if (memberinbox != null) {
			// 根据发件人ID改变成名称
			if (memberinbox.getCust_id() != null) {
				String cust_id = "";
				cust_id = memberinbox.getCust_id();
				Member mem = this.memberService.get(cust_id);
				if (mem != null && mem.getCust_name() != null) {
						cust_name = mem.getCust_name();
				}
			}
			// 根据收件人ID改变成名称
			if (memberinbox.getSend_cust_id() != null) {
				String send_id = "";
				send_id = memberinbox.getSend_cust_id();
				Member send_mem = this.memberService.get(send_id);
				if (send_mem != null && send_mem.getCust_name() != null) {
						send_name = send_mem.getCust_name();
				}
			}
		}
		this.memberinbox.setCust_id(send_name);
		this.memberinbox.setSend_cust_id(cust_name);
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出会员发件信息表信息查看
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sendview() throws Exception {
		// 重新实例化查看详细信件内容视屏
		if (!ValidateUtil.isRequired(this.memberinbox.getInfo_id())) {
			String id = this.memberinbox.getInfo_id();
			// 找出对应发件箱中的数据对象
			Membersendbox sendbox = this.membersendboxService.get(id);
			if (sendbox != null) {
				// 根据发件人ID改成名称
				if (sendbox.getCust_id() != null) {
					String cust_id = sendbox.getCust_id();
					Member mem = this.memberService.get(cust_id);
					if (mem != null && mem.getCust_name() != null) {
							String cust_name = mem.getCust_name();
							this.memberinbox.setCust_id(cust_name);
					}
				}
				if (sendbox.getMess_type() != null) {
					String mess_type = sendbox.getMess_type();
					this.memberinbox.setMess_type(mess_type);
				}
				if (sendbox.getTitle() != null) {
					String title = sendbox.getTitle();
					this.memberinbox.setTitle(title);
				}
				if (sendbox.getContent() != null) {
					String content = sendbox.getContent();
					this.memberinbox.setContent(content);
				}
				// 根据收件人ID改成名称
				if (sendbox.getGet_cust_id() != null) {
					String get_cust_id = sendbox.getGet_cust_id();
					Member send_mem = this.memberService.get(get_cust_id);
					if (send_mem != null && send_mem.getCust_name() != null) {
							String send_name = send_mem.getCust_name();
							this.memberinbox.setSend_cust_id(send_name);
					}
				}
				if (sendbox.getIn_date() != null) {
					String in_date = sendbox.getIn_date();
					this.memberinbox.setIn_date(in_date);
				}
			}
		}
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：根据主键找出会员发件信息表信息查看
	 * 
	 * @return
	 * @throws Exception
	 */
	public String trashview() throws Exception {
		// 判断id的值是否符合条件，不符合的话返回到列表
		String rbtid = this.memberinbox.getInfo_id();
		if (!ValidateUtil.isDigital(rbtid)) {
			return list();
		}
		if (!ValidateUtil.isRequired(this.memberinbox.getInfo_id())) {
			String id = this.memberinbox.getInfo_id();
			// 找出对应发件箱中的数据对象
			Membertrash trashbox = this.membertrashService.get(id);
			if (trashbox != null) {
				// 根据发件人ID改成名称
				if (trashbox.getSend_cust_id() != null) {
					String send_cust_id = trashbox.getSend_cust_id();
					Member mem = this.memberService.get(send_cust_id);
					if (mem != null && mem.getCust_name() != null) {
							String cust_name = mem.getCust_name();
							this.memberinbox.setCust_id(cust_name);
					}
				}
				if (trashbox.getMess_type() != null) {
					String mess_type = trashbox.getMess_type();
					this.memberinbox.setMess_type(mess_type);
				}
				if (trashbox.getTitle() != null) {
					String title = trashbox.getTitle();
					this.memberinbox.setTitle(title);
				}
				if (trashbox.getContent() != null) {
					String content = trashbox.getContent();
					this.memberinbox.setContent(content);
				}
				// 根据收件人ID改成名称
				if (trashbox.getGet_cust_id() != null) {
					String get_cust_id = trashbox.getGet_cust_id();
					Member send_mem = this.memberService.get(get_cust_id);
					if (send_mem != null && send_mem.getCust_name() != null) {
							String send_name = send_mem.getCust_name();
							this.memberinbox.setSend_cust_id(send_name);
					}
				}
				if (trashbox.getIn_date() != null) {
					String in_date = trashbox.getIn_date();
					this.memberinbox.setIn_date(in_date);
				}
			}
		}
		return goUrl(VIEW);
	}

	public void getcommpara(){
		Map map = new HashMap();
		map.put("para_code", para_code);
		commparalist = commparaService.getList(map);
	}
	/**
	 * @return the MemberinboxList
	 */
	public List getMemberinboxList() {
		return memberinboxList;
	}

	/**
	 * @param memberinboxList
	 *            the MemberinboxList to set
	 */
	public void setMemberinboxList(List memberinboxList) {
		this.memberinboxList = memberinboxList;
	}

	/**
	 * @return the memberinbox
	 */
	public Memberinbox getMemberinbox() {
		return memberinbox;
	}

	/**
	 * @param Memberinbox
	 *            the memberinbox to set
	 */
	public void setMemberinbox(Memberinbox memberinbox) {
		this.memberinbox = memberinbox;
	}

	/**
	 * @return the commparalist
	 */
	public List getCommparalist() {
		return commparalist;
	}

	/**
	 * @param commparalist
	 *            the commparalist to set
	 */
	public void setCommparalist(List commparalist) {
		this.commparalist = commparalist;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the cust_name
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * @param cust_name
	 *            the cust_name to set
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	/**
	 * @return the send_name
	 */
	public String getSend_name() {
		return send_name;
	}

	/**
	 * @param send_name
	 *            the send_name to set
	 */
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}

	/**
	 * @return the box_type
	 */
	public String getBox_type() {
		return box_type;
	}

	/**
	 * @param box_type
	 *            the box_type to set
	 */
	public void setBox_type(String box_type) {
		this.box_type = box_type;
	}

	/**
	 * @return the trash
	 */
	public String getTrash() {
		return trash;
	}

	/**
	 * @param trash
	 *            the trash to set
	 */
	public void setTrash(String trash) {
		this.trash = trash;
	}

	/**
	 * @return the sendel
	 */
	public String getSendel() {
		return sendel;
	}

	/**
	 * @param sendel
	 *            the sendel to set
	 */
	public void setSendel(String sendel) {
		this.sendel = sendel;
	}


	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if (memberinbox == null) {
			memberinbox = new Memberinbox();
		}
		String id = memberinbox.getInfo_id();
		if (!ValidateUtil.isDigital(id)) {
			memberinbox = this.memberinboxService.get(id);
		}
	}

	public String getCate_type_s() {
		return cate_type_s;
	}

	public void setCate_type_s(String cate_type_s) {
		this.cate_type_s = cate_type_s;
	}

	public String getTitle_s() {
		return title_s;
	}

	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}

	public String getSend_name_s() {
		return send_name_s;
	}

	public void setSend_name_s(String send_name_s) {
		this.send_name_s = send_name_s;
	}

	public String getIsread_s() {
		return isread_s;
	}

	public void setIsread_s(String isread_s) {
		this.isread_s = isread_s;
	}

	public String getType_s() {
		return type_s;
	}

	public void setType_s(String type_s) {
		this.type_s = type_s;
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

	public String getCust_name_s() {
		return cust_name_s;
	}

	public void setCust_name_s(String cust_name_s) {
		this.cust_name_s = cust_name_s;
	}

	public String getGet_name_s() {
		return get_name_s;
	}

	public void setGet_name_s(String get_name_s) {
		this.get_name_s = get_name_s;
	}

}
