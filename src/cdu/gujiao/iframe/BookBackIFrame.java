package cdu.gujiao.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.main.Library;
import cdu.gujiao.model.Back;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Borrow;
import cdu.gujiao.model.Operater;
import cdu.gujiao.util.BackgroundPanel;
import cdu.gujiao.util.BookTypeIdAndItemMap;
import cdu.gujiao.util.CreateIcon;
import cdu.gujiao.util.MyDocument;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		图书归还类
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-17	 
 * @see 				
 */
public class BookBackIFrame extends JInternalFrame {
	
	/**
	 * 读者借书列表
	 */
	private JTable table;
	/**
	 * 罚款文本框
	 */
	private JTextField fkmoney;
	/**
	 * 超出的天数文本框
	 */
	private JTextField ccdays;
	/**
	 * 借出的天数文本框
	 */
	private JTextField realdays;
	/**
	 * 规定的借阅天数文本框
	 */
	private JTextField borrowdays;
	/**
	 * 借书日期文本框
	 */
	private JTextField borrowDate;
	/**
	 * 读者编号文本框
	 */
	private JTextField readerISBN;
	/**
	 * 表头数组
	 */
	private String[] columnNames = { "图书名称", "图书条形码","图书类别","读者姓名","读者条形码","借书时间","归还时间" };
	/**
	 * 表格视图模型
	 */
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * 日期格式
	 */
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 书籍编号
	 */
	private String bookISBNs=null;
	/**
	 * 读者编号
	 */
	private String readerISBNs=null;
	/**
	 * 归还记录的ID
	 */
	private int id;
	
	/**
	 * @description		查询还书内容并将其添加到表视图
	 * @author			顾蛟	
	 * @createDate		2011-2-17
	 * @param	
	 * @return					
	 * @see						
	 */
	public final void add() {
		readerISBNs = readerISBN.getText().trim();
		List list = Dao.selectBookBack(readerISBNs);
		for(int i=0;i<list.size();i++){
			Back back =(Back)list.get(i);
			id = back.getId();
			String str[] = new String[7];
			str[0] = back.getBookname();
			str[1] = back.getBookISBN();
			str[2] = String.valueOf(BookTypeIdAndItemMap.getMap().get(back.getTypeId()+""));
			str[3] = back.getReaderName();
			str[4] = back.getReaderISBN();
			str[5] = back.getBorrowDate();
			str[6] = back.getBackDate();
			model.addRow(str);				//添加到表视图
		}
	}
	
	/**
	 * 创建窗体
	 */
	public BookBackIFrame() {
		super();
		/*窗体基本设置*/
		setIconifiable(true);								// 设置窗体可最小化
		setClosable(true);									// 设置窗体可关闭
		setTitle("图书归还管理");
		setBounds(100, 100, 550, 400);
		
		/*背景面板并进行设置*/
		final BackgroundPanel mainPanel =
			new BackgroundPanel();
		mainPanel.setSize(550, 400);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);

