package cdu.gujiao.model;

import java.sql.Date;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装图书信息的类
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookInfo {
	private String ISBN;			//图书编号
	private String typeid;			//类别编号
	private String writer;			//作者
	private String translator;		//译者
	private String publisher;		//出版社
	private Date date;				//出版日期
	private Double price;			//书籍价格
	private String bookname;		//图书名称

	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
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

	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getTranslator() {
		return translator;
	}
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
}
