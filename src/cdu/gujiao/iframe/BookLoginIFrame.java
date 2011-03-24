package cdu.gujiao.iframe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.main.Library;
import cdu.gujiao.model.Operater;
import cdu.gujiao.util.CreateIcon;
import cdu.gujiao.util.MyDocument;


/**
 * @(#)			����Ա��¼����				
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class BookLoginIFrame extends JFrame {
	
	private JPasswordField password;
	private JTextField username;
	private JButton login;
	private JButton reset;
	private static Operater user;

	private class BookResetAction implements ActionListener {		//���ð�ť������
		public void actionPerformed(final ActionEvent e){
			username.setText("");									//�����û��������Ϊ��
			password.setText("");									//�������������Ϊ��
			
		}
	}
	private class BookLoginAction implements ActionListener {		//��½��ť������
		public void actionPerformed(final ActionEvent e) {
			user = Dao.checkOperater(username.getText(), 
						new String(password.getPassword()));		//����DAO����
			if (user.getName() != null) {							//�ж��û����Ƿ�Ϊnull
				try {
					Library frame = new Library();					//����һ��������
					frame.setVisible(true);							//������ɼ�
					BookLoginIFrame.this.setVisible(false);			//���õ�¼����Ϊ����ʾ
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, 
							"��������ȷ���û��������룡");			//������ʾ��
				username.setText("");								//�����û��������Ϊ��
				password.setText("");								//�������������Ϊ��
			}
		}
	}

	/**
	 * @description		�ڹ��캯���н��н������
	 * @author			����	
	 * @createDate		2011-2-15
	 * @param	
	 * @return					
	 *	
	 * @see						
	 */
	public BookLoginIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();		//�������ֹ�����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				//���ùرհ�ť�����¼�
		borderLayout.setVgap(10);									//�������֮�䴹ֱ����			
		getContentPane().setLayout(borderLayout);					//ʹ�ò��ֹ�����
		setTitle("ͼ��ݹ���ϵͳ��¼");								//���ô������
		Toolkit tool = Toolkit.getDefaultToolkit();					//���Ĭ�ϵĹ�����
		Dimension screenSize = tool.getScreenSize();				//�����Ļ�Ĵ�С
		setSize(225, 130);											//���ô����С
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);				//���ô���λ��
		
		final JPanel mainPanel = new JPanel();						//���������
		mainPanel.setLayout(new BorderLayout());					//���ñ߿򲼾�
		mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));			//���ñ߿�Ϊ0
		getContentPane().add(mainPanel);							//�ڴ����м��������
		
		final JPanel centerPanel = new JPanel();					//���һ���������	
		final GridLayout gridLayout = new GridLayout(2, 2);			//�������񲼾ֹ�����
		gridLayout.setHgap(5);										//�������֮��ƽ�о���
		gridLayout.setVgap(20);										//�������֮�䴹ֱ����
		centerPanel.setLayout(gridLayout);							//ʹ�ò��ֹ�����
		mainPanel.add(centerPanel);									//��ӵ������
		
		final JLabel userNamelabel = new JLabel();					//����һ����ǩ
		userNamelabel.setHorizontalAlignment(SwingConstants.CENTER);//���ö��뷽ʽ
		userNamelabel.setPreferredSize(new Dimension(0, 0));		//���������С
		userNamelabel.setMinimumSize(new Dimension(0, 0));			//���������С�Ĵ�С
		centerPanel.add(userNamelabel);								//��ӵ��������
		userNamelabel.setText("��  ��  ����");							//���ñ�ǩ�ı�
		username = new JTextField(20);								//�����ı���
		username.setPreferredSize(new Dimension(0, 0));				//���������С
		centerPanel.add(username);									//��ӵ��������
		
		final JLabel passwordLabel = new JLabel();					//����һ����ǩ
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);//���ö��뷽ʽ
		centerPanel.add(passwordLabel);								//��ӵ��������
		passwordLabel.setText("��      �룺");							//���ñ�ǩ�ı�
		password = new JPasswordField(20);							//���������	
		password.setDocument(new MyDocument(6));					//�������볤��Ϊ6
		password.setEchoChar('*');									//���������Ļ����ַ�
		password.addKeyListener(new KeyAdapter() {					//���������
			public void keyPressed(final KeyEvent e) {				//�������̵����¼�
				if (e.getKeyCode() == 10)							//������˻س���
					login.doClick();								//���е�¼
			}
		});
		centerPanel.add(password);									//��ӵ��������

		final JPanel southPanel = new JPanel();						//����һ���ײ����
		mainPanel.add(southPanel, BorderLayout.SOUTH);				//��ӵ��������
		login=new JButton();										//������ť���
		login.addActionListener(new BookLoginAction());				//��Ӽ�����
		login.setText("��¼");										//���ð�ť�ı�
		southPanel.add(login);										//�Ѱ�ť��ӵ��ײ����
		reset=new JButton();										//������ť���
		reset.addActionListener(new BookResetAction());				//��Ӽ�����
		reset.setText("����");										//���ð�ť�ı�
		southPanel.add(reset);										//�Ѱ�ť��ӵ��ײ����
		setVisible(true);											//���ô����ɼ�
		setResizable(false);										//���ô��岻�ɸı��С
	}
	public static Operater getUser() {
		return user;
	}
	public static void setUser(Operater user) {
		BookLoginIFrame.user = user;
	}

}
