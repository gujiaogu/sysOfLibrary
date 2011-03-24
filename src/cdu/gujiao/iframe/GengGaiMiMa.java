package cdu.gujiao.iframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cdu.gujiao.dao.Dao;
import cdu.gujiao.model.Operater;
import cdu.gujiao.util.MyDocument;



/**
 * @(#)							
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		����Ա�޸��������
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-3-4	 
 * @see 				
 */
public class GengGaiMiMa extends JInternalFrame{
	/**������*/
	private JPasswordField oldPass;	
	/**������*/
	private JPasswordField newPass1;							
	/**ȷ��������*/
	private JPasswordField newPass2;							
	/**�û����ı���*/
	private JTextField username;
	/**����Ա��Ϣ*/
	private Operater user = BookLoginIFrame.getUser(); 
	
	public GengGaiMiMa() {
		super();
		
		setIconifiable(true);								//����JInteralFrame�ܷ�ͼ�껯						
		setTitle("��������");								//���ñ���
		setClosable(true);									//����JInternalFrame�ܷ�ر�
		getContentPane().setLayout(new GridBagLayout());	//���ò��ַ�ʽ
		setBounds(100, 100, 300, 168);						//���ñ߽�
		
		/*�û����ı����ı���*/
		final JLabel label_5 = new JLabel();					
		label_5.setFont(new Font("", Font.PLAIN, 14));
		label_5.setText("��  ¼  ����");
		final GridBagConstraints gridBagConstraints_11 
							= new GridBagConstraints();			//�������������Լ��
		gridBagConstraints_11.gridy = 2;						//������ʼ��Ԫ��
		gridBagConstraints_11.gridx = 0;						//������ʼ��Ԫ��
		getContentPane().add(label_5, gridBagConstraints_11);	//����¼����ǩ�ӵ������ϣ�������Լ��

		username  =new JTextField(user.getName());				//��¼���ı���
		final GridBagConstraints gridBagConstraints_12 
							= new GridBagConstraints();			//�����ı���Լ��
		gridBagConstraints_12.gridy = 2;						//���������ʼ��Ԫ��	
		gridBagConstraints_12.gridx = 1;						//���������ʼ��Ԫ��
		gridBagConstraints_12.fill =
			GridBagConstraints.HORIZONTAL;						//�ӿ������ʹ����ˮƽ�����ϵ�����������ʾ����
		getContentPane().add(username, gridBagConstraints_12);	//�������ӵ������ϣ�������Լ��
		
		username.setEditable(false);							//���õ�¼�����ı��򲻿ɱ༭
		
		/*������*/
		final JLabel label_1 = new JLabel();					//�����������ǩ
		label_1.setFont(new Font("", Font.PLAIN, 14));			//��������
		label_1.setText("��  ��  �룺");							//�����ı�
		final GridBagConstraints gridBagConstraints_2 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_2.gridy = 3;							//����������ʼ��Ԫ��
		gridBagConstraints_2.gridx = 0;							//���ú�����ʼ��Ԫ��
		getContentPane().add(label_1, gridBagConstraints_2);	//�������ӵ������ϣ�������Լ��

		oldPass = new JPasswordField();							//�������ı���
		oldPass.setDocument(new MyDocument(6));					//�����ı��򳤶�
		final GridBagConstraints gridBagConstraints_3 	
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_3.weighty = 1.0;						//�е�Ȩ�أ�������η�����ʣ��ռ�
		gridBagConstraints_3.insets = new Insets(0, 0, 0, 10);	//����ָ������������϶���������ң�
		gridBagConstraints_3.fill = 
			GridBagConstraints.HORIZONTAL;						//��ˮƽ������ʹ����
		gridBagConstraints_3.gridwidth = 3;						//�����ռ����
		gridBagConstraints_3.gridy = 3;							//������ʼ��Ԫ��
		gridBagConstraints_3.gridx = 1;							//������ʼ��Ԫ��
		getContentPane().add(oldPass, gridBagConstraints_3);	//�������ӵ������ϣ�������Լ��

		/*������*/
		final JLabel label_2 = new JLabel();					//�������ǩ
		label_2.setFont(new Font("", Font.PLAIN, 14));			//��������������
		label_2.setText("��  ��  �룺");							//���ñ�ǩ�ı�
		final GridBagConstraints gridBagConstraints_4 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_4.gridy = 4;							//������ʼ��Ԫ��
		gridBagConstraints_4.gridx = 0;							//������ʼ��Ԫ��
		getContentPane().add(label_2, gridBagConstraints_4);	//�������ӵ������ϣ�������Լ��

		newPass1 = new JPasswordField();						//�������ı���
		newPass1.setDocument(new MyDocument(6));				//�����ı��򳤶�
		newPass1.setFont(new Font("", Font.PLAIN, 14));			//�������� 
		final GridBagConstraints gridBagConstraints_5 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_5.weighty = 1.0;						//�е�Ȩ�أ�������η�����ʣ��ռ�
		gridBagConstraints_5.ipadx = 30;						//����ĺ�����
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);	//�����������������϶���������ң�
		gridBagConstraints_5.fill = 	
			GridBagConstraints.HORIZONTAL;						//��ˮƽ������ʹ����
		gridBagConstraints_5.gridwidth = 3;						//ˮƽ��ȣ���ռ������
		gridBagConstraints_5.gridy = 4;							//������ʼ��Ԫ��
		gridBagConstraints_5.gridx = 1;							//������ʼ��Ԫ��
		getContentPane().add(newPass1, gridBagConstraints_5);	//�������ӵ������ϣ�������Լ��

