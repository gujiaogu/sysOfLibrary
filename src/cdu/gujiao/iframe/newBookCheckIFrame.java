package cdu.gujiao.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.Operater;
import cdu.gujiao.model.OrderAndBookInfo;
import cdu.gujiao.util.BookTypeIdAndItemMap;


public class newBookCheckIFrame extends JInternalFrame {

	/**图书类别文本框*/
	private JTextField bookType;
	/**订购价格文本框*/
	private JTextField orderPrice;
	/**折扣文本框*/
	private JTextField zk;
	/**表格*/
	private JTable table;
	/**按钮组*/
	private ButtonGroup buttonGroup = new ButtonGroup();
	/**价格文本框*/
	private JTextField price;
	/**操作员文本框*/
	private JTextField operator;
	/**订购数量文本框*/
	private JTextField orderNumber;
	/**书籍编号文本框*/
	private JTextField ISBN;
	/**订购日期格式文本框*/
	private JFormattedTextField orderDate;
	/**操作员信息*/
	private Operater user = BookLoginIFrame.getUser(); 
	/**未验收*/
	JRadioButton radioButton2;
	/**日期格式化*/
	SimpleDateFormat myfmt =new SimpleDateFormat("yyyy-MM-dd");
	/**已验收*/
	JRadioButton radioButton1;
	
	
	private String[] columnNames={ "图书编号", "订购日期", "订购数量","操作员","是否验收","折扣","图书类别","图书名称","作者","译者","出版社","出版日期","图书价格"};
	private Map map=BookTypeIdAndItemMap.getMap();
	
	/**
	 * @description		得到全部订购信息
	 * @author			顾蛟	
	 * @createDate		2011-3-8
	 * @param			List 从数据库里查询得到的结果集
	 * @return			Object[][]用于填充表格的数组		
	 * @see						
	 */
	private Object[][] getFileStates(List list){
		Object[][]results = new Object[list.size()][columnNames.length];
		for(int i=0;i<list.size();i++){
			OrderAndBookInfo order = (OrderAndBookInfo)list.get(i);
			results[i][0]=order.getISBN();
			results[i][1]=order.getOrderdate();
			results[i][2]=order.getNumber();
			results[i][3]=order.getOperator();
			
			String CheckAndAccepts;
			if(order.getCheckAndAccept().equals("1"))//1代表没有验收
				CheckAndAccepts="否";
			else
				CheckAndAccepts="是";
			results[i][4]=CheckAndAccepts;
			
			results[i][5]=order.getZk();
			
			String bookTypes=String.valueOf(BookTypeIdAndItemMap.getMap().get(order.getTypeId()));
			results[i][6]=bookTypes;
			
			results[i][7]=order.getBookname();
			results[i][8]=order.getWriter();
			results[i][9]=order.getTraslator();
			results[i][10]=order.getPublisher();
			results[i][11]=order.getDate();
			results[i][12]=order.getPrice();
		}
		return results;
	         		
	}

