package cdu.gujiao.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.Item;
import cdu.gujiao.util.BookTypeIdAndItemMap;


import java.awt.*;

public class BookSearchIFrame extends JInternalFrame {

	private JTextField textField_1;

	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	private Map m=BookTypeIdAndItemMap.getMap();

	/**
	 * Create the frame
	 */
	String booksearch[] = { "编号", "分类", "名称", "作者", "译者","出版社",  "出版日期", "单价" };

	/**
	 * @description		将查询得的结果集转化为数组
	 * @author			顾蛟	
	 * @createDate		2011-3-2
	 * @param			List list结果集
	 * @return			Object[][]结果数组		
	 *	
	 * @see						
	 */
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			BookInfo book = (BookInfo) list.get(i);
			s[i][0] = book.getISBN();
			String booktypename=String.valueOf(BookTypeIdAndItemMap.getMap().get(book.getTypeid()));
			s[i][1] = booktypename;
			s[i][2] = book.getBookname();
			s[i][3] = book.getWriter();
			s[i][4] = book.getTranslator();
			s[i][5] = book.getPublisher();
			s[i][6] = book.getDate();
			s[i][7] = book.getPrice();
		}
		return s;
	}

	public BookSearchIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("图书查询");
		setBounds(100, 100, 500, 400);
		
		final JTabbedPane tabbedPane = new JTabbedPane();	//创建Tab标签
		tabbedPane.setPreferredSize(new Dimension(0, 50));	//设置Tab标签的大小
		getContentPane().add(tabbedPane);					//将Tab标签添加到窗体上

		final JPanel panel_1 = new JPanel();				//创建条件查询面板
		panel_1.setLayout(new BorderLayout());				//设置条件查询面板的布局管理器
		tabbedPane.addTab("条件查询", null, panel_1, null);	//创建条件查询页签并为其添加一个面板

		final JPanel panel_1_1 = new JPanel();				//查询项目面板		
		panel_1_1.setBorder(new TitledBorder(null, 
				"请选择查询项目", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null, 
				null));										//设置查询项目面板的边界
		panel_1_1.setPreferredSize(new Dimension(0, 50));	//设置查询项目面板大小
		panel_1.add(panel_1_1, BorderLayout.NORTH);			//将查询项目面板添加到条件查询面板上
        choice=new JComboBox();                            	//创建查询类别下拉列表框
		String[] array={"图书名称","图书作者"};				//下拉列表框中的内容
		for(int i=0;i<array.length;i++){					//将数组中的条目添加到下拉列表框 中
			choice.addItem(array[i]);
		}
		panel_1_1.add(choice);								//将下拉列表框添加到查询项目面板中
		textField_1 = new JTextField();						//创建查询输入框
		textField_1.setColumns(20);							//设置查询输入框中的列数
		panel_1_1.add(textField_1);							//将输入框添加到查询项目面板中
        
		final JPanel panel = new JPanel();					//查询结果显示面板
		panel.setBorder(new TitledBorder(null, 
				"查询结果显示",
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null,
				null));										//设置该面板的边界
		panel_1.add(panel);									//将该面板添加到大面板中
		
		scrollPane_1 = new JScrollPane();					//创建一个滚动面板
		scrollPane_1.setPreferredSize(
				new Dimension(400, 200));					//设置滚动面板的大小
		panel.add(scrollPane_1,BorderLayout.CENTER);		//将滚动面板添加到查询结果面板中

		final JPanel panel_2_1 = new JPanel();				//下方面板，装下面两个按钮
		panel_2_1.setPreferredSize(new Dimension(0, 50));	//设置下方面板的大小
		panel_1.add(panel_2_1, BorderLayout.SOUTH);			//将下方面板添加到主面板上

		final JButton button = new JButton(); 				//创建查询按钮
		button.setText("查询"); 								//设置显示的文字
		panel_2_1.add(button); 								//将按钮添加到下方面板上

		button.addActionListener(new ActionListener() { 	//给查询按钮添加事件监听器

			public void actionPerformed(ActionEvent arg0) {
				String name = (String) choice.getSelectedItem();		//得到下拉列表框中被选中的项
				if (name.equals("图书名称")) {							//如果选中的是“图书名称”则执行按图书名称进行查询
					Object[][] results = getselect(Dao				
							.selectbookmohu(textField_1.getText()));	//根据图书名查询图书信息
					table_2 = new JTable(results, booksearch);			//创建表格并将查询出的结果放在表格里

					scrollPane_1.setViewportView(table_2);				//将表格设置到scrollPane中
				} else if (name.equals("图书作者")) {					//根据图书的作者查询图书信息

					Object[][] results = getselect(Dao					
						.selectbookmohuwriter(textField_1.getText())); 	//查询
					table_2 = new JTable(results, booksearch);			//创建表格并将查询出的结果放在表格里

					scrollPane_1.setViewportView(table_2);				//将表格设置到scrollPane中
				}
			}
		});

		final JButton button_1 = new JButton();		//创建退出按钮
		button_1.setText("退出");					//设置按钮显示的文字
		panel_2_1.add(button_1);					//将按钮添加到下方面板上
		button_1.addActionListener(
				new CloseActionListener());			//给退出按钮添加监听器
		
		
		final JPanel panel_2 = new JPanel();		//图书全部信息页签面板
		tabbedPane.addTab("全部图书信息", 
				null, 
				panel_2, 
				null);						 		//添加“全部图书信息”页签	
         
		scrollPane = new JScrollPane();				//创建滚动面板
		scrollPane.setPreferredSize(
				new Dimension(450, 250));			//设置大小
		panel_2.add(scrollPane);					//将滚动面板添加到全部图书信息面板中
		
		Object[][] results = getselect(
				Dao.selectbooksearch());			//查询得的结果集
		String [] booksearch = { "编号", "分类", "名称", "作者", "译者","出版社",  "出版日期", "单价" };
		table_1 = new JTable(results,booksearch);	//创建表格
		
		scrollPane.setViewportView(table_1);		//将表格添加到滚动面板
		
		setVisible(true);							//设置窗体可见
		show();
	}
	
	class CloseActionListener implements ActionListener {		// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
