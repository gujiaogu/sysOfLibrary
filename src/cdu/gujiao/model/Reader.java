package cdu.gujiao.model;

import java.sql.Date;

/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		��װ������Ϣ
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Reader {
	private String name;		//��������
	private String sex;			//�����Ա�
	private String age;			//�����Ա�
	private String identityCard;//֤������
	private Date date;			//��Ա֤��Ч����
	private String maxNum;		//��������
	private String tel;			//�绰����
	private Double keepMoney;	//Ѻ��
	private int zj;				//֤������
	private String zy;			//ְҵ
	private String ISBN;		//���߱��
	private Date bztime;		//��֤����
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