	/**
	 * Create the frame
	 */
	public newBookCheckIFrame() {
		super();
		setClosable(true);				//设置窗体可关闭
		setIconifiable(true);			//设置窗体可最小化
		setAutoscrolls(true);			//设置自动增大
		setTitle("图书验收");			//设置标题
		setBounds(100, 100, 700, 420);	//设置窗体大小
		

		final JPanel panel = new JPanel();	//创建主面板
		getContentPane().add(panel);		//将主面析添加到窗体上

		final JScrollPane scrollPane = 
			new JScrollPane();				//创建滚动面板		
		scrollPane.setPreferredSize(
				new Dimension(680, 180));	//设置大小
		panel.add(scrollPane);				//将滚动面板添加到主面板上

		final DefaultTableModel model = 
			new DefaultTableModel();		//创建表格视图模型
		Object[][] results = 
			getFileStates(
					Dao.selectBookOrder());	//得到表格中的内容
		model.setDataVector(
				results,columnNames);		//给表格视图模型添加内容
		table = new JTable();				//创建表格
		table.setModel(model);				//设置表格的视图模型
		table.setAutoResizeMode(
				JTable.AUTO_RESIZE_OFF);	//关闭表格自动变小
		scrollPane.setViewportView(table);	//将表格添加到滚动面板上
		table.addMouseListener(
				new TableListener());		//给表格注册事件监听器

		final JPanel panel_1_1 =
			new JPanel();					//创建订购信息面板
		final GridLayout gridLayout =
			new GridLayout(0, 4);			//创建布局管理器
		gridLayout.setVgap(5);				//组件间的垂直间距
		panel_1_1.setLayout(gridLayout);	//设置布局管理器
		panel_1_1.setPreferredSize(
				new Dimension(450, 150));	//设置大小
		panel.add(panel_1_1);				//将订购信息面板添加到主面板上

		final JLabel label_1 = new JLabel();//创建订购日期标签
		label_1.setText("订购日期：");		//设置文本
		panel_1_1.add(label_1);				//将标签添加到面板上

		orderDate = 
			new JFormattedTextField(
					myfmt.getDateInstance());	//设置格式文本框格式
		orderDate.setValue(
				new java.util.Date());			//订购日期文本框值
		orderDate.addKeyListener(
				new DateListener());			//注册监听器

		panel_1_1.add(orderDate);				//将文本框添加到面板上

		final JLabel label_3 = new JLabel();	//创建标签
		label_3.setText("书籍编号：");			//设置文本
		panel_1_1.add(label_3);					//添加到面板上

		ISBN = new JTextField();				//创建文本框
		panel_1_1.add(ISBN);					//添加到面板上

		final JLabel label_4 = new JLabel();	//创建订购数量标签
		label_4.setText("订购数量：");			//设置文本
		panel_1_1.add(label_4);					//添加到面板上

		orderNumber = new JTextField();			//创建文本框
		panel_1_1.add(orderNumber);				//添加到面板上

		final JLabel label_5 = new JLabel();	//创建标签
		label_5.setText("操作员：");				//设置文本
		panel_1_1.add(label_5);					//添加到面板上
		operator = 
			new JTextField(user.getName());		//创建文本框并设置初始值
		panel_1_1.add(operator);				//添加到面板上

		final JLabel label_6 = new JLabel();	//创建标签
		label_6.setText("图书类别：");			//设置文本
		panel_1_1.add(label_6);					//添加到面板上

		bookType = new JTextField();			//创建文本框
		panel_1_1.add(bookType);				//添加到面板上


		final JLabel label_7 = new JLabel();	//创建标签
		label_7.setText("图书原价格：");			//设置文本
		panel_1_1.add(label_7);					//添加到面板上

		price = new JTextField();				//创建文本杠
		panel_1_1.add(price);					//添加到面板上

		final JLabel label_9 = new JLabel();	//创建标签
		label_9.setText("是否验收：");			//设置文本
		panel_1_1.add(label_9);					//添加到面板上

		final JPanel panel_1 = new JPanel();	//放按钮组的面板
		panel_1_1.add(panel_1);					//添加到面板上

		radioButton1 = new JRadioButton();		//创建单选按钮
		radioButton1.setSelected(true);			//设置默认为选中
		panel_1.add(radioButton1);				//添加到面板上
		buttonGroup.add(radioButton1);			//添加到按钮组中
		radioButton1.setText("是");				//设置文本

		radioButton2= new JRadioButton();		//创建单选按钮
		panel_1.add(radioButton2);				//添加到面板上
		buttonGroup.add(radioButton2);			//添加到按钮组中
		radioButton2.setText("否");				//设置文本

		final JLabel label = new JLabel();		//创建标签
		label.setText("折扣：");					//设置文本
		panel_1_1.add(label);					//添加到面板上

		zk = new JTextField();					//创建文本杠
		panel_1_1.add(zk);						//添加到面板上

		final JLabel label_2 = new JLabel();	//创建标签
		label_2.setText("订购价格：");			//设置文本
		panel_1_1.add(label_2);					//添加到面板上

		orderPrice = new JTextField();			//创建文本框
		panel_1_1.add(orderPrice);				//添加到面板上
		setVisible(true);						//设置窗体可见

		final JPanel panel_2 = new JPanel();	//创建下方面板
		getContentPane().add(
				panel_2,
				BorderLayout.SOUTH);			//添加到窗体下方

		final JButton buttonCheck = 
			new JButton();						//创建按钮 
		panel_2.add(buttonCheck);				//添加到面板上
		buttonCheck.setText("验收");				//设置文本
		buttonCheck.addActionListener(
				new CheckActionListener(model));//注册监听器

		final JButton buttonExit =
			new JButton();						//创建退出按钮
		panel_2.add(buttonExit);				//添加到面板上
		buttonExit.addActionListener(
				new CloseActionListener());		//注册监听器
		buttonExit.setText("退出");				//设置文本
	}
	/**
	 * 日期文本框监听事件，提醒日期格式 
	 */
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "时间格式请使用\"2007-05-10\"格式");
			}
		}
	}
	/**
	 * 表格选中监听事件 
	 */
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			
			int selRow = table.getSelectedRow();
			ISBN.setText(table.getValueAt(selRow, 0).toString().trim());
			orderDate.setText(table.getValueAt(selRow, 1).toString().trim());
			
			orderNumber.setText(table.getValueAt(selRow, 2).toString().trim());
			operator.setText(table.getValueAt(selRow, 3).toString().trim());
			
			bookType.setText(table.getValueAt(selRow, 6).toString().trim());
			
			price.setText(table.getValueAt(selRow, 12).toString().trim());
			if(table.getValueAt(selRow, 4).toString().trim().equals("否"))//1代表没有验收
				radioButton2.setSelected(true);
			else
				radioButton1.setSelected(true);
			zk.setText(table.getValueAt(selRow, 5).toString().trim());
			orderPrice.setText(Double.valueOf(table.getValueAt(selRow, 12).toString().trim())*Double.valueOf(table.getValueAt(selRow, 5).toString().trim())+"");
			
		}
	}
	/**
	 * 添加关闭按钮的事件监听器
	 */
	class CloseActionListener implements ActionListener {			
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	/**
	 * “验收”按钮事件
	 */
	class CheckActionListener implements ActionListener{
		private final DefaultTableModel model;

		CheckActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(final ActionEvent e) {
			if(radioButton2.isSelected()){
				String ISBNs=ISBN.getText();
				int i=Dao.UpdateCheckBookOrder(ISBNs);
				if(i==1){
					JOptionPane.showMessageDialog(null, "验收成功！");
					Object[][] results=getFileStates(Dao.selectBookOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					radioButton1.setSelected(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "您选择的图书已经进行过验收，请选择其他图书进行验收");
			}
			
		}
	}
}
