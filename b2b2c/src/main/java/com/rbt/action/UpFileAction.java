/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.action
 * FileName: CkeditorUploadAction.java 
 */
package com.rbt.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.Action;
import com.rbt.common.Constants;
import com.rbt.common.util.FileUtil;
import com.rbt.common.util.ImageMarkUtil;
import com.rbt.common.util.ImageZipUtil;
import com.rbt.common.util.PropertiesUtil;
import com.rbt.function.SysconfigFuc;

/**
 * @function 功能 uploadify文件上传下载功能
 * @author 创建人 胡惜坤
 * @date 创建日期 Jun 20, 2011
 */
@Controller
public class UpFileAction extends BaseAction {

	/*
	 * 定义系统参数配置默认名 cfg_imgtype：图片类型 cfg_attachtype：文件类型 cfg_mediatype：视频格式
	 * cfg_ddimg_width:图片默认宽度 cfg_imagemark 是否开启图片水印 cfg_imagemarktype
	 * 水印类型:("是":为图片水印)("否":为文字水印) cfg_imagemarkdegree 水印图片旋转角度
	 * cfg_imagemarkalpha 水印透明度：(0f为全透明、1f为完全不透明、0.5f为半透明) cfg_imagemarkwidth
	 * 水印图片在图片上的坐标位置(x,y)—x坐标位置百分比% cfg_imagemarkheight
	 * 水印图片在图片上的坐标位置(x,y)—y坐标位置百分比% cfg_imagemaricon 水印图片路径 cfg_imagemarktext
	 * 文字水印的文字标题 cfg_imageforntsize 文字水印的字体大小 cfg_imagemarkbold 是否文字水印字体加粗
	 */
	private final static String CFG_IMGTYPE = "cfg_imgtype";
	private final static String CFG_ATTACHTYPE = "cfg_attachtype";
	private final static String CFG_MEDIATYPE = "cfg_mediatype";
	private final static String CFG_DDIMG_WIDTH = "cfg_ddimg_width";
	//控制缩略图大小(单位:px/像素) 
	private final static String CFG_CTRL_IMG = "cfg_ctrl_img";
	private final static String CFG_IMAGEMARK = "cfg_imagemark";
	private final static String CFG_IMAGEMARKTYPE = "cfg_imagemarktype";
	private final static String CFG_IMAGEMARKDEGREE = "cfg_imagemarkdegree";
	private final static String CFG_IMAGEMARKALAPHA = "cfg_imagemarkalpha";
	private final static String CFG_IMAGEMARKWIDTH = "cfg_imagemarkwidth";
	private final static String CFG_IMAGEMARKHEIGHT = "cfg_imagemarkheight";
	private final static String CFG_IMAGEMARICON = "cfg_imagemaricon";
	private final static String CFG_IMAGEMARKTEXT = "cfg_imagemarktext";
	private final static String CFG_IMAGEFORNTSIZE = "cfg_imageforntsize";
	private final static String CFG_IMAGEMARKBOLD = "cfg_imagemarkbold";
	private final static String CFG_SMALLIMAGE = "cfg_smallimage";
	private final static String CFG_MIDDLEIMAGE = "cfg_middleimage";
	private final static String CFG_BIGIMAGE = "cfg_bigimage";
	// 设置编码格式
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	// 获取项目的根目录
	private String rootPath = PropertiesUtil.getRootpath();
	// 图片默认宽度
	public int IMG_WIDTH = 100;
	public File uploadifyfile;
	// 上传文件根目录
	private final String UPLOADS_PATH = "uploads";

	// images图片上传属性
	// 存放图片文件夹名称
	private static final String IMAGES_PATH = "images";

	// 图片格式限制
	public String IMAGE_TYPE = "";
	// 图片大小限制,单位是M
	private static final int IMAGE_SIZE = 3;

	// flash文件上传属性
	// 存放图片文件夹名称
	private static final String FLASH_PATH = "video";
	// 图片格式限制
	public String FLASH_TYPE = "";
	// 图片大小限制,单位是M
	private static final int FLASH_SIZE = 50;

	// File文件上传的属性
	// 存放图片文件夹名称
	private static final String FILE_PATH = "file";
	// 图片格式限制
	public String FILE_TYPE = "";
	// 图片大小限制,单位是M
	private static final int FILE_SIZE = 10;

	private String temp_file_path = "";

	public String temp_file_type = "";

	public int temp_file_size = 0;

	public String uploadifyfileFileName;
	
