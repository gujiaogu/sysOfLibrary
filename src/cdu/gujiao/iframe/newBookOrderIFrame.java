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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Item;
import cdu.gujiao.model.Operater;
import cdu.gujiao.util.BookTypeIdAndItemMap;
import cdu.gujiao.util.MyDocument;


/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		新书订购界面
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-3-7	 
 * @see 				
 */
public class newBookOrderIFrame extends JInternalFrame {

	/**图书名称*/
	private JTextField bookName;	
	/**折扣*/
	private JTextField zk;								
	/**是否验收按钮组*/
	private ButtonGroup buttonGroup = new ButtonGroup();
	/**出版社下拉列表框*/
	private JComboBox cbs;								
	/**图书价格*/
	private JTextField price;							
	/**图书类别下拉列表框*/
	private JComboBox bookType;							
	/**操作员*/
	private JTextField operator;
	/**订购数量*/
	private JTextField orderNumber;
	/**图书编号*/
	private JTextField ISBN;
	/**订购日期*/
	private JFormattedTextField orderDate;
	/**图书类别下拉列表框视图模型*/
	DefaultComboBoxModel bookTypeModel;
	/**出版社下拉列表框视图模型*/
	DefaultComboBoxModel cbsModel;
	/**确定验收*/
	JRadioButton radioButton1;
	/**未验收*/
	JRadioButton radioButton2;
	/**图书类别ID和名称映射*/
	Map map;
	/**表视图模型*/
	DefaultTableModel model;
	/**登录用户信息*/
	private Operater user = BookLoginIFrame.getUser(); 
	/**日期格式化*/
	SimpleDateFormat myfmt =new SimpleDateFormat("yyyy-MM-dd");

	
	public newBookOrderIFrame() {
		super();
		setTitle("新书订购管理");			//设置标题
		setIconifiable(true);				//设置可最小化
		setClosable(true);					//设置可关闭
		setBounds(100, 100, 500, 320);		//设置窗体大小

		final JPanel panel = new JPanel();	//创建面板
		panel.setLayout(new FlowLayout());	//设置布局管理器
		panel.setPreferredSize(
				new Dimension(0, 240));		//设置面板大小
		getContentPane().add(panel);		//将面板添加到窗体里

		final JPanel panel_4 = new JPanel();//创建订购信息面板
		panel_4.setBorder(new TitledBorder(null,
				"订购信息", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null,
				null));						//设置边界
		panel_4.setPreferredSize(
				new Dimension(480, 120));	//设置订购信息面板大小
		final GridLayout gridLayout_1 =
			new GridLayout(0, 4);			//创建布局管理器
		gridLayout_1.setVgap(8);			//设置组件间的竖直间距
		panel_4.setLayout(gridLayout_1);	//设置布局管理器
		panel.add(panel_4);					//将订购信息面板添加到主面板中

		final JLabel label_1 = new JLabel();//创建订购日期标签标签
		panel_4.add(label_1);				//将标签添加到订购信息面板
		label_1.setText("订购日期：");		//设置文本

		
		orderDate = 
			new JFormattedTextField(
					myfmt.getDateInstance());//创建格式文本框并设置格式
		panel_4.add(orderDate);
		orderDate.setValue(
				new java.util.Date());		//设置当前日期
		orderDate.setEditable(false);

		final JLabel label_4 = new JLabel();//订购数量标签
		panel_4.add(label_4);				//将标签添加到面板中
		label_4.setText("订购数量：");		//设置文本

		orderNumber = new JTextField();		//订购数量文本框
		panel_4.add(orderNumber);			//将文本框添加到面板上
		orderNumber.setDocument(
				new MyDocument(4));			//设置文本框的长度
		orderNumber.addKeyListener(
				new NumberListener());		//给文本框添加事件监听器
		final JLabel label_5 = new JLabel();//创建操作员标签
		panel_4.add(label_5);				//添加到面板上
		label_5.setText("操作员：");			//设置文本
		operator  =new JTextField(
				user.getName());			//创建文本框并设置值
		panel_4.add(operator);				//将文本框添加到面板上
		operator.setEditable(false);		//设置文本框不可编辑
		
		final JLabel label_9 = new JLabel();//创建是否验收标签
		panel_4.add(label_9);				//添加到面板上
		label_9.setText("是否验收：");		//设置文本

		final JPanel panel_3 = new JPanel();//按钮组面板
		panel_4.add(panel_3);				//将按钮组面板添加到订购信息面板上

		radioButton1 = new JRadioButton();	//创建单选按钮
		buttonGroup.add(radioButton1);		//将单选按钮添加到按钮组里面
		panel_3.add(radioButton1);			//再将单选按钮添加到按钮组面板上
		radioButton1.setText("是");			//设置文本

		radioButton2 = new JRadioButton();	//创建单选按钮
		radioButton2.setSelected(true);		//设置默认被选状态
		buttonGroup.add(radioButton2);		//将其添加到按钮组中
		panel_3.add(radioButton2);			//将其添加到面板上
		radioButton2.setText("否");			//设置文本

		final JLabel label = new JLabel();	//创建标签
		panel_4.add(label);					//添加到订购信息面板上
		label.setText("折扣：");				//设置文本

		zk = new JTextField();				//创建文本框
		zk.setDocument(new MyDocument(1));	//设置可输入的长度
		zk.addKeyListener(
				new NumberListener());		//给其添加事件监听器
		panel_4.add(zk);					//添加到订购信息面板上


		final JPanel panel_1 = new JPanel();//创建图书信息面板
		panel_1.setBorder(new TitledBorder(null, 
				"图书信息", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null, 
				null));						//设置面板边界
		final GridLayout gridLayout =
			new GridLayout(0, 4);			//创建布局管理器
		gridLayout.setVgap(5);
		panel_1.setLayout(gridLayout);		//设置布局管理器
		panel_1.setPreferredSize(
				new Dimension(0, 100));		//设置大小
		getContentPane().add(panel_1,
				BorderLayout.NORTH);		//将图书信息面板添加到窗体上

		final JLabel label_3 = new JLabel();//创建标签
		label_3.setText("书籍编号：");		//设置文本
		panel_1.add(label_3);				//添加到图书信息面板上

		ISBN = new JTextField();				//创建文本框
		ISBN.setDocument(
				new MyDocument(13));			//设置可输入的长度
		ISBN.addFocusListener(
				new ISBNListenerlostFocus());	//添加焦点监听事件
		panel_1.add(ISBN);						//添加到图书信息面板上

		final JLabel label_2 = new JLabel();	//w创建标签
		label_2.setText("图书名称：");			//设置文本
		panel_1.add(label_2);					//添加到面板上

		bookName = new JTextField();			//创建文本框
		panel_1.add(bookName);					//添加到面板上

		final JLabel label_6 = new JLabel();	//创建标签
		label_6.setText("图书类别：");			//设置文本
		panel_1.add(label_6);					//添加到面板上

		bookType = new JComboBox();						//创建下拉列表框
		bookTypeModel = 
			BookTypeIdAndItemMap.bookTypeModel;			//获得图书类别视图模型
		bookType.setModel(bookTypeModel);				//设置视图模型
		panel_1.add(bookType);							//将下拉列表框添加到面板上

		final JLabel label_8 = new JLabel();			//创建标签
		label_8.setText("出版社：");						//设置文本
		panel_1.add(label_8);							//将标签添加到面板上
		cbs = new JComboBox();							//创建出版社下拉列表框
		cbsModel=(DefaultComboBoxModel)cbs.getModel();	//获得视图模型
		panel_1.add(cbs);								//添加到面板上

		final JLabel label_7 = new JLabel();	//创建标签
		label_7.setText("图书价格：");			//设置文本
		panel_1.add(label_7);					//添加到面板上

		price = new JTextField();				//创建文本框
		price.setDocument(new MyDocument(5));	//设置文本框可输入长度			
		panel_1.add(price);						//将标签添加到面板上


		map = BookTypeIdAndItemMap.getMap();	
		String[] array = 
			new String[]{"出版社1",
				"出版社2",
				"出版社3",
				"出版社4"};							//出版社下拉列表数组
		cbs.setModel(
				new DefaultComboBoxModel(array));	//设置视图模型

		final JPanel panel_2 = new JPanel();		//创建下方面板
		panel_2.setPreferredSize(
				new Dimension(0, 50));				//设置大小
		getContentPane().add(panel_2,
				BorderLayout.SOUTH);				//添加到窗体下方

		final JButton buttonAdd = new JButton();	//创建按钮
		buttonAdd.setText("添加");					//设置文本
		buttonAdd.addActionListener(
				new ButtonAddLisenter());			//添加监听器
		panel_2.add(buttonAdd);						//将按钮添加到面板上

		final JButton ButtonExit = new JButton();	//创建按钮
		ButtonExit.setText("退出");					//设置文本
		ButtonExit.addActionListener(
				new CloseActionListener());			//添加监听器
		panel_2.add(ButtonExit);					//将按钮添加到面板上
		setVisible(true);							//设置窗体可见
	}
	/**
	 * 添加按钮事件监听器
	 */
	class ButtonAddLisenter implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			if(orderDate.getText().isEmpty()){		//判断订书日期框是否为空
				JOptionPane.showMessageDialog(null, "订书日期文本框不可为空");
				return;
			}
			if(ISBN.getText().isEmpty()){			//判断图书编号文本框是否为空
				JOptionPane.showMessageDialog(null, "图书编号文本框不可为空");
				return;
			}
			if(orderNumber.getText().isEmpty()){	//判断订书数量文本框是否为空
				JOptionPane.showMessageDialog(null, "订书数量文本框不可为空");
				return;
			}
			if(operator.getText().isEmpty()){		//判断操作员文本框是否为空
				JOptionPane.showMessageDialog(null, "操作员文本框不可为空");
				return;
			}
					
