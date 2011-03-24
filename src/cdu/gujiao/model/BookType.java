package cdu.gujiao.model;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装图书类别信息
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookType {			//图书类别信息类
	private String id;			//图书类别编号
	private String typeName;	//图书类别名称
	private String days;		//可借天数
	private String fk;			//迟还一天的罚款数目
	public String getFk() {
		return fk;
	}
	public void setFk(String fk) {
		this.fk = fk;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
