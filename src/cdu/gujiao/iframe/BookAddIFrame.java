package cdu.gujiao.iframe;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookType;
import cdu.gujiao.model.Item;
import cdu.gujiao.util.MyDocument;

/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		ͼ����Ϣ��Ӵ���
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookAddIFrame extends JInternalFrame {
	/**
	 * ������
	 */
	private JComboBox publisher;
	/**
	 * �۸�
	 */
	private JTextField   price;
	/**
	 * ��������
	 */
	private JFormattedTextField pubDate;
	/**
	 * ����
	 */
	private JTextField translator;
	/**
	 * ����
	 */
	private JTextField writer;
	/**
	 * ͼ����
	 */
	private JTextField ISBN;
	/**
	 * ͼ������
	 */
	private JTextField bookName;
	/**
	 * ͼ������
	 */
	private JComboBox bookType;
	/**
	 * ��Ӱ�ť
	 */
	private JButton buttonadd;
	/**
	 * �رհ�ť
	 */
	private JButton buttonclose;
	/**
	 * ͼ����������б����ͼģ��
	 */
	DefaultComboBoxModel bookTypeModel;
	
	public BookAddIFrame() {
		super();
		/*�����������*/
		final BorderLayout borderLayout = new BorderLayout();	//�����߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);				//���ò���
		setIconifiable(true);									//���ô������С��
		setClosable(true);										//���ô���ɹر�
		setTitle("ͼ����Ϣ���");								//���ô������
		setBounds(100, 100, 396, 180);							//���ô���λ�úʹ�С

		/*����������壬���������������*/
		final JPanel mainPanel = new JPanel();				//�����������
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));	//���ñ߿�
		final GridLayout gridLayout = new GridLayout(0, 4);	//������񲼾ֹ�����
		gridLayout.setVgap(5);								//�������֮�䴹ֱ����
		gridLayout.setHgap(5);								//�������֮��ƽ�о���
		mainPanel.setLayout(gridLayout);					//���ò���
		getContentPane().add(mainPanel);					//�����������뵽����

		/*ͼ���ű�ǩ���ı���*/
		final JLabel ISBNLabel = new JLabel();	//����ͼ���ű�ǩ
		ISBNLabel.setText("ͼ���ţ�");			//���ñ�ǩ�ı�
		mainPanel.add(ISBNLabel);				//��ӵ��������
		
		ISBN = new JTextField("������13λ���",13);		//��������ı���
		ISBN.setDocument(new MyDocument(13)); 			//��������ı����������ֵΪ13
		ISBN.setColumns(13);							//�����ı��򳤶�
		ISBN.addKeyListener(new ISBNkeyListener());		//ע�������
		ISBN.addFocusListener(new ISBNFocusListener());	//ע�������
		mainPanel.add(ISBN);

		/*ͼ������ǩ���������б��*/
		final JLabel bookTypeLabel = new JLabel();					//�����鼮����ǩ
		bookTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);//����ƽ�ж��뷽ʽ
		bookTypeLabel.setText("���");								//���ñ�ǩ�ı�
		mainPanel.add(bookTypeLabel);								//��ӵ��������
		
		bookType = new JComboBox();									//�����鼮���������
		bookTypeModel= (DefaultComboBoxModel)bookType.getModel();	//�������ģ��
		List list=Dao.selectBookCategory();   						//�����ݿ���ȡ��ͼ�����
		for(int i=0;i<list.size();i++){								//����ͼ�����
			BookType booktype=(BookType)list.get(i);				//���ͼ�����
			Item item=new Item();									//ʵ����ͼ�����ѡ��
			item.setId((String)booktype.getId());					//����ͼ�������
			item.setName((String)booktype.getTypeName());			//����ͼ���������
			bookTypeModel.addElement(item);							//���ͼ�����Ԫ��
		}
		mainPanel.add(bookType);									//��ӵ��������

		/*ͼ�����Ʊ�ǩ�����ı���*/
		final JLabel bookNameLabel = new JLabel();	//����������ǩ
		bookNameLabel.setText("������");				//���ñ�ǩ�ı�
		mainPanel.add(bookNameLabel);				//��ӵ��������

		bookName = new JTextField();				//���������ı���
		mainPanel.add(bookName);					//��ӵ��������

		/*���߱�ǩ�����ı���*/
		final JLabel writerLabel = new JLabel();					//�������߱�ǩ
		writerLabel.setHorizontalAlignment(SwingConstants.CENTER);	//����ƽ�ж��뷽ʽ
		writerLabel.setText("���ߣ�");								//���ñ�ǩ�ı�
		mainPanel.add(writerLabel);									//��ӵ��������

		writer = new JTextField();				//���������ı���
		writer.setDocument(new MyDocument(10));	//���������ı����������ֵΪ10
		mainPanel.add(writer);					//��ӵ��������

		/*�������ǩ���������б�*/
		final JLabel publisherLabel = new JLabel();	//�����������ǩ
		publisherLabel.setText("�����磺");			//���ñ�ǩ�ı�
		mainPanel.add(publisherLabel);				//��ӵ��������

		publisher = new JComboBox();				//����������������
		String[] array=new String[]{"����������","��������",
					"�Ϻ�������","�ɶ���ѧ������"};	//�������б�
		publisher.setModel(
				new DefaultComboBoxModel(array));	//����������ģ��
		mainPanel.add(publisher);					//��ӵ��������

		/*���߱�ǩ�����ı���*/
		final JLabel translatorLabel = new JLabel();//�������߱�ǩ
		translatorLabel.setHorizontalAlignment(
				SwingConstants.CENTER);				//����ƽ�ж��뷽ʽ
		translatorLabel.setText("���ߣ�");			//���ñ�ǩ�ı�
		mainPanel.add(translatorLabel);				//��ӵ��������

		translator = new JTextField();				//���������ı���
		translator.setDocument(new MyDocument(10));	//���������ı����������ֵΪ10
		mainPanel.add(translator);					//��ӵ��������

		/*���ڱ�ǩ�����ı���*/
		final JLabel pubDateLabel = new JLabel();	//�����������ڱ�ǩ
		pubDateLabel.setText("�������ڣ�");			//���ñ�ǩ�ı�
		mainPanel.add(pubDateLabel);				//��ӵ��������

		SimpleDateFormat myfmt = 
			new SimpleDateFormat("yyyy-MM-dd");		//�������ڸ�ʽ
		pubDate= new JFormattedTextField(
				myfmt.getDateInstance());			//�������������
		pubDate.setValue(new java.util.Date());		//��������Ϊ��ǰϵͳʱ��
		mainPanel.add(pubDate);						//��ӵ��������
		
		/*�۸��ǩ�����ı���*/
		final JLabel priceLabel = new JLabel();		//�����۸��ǩ
		priceLabel.setHorizontalAlignment(
				SwingConstants.CENTER);				//����ƽ�ж��뷽ʽ
		priceLabel.setText("���ۣ�");				//���ñ�ǩ�ı�
		mainPanel.add(priceLabel);					//��ӵ��������
		
		price=   new   JTextField();				//�����۸��ı���
		price.setDocument(new MyDocument(5));		//���ü۸��ı����������ֵΪ5
		price.addKeyListener(new NumberListener());	//ע�������
		mainPanel.add(price);						//��ӵ��������

		/*�����ײ���岢�������һЩ��������*/
		final JPanel bottomPanel = new JPanel();				//�����ײ����
		bottomPanel.setBorder(new LineBorder(SystemColor.
							activeCaptionBorder, 1, false));	//���ñ߿�
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);	//��ӵ�������
		final FlowLayout flowLayout = new FlowLayout();			//�����ֹ�����
		flowLayout.setVgap(2);									//�������֮�䴹ֱ����
		flowLayout.setHgap(30);									//�������֮��ƽ�о���
		flowLayout.setAlignment(FlowLayout.RIGHT);				//���ö��뷽ʽ
		bottomPanel.setLayout(flowLayout);						//���õײ���岼��
		
		/*��Ӱ�ť*/
		buttonadd= new JButton();				//������Ӱ�ť
		buttonadd.addActionListener(
				new AddBookActionListener());	//ע�������
		buttonadd.setText("���");				//���ð�ť�ı�
		bottomPanel.add(buttonadd);				//��ӵ��ײ����
		
		/*�رհ�ť*/
		buttonclose = new JButton();		//�����رհ�ť
		buttonclose.addActionListener(
				new CloseActionListener());	//ע�������
		buttonclose.setText("�ر�");			//���ð�ť�ı�
		bottomPanel.add(buttonclose);		//��ӵ��ײ����
		
		setVisible(true);					// ��ʾ����ɼ�
	}
	
	/**
	 * ͼ�����ı���ʧȥ�����¼�
	 */
	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e){
			if(!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "�������ظ���");
				return;
			}
		}
	}
	/**
	 * ͼ�����ı����ı���س��¼�
	 */
	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 10){
				buttonadd.doClick();
			}
		
		}
	}
	/**
	 * �رհ�ť�¼�
	 */
	class CloseActionListener implements ActionListener {	// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
	/**
	 * ��Ӱ�ť�¼�
	 */
	class AddBookActionListener implements ActionListener {	// ��Ӱ�ť�ĵ����¼�������
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length() == 0){				//�ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if(ISBN.getText().length() != 13){				//�ж��鼮��ŵĳ����Ƿ�Ϊ13
				JOptionPane.showMessageDialog(null, "����ı�������λ��Ϊ13λ");
				return;
			}
			if(bookName.getText().length() == 0){			//�ж��Ƿ��������鼮����
				JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
				return;
			}
			if(writer.getText().length() == 0){				//�ж��Ƿ�����������
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(pubDate.getText().length() == 0){			//�ж��Ƿ������˳�������
				JOptionPane.showMessageDialog(null, "���������ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length() == 0){				//�ж��Ƿ��������鼮�۸�
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			String ISBNs = ISBN.getText().trim();			//����鼮���
			Object selectedItem 
			 		= bookType.getSelectedItem();			//�鼮���ѡ��
			if (selectedItem == null) return;
			Item item = (Item) selectedItem;				//�����ѡ���
			String bookTypes = item.getId();				//��������
			String translators = translator.getText().trim();//���������Ϣ
			String bookNames = bookName.getText().trim();	//����鼮����
			String writers = writer.getText().trim();		//���������Ϣ
			String publishers = 
				(String)publisher.getSelectedItem();		//��ó�������Ϣ
			String pubDates=pubDate.getText().trim();		//��ó�������
			String prices=price.getText().trim();			//��ü۸���Ϣ
			
			int i = Dao.Insertbook(ISBNs,bookTypes, bookNames, writers, translators, 
					publishers, java.sql.Date.valueOf(pubDates),Double.parseDouble(prices));
			if(i == 1){										//������ظ��¼�¼��Ϊ1����ʾ��ӳɹ�
				JOptionPane.showMessageDialog(null, "��ӳɹ�");
				doDefaultCloseAction();
			}
		}
	}
	/**
	 * ����ֻ���������֣�С�����Back��
	 */
	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr="0123456789."+(char)8; 	//8ΪBack��
			if(numStr.indexOf(e.getKeyChar())<0){
				e.consume();
			}
		}
	}

}
