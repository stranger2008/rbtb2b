/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: ChannelAction.java 
 */
package com.rbt.action;

import java.util.*;

import com.opensymphony.xwork2.Preparable;
import com.rbt.action.BaseAction;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.common.util.ValidateUtil;
import com.rbt.createHtml.DoHtml;
import com.rbt.createHtml.ParseHtml;
import com.rbt.function.CreateSpringContext;
import com.rbt.model.Channel;
import com.rbt.model.Commpara;
import com.rbt.service.IChannelService;
import com.rbt.service.ICommparaService;
import com.rbt.service.ISysmoduleService;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @function 功能 记录网站栏目信息action类
 * @author 创建人 胡惜坤
 * @date 创建日期 Mon Aug 15 10:57:10 CST 2011
 */
@Controller
public class ChannelAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = 1L;
	/*
	 * 记录网站栏目信息对象
	 */
	public Channel channel;
	/*
	 * 业务层接口
	 */
	@Autowired
	public IChannelService channelService;
	@Autowired
	private ISysmoduleService sysmoduleService;
	/*
	 * 记录网站栏目信息信息集合
	 */
	public List channelList;
	/*
	 * 排序ID集合
	 */
	public String admin_Sort_id;
	/*
	 * 注入commparaService
	 */
	@Autowired
	public ICommparaService commparaService;
	/*
	 * 列表页参数列表
	 */
	public Commpara commpara;
	/*
	 * 参数列表
	 */
	public List commparaList;

	public String[] files;
	public List myfileList;
	/*
	 * 更新栏目获取的ID
	 */
	public String update_ch_id;
	/*
	 * 获取更新栏目的Service
	 */
	public ParseHtml parsehtmlService;
	public String channel_id;
	public String cname;
	/*
	 * 页面请求的类型的判断：参数为all：表示请求的是为"更新全部栏目";one:表示请求的动作为"更新当前栏目页"；two:表示请求的动作为"更新子栏目页"
	 */
	public String strall="all";//表示请求的是为"更新全部栏目
	public String strone="one";//表示请求的动作为"更新当前栏目页
	public String strtwo="two";//表示请求的动作为"更新子栏目页
	/*
	 * 更新栏目页的请求类型：如果为“all”:就是更新全部的请求；"one":更新本栏目页的请求；"two":更新子栏目有的请求；
	 */
	public String update_state;
	/*
	 * 更新的类型：如有update_type不为空的话，就是请求的更新详细栏目页的，否则就是为一般的栏目更新请求
	 */
	public String update_type;

	/**
	 * 方法描述：返回新增记录网站栏目信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		setCommpara();
		getfilelist();
		return goUrl(ADD);
	}

	/**
	 * 方法描述：网站栏目的级别增加
	 */
	public void validateChannelLevel() {
		if (channel.getCh_level().equals("1")) {
			channel.setCh_level("1");
		} else {
			Integer ch_level = Integer.parseInt(channel.getCh_level()) - 1;
			channel.setCh_level(ch_level.toString());
		}
	}

	public void getfilelist() throws Exception {
		File file = new File(PropertiesUtil.getRootpath()
				+ "/templets/"+templateFiles+"/");
		files = file.list();
		myfileList = new ArrayList();
		Map map = new HashMap();
		for (int i = 0; i < files.length; i++) {
			map.put("filenames", files[i]);
			myfileList.add(map);
		}
	}

	// 用于判断是否出现相同的栏目名称
	public void getCName() throws Exception {
		PrintWriter out = getResponse().getWriter();
		Map map = new HashMap();
		map.put("ch_name", cname);
		Integer count = 0;
		if (channel_id != null && !channel_id.equals("")) {
			Channel channames = new Channel();
			channames = channelService.get(channel_id);
			if (cname.equals(channames.getCh_name())) {
				count = 0;
			} else {
				count = channelService.getCount(map);
			}
		} else {
			count = channelService.getCount(map);
		}
		out.write(count.toString());

	}

	// 用于判断是否出现相同的栏目名称
	public int getChanneName(String name, String id) throws Exception {
		Map map = new HashMap();
		map.put("ch_name", name);
		int count = 0;
		if (id != null && !id.equals("0")) {
			Channel chans = new Channel();
			chans = channelService.get(id);
			if (name.equals(chans.getCh_name())) {
				count = 0;
			} else {
				count = channelService.getCount(map);
			}
		} else {
			count = channelService.getCount(map);
		}
		return count;
	}
	//更新前台栏目页面
	//type：更新类型 all：更新全部栏目，one：更新当前栏目首页，many：更新下级栏目
	//ch_id：栏目标识 type=all时，此值可为空
	//1：表示更新成功！0：更新失败
	public void updateHtmlPage() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = getResponse().getWriter();
		response.setCharacterEncoding("UTF-8");
		String type="";//更新栏目的类型
		String ch_id="";//更新栏目的ID
		String outString="0";//1：表示更新成功！0：更新失败
		//获取更新栏目的类型
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		//获取更新栏目的ID
		if(request.getParameter("ch_id")!=null){
			ch_id=request.getParameter("ch_id");
		}
		//更新全部栏目
		if(type.equals("all")){
			doHtml(new HashMap());
			outString="1";
		}else if(type.equals("one")){
			HashMap chMap = new HashMap();
			chMap.put("ch_id", ch_id);
			doHtml(chMap);
			outString="1";
			
		}else if(type.equals("many")){
			HashMap chMap = new HashMap();
			chMap.put("ch_id", ch_id);
			doHtml(chMap,"1");
			outString="1";
		}else {
			outString="0";//更新失败
		}
		
		out.write(outString); ;
	}
	public static void doHtml(HashMap map){
		doHtml(map,"");
	}
	//type不为空，则递归更新子栏目
	public static void doHtml(HashMap map,String type){
		
		DoHtml doHtml = new DoHtml();
		List chList = getChannelList(map);
		if(chList!=null && chList.size()>0){
			HashMap chMap = new HashMap();
			for(Iterator it = chList.iterator();it.hasNext();){
				chMap = (HashMap)it.next();
				doHtml.doIndexHtml(chMap);
			}
			//以下为递归更新
			if(type.equals("1")){
				String ch_id  = "";
				if(map.get("ch_id")!=null) ch_id = map.get("ch_id").toString();
				List downList = getDownChList(ch_id);
				if(downList!=null && downList.size()>0){
					Map downMap = new HashMap();
					HashMap downChMap = new HashMap();
					for(Iterator its = downList.iterator();its.hasNext();){
						downMap = (HashMap)its.next();
						String ch_id_down = "";
						if(downMap.get("ch_id")!=null){
							ch_id_down = downMap.get("ch_id").toString();
							downChMap.put("ch_id", ch_id_down);
							doHtml(downChMap,type);
						}
						
					}
				}
			}
		}
	}
	//根据栏目标识找出下级栏目
	public static List getDownChList(String ch_id){
		HashMap chMap = new HashMap();
		chMap.put("up_ch_id", ch_id);
		return getChannelList(chMap);
	}
	//获取栏目信息List
	public static List getChannelList(HashMap chMap){
		IChannelService channel_Service = (IChannelService)CreateSpringContext.getContext().getBean("channelService");
		return channel_Service.getList(chMap);
	}
	
	//通过模型类型module_type找出栏目标识ch_id
	public static String getChidByModuletype(String module_type){
		HashMap map = new HashMap();
		map.put("moduletype", module_type);
		List list = getChannelList(map);
		String ch_id = "";
		if(list!=null && list.size()>0){
			Map cMap = (HashMap)list.get(0);
			if(cMap.get("ch_id")!=null) ch_id = cMap.get("ch_id").toString();
		}
		return ch_id;
	}
	//更新详细页
	//ch_id all：更新所有
	//type 1：递归更新下级栏目详细页
	public  void updateArticleHtml() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		PrintWriter out = getResponse().getWriter();
		response.setCharacterEncoding("UTF-8");
		String type="";//更新栏目的类型
		String ch_id="";//更新栏目的ID
		String outString="0";//1：表示更新成功！0：更新失败
		//获取更新栏目的类型
		if(request.getParameter("type")!=null){
			type=request.getParameter("type");
		}
		//获取更新栏目的ID
		if(request.getParameter("ch_id")!=null){
			ch_id=request.getParameter("ch_id");
		}
		DoHtml doHtml = new DoHtml();
		
		if(type.equals("all")){
			List chList = getChannelList(new HashMap());
			if(chList!=null && chList.size()>0){
				HashMap chMap = new HashMap();
				for(Iterator it = chList.iterator();it.hasNext();){
					chMap = (HashMap)it.next();
					doHtml.doArticleHtml(chMap);
				}
			}
			outString="1";
		}else{
			doArticleHtml(type,ch_id);
			outString="1";
		}
		out.write(outString); ;
		
	}
	
	
	//type不为空，则递归更新子栏目
	public static void doArticleHtml(String type,String ch_id){	
		DoHtml doHtml = new DoHtml();
		HashMap map = new HashMap();
		map.put("ch_id", ch_id);
		List chList = getChannelList(map);
		if(chList!=null && chList.size()>0){
			HashMap chMap = new HashMap();
			for(Iterator it = chList.iterator();it.hasNext();){
				chMap = (HashMap)it.next();
				doHtml.doArticleHtml(chMap);
			}
			//以下为递归更新
			if(type.equals("1")){
				List downList = getDownChList(ch_id);
				if(downList!=null && downList.size()>0){
					HashMap downMap = new HashMap();
					HashMap downChMap = new HashMap();
					for(Iterator its = downList.iterator();its.hasNext();){
						downMap = (HashMap)its.next();
						String ch_id_down = "";
						if(downMap.get("ch_id")!=null){
							ch_id_down = downMap.get("ch_id").toString();
							downChMap.put("ch_id", ch_id_down);
							doArticleHtml(ch_id_down,type);
						}
						
					}
				}
			}
		}
	}
	/**
	 * 方法描述：新增记录网站栏目信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String insert() throws Exception {
	    if (getChanneName(channel.getCh_name(), "0") != 0) {
			this.addFieldError("channel.ch_name", "已经存在该栏目名称");
		}
		if (this.channel.getModule_type().equals("0")) {
			channel.setModule_type("");
		}
		 channel.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(channel);
		if(super.ifvalidatepass){
			validateChannelLevel();
			return add();
		}
		this.channelService.insert(channel);
		validateChannelLevel();
		this.addActionMessage("新增网站栏目信息成功");
		return add();
	}

	/**
	 * 方法描述：修改记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {
		String id = channel.getCh_id();
		//判断实体ID是否存在,若不存在该实体，返回到列表页，不进行任何操作
		if(ValidateUtil.isDigital(id)){
			return list();
		}
	    if (getChanneName(channel.getCh_name(), channel.getCh_id()) != 0) {
			this.addFieldError("channel.ch_name", "已经存在该栏目名称");
		}
	    channel.setPlat_type(mall_type);
		//字段验证
		super.commonValidateField(channel);
		if(super.ifvalidatepass){
			return view();
		}
		this.channelService.update(channel);
		this.addActionMessage("修改网站栏目信息成功");
		return list();
	}

	/**
	 * 方法描述：批量地区排序
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateAllSortNo() throws Exception {
		String channelid = this.admin_Sort_id;
		String channelsortno = this.channel.getSort_no();
		channelid = channelid.replace(" ", "");
		channelsortno = channelsortno.replace(" ", "");
		String channelidStr[] = channelid.split(",");
		String channelsortnoStr[] = channelsortno.split(",");
		List channelList = new ArrayList();
		if (channelidStr.length > 0) {
			HashMap channelMap = new HashMap();
			for (int i = 0; i < channelidStr.length; i++) {
				channelMap.put("sort_no", channelsortnoStr[i]);
				channelMap.put("ch_id", channelidStr[i]);
				channelList.add(channelMap);
			}
		}
		this.channelService.updateChannelSortNo(channelList);
		this.addActionMessage("网站栏目排序成功");
		return list();
	}

	/**
	 * 方法描述：删除记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		String id = this.channel.getCh_id();
		id = id.replace(" ", "");
		Map pageMap = new HashMap();
		pageMap.put("up_ch_id", id);
		int count = this.channelService.getCount(pageMap);
		// 用于判断要删除的地区的子地区是否已经被删除，若没有，则先删除子地区后才可以删除上一级地区；
		if (count == 0) {
			this.channelService.delete(id);
			this.addActionMessage("删除栏目成功");
		} else {
			this.addActionMessage("如果选择栏目有子栏目则无法删除");
		}
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
    //商城排序信息
	public String mallupdateAllSortNo()throws Exception
	{
		setPlatType();
		return updateAllSortNo();
	}
	/**
	 * 方法描述：根据搜索条件列出信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		HashMap pageMap=new HashMap();
		pageMap.put("plat_type", mall_type);
		channelList = this.channelService.getList(pageMap);
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：根据主键找出记录网站栏目信息信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {
		getfilelist();
		setCommpara();
		return goUrl(VIEW);
	}

	/**
	 * 方法描述：取参数表所属模块集合信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void setCommpara(){
		Map pageMap = new HashMap();
    	pageMap.put("state_code", "0");
    	commparaList = this.sysmoduleService.getList(pageMap);
	}
	
	/**
	 * @MethodDescribe 方法描述    加载需要的索引页面
	 * @author  创建人  林俊钦
	 * @date  创建日期  Aug 24, 2011 10:06:00 AM
	 */
	public String luceneindex() throws Exception {
		channelList = this.channelService.getList(new HashMap());
		return goUrl(AUDITLIST);
	}

	/**
	 * 方法描述：初始化加载更新栏目页面信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String auditList() throws Exception {
		channelList = this.channelService.getList( new HashMap());
		return goUrl(AUDITLIST);
	}
	/**
	 * 方法描述：初始化加载更新详细栏目页面信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String audit() throws Exception {
		Map pageMap = new HashMap();
		pageMap.put("module_type", "0");
		channelList = this.channelService.getList(pageMap);
		return goUrl(AUDIT);
	}
    /**
     *执行更行动作的判断
     * @param mchannel
     * @param type
     */
	public void chanvalue(Channel mchannel,String type)
    {
		 DoHtml pHtml = new DoHtml();
		 if(type.equals("1"))//判断请求的动作是：更新栏目页，还是更新详细栏目页；1为：更新栏目页；0：更新详细栏目
		 {
		   //pHtml.doArticleHtml(mchannel);TODO
		 }
		 else {
			 //pHtml.doIndexHtml(mchannel);TODO
		}
    }
	/**
	 * @MethodDescribe 方法描述    更新首页静态化方法
	 * @author  创建人  林俊钦
	 * @throws java.io.IOException
	 * @date  创建日期  Sep 17, 2011 11:18:54 AM
	 */
	public void updateIndexPage() throws IOException{
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		ParseHtml pHtml = new ParseHtml();
		String dir="",filename="",savedir="";
		//获取模板的路径
		if(request.getParameter("dir")!=null){
			dir=request.getParameter("dir");
		}
		//获取生成后的文件名
		if(request.getParameter("filename")!=null){
			filename=request.getParameter("filename");
		}
		//获取生成后的文件保存路径
		if(request.getParameter("savedir")!=null){
			savedir=request.getParameter("savedir");
		}
		//pHtml.doIndexHtml(dir, filename, savedir, null);
	}
	/*
	 * 将List的值，转换为对象的值
	 */
	public Channel switchChannel(HashMap myMap)
	{
		 Channel myChannel=new Channel();
		 myChannel.setArticle_rule(myMap.get("article_rule").toString());
		 myChannel.setArticle_temp(myMap.get("article_temp").toString());
		 myChannel.setCh_id(myMap .get("ch_id").toString());
		 myChannel.setCh_level(myMap.get("ch_level").toString());
		 myChannel.setCh_name(myMap.get("ch_name").toString());
		 myChannel.setCh_title(myMap.get("ch_title").toString());
		 myChannel.setFile_name(myMap.get("file_name").toString());
		 myChannel.setMeta_desc(myMap.get("meta_desc").toString());
		 myChannel.setMeta_keyword(myMap.get("meta_keyword").toString());
		 myChannel.setModule_type(myMap.get("module_type").toString());
		 myChannel.setRemark(myMap.get("remark").toString());
		 myChannel.setSave_dir(myMap.get("save_dir").toString());
		 myChannel.setSort_no(myMap.get("sort_no").toString());
		 myChannel.setTemp_path(myMap.get("temp_path").toString());
		 myChannel.setUp_ch_id(myMap.get("up_ch_id").toString());
		 return myChannel;
	}
	
	/**
	 * 方法描述：AJAX更新栏目信息的方法
	 * 
	 * @throws Exception
	 */
	public void updateChannelPage() throws Exception {
		String preview="";//栏目跟新成功添加预览
		String failString="";// 用于存储失败的栏目名称，用于提示；
		String flagString = "";// 1:表示完全成功，0：表示更新失败；2：部分失败！
		String outString="";//返回前台页面提示的字符串；
		String id = this.update_ch_id;//获取更新ID
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
	    List mychannelList = new ArrayList();
		Map pageMap = new HashMap();
			if (update_state.equals(strall)||update_state .equals(strtwo))//如果请求为：strall:更新全部页，strtwo为请求更新子栏目页
			{	
				if(update_state.equals(strtwo))//strtwo:为请求更新子栏目页。
				{
					pageMap.put("up_ch_id", id);
				}
				mychannelList = this.channelService.getList(pageMap);
				if (mychannelList != null && mychannelList.size() != 0){
					HashMap myMap=new HashMap();
					for(int i=0;i<mychannelList.size()-1;i++)
					{
						myMap=(HashMap)mychannelList.get(i);
						try {
							 chanvalue(switchChannel(myMap),update_type);//执行更新的动作
							 
						} catch (Exception e) {
							 failString+=myMap.get("ch_name").toString()+",";//获取更新失败的栏目名称通过","累加
							 flagString="2"; //flagString=2：表示为更新为完全成功！
						} 
					}
					if(!flagString.equals("2")){
						flagString="1";//更新全部成功的标识串
						
					}
				}
				else {
						flagString = "0";//更新失败的标识串
						channel = this.channelService.get(id);
						preview="<a href='/"+channel.getSave_dir()+channel.getFile_name()+"'>预览</a>";
				}
	        }
			// strone:当选择更行本栏目
		    if (this.update_state.equals(strone)||update_state .equals(strtwo))
			{
				try {
					channel = this.channelService.get(id);
					chanvalue(channel,update_type);//执行更行动作的判断
					flagString=flagstrs(flagString);

				} catch (Exception e) {
					//出现异常的时候，先去判断请求是否为更新子栏目页，和是否在更新子栏目有中有没有出现栏目更新的失败的；
					if(update_state .equals(strtwo)&&!failString.equals(""))
					{
						flagString = "2";//2：表示更新未全部成功，也就是说，有部分栏目更新失败！
						failString+=channel.getCh_name();
					}
					else {
						flagString = "0";
					}
				}
     		} 
		outString=outputString(flagString,failString,preview);//用于处理返回的方法
		out.write(outString);
	}
    /**
     * 判断状态标识
     * @param flagString
     * @return
     */
	public String flagstrs(String flagString)
	{
		String retFlages="";
		if(flagString.equals("2"))
		{
			retFlages="2";
		}
		else {
			retFlages = "1";						
		}
		return retFlages;
	}
	/**
	 * 用于处理返回值的方法flagStr：标识字符串1:表示完全成功，0：表示更新失败；2：部分失败！；failStr：错误提示字符串
	 * @param flagStr
	 * @param failStr
	 * @return
	 */
   public String outputString( String flagStr,String failStr,String preview)
   {
	   String outString="";
		if(flagStr.equals("2"))
		{
			outString=flagStr+"@"+failStr;//与"@"隔开的字符串的格式为"2@首页,资讯..."
			int len=outString.length();
			outString=outString.substring(0, len-1);
		}
		else 
		{
			outString=flagStr+"@"+preview;
		}
	   return outString;
   }
	/**
	 * @return the ChannelList
	 */
	public List getChannelList() {
		return channelList;
	}

	/**
	 * @param channelList
	 *            the ChannelList to set
	 */
	public void setChannelList(List channelList) {
		this.channelList = channelList;
	}

	public String getAdmin_Sort_id() {
		return admin_Sort_id;
	}

	public void setAdmin_Sort_id(String admin_Sort_id) {
		this.admin_Sort_id = admin_Sort_id;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public List getMyfileList() {
		return myfileList;
	}

	public void setMyfileList(List myfileList) {
		this.myfileList = myfileList;
	}

	public String getUpdate_ch_id() {
		return update_ch_id;
	}

	public void setUpdate_ch_id(String update_ch_id) {
		this.update_ch_id = update_ch_id;
	}

	public ParseHtml getParsehtmlService() {
		return parsehtmlService;
	}

	public void setParsehtmlService(ParseHtml parsehtmlService) {
		this.parsehtmlService = parsehtmlService;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public Commpara getCommpara() {
		return commpara;
	}

	public void setCommpara(Commpara commpara) {
		this.commpara = commpara;
	}

	public List getCommparaList() {
		return commparaList;
	}

	public void setCommparaList(List commparaList) {
		this.commparaList = commparaList;
	}

	public String getUpdate_state() {
		return update_state;
	}

	public void setUpdate_state(String update_state) {
		this.update_state = update_state;
	}
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param Channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	/**
	 * 方法描述：在执行这个类中的其他方法前先调用此方法，保证存在实体类
	 * 
	 * @return
	 * @throws Exception
	 */
	public void prepare() throws Exception { super.saveRequestParameter();
		if(channel == null){
			channel = new Channel();
		}
		String id = this.channel.getCh_id();
		if(!ValidateUtil.isDigital(id)){
			channel = this.channelService.get(id);
		}
	}
}
