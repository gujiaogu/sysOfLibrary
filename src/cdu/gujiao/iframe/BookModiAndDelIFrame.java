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
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		ͼ����Ϣ�޸Ĵ���
 *		
 * @author		����
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
	 * @description		ȡ���ݿ���ͼ�������Ϣ��������
	 * @author			����	
	 * @createDate		2011-2-21
	 * @param			list ��װ����ͼ�����Ϣ
	 * @return			����ͼ����Ϣ������		
	 *	
	 * @see						
	 */
	private Object[][] getFileStates(List list){
		
		String[] columnNames = { "ͼ����", "ͼ�����", "ͼ������", "����", "����", "������",
				"��������", "�۸�" };
		Object[][]results =
			new Object[list.size()][columnNames.length];//��ά���������������м�¼
		
		for(int i=0;i<list.size();i++){					//����list
			BookInfo bookinfo=(BookInfo)list.get(i);	//ȡ��ͼ���¼
			results[i][0]=bookinfo.getISBN();			//����ͼ����
			String booktypename =
					String.valueOf(BookTypeIdAndItemMap.getMap().get(bookinfo.getTypeid()));//���ͼ���������
			results[i][1]=booktypename;					//����ͼ���������
			results[i][2]=bookinfo.getBookname();		//����ͼ������
			results[i][3]=bookinfo.getWriter();			//����ͼ������
			results[i][4]=bookinfo.getTranslator();		//����ͼ������
			results[i][5]=bookinfo.getPublisher();		//���ó�����
			results[i][6]=bookinfo.getDate();			//���ó�������
			results[i][7]=bookinfo.getPrice();			//�����鼮�۸�
		}
		return results;									//��Χ��ά������鼮��¼
	         		
	}
	public BookModiAndDelIFrame() {
		super();
		final BorderLayout borderLayout 
				= new BorderLayout();					//�߿򲼾ֹ�����
		getContentPane().setLayout(borderLayout);		//ʹ�ñ߿򲼾ֹ�����
		setIconifiable(true);							// ���ô������С��
		setClosable(true);								// ���ô���ɹر�
		setTitle("ͼ����Ϣ�޸�");						// ���ô������
		setBounds(100, 100, 593, 406);					// ���ô���λ�úʹ�С

		final JPanel mainPanel = new JPanel();				//�����
		final BorderLayout borderLayout_1 =
			new BorderLayout();								//�߿򲼾ֹ�����
		borderLayout_1.setVgap(5);							//�������֮�䴹ֱ����
		mainPanel.setLayout(borderLayout_1);				//ʹ�ñ߿򲼾ֹ�����
		mainPanel.setBorder(new EmptyBorder(5, 10, 5, 10));	//���ñ߿�
		getContentPane().add(mainPanel);					//���������ӵ�������

		final JScrollPane scrollPane = new JScrollPane();	//�������
		mainPanel.add(scrollPane);							//�����������ӵ��������
		
		/*��table��ӵ�scrollPane����*/
		Object[][] results =
			getFileStates(Dao.selectBookInfo());			//����鼮��¼
		columnNames = new String[]{"ͼ����", "ͼ�����", "ͼ������",
				"����", "����", "������", "��������","�۸�"};	//�����б�
		table = new JTable(results,columnNames);			//�������
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	//����Ӧ����
		table.addMouseListener(new TableListener());		//��굥������е����ݲ����¼�,������е����ݷ����ı�����
		scrollPane.setViewportView(table);					//�������ӵ����������

		/*��ͼ����Ϣ�޸������ӵ��������ϲ�*/
		final JPanel bookPanel = new JPanel();				//�鼮�޸����
		mainPanel.add(bookPanel, BorderLayout.SOUTH);		//��ӵ������׶�
		final GridLayout gridLayout = new GridLayout(0, 6);	//���񲼾�
		gridLayout.setVgap(5);								//�������֮�䴹ֱ����
		gridLayout.setHgap(5);								//�������֮��ƽ�о���
		bookPanel.setLayout(gridLayout);					//�����鼮�����岼��
		
		/*ͼ���ű�ǩ���ı���*/
		final JLabel ISBNLabel = new JLabel();				//����ͼ���ű�ǩ
		ISBNLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		ISBNLabel.setText("��       �ţ�");						//���ñ�ǩ�ı�
		bookPanel.add(ISBNLabel);							//��ӵ��鼮�޸����
		
		ISBN = new JTextField();							//��������ı���
		ISBN.setDocument(new MyDocument(13)); 				//����ı����������ֵΪ13
		bookPanel.add(ISBN);								//��ӵ��鼮�޸����
		
		/*ͼ������ǩ��������*/
		final JLabel bookTypeLabel = new JLabel();			//�����鼮����ǩ
		bookTypeLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		bookTypeLabel.setText("��       ��");					//���ñ�ǩ�ı�
		bookPanel.add(bookTypeLabel);						//��ӵ��鼮�޸����
		
		bookType = new JComboBox();							//�����鼮���������
		bookTypeModel =
				(DefaultComboBoxModel)bookType.getModel();	//�������ģ��
		List list=Dao.selectBookCategory();					//�����ݿ���ȡ��ͼ�����
		for(int i=0;i<list.size();i++){						//����ͼ�����
			BookType booktype=(BookType)list.get(i);		//���ͼ�����
			item=new Item();								//ʵ����ͼ�����ѡ��
			item.setId((String)booktype.getId());			//����ͼ�������
			item.setName((String)booktype.getTypeName());	//����ͼ���������
			bookTypeModel.addElement(item);					//���ͼ�����Ԫ��
			map.put(item.getId(), item);					//�Լ�ֵ�Ե���ʽ����
		}
		bookPanel.add(bookType);							//��ӵ��鼮�޸����
		
		/*������ǩ���ı���*/
		final JLabel bookNameLabel = new JLabel();			//����������ǩ
		bookNameLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		bookNameLabel.setText("��    ����");					//���ñ�ǩ�ı�
		bookPanel.add(bookNameLabel);						//��ӵ��鼮�޸����
		
		bookName = new JTextField();						//�����ı���
		bookPanel.add(bookName);							//��ӵ��鼮�޸����
		
		/*�������߱�ǩ���ı���*/
		final JLabel writerLabel = new JLabel();			//�������߱�ǩ
		writerLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		writerLabel.setText("��       �ߣ�");					//���ñ�ǩ�ı�
		bookPanel.add(writerLabel);							//��ӵ��鼮�޸����
		
		writer = new JTextField();							//�����ı���
		bookPanel.add(writer);
		
		/*�����������ǩ���ı���*/
		final JLabel publisherLabel = new JLabel();			//�����������ǩ
		publisherLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		publisherLabel.setText("��  ��  �磺");				//���ñ�ǩ�ı�
		bookPanel.add(publisherLabel);						//��ӵ��鼮�޸����
		
		publisher = new JTextField();						//�������ı���
		bookPanel.add(publisher);							//��ӵ��鼮�޸����
		
		/*�������߱�ǩ���ı���*/
		final JLabel translatorLabel = new JLabel();		//�������߱�ǩ
		translatorLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		translatorLabel.setText("��    �ߣ�");					//���ñ�ǩ�ı�
		bookPanel.add(translatorLabel);						//��ӵ��鼮�޸����
		
		translator = new JTextField();						//�����ı���
		bookPanel.add(translator);							//��ӵ��鼮�޸����
		
		/*�������ڱ�ǩ���ı���*/
		final JLabel pubDateLabel = new JLabel();			//�����������ڱ�ǩ
		pubDateLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		pubDateLabel.setText("�� �� �� �ڣ�");					//���ñ�ǩ�ı�
		bookPanel.add(pubDateLabel);						//��ӵ��鼮�޸����
		
		SimpleDateFormat myfmt = 
			new SimpleDateFormat("yyyy-MM-dd");				//�������ڸ�ʽ
		pubDate =
			new JFormattedTextField(myfmt.getDateInstance());//�������������
		pubDate.setValue(new java.util.Date());				//��������Ϊ��ǰϵͳʱ��
		bookPanel.add(pubDate);								//��ӵ��鼮�޸����
		
		/*�������۱�ǩ*/
		final JLabel priceLabel = new JLabel();				//�������۱�ǩ
		priceLabel.setHorizontalAlignment(
				SwingConstants.CENTER);						//ˮƽ����
		priceLabel.setText("��      �ۣ�");						//���ñ�ǩ�ı�
		bookPanel.add(priceLabel);							//��ӵ��鼮�޸����
		price= new JFormattedTextField();					//�۸��ı���
		price.addKeyListener(new NumberListener());			//ע�������
		bookPanel.add(price);								//��ӵ��鼮�޸����
		
		/*�ײ����*/
		final JPanel bottomPanel = new JPanel();			//�����ײ����
		bottomPanel.setBorder(new LineBorder(
				SystemColor.activeCaptionBorder, 1, false));//���ñ߿�
		getContentPane().add(bottomPanel,
				BorderLayout.SOUTH);						//��ӵ�����׶�
		final FlowLayout flowLayout = new FlowLayout();		//�����ֹ�����
		flowLayout.setVgap(2);								//�������֮�䴹ֱ����
		flowLayout.setHgap(30);								//�������֮��ƽ�о���
		flowLayout.setAlignment(FlowLayout.RIGHT);			//�������Ҷ���
		bottomPanel.setLayout(flowLayout);					//���õײ���岼��
		
		/*�޸İ�ť*/
		final JButton updateButton = new JButton();			//�����޸İ�ť
		updateButton.addActionListener(
				new UpdateBookActionListener ());			//ע�������
		updateButton.setText("�޸�");						//���ð�ť�ı�
		bottomPanel.add(updateButton);						//��ӵ��ײ����
		
		/*�رհ�ť*/
		final JButton closeButton= new JButton();			//�����رհ�ť
		closeButton.addActionListener(new ActionListener() {//ע�������
			public void actionPerformed(final ActionEvent e) {
				doDefaultCloseAction();						//�رմ���
			}
		});
		closeButton.setText("�ر�");							//���ð�ť�ı�
		bottomPanel.add(closeButton);						//��ӵ��ײ����
		
		setVisible(true);									//��ʾ����ɼ�
	}
	class TableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			String ISBNs, typeids, bookNames,writers,
					translators,publishers,dates,prices;					//��������
			int selRow = table.getSelectedRow();							//�����ѡ�к�
			ISBNs = table.getValueAt(selRow, 0).toString().trim();			//������
			typeids = table.getValueAt(selRow, 1).toString().trim();		//��������
			bookNames = table.getValueAt(selRow, 2).toString().trim();		//�������
			writers = table.getValueAt(selRow, 3).toString().trim();		//�������
			translators = table.getValueAt(selRow, 4).toString().trim();	//�������
			publishers = table.getValueAt(selRow, 5).toString().trim();		//��ó�����
			dates = table.getValueAt(selRow, 6).toString().trim();			//��ó�������
			prices = table.getValueAt(selRow, 7).toString().trim();			//��ü۸�
			
			/*�����ı���ֵ*/
			ISBN.setText(ISBNs);					//��������ı���Ϊ��õ������Ϣ
			bookTypeModel.setSelectedItem(typeids);	//���������������ѡ��
			bookName.setText(bookNames);			//���������ı���Ϊ��õ�������Ϣ
			writer.setText(writers);				//���������ı���Ϊ��õ�������Ϣ
			translator.setText(translators);		//���������ı���Ϊ��õ�������Ϣ
			publisher.setText(publishers);			//���ó������ı���Ϊ��õĳ�������Ϣ
			pubDate.setText(dates);					//���ó��������ı���Ϊ��õĳ���������Ϣ
			price.setText(prices);					//���ü۸��ı���Ϊ��õļ۸���Ϣ
		}
	}
	class UpdateBookActionListener  implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			if(ISBN.getText().length()==0){			//�ж��Ƿ��������鼮���
				JOptionPane.showMessageDialog(null, "����ı��򲻿���Ϊ��");
				return;
			}
			if(ISBN.getText().length()!=13){		//�ж��鼮��ŵĳ����Ƿ�Ϊ13
				JOptionPane.showMessageDialog(null, "����ı�������λ��Ϊ13λ");
				return;
			}
			if(bookName.getText().length()==0){		//�ж��Ƿ��������鼮����
				JOptionPane.showMessageDialog(null, "ͼ�������ı��򲻿���Ϊ��");
				return;
			}
			if(writer.getText().length()==0){		//�ж��Ƿ�����������
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			if(publisher.getText().length()==0){	//�ж��Ƿ������˳�����
				JOptionPane.showMessageDialog(null, "�������ı��򲻿���Ϊ��");
				return;
			}
			if(pubDate.getText().length()==0){		//�ж��Ƿ������˳�������
				JOptionPane.showMessageDialog(null, "���������ı��򲻿���Ϊ��");
				return;
			}
			if(price.getText().length()==0){		//�ж��Ƿ��������鼮�۸�
				JOptionPane.showMessageDialog(null, "�����ı��򲻿���Ϊ��");
				return;
			}
			String ISBNs=ISBN.getText().trim();				//����鼮���
			Object selectedItem = 
				bookTypeModel.getSelectedItem();			//�鼮���ѡ��
			if (selectedItem == null)  return;
			Item item = (Item)selectedItem;					//�����ѡ���
			String bookTypes=item.getId();					//��������
			String translators=translator.getText().trim();	//���������Ϣ
			String bookNames=bookName.getText().trim();		//����鼮����
			String writers=writer.getText().trim();			//���������Ϣ
			String publishers=publisher.getText().trim();	//��ó�������Ϣ
			String pubDates=pubDate.getText().trim();		//��ó�������
			String prices=price.getText().trim();			//��ü۸���Ϣ
			int i=Dao.Updatebook(ISBNs, bookTypes, bookNames, writers, translators,
					publishers, Date.valueOf(pubDates), Double.parseDouble(prices));
			if(i==1){										//������ظ��¼�¼��Ϊ1����ʾ�޸ĳɹ�
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				Object[][] results = 
					getFileStates(Dao.selectBookInfo());	//���»���鼮��Ϣ
				DefaultTableModel model = 
					new DefaultTableModel();				//��ñ��ģ��
				table.setModel(model);						//���ñ��ģ��
				model.setDataVector(results,columnNames);	//����ģ�����ݺ�����
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
