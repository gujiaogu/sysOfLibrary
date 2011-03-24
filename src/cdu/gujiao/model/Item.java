package cdu.gujiao.model;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Item {
	public String id;
	public String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return getName();
	}
	public Item(String name) {
		super();
		this.name = name;
	}
	public Item() {
		super();
	}
	
}
