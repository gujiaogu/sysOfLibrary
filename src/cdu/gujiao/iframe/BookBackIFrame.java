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
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		ͼ��黹��
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-17	 
 * @see 				
 */
public class BookBackIFrame extends JInternalFrame {
	
	/**
	 * ���߽����б�
	 */
	private JTable table;
	/**
	 * �����ı���
	 */
	private JTextField fkmoney;
	/**
	 * �����������ı���
	 */
	private JTextField ccdays;
	/**
	 * ����������ı���
	 */
	private JTextField realdays;
	/**
	 * �涨�Ľ��������ı���
	 */
	private JTextField borrowdays;
	/**
	 * ���������ı���
	 */
	private JTextField borrowDate;
	/**
	 * ���߱���ı���
	 */
	private JTextField readerISBN;
	/**
	 * ��ͷ����
	 */
	private String[] columnNames = { "ͼ������", "ͼ��������","ͼ�����","��������","����������","����ʱ��","�黹ʱ��" };
	/**
	 * �����ͼģ��
	 */
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * ���ڸ�ʽ
	 */
	SimpleDateFormat myfmt=new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * �鼮���
	 */
	private String bookISBNs=null;
	/**
	 * ���߱��
	 */
	private String readerISBNs=null;
	/**
	 * �黹��¼��ID
	 */
	private int id;
	
