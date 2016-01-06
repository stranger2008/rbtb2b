package com.rbt.index;

import java.io.IOException;
import java.util.List;

public class CreateIndex {
	
	
	// 分类查询的sql
	private String cat_sql="select * from category";
	// 地区查询的sql
	private String area_sql="select * from area";
	// 分类属性查询的sql
	private String categoryattr_sql="select attr_id,attr_name,attr_type,default_val,is_must,sort_no,REPLACE(cat_attr,',','/') AS cat_attr from categoryattr";
	// 分类属性值查询的sql
	private String attrvalue_sql="select c.attr_id,attr_name,attr_type,default_val,is_must,sort_no,REPLACE(cat_attr,',','/') AS cat_attr,a.trade_id,attr_value "+
								"FROM categoryattr c right join attrvalue a on c.attr_id = a.attr_id";
	//公共的删除索引语句
	private String com_deSql="select oper_type,info_id as index_id from indexrecord where module_name=";
	//公共删除索引记录表indexrecord的数据
	private String com_deIndex="DELETE FROM indexrecord WHERE module_name=";

	// 供应查询的sql
	private String supply_sql="select distinct supply_id as index_id,supply_id,supply.cust_id,title,content,m.contact_qq," +
			"m.contact_msn,m.email,supply.info_state,supply.is_recom,m.cust_name,REPLACE(supply.cat_attr,',','/') AS cat_attr," +
			"REPLACE(supply.area_attr,',','/') AS area_attr,supply.supply_type,fare,img_path,img_path as lu_img_path," +
			"onlineorder,LPAD(min_order,17,'0') AS lpad_min_order,min_order,u.user_name,LPAD(price,20,'0') AS lpad_price,price,unit," +
			"onlineorder,shipfee,level_name,IF(level_code IS NULL,0,level_code) level_code,fund_num,supply.in_date," +
			"c_num,m.client_status as status_id, l.level_code,m.client_status,DATE_FORMAT(supply.in_date,'%Y%m%d') as lu_in_date, " +
			" GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc  " +
			" FROM supply LEFT JOIN member m ON supply.cust_id=m.cust_id" +
			" LEFT JOIN memberuser u ON u.cust_id=m.cust_id  AND u.user_type='1'" +
			" LEFT JOIN memberinter t ON t.cust_id=u.cust_id" +
			" LEFT JOIN levelinfo l ON supply.cust_id=l.cust_id" +
			" LEFT JOIN memberlevel b ON b.level_id=l.level_code " +
			" LEFT JOIN membercredit c on supply.cust_id=c.cust_id" +
			" LEFT JOIN infoattr i on i.infoattr_id=supply.infoattr_id";
	
	//求购查询的SQL
	private String buy_sql="select buy_id as index_id,buy.info_state, buy_id,title,buy.content,buy.price,member.contact_qq,member.contact_msn,member.email,member.cust_name,"+
          " buy.in_date,REPLACE(buy.cat_attr,',','/') AS cat_attr,REPLACE(buy.area_attr,',','/') AS area_attr,fare,img_path,buy_type," +
          "img_path as lu_img_path,buy.end_date,DATE_FORMAT(buy.in_date,'%Y%m%d') as lu_in_date,buy.in_date,l.level_code, " +
          "memberuser.user_name,c_num,member.client_status as status_id,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc  " +
          " FROM buy left join member on buy.cust_id=member.cust_id "+
          " left join memberuser on memberuser.cust_id=member.cust_id  and memberuser.user_type='1'"+
		  " left join memberinter on memberinter.cust_id=memberuser.cust_id	"+
		  " left join levelinfo l on buy.cust_id=l.cust_id"+
		  "	left join memberlevel b ON b.level_id=l.level_code"+ 
		  " left join membercredit c on buy.cust_id=c.cust_id" +
		  " LEFT JOIN infoattr i on i.infoattr_id=buy.infoattr_id";
	
	
	//品牌查询的SQL
	private String brand_sql=" SELECT brand.brand_id,brand.brand_id as index_id,brand.cust_id," +
			" brand.title,brand.img_path,brand.img_path as lu_img_path" +
			" ,brand.web_url,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, brand.content,brand.is_recom,DATE_FORMAT(brand.in_date,'%Y%m%d') as lu_in_date," +
			" brand.info_state,brand.no_reason,brand.in_date,brand.clicknum," +
			" brand.fare,brand.user_id,t2.cust_name,t3.user_name,brand.infoattr_id," +
			" REPLACE(brand.cat_attr,',','/') AS cat_attr,REPLACE(brand.area_attr,',','/') AS area_attr " +
			" FROM brand AS brand LEFT JOIN member AS t2 ON brand.cust_id=t2.cust_id " +
			" LEFT JOIN memberuser AS t3 ON t3.cust_id=t2.cust_id  and t3.user_type='1'" +
			" LEFT JOIN infoattr i on i.infoattr_id=brand.infoattr_id ";
	
