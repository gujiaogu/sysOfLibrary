package cdu.gujiao.main;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JInternalFrame;

import cdu.gujiao.iframe.BookAddIFrame;
import cdu.gujiao.iframe.BookBackIFrame;
import cdu.gujiao.iframe.BookBorrowIFrame;
import cdu.gujiao.iframe.BookModiAndDelIFrame;
import cdu.gujiao.iframe.BookSearchIFrame;
import cdu.gujiao.iframe.BookTypeAddIFrame;
import cdu.gujiao.iframe.BookTypeModiAndDelIFrame;
import cdu.gujiao.iframe.GengGaiMiMa;
import cdu.gujiao.iframe.ReaderAddIFrame;
import cdu.gujiao.iframe.ReaderModiAndDelIFrame;
//import cdu.gujiao.iframe.UserAddIFrame;
//import cdu.gujiao.iframe.UserModiAndDelIFrame;
import cdu.gujiao.iframe.newBookCheckIFrame;
import cdu.gujiao.iframe.newBookOrderIFrame;
import cdu.gujiao.util.*;

/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		�˵��Ͱ�ť��Action����
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class MenuActions {
	/**
	 * �Ӵ��弯��
	 */
	private static Map<String, JInternalFrame> frames; 					
	
	/**
	 * �޸����봰�嶯��
	 */
	public static PasswordModiAction MODIFY_PASSWORD; 					
	/**
	 * ͼ���������嶯��
	 */
	public static BookSearchAction BOOK_SEARCH; 						
	/**
	 * ͼ��黹���嶯��
	 */
	public static GiveBackAction GIVE_BACK; 							
	/**
	 * ͼ����Ĵ��嶯��
	 */
	public static BorrowAction BORROW; 									
	/**
	 * �������ն���
	 */
	public static CheckAndAcceptNewBookAction NEWBOOK_CHECK_ACCEPT;		
	/**
	 * ���鶨�����嶯��
	 */
	public static BoodOrderAction NEWBOOK_ORDER; 						
	/**
	 * ͼ�������޸Ĵ��嶯��
	 */
	public static BookTypeModiAction BOOKTYPE_MODIFY; 					
	/**
	 * ͼ��������Ӵ��嶯��
	 */
	public static BookTypeAddAction BOOKTYPE_ADD; 						
	/**
	 * ������Ϣ���嶯��
	 */
	public static ReaderModiAction READER_MODIFY; 						
	/**
	 * ������Ϣ��Ӵ��嶯��
	 */
	public static ReaderAddAction READER_ADD; 							
	/**
	 * ͼ����Ϣ�޸Ĵ��嶯��
	 */
	public static BookModiAction BOOK_MODIFY; 							
	/**
	 * ͼ����Ϣ��Ӵ��嶯��
	 */
	public static BookAddAction BOOK_ADD; 								
	/**
	 * ϵͳ�˳�����
	 */
	public static ExitAction EXIT; 										

	static {
		frames = new HashMap<String, JInternalFrame>();
		MODIFY_PASSWORD = new PasswordModiAction();
		BOOK_SEARCH = new BookSearchAction();
		GIVE_BACK = new GiveBackAction();
		BORROW = new BorrowAction();
		NEWBOOK_CHECK_ACCEPT = new CheckAndAcceptNewBookAction();
		NEWBOOK_ORDER = new BoodOrderAction();
		BOOKTYPE_MODIFY = new BookTypeModiAction();
		BOOKTYPE_ADD = new BookTypeAddAction();
		READER_MODIFY = new ReaderModiAction();
		READER_ADD = new ReaderAddAction();
		BOOK_MODIFY = new BookModiAction();
		BOOK_ADD = new BookAddAction();
		EXIT = new ExitAction();
	}

	/**
	 * �޸����봰�嶯��
	 */
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			super("���Ŀ���",null);				//��ָ���ַ�������һ��Action����
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("��������")
					||frames.get("��������").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("��������", iframe);
				Library.addIFame(frames.get("��������"));
			}
		}
	}


	/**
	 * ͼ���������嶯��
	 */
	private static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("ͼ������", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���ѯ")
					||frames.get("ͼ���ѯ").isClosed()) {
				BookSearchIFrame iframe=new BookSearchIFrame();
				frames.put("ͼ���ѯ", iframe);
				Library.addIFame(frames.get("ͼ���ѯ"));
			}
		}
	}

	/**
	 * ͼ��黹���嶯��
	 */
	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("ͼ��黹", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��黹����")
					||frames.get("ͼ��黹����").isClosed()) {
				BookBackIFrame iframe=new BookBackIFrame();
				frames.put("ͼ��黹����", iframe);
				Library.addIFame(frames.get("ͼ��黹����"));
			}
		}
	}

	/**
	 * ͼ����Ĵ��嶯��
	 */
	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("ͼ�����", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ĺ���")
					||frames.get("ͼ����Ĺ���").isClosed()) {
				BookBorrowIFrame iframe=new BookBorrowIFrame();
				frames.put("ͼ����Ĺ���", iframe);
				Library.addIFame(frames.get("ͼ����Ĺ���"));
			}
		}
	}

	/**
	 * �������ն���
	 */
	private static class CheckAndAcceptNewBookAction 
					extends AbstractAction {
		CheckAndAcceptNewBookAction() {
			super("��������", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("ͼ������")
					||frames.get("ͼ������").isClosed()) {
				newBookCheckIFrame iframe=new newBookCheckIFrame();
				frames.put("ͼ������", iframe);
				Library.addIFame(frames.get("ͼ������"));
			}
		}
	}

	/**
	 * ���鶨�����嶯��
	 */
	private static class BoodOrderAction extends AbstractAction {
		BoodOrderAction() {
			super("���鶨��", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("���鶩������")
					||frames.get("���鶩������").isClosed()) {
				newBookOrderIFrame iframe=new newBookOrderIFrame();
				frames.put("���鶩������", iframe);
				Library.addIFame(frames.get("���鶩������"));
			}
		}
	}

	/**
	 * ͼ�������޸Ĵ��嶯��
	 */
	private static class BookTypeModiAction extends AbstractAction {
		BookTypeModiAction() {
			super("ͼ������޸�", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ������޸�")
					||frames.get("ͼ������޸�").isClosed()) {
				BookTypeModiAndDelIFrame iframe=new BookTypeModiAndDelIFrame();
				frames.put("ͼ������޸�", iframe);
				Library.addIFame(frames.get("ͼ������޸�"));
			}
		}
	}

	/**
	 * ͼ��������Ӵ��嶯��
	 */
	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("ͼ��������", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ��������")
					||frames.get("ͼ��������").isClosed()) {
				BookTypeAddIFrame iframe=new BookTypeAddIFrame();
				frames.put("ͼ��������", iframe);
				Library.addIFame(frames.get("ͼ��������"));
			}
		}
	}
	
	/**
	 * ������Ϣ���嶯��
	 */
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("�����޸���ɾ��", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("������Ϣ�޸���ɾ��")
					||frames.get("������Ϣ�޸���ɾ��").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("������Ϣ�޸���ɾ��", iframe);
				Library.addIFame(frames.get("������Ϣ�޸���ɾ��"));
			}
		}
	}

	/**
	 * ������Ϣ��Ӵ��嶯��
	 */
	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("������Ϣ���", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("���������Ϣ���")
					||frames.get("���������Ϣ���").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("���������Ϣ���", iframe);
				Library.addIFame(frames.get("���������Ϣ���"));
			}
		}
	}
	/**
	 * ͼ����Ϣ�޸Ĵ��嶯��
	 */
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("ͼ���޸�", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ���޸�")
					||frames.get("ͼ���޸�").isClosed()) {
				BookModiAndDelIFrame iframe=new BookModiAndDelIFrame();
				frames.put("ͼ���޸�", iframe);
				Library.addIFame(frames.get("ͼ���޸�"));
			}
		}
	}
	
	/**
	 * ͼ����Ϣ��Ӵ��嶯��
	 */
	private static class BookAddAction extends AbstractAction {		
		BookAddAction() {

			super("ͼ����Ϣ���", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("ͼ����Ϣ���")
					||frames.get("ͼ����Ϣ���").isClosed()) {
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("ͼ����Ϣ���", iframe);
				Library.addIFame(frames.get("ͼ����Ϣ���"));
			}
		}
	}
	
	/**
	 * ϵͳ�˳�����
	 */
	private static class ExitAction extends AbstractAction { 
		public ExitAction() {
			super("�˳�ϵͳ", null);
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	
	private MenuActions() {
		super();
	}

}