		/*借书信息面板*/
		final JPanel panel = new JPanel();					//基本信息面板
		panel.setBorder(new TitledBorder(null, "借书信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(0, 200));		//设置基本信息面板大小
		mainPanel.add(panel, BorderLayout.NORTH);			//将基本信息面板添加到JInternalFrame里

		/*读者编号面板*/
		final JPanel panel_5 = new JPanel();				//读者编号面板		
		final GridLayout gridLayout_1 =
				new GridLayout(0, 2);						//读者编号面板布局管理器
		gridLayout_1.setVgap(5);							//设置垂直高度
		panel_5.setLayout(gridLayout_1);					//设置布局管理器
		panel_5.setPreferredSize(new Dimension(400, 20));	//设置大小
		panel.add(panel_5);									//将读者编号面板添加到基本信息面板

		/*读者编号标签及其文本框*/
		final JLabel label_4 = new JLabel();				
		label_4.setText("读者编号：");
		panel_5.add(label_4);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(new readerISBNListenerlostFocus());
		panel_5.add(readerISBN);

		/*放滚动面板的面板*/
		final JPanel panel_4 = new JPanel();				//放表格的面板
		panel_4.setLayout(new FlowLayout());
		panel_4.setPreferredSize(new Dimension(450, 130));
		panel.add(panel_4);

		/*放表格的滚动面板*/
		final JScrollPane scrollPane = new JScrollPane();	//添加一个ScrollPane
		scrollPane.setPreferredSize(new Dimension(450, 120));
		panel_4.add(scrollPane);

		/*表格相关设置*/
		table = new JTable();
		scrollPane.setViewportView(table);					//创建一个视口并设置视图
		model.setColumnIdentifiers(columnNames);			//设置表格头部
		table.setModel(model);								//设置表格显示内容
		table.addMouseListener(new TableListener());		//给表格添加监听器
		
		/*下方面板*/
		final JPanel panel_1 = new JPanel();				//下方面板
		mainPanel.add(panel_1,BorderLayout.CENTER);

		/*罚款信息面板*/
		final JPanel panel_2 = new JPanel();				//罚款信息面板
		final GridLayout gridLayout_2 = new GridLayout(0, 4);
		gridLayout_2.setVgap(10);
		gridLayout_2.setHgap(5);
		panel_2.setLayout(gridLayout_2);
		panel_2.setBorder(new TitledBorder(null, "罚款信息", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_2.setPreferredSize(new Dimension(540, 110));
		panel_1.add(panel_2);

		/*借书日期标签及其文本框*/
		final JLabel label_11 = new JLabel();
		label_11.setText("借书日期：");
		panel_2.add(label_11);

		borrowDate = new JTextField();
		borrowDate.setEditable(false);
		panel_2.add(borrowDate);

		/*规定天数标签及其文本框*/
		final JLabel label_12 = new JLabel();
		label_12.setText("规定天数：");
		panel_2.add(label_12);

		borrowdays = new JTextField();
		borrowdays.setEditable(false);
		panel_2.add(borrowdays);

		/*实际天数标签及文本框*/
		final JLabel label_13 = new JLabel();
		label_13.setText("实际天数：");
		panel_2.add(label_13);

		realdays = new JTextField();
		realdays.setEditable(false);
		panel_2.add(realdays);

		/*超出天数标签及其文本框*/
		final JLabel label_14 = new JLabel();
		label_14.setText("超出天数：");
		panel_2.add(label_14);

		ccdays = new JTextField();
		ccdays.setEditable(false);
		panel_2.add(ccdays);

		/*罚款金额标签及其文本框*/
		final JLabel label_15 = new JLabel();
		label_15.setText("罚款金额：");
		panel_2.add(label_15);

		fkmoney = new JTextField();
		fkmoney.setEditable(false);
		panel_2.add(fkmoney);

		/*下方按钮面板*/
		final JPanel panel_3 = new JPanel();		
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_3.setPreferredSize(new Dimension(540, 50));
		mainPanel.add(panel_3,BorderLayout.SOUTH);

		/*下方按钮面板*/
		final JPanel panel_7 = new JPanel();		
		final GridLayout gridLayout_3 = new GridLayout(0, 4);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_3.add(panel_7);

		/*图书归还按钮*/
		final JButton buttonback = new JButton();
		buttonback.setText("图书归还");
		buttonback.addActionListener(
				new BookBackActionListener(model)); //还书按钮监听事件
		panel_7.add(buttonback);

		/*退出按钮*/
		final JButton buttonExit= new JButton();
		buttonExit.setText("退出");
		buttonExit.addActionListener(
				new CloseActionListener());   		//关闭按钮监听事件
		panel_7.add(buttonExit);
		
		setVisible(true);
	}
	/**
	 * 读者编号文本框，按回车时的事件
	 */
	class readerISBNListenerlostFocus extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { 			//判断在文本框是否输入回车。
				add();
			}
		}
	}
	/**
	 * 表格监听事件
	 */
	class TableListener extends MouseAdapter {
		@SuppressWarnings("deprecation")
		public void mouseClicked(final MouseEvent e) {
			
			java.util.Date date = new java.util.Date();   			//系统当前时间
			String fk = "";											//罚款
			String days1 = "";										//可借天数 
			int selRow = table.getSelectedRow();					//获得选中行
			List list = Dao.selectBookTypeFk(
					table.getValueAt(selRow, 2).toString().trim());	//查询该类图书罚款数和可借天数 
			for(int i=0;i<list.size();i++){
				BookType booktype=(BookType)list.get(i);
				fk=booktype.getFk();								//获得罚款数
				days1=booktype.getDays();							//获得可借天数
			}
			borrowDate.setText(
					table.getValueAt(selRow, 5).toString().trim());	
			int days2;												//实际天数
			int days3;												//超出天数
			borrowdays.setText(days1+"");
//			days2 = date.getDate() 
//					- java.sql.Timestamp.valueOf(
//							table.getValueAt(selRow, 5).toString().trim()).getDate();
			/*时间相减*/
			long realDateMilli = date.getTime()/(24*60*60*1000);
			long borrowDateMilli = 0l;
			try {
				borrowDateMilli = DateFormat.getInstance().parse(
						table.getValueAt(selRow, 5).toString().trim()).getTime()/(24*60*60*1000);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			days2 = (int) (realDateMilli - borrowDateMilli > 0 ? realDateMilli - borrowDateMilli : 0);
			
			realdays.setText(days2+"");
			days3=days2-Integer.parseInt(days1);
			if(days3>0){
				ccdays.setText(days3+"");
				Double zfk=Double.valueOf(fk)*days3;
				fkmoney.setText(zfk+"元");
			}
			else{
				ccdays.setText("没有超过规定天数");
				fkmoney.setText("0");
			}
			bookISBNs=table.getValueAt(selRow, 1).toString().trim();
		}
	}
	/**
	 * 还书监听事件
	 */
	class BookBackActionListener implements ActionListener{
		private final DefaultTableModel model;

		BookBackActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(ActionEvent e) {
			if(readerISBNs==null){
				JOptionPane.showMessageDialog(null, "请输入读者编号！");
				return;
			}
			System.out.println(bookISBNs==null);

			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "请选择所要归还的图书！");
				return;	
			}

		
			int i=Dao.UpdateBookBack(bookISBNs, readerISBNs,id);
			System.out.print(i);
			 if(i==1){	
				int selectedRow = table.getSelectedRow();
				model.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "还书操作完成！");		
			}
		}
	}
	/**
	 * 关闭监听事件
	 */
	class CloseActionListener implements ActionListener {			// 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
