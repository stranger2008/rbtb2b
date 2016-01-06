/*
 * ISConsole Copyright 2011 ruibaotong COMPANY, Co.ltd . 
 * All rights reserved.
 * Package:com.rbt.common.util
 * FileName: ImageMarkUtil.java 
 */
package com.rbt.common.util;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 图片水印处理
 * @author 胡惜坤
 */
public class ImageMarkUtil {


	/**
	 * 给图片添加水印、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 * @param transparency
	 *            水印透明度 如0.5f
	 * @param sWidth
	 *            水印图片离原图左边框距离百分比
	 * @param sHeight 水印图片离原图上边框距离百分比
	 */
	public  void markImageByIcon(String iconPath, String srcImgPath,String targerPath, Integer degree, float transparency,Integer sWidth, Integer sHeight) 
	{
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}
			// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
			//获取项目的根目录路径
		    String rootPath = PropertiesUtil.getRootpath();
			ImageIcon imgIcon = new ImageIcon(rootPath+iconPath);
			// 得到Image对象。
			Image img = imgIcon.getImage();
			float alpha = transparency; // 透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			ImageIcon imgtarget = new ImageIcon(srcImgPath);
			Image theImg = imgtarget.getImage();
			int  width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			// 表示水印图片的位置
			g.drawImage(img,(int)(width*((float)sWidth/(float)100)), (int)(height*(float)sHeight/(float)100), null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			g.dispose();
			os = new FileOutputStream(targerPath);
			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 给图片添加文字水印、可设置水印的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 * @param transparency
	 *            水印透明度 如0.5f
	 * @param sWidth
	 *            水印图片离原图左边框距离百分比
	 * @param sHeight
	 *           水印图片离原图上边框距离百分比
	 *@param sfontbold 字体是否加粗（0：加粗，1：正常）
	 *@param sforntsize 字体大小        
	 */
	public  void markByText(String logoText, String srcImgPath,String targerPath, Integer degree,
			float transparency,Integer sWidth, Integer sHeight ,Integer sforntsize) {
		// 主图片的路径
		InputStream is = null;
		OutputStream os = null;
		try {
			Image srcImg = ImageIO.read(new File(srcImgPath));

			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 得到画笔对象
			Graphics2D g = buffImg.createGraphics();

			// 设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg
					.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);

			if (null != degree) {
				// 设置水印旋转
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2, (double) buffImg
								.getHeight() / 2);
			}
			// 设置颜色
			g.setColor(Color.black);
			// 设置 Font(字体，字体样式，字体大小) 如font("宋体",Font.BOLD,20)
			g.setFont(new Font("宋体", Font.BOLD, sforntsize));
			// 设置透明度 1f 不透明，0.5f半透明，0f完全透明
			float alpha = transparency;
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			ImageIcon imgIcon = new ImageIcon(srcImgPath);
			Image theImg = imgIcon.getImage();
			int width = theImg.getWidth(null);
			int height = theImg.getHeight(null);
			// 第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y) .
			g.drawString(logoText, width*((float)sWidth/(float)100), height*(float)sHeight/(float)100);

			g.dispose();

			os = new FileOutputStream(targerPath);

			// 生成图片
			ImageIO.write(buffImg, "JPG", os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
