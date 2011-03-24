package cdu.gujiao.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Item;


/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		��ѯͼ����𣬲�����ID�ͼ�¼�����װ��Map����
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookTypeIdAndItemMap {
	/**
	 * ���ͼ�����ID�ͼ�¼����
	 */
	static Map map = new HashMap();
	/**
	 * ͼ����������б����ͼģ��
	 */
	public static DefaultComboBoxModel bookTypeModel = new DefaultComboBoxModel();

	/**
	 * @return  Map ��ѯͼ������ID�ͼ�¼�����Map
	 */
	public static Map getMap() {
		List list = Dao.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);

			Item item = new Item();
			item.setId(booktype.getId());
			item.setName(booktype.getTypeName());
			bookTypeModel.addElement(item);
			map.put(item.getId(), item);

		}
		return map;
	}
}
