package com.rbt.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;
import com.browseengine.bobo.api.BoboBrowser;
import com.browseengine.bobo.api.BoboIndexReader;
import com.browseengine.bobo.api.Browsable;
import com.browseengine.bobo.api.BrowseException;
import com.browseengine.bobo.api.BrowseFacet;
import com.browseengine.bobo.api.BrowseRequest;
import com.browseengine.bobo.api.BrowseResult;
import com.browseengine.bobo.api.FacetAccessible;
import com.browseengine.bobo.api.FacetSpec;
import com.browseengine.bobo.api.FacetSpec.FacetSortSpec;
import com.browseengine.bobo.facets.FacetHandler;
import com.browseengine.bobo.facets.impl.CompactMultiValueFacetHandler;

public class SearchIndex {

	IndexReader reader = null;
	Analyzer analyzer = null;
	IndexSearcher searcher = null;
	TokenStream tokenStream = null;
	
	private boolean isNotExistIndex = true;

	/**
	 * @author : 林俊钦
	 * @date : Aug 30, 2012 3:25:41 PM
	 * @Method Description :初始化搜索的模块索引
	 */
	public SearchIndex(String filePath){
		// 获取IndexSearcher
		try {
			String fileIndexDir=LuceneUtil.getBasePath()+"/"+filePath;
			File fPath=new File(fileIndexDir);
			reader = IndexReader.open(FSDirectory.open(fPath));
			searcher = new IndexSearcher(reader);
			// 创建一个语法分析器
			analyzer = LuceneUtil.getAnalyzer();
		} catch (Exception e) {
			isNotExistIndex = false;
		}
		
	}

	