	//判断是否需要控制缩略图大小
	public String isImgControl;	
	//判断是否加水印
	public String isYin;
	//判断是否需要控制缩略图大小+水印
	public String isImgControl_isYin;
	
	
	/*
	 * 主要是用于上传Flash的方法
	 */
	public String executeUpFlash() throws Exception {
		// 获取系统参数配置的视频格式
		this.FLASH_TYPE = SysconfigFuc.getSysValue(CFG_MEDIATYPE);
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.FLASH_PATH);
			this.setTemp_file_size(this.FLASH_SIZE);
			this.setTemp_file_type(this.FLASH_TYPE);
		}
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);

		String vertify_message = "";

		if (this.temp_file_type.indexOf(ftype) == -1) {
			vertify_message = this.temp_file_type + " is allowed";
		} else {

			// 获取表单的图片输入流、输出流
			InputStream is = new FileInputStream(this.uploadifyfile);
			// 创建文件
			OutputStream os = new FileOutputStream(new File(strPath
					+ File.separator + filename));

			// 保存文件
			try {
				byte[] buffer = new byte[1024 * 1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
				vertify_message = "upload fail";
			} finally {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}

		// 浏览器访问的地址
		String file_url = "/" + UPLOADS_PATH + "/" + this.temp_file_path + "/"
				+ getYearMonthDay() + "/" + filename;

		// 验证不通过则访问地址置空
		if (!vertify_message.equals("")) {
			file_url = "";
		}
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		response.getWriter().print(file_url);

		return Action.NONE;
	}

	
	
	
	/*
	 * 主要是用于上传文件的方法
	 */
	public String executeUpFile() throws Exception {
		// 获取系统参数配置的文件类型
		this.FILE_TYPE = SysconfigFuc.getSysValue(CFG_ATTACHTYPE).toString();
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.FILE_PATH);
			this.setTemp_file_size(this.FILE_SIZE);
			this.setTemp_file_type(this.FILE_TYPE);
		}
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);

		String vertify_message = "";

		if (this.temp_file_type.indexOf(ftype) == -1) {
			vertify_message = this.temp_file_type + " is allowed";
		} else {

			// 获取表单的图片输入流、输出流
			InputStream is = new FileInputStream(this.uploadifyfile);
			// 创建文件
			OutputStream os = new FileOutputStream(new File(strPath
					+ File.separator + filename));

			// 保存文件
			try {
				byte[] buffer = new byte[1024 * 1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);					
				}
			} catch (Exception e) {
				e.printStackTrace();
				vertify_message = "upload fail";
			} finally {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}

		// 浏览器访问的地址
		String file_url = "/" + UPLOADS_PATH + "/" + this.temp_file_path + "/"
				+ getYearMonthDay() + "/" + filename;

		// 验证不通过则访问地址置空
		if (!vertify_message.equals("")) {
			file_url = "";
		}
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		response.getWriter().print(file_url);

		return Action.NONE;
	}

	/**
	 * @author : 林俊钦
	 * @throws Exception 
	 * @date : May 4, 2012 11:05:11 AM
	 * @Method Description :主要用于商品的大，中，小 三种图片上传
	 */
	public String executeUpGoodsimages() throws Exception{
		// 获取系统参数配置默认缩略图的宽度
		this.IMG_WIDTH = Integer.parseInt(SysconfigFuc
				.getSysValue(CFG_DDIMG_WIDTH));
		// 获取系统参数配置的图片类型
		this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.IMAGES_PATH);
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);
		String filenameremovetype=filename.replace("."+ftype, "");
		String vertify_message = "";
		if (this.temp_file_type.indexOf(ftype) == -1) {
			vertify_message = this.temp_file_type + " is allowed";
		} else {

			// 获取表单的图片输入流、输出流
			InputStream is = new FileInputStream(this.uploadifyfile);
			// 创建文件
			OutputStream os = new FileOutputStream(new File(strPath
					+ File.separator + filename));

			// 保存文件
			try {
				byte[] buffer = new byte[1024 * 1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);
					os.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}

		// 浏览器访问的地址
		String browse_url =UPLOADS_PATH + "/" + this.temp_file_path + "/"
				+ getYearMonthDay();
		String file_url= rootPath+ browse_url + "/" + filename;
		//获取文件去扩展名名称路径
		String fileNameUrl="";
		int len=file_url.lastIndexOf(".");
		if(len>-1){
			fileNameUrl=file_url.substring(0,len);
		}		
		
		//复制大，中，小三张图片
		String smallImg=fileNameUrl+"_small."+ftype;
		FileUtil fu=new FileUtil();
		//复制小图
		fu.copyFile(file_url, smallImg);
		//复制中图
		String middleImg=fileNameUrl+'.'+ftype;
		//复制原图
		String bigImg=fileNameUrl+"_big."+ftype;
		fu.copyFile(file_url, bigImg);		
		String browse_url_big= "/" +browse_url+ "/" +filenameremovetype+ "."+ftype;
		
		HttpServletResponse response = getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		out.write(browse_url_big);
		// 图片加水印处理方法
		// imageMarkProcess(strPath + File.separator + filename);
		// 图片压缩处理方法
		ImageZipUtil imageZipUtil = new ImageZipUtil();
		//压缩小图
		String small_rate= SysconfigFuc.getSysValue(CFG_SMALLIMAGE).toString();
		if(small_rate.indexOf("，")>-1){
			String small[]=small_rate.split("，");
			if(small.length>1){
				int width=Integer.parseInt(small[0].toString());
				int height=Integer.parseInt(small[1].toString());
				imageZipUtil.imageZipProce(smallImg,width, height, 1);
			}
		}		
		//压缩中图
		String middle_rate= SysconfigFuc.getSysValue(CFG_MIDDLEIMAGE).toString();
		if(middle_rate.indexOf("，")>-1){
			String middle[]=middle_rate.split("，");
			if(middle.length>1){
				int width=Integer.parseInt(middle[0].toString());
				int height=Integer.parseInt(middle[1].toString());
				imageZipUtil.imageZipProce(middleImg,width, height, 1);
			}
		}		
		//压缩大图
		String big_rate= SysconfigFuc.getSysValue(CFG_BIGIMAGE).toString();
		if(big_rate.indexOf("，")>-1){
			String big[]=big_rate.split("，");
			if(big.length>1){
				int width=Integer.parseInt(big[0].toString());
				int height=Integer.parseInt(big[1].toString());
				imageZipUtil.imageZipProce(bigImg,width, height, 1);
			}
		}
		return Action.NONE;		
	}
	
	
	
	/*
	 * 主要是用于上传图片的方法
	 */
	public String executeUpimages() throws Exception {
		// 获取系统参数配置默认缩略图的宽度
		this.IMG_WIDTH = Integer.parseInt(SysconfigFuc
				.getSysValue(CFG_DDIMG_WIDTH));
		// 获取系统参数配置的图片类型
		this.IMAGE_TYPE = SysconfigFuc.getSysValue(CFG_IMGTYPE).toString();
		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.IMAGES_PATH);
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
		// 获取文件的存放文件位置
		String strPath = getImageSavePath();
		// 得到上传文件的扩展名
		String ftype = new FileUtil().getFileExt(this.uploadifyfileFileName);
		// 获得当前的系统时间（小时分钟秒）来作为文件名
		String filename = createFileNameByDate(ftype);

		String vertify_message = "";

		if (this.temp_file_type.indexOf(ftype) == -1) {
			vertify_message = this.temp_file_type + " is allowed";
		} else {

			// 获取表单的图片输入流、输出流
			InputStream is = new FileInputStream(this.uploadifyfile);
			// 创建文件
			OutputStream os = new FileOutputStream(new File(strPath
					+ File.separator + filename));

			// 保存文件
			try {
				byte[] buffer = new byte[1024 * 1024];
				while (is.read(buffer) > 0) {
					os.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
				vertify_message = "upload fail";
			} finally {
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
			}
		}

		// 浏览器访问的地址
		String file_url = "/" + UPLOADS_PATH + "/" + this.temp_file_path + "/"
				+ getYearMonthDay() + "/" + filename;

		// 验证不通过则访问地址置空
		if (!vertify_message.equals("")) {
			file_url = "";
		}
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		// 返回给ckeditor
		response.getWriter().print(file_url);
		//判断是否是缩略图
		if(isImgControl!=null && !"".equals(isImgControl)){
			String cfg_ctrl=SysconfigFuc.getSysValue(CFG_CTRL_IMG);
			if(cfg_ctrl!=null && !cfg_ctrl.equals("")){
				//缩略图----图片压缩处理方法
				zipimagesproces(strPath + File.separator + filename,cfg_ctrl);
			}
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE) && "1".equals(this.getSession().getAttribute("isshuiyin")))
			{
				perMarkProcess(strPath + File.separator + filename);
			}
		}
		
		// 大图片加水印处理方法
		else if(isYin!=null &&  !"".equals(isYin)){
			String cfg_ddimg = SysconfigFuc.getSysValue(CFG_DDIMG_WIDTH);
			if(cfg_ddimg!=null && !cfg_ddimg.equals("")){
				//图片压缩处理方法(大图片)
				zipimagesproces(strPath + File.separator + filename,cfg_ddimg);
			}			
			//用户类型会员  
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE) && "1".equals(this.getSession().getAttribute("isshuiyin")))
			{
				perMarkProcess(strPath + File.separator + filename);
			}else{
				// 图片加水印处理方法
				imageMarkProcess(strPath + File.separator + filename);
			}
		}
		// 缩略图--图片压缩+加水印处理方法
		else if(isImgControl_isYin!=null && !"".equals(isImgControl_isYin)){
			String cfg_ctrl=SysconfigFuc.getSysValue(CFG_CTRL_IMG);
			if(cfg_ctrl!=null && !cfg_ctrl.equals("")){
				//缩略图----图片压缩处理方法
				zipimagesproces(strPath + File.separator + filename,cfg_ctrl);
			}
			//用户类型会员  
			if(this.session_cust_type.equals(Constants.MEMBER_TYPE) && "1".equals(this.getSession().getAttribute("isshuiyin")))
			{
				perMarkProcess(strPath + File.separator + filename);
			}else{
				// 图片加水印处理方法
				imageMarkProcess(strPath + File.separator + filename);
			}
			
		}
		else{
			String cfg_ddimg = SysconfigFuc.getSysValue(CFG_DDIMG_WIDTH);
			if(cfg_ddimg!=null && !cfg_ddimg.equals("")){
				//图片压缩处理方法
				zipimagesproces(strPath + File.separator + filename,cfg_ddimg);
			}
		}
		return Action.NONE;
	}
		

	/*
	 * 图片压缩处理方法
	 */
   public void zipimagesproces(String image_paths,String image_width)
   {
	   ImageZipUtil imageZipUtil = new ImageZipUtil();
	   Integer sWidthImage = 0;//图片最大宽度，从配置表中取配置信息
	   if ( image_width!= null&& !image_width.equals("")&& !image_width.equals("0")) {
			sWidthImage = Integer.parseInt(image_width);
			//处理压缩的方法的，image_paths：图片的路径，sWidthImage：图片的最大宽度，1：为压缩清晰度
			imageZipUtil.imageZipProce(image_paths,sWidthImage, sWidthImage, 1);
		}
   }
     
   
   public void perMarkProcess(String image_path){

	    // 实例化水印图片处理类
		ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
		String iconPath = "", logoText = "";
		Integer degree = 0, sWidth = 0, sHeight = 0, sforntsize = 0;
		Float transparency;// 水印透明度
		if (!SysconfigFuc.getSysValue(CFG_IMAGEMARK).equals("1")) {
	     // 从系统配置表中获取配置图片水印信息
		// 水印图标
		iconPath = SysconfigFuc.getSysValue(CFG_IMAGEMARICON);
		// 水印旋转度数
		degree = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKDEGREE));
		// 水印距离原图左边框的百分比
		sWidth = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKWIDTH));
		// 水印距离原图上边框的百分比
		sHeight = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEMARKHEIGHT));
		// 水印透明度
		transparency = Float.parseFloat(SysconfigFuc.getSysValue(CFG_IMAGEMARKALAPHA));
		// 文字水印的字体大小
		sforntsize = Integer.parseInt(SysconfigFuc.getSysValue(CFG_IMAGEFORNTSIZE));
		//水印文字用户名称
		logoText = this.session_user_name;
		imageMarkUtil.markByText(logoText, image_path, image_path,
				degree, transparency, sWidth, sHeight, sforntsize);
		} 
		this.getSession().setAttribute("isshuiyin", "");
   }
     
   
	/*
	 * 图片水印处理方法
	 */
	public void imageMarkProcess(String image_path) {
		// 实例化水印图片处理类
		ImageMarkUtil imageMarkUtil = new ImageMarkUtil();
		String iconPath = "", logoText = "";
		Integer degree = 0, sWidth = 0, sHeight = 0, sforntsize = 0;
		Float transparency;// 水印透明度
		if (!SysconfigFuc.getSysValue(CFG_IMAGEMARK).equals("1")) {
			// 从系统配置表中获取配置图片水印信息
			// 水印图标
			iconPath = SysconfigFuc.getSysValue(CFG_IMAGEMARICON);
			// 水印旋转度数
			degree = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKDEGREE));
			// 水印距离原图左边框的百分比
			sWidth = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKWIDTH));
			// 水印距离原图上边框的百分比
			sHeight = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKHEIGHT));
			// 水印透明度
			transparency = Float.parseFloat(SysconfigFuc
					.getSysValue(CFG_IMAGEMARKALAPHA));
			// 文字水印的字体大小
			sforntsize = Integer.parseInt(SysconfigFuc
					.getSysValue(CFG_IMAGEFORNTSIZE));
			// 文字水印的标题的文字
			logoText = SysconfigFuc.getSysValue(CFG_IMAGEMARKTEXT);
			// 判断为图片水印类型
			if (SysconfigFuc.getSysValue(CFG_IMAGEMARKTYPE).equals("0")) {
				imageMarkUtil.markImageByIcon(iconPath, image_path, image_path,
						degree, transparency, sWidth, sHeight);
			}
			// 判断为文字水印类型
			else if (SysconfigFuc.getSysValue(CFG_IMAGEMARKTYPE).equals("1")) {
				imageMarkUtil.markByText(logoText, image_path, image_path,
						degree, transparency, sWidth, sHeight, sforntsize);
			}
		}
	}

	// 暂时不使用该方法
	public String execute() throws ServletException, IOException {

		if (this.temp_file_path.equals("")) {
			this.setTemp_file_path(this.IMAGES_PATH);
			this.setTemp_file_size(this.IMAGE_SIZE);
			this.setTemp_file_type(this.IMAGE_TYPE);
		}
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		// 获取文件的存放文件位置
		String savePath = getImageSavePath();
		// String savePath = "F:/";
		System.out.print("路径为：======:" + savePath);
		// savePath = savePath + "/uploads/"+getYearMonthDay()+"/";
		File f1 = new File(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("UTF-8");
		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
			System.out.print("文件个数为：======:" + fileList.size());
		} catch (FileUploadException ex) {
			return null;
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				long size = item.getSize();
				String type = item.getContentType();
				System.out.println(size + "字节" + " " + type);
				if (name == null || name.trim().equals("")) {
					continue;
				}

				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				File file = null;
				do {
					Date current = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
					name = sdf.format(current);
					file = new File(savePath + name + extName);
				} while (file.exists());
				File saveFile = new File(savePath + name + extName);
				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		response.getWriter().print(name + extName);
		return Action.NONE;
	}

	// 获得当前的系统时间（小时分钟秒）来作为文件名
	// ftype：文件扩展名
	public String createFileNameByDate(String ftype) {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		String filename = sdf.format(current) + "." + ftype;
		return filename;
	}

	// 获取图片存放的服务器地址
	public String getImageSavePath() {
		// 获取文件的存放文件位置
		String strPath = PropertiesUtil.getRootpath() + UPLOADS_PATH
				+ File.separator + this.temp_file_path;

		// 获取当天日期作为文件夹
		String datePath = getYearMonthDay();

		// 文件最终保存的文件夹
		strPath = strPath + File.separator + datePath;

		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return strPath;
	}

	// 获取当前年月日
	public String getYearMonthDay() {
		Date current = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(current);
	}

	public File getUploadifyfile() {
		return uploadifyfile;
	}

	public void setUploadifyfile(File uploadifyfile) {
		this.uploadifyfile = uploadifyfile;
	}

	public String getUPLOADS_PATH() {
		return UPLOADS_PATH;
	}

	public String getIMAGES_PATH() {
		return IMAGES_PATH;
	}

	public String getTemp_file_path() {
		return temp_file_path;
	}

	public void setTemp_file_path(String temp_file_path) {
		this.temp_file_path = temp_file_path;
	}

	public String getTemp_file_type() {
		return temp_file_type;
	}

	public void setTemp_file_type(String temp_file_type) {
		this.temp_file_type = temp_file_type;
	}

	public int getTemp_file_size() {
		return temp_file_size;
	}

	public void setTemp_file_size(int temp_file_size) {
		this.temp_file_size = temp_file_size;
	}

	public String getIMAGE_TYPE() {
		return IMAGE_TYPE;
	}

	public int getIMAGE_SIZE() {
		return IMAGE_SIZE;
	}

	public String getUploadifyfileFileName() {
		return uploadifyfileFileName;
	}

	public void setUploadifyfileFileName(String uploadifyfileFileName) {
		this.uploadifyfileFileName = uploadifyfileFileName;
	}
	
	public String getIsImgControl() {
		return isImgControl;
	}
	public void setIsImgControl(String isImgControl) {
		this.isImgControl = isImgControl;
	}

	public String getIsYin() {
		return isYin;
	}
	public void setIsYin(String isYin) {
		this.isYin = isYin;
	}
	public String getIsImgControl_isYin() {
		return isImgControl_isYin;
	}

	public void setIsImgControl_isYin(String isImgControl_isYin) {
		this.isImgControl_isYin = isImgControl_isYin;
	}
	
	

}
