package cdu.gujiao.model;

import java.sql.Date;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装读者信息
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Reader {
	private String name;		//读者姓名
	private String sex;			//读者性别
	private String age;			//读者性别
	private String identityCard;//证件号码
	private Date date;			//会员证有效日期
	private String maxNum;		//最大借书量
	private String tel;			//电话号码
	private Double keepMoney;	//押金
	private int zj;				//证件类型
	private String zy;			//职业
	private String ISBN;		//读者编号
	private Date bztime;		//办证日期
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getBztime() {
		return bztime;
	}
	public void setBztime(Date bztime) {
		this.bztime = bztime;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String isbn) {
		ISBN = isbn;
	}

	public Double getKeepMoney() {
		return keepMoney;
	}
	public void setKeepMoney(Double keepMoney) {
		this.keepMoney = keepMoney;
	}
	public String getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getZj() {
		return zj;
	}
	public void setZj(int zj) {
		this.zj = zj;
	}
	public String getZy() {
		return zy;
	}
	public void setZy(String zy) {
		this.zy = zy;
	}
}