	/**
	 * @author : 林俊钦
	 * @throws org.apache.lucene.search.highlight.InvalidTokenOffsetsException
	 * @date : Jul 24, 2012 4:20:08 PM
	 * @Method Description : 查询搜索条件列表的数据
	 */
	public List search(List paraList, Sort sort, int start, int limit) throws ParseException, IOException{
		List list = new ArrayList();
		if(!isNotExistIndex) return list;
		BooleanQuery booleanQuery = getCommonQuery(paraList);
		//返回符合条件的列表数据
		TopDocs result = getCommonIndex(booleanQuery, sort);
		list = getDocList(result.scoreDocs,start,limit);
		closeIO();
		return list;
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 29, 2012 2:55:08 PM
	 * @Method Description :关闭数据源
	 */
	public void closeIO(){
		try {
			reader.close();
			searcher.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 30, 2012 3:27:06 PM
	 * @Method Description :公用的查询索引方法
	 */
	public BooleanQuery getCommonQuery(List paraList) throws ParseException{
		
		BooleanQuery booleanQuery = new BooleanQuery();
		if (paraList == null)
			return null;
		if (paraList.size() == 0)
			return null;

		Query query = null;

		for (Object obj : paraList) {
			ParaModel pm = (ParaModel) obj;
			String search_type = pm.getSearch_type();
			String[] fields = pm.getFields();
			String search_key = pm.getSearch_key();
			String search_value = pm.getSearch_value();
			String start_value = pm.getStart_value();
			String end_value = pm.getEnd_value();

			if (search_type.equals(Constants.NORMAL)) {
				query = normalQuery(search_key, search_value);
			}

			if (search_type.equals(Constants.MULTI)) {
				query = multiFieldQuery(fields, search_value);
			}

			if (search_type.equals(Constants.ONE)) {
				query = termQuery(search_key, search_value);
			}

			if (search_type.equals(Constants.RANGE)) {
				query = rangeQuery(search_key, start_value, end_value);
			}

			booleanQuery.add(query, BooleanClause.Occur.MUST);

		}
		return booleanQuery;
	}
	
	

	/**
	 * @author : 林俊钦
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Jul 24, 2012 4:39:17 PM
	 * @Method Description : 获取符合条件的搜索的记录条件
	 */
	public int getCount(List paraList) throws IOException, ParseException {
		int count=0;
		if(!isNotExistIndex) return count;
		BooleanQuery booleanQuery = getCommonQuery(paraList);
		//返回符合条件的列表数据
		TopDocs result = getCommonIndex(booleanQuery, null);
		//返回条数
		return result.totalHits;
	}

	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @date : Aug 1, 2012 11:27:54 AM
	 * @Method Description :获取分类信息数量的list方法
	 */
	public List catInfoNum(List paraList) throws IOException, ParseException, BrowseException{
		List list= new ArrayList();
		if(!isNotExistIndex) return list;
		String fieldName="cat_attr";
		String fieldLevel="cat_level";
		String level =getInfoLevel(paraList,fieldName,fieldLevel);
		Map<String,FacetAccessible> facetMap = getCommonInfo(paraList,fieldName);
        if(facetMap.size() > 0){
            System.out.println("----------------------分类列表----------------------");                  
            FacetAccessible vendorFacets = facetMap.get(fieldName);  
            List<BrowseFacet> facetVals = vendorFacets.getFacets();  
            for(BrowseFacet f:facetVals){  
            	//找出对应的等级后输出
            	String cat_level = getCatLevel(f.getValue(),fieldLevel);
            	String cat_name = getCatName(f.getValue());
            	System.out.println(level+"================"+cat_level+"==============="+cat_name);
            	if(cat_level.equals(level)){
            		Map listMap=new HashMap();
                	listMap.put("cat_id", f.getValue());
                	listMap.put("cat_name", cat_name);
                	listMap.put("num", f.getHitCount());
                	list.add(listMap);
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");  
            	}           	
            }
        }
		return list;
	}
	
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws java.io.IOException
	 * @throws com.browseengine.bobo.api.BrowseException
	 * @date : Aug 2, 2012 3:45:03 PM
	 * @Method Description : 通过公共的方法搜索
	 */
	private Map getCommonInfo(List paraList,String fieldName) throws ParseException, IOException, BrowseException{
		//搜索的条件
		BooleanQuery booleanQuery = getCommonQuery(paraList);
        //分组开始
        List<FacetHandler<?>> facetHandlers = new ArrayList<FacetHandler<?>>();
        //搜索的形式与搜索的字段
        facetHandlers.add(new CompactMultiValueFacetHandler(fieldName));
        BoboIndexReader boboIndexReader = BoboIndexReader.getInstance(reader,facetHandlers); 
        //搜索实例
        BrowseRequest browseRequest = new BrowseRequest();  
        //browseRequest.setCount(10);  //?
        //browseRequest.setOffset(0);  //?
        browseRequest.setQuery(booleanQuery); 
        //字段的设置
        FacetSpec facetSpec = new FacetSpec();  
        //搜索出来的标签数目  
        facetSpec.setMaxCount(10);
        //分类的排序  
        facetSpec.setOrderBy(FacetSortSpec.OrderHitsDesc);
        browseRequest.setFacetSpec(fieldName, facetSpec);  
        //浏览索引文件  
        Browsable browser = new BoboBrowser(boboIndexReader);  
        //搜索结果
        BrowseResult browseResult = browser.browse(browseRequest);  
        //返回搜索条数
        int totalHits = browseResult.getNumHits();  
        System.out.println("=====Total records: "+totalHits);  
        // 构造符合条件的list
        List list=new ArrayList();             
        Map<String,FacetAccessible> facetMap = browseResult.getFacetMap();  
		return facetMap;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 2, 2012 4:04:57 PM
	 * @Method Description :获取对应分类ID的等级
	 */
	private String getInfoLevel(List paraList,String fieldName,String fieldLevel) throws ParseException, IOException{
		String parent_id="";//点击该分类的id
        if(paraList!=null && paraList.size()>0){
        	for(int i=0;i<paraList.size();i++){
        		ParaModel paraModel =(ParaModel)paraList.get(i);
            	if(paraModel.getSearch_key()!=null && paraModel.getSearch_key().equals(fieldName)){
            		parent_id = paraModel.getSearch_value().toString();
            	}
            	
        	}
        }
        // 设置下一级需要显示分类的等级
        String level="0";
        if(!parent_id.equals("")){
        	level=getCatLevel(parent_id,fieldLevel);
        }
        level=String.valueOf((Integer.parseInt(level)+1));
        return level;
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 30, 2012 11:22:55 AM
	 * @Method Description :获取对应地区的等级
	 */
	private String getAreaInfoLevel(List paraList,String fieldName,String fieldLevel) throws ParseException, IOException{
		String parent_id="";//点击该地区的id
        if(paraList!=null && paraList.size()>0){
        	for(int i=0;i<paraList.size();i++){
        		ParaModel paraModel =(ParaModel)paraList.get(i);
            	if(paraModel.getSearch_key()!=null && paraModel.getSearch_key().equals(fieldName)){
            		parent_id = paraModel.getSearch_value().toString();
            	}
            	
        	}
        }
        // 设置下一级需要显示地区的等级
        String level="0";
        if(!parent_id.equals("")){
        	level=getAreaLevel(parent_id,fieldLevel);
        }
        level=String.valueOf((Integer.parseInt(level)+1));
        return level;
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 1, 2012 5:29:00 PM
	 * @Method Description : 搜索分类信息的等级
	 */
	private String getCatLevel(String cat_id,String fieldName) throws ParseException, IOException{
		String catLevel="1";
		List list =searchCatList(cat_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get(fieldName)!=null){
				catLevel=listMap.get(fieldName).toString();
			}
		}
		return catLevel;
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 30, 2012 11:29:06 AM
	 * @Method Description : 搜索地区信息的等级
	 */
	private String getAreaLevel(String area_id,String fieldName) throws ParseException, IOException{
		String areaLevel="1";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get(fieldName)!=null){
				areaLevel=listMap.get(fieldName).toString();
			}
		}
		return areaLevel;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 2, 2012 1:17:44 PM
	 * @Method Description : 获取地区的信息数据
	 */
	public List areaInfoNum(List paraList) throws IOException, ParseException, BrowseException{
		List list= new ArrayList();
		if(!isNotExistIndex) return list;
		String fieldName="area_attr";
		String fieldLevel="area_level";
		//寻找area_attr中的对应的ID级别
		String level =getAreaInfoLevel(paraList,fieldName,fieldLevel);
		//寻找出上一级ID的等级+1,因为第一级是国家
		level=String.valueOf(Integer.parseInt(level)+1);
		Map<String,FacetAccessible> facetMap = getCommonInfo(paraList,fieldName);
        if(facetMap.size() > 0){
            System.out.println("----------------------地区列表----------------------");                  
            FacetAccessible vendorFacets = facetMap.get(fieldName);  
            List<BrowseFacet> facetVals = vendorFacets.getFacets();  
            for(BrowseFacet f:facetVals){  
            	//找出对应的等级后输出
            	String area_level = getAreaLevel(f.getValue(),fieldLevel);
            	String area_name = getAreaName(f.getValue());
            	System.out.println(level+"=============="+area_level+"=========="+area_name+"============"+"地区ID:"+f.getValue());
            	if(area_level.equals(level)){
            		Map listMap=new HashMap();
                	listMap.put("area_id", f.getValue());
                	listMap.put("area_name", area_name);
                	listMap.put("num", f.getHitCount());
                	list.add(listMap);
                    System.out.println(f.getValue() + "(" + f.getHitCount() + ")");  
            	}           	
            }
        }
		return list;
	}
	
	

	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 2, 2012 1:06:49 PM
	 * @Method Description : 搜索地区的等级
	 */
	private String getAreaLevel(String area_id) throws ParseException, IOException{
		String areaLevel="1";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("area_level")!=null){
				areaLevel=listMap.get("area_level").toString();
			}
		}
		return areaLevel;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 2, 2012 9:55:15 AM
	 * @Method Description : 获取索引分类的名称
	 */
	private String getCatName(String cat_id) throws ParseException, IOException{
		String catName="";
		List list =searchCatList(cat_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("cat_name")!=null){
				catName=listMap.get("cat_name").toString();
			}
		}
		return catName;
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 2, 2012 1:15:16 PM
	 * @Method Description : 获取索引文件中对应的地区名称
	 */
	private String getAreaName(String area_id) throws ParseException, IOException{
		String areaName="";
		List list =searchAreaList(area_id);
		if(list!=null && list.size() > 0){
			HashMap listMap=(HashMap)list.get(0);
			if(listMap.get("area_name")!=null){
				areaName=listMap.get("area_name").toString();
			}
		}
		return areaName;
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 2, 2012 9:46:13 AM
	 * @Method Description : 获取搜索分类的列表
	 */
	private List searchCatList(String cat_id) throws ParseException, IOException{
		List catList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"cat_id"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(cat_id!=null && cat_id.equals("")){
			cat_id=null;
		}
		pm.setSearch_value(cat_id);
		catList.add(pm);
		List list = new SearchIndex("category").search(catList,null, 0, 0);
		return list;
	}

	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Aug 2, 2012 1:08:08 PM
	 * @Method Description :搜索地区的列表
	 */
	private List searchAreaList(String area_id) throws ParseException, IOException{
		List areaList = new ArrayList();
		ParaModel pm = new ParaModel();
		String[] fields = {"area_id"};
		pm.setSearch_type(Constants.MULTI);
		pm.setFields(fields);
		if(area_id!=null && area_id.equals("")){
			area_id=null;
		}
		pm.setSearch_value(area_id);
		areaList.add(pm);
		List list = new SearchIndex("area").search(areaList,null, 0, 0);
		return list;
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @throws org.apache.lucene.queryParser.ParseException
	 * @date : Jul 24, 2012 4:13:42 PM
	 * @Method Description : 获取公共的索引条件
	 */
	public TopDocs getCommonIndex(BooleanQuery booleanQuery,Sort sort) throws IOException, ParseException {
		// 上一页的最后一个document索引
		// 查询搜索引擎
		TopDocs result = null;
		if (sort != null) {
			result = searcher.search(booleanQuery, 1000, sort);
		} else {
			result = searcher.search(booleanQuery, 1000);
		}
		return result;
	}

	/**
	 * @author : 林俊钦
	 * @throws org.apache.lucene.search.highlight.InvalidTokenOffsetsException
	 * @throws java.io.IOException
	 * @date : Jul 26, 2012 10:06:03 AM
	 * @Method Description : 文本高亮方法
	 */
	public String heightLight(BooleanQuery booleanQuery, String fieldName, String fieldValue) throws IOException, InvalidTokenOffsetsException {
		//加亮处理  
		//SimpleHTMLFormatter simplehtml=new SimpleHTMLFormatter("<font color='red'>", "</font>");  
		//Highlighter highlighter = new Highlighter(simplehtml,new QueryScorer(booleanQuery));    

		String reText = fieldValue;
		if (fieldValue != null && fieldName.equals("title")) {
			/// tokenStream = analyzer.tokenStream("title",new StringReader(fieldValue));      
			// String highLightText = highlighter.getBestFragment(tokenStream, fieldValue);  
			/// reText=(highLightText==null?fieldValue:highLightText);
			//Highlighter highlighter = new Highlighter(new QueryScorer(booleanQuery));  
			//TokenStream ts = analyzer.tokenStream("context", new StringReader(fieldValue));  
			//String frament = highlighter.getBestFragment(ts,fieldValue);  
			//System.out.println(frament);  

			// 高亮htmlFormatter对象
			SimpleHTMLFormatter sHtmlF = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
			// 高亮对象
			Highlighter highlighter = new Highlighter(sHtmlF, new QueryScorer(booleanQuery));
			reText = highlighter.getBestFragment(analyzer, "title", fieldValue);
		}
		return reText;
	}

	/**
	 * @author : 林俊钦
	 * @date : Aug 30, 2012 3:29:32 PM
	 * @Method Description : 对搜索的结果分页处理
	 */
	public List getDocList(ScoreDoc[] hits,int start,int limit) throws CorruptIndexException, IOException{
		List list = new ArrayList();
		if(start==0 && limit==0){
			for(ScoreDoc scoredoc: hits){
				Document doc = searcher.doc(scoredoc.doc);
				Map map = docToMap(doc);
				list.add(map);
			}
		}else{
			//获取取搜索记录的索引
			int index=(start-1)*limit;
			int recordLen=index+limit;
			//如果需要显示的最大数大于总的条数时
			if(recordLen>hits.length){
				recordLen=hits.length;
			}
			//寻找分页数据
			if(index<=hits.length){
				for (int i = index; i <recordLen; i++) {
					 Document doc = searcher.doc(hits[i].doc);
					 Map map = docToMap(doc);
					 list.add(map);
				}
			}
		}
		
		return list;
	}

	/**
	 * @author : 林俊钦
	 * @date : Aug 30, 2012 3:29:06 PM
	 * @Method Description :文档转换成map
	 */
	public Map docToMap(Document doc) throws IOException{
		Map map = new HashMap();
		List fieldList = doc.getFields();
		if (fieldList != null && fieldList.size() > 0) {
			for (int i = 0; i < fieldList.size(); i++) {
				Field fa = (Field) fieldList.get(i);
				String name = fa.name();
				String value = fa.stringValue();
				if (name != null) {
					//高亮设置
					//value = heightLight(booleanQuery,name,value);
					map.put(name, value);
				}
			}
		}
		return map;
	}

	/***************************************************************************
	 * 一个或多个字段搜索同一值 或查询 模糊查询
	 **************************************************************************/
	public Query multiFieldQuery(String[] fields, String value) throws ParseException {
		QueryParser parser = new MultiFieldQueryParser(Constants.VERSION, fields, analyzer);
		return parser.parse(value);
	}

	/** 单个字段精确查询 * */
	public Query termQuery(String field, String value) {
		return new TermQuery(new Term(field, value));
	}

	/** 范围查询 包括数字和日期* */
	/** 两个true表示是否包含搜索边界 * */
	public Query rangeQuery(String field, String start, String end) {
		return new TermRangeQuery(field, start, end, true, true);
	}

	/** 普通的字段查询 */
	public Query normalQuery(String field, String value) throws ParseException {
		QueryParser parser = new QueryParser(Constants.VERSION, field, analyzer);
		return parser.parse(value);
	}

}
