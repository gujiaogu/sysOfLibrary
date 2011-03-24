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
	
	private final JTextField operator;	//����Ա

	private JTextField todaydate;		//��ǰʱ��

	private JTable table;				//ͼ���б�

	private JTextField price;			//ͼ��۸�

	private JTextField bookType;		//ͼ�����

	private JTextField bookName;		//ͼ������

	private JTextField bookISBN;		//ͼ����

	private JTextField keepMoney;		//Ѻ��

	private JTextField number;			//�ɽ�����

	private JTextField readerName;		//��������

	private JTextField readerISBN;		//���߱��

	private String[] columnNames = { "�鼮���", "��������", "Ӧ������", "���߱��" };

	private Map map = BookTypeIdAndItemMap.getMap();//����ͼ������Id�����Ƶ�Map


	DefaultTableModel model = new DefaultTableModel();
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @description		���һ����¼�����ĩβ
	 * @author			����	
	 * @createDate		2011-2-28
	 * @param			
	 * @return					
	 *	
	 * @see						
	 */
	public final void add() {
		String str[] = new String[4];				
		str[0] = bookISBN.getText().trim();			//ͼ����	
		str[1] = String.valueOf(
				myfmt.format(new java.util.Date()));//��������
		str[2] = myfmt.format(getBackTime());		//Ӧ��������
		str[3] = readerISBN.getText().trim();		//���߱��
		model.addRow(str);							//����¼��ӵ���ĩβ
	}
	/**
	 * @description		���㻹��ʱ��
	 * @author			����	
	 * @createDate		2011-2-28
	 * @param			
	 * @return			Date ����ʱ��		
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
		setTitle("ͼ����Ĺ���");
		setIconifiable(true); 				// ���ô������С��
		setClosable(true); 					// ���ô���ɹر�
		setBounds(100, 100, 500, 375);
		
		/*�����һ�鶫��*/
		final JPanel panel = new JPanel();						//������
		getContentPane().add(panel);
		
		final JScrollPane scrollPane = new JScrollPane();		//�ű������
		scrollPane.setPreferredSize(new Dimension(400, 100));	//���ù������Ĵ�С
		panel.add(scrollPane);									//�����������ӵ���������

		table = new JTable();									//�������
		scrollPane.setViewportView(table);						//��������õ����������
		model.setColumnIdentifiers(columnNames);				//����ģ���е��б�ʶ��
		table.setModel(model);									//���ñ�����ͼģ��
		
		/*�����ǿ鶫��*/
		final JPanel panel_1 = new JPanel();					//���ߺ�Ҫ���鼮���
		panel_1.setPreferredSize(new Dimension(0, 120));		//���ø�����С
		getContentPane().add(panel_1, BorderLayout.NORTH);		//���Ϸ��������ӵ���������

		final JSplitPane splitPane = new JSplitPane();			//�����ָ���� 
		panel_1.add(splitPane);									//���ָ������ӵ��Ϸ��������

		/*����ǿ飨������Ϣ�ǿ飩*/
		final JPanel panel_3 = new JPanel();					//���������壨��㣩
		panel_3.setPreferredSize(new Dimension(240, 110));		//��������С
		splitPane.setLeftComponent(panel_3);					//�������ӵ�JSplitPane���

		final JPanel panel_5 = new JPanel();					//���������壨�ڲ㣩
		final GridLayout gridLayout = new GridLayout(0, 2);		//�������Ĳ��ֹ�����
		gridLayout.setHgap(2);									//�������ˮƽ���
		gridLayout.setVgap(10);									//��������Ĵ�ֱ����
		panel_5.setLayout(gridLayout);							//�������Ĳ��ֹ�����
		panel_5.setPreferredSize(new Dimension(150, 100));		//�������Ĵ�С
		panel_3.add(panel_5);									//���������ӵ���������

		final JLabel label = new JLabel();			//���߱�ű�ǩ
		label.setText("���߱�ţ�");
		panel_5.add(label);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(
				new bookISBNListenerlostFocus());	//�����߱�ǩ��ӵ��¼�
		panel_5.add(readerISBN);

		final JLabel label_1 = new JLabel();		//����������ǩ
		label_1.setText("����������");
		panel_5.add(label_1);

		readerName = new JTextField();
		readerName.setEditable(false);
		panel_5.add(readerName);

		final JLabel label_2 = new JLabel();		//�ɽ�������ǩ
		label_2.setText("�ɽ�������");
		panel_5.add(label_2);

		number = new JTextField();
		number.setEditable(false);
		panel_5.add(number);

		final JLabel label_4 = new JLabel();		//Ѻ���ǩ					
		label_4.setText("Ѻ    ��");
		panel_5.add(label_4);

		keepMoney = new JTextField();
		keepMoney.setEditable(false);
		panel_5.add(keepMoney);

		/*�ұ��ǿ飨�鼮��Ϣ�ǿ飩*/
		final JPanel panel_4 = new JPanel();					//�����ұ���壨��㣩
		final GridLayout gridLayout_1 = new GridLayout(0, 2);	//�������Ĳ��ֹ�����
		gridLayout_1.setVgap(10);								//��������Ĵ�ֱ���
		panel_4.setLayout(gridLayout_1);						//���ò��ֹ�����
		panel_4.setPreferredSize(new Dimension(240, 110));		//���ô�С
		splitPane.setRightComponent(panel_4);					//�������ӵ�JSplitPane���ұ� 

		final JLabel label_5 = new JLabel();		//�鼮��ű�ǩ
		label_5.setText("�鼮��ţ�");
		panel_4.add(label_5);

		bookISBN = new JTextField();
		bookISBN.setDocument(new MyDocument(13));
		bookISBN.addKeyListener(
				new bookISBNListenerlostFocus());	//�鼮����ı����¼�
		panel_4.add(bookISBN);

		final JLabel label_6 = new JLabel();		//�鼮���Ʊ�ǩ
		label_6.setText("�鼮���ƣ�");
		panel_4.add(label_6);

		bookName = new JTextField();
		bookName.setEditable(false);
		panel_4.add(bookName);

		final JLabel label_7 = new JLabel();		//�鼮����ǩ
		label_7.setText("�鼮���");			
		panel_4.add(label_7);

		bookType = new JTextField();
		bookType.setEditable(false);
		panel_4.add(bookType);

		final JLabel label_8 = new JLabel();		//�鼮�۸��ǩ
		label_8.setText("�鼮�۸�");
		panel_4.add(label_8);

		price = new JTextField();
		price.setEditable(false);
		panel_4.add(price);

		/*�����ǿ鶫��*/
		final JPanel panel_2 = new JPanel();					//�·����
		panel_2.setPreferredSize(new Dimension(0, 100));		//���ô�С
		getContentPane().add(panel_2, BorderLayout.SOUTH);		//���·������ӵ�������

		final JPanel panel_7 = new JPanel();					//�·��������
		final GridLayout gridLayout_2 = new GridLayout(0, 2);	//�������ֹ�����
		gridLayout_2.setVgap(10);								//���������ֱ���
		panel_7.setLayout(gridLayout_2);						//���ò��ֹ�����
		panel_7.setPreferredSize(new Dimension(280, 50));		//��������С
		panel_2.add(panel_7);									//���·����������ӵ��·������

		final JLabel label_9 = new JLabel();	//��ǰ���ڱ�ǩ
		label_9.setText("��ǰ���ڣ�");
		panel_7.add(label_9);

		todaydate = new JTextField();
		todaydate.setEditable(false);
		todaydate.setPreferredSize(new Dimension(0, 0));
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		todaydate.setText(s.format(new java.util.Date()));
		todaydate.setFocusable(false);
		panel_7.add(todaydate);

		final JLabel label_11 = new JLabel();	//����Ա��ǩ
		label_11.setText("����Ա��");
		panel_7.add(label_11);

		operator  =new JTextField(user.getName());
		operator.setEditable(false);
		panel_7.add(operator);

		final JPanel panel_8 = new JPanel();	//��������ť�����
		panel_8.setLayout(new FlowLayout());
		panel_8.setPreferredSize(new Dimension(200, 60));
		panel_2.add(panel_8);

		final JButton buttonBorrow = new JButton();
		buttonBorrow.setText("�����ǰͼ��");
		buttonBorrow.addActionListener(new BorrowActionListener());
		panel_8.add(buttonBorrow);

		final JButton buttonClear = new JButton();
		buttonClear.setText("������м�¼");
		buttonClear.addActionListener(new ClearActionListener(model));
		panel_8.add(buttonClear);

		setVisible(true);
	}

	/**
	 * 
	 * @(#)							
	 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
	 * ������		
	 *		
	 * @author		����
	 * @version		final		
	 * @createDate	2011-3-9	 
	 * @see
	 */
	class bookISBNListenerlostFocus extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { // �ж����ı����Ƿ�����س���
				if (readerISBN.getText().trim().length()!=0
						&& bookISBN.getText().trim().length()!=0) {//�ж��鼮��źͶ��߱���Ƿ�Ϊ��
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
					List list5 = Dao.selectReader(readerISBNs);			// �˶����Ƿ���tb_reader����
					List list4 = Dao.selectBookInfo(ISBNs);				// �����Ƿ���tb_bookInfo����
					if (!readerISBNs.isEmpty() && list5.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"�˶��߱��û��ע�ᣬ��ѯ������߱���Ƿ�����");
						return;
					}
					if (list4.isEmpty() && !ISBNs.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"��ͼ���û�д��飬��ѯ����ͼ�����Ƿ�����");
						return;
					}
					for (int i = 0; i < list5.size(); i++) {
						Reader reader = (Reader) list5.get(i);
						readerName.setText(reader.getName());
						number.setText(reader.getMaxNum());
						keepMoney.setText(reader.getKeepMoney() + "");
					}
					if (Integer.parseInt(number.getText().trim()) <= 0) {
						JOptionPane.showMessageDialog(null, "�������Ѿ���������������");
						return;
					}

					add();
					number.setText(String.valueOf(Integer.parseInt(number
							.getText().trim()) - 1));
				}

				else
					JOptionPane.showMessageDialog(null, "��������������룡���鼮������");
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
				JOptionPane.showMessageDialog(null, "ͼ�������ɣ�");
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
				JOptionPane.showMessageDialog(null, "�������ʱû�����ݣ�����н��Ĳ���");
			}
		}
	}
}
