/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: MemberAction.java 
 */
package com.rbt.webaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Sort;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.function.CreateSpringContext;
import com.rbt.function.SysconfigFuc;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.model.Answer;
import com.rbt.model.Ask;
import com.rbt.service.IAnswerService;
import com.rbt.service.IAskService;
import com.rbt.service.IMemberuserService;

/**
 * @function 功能 前台知道列表页
 * @author 创建人 林俊钦
 * @date 创建日期 Sep 1, 2011 8:59:52 AM
 */
@Controller
public class WebknowAction extends WebbaseAction {

	private static final long serialVersionUID = -6025482882435476746L;
	/*
	 * 是否开启答案审核参数
	 */
	private static final String CFG_ANSWERISAUDIT_VALUE = "cfg_answerisaudit";
	
	/*
	 * 业务层接口
	 */
	@Autowired
	public IAskService askService;
	@Autowired
	public IAnswerService answerService;
	
	/*
	 * 知道表信息集合
	 */
	public List askList;
	public List commparaList;
	public List paraList;
	public List supplyTopList;
	public List recomList;
	public List  answerList;
	
	/*
	 * 接收前台参数值
	 */
	public int titleCount;
	public String ans_state;
	public String starttime;
	public String endtime;
	public String ans_state_1;
	public String searchText_1;
	public String serarchtexts;
	public String referdata_s;// 参考资料
	public String rand_s;// 验证码
	public String askid;
	public String content;
	public String title_s;
	
	/*
	 * 知道模块名称
	 */
	private String module_type = "know";

