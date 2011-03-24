package cdu.gujiao.iframe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Item;
import cdu.gujiao.util.CreateIcon;
import cdu.gujiao.util.BookTypeIdAndItemMap;
import cdu.gujiao.util.MyDocument;

/**
 * @(#)							
 * 版权：		成都大学毕业设计		 	
 * 描述：		图书信息修改窗体
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-2-21	 
 * @see 				
 */
public class BookModiAndDelIFrame extends JInternalFrame {
	private JTable table;
	private JFormattedTextField price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField publisher;
	private JTextField writer;
	private JTextField ISBN;
	private JTextField bookName;
	private JComboBox bookType;
	DefaultComboBoxModel bookTypeModel;
	private Item item;
	Map map=new HashMap();
	private String[] columnNames;
	private Map m=BookTypeIdAndItemMap.getMap();

	/**
	 * @description		取数据库中图书相关信息放入表格中
	 * @author			顾蛟	
	 * @createDate		2011-2-21
	 * @param			list 封装所有图书的信息
	 * @return			所有图书信息的数组		
	 *	
	 * @see						
	 */
	private Object[][] getFileStates(List list){
		
		String[] columnNames = { "图书编号", "图书类别", "图书名称", "作者", "译者", "出版商",
				"出版日期", "价格" };
		Object[][]results =
			new Object[list.size()][columnNames.length];//二维数组用来保存所有记录
		
		for(int i=0;i<list.size();i++){					//遍历list
			BookInfo bookinfo=(BookInfo)list.get(i);	//取出图书记录
			results[i][0]=bookinfo.getISBN();			//设置图书编号
			String booktypename =
					String.valueOf(BookTypeIdAndItemMap.getMap().get(bookinfo.getTypeid()));//获得图书类别名称
			results[i][1]=booktypename;					//设置图书类别名称
			results[i][2]=bookinfo.getBookname();		//设置图书名称
			results[i][3]=bookinfo.getWriter();			//设置图书作者
			results[i][4]=bookinfo.getTranslator();		//设置图书译者
			results[i][5]=bookinfo.getPublisher();		//设置出版社
			results[i][6]=bookinfo.getDate();			//设置出版日期
			results[i][7]=bookinfo.getPrice();			//设置书籍价格
		}
		return results;									//范围二维数组的书籍记录
	         		
	}
	public BookModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout 
				= new BorderLayout();					//边框布局管理器
		getContentPane().setLayout(borderLayout);		//使用边框布局管理器
		setIconifiable(true);							// 设置窗体可最小化
		setClosable(true);								// 设置窗体可关闭
		setTitle("图书信息修改");						// 设置窗体标题
		setBounds(100, 100, 593, 406);					// 设置窗体位置和大小

