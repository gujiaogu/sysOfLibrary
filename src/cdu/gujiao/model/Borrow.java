package cdu.gujiao.model;
/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		��װ�鼮������Ϣ
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Borrow {			//�鼮������Ϣ��
	private int id;				//���ı��
	private String bookISBN;	//�鼮���
	private String readerISBN;	//���߱��
	private String num;			//��������
	private String borrowDate;	//��������
	private String backDate;	//Ӧ������
	private String bookName;	//�鼮����
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
