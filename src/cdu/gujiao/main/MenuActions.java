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
 * 版权：		成都大学毕业设计		 	
 * 描述：		菜单和按钮的Action对象
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class MenuActions {
	/**
	 * 子窗体集合
	 */
	private static Map<String, JInternalFrame> frames; 					
	
	/**
	 * 修改密码窗体动作
	 */
	public static PasswordModiAction MODIFY_PASSWORD; 					
	/**
	 * 图书搜索窗体动作
	 */
	public static BookSearchAction BOOK_SEARCH; 						
	/**
	 * 图书归还窗体动作
	 */
	public static GiveBackAction GIVE_BACK; 							
	/**
	 * 图书借阅窗体动作
	 */
	public static BorrowAction BORROW; 									
	/**
	 * 新书验收动作
	 */
	public static CheckAndAcceptNewBookAction NEWBOOK_CHECK_ACCEPT;		
	/**
	 * 新书定购窗体动作
	 */
	public static BoodOrderAction NEWBOOK_ORDER; 						
	/**
	 * 图书类型修改窗体动作
	 */
	public static BookTypeModiAction BOOKTYPE_MODIFY; 					
	/**
	 * 图书类型添加窗体动作
	 */
	public static BookTypeAddAction BOOKTYPE_ADD; 						
	/**
	 * 读者信息窗体动作
	 */
	public static ReaderModiAction READER_MODIFY; 						
	/**
	 * 读者信息添加窗体动作
	 */
	public static ReaderAddAction READER_ADD; 							
	/**
	 * 图书信息修改窗体动作
	 */
	public static BookModiAction BOOK_MODIFY; 							
	/**
	 * 图书信息添加窗体动作
	 */
	public static BookAddAction BOOK_ADD; 								
	/**
	 * 系统退出动作
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
	 * 修改密码窗体动作
	 */
	private static class PasswordModiAction extends AbstractAction {
		PasswordModiAction() {
			super("更改口令",null);				//用指定字符串定义一个Action对象
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("更改密码")
					||frames.get("更改密码").isClosed()) {
				GengGaiMiMa iframe=new GengGaiMiMa();
				frames.put("更改密码", iframe);
				Library.addIFame(frames.get("更改密码"));
			}
		}
	}


	/**
	 * 图书搜索窗体动作
	 */
	private static class BookSearchAction extends AbstractAction {
		BookSearchAction() {
			super("图书搜索", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书查询")
					||frames.get("图书查询").isClosed()) {
				BookSearchIFrame iframe=new BookSearchIFrame();
				frames.put("图书查询", iframe);
				Library.addIFame(frames.get("图书查询"));
			}
		}
	}

	/**
	 * 图书归还窗体动作
	 */
	private static class GiveBackAction extends AbstractAction {
		GiveBackAction() {
			super("图书归还", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书归还管理")
					||frames.get("图书归还管理").isClosed()) {
				BookBackIFrame iframe=new BookBackIFrame();
				frames.put("图书归还管理", iframe);
				Library.addIFame(frames.get("图书归还管理"));
			}
		}
	}

	/**
	 * 图书借阅窗体动作
	 */
	private static class BorrowAction extends AbstractAction {
		BorrowAction() {
			super("图书借阅", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书借阅管理")
					||frames.get("图书借阅管理").isClosed()) {
				BookBorrowIFrame iframe=new BookBorrowIFrame();
				frames.put("图书借阅管理", iframe);
				Library.addIFame(frames.get("图书借阅管理"));
			}
		}
	}

	/**
	 * 新书验收动作
	 */
	private static class CheckAndAcceptNewBookAction 
					extends AbstractAction {
		CheckAndAcceptNewBookAction() {
			super("验收新书", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("图书验收")
					||frames.get("图书验收").isClosed()) {
				newBookCheckIFrame iframe=new newBookCheckIFrame();
				frames.put("图书验收", iframe);
				Library.addIFame(frames.get("图书验收"));
			}
		}
	}

	/**
	 * 新书定购窗体动作
	 */
	private static class BoodOrderAction extends AbstractAction {
		BoodOrderAction() {
			super("新书定购", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("新书订购管理")
					||frames.get("新书订购管理").isClosed()) {
				newBookOrderIFrame iframe=new newBookOrderIFrame();
				frames.put("新书订购管理", iframe);
				Library.addIFame(frames.get("新书订购管理"));
			}
		}
	}

	/**
	 * 图书类型修改窗体动作
	 */
	private static class BookTypeModiAction extends AbstractAction {
		BookTypeModiAction() {
			super("图书类别修改", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别修改")
					||frames.get("图书类别修改").isClosed()) {
				BookTypeModiAndDelIFrame iframe=new BookTypeModiAndDelIFrame();
				frames.put("图书类别修改", iframe);
				Library.addIFame(frames.get("图书类别修改"));
			}
		}
	}

	/**
	 * 图书类型添加窗体动作
	 */
	private static class BookTypeAddAction extends AbstractAction {
		BookTypeAddAction() {
			super("图书类别添加", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书类别添加")
					||frames.get("图书类别添加").isClosed()) {
				BookTypeAddIFrame iframe=new BookTypeAddIFrame();
				frames.put("图书类别添加", iframe);
				Library.addIFame(frames.get("图书类别添加"));
			}
		}
	}
	
	/**
	 * 读者信息窗体动作
	 */
	private static class ReaderModiAction extends AbstractAction {
		ReaderModiAction() {
			super("读者修改与删除", null);
		}
		public void actionPerformed(ActionEvent e) {
			
			if (!frames.containsKey("读者信息修改与删除")
					||frames.get("读者信息修改与删除").isClosed()) {
				ReaderModiAndDelIFrame iframe=new ReaderModiAndDelIFrame();
				frames.put("读者信息修改与删除", iframe);
				Library.addIFame(frames.get("读者信息修改与删除"));
			}
		}
	}

	/**
	 * 读者信息添加窗体动作
	 */
	private static class ReaderAddAction extends AbstractAction {
		ReaderAddAction() {
			super("读者信息添加", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("读者相关信息添加")
					||frames.get("读者相关信息添加").isClosed()) {
				ReaderAddIFrame iframe=new ReaderAddIFrame();
				frames.put("读者相关信息添加", iframe);
				Library.addIFame(frames.get("读者相关信息添加"));
			}
		}
	}
	/**
	 * 图书信息修改窗体动作
	 */
	private static class BookModiAction extends AbstractAction {
		BookModiAction() {
			super("图书修改", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书修改")
					||frames.get("图书修改").isClosed()) {
				BookModiAndDelIFrame iframe=new BookModiAndDelIFrame();
				frames.put("图书修改", iframe);
				Library.addIFame(frames.get("图书修改"));
			}
		}
	}
	
	/**
	 * 图书信息添加窗体动作
	 */
	private static class BookAddAction extends AbstractAction {		
		BookAddAction() {

			super("图书信息添加", null);
		}
		public void actionPerformed(ActionEvent e) {
			if (!frames.containsKey("图书信息添加")
					||frames.get("图书信息添加").isClosed()) {
				BookAddIFrame iframe = new BookAddIFrame();
				frames.put("图书信息添加", iframe);
				Library.addIFame(frames.get("图书信息添加"));
			}
		}
	}
	
	/**
	 * 系统退出动作
	 */
	private static class ExitAction extends AbstractAction { 
		public ExitAction() {
			super("退出系统", null);
		}
		public void actionPerformed(final ActionEvent e) {
			System.exit(0);
		}
	}

	
	private MenuActions() {
		super();
	}

}