	//企业查询SQL 还有问题
	private String company_sql=" SELECT member.cust_id,member.recommend, member.cust_id as index_id,cust_name, logo_path,main_product,memberuser.user_name," +
			" member.address,cust_desc,address,cust_name,contact_cellphone,member.logo_path as lu_img_path" +
			" ,contact_qq,contact_msn,member.email,member.in_date,DATE_FORMAT(member.in_date,'%Y%m%d') as lu_in_date," +
			" REPLACE(member.cat_attr,',','/') AS cat_attr,REPLACE(member.area_attr,',','/') AS area_attr," +
			" member.cust_id,level_name,l.level_code,c_num," +
			" member.client_status as status_id,member.client_status,cust_key,mem_type,fund_num,member.info_state" +
			" ,LPAD((YEAR(NOW())-YEAR(member.in_date))*12 + (MONTH(NOW())-MONTH(member.in_date)) +(DAY(NOW())>DAY(member.in_date)),17,'0')  AS nMonth " +
			" FROM member left join memberuser on member.cust_id=memberuser.cust_id and memberuser.user_type='1' " +
			" left join levelinfo l on l.cust_id=member.cust_id " +
			" left join memberlevel on memberlevel.level_id=l.level_code  " +
			" left join memberinter on memberinter.cust_id=member.cust_id" +
			" left join membercredit c on member.cust_id=c.cust_id" ;
	//下载查询SQL
	private String download_sql="SELECT down_id, down_id as index_id,  title,file_path,file_size,download.infoattr_id," +
			"size_unit,version,update_time,file_type,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, img_path as lu_img_path,img_path,DATE_FORMAT(download.in_date,'%Y%m%d') as lu_in_date, " +
			"down_desc,download.in_date,clicknum,fare,down_num,is_recom,download.info_state," +
			" REPLACE(download.cat_attr,',','/') AS cat_attr " +
			" FROM download LEFT JOIN infoattr i on i.infoattr_id=download.infoattr_id ";
	//图库查询SQL
	private String gallery_sql="select gal_id,gal_id as index_id,title,gal_desc,gallery.img_path as lu_img_path," +
			" member.cust_name,gallery.in_date,DATE_FORMAT(gallery.in_date,'%Y%m%d') as lu_in_date," +
			" fare,img_path,gallery.is_recom,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc,  gallery.info_state,gallery.clicknum,  " +
			" REPLACE(gallery.cat_attr,',','/') AS cat_attr " +
			" FROM gallery left join member on gallery.cust_id=member.cust_id" +
			" LEFT JOIN infoattr i on i.infoattr_id=gallery.infoattr_id";
	//招聘查询SQL
	private String job_sql="SELECT title,job_id as index_id,job.in_date,job.infoattr_id,job_id,job.clicknum," +
			" cust_name,org_name,is_recom ,job.cust_id" +
			" ,job.info_state,job.is_trust,job.is_where,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, job.job_type" +
			" ,job.sex,job.marry,job.enducation,REPLACE(job.salary,'~','') AS salary" +
			" ,REPLACE(job.cat_attr,',','/') AS cat_attr,REPLACE(job.area_attr,',','/') AS area_attr" +
			" ,job.workexper,job.in_date ,DATE_FORMAT(job.in_date,'%Y%m%d') as lu_in_date" +
			" FROM job  left join member on job.cust_id=member.cust_id" +
			" LEFT JOIN infoattr i on i.infoattr_id=job.infoattr_id";
	//知道查询SQL
	private String know_sql="SELECT (SELECT COUNT(*) FROM answer WHERE ask_id=ask.ask_id ) AS num," +
			" ask_id,ask_id as index_id,title,is_recom,info_state,REPLACE(ask.cat_attr,',','/') AS cat_attr ," +
			" answer_state,in_date,fare,integral ,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, DATE_FORMAT(ask.in_date,'%Y%m%d') as lu_in_date" +
			" FROM ask LEFT JOIN infoattr i on i.infoattr_id=ask.infoattr_id and ask.info_state='1' ";
	//资讯查询SQL
	private String news_sql="SELECT news_id,news_id as index_id,title,DATE_FORMAT(news.in_date,'%Y%m%d') as lu_in_date," +
			" out_link,in_date,description,source,news_attr,info_state,clicknum," +
			" litpic,content,news.infoattr_id,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, litpic as lu_img_path " +
			" ,REPLACE(news.cat_attr,',','/') AS cat_attr,REPLACE(news.area_attr,',','/') AS area_attr" +
			" from news LEFT JOIN infoattr i on i.infoattr_id=news.infoattr_id";
	//产品查询SQL
	private String product_sql="SELECT product_id,product_id as index_id , title,product.img_path,product.content" +
			" ,product.in_date,clicknum,contact_qq,DATE_FORMAT(product.in_date,'%Y%m%d') as lu_in_date" +
			",REPLACE(product.cat_attr,',','/') AS cat_attr,REPLACE(product.area_attr,',','/') AS area_attr ," +
			" contact_msn,product.infoattr_id,member.email,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, fare,fund_num," +
			" member.cust_name,memberuser.user_name,product.img_path as lu_img_path, GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc,  " +
			" level_name,level_code,c_num,member.client_status as status_id " +
			" ,product.info_state ,levelinfo.level_code,member.client_status" +
			" FROM product left join member on product.cust_id=member.cust_id" +
			" left join memberuser on memberuser.cust_id=member.cust_id  and memberuser.user_type='1' " +
			" left join memberinter on memberinter.cust_id=memberuser.cust_id " +
			" left join  levelinfo on levelinfo.cust_id=member.cust_id  " +
			" left join memberlevel on memberlevel.level_id=levelinfo.level_code " +
			" left join membercredit c on product.cust_id=c.cust_id" +
			" LEFT JOIN infoattr i on i.infoattr_id=product.infoattr_id";
	//简历查询SQL
	private String resume_sql="SELECT resume_id,resume_id as index_id ,real_name,info_state,cust_id" +
			" ,in_date,technical,resume_name,job_type,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, DATE_FORMAT(resume.in_date,'%Y%m%d') as lu_in_date" +
			" ,sex,education,work_exper,spec,marry,is_trust,REPLACE(resume.salary,'~','') AS salary" +
			" ,is_recom,img_path,resume.infoattr_id,clicknum,resume.img_path as lu_img_path " +
			" ,REPLACE(resume.cat_attr,',','/') AS cat_attr,REPLACE(resume.area_attr,',','/') AS area_attr" +
			" FROM resume LEFT JOIN infoattr i on i.infoattr_id=resume.infoattr_id";
	//展会查询SQL
	private String showinfo_sql="SELECT exh_id as index_id ,title,host_unit,address,start_date" +
			" ,end_date,img_path,exh_id,in_date,info_state,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, clicknum,showinfo.img_path as lu_img_path," +
			" IF(TO_DAYS(end_date) > TO_DAYS(NOW()) && TO_DAYS(start_date) < TO_DAYS(NOW()),1,0) AS state_in," +
			" IF(TO_DAYS(start_date) > TO_DAYS(NOW()),1,0) AS state_before, " +
			" IF(TO_DAYS(end_date) < TO_DAYS(NOW()),1,0) AS state_after,showinfo.infoattr_id" +
			" ,REPLACE(showinfo.cat_attr,',','/') AS cat_attr,REPLACE(showinfo.area_attr,',','/') AS area_attr " +
			" ,DATE_FORMAT(showinfo.in_date,'%Y%m%d') as lu_in_date" +
			" FROM showinfo LEFT JOIN infoattr i on i.infoattr_id=showinfo.infoattr_id ";
	//专题查询SQL
	private String subject_sql="SELECT sub_id,sub_id  as index_id,sub_id as index_id, title,in_date,clicknum,sub_desc " +
			" ,is_recom ,info_state,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, img_path,img_path as lu_img_path " +
			",REPLACE(subject.cat_attr,',','/') AS cat_attr,DATE_FORMAT(subject.in_date,'%Y%m%d') as lu_in_date" +
			" FROM subject LEFT JOIN infoattr i on i.infoattr_id=subject.infoattr_id";
	//视频查询SQL
	private String video_sql="SELECT video_id,video_id as index_id,title,img_path,video_path," +
			" clicknum,video.in_date,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, is_recom,video.info_state " +
			" ,REPLACE(video.cat_attr,',','/') AS cat_attr,video.img_path as lu_img_path" +
			" ,DATE_FORMAT(video.in_date,'%Y%m%d') as lu_in_date" +
			" FROM video left join member on member.cust_id=video.cust_id" +
			" LEFT JOIN infoattr i on i.infoattr_id=video.infoattr_id";
	//分类信息索引SQL
	private String classified_sql="select classified.info_id,classified.info_id  as index_id," +
			"classified.title,classified.fare,classified.img_path,classified.img_path as lu_img_path," +
			"classified.info_state,REPLACE(classified.cat_attr,',','/') AS cat_attr,GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc, " +
			"REPLACE(classified.area_attr,',','/') AS area_attr,classified.in_date,t2.mem_type," +
			" GROUP_CONCAT(REPLACE(i.value_id,' ','') SEPARATOR '/') AS attr_desc  " +
			" FROM classified  left join member as t2 on classified.cust_id=t2.cust_id" +
			" LEFT JOIN infoattr i ON i.infoattr_id=classified.infoattr_id " ;
	
