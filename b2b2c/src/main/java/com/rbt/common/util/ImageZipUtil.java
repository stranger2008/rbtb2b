/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.util
 * FileName: ImageZipUtil.java 
 */
package com.rbt.common.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
/**
 * @function 功能 图像压缩工具
 * @author  创建人 胡惜坤
 */
public class ImageZipUtil {
    private int wideth;
    private int height;
    private String t = null;

    public void setT(String t) {
        this.t = t;
    }

    public void setWideth(int wideth) {
        // wideth=320;
        this.wideth = wideth;
    }

    public int getWideth() {
        return this.wideth;
    }

    public void setHeight(int height) {
        // height=240;
        this.height = height;
    }
    /**
    * 压缩图片方法
    * 
    * @param oldFile
    *            将要压缩的图片
    * @param width
    *            压缩宽
    * @param height
    *            压缩长
    * @param quality
    *            压缩清晰度 <b>建议为1.0</b>
    * @param smallIcon
    *            压缩图片后,添加的扩展名
    * @return
    */
    public String imageZipProce(String oldFile, int width, int height, float quality) {
        if (oldFile == null) {
            return null;
        }
        String newImage = null;
        try {
            File file = new File(oldFile);
            //文件不存在时
            if(!file.exists())return null;
            /** 对服务器上的临时文件进行处理 */
            Image srcFile = ImageIO.read(file);
            int new_w=0,new_h=0;
            //获取图片的实际大小 高度
            int h=(int)srcFile.getHeight(null);
            //获取图片的实际大小 宽度
            int w=(int)srcFile.getWidth(null);
            // 为等比缩放计算输出的图片宽度及高度
            if((((double)w) >(double)width)||(((double)h)>(double) height))
            {
            	double rate=0;//算出图片比例值
            	//宽度大于等于高度
            	if(w>=h){
            	  rate = ((double) w) / (double) width;
            	}
            	//宽度小于高度
            	else if(h>w) {
            		rate = ((double) h) / (double) height;
				}
            	//构造新的比例的图片高度与宽度值
	            new_w = (int) (((double) w) / rate);
	            new_h = (int) (((double) h) / rate);
	            /** 宽,高设定 */
	            BufferedImage tag = new BufferedImage(new_w, new_h,BufferedImage.TYPE_INT_RGB);
	            tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
	            String filePrex = oldFile.substring(0, oldFile.indexOf('.'));
	            /** 压缩后的文件名 */
	            newImage = filePrex + oldFile.substring(filePrex.length());
	            /** 压缩之后临时存放位置 */
	            FileOutputStream out = new FileOutputStream(newImage);
	            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
	            /** 压缩质量 */
	            jep.setQuality(quality, true);
	            encoder.encode(tag, jep);
	            out.close();
	            srcFile.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newImage;
    }
}