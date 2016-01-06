/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ComplaintAction.java 
 */
package com.rbt.action;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Preparable;
import com.rbt.common.Constants;
import com.rbt.common.util.FileUpDownFileUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.function.CategoryFuc;
import com.rbt.model.Complaint;
import com.rbt.model.Member;
import com.rbt.service.ICommparaService;
import com.rbt.service.IComplaintService;
import com.rbt.service.IMemberService;
import com.rbt.service.IOrderinfoService;

/**
 * @function 功能 会员投诉信息action类
 * @author 创建人 蔡毅存
 * @date 创建日期 Fri Dec 02 11:20:21 CST 2011
 */
@Controller
public class ComplaintAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	/*
	 * 会员投诉信息对象
	 */
	public Complaint complaint;
	//会员对象
	public Member member;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IComplaintService complaintService;
	@Autowired
	public ICommparaService commparaService;
	@Autowired
	public IMemberService memberService;
	@Autowired
	public IOrderinfoService orderinfoService;
	/*
	 * 会员投诉信息信息集合
	 */
	public List complaintList;
	
	//投诉类型列表
	public List comp_typeList;
	//投诉状态列表
	public List state_codeList;
	//订单类别
	public List orderList;
	//用户类别
	public List memberList;
	
	//图片串
	public String img_path;
	//上传身份证明资料
	public String file_path;
	//判断验证订单id号还是用户名称
	public String selid;
	//下载文件路径
	public String attach_path;
	//当前投诉状态
	public String doresultKey;
	//接收发送类型
	public String type;
	
	/*
	 * 搜索字段
	 */
	public String starttime_s;
	public String comp_type_s;
	public String state_code_s;
	public String endtime_s;

	
	
	/**
	 * 方法描述：返回新增会员投诉信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		
		//读取参数表投诉类型
		HashMap comp_typemap=new HashMap();
		comp_typemap.put("para_code", "comp_type");
		comp_typeList=commparaService.getList(comp_typemap);
		//读取参数表投诉状态
		HashMap state_codemap=new HashMap();
		state_codemap.put("para_code", "state_code");
		state_codeList=commparaService.getList(state_codemap);
		
		if(this.session_cust_id!=null && !this.session_cust_id.equals("")){
		    member=this.memberService.get(this.session_cust_id);
		}
		if(member!=null && member.getEmail()!=null){
		    this.complaint.setEmail(member.getEmail());
		}
		if(member!=null && member.getPhone()!=null){
			this.complaint.setPhone(member.getPhone());
		}
		return goUrl(ADD);
	}

	/**
	 * 方法描述：新增会员投诉信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
		
		if ("0".equals(this.complaint.getComp_type())) {
			this.addFieldError("complaint.comp_type", "请选择投诉类型");
		}
		String get_cust_id="";
		String get_cust_name="";
		if("1".equals(selid)){
			if(ValidateUtil.isRequired(this.complaint.getOrder_id())){
				this.addFieldError("complaint.order_id", "订单号不能为空");
			}else{
			HashMap map=new HashMap();
			map.put("order_id", this.complaint.getOrder_id());
			orderList = orderinfoService.getList(map);
			if(orderList!=null&&orderList.size()>0){
				HashMap orderidmap = (HashMap)orderList.get(0);
				if(orderidmap.get("seller_id")!=null&&orderidmap.get("seller_cust_name")!=null){
				get_cust_id=orderidmap.get("seller_id").toString();
				get_cust_name=orderidmap.get("seller_cust_name").toString();
			    }
			}else{
				this.addFieldError("complaint.order_id", "订单号不存在");
			}
			}
		}
		
		
		if("2".equals(selid)){
			if(ValidateUtil.isRequired(this.complaint.getGet_cust_name())){
				this.addFieldError("complaint.get_cust_name", "被投诉方名称不能为空");
			}else{
			//获取被投诉方
			HashMap namemap=new HashMap();
			namemap.put("company_name", this.complaint.getGet_cust_name());
			memberList=this.memberService.getList(namemap);
			if(memberList!=null&&memberList.size()>0){
			HashMap cust_namemap = (HashMap)memberList.get(0);
				if(cust_namemap.get("cust_name")!=null && cust_namemap.get("cust_id")!=null){
					get_cust_id=cust_namemap.get("cust_id").toString();
					get_cust_name=cust_namemap.get("cust_name").toString();
				}
			}else{
				this.addFieldError("complaint.get_cust_name", "被投诉方名称不存在");
			}
			}
			if(this.session_cust_id.equals(get_cust_id)){
				this.addFieldError("complaint.get_cust_name", "您不能对自己发起投诉");
			}	
		}
		complaint.setCust_id(this.session_cust_id);
		complaint.setUser_id(this.session_user_id);
		complaint.setGet_cust_id(get_cust_id);
		complaint.setGet_cust_name(get_cust_name);
		complaint.setState_code("1");
		//字段验证
		super.commonValidateField(complaint);
		if(super.ifvalidatepass){
			return add();
		}
		this.complaintService.insert(complaint);
		this.addActionMessage("投诉成功");
		this.complaint = null;
		return list();
	}

	/**
	 * 方法描述：修改会员投诉信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		
		if ("0".equals(this.complaint.getComp_type())) {
			this.addFieldError("complaint.comp_type", "请选择投诉类型");
		}
		complaint.setState_code("1");
		//字段验证
		super.commonValidateField(complaint);
		if(super.ifvalidatepass){
			return view();
		}
		this.complaintService.update(complaint);
		this.addActionMessage("修改会员投诉成功");
		return list();
	}
	/**
	 * 方法描述：删除会员投诉信息信息
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.complaint.getInfo_id();
		id = id.replace(" ", "");
		this.complaintService.delete(id);
		this.addActionMessage("删除会员投诉成功");
		return list();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//读取参数表投诉类型
		HashMap comp_typemap=new HashMap();
		comp_typemap.put("para_code", "comp_type");
		comp_typeList=commparaService.getList(comp_typemap);
		//读取参数表投诉状态
		HashMap state_codemap=new HashMap();
		state_codemap.put("para_code", "state_code");
		state_codeList=commparaService.getList(state_codemap);
		
		Map pageMap = new HashMap();
		
		if (comp_type_s != null && !comp_type_s.equals("")) {
			pageMap.put("comp_type", comp_type_s);
		}
		
		if (state_code_s != null && !state_code_s.equals("")) {
			pageMap.put("state_code", state_code_s);
		}
		
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		
		//我发起的投诉 用cust_id来匹配
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			pageMap.put("cust_id", this.session_cust_id);
		}
		
		//过滤地区
		pageMap=super.areafilter(pageMap);
		
		//根据页面提交的条件找出信息总数
		int count=this.complaintService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		complaintList = this.complaintService.getList(pageMap);
		complaintList = CategoryFuc.replaceList(complaintList, "comp_type");
		return goUrl(INDEXLIST);
	}
	/**
	 * 方法描述：根据主键找出会员投诉信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		if(complaint.getCust_id()!=null){
			if(accessControl(complaint.getCust_id())){
				return list();
			}
		}
		String id = this.complaint.getInfo_id();
		if(id==null || id.equals("")){
			complaint = new Complaint();
		}else{
			complaint = this.complaintService.get(id);
		}

		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			
		}else{
			if(this.complaint.getCust_id()!=null && !"".equals(this.complaint.getCust_id())){
			member=this.memberService.get(this.complaint.getCust_id());
			}
			if(complaint!=null && !"".equals(complaint.getComp_path())&&complaint.getComp_path()!=null){
				img_path=imgString(complaint.getComp_path().toString());
				
			}
			if(complaint!=null && !"".equals(complaint.getFile_path())&&complaint.getFile_path()!=null){
			    file_path=imgString(complaint.getFile_path().toString());
			}
			if(complaint!=null && complaint.getState_code()!=null){
				doresultKey=complaint.getState_code().toString();
			}
		}
	
		
		//读取参数表投诉类型
		HashMap comp_typemap=new HashMap();
		comp_typemap.put("para_code", "comp_type");
		comp_typeList=commparaService.getList(comp_typemap);
		return goUrl(VIEW);
	}
	/**
	 * @return the ComplaintList
	 */
	public List getComplaintList() {
		return complaintList;
	}
	
	public String auditList() throws Exception {
		
		//读取参数表投诉类型
		HashMap comp_typemap=new HashMap();
		comp_typemap.put("para_code", "comp_type");
		comp_typeList=commparaService.getList(comp_typemap);
		//读取参数表投诉状态
		HashMap state_codemap=new HashMap();
		state_codemap.put("para_code", "state_code");
		state_codeList=commparaService.getList(state_codemap);
		
		Map pageMap = new HashMap();
		
		if (comp_type_s != null && !comp_type_s.equals("")) {
			pageMap.put("comp_type", comp_type_s);
		}
		
		if (state_code_s != null && !state_code_s.equals("")) {
			pageMap.put("state_code", state_code_s);
		}
		
		if (starttime_s != null && !starttime_s.equals("")) {
			pageMap.put("starttime", starttime_s);
		}
		if (endtime_s != null && !endtime_s.equals("")) {
			pageMap.put("endtime", endtime_s);
		}
		
		//我发起的投诉 用cust_id来匹配
		
		pageMap.put("get_cust_id", this.session_cust_id);
		
		//根据页面提交的条件找出信息总数
		int count=this.complaintService.getCount(pageMap);
		
		//分页插件
		pageMap = super.pageTool(count,pageMap);
		
		complaintList = this.complaintService.getList(pageMap);
		complaintList = CategoryFuc.replaceList(complaintList, "comp_type");
		
		return goUrl(AUDITLIST);
	}
	
	public String updatestate() throws Exception {
		//获取订单ID号
		HttpServletRequest request = getRequest();
		
		if (this.session_cust_type.equals(Constants.MEMBER_TYPE)) {
			//获取ID号
			String info_id="";
			if(request.getParameter("info_id")!=null){
				info_id = request.getParameter("info_id");		
			}
			//获取投诉状态
			String state_code="";
			if(request.getParameter("state_code")!=null){
				state_code = request.getParameter("state_code");		
			}
			
			complaint=this.complaintService.get(info_id);
			complaint.setState_code(state_code);
		}else{
			complaint.setState_code(this.complaint.getState_code());
			complaint.setDo_result(this.complaint.getDo_result());
		}
		this.complaintService.update(complaint);
		this.addActionMessage("修改投诉状态成功");
		return list();
		
	}
	
	
	/**
	 * 方法描述：根据主键找出会员投诉信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		String id = this.complaint.getInfo_id();
		if(id==null || id.equals("")){
			complaint = new Complaint();
		}else{
			complaint = this.complaintService.get(id);
		}
		
		//读取参数表投诉类型
		HashMap comp_typemap=new HashMap();
		comp_typemap.put("para_code", "comp_type");
		comp_typeList=commparaService.getList(comp_typemap);
		
		
		StringBuilder sb=new StringBuilder();
		img_path="";
		if(complaint!=null&&!"".equals(complaint.getComp_path())&&complaint.getComp_path()!=null){
		img_path=imgString(complaint.getComp_path());
		}
		return goUrl(AUDIT);
	}
	
	public String appeal() throws Exception{
		
		if(ValidateUtil.isRequired(this.complaint.getAppeal_desc())){
			this.addFieldError("complaint.appeal_desc", "申诉内容不能为空");
			return audit();
		}
		//获取系统当前时间
		Date time=new Date();
		DateFormat d = DateFormat.getDateInstance();
	    String str = d.format(time); 
		complaint.setAppeal_desc(this.complaint.getAppeal_desc());
		complaint.setAppeal_date(str);
		complaint.setAppeal_user_id(this.session_user_id);
		this.complaintService.update(complaint);
		this.addActionMessage("提起申诉成功");
		
		return audit();
	}
	
	public void downloadTab() throws Exception {     

		HttpServletRequest request = getRequest();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		//获取投诉状态
		attach_path="";
		if(request.getParameter("attach_path")!=null){
			attach_path = request.getParameter("attach_path");		
		}
		if(attach_path!=null&&!"".equals(attach_path)){
		//文件名
		String filename=attach_path.substring(attach_path.lastIndexOf('/')+1);
		//路径
		String url=attach_path.substring(0,attach_path.lastIndexOf('/')+1);
        FileUpDownFileUtil fudf=new FileUpDownFileUtil();
        fudf.downloadFile(url, filename);
		}
	}	
	
	public String imgString(String img_url) throws Exception{
		String img_paths="";
		StringBuilder sb=new StringBuilder();
		
	        if(img_url.indexOf(",")!=-1){
			String url[]=img_url.split(",");
				for(int i=0;i<url.length;i++){
					sb.append("<li style='float:left'><img src='"+ url[i] +"' width='90' height='100' border='0'/></li>&nbsp;");
				}
		    }else{
				sb.append("<li style='float:left'><img src='"+ img_url +"' width='90' height='100' border='0'/></li>&nbsp;");
		    }
	
		if(sb!=null){
			img_paths=sb.toString();
			}
		return img_paths;
	}
	
	public String result() throws Exception{
		HttpServletRequest request = getRequest();
		//设置接收与发送的编码格式
		request.setCharacterEncoding("UTF-8");
		//获取订单ID号
		String type="";
		if(request.getParameter("type")!=null){
			type = request.getParameter("type");
		}
		return INPUT;
	}
	
	/**
	 * @param complaintList
	 *  the ComplaintList to set
	 */
	public void setComplaintList(List complaintList) {
		this.complaintList = complaintList;
	}
	
	public void prepare() throws Exception { super.saveRequestParameter();
		if(complaint == null){
			complaint = new Complaint();
		}
		String id = this.complaint.getInfo_id();
		if(!ValidateUtil.isDigital(id)){
			complaint = this.complaintService.get(id);
		}
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getSelid() {
		return selid;
	}
	public void setSelid(String selid) {
		this.selid = selid;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getAttach_path() {
		return attach_path;
	}
	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getDoresultKey() {
		return doresultKey;
	}
	public void setDoresultKey(String doresultKey) {
		this.doresultKey = doresultKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the complaint
	 */
	public Complaint getComplaint() {
		return complaint;
	}
	/**
	 * @param Complaint
	 *            the complaint to set
	 */
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
}