		final JPanel mainPanel = new JPanel();				//主面板
		final BorderLayout borderLayout_1 =
			new BorderLayout();								//边框布局管理器
		borderLayout_1.setVgap(5);							//设置组件之间垂直距离
		mainPanel.setLayout(borderLayout_1);				//使用边框布局管理器
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));	//设置边框
		getContentPane().add(mainPanel);					//将主面板添加到窗体中

		final JScrollPane scrollPane = new JScrollPane();	//滚动面板
		mainPanel.add(scrollPane);							//将滚动面板添加到主面板中
		
		/*将table添加到scrollPane里面*/
		Object[][] results =
			getFileStates(Dao.selectBookInfo());			//获得书籍记录
		columnNames = new String[]{"图书编号", "图书类别", "图书名称",
				"作者", "译者", "出版商", "出版日期","价格"};	//列名列表
		table = new JTable(results,columnNames);			//创建表格
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	//自适应窗体
		table.addMouseListener(new TableListener());		//鼠标单击表格中的内容产生事件,将表格中的内容放入文本框中
		scrollPane.setViewportView(table);					//将表格添加到滚动面板中

		/*将图书信息修改面板添加到主面板的南部*/
		final JPanel bookPanel = new JPanel();				//书籍修改面板
		mainPanel.add(bookPanel, BorderLayout.SOUTH);		//添加到主面板底端
		final GridLayout gridLayout = new GridLayout(0, 6);	//网格布局
		gridLayout.setVgap(5);								//设置组件之间垂直距离
		gridLayout.setHgap(5);								//设置组件之间平行距离
		bookPanel.setLayout(gridLayout);					//设置书籍添加面板布局
		
		/*图书编号标签和文本框*/
		final JLabel ISBNLabel = new JLabel();				//创建图书编号标签
		ISBNLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		ISBNLabel.setText("书       号：");						//设置标签文本
		bookPanel.add(ISBNLabel);							//添加到书籍修改面板
		
		ISBN = new JTextField();							//创建书号文本框
		ISBN.setDocument(new MyDocument(13)); 				//书号文本框最大输入值为13
		bookPanel.add(ISBN);								//添加到书籍修改面板
		
		/*图书类别标签和下拉框*/
		final JLabel bookTypeLabel = new JLabel();			//创建书籍类别标签
		bookTypeLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		bookTypeLabel.setText("类       别：");					//设置标签文本
		bookPanel.add(bookTypeLabel);						//添加到书籍修改面板
		
		bookType = new JComboBox();							//创建书籍类别下拉框
		bookTypeModel =
				(DefaultComboBoxModel)bookType.getModel();	//设置类别模型
		List list=Dao.selectBookCategory();					//从数据库中取出图书类别
		for(int i=0;i<list.size();i++){						//遍历图书类别
			BookType booktype=(BookType)list.get(i);		//获得图书类别
			item=new Item();								//实例化图书类别选项
			item.setId((String)booktype.getId());			//设置图书类别编号
			item.setName((String)booktype.getTypeName());	//设置图书类别名称
			bookTypeModel.addElement(item);					//添加图书类别元素
			map.put(item.getId(), item);					//以键值对的形式保存
		}
		bookPanel.add(bookType);							//添加到书籍修改面板
		
		/*书名标签及文本框*/
		final JLabel bookNameLabel = new JLabel();			//创建书名标签
		bookNameLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		bookNameLabel.setText("书    名：");					//设置标签文本
		bookPanel.add(bookNameLabel);						//添加到书籍修改面板
		
		bookName = new JTextField();						//书名文本框
		bookPanel.add(bookName);							//添加到书籍修改面板
		
		/*创建作者标签和文本框*/
		final JLabel writerLabel = new JLabel();			//创建作者标签
		writerLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		writerLabel.setText("作       者：");					//设置标签文本
		bookPanel.add(writerLabel);							//添加到书籍修改面板
		
		writer = new JTextField();							//作者文本框
		bookPanel.add(writer);
		
		/*创建出版社标签和文本框*/
		final JLabel publisherLabel = new JLabel();			//创建出版社标签
		publisherLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		publisherLabel.setText("出  版  社：");				//设置标签文本
		bookPanel.add(publisherLabel);						//添加到书籍修改面板
		
		publisher = new JTextField();						//出版社文本框
		bookPanel.add(publisher);							//添加到书籍修改面板
		
		/*创建译者标签和文本框*/
		final JLabel translatorLabel = new JLabel();		//创建译者标签
		translatorLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		translatorLabel.setText("译    者：");					//设置标签文本
		bookPanel.add(translatorLabel);						//添加到书籍修改面板
		
		translator = new JTextField();						//译者文本框
		bookPanel.add(translator);							//添加到书籍修改面板
		
		/*创建日期标签及文本框*/
		final JLabel pubDateLabel = new JLabel();			//创建出版日期标签
		pubDateLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		pubDateLabel.setText("出 版 日 期：");					//设置标签文本
		bookPanel.add(pubDateLabel);						//添加到书籍修改面板
		
		SimpleDateFormat myfmt = 
			new SimpleDateFormat("yyyy-MM-dd");				//设置日期格式
		pubDate =
			new JFormattedTextField(myfmt.getDateInstance());//创建日期输入框
		pubDate.setValue(new java.util.Date());				//设置日期为当前系统时间
		bookPanel.add(pubDate);								//添加到书籍修改面板
		
		/*创建单价标签*/
		final JLabel priceLabel = new JLabel();				//创建单价标签
		priceLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//水平居中
		priceLabel.setText("单      价：");						//设置标签文本
		bookPanel.add(priceLabel);							//添加到书籍修改面板
		price= new JFormattedTextField();					//价格文本框
		price.addKeyListener(new NumberListener());			//注册监听器
		bookPanel.add(price);								//添加到书籍修改面板
		
		/*底部面板*/
		final JPanel bottomPanel = new JPanel();			//创建底部面板
		bottomPanel.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//设置边框
		getContentPane().add(bottomPanel,
				BorderLayout.SOUTH);						//添加到窗体底端
		final FlowLayout flowLayout = new FlowLayout();		//流布局管理器
		flowLayout.setVgap(2);								//设置组件之间垂直距离
		flowLayout.setHgap(30);								//设置组件之间平行距离
		flowLayout.setAlignment(FlowLayout.RIGHT);			//设置向右对齐
		bottomPanel.setLayout(flowLayout);					//设置底部面板布局
		
		/*修改按钮*/
		final JButton updateButton = new JButton();			//创建修改按钮
		updateButton.addActionListener(
				new UpdateBookActionListener ());			//注册监听器
		updateButton.setText("修改");						//设置按钮文本
		bottomPanel.add(updateButton);						//添加到底部面板
		
		/*关闭按钮*/
		final JButton closeButton= new JButton();			//创建关闭按钮
		closeButton.addActionListener(new ActionListener() {//注册监听器
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();						//关闭窗体
			}
		});
		closeButton.setText("关闭");							//设置按钮文本
		bottomPanel.add(closeButton);						//添加到底部面板
		
		setVisible(true);									//显示窗体可见
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, typeids, bookNames,writers,
					translators,publishers,dates,prices;					//声明变量
			int selRow = table.getSelectedRow();							//获得所选行号
			ISBNs = table.getValueAt(selRow, 0).toString().trim();			//获得书号
			typeids = table.getValueAt(selRow, 1).toString().trim();		//获得类别编号
			bookNames = table.getValueAt(selRow, 2).toString().trim();		//获得书名
			writers = table.getValueAt(selRow, 3).toString().trim();		//获得作者
			translators = table.getValueAt(selRow, 4).toString().trim();	//获得译者
			publishers = table.getValueAt(selRow, 5).toString().trim();		//获得出版社
			dates = table.getValueAt(selRow, 6).toString().trim();			//获得出版日期
			prices = table.getValueAt(selRow, 7).toString().trim();			//获得价格
			
			/*给各文本框赋值*/
			ISBN.setText(ISBNs);					//设置书号文本框为获得的书号信息
			bookTypeModel.setSelectedItem(typeids);	//设置类别下拉框所选项
			bookName.setText(bookNames);			//设置书名文本框为获得的书名信息
			writer.setText(writers);				//设置作者文本框为获得的作者信息
			translator.setText(translators);		//设置译者文本框为获得的译者信息
			publisher.setText(publishers);			//设置出版社文本框为获得的出版社信息
			pubDate.setText(dates);					//设置出版日期文本框为获得的出版日期信息
			price.setText(prices);					//设置价格文本框为获得的价格信息
		}
	}
	class UpdateBookActionListener  implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){			//判断是否输入了书籍编号
				JOptionPane.showMessageDialog(null, "书号文本框不可以为空");
				return;
			}
			if(ISBN.getText().length()!=13){		//判断书籍编号的长度是否为13
				JOptionPane.showMessageDialog(null, "书号文本框输入位数为13位");
				return;
			}
			if(bookName.getText().length()==0){		//判断是否输入了书籍名称
				JOptionPane.showMessageDialog(null, "图书名称文本框不可以为空");
				return;
			}
			if(writer.getText().length()==0){		//判断是否输入了作者
				JOptionPane.showMessageDialog(null, "作者文本框不可以为空");
				return;
			}
			if(publisher.getText().length()==0){	//判断是否输入了出版社
				JOptionPane.showMessageDialog(null, "出版人文本框不可以为空");
				return;
			}
			if(pubDate.getText().length()==0){		//判断是否输入了出版日期
				JOptionPane.showMessageDialog(null, "出版日期文本框不可以为空");
				return;
			}
			if(price.getText().length()==0){		//判断是否输入了书籍价格
				JOptionPane.showMessageDialog(null, "单价文本框不可以为空");
				return;
			}
			String ISBNs=ISBN.getText().trim();				//获得书籍编号
			Object selectedItem = 
				bookTypeModel.getSelectedItem();			//书籍类别选项
			if (selectedItem == null)  return;
			Item item = (Item)selectedItem;					//获得所选类别
			String bookTypes=item.getId();					//获得类别编号
			String translators=translator.getText().trim();	//获得译者信息
			String bookNames=bookName.getText().trim();		//获得书籍名称
			String writers=writer.getText().trim();			//获得作者信息
			String publishers=publisher.getText().trim();	//获得出版社信息
			String pubDates=pubDate.getText().trim();		//获得出版日期
			String prices=price.getText().trim();			//获得价格信息
			int i=Dao.Updatebook(ISBNs, bookTypes, bookNames, writers, translators,
					publishers, Date.valueOf(pubDates), Double.parseDouble(prices));
			if(i==1){										//如果返回更新记录数为1，表示修改成功
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results = 
					getFileStates(Dao.selectBookInfo());	//重新获得书籍信息
				DefaultTableModel model = 
					new DefaultTableModel();				//获得表格模型
				table.setModel(model);						//设置表格模型
				model.setDataVector(results,columnNames);	//设置模型数据和列名
			}
			
		}
	}
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}
	

}