	/**
	 * @Method Description : 前台绑定知道数据列表
	 * @author : 林俊钦
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @date : Nov 1, 2011 4:11:13 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {
		// 设置网页位置
		super.setPage_position(this.module_type);
		HttpServletRequest request = getRequest();
		//构造list列表搜索条件
		List shList = new ArrayList();
		Sort sort=new  Sort();  
		if (reqKeyword("title","ask", shList)) {
			if (request.getParameter("state1") != null && !request.getParameter("state1").equals("")) {
				String  stateString = request.getParameter("state1");
				shList = normalSearch("answer_state",stateString,shList);
			}			
		}else{
			// 分类属性的搜索
			if(attrString!=null && !attrString.equals("")){
				String attrs[] = attrString.split(",");
				for(int i=0;i<attrs.length;i++){
					if(attrs[i]!=null && !attrs[i].equals("")){
						String vals[] = attrs[i].split("\\|");
						if(vals!=null && vals.length>1 && !vals[1].equals("none")){
							shList = normalSearch("attr_desc",vals[1],shList);
						}
					}
				}
			}
			// 按分类列表选择列表
			shList = this.normalSearch("cat_attr", cat_id, shList);
			// 按地区选择列表
			shList = this.normalSearch("area_attr", area_id, shList);
			// 按搜索标题选择列表
			shList = this.normalSearch("title", searchText, shList);
			// 按搜索标题选择列表
			shList = this.normalSearch("title", searchText_1, shList);
			// 搜索回答状态
			if (ans_state != null && !ans_state.equals("")) {
				if (this.ans_state.equals("0")) {
					shList = this.normalSearch("answer_state", "0", shList);
				} else if (ans_state.equals("1")) {
					shList = this.normalSearch("answer_state", "1", shList);
				} else if (ans_state.equals("3")) {			
					shList = this.normalSearch("answer_state","0", shList);
					shList = this.normalSearch("num","0", shList);
				} else if(ans_state.equals("10")){
					shList = this.normalSearch("integral",ans_state, shList);
				} else if(ans_state.equals("8")){
					shList = this.normalSearch("info_state","1", shList);
				}
			}
			if (ans_state_1 != null && !ans_state_1.equals("")) {
				shList = this.normalSearch("answer_state",ans_state_1, shList);
			}
			
			String sdate=null,edate=null;
			// 搜索开始时间
			if (starttime != null && !starttime.equals("")) {
				sdate=formatLuDate(starttime);
			}
			// 搜索结束时间
			if (endtime != null && !endtime.equals("")) {
				edate=formatLuDate(endtime);
			}
			shList = rangeSearch("lu_in_date",sdate,edate,shList);
			
		}
		
		// 如果搜索内容为空则不搜索
		if (is_souch) {
			SearchIndex si=new SearchIndex("ask");
			//计算符合条件条数
			int count=si.getCount(shList);
			infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);
			//搜索符合条件的list
			askList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			askList = ToolsFuc.replaceList(askList);
			if(cateList!=null &&cateList.size()==0){
				if(cat_id!=null && !cat_id.equals("")){
					//分类属性信息开始
					SearchIndex attrsi=new SearchIndex("categoryattr");
					List aList = new ArrayList();
					aList = normalSearch("cat_attr",cat_id,aList);
					aList = normalSearch("attr_type","2",aList);
					aList = normalSearch("is_must","1",aList);
					attrList = attrsi.search(aList,null,0,0);
					//分类属性信息结束
					//分类属性值信息开始
					SearchIndex attrvalue=new SearchIndex("attrvalue");
					List vList = new ArrayList();
					vList = normalSearch("cat_attr",cat_id,vList);
					vList = normalSearch("attr_type","2",vList);
					vList = normalSearch("is_must","1",vList);
					attrvalueList = attrvalue.search(aList,null,0,0);
					//分类属性值信息结束
				}
			}
		}
		
		
		// 绑定推荐问题列表
		SearchIndex si=new SearchIndex("ask");
		List recom1List = new ArrayList();
		recom1List = normalSearch("info_state","1",recom1List);
		recom1List = normalSearch("is_recom","1",recom1List);
		recomList = si.search(recom1List, null , 1, 10); 
		
		return goUrl("knowList");
	}
	
	
	/**
	 * 方法描述：AJAX获取答案内容
	 * 
	 * @return 返回串的格式为： * ##########内容1@@@@@@@@@@用户1&&&&&&&&&&时间1%%%%%%%%%%参考资料1##########内容2@@@@@@@@@@用户2&&&&&&&&&&时间2%%%%%%%%%%参考资料2 "##########";//用于分隔内容的符号
	 *         "@@@@@@@@@@";//使用分隔发布人的符号 "&&&&&&&&&&";//用于分隔时间的符号 "$$$$$$$$$$";//用于区分总记录数"%%%%%%%%%%";//用于分隔参考资料的符号
	 * @throws Exception
	 */
	public void getallanswers() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		String contentsplit = "##########";// 用于分隔内容的符号
		String refdatasplit = "%%%%%%%%%%";// 用于分隔参考资料的符号
		String usernamesplit = "@@@@@@@@@@";// 使用分隔发布人的符号
		String datesplit = "&&&&&&&&&&";// 用于分隔时间的符号
		String countsplitString = "$$$$$$$$$$";// 用于区分总记录数
		String companysplit = "**********";// 用于区分企业用户名
		Integer countString = 0;
		StringBuilder outprintString = new StringBuilder();
		Map map = new HashMap();
		map.put("ask_id", askid);
		//isselect是否选择为最佳答案，0：否，1:是
		map.put("isselect", "0");
		// 判断后台是否开始答案审核功能
		String cfg_answerisaudit = SysconfigFuc.getSysValue(CFG_ANSWERISAUDIT_VALUE);
		if (!cfg_answerisaudit.equals("1")) {
			map.put("info_state", "1");
		}
		answerList = this.answerService.getList(map);
		Map mapcount = new HashMap();
		mapcount.put("ask_id", askid);
		countString = this.answerService.getCount(mapcount);
		outprintString.append(countString.toString());
		outprintString.append(countsplitString);
		if (answerList != null && answerList.size() != 0) {
			Map mapvalue;
			HashMap usernamevalue = new HashMap();
			for (int i = 0; i < answerList.size(); i++) {
				String refdatatextString = "", contenttextString = "", indatetextString = "", companytextString = "";
				mapvalue =(HashMap) answerList.get(i);
				if(mapvalue!=null&&mapvalue.get("user_id")!=null)
				{
					usernamevalue.put("user_id", mapvalue.get("user_id").toString());// 通过user_id获取user_name
				}
				// 获取答案内容
				outprintString.append(contentsplit);// 添加#########
				if (mapvalue!=null&&mapvalue.get("answer_desc") != null) {
					contenttextString = mapvalue.get("answer_desc").toString();
				}
				outprintString.append(contenttextString);// 添加内容
				// 获取发布人
				outprintString.append(usernamesplit);// 添加@@@@@@@@@@
				outprintString.append(getUserName(usernamevalue));// 添加用户名
				// 获取时间
				outprintString.append(datesplit);// 添加&&&&&&&&&&&&
				if (mapvalue.get("in_date") != null) {
					indatetextString = mapvalue.get("in_date").toString();
				}
				outprintString.append(indatetextString);// 添加时间
				// 获取参考资料
				outprintString.append(refdatasplit);// 添加%%%%%%%%%%
				if (mapvalue!=null&&mapvalue.get("refer_data") != null) {
					refdatatextString = mapvalue.get("refer_data").toString();
				}
				outprintString.append(refdatatextString);// 添加参考资料
				// 获取企业用户名称
				outprintString.append(companysplit);// 添加**********
				if (mapvalue!=null&&mapvalue.get("cust_id") != null) {
					HashMap usernameMap1 = new HashMap();
					usernameMap1.put("cust_id", mapvalue.get("cust_id").toString());
					usernameMap1.put("user_type", "1");
					companytextString = getUserName(usernameMap1);
				}
				outprintString.append(companytextString);// 添加参考资料

			}
		}
		out.write(outprintString.toString());
	}
	
	// 通过客户ID找到客户会员名称
	public String getUserName(HashMap map) {
		String usernameString = "";
		IMemberuserService news_Service = (IMemberuserService) CreateSpringContext.getContext().getBean("memberuserService");
		List memberusernameList = news_Service.getList(map);
		if (memberusernameList != null && memberusernameList.size() != 0) {
			HashMap mapusername = (HashMap) memberusernameList.get(0);
			if (mapusername.get("user_name") != null) {
				usernameString = mapusername.get("user_name").toString();
			}
		}
		return usernameString;

	}
	
	/**
	 * 方法描述：AJAX获取问题的状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getanswerstates() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Ask sy = new Ask();
		sy = this.askService.get(askid);
		out.write(sy.getAnswer_state());
	}
	
	/**
	 * 方法描述：AJAX获取问题的最佳答案
	 * 
	 * @return
	 * @throws Exception
	 */
	public void getanswerbest() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Map map = new HashMap();
		map.put("ask_id", askid);
		//isselect是否选择为最佳答案，0：否，1:是
		map.put("isselect", "1");
		answerList = this.answerService.getList(map);
		HashMap bestmap = new HashMap();
		// 获取最佳答案列表
		if (answerList != null && answerList.size() != 0) {
			bestmap = (HashMap) answerList.get(0);
		}
		StringBuilder outprintString = new StringBuilder();// 返回值的格式为："内容&&&&&&&&&&回答时间##########客户ID**********客户名称"
		// 获取最佳答案的内容以"&&&&&&&&&&"分隔开
		if (bestmap!=null&&bestmap.get("answer_desc") != null && !bestmap.get("answer_desc").equals("")) {
			outprintString.append(bestmap.get("answer_desc").toString() + "&&&&&&&&&&");
		} else {
			outprintString.append("未知答案&&&&&&&&&&");
		}
		// 获取最佳答案的回答时间 以"##########"分隔开
		if (bestmap!=null&&bestmap.get("in_date") != null && !bestmap.get("in_date").equals("")) {
			outprintString.append( bestmap.get("in_date").toString() + "##########");
		} else {
			outprintString.append("未知##########");
		}
		// 获取最佳答案的客户ID 以"**********"分隔开
		if (bestmap!=null&&bestmap.get("cust_id") != null && !bestmap.get("cust_id").equals("")) {
			HashMap usernameMap1 = new HashMap();
			if(bestmap!=null&&bestmap.get("cust_id")!=null)
			{
				usernameMap1.put("cust_id", bestmap.get("cust_id").toString());
			}
			usernameMap1.put("user_type", "1");
			outprintString.append( getUserName(usernameMap1) + "**********");
		}
		// 获取最佳答案的回答者名称
		if (bestmap.get("user_id") != null && !bestmap.get("user_id").equals("")) {
			HashMap usernameMap = new HashMap();
			if(usernameMap!=null&&usernameMap.get("user_id")!=null)
			{
				usernameMap.put("user_id", bestmap.get("user_id").toString());
			}
			outprintString.append( getUserName(usernameMap));
		}
		out.write(outprintString.toString());
	}

	/**
	 * AJAX回答问题,验证用户输入的验证是否正确和用户是否登录
	 * 
	 * @throws Exception
	 */
	public void verificationrandusername() throws Exception {
		PrintWriter out = getResponse().getWriter();
		String outputString = "0";
		String sysrand = super.getSessionFieldValue("sysrand");
		// 获取系统生成的验证码
		if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
			if (!sysrand.equals(rand_s)) {
				outputString = "2";// 验证码输入错误
			} else {
				Ask askuser = new Ask();
				if (askid != null && !askid.equals("")) {
					askuser = this.askService.get(askid);
				}
				if (askuser != null && askuser.getUser_id() != null && !askuser.getUser_id().equals("")) {
					if (askuser.getUser_id().equals(this.session_user_id)) {
						outputString = "3";// 问题为自己发布的问题
					} else {
						outputString = "1";// 用户已经登录
					}
				}
			}

		} else {
			outputString = "0";// 用户没有登录
		}
		out.write(outputString);
	}
	/**
	 * 问答管理 回答问题
	 * 
	 * @throws Exception
	 */
	public void addwebanswers() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		String fromURL = request.getHeader("Referer"); // 获取请求的URL地址
		Answer answermodel = new Answer();
		// 获取系统生成的验证码
		String sysrand = super.getSessionFieldValue("sysrand");
		// 判断用户输入的验证码是否正确
		if (sysrand.equals(rand_s)) {
			// 判断用户是已经登录
			if (this.session_cust_id != null && !this.session_cust_id.equals("")) {
				Ask askuser = new Ask();
				if (askid != null && !askid.equals("")) {
					askuser = this.askService.get(askid);
				}
				if (askuser != null && askuser.getUser_id() != null && !askuser.getUser_id().equals("")) {
					// 判断问题是否为自己用户自己发布的问题，如果是就不让用户回答
					if (!askuser.getUser_id().equals(this.session_user_id)) {
						answermodel.setCust_id(this.session_cust_id);
						answermodel.setUser_id(this.session_user_id);
						String requestIp = request.getRemoteAddr();
						answermodel.setIp(requestIp);
						answermodel.setAsk_id(askid);
						if (content != null && !content.equals("")) {
							content = content.replace("<", "");
							content = content.replace(">", "");
						}
						//文本过滤，防止输入非法字符
						content=ToolsFuc.xssFilter(content);
						answermodel.setAnswer_desc(content);
						// isselect是否是最佳答案 0：否
						answermodel.setIsselect("0");
						// Info_state审核状态 1:审核通过
						answermodel.setInfo_state(SysconfigFuc.getSysValue(CFG_ANSWERISAUDIT_VALUE));
						answermodel.setAsk_title(title_s);
						if (referdata_s != null && !referdata_s.equals("")) {
							referdata_s = referdata_s.replace("<", "");
							referdata_s = referdata_s.replace(">", "");
						}
						answermodel.setRefer_data(referdata_s);
						answerService.insert(answermodel);
					}
				}
			}
		}
		response.sendRedirect(fromURL);
	}

	public List getAnswerList() {
		return answerList;
	}


	public void setAnswerList(List answerList) {
		this.answerList = answerList;
	}


	public String getReferdata_s() {
		return referdata_s;
	}


	public void setReferdata_s(String referdata_s) {
		this.referdata_s = referdata_s;
	}


	public String getRand_s() {
		return rand_s;
	}


	public void setRand_s(String rand_s) {
		this.rand_s = rand_s;
	}


	public String getAskid() {
		return askid;
	}


	public void setAskid(String askid) {
		this.askid = askid;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getTitle_s() {
		return title_s;
	}


	public void setTitle_s(String title_s) {
		this.title_s = title_s;
	}
	
}
