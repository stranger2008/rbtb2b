/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.webaction
 * FileName: WebVoteAction.java 
 */
package com.rbt.webaction;


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rbt.service.IVoteService;
import com.rbt.service.IVote_logService;
import com.rbt.service.IVote_optionService;
import com.rbt.function.SysconfigFuc;
import com.rbt.model.Vote;
import com.rbt.model.Vote_log;
import com.rbt.model.Vote_option;

/**
 * @function 功能 在线调查action类
 * @author 创建人 胡惜坤
 * @date 创建日期 11 3, 2011
 */
@Controller
public class WebvoteAction extends WebbaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 序列化
	 */
	/*
	 * 注入voteService
	 */
	@Autowired
	public IVoteService voteService;
	@Autowired
	public IVote_optionService vote_optionService;
	@Autowired
	public IVote_logService vote_logService;
	/*
	 * 在线调查对象
	 */
	public Vote vote;
	/*
	 * 在线调查列表
	 */
	public List voteList;

	/*
	 * Vote_option在线选项
	 */
	public Vote_option vote_option;

	/*
	 * 在线选项列表
	 */
	public List vote_optionList;

	/*
	 * 是否显示字段
	 */
	public String is_multi;
	/*
	 * 搜索字段
	 */
	public String vote_title_s;
	/*
	 * 搜索字段
	 */
	public String vote_is_multi;
	/*
	 * 搜索字段
	 */
	public String is_multi_s;
	/*
	 * 开始时间
	 */
	public String starttime;
	/*
	 * 结束时间
	 */
	public String endtime;
	public String vote_id;
	public String chkbtvalue;
	public String rbtvalue;
	public String isshowvote="1";//是否显示投票1：显示，2：不显示,//显示投票是否进行中，未开始，已结束
	public String webtitleString="";

	/**
	 * 方法描述：根据主键找出在线调查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if (vote_id != null && !vote_id.equals("")) {
			vote = this.voteService.get(vote_id);
			String starttimeString="",endtimeString="";
			if(vote.getStart_date()!=null&&!vote.getStart_date().equals(""))
			{
				starttimeString=vote.getStart_date();
			}
			if(vote.getEnd_date()!=null&&!vote.getEnd_date().equals(""))
			{
				endtimeString=vote.getEnd_date();
			}
			isshowvote=compare_date(starttimeString,endtimeString);//是否显示投票选项的标识
			Map voteoptionMap = new HashMap();
			voteoptionMap.put("vote_id", vote_id);
			Integer allpernuml=0;//统计所有投票的次数
			List vote_optionList1 = this.vote_optionService.getList(voteoptionMap);
			//统计所有投票的总票数
			if (vote_optionList1 != null && vote_optionList1.size() > 0) {
				HashMap aMap1 = new HashMap();
				for (int i = 0; i < vote_optionList1.size(); i++) {
					aMap1 = (HashMap) vote_optionList1.get(i);
					String countString="0";
					if(aMap1.get("option_count")!=null&&!aMap1.get("option_count").equals(""))
					{
						countString=aMap1.get("option_count").toString();
					}
					allpernuml+=Integer.parseInt(countString);//统计所有投票的总票数
				}
			}
			vote_optionList=new ArrayList();
			//构造一个新的列表信息，加入"pers"百分比为前台绑定
			if (vote_optionList1 != null && vote_optionList1.size() > 0) {
				HashMap aMap2 = new HashMap();
				for (int i = 0; i < vote_optionList1.size(); i++) {
					String result="0.00";
					Integer onecountString=0;
					aMap2 = (HashMap) vote_optionList1.get(i);
					if(aMap2.get("option_count")!=null&&!aMap2.get("option_count").equals(""))
					{
						onecountString=Integer.parseInt(aMap2.get("option_count").toString());//获取每一个选项的投票次数
					}
					if(allpernuml!=0)
					{
						//求出每一个选项投票次数所占的百分比
						NumberFormat numberFormat = NumberFormat.getInstance();
						numberFormat.setMaximumFractionDigits(2);
						result = numberFormat.format((float)onecountString/(float)allpernuml*100);
					}
					aMap2.put("pers", result);
					vote_optionList.add(aMap2);
				}
			}
		} else {
			vote = new Vote();
		}
		String title_separator = SysconfigFuc.getSysValue("cfg_separator");
		 //获取公司网站名称
		String web_name=SysconfigFuc.getSysValue("cfg_webtitle");
		webtitleString=title_separator+web_name;
		return goUrl("voteDetail");
	}
	//时间的比较
	public String compare_date(String strstartdate,String strenddata) throws ParseException
	{
		String retString="2";
		SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");      
		String strcdate=df.format(new Date());
		Date cdate =df.parse(strcdate);
		Date sdate =df.parse(strstartdate);
		Date edate =df.parse(strenddata);
        if(cdate.before(edate)&&cdate.after(sdate))
        {
        	retString="1";
        }else if(cdate.before(sdate)){
        	retString="2";
        }else if(cdate.after(edate)){
        	retString="3";
        }
		return retString;
	}
	
	/**
	 * 方法描述：更新在线调查信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String optonvalues="";
		optonvalues=chkbtvalue;
		if(!optonvalues.equals(""))
		{
			   optonvalues=optonvalues.replace(" ", "");
			   HttpServletRequest request = getRequest();
			   String requestIp = request.getRemoteAddr();//获取IP地址
			   Vote_log votelogmodel=new Vote_log();
			   votelogmodel.setIp_addr(requestIp);
			   votelogmodel.setVote_id(vote_id);
			  
               //更新投票总数
			   String stroptionvalus[]=optonvalues.split(",");
			   StringBuilder sb=new StringBuilder();
			   for(int i=0;i<stroptionvalus.length;i++)
			   {
			    //更新投票总数
			    voteService.updateVoteCountNum(vote_id);
			    if(stroptionvalus[i]!=null)
			    vote_option=this.vote_optionService.get(stroptionvalus[i].toString());
			    if(vote_option!=null)
			    sb.append(vote_option.getOption_name());
			    sb.append(",");
			   }
			   votelogmodel.setOption_attr(sb.toString());
			   vote_logService.insert(votelogmodel);//插入投票日志信息
			   //更新投票选项的投票数量
			   String subStr[] = optonvalues.split(",");
			   List subList = new ArrayList();
				if(subStr.length>0){
					for(int i=0;i<subStr.length;i++){
						HashMap subMap = new HashMap();
						subMap.put("option_id", subStr[i]);
						subList.add(subMap);
					}
				}
			  vote_optionService.update_optioncount(subList);
		}
		return view();
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
		// 根据页面提交的条件找出信息总数
		int count = this.voteService.getCount(pageMap);
		// 分页插件
		pageMap = super.pageTool(count, pageMap);
		// 找出信息列表，放入list
		voteList = this.voteService.getList(pageMap);
		if(voteList!=null&&voteList.size()>0){
			for(int i=0;i<voteList.size();i++){
				HashMap map=new HashMap();
				map=(HashMap)voteList.get(i);
				if(map.get("option_attr")!=null){
				String option=map.get("option_attr").toString();
				String stroptionvalus[]=option.split(",");
				}
			}	
		}
		String title_separator = SysconfigFuc.getSysValue("cfg_separator");
		   //获取公司网站名称
		String web_name=SysconfigFuc.getSysValue("cfg_webtitle");
		webtitleString=title_separator+web_name;
		return goUrl("voteList");
	}

	/**
	 * 方法描述：返回新增页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		return goUrl(ADD);
	}

	/**
	 * 方法描述：get/set方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public Vote getVote() {
		return vote;
	}

	public void setVote(Vote vote) {
		this.vote = vote;
	}

	public List getVoteList() {
		return voteList;
	}

	public void setVoteList(List voteList) {
		this.voteList = voteList;
	}

	public String getIs_multi() {
		return is_multi;
	}

	public void setIs_multi(String is_multi) {
		this.is_multi = is_multi;
	}

	public String getVote_title_s() {
		return vote_title_s;
	}

	public void setVote_title_s(String vote_title_s) {
		this.vote_title_s = vote_title_s;
	}

	public String getVote_is_multi() {
		return vote_is_multi;
	}

	public void setVote_is_multi(String vote_is_multi) {
		this.vote_is_multi = vote_is_multi;
	}

	public String getIs_multi_s() {
		return is_multi_s;
	}

	public void setIs_multi_s(String is_multi_s) {
		this.is_multi_s = is_multi_s;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getVote_id() {
		return vote_id;
	}

	public void setVote_id(String vote_id) {
		this.vote_id = vote_id;
	}

	public Vote_option getVote_option() {
		return vote_option;
	}

	public void setVote_option(Vote_option vote_option) {
		this.vote_option = vote_option;
	}

	public List getVote_optionList() {
		return vote_optionList;
	}

	public void setVote_optionList(List vote_optionList) {
		this.vote_optionList = vote_optionList;
	}
	public String getChkbtvalue() {
		return chkbtvalue;
	}
	public void setChkbtvalue(String chkbtvalue) {
		this.chkbtvalue = chkbtvalue;
	}
	public String getRbtvalue() {
		return rbtvalue;
	}
	public void setRbtvalue(String rbtvalue) {
		this.rbtvalue = rbtvalue;
	}
	public String getIsshowvote() {
		return isshowvote;
	}
	public void setIsshowvote(String isshowvote) {
		this.isshowvote = isshowvote;
	}
	public String getWebtitleString() {
		return webtitleString;
	}
	public void setWebtitleString(String webtitleString) {
		this.webtitleString = webtitleString;
	}
	

}
