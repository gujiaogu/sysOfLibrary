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

	/**ͼ������ı���*/
	private JTextField bookType;
	/**�����۸��ı���*/
	private JTextField orderPrice;
	/**�ۿ��ı���*/
	private JTextField zk;
	/**���*/
	private JTable table;
	/**��ť��*/
	private ButtonGroup buttonGroup = new ButtonGroup();
	/**�۸��ı���*/
	private JTextField price;
	/**����Ա�ı���*/
	private JTextField operator;
	/**���������ı���*/
	private JTextField orderNumber;
	/**�鼮����ı���*/
	private JTextField ISBN;
	/**�������ڸ�ʽ�ı���*/
	private JFormattedTextField orderDate;
	/**����Ա��Ϣ*/
	private Operater user = BookLoginIFrame.getUser(); 
	/**δ����*/
	JRadioButton radioButton2;
	/**���ڸ�ʽ��*/
	SimpleDateFormat myfmt =new SimpleDateFormat("yyyy-MM-dd");
	/**������*/
	JRadioButton radioButton1;
	
	
	private String[] columnNames={ "ͼ����", "��������", "��������","����Ա","�Ƿ�����","�ۿ�","ͼ�����","ͼ������","����","����","������","��������","ͼ��۸�"};
	private Map map=BookTypeIdAndItemMap.getMap();
	
	/**
	 * @description		�õ�ȫ��������Ϣ
	 * @author			����	
	 * @createDate		2011-3-8
	 * @param			List �����ݿ����ѯ�õ��Ľ����
	 * @return			Object[][]��������������		
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
			if(order.getCheckAndAccept().equals("1"))//1����û������
				CheckAndAccepts="��";
			else
				CheckAndAccepts="��";
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
		setClosable(true);				//���ô���ɹر�
		setIconifiable(true);			//���ô������С��
		setAutoscrolls(true);			//�����Զ�����
		setTitle("ͼ������");			//���ñ���
		setBounds(100, 100, 700, 420);	//���ô����С
		

		final JPanel panel = new JPanel();	//���������
		getContentPane().add(panel);		//����������ӵ�������

		final JScrollPane scrollPane = 
			new JScrollPane();				//�����������		
		scrollPane.setPreferredSize(
				new Dimension(680, 180));	//���ô�С
		panel.add(scrollPane);				//�����������ӵ��������

		final DefaultTableModel model = 
			new DefaultTableModel();		//���������ͼģ��
		Object[][] results = 
			getFileStates(
					Dao.selectBookOrder());	//�õ�����е�����
		model.setDataVector(
				results,columnNames);		//�������ͼģ���������
		table = new JTable();				//�������
		table.setModel(model);				//���ñ�����ͼģ��
		table.setAutoResizeMode(
				JTable.AUTO_RESIZE_OFF);	//�رձ���Զ���С
		scrollPane.setViewportView(table);	//�������ӵ����������
		table.addMouseListener(
				new TableListener());		//�����ע���¼�������

		final JPanel panel_1_1 =
			new JPanel();					//����������Ϣ���
		final GridLayout gridLayout =
			new GridLayout(0, 4);			//�������ֹ�����
		gridLayout.setVgap(5);				//�����Ĵ�ֱ���
		panel_1_1.setLayout(gridLayout);	//���ò��ֹ�����
		panel_1_1.setPreferredSize(
				new Dimension(450, 150));	//���ô�С
		panel.add(panel_1_1);				//��������Ϣ�����ӵ��������

		final JLabel label_1 = new JLabel();//�����������ڱ�ǩ
		label_1.setText("�������ڣ�");		//�����ı�
		panel_1_1.add(label_1);				//����ǩ��ӵ������

		orderDate = 
			new JFormattedTextField(
					myfmt.getDateInstance());	//���ø�ʽ�ı����ʽ
		orderDate.setValue(
				new java.util.Date());			//���������ı���ֵ
		orderDate.addKeyListener(
				new DateListener());			//ע�������

		panel_1_1.add(orderDate);				//���ı�����ӵ������

		final JLabel label_3 = new JLabel();	//������ǩ
		label_3.setText("�鼮��ţ�");			//�����ı�
		panel_1_1.add(label_3);					//��ӵ������

		ISBN = new JTextField();				//�����ı���
		panel_1_1.add(ISBN);					//��ӵ������

		final JLabel label_4 = new JLabel();	//��������������ǩ
		label_4.setText("����������");			//�����ı�
		panel_1_1.add(label_4);					//��ӵ������

		orderNumber = new JTextField();			//�����ı���
		panel_1_1.add(orderNumber);				//��ӵ������

		final JLabel label_5 = new JLabel();	//������ǩ
		label_5.setText("����Ա��");				//�����ı�
		panel_1_1.add(label_5);					//��ӵ������
		operator = 
			new JTextField(user.getName());		//�����ı������ó�ʼֵ
		panel_1_1.add(operator);				//��ӵ������

		final JLabel label_6 = new JLabel();	//������ǩ
		label_6.setText("ͼ�����");			//�����ı�
		panel_1_1.add(label_6);					//��ӵ������

		bookType = new JTextField();			//�����ı���
		panel_1_1.add(bookType);				//��ӵ������


		final JLabel label_7 = new JLabel();	//������ǩ
		label_7.setText("ͼ��ԭ�۸�");			//�����ı�
		panel_1_1.add(label_7);					//��ӵ������

		price = new JTextField();				//�����ı���
		panel_1_1.add(price);					//��ӵ������

		final JLabel label_9 = new JLabel();	//������ǩ
		label_9.setText("�Ƿ����գ�");			//�����ı�
		panel_1_1.add(label_9);					//��ӵ������

		final JPanel panel_1 = new JPanel();	//�Ű�ť������
		panel_1_1.add(panel_1);					//��ӵ������

		radioButton1 = new JRadioButton();		//������ѡ��ť
		radioButton1.setSelected(true);			//����Ĭ��Ϊѡ��
		panel_1.add(radioButton1);				//��ӵ������
		buttonGroup.add(radioButton1);			//��ӵ���ť����
		radioButton1.setText("��");				//�����ı�

		radioButton2= new JRadioButton();		//������ѡ��ť
		panel_1.add(radioButton2);				//��ӵ������
		buttonGroup.add(radioButton2);			//��ӵ���ť����
		radioButton2.setText("��");				//�����ı�

		final JLabel label = new JLabel();		//������ǩ
		label.setText("�ۿۣ�");					//�����ı�
		panel_1_1.add(label);					//��ӵ������

		zk = new JTextField();					//�����ı���
		panel_1_1.add(zk);						//��ӵ������

		final JLabel label_2 = new JLabel();	//������ǩ
		label_2.setText("�����۸�");			//�����ı�
		panel_1_1.add(label_2);					//��ӵ������

		orderPrice = new JTextField();			//�����ı���
		panel_1_1.add(orderPrice);				//��ӵ������
		setVisible(true);						//���ô���ɼ�

		final JPanel panel_2 = new JPanel();	//�����·����
		getContentPane().add(
				panel_2,
				BorderLayout.SOUTH);			//��ӵ������·�

		final JButton buttonCheck = 
			new JButton();						//������ť 
		panel_2.add(buttonCheck);				//��ӵ������
		buttonCheck.setText("����");				//�����ı�
		buttonCheck.addActionListener(
				new CheckActionListener(model));//ע�������

		final JButton buttonExit =
			new JButton();						//�����˳���ť
		panel_2.add(buttonExit);				//��ӵ������
		buttonExit.addActionListener(
				new CloseActionListener());		//ע�������
		buttonExit.setText("�˳�");				//�����ı�
	}
	/**
	 * �����ı�������¼����������ڸ�ʽ 
	 */
	class DateListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			if(orderDate.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "ʱ���ʽ��ʹ��\"2007-05-10\"��ʽ");
			}
		}
	}
	/**
	 * ���ѡ�м����¼� 
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
			if(table.getValueAt(selRow, 4).toString().trim().equals("��"))//1����û������
				radioButton2.setSelected(true);
			else
				radioButton1.setSelected(true);
			zk.setText(table.getValueAt(selRow, 5).toString().trim());
			orderPrice.setText(Double.valueOf(table.getValueAt(selRow, 12).toString().trim())*Double.valueOf(table.getValueAt(selRow, 5).toString().trim())+"");
			
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
	/**
	 * �����ա���ť�¼�
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
					JOptionPane.showMessageDialog(null, "���ճɹ���");
					Object[][] results=getFileStates(Dao.selectBookOrder());
					model.setDataVector(results,columnNames);
					table.setModel(model);
					radioButton1.setSelected(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "��ѡ���ͼ���Ѿ����й����գ���ѡ������ͼ���������");
			}
			
		}
	}
}
