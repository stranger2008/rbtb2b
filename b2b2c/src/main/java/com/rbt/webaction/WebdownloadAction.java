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
import org.apache.lucene.search.SortField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.browseengine.bobo.api.BrowseException;
import com.rbt.common.util.FileUpDownFileUtil;
import com.rbt.function.ToolsFuc;
import com.rbt.index.SearchIndex;
import com.rbt.model.Download;
import com.rbt.service.IDownloadService;


/**
 * @function 功能   前台知道列表页
 * @author  创建人  林俊钦
 * @date  创建日期  Sep 1, 2011 8:59:52 AM
 */
@Controller
public class WebdownloadAction extends WebbaseAction {
	
	private static final long serialVersionUID = -1550490740839219956L;
	/*
	 * 业务层接口
	 */

	@Autowired
	public IDownloadService downloadService;	
	/*
	 * 下载表信息集合
	 */
	public List downloadList;
	public List downTopList;
	public List recomList;    
	public String downpath;
	public String downid;
    
    /*
	 * 接收前台参数值
	 */
    public String sortbycondition;
    
    /*
	 * 下载模块名称
	 */
    private String module_type="download";	
    
	/**
	 * @Method Description :前台下载列表的绑定
	 * @author : 林俊钦
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @date : Nov 1, 2011 4:22:48 PM
	 */
	public String list() throws IOException, ParseException, BrowseException {		
		// 设置网页位置
		super.setPage_position(this.module_type);			
		
		//构造list列表搜索条件
		List shList = new ArrayList();
		// 找通过审核的记录
		shList = normalSearch("info_state","1",shList);
		Sort sort=new  Sort();  
		if (!reqKeyword("title",module_type, shList)) {

			//根据分类ID值查找出相应的分类列表
			shList = this.normalSearch("cat_attr", cat_id, shList);
			//根据地区ID值查找出相应的分类列表
			shList = this.normalSearch("area_attr", area_id, shList);
			//搜索标题
			shList = this.normalSearch("title", searchText, shList);
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
			if(this.sortbycondition!=null&&!this.sortbycondition.equals("")){
				//判断按时间还是点击量排序
				if(this.sortbycondition.equals("1")){
					SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序  
					sort.setSort(new  SortField[]{sf}); 
				}else{
					SortField sf=new  SortField("clicknum", SortField.STRING,true);//降序  
					sort.setSort(new  SortField[]{sf});
				}
			}else{
				sortbycondition="1";
				SortField sf=new  SortField("lu_in_date", SortField.STRING,true);//降序  
				sort.setSort(new  SortField[]{sf}); 
			}
		}	
		//如果搜索内容为空则不搜索
		if (is_souch) {
			//计算符合条件条数
			SearchIndex si=new SearchIndex(module_type);
			int count=si.getCount(shList);
    		infoCount=count;
			//lucene的分页插件
			lucenePageTool(count);
			//分类信息分组
			cateList = si.catInfoNum(shList);
			//搜索符合条件的list
			downloadList = si.search(shList, sort , pages_s, pageSize_s);  
			// 替换list表中字段的处理
			downloadList = ToolsFuc.replaceList(downloadList);
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
		//绑定下载排行
		SearchIndex si=new SearchIndex(module_type);
		List downTop1List = new ArrayList();
		downTop1List = normalSearch("info_state","1",downTop1List);
		downTopList = si.search(downTop1List, null , 1, 10); 
		
		//推荐下载列表
		SearchIndex si1=new SearchIndex(module_type);
		List recom1List = new ArrayList();
		recom1List = normalSearch("info_state","1",recom1List);
		recom1List = normalSearch("is_recom","1",recom1List);
		recomList = si1.search(recom1List, null , 1, 10); 

		return goUrl("downloadList");
	}
	
	
	public void downloadFile() throws Exception{
        FileUpDownFileUtil fudf=new FileUpDownFileUtil();
        if(downpath!=null && !downpath.equals("")){
        	fudf.downloadWebFile(downpath);
        }
        //更新下载次数
        updatedownnum();
	}
	
	/**
	 * @Method Description :前台更新下载次数
	 * @author : 胡惜坤
	 */
	public void updatedownnum()throws Exception
	{
		String outprintString = "1";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//更新下载次数
		if(request.getParameter("id")!=null&&!"".equals(request.getParameter("id")))
		{
			String downid=request.getParameter("id").toString();
			downloadService.updateDownNum(downid);
			Download downmodel=new Download();
			downmodel=downloadService.get(downid);
			if(downmodel!=null&&downmodel.getDown_num()!=null)
			{
				outprintString=downmodel.getDown_num();
			}
		}
		out.write(outprintString);
	}
	//页面加载的时候获取下载次数
	public void Getdownnum()throws Exception
	{
		String outprintString = "0";
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("id")!=null&&!"".equals(request.getParameter("id")))
		{
			String downid=request.getParameter("id").toString();
			Download downmodel=new Download();
			downmodel=downloadService.get(downid);
			if(downmodel!=null&&downmodel.getDown_num()!=null)
			{
				outprintString=downmodel.getDown_num();
			}
		}
		
		out.write(outprintString);
	}
}
