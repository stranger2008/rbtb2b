package com.rbt.common;

import java.io.RandomAccessFile;

import com.rbt.common.util.FileUtil;

public class svnCommit {
	//svn的更新路径
	private static  String svnPath="C:\\Documents and Settings\\Administrator\\桌面\\out.txt";
	//更新的文件夹
	private static  String updatePath="C:\\Documents and Settings\\Administrator\\桌面\\20120924更新内容\\"; 
	//保存本次提交到服务器的文件
	private static  String updateSavePath="C:\\Documents and Settings\\Administrator\\桌面\\更新服务器\\"; 
	//定义Web文件存放的路径
	private static  String updateWebPath=updatePath+"WebRoot\\";
	//定义Src文件存放的路径
	private static  String updateSrcPath=updatePath+"src\\";
	              
	
	
	public static void main(String[] args) {
		runSvnText();
	}
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 17, 2012 10:29:22 AM
	 * @Method Description : 执行svn的记录的text文本
	 */
	public static void runSvnText(){
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(svnPath,"r");
			String temp = null;
			int count=0;
			while (true) {
				temp = raf.readLine();
				if(temp == null){
					break;
				}
				if(searchLine(temp)){
					count++;
				}
			}
			System.out.print("========================总共提交的文件数有"+count+"条========================");
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(raf != null){
				try {
					raf.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	/**
	 * @author : 林俊钦
	 * @date : Jul 17, 2012 10:27:30 AM
	 * @Method Description : 处理行的记录，找出属于sending的行
	 */
	public static boolean searchLine(String lineStr){
		boolean flag=false;
		if(lineStr.indexOf("Sending")>-1 || lineStr.indexOf("Adding")>-1){
			lineStr=lineStr.replace("Sending","").replace("Adding", "").replace(" ","");
			System.out.println(lineStr);
			FileUtil fu=new FileUtil();		
			//如果不是类文件的处理方式
			if(lineStr.indexOf("WebRoot")>-1){
				int len=lineStr.indexOf("WebRoot");
				String newStr=lineStr.substring(len,lineStr.length());
				newStr=newStr.replace("WebRoot/", "");
				//创建目录
				int newlen=newStr.lastIndexOf("/");
				String newFold="";
				if(newlen>-1){
					newFold=newStr.substring(0,newlen);
				}
				
				fu.createFolder(updateWebPath+newFold);
				//提交到服务器文件
				fu.createFolder(updateSavePath+newFold);
				//复制文件
				fu.copyFile(lineStr, updateWebPath+newStr);
				//提交到服务器文件
				fu.copyFile(lineStr, updateSavePath+newStr);
				
				flag=true;
			}else if(lineStr.indexOf("src")>-1){
				//如果是src包下的则执行别的方式
				int len=lineStr.indexOf("src");
				String lines=lineStr;
				String newStr=lines.substring(len,lineStr.length());
				newStr=newStr.replace("src/", "");
				//创建目录
				int newlen=newStr.lastIndexOf("/");
				String newFold="";
				if(newlen>-1){
					newFold=newStr.substring(0,newlen);
				}
				fu.createFolder(updateSrcPath+newFold);
				//复制文件
				fu.copyFile(lineStr, updateSrcPath+newStr);
				
				/***************处理部署文件*****************/
				if(lines.indexOf(".java")>-1 || (lines.indexOf(".xml")>-1 && lines.indexOf("model")>-1 )){
					//存放的.class文件位置
					String classesStr=lines.replace("src","WebRoot\\WEB-INF\\classes").replace("java", "class");
					//更新包提取的存放路径
					String upnewclassStr=newStr.replace("java", "class");
					upnewclassStr="WEB-INF\\classes\\"+upnewclassStr;					
					//创建文件夹
					String upnewclassFold="";
					if(upnewclassStr.lastIndexOf("/")>-1){
						int upLen=upnewclassStr.lastIndexOf("/");
						upnewclassFold=upnewclassStr.substring(0,upLen);
					}
					fu.createFolder(updateWebPath+upnewclassFold);
					fu.createFolder(updateSavePath+upnewclassFold);
					//复制文件
					fu.copyFile(classesStr, updateWebPath+upnewclassStr);
					fu.copyFile(classesStr, updateSavePath+upnewclassStr);
				}
				flag=true;
			}
		}
		return flag;
	}
}
