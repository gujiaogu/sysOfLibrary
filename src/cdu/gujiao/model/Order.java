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
public class Order {				//�鼮������Ϣ��
	private String ISBN;			//ͼ����
	private Date date;				//�µ�ʱ��
	private String number;			//ͼ������
	private String operator;		//����Ա
	private String checkAndAccept;	//�Ƿ�����
	private String zk;				//�鼮�ۿ�
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