		/*ȷ������*/
		final JLabel label_3 = new JLabel();					//ȷ�������ǩ
		label_3.setFont(new Font("", Font.PLAIN, 14));			//��������
		label_3.setText("ȷ�������룺");							//�����ı�
		final GridBagConstraints gridBagConstraints_6 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_6.gridy = 5;							//������ʼ��Ԫ��
		gridBagConstraints_6.gridx = 0;							//������ʼ��Ԫ��
		getContentPane().add(label_3, gridBagConstraints_6);	//�������ӵ������ϣ�������Լ��

		newPass2 = new JPasswordField();						//�����ı���
		newPass2.setDocument(new MyDocument(6));				//�����ı��򳤶�
		newPass2.setFont(new Font("", Font.PLAIN, 14));			//��������
		final GridBagConstraints gridBagConstraints_7 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_7.weighty = 1.0;						//��Ȩ�أ�������η�����ʣ��ռ�
		gridBagConstraints_7.ipadx = 30;						//����������
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);	//�����������������϶���������ң�
		gridBagConstraints_7.fill = 
			GridBagConstraints.HORIZONTAL;						//����ˮƽ����
		gridBagConstraints_7.weightx = 1.0;						//��Ȩ�أ�������η�����ʣ��ռ�
		gridBagConstraints_7.gridwidth = 3;						//���ÿ�ȣ���ռ������
		gridBagConstraints_7.gridy = 5;							//����������ʼ��Ԫ��
		gridBagConstraints_7.gridx = 1;							//���ú�����ʼ��Ԫ��
		getContentPane().add(newPass2, gridBagConstraints_7);	//�������ӵ������ϣ�������Լ��

		/*ȷ�ϰ�ť*/
		final JButton button = new JButton();								//������ť
		button.addActionListener(new ActionListener() {						//����ť��ӵ����¼�
			public void actionPerformed(final ActionEvent e) {				//δʵ�ֵķ���
				if (oldPass.getText().equals(user.getPassword())) {			//�жϾ����������Ƿ���ȷ
					if (newPass1.getText().equals(newPass2.getText())) {	//�ж�����������������Ƿ�һ��
						user.setPassword(newPass1.getText());				//����user�е�password�ֶ�ֵΪ�µ�����ֵ
						Dao.Updatepass(user.getPassword(),user.getName());	//�����û�����
						/*�ı���ȫ������Ϊ��*/
						oldPass.setText(null);								
						newPass1.setText(null);								
						newPass2.setText(null);								
						JOptionPane.showMessageDialog(getContentPane(), 
								"�����޸ĳɹ���");						//�޸���ɺ󵯳���ʾ��
						doDefaultCloseAction();							//�رմ���
					}else {
						JOptionPane.showMessageDialog(getContentPane(), 
								"������������벻һ�£����������롣");		//���������벻һ�µ�ʱ�򵯳���ʾ��
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), 
							"���������������ȷ�����롣");				//���������벻��ȷʱ������ʾ��
				}
			}
		});
		button.setText("ȷ��");									//���ð�ť�ı�
		final GridBagConstraints gridBagConstraints_8 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_8.weighty = 1.0;						//��Ȩ�أ�������η�����ʣ��ռ�
		gridBagConstraints_8.gridy = 6;							//�����������ʼ��Ԫ��
		gridBagConstraints_8.gridx = 1;							//���ú������ʼ��Ԫ��
		getContentPane().add(button, gridBagConstraints_8);		//�������ӵ������ϣ�������Լ��

		/*���ð�ť*/
		final JButton button_1 = new JButton();					//������ť	
		button_1.addActionListener(new ActionListener() {		//����ť��Ӽ�����
			public void actionPerformed(final ActionEvent e) {
				oldPass.setText(null);
				newPass1.setText(null);
				newPass2.setText(null);
			}
		});
		button_1.setText("����");								//���ð�ť�ı�
		final GridBagConstraints gridBagConstraints_9 
							= new GridBagConstraints();			//����Լ��
		gridBagConstraints_9.gridwidth = 2;						//���ÿ�ȣ���ռ������
		gridBagConstraints_9.weighty = 1.0;						//��Ȩ�أ�������η���ʣ��ռ�
		gridBagConstraints_9.gridy = 6;							//����������ʼ��Ԫ��
		gridBagConstraints_9.gridx = 2;							//���ú�����ʼ��Ԫ��
		getContentPane().add(button_1, gridBagConstraints_9);	//�������ӵ������ϣ�������Լ��
		
		
		setVisible(true);	//���ô���ɼ�
	}
}
