package cdu.gujiao.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import cdu.gujiao.iframe.BookLoginIFrame;
import cdu.gujiao.util.CreateIcon;


/**
 * @(#)						
 * 版权：		成都大学毕业设计		 	
 * 描述：		主窗体
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Library extends JFrame {
	
	/**桌面窗体*/
	private static final JDesktopPane 
				DESKTOP_PANE = new JDesktopPane();	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());	//设置系统外观
			new BookLoginIFrame();								//登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @description		在桌面窗体上添加子窗体（JInternalFrame）
	 * @author			顾蛟	
	 * @createDate		2011-3-15
	 * @param			
	 * @return					
	 * @see						
	 */
	public static void addIFame(JInternalFrame iframe) { 		//添加子窗体的方法
		DESKTOP_PANE.add(iframe);								//新增子窗体
	}
	
	/**
	 * 构造函数
	 */
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置关闭按钮处理事件
		Toolkit tool = Toolkit.getDefaultToolkit();				//获得默认的工具箱
		Dimension screenSize = tool.getScreenSize();			//获得屏幕的大小
		setSize(800, 600);										//设置窗体大小
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);			//设置窗体位置
		setTitle("图书馆管理系统");								//设置窗体标题
		JMenuBar menuBar = createMenu(); 						//调用创建菜单栏的方法
		setJMenuBar(menuBar);									//设置菜单栏
		final JLabel label = new JLabel();								//创建一个标签，显示图片
		label.setBounds(0, 0, 0, 0);									//设置窗体的大小和位置
		label.setIcon(null); 											// 窗体背景
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();			//获得组件大小
				label.setSize(size);									//设置标签大小
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/lib.jpg")
						+ "'></html>");									//设置标签文本
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));			//后面一个参数表示label是JDesktoPane的最后一个组件，也就是说label永远在最底层
		getContentPane().add(DESKTOP_PANE);								//将桌面窗体添加到主窗体中
	}
	
	/**
	 * @description		创建菜单栏
	 * @author			顾蛟	
	 * @createDate		2011-2-15
	 * @param	
	 * @return					
	 *	
	 * @see						
	 */
	private JMenuBar createMenu() { 							//创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();						//创建工具栏
		
		/*读者信息管理*/
		JMenu readerManagerMItem = new JMenu("读者信息管理");	//新增读者信息管理子菜单
		readerManagerMItem.add(MenuActions.READER_ADD);			//添加读者信息添加菜单项
		readerManagerMItem.add(MenuActions.READER_MODIFY);		//添加读者信息添加菜单项
		
		/*图书类别信息管理*/
		JMenu bookTypeManageMItem = new JMenu("图书类别管理");	//新增图书类别管理子菜单
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);		//添加图书类型添加菜单项
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);	//添加图书类型修改菜单项
		
		/*图书信息管理*/
		JMenu menu = new JMenu("图书信息管理");					//新增图书信息管理子菜单
		menu.add(MenuActions.BOOK_ADD);							//添加图书信息添加菜单项
		menu.add(MenuActions.BOOK_MODIFY);						//添加图书信息修改菜单项
		
		/*新书订购管理*/
		JMenu bookOrderMenu = new JMenu("新书订购管理"); 		//初始化新书订购管理菜单
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);			//添加新书定购菜单项
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);	//添加验收新书菜单项
		
		/*借阅管理*/
		JMenu borrowManageMenu = new JMenu("借阅管理"); 			//借阅管理
		borrowManageMenu.add(MenuActions.BORROW); 				//添加借阅菜单项
		borrowManageMenu.add(MenuActions.GIVE_BACK); 			//添加归还菜单项
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); 			//添加搜索菜单项
		
		/*修改密码*/
		JMenu sysManageMenu = new JMenu("修改密码"); 			//操作员管理
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);			//添加更改菜单项
		
		/*在工具栏上添加菜单*/
		menuBar.add(readerManagerMItem); 						//添加读者信息菜单到菜单栏
		menuBar.add(bookTypeManageMItem); 						//添加基础数据维护菜单到菜单栏
		menuBar.add(menu); 										//添加基础数据维护菜单到菜单栏
		menuBar.add(bookOrderMenu); 							//添加新书订购管理菜单到菜单栏
		menuBar.add(borrowManageMenu); 							//添加借阅管理菜单到菜单栏
		menuBar.add(sysManageMenu); 							//添加系统维护菜单到菜单栏
		return menuBar;
	}
}
