package cdu.gujiao.model;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装操作员信息的类
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Operater {
	private String id;		//操作员编号
	private String name;	//操作员用户名
	private String grade;	//操作员等级
	private String password;//操作员密码
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}