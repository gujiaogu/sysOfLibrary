package cdu.gujiao.iframe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.logging.SimpleFormatter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Operater;
import cdu.gujiao.model.Reader;
import cdu.gujiao.util.BookTypeIdAndItemMap;
import cdu.gujiao.util.MyDocument;


public class BookBorrowIFrame extends JInternalFrame {
	
	private Operater user = BookLoginIFrame.getUser(); 
	
	private final JTextField operator;	//管理员

	private JTextField todaydate;		//当前时间

	private JTable table;				//图书列表

	private JTextField price;			//图书价格

	private JTextField bookType;		//图书类别

	private JTextField bookName;		//图书名称

	private JTextField bookISBN;		//图书编号

	private JTextField keepMoney;		//押金

	private JTextField number;			//可借数量

	private JTextField readerName;		//读者名称

	private JTextField readerISBN;		//读者编号

	private String[] columnNames = { "书籍编号", "借书日期", "应还日期", "读者编号" };

	private Map map = BookTypeIdAndItemMap.getMap();//返回图书类型Id和名称的Map


	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @description		添加一条记录到表的末尾
	 * @author			顾蛟	
	 * @createDate		2011-2-28
	 * @param			
	 * @return					
	 *	
	 * @see						
	 */
	public final void add() {
		String str[] = new String[4];				
		str[0] = bookISBN.getText().trim();			//图书编号	
		str[1] = String.valueOf(
				myfmt.format(new java.util.Date()));//借书日期
		str[2] = myfmt.format(getBackTime());		//应还书日期
		str[3] = readerISBN.getText().trim();		//读者编号
		model.addRow(str);							//将记录添加到表末尾
	}
	/**
	 * @description		计算还书时间
	 * @author			顾蛟	
	 * @createDate		2011-2-28
	 * @param			
	 * @return			Date 还书时间		
	 *	
	 * @see						
	 */
	public Date getBackTime() {	
		String days = "0";
		List list2 =
			Dao.selectBookCategory(bookType.getText().trim());
		for (int j = 0; j < list2.size(); j++) {
			BookType type = (BookType) list2.get(j);
			days = type.getDays();
		}
		java.util.Date date = new java.util.Date();
		date.setDate(date.getDate() + Integer.parseInt(days));
		return date;
	}
	public BookBorrowIFrame() {
		super();
		setTitle("图书借阅管理");
		setIconifiable(true); 				// 设置窗体可最小化
		setClosable(true); 					// 设置窗体可关闭
		setBounds(100, 100, 500, 375);
		
		/*表格那一块东西*/
		final JPanel panel = new JPanel();						//表格面板
		getContentPane().add(panel);
		
		final JScrollPane scrollPane = new JScrollPane();		//放表格的面板
		scrollPane.setPreferredSize(new Dimension(400, 100));	//设置滚动面板的大小
		panel.add(scrollPane);									//将滚动面板添加到表格面板中

		table = new JTable();									//创建表格
		scrollPane.setViewportView(table);						//将表格设置到滚动面板中
		model.setColumnIdentifiers(columnNames);				//设置模型中的列标识符
		table.setModel(model);									//设置表格的视图模型
		
		/*上面那块东西*/
		final JPanel panel_1 = new JPanel();					//读者和要借书籍面板
		panel_1.setPreferredSize(new Dimension(0, 120));		//设置该面板大小
		getContentPane().add(panel_1, BorderLayout.NORTH);		//将上方的面板添加到主窗体上

		final JSplitPane splitPane = new JSplitPane();			//创建分隔面板 
		panel_1.add(splitPane);									//将分隔面板添加到上方的面板中

		/*左边那块（读者信息那块）*/
		final JPanel panel_3 = new JPanel();					//创建左边面板（外层）
		panel_3.setPreferredSize(new Dimension(240, 110));		//设置面板大小
		splitPane.setLeftComponent(panel_3);					//将面板添加到JSplitPane左边

		final JPanel panel_5 = new JPanel();					//创建左边面板（内层）
		final GridLayout gridLayout = new GridLayout(0, 2);		//创建面板的布局管理器
		gridLayout.setHgap(2);									//设置组件水平间距
		gridLayout.setVgap(10);									//设置组件的垂直距离
		panel_5.setLayout(gridLayout);							//设置面板的布局管理器
		panel_5.setPreferredSize(new Dimension(150, 100));		//设置面板的大小
		panel_3.add(panel_5);									//将此面板添加到左边面板上

		final JLabel label = new JLabel();			//读者编号标签
		label.setText("读者编号：");
		panel_5.add(label);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(
				new bookISBNListenerlostFocus());	//给读者标签添加到事件
		panel_5.add(readerISBN);

		final JLabel label_1 = new JLabel();		//读者姓名标签
		label_1.setText("读者姓名：");
		panel_5.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_5.add(readerName);

		final JLabel label_2 = new JLabel();		//可借数量标签
		label_2.setText("可借数量：");
		panel_5.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_5.add(number);

		final JLabel label_4 = new JLabel();		//押金标签					
		label_4.setText("押    金：");
		panel_5.add(label_4);

		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel_5.add(keepMoney);

		/*右边那块（书籍信息那块）*/
		final JPanel panel_4 = new JPanel();					//创建右边面板（外层）
		final GridLayout gridLayout_1 = new GridLayout(0, 2);	//创建面板的布局管理器
		gridLayout_1.setVgap(10);								//设置组件的垂直间距
		panel_4.setLayout(gridLayout_1);						//设置布局管理器
		panel_4.setPreferredSize(new Dimension(240, 110));		//设置大小
		splitPane.setRightComponent(panel_4);					//将面板添加到JSplitPane的右边 

		final JLabel label_5 = new JLabel();		//书籍编号标签
		label_5.setText("书籍编号：");
		panel_4.add(label_5);

		bookISBN = new JTextField();
		bookISBN.setDocument(new MyDocument(13));
		bookISBN.addKeyListener(
				new bookISBNListenerlostFocus());	//书籍编号文本框事件
		panel_4.add(bookISBN);

		final JLabel label_6 = new JLabel();		//书籍名称标签
		label_6.setText("书籍名称：");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		final JLabel label_7 = new JLabel();		//书籍类别标签
		label_7.setText("书籍类别：");			
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		final JLabel label_8 = new JLabel();		//书籍价格标签
		label_8.setText("书籍价格：");
		panel_4.add(label_8);

		price = new JTextField();
		price.setEditable(false);
		panel_4.add(price);

		/*下面那块东西*/
		final JPanel panel_2 = new JPanel();					//下方面板
		panel_2.setPreferredSize(new Dimension(0, 100));		//设置大小
		getContentPane().add(panel_2, BorderLayout.SOUTH);		//将下方面板添加到窗体中

		final JPanel panel_7 = new JPanel();					//下方容器面板
		final GridLayout gridLayout_2 = new GridLayout(0, 2);	//创建布局管理器
		gridLayout_2.setVgap(10);								//设置组件垂直间距
		panel_7.setLayout(gridLayout_2);						//设置布局管理器
		panel_7.setPreferredSize(new Dimension(280, 50));		//设置面板大小
		panel_2.add(panel_7);									//将下方窗口面板添加到下方面板中

		final JLabel label_9 = new JLabel();	//当前日期标签
		label_9.setText("当前日期：");
		panel_7.add(label_9);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		todaydate.setText(s.format(new java.util.Date()));
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		final JLabel label_11 = new JLabel();	//管理员标签
		label_11.setText("管理员：");
		panel_7.add(label_11);

		operator  =new JTextField(user.getName());
		operator.setEditable(false);
		panel_7.add(operator);

		final JPanel panel_8 = new JPanel();	//放两个按钮的面板
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 60));
		panel_2.add(panel_8);

		final JButton buttonBorrow = new JButton();
		buttonBorrow.setText("借出当前图书");
		buttonBorrow.addActionListener(new BorrowActionListener());
		panel_8.add(buttonBorrow);

		final JButton buttonClear = new JButton();
		buttonClear.setText("清除所有记录");
		buttonClear.addActionListener(new ClearActionListener(model));
		panel_8.add(buttonClear);

		setVisible(true);
	}

	/**
	 * 
	 * @(#)							
	 * 版权：		成都大学毕业设计		 	
	 * 描述：		
	 *		
	 * @author		顾蛟
	 * @version		final		
	 * @createDate	2011-3-9	 
	 * @see
	 */
	class bookISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
				if (readerISBN.getText().trim().length()!=0
						&& bookISBN.getText().trim().length()!=0) {//判断书籍编号和读者编号是否为空
					String ISBNs = bookISBN.getText().trim();
					List list = Dao.selectBookInfo(ISBNs);
					for (int i = 0; i < list.size(); i++) {
						BookInfo book = (BookInfo) list.get(i);
						bookName.setText(book.getBookname());
						bookType.setText(String.valueOf(map.get(book
								.getTypeid())));
						price.setText(String.valueOf(book.getPrice()));
					}
					String days = "0";
					List list2 = Dao.selectBookCategory(bookType.getText()
							.trim());
					for (int j = 0; j < list2.size(); j++) {
						BookType type = (BookType) list2.get(j);
						days = type.getDays();
					}
					String readerISBNs = readerISBN.getText().trim();
					List list5 = Dao.selectReader(readerISBNs);			// 此读者是否在tb_reader表中
					List list4 = Dao.selectBookInfo(ISBNs);				// 此书是否在tb_bookInfo表中
					if (!readerISBNs.isEmpty() && list5.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"此读者编号没有注册，查询输入读者编号是否有误！");
						return;
					}
					if (list4.isEmpty() && !ISBNs.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"本图书馆没有此书，查询输入图书编号是否有误！");
						return;
					}
					for (int i = 0; i < list5.size(); i++) {
						Reader reader = (Reader) list5.get(i);
						readerName.setText(reader.getName());
						number.setText(reader.getMaxNum());
						keepMoney.setText(reader.getKeepMoney() + "");
					}
					if (Integer.parseInt(number.getText().trim()) <= 0) {
						JOptionPane.showMessageDialog(null, "借书量已经超过最大借书量！");
						return;
					}

					add();
					number.setText(String.valueOf(Integer.parseInt(number
							.getText().trim()) - 1));
				}

				else
					JOptionPane.showMessageDialog(null, "请输入读者条形码！或书籍条形码");
			}

		}
	}
	
	class BorrowActionListener implements ActionListener { 
		public void actionPerformed(final ActionEvent e) {

			String bookISBNs=bookISBN.getText().trim();
			String readerISBNs=readerISBN.getText().trim();
			String bookNames=bookName.getText().trim();
			String operatorId=user.getId();
			String borrowDate=myfmt.format(new java.util.Date());
			String backDate=myfmt.format(getBackTime());
			int i=Dao.InsertBookBorrow(bookISBNs, 
					readerISBNs, 
					operatorId, 
					java.sql.Timestamp.valueOf(borrowDate), 
					java.sql.Timestamp.valueOf(backDate));
			if(i==1){
				JOptionPane.showMessageDialog(null, "图书借阅完成！");
				doDefaultCloseAction();
			}
		}
	}

	class ClearActionListener implements ActionListener {
		private final DefaultTableModel model;

		ClearActionListener(DefaultTableModel model) {
			this.model = model;
		}
		
		public void actionPerformed(final ActionEvent e) {
			if(table.getRowCount()!=0){
				model.setDataVector(null, columnNames);
			}
			else {
				JOptionPane.showMessageDialog(null, "表格中暂时没有数据，请进行借阅操作");
			}
		}
	}
}