	//商品信息索引SQL
	private String goods_sql="SELECT g.goods_id,g.cust_id,g.is_virtual,m.cust_name,REPLACE(g.cat_attr,',','/') AS cat_attr,g.infoattr_id,g.size_attr," +
			" g.volume,g.goods_name,g.goods_no,g.goods_wd,g.brand_id,g.goods_desc,g.img_path," +
			" g.goods_video,g.goods_detail,g.self_cat,g.up_date,g.down_date,g.market_price,g.sale_price,LPAD(g.sale_price,17,'0') AS lu_price,g.cost_price,g.mem_price,g.ship_price," +
			" g.total_stock,g.now_stock,g.warn_stock,g.saled_num,LPAD(g.saled_num,17,'0') AS lu_saled_num,g.give_inter,g.interbuy,g.weight,g.unit,g.is_ship,g.is_volume,g.seo_titel," +
			" g.seo_keyword,g.seo_desc,g.relate_goods,g.give,g.label,g.busi_remark,g.is_del,g.info_state,DATE_FORMAT(g.in_date,'%Y%m%d') as lu_in_date,g.user_id,gb.brand_name,g.no_reason," +
			" IF(TO_DAYS(down_date) > TO_DAYS(NOW()) && TO_DAYS(up_date) < TO_DAYS(NOW()),1,0) AS state_in, " +
			" IF(TO_DAYS(up_date) > TO_DAYS(NOW()),1,0) AS state_before, " +
			" IF(TO_DAYS(down_date) < TO_DAYS(NOW()),1,0) AS state_after" +
			" FROM goods g left join member m on g.cust_id=m.cust_id " +
			" left join goodsbrand gb on g.brand_id=gb.brand_id" +
			" left join commpara c on c.para_key=g.label";
	

	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Sep 20, 2012 9:10:20 AM
	 * @Method Description : 建立商品索引
	 */
	public void CreateGoodsIndex() throws IOException{
		//拼接sql语句
		String sql=goods_sql;
		System.out.println(sql);
		createModelIndex(sql,"goods");
		System.out.println("商品信息全量索引创建成功!");
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Sep 8, 2012 11:10:29 AM
	 * @Method Description : 建立分类信息索引
	 */
	public void createClassifiedIndex() throws IOException{
		//拼接sql语句
		String sql=classified_sql+" GROUP BY classified.infoattr_id ";
		createModelIndex(sql,"classified");
		System.out.println(sql);
		System.out.println("分类信息全量索引创建成功!");
	}

	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立视频模块的全量更新索引文件
	 */
	public void createVideoIndex() throws IOException{
		//拼接sql语句
		String sql=video_sql+" GROUP BY video.infoattr_id";
		createModelIndex(sql,"video");
		System.out.println(sql);
		System.out.println("视频模块全量索引创建成功!");
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立专题模块的全量更新索引文件
	 */
	public void createSubjectIndex() throws IOException{
		//拼接sql语句
		String sql=subject_sql+" GROUP BY subject.infoattr_id";
		createModelIndex(sql,"subject");
		System.out.println(sql);
		System.out.println("专题模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立展会模块的全量更新索引文件
	 */
	public void createShowinfoIndex() throws IOException{
		//拼接sql语句
		String sql=showinfo_sql+" GROUP BY showinfo.infoattr_id";
		createModelIndex(sql,"showinfo");
		System.out.println(sql);
		System.out.println("展会模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立简历模块的全量更新索引文件
	 */
	public void createResumeIndex() throws IOException{
		//拼接sql语句
		String sql=resume_sql+" GROUP BY resume.infoattr_id";
		createModelIndex(sql,"resume");
		System.out.println(sql);
		System.out.println("简历模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立产品模块的全量更新索引文件
	 */
	public void createProductIndex() throws IOException{
		//拼接sql语句
		String sql=product_sql+" GROUP BY product.infoattr_id";
		createModelIndex(sql,"product");
		System.out.println(sql);
		System.out.println("产品模块全量索引创建成功!");
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立资讯模块的全量更新索引文件
	 */
	public void createNewsIndex() throws IOException{
		//拼接sql语句
		String sql=news_sql+" GROUP BY news.infoattr_id";
		createModelIndex(sql,"news");
		System.out.println(sql);
		System.out.println("资讯模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立知道模块的全量更新索引文件
	 */
	public void createKnowIndex() throws IOException{
		//拼接sql语句
		String sql=know_sql+" GROUP BY ask.infoattr_id";;
		createModelIndex(sql,"ask");
		System.out.println(sql);
		System.out.println("知道模块全量索引创建成功!");
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立招聘模块的全量更新索引文件
	 */
	public void createJobIndex() throws IOException{
		//拼接sql语句
		String sql=job_sql+" GROUP BY job.infoattr_id";
		createModelIndex(sql,"job");
		System.out.println(sql);
		System.out.println("招聘模块全量索引创建成功!");
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立图库模块的全量更新索引文件
	 */
	public void createGalleryIndex() throws IOException{
		//拼接sql语句
		String sql=gallery_sql+" GROUP BY gallery.infoattr_id";
		createModelIndex(sql,"gallery");
		System.out.println(sql);
		System.out.println("图库模块全量索引创建成功!");
	}
	
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立下载模块的全量更新索引文件
	 */
	public void createDownloadIndex() throws IOException{
		//拼接sql语句
		String sql=download_sql+" GROUP BY download.infoattr_id";
		createModelIndex(sql,"download");
		System.out.println(sql);
		System.out.println("下载模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立企业模块的全量更新索引文件
	 */
	public void createMemberIndex() throws IOException{
		//拼接sql语句
		String sql=company_sql+" GROUP BY member.cust_id  ";
		createModelIndex(sql,"member");
		System.out.println(sql);
		System.out.println("企业模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立品牌模块的全量更新索引文件
	 */
	public void createBrandIndex() throws IOException{
		//拼接sql语句
		String sql=brand_sql+" GROUP BY brand.infoattr_id";
		createModelIndex(sql,"brand");
		System.out.println(sql);
		System.out.println("品牌模块全量索引创建成功!");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立求购模块的全量更新索引文件
	 */
	public void createBuyIndex() throws IOException{
		//拼接sql语句
		String sql=buy_sql+" GROUP BY buy.infoattr_id";
		createModelIndex(sql,"buy");
		System.out.println(sql);
		System.out.println("求购模块全量索引创建成功!");
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Jul 18, 2012 2:33:53 PM
	 * @Method Description : 建立供应模块的全量更新索引文件
	 */
	public void createSupplyIndex() throws IOException{
		//拼接sql语句
		String sql=supply_sql+" GROUP BY supply.infoattr_id";
		createModelIndex(sql,"supply");
		System.out.println(sql);
		System.out.println("供应模块全量索引创建成功!");
	}
	/**
	 * @param args
	 * 运行的主方法
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {
		CreateIndex ci = new  CreateIndex();
		ci.CreateGoodsIndex();
		ci.createGoodsIncreIndex();
		/*ci.createAreaIncreIndex();
		ci.createCatIndex();
		//测试全量索引
		ci.createAreaIndex();
		ci.createCateAttrIndex();
		ci.createCateAttrValueIndex();
		ci.createDownloadIndex();
		ci.createGalleryIndex();
		ci.createJobIndex();
		ci.createKnowIndex();
		ci.createBuyIndex();
		ci.createMemberIndex();
		ci.createBrandIndex();
		ci.createNewsIndex();
		ci.createProductIndex();
		ci.createResumeIndex();
		ci.createShowinfoIndex();
		ci.createSubjectIndex();
		ci.createVideoIndex();
		ci.createClassifiedIndex();
		ci.createSupplyIndex();
		//测试增量索引
		ci.createSupplyIncreIndex();
		ci.createBuyIncreIndex();
		ci.createBrandIncreIndex();
		ci.createDownloadIncreIndex();
		ci.createGalleryIncreIndex();
		ci.createVideoIncreIndex();
		ci.createJobIncreIndex();
		ci.createMemberIncreIndex();
		ci.createNewsIncreIndex();
		ci.createProductIncreIndex();
		ci.createResumeIncreIndex();
		ci.createShowinfoIncreIndex();
		ci.createSubjectIncreIndex();
		ci.createKnowIncreIndex();
		ci.createClassifiedIncreIndex();
		ci.createCateAttrIndex();
		ci.createCateAttrValueIndex();
		ci.createProductIndex();*/
		
		
	}
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立供应模块的增量更新索引文件
	 */
	public void createSupplyIncreIndex() throws IOException{
		//拼接sql语句
		/*String com_sql = " LEFT JOIN indexrecord r ON  supply.supply_id = r.info_id WHERE r.module_name='supply' and ";
		String gro_sql="  GROUP BY supply.infoattr_id order by r.trade_id asc ";
		String ai_sql=supply_sql+com_sql+" r.oper_type='1' "+gro_sql;
		String au_sql=supply_sql+com_sql+" r.oper_type='2' "+gro_sql;
		String ad_sql=com_deSql+"'supply' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"supply","1");
		//修改索引
		createModelIndex(au_sql,"supply","2");
		//删除索引
		createModelIndex(ad_sql,"supply","3");
		System.out.println("供应模块增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex+"'supply'");*/
		createInfoIncreIndex("supply", "supply_id", supply_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立求购模块的增量更新索引文件
	 */
	public void createBuyIncreIndex() throws IOException{
		
		createInfoIncreIndex("buy", "buy_id", buy_sql);
		
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立品牌模块的增量更新索引文件
	 */
	public void createBrandIncreIndex() throws IOException{
		
		createInfoIncreIndex("brand", "brand_id", brand_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立下载模块的增量更新索引文件
	 */
	public void createDownloadIncreIndex() throws IOException{
		
		createInfoIncreIndex("download", "down_id", download_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立图库模块的增量更新索引文件
	 */
	public void createGalleryIncreIndex() throws IOException{
		
		createInfoIncreIndex("gallery", "gal_id", gallery_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立招聘模块的增量更新索引文件
	 */
	public void createJobIncreIndex() throws IOException{
		
		createInfoIncreIndex("job", "job_id", job_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立企业模块的增量更新索引文件
	 */
	public void createMemberIncreIndex() throws IOException{
		//拼接sql语句
		String com_sql = " LEFT JOIN indexrecord r ON  member.cust_id = r.info_id WHERE r.module_name='member' and ";
		String ai_sql=company_sql+com_sql+" r.oper_type='1' ";
		String au_sql=company_sql+com_sql+" r.oper_type='2' ";
		String ad_sql=com_deSql+"'member' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"member","1");
		//修改索引
		createModelIndex(au_sql,"member","2");
		//删除索引
		createModelIndex(ad_sql,"member","3");
		System.out.println("企业模块增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex+"'member'");
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立资讯模块的增量更新索引文件
	 */
	public void createNewsIncreIndex() throws IOException{
		
		createInfoIncreIndex("news", "news_id", news_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立产品模块的增量更新索引文件
	 */
	public void createProductIncreIndex() throws IOException{
		
		createInfoIncreIndex("product", "product_id", product_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立简历模块的增量更新索引文件
	 */
	public void createResumeIncreIndex() throws IOException{
		
		createInfoIncreIndex("resume", "resume_id", resume_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立展会模块的增量更新索引文件
	 */
	public void createShowinfoIncreIndex() throws IOException{
		
		createInfoIncreIndex("showinfo", "exh_id", showinfo_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立专题模块的增量更新索引文件
	 */
	public void createSubjectIncreIndex() throws IOException{
		
		createInfoIncreIndex("subject", "sub_id", subject_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立专题模块的增量更新索引文件
	 */
	public void createVideoIncreIndex() throws IOException{
		createInfoIncreIndex("video","video_id",video_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立知道模块的增量更新索引文件
	 */
	public void createKnowIncreIndex() throws IOException{
		
		createInfoIncreIndex("ask", "ask_id", know_sql);
	}
	/**
	 * @author : 胡惜坤
	 * @throws java.io.IOException
	 * @date : Aug 27, 2012 4:19:24 PM
	 * @Method Description :  建立分类信息模块的增量更新索引文件
	 */
	public void createClassifiedIncreIndex() throws IOException{
		
		createInfoIncreIndex("classified", "info_id", classified_sql);
		
	}
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Sep 20, 2012 9:16:28 AM
	 * @Method Description : 建立商品增量索引
	 */
	public void createGoodsIncreIndex() throws IOException{
		createInfoIncreIndex("g", "goods_id",goods_sql);
	}
	
	
	/**
	 * @author : 胡惜坤 
	 * @param table_name:模块名称即（表名）
	 * @param table_pk：表主键
	 * @param info_sql：索引的SQL
	 * @throws java.io.IOException
	 * @Method Description : 建立模块的增量更新索引文件
	 */
	public void createInfoIncreIndex(String table_name,String table_pk,String info_sql) throws IOException{
		//拼接sql语句
		String com_sql = " LEFT JOIN indexrecord r ON  "+table_name+"."+table_pk+" = r.info_id WHERE r.module_name='"+table_name+"' and ";
		String gro_sql="  GROUP BY "+table_name+".infoattr_id order by r.trade_id asc ";
		String ai_sql=info_sql+com_sql+" r.oper_type='1' "+gro_sql;
		String au_sql=info_sql+com_sql+" r.oper_type='2' "+gro_sql;
		String ad_sql=com_deSql+"'"+table_name+"' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,table_name,"1");
		//修改索引
		createModelIndex(au_sql,table_name,"2");
		//删除索引
		createModelIndex(ad_sql,table_name,"3");
		System.out.println(table_name+"模块增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex+"'"+table_name+"'");
	}

	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 1, 2012 5:18:04 PM
	 * @Method Description : 建立分类索引
	 */
	public void createCatIndex() throws IOException{
		String sql=cat_sql;
		createModelIndex(sql,"category");
		System.out.println("分类全量索引创建成功!");
	}
	
	
	
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 29, 2012 10:03:18 AM
	 * @Method Description : 建立分类的增量索引
	 */
	public void createCatIncreIndex() throws IOException{
		String com_sql=" LEFT JOIN indexrecord r ON  category.cat_id = r.info_id  WHERE r.module_name='category' AND ";
		String ord_sql=" ORDER BY r.trade_id ASC";
		String ai_sql=cat_sql+com_sql+"oper_type='1'"+ord_sql;
		String au_sql=cat_sql+com_sql+"oper_type='2'"+ord_sql;
		String ad_sql=com_deSql+"'category' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"category","1");
		//修改索引
		createModelIndex(au_sql,"category","2");
		//删除索引
		createModelIndex(ad_sql,"category","3");
		System.out.println("分类增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex+"'category'");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 2, 2012 1:00:46 PM
	 * @Method Description : 建立地区的索引
	 */
	public void createAreaIndex() throws IOException{
		String sql=area_sql;
		createModelIndex(sql,"area");
		System.out.println("地区索引创建成功!");
	}

	
	/**
	 * @author : 林俊钦
	 * @date : Aug 29, 2012 10:47:04 AM
	 * @Method Description : 地区索引的增量更新
	 */
	public void createAreaIncreIndex() throws IOException{
		String com_sql=" LEFT JOIN indexrecord r ON  area.area_id = r.info_id  WHERE r.module_name='area' AND ";
		String ord_sql=" ORDER BY r.trade_id ASC";
		String ai_sql=area_sql+com_sql+"oper_type='1'"+ord_sql;
		String au_sql=area_sql+com_sql+"oper_type='2'"+ord_sql;
		String ad_sql=com_deSql+"'area' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"area","1");
		//修改索引
		createModelIndex(au_sql,"area","2");
		//删除索引
		createModelIndex(ad_sql,"area","3");
		System.out.println("地区增量索引创建成功!");
		//删除需要的记录
		deleteRecord(com_deIndex+"'area'");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 3, 2012 11:28:54 AM
	 * @Method Description : 创建分类属性的索引
	 */
	
	public void createCateAttrIndex() throws IOException{	
		String sql=categoryattr_sql;
		createModelIndex(sql,"categoryattr");
		System.out.println("分类属性索引创建成功!");
	}
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 3, 2012 11:28:54 AM
	 * @Method Description : 创建分类属性的增量索引
	 */
	public void createCateAttrIncreIndex() throws IOException{	
		String com_sql=" LEFT JOIN indexrecord r ON  categoryattr.attr_id = r.info_id  WHERE r.module_name='categoryattr' AND ";
		String ord_sql=" ORDER BY r.trade_id ASC";
		String ai_sql=categoryattr_sql+com_sql+"oper_type='1'"+ord_sql;
		String au_sql=categoryattr_sql+com_sql+"oper_type='2'"+ord_sql;
		String ad_sql=com_deSql+"'categoryattr' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"categoryattr","1");
		//修改索引
		createModelIndex(au_sql,"categoryattr","2");
		//删除索引
		createModelIndex(ad_sql,"categoryattr","3");
		System.out.println("分类属性增量索引创建成功");
		//删除需要的记录
		deleteRecord(com_deIndex+"'categoryattr'");
	}
	
	
	
	/**
	 * @author : 林俊钦
	 * @throws java.io.IOException
	 * @date : Aug 3, 2012 11:28:54 AM
	 * @Method Description : 创建分类属性值的索引
	 */
	public void createCateAttrValueIndex() throws IOException{
		String sql= attrvalue_sql+" ORDER BY sort_no ASC";
		createModelIndex(sql,"attrvalue");
		System.out.println("分类属性值的索引创建成功!");
	}

	/**
	 * @author : 林俊钦
	 * @date : Aug 29, 2012 11:05:14 AM
	 * @Method Description : 创建分类属性值的增量索引
	 */
	public void createCateAttrValueIncreIndex() throws IOException{
		String com_sql=" LEFT JOIN indexrecord r ON  a.trade_id = r.info_id  WHERE r.module_name='attrvalue' AND ";
		String ord_sql="ORDER BY r.trade_id ASC";
		String ai_sql=attrvalue_sql+com_sql+"oper_type='1'"+ord_sql;
		String au_sql=attrvalue_sql+com_sql+"oper_type='2'"+ord_sql;
		String ad_sql=com_deSql+"'attrvalue' and oper_type='3'";
		//新增索引
		createModelIndex(ai_sql,"attrvalue","1");
		//修改索引
		createModelIndex(au_sql,"attrvalue","2");
		//删除索引
		createModelIndex(ad_sql,"attrvalue","3");
		System.out.println("分类属性增量索引创建成功");
		//删除需要的记录
		deleteRecord(com_deIndex+"'attrvalue'");
	}
	
	
	
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 27, 2012 3:28:35 PM
	 * @Method Description : 更新索引方法
	 */
	private void createModelIndex(String sql,String folder,String index_type) throws IOException{
		LuceneUtil lu = new LuceneUtil();
		List list = lu.getSourceList(sql);
		if(list!=null && list.size()>0){
			lu.createIndex(list, folder,index_type);
		}
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 27, 2012 3:28:35 PM
	 * @Method Description : 更新索引默认方法
	 */
	private void createModelIndex(String sql,String folder) throws IOException{
		LuceneUtil lu = new LuceneUtil();
		List list = lu.getSourceList(sql);
		lu.createIndex(list, folder,"4");
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Aug 28, 2012 2:27:40 PM
	 * @Method Description :删除信息记录
	 */
	private void deleteRecord(String sql){
		LuceneUtil lu = new LuceneUtil();
		lu.deleteInfo(sql);
	}
}
