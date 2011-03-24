package cdu.gujiao.util;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		����JTextField���볤��
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class MyDocument extends PlainDocument{ 
	   int maxLength =10; 
	   public MyDocument(int newMaxLength){ 
	      super(); 
	      maxLength = newMaxLength; 
	   } 
	   public MyDocument(){ 
	      this(10); 
	   } 

	   /*���ظ����insertString���� �����ĵ��в���ĳЩ����*/
	    public void insertString(int offset, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() > maxLength) {	//����ٶ�������Ƴ���Ϊ10 
			return;
		} else {
			super.insertString(offset, str, a);

		}
	} 

} 
