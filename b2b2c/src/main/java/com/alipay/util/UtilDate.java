package com.alipay.util;

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
/**
 * ��ƣ��Զ��嶩����
 * ���ܣ������࣬���������ȡϵͳ���ڡ�������ŵ�
 * �����ԣ�֧����������
 * �汾��2.0
 * ���ڣ�2008-12-25
 * ���ߣ�֧������˾���۲�����֧���Ŷ�
 * jϵ��0571-26888888
 * ��Ȩ��֧������˾
 * */
public class UtilDate {
	public  static String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}
	//��ȡ���ڣ���ʽ��yyyy-MM-dd HH:mm:ss
	public  static String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	
	public static String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	
	//����������λ��
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
	
	public static void main(String[] args) {
		UtilDate date=new UtilDate();
		System.out.println(date.getOrderNum());
	}
	
}
