package cdu.gujiao.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.BookInfo;
import cdu.gujiao.model.Item;
import cdu.gujiao.util.BookTypeIdAndItemMap;


import java.awt.*;

public class BookSearchIFrame extends JInternalFrame {

	private JTextField textField_1;

	private JComboBox comboBox_1;

	private JTable table_1, table_2;

	private JComboBox choice;

	private JTextField textField_2;

	private JScrollPane scrollPane, scrollPane_1;
	private Map m=BookTypeIdAndItemMap.getMap();

	/**
	 * Create the frame
	 */
	String booksearch[] = { "���", "����", "����", "����", "����","������",  "��������", "����" };

	/**
	 * @description		����ѯ�õĽ����ת��Ϊ����
	 * @author			����	
	 * @createDate		2011-3-2
	 * @param			List list�����
	 * @return			Object[][]�������		
	 *	
	 * @see						
	 */
	private Object[][] getselect(List list) {
		Object[][] s = new Object[list.size()][8];
		for (int i = 0; i < list.size(); i++) {
			BookInfo book = (BookInfo) list.get(i);
			s[i][0] = book.getISBN();
			String booktypename=String.valueOf(BookTypeIdAndItemMap.getMap().get(book.getTypeid()));
			s[i][1] = booktypename;
			s[i][2] = book.getBookname();
			s[i][3] = book.getWriter();
			s[i][4] = book.getTranslator();
			s[i][5] = book.getPublisher();
			s[i][6] = book.getDate();
			s[i][7] = book.getPrice();
		}
		return s;
	}

	public BookSearchIFrame() {
		super();
		setIconifiable(true);
		setClosable(true);
		setTitle("ͼ���ѯ");
		setBounds(100, 100, 500, 400);
		
		final JTabbedPane tabbedPane = new JTabbedPane();	//����Tab��ǩ
		tabbedPane.setPreferredSize(new Dimension(0, 50));	//����Tab��ǩ�Ĵ�С
		getContentPane().add(tabbedPane);					//��Tab��ǩ��ӵ�������

		final JPanel panel_1 = new JPanel();				//����������ѯ���
		panel_1.setLayout(new BorderLayout());				//����������ѯ���Ĳ��ֹ�����
		tabbedPane.addTab("������ѯ", null, panel_1, null);	//����������ѯҳǩ��Ϊ�����һ�����

		final JPanel panel_1_1 = new JPanel();				//��ѯ��Ŀ���		
		panel_1_1.setBorder(new TitledBorder(null, 
				"��ѡ���ѯ��Ŀ", 
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null, 
				null));										//���ò�ѯ��Ŀ���ı߽�
		panel_1_1.setPreferredSize(new Dimension(0, 50));	//���ò�ѯ��Ŀ����С
		panel_1.add(panel_1_1, BorderLayout.NORTH);			//����ѯ��Ŀ�����ӵ�������ѯ�����
        choice=new JComboBox();                            	//������ѯ��������б��
		String[] array={"ͼ������","ͼ������"};				//�����б���е�����
		for(int i=0;i<array.length;i++){					//�������е���Ŀ��ӵ������б�� ��
			choice.addItem(array[i]);
		}
		panel_1_1.add(choice);								//�������б����ӵ���ѯ��Ŀ�����
		textField_1 = new JTextField();						//������ѯ�����
		textField_1.setColumns(20);							//���ò�ѯ������е�����
		panel_1_1.add(textField_1);							//���������ӵ���ѯ��Ŀ�����
        
		final JPanel panel = new JPanel();					//��ѯ�����ʾ���
		panel.setBorder(new TitledBorder(null, 
				"��ѯ�����ʾ",
				TitledBorder.DEFAULT_JUSTIFICATION, 
				TitledBorder.DEFAULT_POSITION, 
				null,
				null));										//���ø����ı߽�
		panel_1.add(panel);									//���������ӵ��������
		
		scrollPane_1 = new JScrollPane();					//����һ���������
		scrollPane_1.setPreferredSize(
				new Dimension(400, 200));					//���ù������Ĵ�С
		panel.add(scrollPane_1,BorderLayout.CENTER);		//�����������ӵ���ѯ��������

		final JPanel panel_2_1 = new JPanel();				//�·���壬װ����������ť
		panel_2_1.setPreferredSize(new Dimension(0, 50));	//�����·����Ĵ�С
		panel_1.add(panel_2_1, BorderLayout.SOUTH);			//���·������ӵ��������

		final JButton button = new JButton(); 				//������ѯ��ť
		button.setText("��ѯ"); 								//������ʾ������
		panel_2_1.add(button); 								//����ť��ӵ��·������

		button.addActionListener(new ActionListener() { 	//����ѯ��ť����¼�������

			public void actionPerformed(ActionEvent arg0) {
				String name = (String) choice.getSelectedItem();		//�õ������б���б�ѡ�е���
				if (name.equals("ͼ������")) {							//���ѡ�е��ǡ�ͼ�����ơ���ִ�а�ͼ�����ƽ��в�ѯ
					Object[][] results = getselect(Dao				
							.selectbookmohu(textField_1.getText()));	//����ͼ������ѯͼ����Ϣ
					table_2 = new JTable(results, booksearch);			//������񲢽���ѯ���Ľ�����ڱ����

					scrollPane_1.setViewportView(table_2);				//��������õ�scrollPane��
				} else if (name.equals("ͼ������")) {					//����ͼ������߲�ѯͼ����Ϣ

					Object[][] results = getselect(Dao					
						.selectbookmohuwriter(textField_1.getText())); 	//��ѯ
					table_2 = new JTable(results, booksearch);			//������񲢽���ѯ���Ľ�����ڱ����

					scrollPane_1.setViewportView(table_2);				//��������õ�scrollPane��
				}
			}
		});

		final JButton button_1 = new JButton();		//�����˳���ť
		button_1.setText("�˳�");					//���ð�ť��ʾ������
		panel_2_1.add(button_1);					//����ť��ӵ��·������
		button_1.addActionListener(
				new CloseActionListener());			//���˳���ť��Ӽ�����
		
		
		final JPanel panel_2 = new JPanel();		//ͼ��ȫ����Ϣҳǩ���
		tabbedPane.addTab("ȫ��ͼ����Ϣ", 
				null, 
				panel_2, 
				null);						 		//��ӡ�ȫ��ͼ����Ϣ��ҳǩ	
         
		scrollPane = new JScrollPane();				//�����������
		scrollPane.setPreferredSize(
				new Dimension(450, 250));			//���ô�С
		panel_2.add(scrollPane);					//�����������ӵ�ȫ��ͼ����Ϣ�����
		
		Object[][] results = getselect(
				Dao.selectbooksearch());			//��ѯ�õĽ����
		String [] booksearch = { "���", "����", "����", "����", "����","������",  "��������", "����" };
		table_1 = new JTable(results,booksearch);	//�������
		
		scrollPane.setViewportView(table_1);		//�������ӵ��������
		
		setVisible(true);							//���ô���ɼ�
		show();
	}
	
	class CloseActionListener implements ActionListener {		// ��ӹرհ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}
}
