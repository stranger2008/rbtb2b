/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberchconfigAction.java 
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
import com.rbt.model.Memberchconfig;
import com.rbt.service.IMemberchconfigService;
/**
 * @function 功能 记录会员企业站栏目配置信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Aug 26 13:24:50 CST 2011
 */
@Controller
public class MemberchconfigAction extends BaseAction  implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 记录会员企业站栏目配置信息对象
	 */
	public Memberchconfig memberchconfig;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IMemberchconfigService memberchconfigService;
	/*
	 * 记录会员企业站栏目配置信息信息集合
	 */
	public List memberchconfigList;
	/*
	 * 搜索字段
	 */
	public String ch_name_s;
	public String ch_code_s;
	public String is_dis_s;
	public String ch_type_s;
	/*
	 * 批量修改标识字段
	 */
	public String admin_memberchconfig_id;
	/*
	 * 批量修改标识字段
	 */
	 public String isort_no;
	
	/**
	 * 方法描述：返回新增记录会员企业站栏目配置信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增记录会员企业站栏目配置信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
	
		String ch_code = this.memberchconfig.getCh_code();
		if(!"".equals(ch_code)&&ch_code!=null){
			Map pageMap = new HashMap();
			pageMap.put("ch_code", ch_code);
			// 通过栏目ID号找出栏目信息
			List chList = this.memberchconfigService.getList(pageMap);
			if (chList != null && chList.size() > 0) {
				this.addFieldError("memberchconfig.ch_code", "栏目编号已存在,请重新输入");
			}
		}
		
		if("3".equals(this.memberchconfig.getCh_type())){
			this.addFieldError("memberchconfig.ch_type", "请选择栏目类型");
		}
		
		//字段验证
		super.commonValidateField(memberchconfig);
		if(super.ifvalidatepass){
			return add();
		}
		
		this.memberchconfigService.insert(memberchconfig);
		this.addActionMessage("新增企业站栏目配置成功");
		this.memberchconfig = null;
		return INPUT;
	}

	/**
	 * 方法描述：修改记录会员企业站栏目配置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		//字段验证
		super.commonValidateField(memberchconfig);
		if(super.ifvalidatepass){
			return view();
		}
		this.memberchconfigService.update(memberchconfig);
		this.addActionMessage("修改企业站栏目配置成功");
		return list();
	}
	/**
	 * 方法描述：删除记录会员企业站栏目配置信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.memberchconfig.getCh_id();
		id = id.replace(" ", "");
		this.memberchconfigService.delete(id);
		this.addActionMessage("删除企业站栏目配置成功");
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
		if(ch_name_s!=null && !ch_name_s.equals("")) pageMap.put("ch_name", ch_name_s);
		if(ch_code_s!=null && !ch_code_s.equals("")) pageMap.put("ch_code", ch_code_s);
		if(is_dis_s!=null && !is_dis_s.equals("")) pageMap.put("is_dis", is_dis_s);
		if(ch_type_s!=null && !ch_type_s.equals("")) pageMap.put("ch_type", ch_type_s);
		//过滤地区
		pageMap=super.areafilter(pageMap);
		//根据页面提交的条件找出信息总数
		int count=this.memberchconfigService.getCount(pageMap);
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		memberchconfigList = this.memberchconfigService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出记录会员企业站栏目配置信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		//判断id的值是否符合条件，不符合的话返回到列表
		String rbtid=this.memberchconfig.getCh_id();
		if(ValidateUtil.isDigital(rbtid)){
			return list();
		}	
		return goUrl(VIEW);
	}
	
	
	
	/**
	 * 方法描述：批量显示/不显示导航
	 * @return
	 * @throws Exception
	 */
	public String updateisshow() throws Exception {
		String chid = this.memberchconfig.getCh_id();
		String is_dis = this.memberchconfig.getIs_dis();
		chid = chid.replace(" ", "");
		String chidStr[] = chid.split(",");
		List chList = new ArrayList();
		if(chidStr.length>0){
			for(int i=0;i<chidStr.length;i++){
				Map linkMap = new HashMap();
				linkMap.put("is_dis", is_dis);
				linkMap.put("ch_id", chidStr[i]);
				chList.add(linkMap);
			}
		}
		this.memberchconfigService.updateisdis(chList);
		String tip = "";
		if(is_dis.equals("0")){
			tip = "显示栏目成功";
		}else if(is_dis.equals("1")){
			tip = "不显示栏目成功";
		}
		this.addActionMessage(tip);
		return list();
	}
	
	
	/**
	 * 方法描述：批量修改企业模板排序
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.admin_memberchconfig_id;
		String sort_no =isort_no;
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String tempidStr[] = id.split(",");
		String tempsortStr[] = sort_no.split(",");
		List sotrList = new ArrayList();
		if(tempidStr.length>0){
			for(int i=0;i<tempidStr.length;i++){
				Map sortMap = new HashMap();
				sortMap.put("sort_no", tempsortStr[i]);
				sortMap.put("ch_id", tempidStr[i]);
				sotrList.add(sortMap);
			}
		}
		this.memberchconfigService.updatesort_no(sotrList);
		this.addActionMessage("批量排序修改成功");
		return list();
	}
	
	/**
	 * @return the memberchconfig
	 */
	public Memberchconfig getMemberchconfig() {
		return memberchconfig;
	}
	/**
	 * @param Memberchconfig
	 *            the memberchconfig to set
	 */
	public void setMemberchconfig(Memberchconfig memberchconfig) {
		this.memberchconfig = memberchconfig;
	}
	
	/**
	 * @return the MemberchconfigList
	 */
	public List getMemberchconfigList() {
		return memberchconfigList;
	}
	/**
	 * @param memberchconfigList
	 *  the MemberchconfigList to set
	 */
	public void setMemberchconfigList(List memberchconfigList) {
		this.memberchconfigList = memberchconfigList;
	}
	public String getAdmin_memberchconfig_id() {
		return admin_memberchconfig_id;
	}
	public void setAdmin_memberchconfig_id(String admin_memberchconfig_id) {
		this.admin_memberchconfig_id = admin_memberchconfig_id;
	}
	public String getIsort_no() {
		return isort_no;
	}
	public void setIsort_no(String isort_no) {
		this.isort_no = isort_no;
	}
	/**
	 * @Method Description : 当进入方法后初始化对象
	 * @author : 林俊钦
	 * @date : Nov 8, 2011 2:36:50 PM
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(memberchconfig == null){
			memberchconfig = new Memberchconfig();
		}
		String id = memberchconfig.getCh_id();
		if(!ValidateUtil.isDigital(id)){
			memberchconfig = this.memberchconfigService.get(id);
		}
	}
	

}