	/**
	 * @description		��ѯ�������ݲ�������ӵ�����ͼ
	 * @author			����	
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
			model.addRow(str);				//��ӵ�����ͼ
		}
	}
	
	/**
	 * ��������
	 */
	public BookBackIFrame() {
		super();
		/*�����������*/
		setIconifiable(true);								// ���ô������С��
		setClosable(true);									// ���ô���ɹر�
		setTitle("ͼ��黹����");
		setBounds(100, 100, 550, 400);
		
		/*������岢��������*/
		final BackgroundPanel mainPanel =
			new BackgroundPanel();
		mainPanel.setSize(550, 400);
		mainPanel.setLayout(new BorderLayout());
		getContentPane().add(mainPanel);

		/*������Ϣ���*/
		final JPanel panel = new JPanel();					//������Ϣ���
		panel.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setPreferredSize(new Dimension(0, 200));		//���û�����Ϣ����С
		mainPanel.add(panel, BorderLayout.NORTH);			//��������Ϣ�����ӵ�JInternalFrame��

		/*���߱�����*/
		final JPanel panel_5 = new JPanel();				//���߱�����		
		final GridLayout gridLayout_1 =
				new GridLayout(0, 2);						//���߱����岼�ֹ�����
		gridLayout_1.setVgap(5);							//���ô�ֱ�߶�
		panel_5.setLayout(gridLayout_1);					//���ò��ֹ�����
		panel_5.setPreferredSize(new Dimension(400, 20));	//���ô�С
		panel.add(panel_5);									//�����߱�������ӵ�������Ϣ���

		/*���߱�ű�ǩ�����ı���*/
		final JLabel label_4 = new JLabel();				
		label_4.setText("���߱�ţ�");
		panel_5.add(label_4);

		readerISBN = new JTextField();
		readerISBN.setDocument(new MyDocument(13));
		readerISBN.addKeyListener(new readerISBNListenerlostFocus());
		panel_5.add(readerISBN);

		/*�Ź����������*/
		final JPanel panel_4 = new JPanel();				//�ű������
		panel_4.setLayout(new FlowLayout());
		panel_4.setPreferredSize(new Dimension(450, 130));
		panel.add(panel_4);

		/*�ű��Ĺ������*/
		final JScrollPane scrollPane = new JScrollPane();	//���һ��ScrollPane
		scrollPane.setPreferredSize(new Dimension(450, 120));
		panel_4.add(scrollPane);

		/*����������*/
		table = new JTable();
		scrollPane.setViewportView(table);					//����һ���ӿڲ�������ͼ
		model.setColumnIdentifiers(columnNames);			//���ñ��ͷ��
		table.setModel(model);								//���ñ����ʾ����
		table.addMouseListener(new TableListener());		//�������Ӽ�����
		
		/*�·����*/
		final JPanel panel_1 = new JPanel();				//�·����
		mainPanel.add(panel_1,BorderLayout.CENTER);

		/*������Ϣ���*/
		final JPanel panel_2 = new JPanel();				//������Ϣ���
		final GridLayout gridLayout_2 = new GridLayout(0, 4);
		gridLayout_2.setVgap(10);
		gridLayout_2.setHgap(5);
		panel_2.setLayout(gridLayout_2);
		panel_2.setBorder(new TitledBorder(null, "������Ϣ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_2.setPreferredSize(new Dimension(540, 110));
		panel_1.add(panel_2);

		/*�������ڱ�ǩ�����ı���*/
		final JLabel label_11 = new JLabel();
		label_11.setText("�������ڣ�");
		panel_2.add(label_11);

		borrowDate = new JTextField();
		borrowDate.setEditable(false);
		panel_2.add(borrowDate);

		/*�涨������ǩ�����ı���*/
		final JLabel label_12 = new JLabel();
		label_12.setText("�涨������");
		panel_2.add(label_12);

		borrowdays = new JTextField();
		borrowdays.setEditable(false);
		panel_2.add(borrowdays);

		/*ʵ��������ǩ���ı���*/
		final JLabel label_13 = new JLabel();
		label_13.setText("ʵ��������");
		panel_2.add(label_13);

		realdays = new JTextField();
		realdays.setEditable(false);
		panel_2.add(realdays);

		/*����������ǩ�����ı���*/
		final JLabel label_14 = new JLabel();
		label_14.setText("����������");
		panel_2.add(label_14);

		ccdays = new JTextField();
		ccdays.setEditable(false);
		panel_2.add(ccdays);

		/*�������ǩ�����ı���*/
		final JLabel label_15 = new JLabel();
		label_15.setText("�����");
		panel_2.add(label_15);

		fkmoney = new JTextField();
		fkmoney.setEditable(false);
		panel_2.add(fkmoney);

		/*�·���ť���*/
		final JPanel panel_3 = new JPanel();		
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel_3.setPreferredSize(new Dimension(540, 50));
		mainPanel.add(panel_3,BorderLayout.SOUTH);

		/*�·���ť���*/
		final JPanel panel_7 = new JPanel();		
		final GridLayout gridLayout_3 = new GridLayout(0, 4);
		gridLayout_3.setVgap(35);
		panel_7.setLayout(gridLayout_3);
		panel_3.add(panel_7);

		/*ͼ��黹��ť*/
		final JButton buttonback = new JButton();
		buttonback.setText("ͼ��黹");
		buttonback.addActionListener(
				new BookBackActionListener(model)); //���鰴ť�����¼�
		panel_7.add(buttonback);

		/*�˳���ť*/
		final JButton buttonExit= new JButton();
		buttonExit.setText("�˳�");
		buttonExit.addActionListener(
				new CloseActionListener());   		//�رհ�ť�����¼�
		panel_7.add(buttonExit);
		
		setVisible(true);
	}
	/**
	 * ���߱���ı��򣬰��س�ʱ���¼�
	 */
	class readerISBNListenerlostFocus extends KeyAdapter{
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == '\n') { 			//�ж����ı����Ƿ�����س���
				add();
			}
		}
	}
	/**
	 * �������¼�
	 */
	class TableListener extends MouseAdapter {
		@SuppressWarnings("deprecation")
		public void mouseClicked(final MouseEvent e) {
			
			java.util.Date date = new java.util.Date();   			//ϵͳ��ǰʱ��
			String fk = "";											//����
			String days1 = "";										//�ɽ����� 
			int selRow = table.getSelectedRow();					//���ѡ����
			List list = Dao.selectBookTypeFk(
					table.getValueAt(selRow, 2).toString().trim());	//��ѯ����ͼ�鷣�����Ϳɽ����� 
			for(int i=0;i<list.size();i++){
				BookType booktype=(BookType)list.get(i);
				fk=booktype.getFk();								//��÷�����
				days1=booktype.getDays();							//��ÿɽ�����
			}
			borrowDate.setText(
					table.getValueAt(selRow, 5).toString().trim());	
			int days2;												//ʵ������
			int days3;												//��������
			borrowdays.setText(days1+"");
//			days2 = date.getDate() 
//					- java.sql.Timestamp.valueOf(
//							table.getValueAt(selRow, 5).toString().trim()).getDate();
			/*ʱ�����*/
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
				fkmoney.setText(zfk+"Ԫ");
			}
			else{
				ccdays.setText("û�г����涨����");
				fkmoney.setText("0");
			}
			bookISBNs=table.getValueAt(selRow, 1).toString().trim();
		}
	}
	/**
	 * ��������¼�
	 */
	class BookBackActionListener implements ActionListener{
		private final DefaultTableModel model;

		BookBackActionListener(DefaultTableModel model) {
			this.model = model;
		}
		public void actionPerformed(ActionEvent e) {
			if(readerISBNs==null){
				JOptionPane.showMessageDialog(null, "��������߱�ţ�");
				return;
			}
			System.out.println(bookISBNs==null);

			if(table.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�黹��ͼ�飡");
				return;	
			}

		
			int i=Dao.UpdateBookBack(bookISBNs, readerISBNs,id);
			System.out.print(i);
			 if(i==1){	
				int selectedRow = table.getSelectedRow();
				model.removeRow(selectedRow);
				JOptionPane.showMessageDialog(null, "���������ɣ�");		
			}
		}
	}
	/**
	 * �رռ����¼�
	 */
	class CloseActionListener implements ActionListener {			// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
