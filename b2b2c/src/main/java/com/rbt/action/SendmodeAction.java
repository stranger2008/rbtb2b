/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: SendmodeAction.java 
 */
package com.rbt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rbt.action.BaseAction;
import com.rbt.common.util.JsonUtil;
import com.rbt.common.util.RandomStrUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Sendmode;
import com.rbt.service.ISendmodeService;
import com.opensymphony.xwork2.Preparable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 配送方式表action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Fri Mar 23 09:55:49 CST 2012
 */
@Controller
public class SendmodeAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 配送方式表对象
	 */
	private Sendmode sendmode;
	/*
	 * 配送方式表业务层接口
	 */
	@Autowired
	private ISendmodeService sendmodeService;
	/*
	 * 配送方式表信息集合
	 */
	public List sendmodeList;
	public String smode_name_s;
	public String is_insured_s;
	public String rate_s;
	public String mix_insured_s;
	public String max_insured_s;
	public String reach_pay_s;
	public String sendmode_sortno_id;
	public String isort_no;
	/**
	 * @return the sendmode
	 */
	public Sendmode getSendmode() {
		return sendmode;
	}
	/**
	 * @param Sendmode
	 *            the sendmode to set
	 */
	public void setSendmode(Sendmode sendmode) {
		this.sendmode = sendmode;
	}
	
	/**
	 * 方法描述：返回新增配送方式表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增配送方式表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		sendmode.setSmode_id(RandomStrUtil.getNumberRand());
		super.commonValidateField(sendmode);
		if(super.ifvalidatepass){
			return add();
		}
	    if(sendmode.getRate()==null||sendmode.getRate().equals(""))
	    {
	    	sendmode.setRate("0");
	    }
	    if(sendmode.getMax_insured()==null||sendmode.getMax_insured().equals(""))
	    {
	    	sendmode.setMax_insured("0");
	    }
	    if(sendmode.getMix_insured()==null||sendmode.getMix_insured().equals(""))
	    {
	    	sendmode.setMix_insured("0");
	    }
	    if(sendmode.getSort_no()==null||sendmode.getSort_no().equals(""))
	    {
	    	sendmode.setSort_no("0");
	    }
		this.sendmodeService.insert(sendmode);
		this.addActionMessage("新增配送方式表成功！");
		this.sendmode = null;
		return INPUT;
	}
	/**
	 * 方法描述：批量排序
	 * @return
	 * @throws Exception
	 */
	public String updatesortno() throws Exception { 
		String id = this.sendmode_sortno_id;
		String sort_no =isort_no;
		id = id.replace(" ", "");
		sort_no=sort_no.replace(" ", "");
		String paraidStr[] = id.split(",");
		String parasortStr[] = sort_no.split(",");
		List paraList = new ArrayList();
		if(paraidStr.length>0){
			for(int i=0;i<paraidStr.length;i++){	
				Map sortMap = new HashMap();
				sortMap.put("sort_no", parasortStr[i]);
				sortMap.put("smode_id", paraidStr[i]);
				paraList.add(sortMap);
			}
		}
		this.sendmodeService.updatesort_no(paraList);
		this.addActionMessage("参数批量排序成功");
		return list();
	}
	/**
	 * 方法描述：修改配送方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
			super.commonValidateField(sendmode);
		if(super.ifvalidatepass){
			return view();
		}
		if(sendmode.getRate()==null||sendmode.getRate().equals(""))
	    {
	    	sendmode.setRate("0");
	    }
	    if(sendmode.getMax_insured()==null||sendmode.getMax_insured().equals(""))
	    {
	    	sendmode.setMax_insured("0");
	    }
	    if(sendmode.getMix_insured()==null||sendmode.getMix_insured().equals(""))
	    {
	    	sendmode.setMix_insured("0");
	    }
	    if(sendmode.getSort_no()==null||sendmode.getSort_no().equals(""))
	    {
	    	sendmode.setSort_no("0");
	    }
		this.sendmodeService.update(sendmode);
		this.addActionMessage("修改配送方式表成功！");
		return list();
	}
	/**
	 * 方法描述：删除配送方式表信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.sendmode.getSmode_id();
		id = id.replace(" ", "");
		this.sendmodeService.delete(id);
		this.addActionMessage("删除配送方式表成功！");
		return list();
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : May 17, 2012 4:48:58 PM
	 * @Method Description : 找出所有的配送方式
	 */
	public void modeList() throws IOException{
		//AJAX获取操作获取运费
		HttpServletResponse response = getResponse();
		HttpServletRequest request = getRequest();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map pageMap = new HashMap();	
		sendmodeList = this.sendmodeService.getList(pageMap);
		JsonUtil json=new JsonUtil();
		String shopStr = json.list2json(sendmodeList);
		out.print(shopStr);	
	}
	
	
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String list() throws Exception {
		Map pageMap = new HashMap();
		
		if(smode_name_s!=null&&!"".equals(smode_name_s))
		{
			pageMap.put("smode_name", smode_name_s);
		}
		if(is_insured_s!=null&&!"".equals(is_insured_s))
		{
			pageMap.put("is_insured", is_insured_s);
		}
		if(rate_s!=null&&!"".equals(rate_s))
		{
			pageMap.put("rate", rate_s);
		}
		if(mix_insured_s!=null&&!"".equals(mix_insured_s))
		{
			pageMap.put("mix_insured", mix_insured_s);
		}
		if(max_insured_s!=null&&!"".equals(max_insured_s))
		{
			pageMap.put("max_insured", max_insured_s);
		}
		if(reach_pay_s!=null&&!"".equals(reach_pay_s))
		{
			pageMap.put("reach_pay", reach_pay_s);
		}
		
		//根据页面提交的条件找出信息总数
		int count=this.sendmodeService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		sendmodeList = this.sendmodeService.getList(pageMap);
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出配送方式表信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		String id = this.sendmode.getSmode_id();
		if(id==null || id.equals("")){
			sendmode = new Sendmode();
		}else{
			sendmode = this.sendmodeService.get(id);
		}
		return goUrl(VIEW);
	}
	/**
	 * @return the SendmodeList
	 */
	public List getSendmodeList() {
		return sendmodeList;
	}
	
	public String getSmode_name_s() {
		return smode_name_s;
	}
	public void setSmode_name_s(String smode_name_s) {
		this.smode_name_s = smode_name_s;
	}
	public String getIs_insured_s() {
		return is_insured_s;
	}
	public void setIs_insured_s(String is_insured_s) {
		this.is_insured_s = is_insured_s;
	}
	public String getRate_s() {
		return rate_s;
	}
	public void setRate_s(String rate_s) {
		this.rate_s = rate_s;
	}
	public String getMix_insured_s() {
		return mix_insured_s;
	}
	public void setMix_insured_s(String mix_insured_s) {
		this.mix_insured_s = mix_insured_s;
	}
	public String getMax_insured_s() {
		return max_insured_s;
	}
	public void setMax_insured_s(String max_insured_s) {
		this.max_insured_s = max_insured_s;
	}
	public String getReach_pay_s() {
		return reach_pay_s;
	}
	public void setReach_pay_s(String reach_pay_s) {
		this.reach_pay_s = reach_pay_s;
	}
	/**
	 * @param sendmodeList
	 *  the SendmodeList to set
	 */
	public void setSendmodeList(List sendmodeList) {
		this.sendmodeList = sendmodeList;
	}
	
	public void prepare() throws Exception {
		super.saveRequestParameter();
		if(sendmode == null){
			sendmode = new Sendmode();
		}
		String id = this.sendmode.getSmode_id();
		if(!this.validateFactory.isDigital(id)){
			sendmode = this.sendmodeService.get(id);
		}
	}

}

