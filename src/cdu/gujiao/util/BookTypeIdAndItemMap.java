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
 * 版权：		成都大学毕业设计		 	
 * 描述：		查询图书类别，并将其ID和记录本身封装到Map里面
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookTypeIdAndItemMap {
	/**
	 * 存放图书类别ID和记录本身
	 */
	static Map map = new HashMap();
	/**
	 * 图书类别下拉列表框视图模型
	 */
	public static DefaultComboBoxModel bookTypeModel = new DefaultComboBoxModel();

	/**
	 * @return  Map 查询图书类别的ID和记录本身的Map
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