			if(price.getText().isEmpty()){			//判断价格文本框是否为空
				JOptionPane.showMessageDialog(null, "价格文本框不可为空");
				return;
			}
			if(!Dao.selectBookOrder(ISBN.getText().trim()).isEmpty()){	//判断添加书号是否重复
				JOptionPane.showMessageDialog(null, "添加书号重复！");
				return;
			}
			
			String checkAndAccept="0";
			if(radioButton2.isSelected()){			//判断哪个单选按钮被选中
				checkAndAccept="1";
			}
			
			Double zks = 
				Double.valueOf(zk.getText())/10;	//计算折扣
			
			try{
				int i=Dao.InsertBookOrder(ISBN.getText().trim(), java.sql.Date.valueOf(orderDate.getText().trim()), orderNumber.getText().trim(), operator.getText().trim(), checkAndAccept,zks);
				if(i==1){
					JOptionPane.showMessageDialog(null, "添加成功！");
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	/**
	 *检查是否为数字 
	 */
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){//indexof返回输入字符串在该字符串中第一次出的时候的索引
				e.consume();//不是这里的按键内容时，不显示
			}
		}
	}
//	class ISBNListener extends KeyAdapter {//使用回车键进行触发事件方法
//		public void keyTyped(KeyEvent e) {
//			if (e.getKeyChar() == '\n') { // 判断在文本框是否输入回车。
//				String ISBNs = ISBN.getText().trim();
//				List list = Dao.selectBookInfo(ISBNs);
//				System.out.println(list.isEmpty());
//				if(list.isEmpty()){
//					new BookAddIFrame();
//				}
//				for (int i = 0; i < list.size(); i++) {
//					BookInfo bookinfo = (BookInfo) list.get(i);
//					bookName.setText(bookinfo.getBookname());
//					bookType.setSelectedItem(map.get(bookinfo.getTypeid()));
//					cbs.setSelectedItem(bookinfo.getPublisher());
//					price.setText(String.valueOf(bookinfo.getPrice()));
//
//				}
//			}
//		}
//	}
	/**
	 * 书籍编号文本框事件监听器
	 */
	class ISBNListenerlostFocus extends FocusAdapter{
		public void focusLost(FocusEvent e){
			String ISBNs = ISBN.getText().trim();
			if(!Dao.selectBookOrder(ISBN.getText().trim()).isEmpty()){//检查该图书是否已经添加到订购信息中
				JOptionPane.showMessageDialog(null, "已经为此编号图书添加订购信息，请输入其他图书编号！");
				ISBN.setText("");
				bookName.setText("");
				price.setText("");
				return;
			}
			List list = Dao.selectBookInfo(ISBNs);			//根据书籍编号查询书籍信息
			if(list.isEmpty()&&!ISBN.getText().isEmpty()){	//检查该书籍是否存在
				ISBN.setText("");
				bookName.setText("");
				price.setText("");
				JOptionPane.showMessageDialog(null, "图书信息表中无此书号，请您首先到基础数据维护中进行图书信息添加操作");
			}
			if(list.size() == 1){							//如果只有一条记录
				BookInfo bookinfo = (BookInfo) list.get(0);
				bookName.setText(bookinfo.getBookname());
				bookType.setSelectedItem(map.get(bookinfo.getTypeid()));
				cbs.setSelectedItem(bookinfo.getPublisher());
				price.setText(String.valueOf(bookinfo.getPrice()));
			}else{											//记录有重复
				return;
			}
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
}
