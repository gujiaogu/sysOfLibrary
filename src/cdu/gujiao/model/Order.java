package cdu.gujiao.model;

import java.sql.Date;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装订单信息
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Order {				//书籍订单信息类
	private String ISBN;			//图书编号
	private Date date;				//下单时间
	private String number;			//图书数量
	private String operator;		//操作员
	private String checkAndAccept;	//是否验收
	private String zk;				//书籍折扣
	public String getCheckAndAccept() {
		return checkAndAccept;
	}
	public void setCheckAndAccept(String checkAndAccept) {
		this.checkAndAccept = checkAndAccept;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String isbn) {
		ISBN = isbn;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getZk() {
		return zk;
	}
	public void setZk(String zk) {
		this.zk = zk;
	}
	
}
