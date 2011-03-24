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
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		���鶩������
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-3-7	 
 * @see 				
 */
public class newBookOrderIFrame extends JInternalFrame {

	/**ͼ������*/
	private JTextField bookName;	
	/**�ۿ�*/
	private JTextField zk;								
	/**�Ƿ����հ�ť��*/
	private ButtonGroup buttonGroup = new ButtonGroup();
	/**�����������б��*/
	private JComboBox cbs;								
	/**ͼ��۸�*/
	private JTextField price;							
	/**ͼ����������б��*/
	private JComboBox bookType;							
	/**����Ա*/
	private JTextField operator;
	/**��������*/
	private JTextField orderNumber;
	/**ͼ����*/
	private JTextField ISBN;
	/**��������*/
	private JFormattedTextField orderDate;
	/**ͼ����������б����ͼģ��*/
	DefaultComboBoxModel bookTypeModel;
	/**�����������б����ͼģ��*/
	DefaultComboBoxModel cbsModel;
	/**ȷ������*/
	JRadioButton radioButton1;
	/**δ����*/
	JRadioButton radioButton2;
	/**ͼ�����ID������ӳ��*/
	Map map;
	/**����ͼģ��*/
	DefaultTableModel model;
	/**��¼�û���Ϣ*/
	private Operater user = BookLoginIFrame.getUser(); 
	/**���ڸ�ʽ��*/
	SimpleDateFormat myfmt =new SimpleDateFormat("yyyy-MM-dd");

	
	public newBookOrderIFrame() {
		super();
		setTitle("���鶩������");			//���ñ���
		setIconifiable(true);				//���ÿ���С��
		setClosable(true);					//���ÿɹر�
		setBounds(100, 100, 500, 320);		//���ô����С

		final JPanel panel = new JPanel();	//�������
		panel.setLayout(new FlowLayout());	//���ò��ֹ�����
		panel.setPreferredSize(
				new Dimension(0, 240));		//��������С
		getContentPane().add(panel);		//�������ӵ�������

		final JPanel panel_4 = new JPanel();//����������Ϣ���
		panel_4.setBorder(new TitledBorder(null,
				"������Ϣ", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null,
				null));						//���ñ߽�
		panel_4.setPreferredSize(
				new Dimension(480, 120));	//���ö�����Ϣ����С
		final GridLayout gridLayout_1 =
			new GridLayout(0, 4);			//�������ֹ�����
		gridLayout_1.setVgap(8);			//������������ֱ���
		panel_4.setLayout(gridLayout_1);	//���ò��ֹ�����
		panel.add(panel_4);					//��������Ϣ�����ӵ��������

		final JLabel label_1 = new JLabel();//�����������ڱ�ǩ��ǩ
		panel_4.add(label_1);				//����ǩ��ӵ�������Ϣ���
		label_1.setText("�������ڣ�");		//�����ı�

		
		orderDate = 
			new JFormattedTextField(
					myfmt.getDateInstance());//������ʽ�ı������ø�ʽ
		panel_4.add(orderDate);
		orderDate.setValue(
				new java.util.Date());		//���õ�ǰ����
		orderDate.setEditable(false);

		final JLabel label_4 = new JLabel();//����������ǩ
		panel_4.add(label_4);				//����ǩ��ӵ������
		label_4.setText("����������");		//�����ı�

		orderNumber = new JTextField();		//���������ı���
		panel_4.add(orderNumber);			//���ı�����ӵ������
		orderNumber.setDocument(
				new MyDocument(4));			//�����ı���ĳ���
		orderNumber.addKeyListener(
				new NumberListener());		//���ı�������¼�������
		final JLabel label_5 = new JLabel();//��������Ա��ǩ
		panel_4.add(label_5);				//��ӵ������
		label_5.setText("����Ա��");			//�����ı�
		operator  =new JTextField(
				user.getName());			//�����ı�������ֵ
		panel_4.add(operator);				//���ı�����ӵ������
		operator.setEditable(false);		//�����ı��򲻿ɱ༭
		
		final JLabel label_9 = new JLabel();//�����Ƿ����ձ�ǩ
		panel_4.add(label_9);				//��ӵ������
		label_9.setText("�Ƿ����գ�");		//�����ı�

		final JPanel panel_3 = new JPanel();//��ť�����
		panel_4.add(panel_3);				//����ť�������ӵ�������Ϣ�����

		radioButton1 = new JRadioButton();	//������ѡ��ť
		buttonGroup.add(radioButton1);		//����ѡ��ť��ӵ���ť������
		panel_3.add(radioButton1);			//�ٽ���ѡ��ť��ӵ���ť�������
		radioButton1.setText("��");			//�����ı�

		radioButton2 = new JRadioButton();	//������ѡ��ť
		radioButton2.setSelected(true);		//����Ĭ�ϱ�ѡ״̬
		buttonGroup.add(radioButton2);		//������ӵ���ť����
		panel_3.add(radioButton2);			//������ӵ������
		radioButton2.setText("��");			//�����ı�

		final JLabel label = new JLabel();	//������ǩ
		panel_4.add(label);					//��ӵ�������Ϣ�����
		label.setText("�ۿۣ�");				//�����ı�

		zk = new JTextField();				//�����ı���
		zk.setDocument(new MyDocument(1));	//���ÿ�����ĳ���
		zk.addKeyListener(
				new NumberListener());		//��������¼�������
		panel_4.add(zk);					//��ӵ�������Ϣ�����


		final JPanel panel_1 = new JPanel();//����ͼ����Ϣ���
		panel_1.setBorder(new TitledBorder(null, 
				"ͼ����Ϣ", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null, 
				null));						//�������߽�
		final GridLayout gridLayout =
			new GridLayout(0, 4);			//�������ֹ�����
		gridLayout.setVgap(5);
		panel_1.setLayout(gridLayout);		//���ò��ֹ�����
		panel_1.setPreferredSize(
				new Dimension(0, 100));		//���ô�С
		getContentPane().add(panel_1,
				BorderLayout.NORTH);		//��ͼ����Ϣ�����ӵ�������

		final JLabel label_3 = new JLabel();//������ǩ
		label_3.setText("�鼮��ţ�");		//�����ı�
		panel_1.add(label_3);				//��ӵ�ͼ����Ϣ�����

		ISBN = new JTextField();				//�����ı���
		ISBN.setDocument(
				new MyDocument(13));			//���ÿ�����ĳ���
		ISBN.addFocusListener(
				new ISBNListenerlostFocus());	//��ӽ�������¼�
		panel_1.add(ISBN);						//��ӵ�ͼ����Ϣ�����

		final JLabel label_2 = new JLabel();	//w������ǩ
		label_2.setText("ͼ�����ƣ�");			//�����ı�
		panel_1.add(label_2);					//��ӵ������

		bookName = new JTextField();			//�����ı���
		panel_1.add(bookName);					//��ӵ������

		final JLabel label_6 = new JLabel();	//������ǩ
		label_6.setText("ͼ�����");			//�����ı�
		panel_1.add(label_6);					//��ӵ������

		bookType = new JComboBox();						//���������б��
		bookTypeModel = 
			BookTypeIdAndItemMap.bookTypeModel;			//���ͼ�������ͼģ��
		bookType.setModel(bookTypeModel);				//������ͼģ��
		panel_1.add(bookType);							//�������б����ӵ������

		final JLabel label_8 = new JLabel();			//������ǩ
		label_8.setText("�����磺");						//�����ı�
		panel_1.add(label_8);							//����ǩ��ӵ������
		cbs = new JComboBox();							//���������������б��
		cbsModel=(DefaultComboBoxModel)cbs.getModel();	//�����ͼģ��
		panel_1.add(cbs);								//��ӵ������

		final JLabel label_7 = new JLabel();	//������ǩ
		label_7.setText("ͼ��۸�");			//�����ı�
		panel_1.add(label_7);					//��ӵ������

		price = new JTextField();				//�����ı���
		price.setDocument(new MyDocument(5));	//�����ı�������볤��			
		panel_1.add(price);						//����ǩ��ӵ������


		map = BookTypeIdAndItemMap.getMap();	
		String[] array = 
			new String[]{"������1",
				"������2",
				"������3",
				"������4"};							//�����������б�����
		cbs.setModel(
				new DefaultComboBoxModel(array));	//������ͼģ��

		final JPanel panel_2 = new JPanel();		//�����·����
		panel_2.setPreferredSize(
				new Dimension(0, 50));				//���ô�С
		getContentPane().add(panel_2,
				BorderLayout.SOUTH);				//��ӵ������·�

		final JButton buttonAdd = new JButton();	//������ť
		buttonAdd.setText("���");					//�����ı�
		buttonAdd.addActionListener(
				new ButtonAddLisenter());			//��Ӽ�����
		panel_2.add(buttonAdd);						//����ť��ӵ������

		final JButton ButtonExit = new JButton();	//������ť
		ButtonExit.setText("�˳�");					//�����ı�
		ButtonExit.addActionListener(
				new CloseActionListener());			//��Ӽ�����
		panel_2.add(ButtonExit);					//����ť��ӵ������
		setVisible(true);							//���ô���ɼ�
	}
	/**
	 * ��Ӱ�ť�¼�������
	 */
	class ButtonAddLisenter implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
			if(orderDate.getText().isEmpty()){		//�ж϶������ڿ��Ƿ�Ϊ��
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(ISBN.getText().isEmpty()){			//�ж�ͼ�����ı����Ƿ�Ϊ��
				JOptionPane.showMessageDialog(null, "ͼ�����ı��򲻿�Ϊ��");
				return;
			}
			if(orderNumber.getText().isEmpty()){	//�ж϶��������ı����Ƿ�Ϊ��
				JOptionPane.showMessageDialog(null, "���������ı��򲻿�Ϊ��");
				return;
			}
			if(operator.getText().isEmpty()){		//�жϲ���Ա�ı����Ƿ�Ϊ��
				JOptionPane.showMessageDialog(null, "����Ա�ı��򲻿�Ϊ��");
				return;
			}
					
			if(price.getText().isEmpty()){			//�жϼ۸��ı����Ƿ�Ϊ��
				JOptionPane.showMessageDialog(null, "�۸��ı��򲻿�Ϊ��");
				return;
			}
			if(!Dao.selectBookOrder(ISBN.getText().trim()).isEmpty()){	//�ж��������Ƿ��ظ�
				JOptionPane.showMessageDialog(null, "�������ظ���");
				return;
			}
			
			String checkAndAccept="0";
			if(radioButton2.isSelected()){			//�ж��ĸ���ѡ��ť��ѡ��
				checkAndAccept="1";
			}
			
			Double zks = 
				Double.valueOf(zk.getText())/10;	//�����ۿ�
			
			try{
				int i=Dao.InsertBookOrder(ISBN.getText().trim(), java.sql.Date.valueOf(orderDate.getText().trim()), orderNumber.getText().trim(), operator.getText().trim(), checkAndAccept,zks);
				if(i==1){
					JOptionPane.showMessageDialog(null, "��ӳɹ���");
				}
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	/**
	 *����Ƿ�Ϊ���� 
	 */
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789"+(char)8;
			if(numStr.indexOf(e.getKeyChar())<0){//indexof���������ַ����ڸ��ַ����е�һ�γ���ʱ�������
				e.consume();//��������İ�������ʱ������ʾ
			}
		}
	}
//	class ISBNListener extends KeyAdapter {//ʹ�ûس������д����¼�����
//		public void keyTyped(KeyEvent e) {
//			if (e.getKeyChar() == '\n') { // �ж����ı����Ƿ�����س���
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
	 * �鼮����ı����¼�������
	 */
	class ISBNListenerlostFocus extends FocusAdapter{
		public void focusLost(FocusEvent e){
			String ISBNs = ISBN.getText().trim();
			if(!Dao.selectBookOrder(ISBN.getText().trim()).isEmpty()){//����ͼ���Ƿ��Ѿ���ӵ�������Ϣ��
				JOptionPane.showMessageDialog(null, "�Ѿ�Ϊ�˱��ͼ����Ӷ�����Ϣ������������ͼ���ţ�");
				ISBN.setText("");
				bookName.setText("");
				price.setText("");
				return;
			}
			List list = Dao.selectBookInfo(ISBNs);			//�����鼮��Ų�ѯ�鼮��Ϣ
			if(list.isEmpty()&&!ISBN.getText().isEmpty()){	//�����鼮�Ƿ����
				ISBN.setText("");
				bookName.setText("");
				price.setText("");
				JOptionPane.showMessageDialog(null, "ͼ����Ϣ�����޴���ţ��������ȵ���������ά���н���ͼ����Ϣ��Ӳ���");
			}
			if(list.size() == 1){							//���ֻ��һ����¼
				BookInfo bookinfo = (BookInfo) list.get(0);
				bookName.setText(bookinfo.getBookname());
				bookType.setSelectedItem(map.get(bookinfo.getTypeid()));
				cbs.setSelectedItem(bookinfo.getPublisher());
				price.setText(String.valueOf(bookinfo.getPrice()));
			}else{											//��¼���ظ�
				return;
			}
		}
	}
	/**
	 * ��ӹرհ�ť���¼�������
	 */
	class CloseActionListener implements ActionListener {			 
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
