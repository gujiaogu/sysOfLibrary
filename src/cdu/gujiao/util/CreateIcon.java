package cdu.gujiao.util;

import java.net.URL;

import javax.swing.ImageIcon;

import cdu.gujiao.main.Library;

/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		��ͼƬ��Դ����һ��ͼ��
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class CreateIcon {
	
	/**
	 * @param ImageName	Դͼ������
	 * @return	ImageIcon ����һ��ImageIcon����
	 */
	public static ImageIcon add(String ImageName){
		URL IconUrl = Library.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
