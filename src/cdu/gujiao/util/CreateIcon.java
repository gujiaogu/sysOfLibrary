package cdu.gujiao.util;

import java.net.URL;

import javax.swing.ImageIcon;

import cdu.gujiao.main.Library;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		用图片资源创建一个图标
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class CreateIcon {
	
	/**
	 * @param ImageName	源图象名字
	 * @return	ImageIcon 返回一个ImageIcon对象
	 */
	public static ImageIcon add(String ImageName){
		URL IconUrl = Library.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
