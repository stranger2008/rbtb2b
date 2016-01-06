/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.timerTask
 * FileName: SendSubscribeJobAction.java 
 */
package com.rbt.timerTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.rbt.common.util.MailUtil;
import com.rbt.function.ChannelFuc;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Member;
import com.rbt.model.Memberinbox;
import com.rbt.model.Subscribeinfo;
import com.rbt.service.IBuyService;
import com.rbt.service.IEmailtemplateService;
import com.rbt.service.IMemberService;
import com.rbt.service.IMemberinboxService;
import com.rbt.service.ISubscribeService;
import com.rbt.service.ISubscribeinfoService;
import com.rbt.service.ISupplyService;

/**
 * @function 功能  执行商机订阅自动发送
 * @author  创建人  胡惜坤
 * @date  创建日期  2011-09-24
 */
public class SendSubscribeJob extends CreateSpringContext implements Job {

	ISubscribeService subscribeService = (ISubscribeService) getContext().getBean("subscribeService");//订阅
	IBuyService buyService = (IBuyService) getContext().getBean("buyService");//求购
	ISupplyService supplyService = (ISupplyService) getContext().getBean("supplyService");//供应
	IEmailtemplateService emailtemplateService = (IEmailtemplateService) getContext().getBean("emailtemplateService");//邮件模版
	ISubscribeinfoService subscribeinfoService = (ISubscribeinfoService) getContext().getBean("subscribeinfoService");//订阅发送历史记录
	IMemberinboxService memberinboxService = (IMemberinboxService) getContext().getBean("memberinboxService");//站内信件
	IMemberService memberService = (IMemberService)getContext().getBean("memberService");
	public String sendtype_inner="1";//1：站内信
	public String sendtype_email="0";//0：邮件
	public String infotype_buy="1";//1:求购
	public String infotype_supply="0";//0:供应
	public String infotitle="商机订阅";
	public List emailtemplateList;//记录会员邮件发送模板信息信息集
	public String url=SysconfigFuc.getSysValue("cfg_basehost");
	private static final String TEMP_CODE = "subscribe_info";
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String ifflageString="";//获取系统配置是否启用发送商机订阅的值
			List subscibeList=new ArrayList();
			ifflageString=SysconfigFuc.getSysValue("cfg_qtzSendSubscribe");
			//根据系统配置表取值，如果cfg_qtzSendSubscribe的值为0：表示启用发送商机订阅；1：不启用发送商机订阅
			if(ifflageString.equals("0"))
			{
			    //填写发送商机订阅信息的方法
				subscibeList=getsubslist();
				for(int i=0; i<subscibeList.size();i++)
				{
					HashMap sMap=(HashMap)subscibeList.get(i);
					if(sMap!=null&&sMap.get("send_type")!=null&&!"".equals(sMap.get("send_type").toString()))
					{
						String strsendtype="",strsub_id="",strcust_id="",strkeyword="",strcat_attr="",strarea_attr="",strperiod="",strinfo_type="",stremail="";
						strsendtype=sMap.get("send_type").toString();
						if(sMap.get("sub_id")!=null)strsub_id=sMap.get("sub_id").toString();//获取订阅ID
						if(sMap.get("keyword")!=null)strkeyword=sMap.get("keyword").toString();//获取订阅关键字
						if(sMap.get("cat_attr")!=null)strcat_attr=sMap.get("cat_attr").toString();//获取订阅分类
						if(sMap.get("area_attr")!=null)strarea_attr=sMap.get("area_attr").toString();//获取订阅地区
						if(sMap.get("period")!=null)strperiod=sMap.get("period").toString();//获取发送频率
						if(sMap.get("info_type")!=null)strinfo_type=sMap.get("info_type").toString();//获取订阅类型
						if(sMap.get("cust_id")!=null)//获取订阅客户ID
						{
							strcust_id=sMap.get("cust_id").toString();
							stremail=getAlluserEmail(strcust_id);//获取订阅的邮箱地址
						}
						//0：发送邮件
						if(strsendtype.equals("0"))
						{
							if(stremail!=null&&!stremail.equals(""))
							{
								sendSubscribeEmail(strinfo_type,strkeyword,strcat_attr,strarea_attr,strperiod,stremail,strcust_id,strsub_id);
							}
						}//1：发送站内信件
						else if(strsendtype.equals("1"))
						{
							sendSubscribeInnerLetter(strinfo_type,strkeyword,strcat_attr,strarea_attr,strperiod,strcust_id,strsub_id);
						}
					}
					
				}
			}
			System.out .println("==============================================商机订阅发送结束了！=================================================");
		} catch (Exception e) {
			System.err.println("定时发送商机订阅出现异常情况");
		}
	}
	//根据用户ID获取用户邮箱地址
	public String getAlluserEmail(String cust_id)
	{
		String stremail="";
		Member member=new Member();
		member=memberService.get(cust_id);
		if(member!=null&&member.getEmail()!=null)
		{
			stremail=member.getEmail();
		}
		return stremail;
	}
	/**
	 * 发送订阅邮件
	 * @param infotype 信息类型：0：供应 1：求购
	 * @param keyword 订阅关键字
	 * @param strcat_attr 订阅分类
	 * @param strarea_attr订阅地区
	 * @param dates 发送频率
	 * @param email 发送邮件地址
	 * @param cust_id 订阅者ID
	 * @param sub_id 订阅ID
	 */
	public void sendSubscribeEmail(String infotype,String keyword,String strcat_attr,String strarea_attr,String dates,String email,String cust_id,String sub_id )
	{
		String strinfoString="";//获取订阅信息内容
		String content="";//邮件内容
		String temp_con = "";// 模板内容
		strinfoString=getAllInfo(infotype,keyword,strcat_attr,strarea_attr,dates,cust_id,sub_id);
		if(!"".equals(strinfoString))
		{
			// 邮件发送实例
			MailUtil mail = new MailUtil();
			// 选择发送邮箱模板
			HashMap map = new HashMap();
			map.put("temp_code", TEMP_CODE);
			emailtemplateList = emailtemplateService.getList(map);
			if (emailtemplateList != null && emailtemplateList.size() > 0) {
				HashMap value  = (HashMap) emailtemplateList.get(0);
				if(value!=null&&value.get("temp_con")!=null)
				{
					temp_con = value.get("temp_con").toString();
				}
			}
			// 替换邮件模板
			if(temp_con!=null&&!"".equals(temp_con))
			{
				content = temp_con.replace("{info_content}", strinfoString);
			}
			// 发送邮件
			mail.sendMail(infotitle, content, email);
			//加入发送历史
			//addsubscribeinfo(cust_id,infotype,strinfoString,sub_id);
		}
	}
	/**
	 * 发送订阅站内信件
	 * @param infotype 信息类型：0：供应 1：求购
	 * @param keyword 订阅关键字
	 * @param strcat_attr 订阅分类
	 * @param strarea_attr订阅地区
	 * @param dates 发送频率
	 * @param cust_id 订阅者ID
	 * @param sub_id 订阅ID
	 */
	public void sendSubscribeInnerLetter(String infotype,String keyword,String strcat_attr,String strarea_attr,String dates,String cust_id,String sub_id )
	{
		String strinfoString="";//获取订阅信息内容
		strinfoString=getAllInfo(infotype,keyword,strcat_attr,strarea_attr,dates,cust_id,sub_id);
		if(!"".equals(strinfoString))
		{
			Memberinbox memberinbox=new Memberinbox();
			memberinbox.setContent(strinfoString);
			memberinbox.setCust_id(cust_id);
			memberinbox.setTitle(infotitle);
			memberinbox.setIs_read("0");
			memberinbox.setSend_cust_id("0");
			memberinbox.setMess_type("5");
			memberinboxService.insert(memberinbox);
			//加入发送历史
			//addsubscribeinfo(cust_id,infotype,strinfoString,sub_id);
		}
		
	}
	/**
	 * 添加订阅发送历史信息
	 * @param user_id 订阅者ID
	 * @param infotype 订阅类型
	 * @param content 订阅内容
	* @param sub_id 订阅ID
	 */
	public void addsubscribeinfo(String user_id,String infotype,String content,String sub_id)
	{
		Subscribeinfo subscribeinfomodel=new Subscribeinfo();
		subscribeinfomodel.setCust_id(user_id);
		subscribeinfomodel.setInfo_attr(content);
		subscribeinfomodel.setInfo_type(infotype);
		subscribeinfomodel.setSub_id(sub_id);
		subscribeinfoService.insert(subscribeinfomodel);
	}
	/**
	 * 获取订阅信息串
	 *  * @param infotype 信息类型：0：供应 1：求购
	 * @param keyword 订阅关键字
	 * @param strcat_attr 订阅分类
	 * @param strarea_attr订阅地区
	 * @return <a href='xxxx'>xxxxxx</a><br/><a href='xxxx'>xxxxxx</a><br/>....
	 */
	public String getAllInfo(String infotype,String keyword,String strcat_attr,String strarea_attr,String dates,String cust_id,String sub_id)
	{
	  HashMap infoMap=new HashMap ();
	  String strInfoBuilder="";
	  String strinfoid="";
	  String dateString="";//获取处理后的时间
	  if(keyword!=null&&!"".equals(keyword)) infoMap.put("title", keyword);
	  if(strcat_attr!=null&&!"".equals(strcat_attr)) infoMap.put("cat_attr", strcat_attr);
	  if(strarea_attr!=null&&!"".equals(strarea_attr)) infoMap.put("area_attr", strarea_attr);
	  infoMap.put("info_state", "1");
	  if(dates!=null&&!dates.equals(""))
	  {
		  //时间处理
		  Integer getdates=(0-Integer.parseInt(dates));
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, getdates);
		  DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  dateString=dateformat.format(cal.getTime());
		  if(dateString!=null&&!"".equals(dateString))
		  {
			infoMap.put("starttime", dateString);
		  }
	  }
	  //获取订阅信息列表
	  List infoList=new ArrayList();
	  if(infotype.equals(infotype_supply))
	  {
		  //供应列表
		  infoList=supplyService.getList(infoMap);
		  
	  }
	  else if(infotype.equals(infotype_buy))
	  {
		  //求购列表
		  infoList=buyService.getList(infoMap);
	  }
	  //过滤已经发送信息，不重复发送信息
	  infoList=searchsendinfo(infoList,sub_id,infotype);
	  if(infoList!=null&&infoList.size()>0)
	  {
		  for(int i=0;i<infoList.size();i++)
		  {
			  HashMap getinfosMap=(HashMap)infoList.get(i);
			  String info_id="",title="",indate="",strurl="";
			  if(getinfosMap!=null)
			  {
				  
				  if(getinfosMap.get("title")!=null)title=(i+1)+". "+getinfosMap.get("title").toString();
				  if(getinfosMap.get("in_date")!=null)indate=getinfosMap.get("in_date").toString();
				  if(infotype.equals(infotype_supply))//供应
				  {
					  if(getinfosMap.get("supply_id")!=null)info_id=getinfosMap.get("supply_id").toString();
					  strurl=url+ChannelFuc.getArticleUrl("supply", info_id, indate);
				  }
				  else if(infotype.equals(infotype_buy))//求购
				  {
					  if(getinfosMap.get("buy_id")!=null)info_id=getinfosMap.get("buy_id").toString();
					  strurl=url+ChannelFuc.getArticleUrl("buy", info_id, indate);
				  }
				  strinfoid+=info_id+",";//获取发送的信息标识串
				  /*
				   * 一下开始绑定信息内容，格式可以自己定义
				   */
				  strInfoBuilder+="<a target='_blank' href='";
				  strInfoBuilder+=strurl;//点击链接地址
				  strInfoBuilder+="'>";
				  strInfoBuilder+=title;//标题文字
				  strInfoBuilder+="</a><br/>";
			  }
		  }
	  }
	   //加入发送历史
	  if(strInfoBuilder!=null&&!"".equals(strInfoBuilder))
	  {
		  strinfoid=strinfoid.replace(" ", "");
		  strinfoid=strinfoid.substring(0,(strinfoid.length()-1));
		  addsubscribeinfo(cust_id,infotype,strinfoid,sub_id);
	  }
	  return strInfoBuilder;	
	}
	/**
	 * 过滤已经发送的信息，防止重复发送同一条信息
	 * @param alList 已经发送的信息列表
	 * @param infotype 订阅信息类型
	 * @return
	 */
	public List searchsendinfo(List alList,String sub_id,String infotype)
	{
	  //获取发送信息记录列表
	  List subinfoList=new ArrayList();
	  List returnList=new ArrayList();
	  HashMap subMaps=new HashMap ();
	  subMaps.put("sub_id", sub_id);
	  subinfoList=subscribeinfoService.getList(subMaps);
	  String infoidattr="";
	  if(subinfoList.size()==0)
	  {
		  returnList=alList;
	  }
	  else
	  {
		  //获取所以已经发送的订阅ID串
		  for(int j=0;j<subinfoList.size();j++)
		  { 
			 
			 HashMap subsubinfo=(HashMap)subinfoList.get(j);
			 if(subsubinfo!=null&&subsubinfo.get("info_attr")!=null)
			 {
				 infoidattr+=subsubinfo.get("info_attr").toString()+",";
			 }
			 
		  }
		  //去掉最后一个逗号
		  if(infoidattr!=null)
		  {
			  infoidattr=infoidattr.substring(0,(infoidattr.length()-1));
		  }
		  String strinfoattr[]=infoidattr.split(",");//已经发送的ID串数组
		  //循环查找到所以已经获取的订阅信息，过滤那些已经发送过的信息
		  for(int z=0;z<alList.size();z++)
		  {
			 HashMap infosMap=(HashMap)alList.get(z);
			 String infoid="";
			 boolean flages=false;//如果flages为false表示该信息还没有发送过，否则表示已经发送过
			 if(infotype.equals(infotype_supply))//供应
			 {
				  if(infosMap.get("supply_id")!=null)infoid=infosMap.get("supply_id").toString();
			 }
			 else if(infotype.equals(infotype_buy))//求购
			 {
				  if(infosMap.get("buy_id")!=null)infoid=infosMap.get("buy_id").toString();
			 }
			 for(int zz=0;zz<strinfoattr.length;zz++)
			 {
				 if(infoid.equals(strinfoattr[zz]))
				 {
					 flages=true;
				 }
			 }
			 if(flages==false)
			 {
				 returnList.add(infosMap);
			 }
		  }
	  }
	  return returnList;
	}
	/**
	 * 获取订阅列表，根据信息发送类型，
	 * @param sendtype 0：邮件 1：站内信
	 * @return
	 */
	public List getsubslist( )
	{
		List subList=new ArrayList();
		HashMap subMap=new HashMap();
		subMap.put("enabled", 0);
		subList=subscribeService.getList(subMap);
		return subList;
	}
	
}
