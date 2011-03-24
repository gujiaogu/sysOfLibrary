package cdu.gujiao.model;
/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		封装书籍借阅信息
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Borrow {			//书籍借阅信息类
	private int id;				//借阅编号
	private String bookISBN;	//书籍编号
	private String readerISBN;	//读者编号
	private String num;			//借书数量
	private String borrowDate;	//借书日期
	private String backDate;	//应还日期
	private String bookName;	//书籍名称
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBackDate() {
		return backDate;
	}
	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}
	public String getBookISBN() {
		return bookISBN;
	}
	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}
	public String getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getReaderISBN() {
		return readerISBN;
	}
	public void setReaderISBN(String readerISBN) {
		this.readerISBN = readerISBN;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
