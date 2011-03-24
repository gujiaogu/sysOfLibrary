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
 * 版权：		成都大学毕业设计		 	
 * 描述：		操作员修改密码的类
 *		
 * @author		顾蛟
 * @version		final		
 * @createDate	2011-3-4	 
 * @see 				
 */
public class GengGaiMiMa extends JInternalFrame{
	/**旧密码*/
	private JPasswordField oldPass;	
	/**新密码*/
	private JPasswordField newPass1;							
	/**确认新密码*/
	private JPasswordField newPass2;							
	/**用户名文本框*/
	private JTextField username;
	/**操作员信息*/
	private Operater user = BookLoginIFrame.getUser(); 
	
	public GengGaiMiMa() {
		super();
		
		setIconifiable(true);								//设置JInteralFrame能否图标化						
		setTitle("更改密码");								//设置标题
		setClosable(true);									//设置JInternalFrame能否关闭
		getContentPane().setLayout(new GridBagLayout());	//设置布局方式
		setBounds(100, 100, 300, 168);						//设置边界
		
		/*用户名文本和文本框*/
		final JLabel label_5 = new JLabel();					
		label_5.setFont(new Font("", Font.PLAIN, 14));
		label_5.setText("登  录  名：");
		final GridBagConstraints gridBagConstraints_11 
							= new GridBagConstraints();			//创建网格包布局约束
		gridBagConstraints_11.gridy = 2;						//纵向起始单元格
		gridBagConstraints_11.gridx = 0;						//横向起始单元格
		getContentPane().add(label_5, gridBagConstraints_11);	//将登录名标签加到窗体上，并设置约束

		username  =new JTextField(user.getName());				//登录名文本框
		final GridBagConstraints gridBagConstraints_12 
							= new GridBagConstraints();			//创建文本框约束
		gridBagConstraints_12.gridy = 2;						//组件纵向起始单元格	
		gridBagConstraints_12.gridx = 1;						//组件横向起始单元格
		gridBagConstraints_12.fill =
			GridBagConstraints.HORIZONTAL;						//加宽组件，使它在水平方向上得以填满其显示区域
		getContentPane().add(username, gridBagConstraints_12);	//将组件添加到窗体上，并设置约束
		
		username.setEditable(false);							//设置登录名的文本框不可编辑
		
		/*旧密码*/
		final JLabel label_1 = new JLabel();					//创建旧密码标签
		label_1.setFont(new Font("", Font.PLAIN, 14));			//设置字体
		label_1.setText("旧  密  码：");							//设置文本
		final GridBagConstraints gridBagConstraints_2 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_2.gridy = 3;							//设置纵向起始单元格
		gridBagConstraints_2.gridx = 0;							//设置横向起始单元格
		getContentPane().add(label_1, gridBagConstraints_2);	//将组件添加到窗体上，并设置约束

		oldPass = new JPasswordField();							//旧密码文本框
		oldPass.setDocument(new MyDocument(6));					//设置文本框长度
		final GridBagConstraints gridBagConstraints_3 	
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_3.weighty = 1.0;						//列的权重，设置如何分配列剩余空间
		gridBagConstraints_3.insets = new Insets(0, 0, 0, 10);	//设置指定四周所留空隙（上左下右）
		gridBagConstraints_3.fill = 
			GridBagConstraints.HORIZONTAL;						//在水平方向上使充满
		gridBagConstraints_3.gridwidth = 3;						//组件所占列数
		gridBagConstraints_3.gridy = 3;							//纵向起始单元格
		gridBagConstraints_3.gridx = 1;							//横向起始单元格
		getContentPane().add(oldPass, gridBagConstraints_3);	//将组件添加到窗体上，并设置约束

		/*新密码*/
		final JLabel label_2 = new JLabel();					//新密码标签
		label_2.setFont(new Font("", Font.PLAIN, 14));			//设置新密码字体
		label_2.setText("新  密  码：");							//设置标签文本
		final GridBagConstraints gridBagConstraints_4 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_4.gridy = 4;							//纵向起始单元格
		gridBagConstraints_4.gridx = 0;							//横向起始单元格
		getContentPane().add(label_2, gridBagConstraints_4);	//将组件添加到窗体上，并设置约束

		newPass1 = new JPasswordField();						//新密码文本框
		newPass1.setDocument(new MyDocument(6));				//限制文本框长度
		newPass1.setFont(new Font("", Font.PLAIN, 14));			//设置字体 
		final GridBagConstraints gridBagConstraints_5 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_5.weighty = 1.0;						//列的权重，设置如何分配列剩余空间
		gridBagConstraints_5.ipadx = 30;						//组件的横向间距
		gridBagConstraints_5.insets = new Insets(0, 0, 0, 10);	//设置组件四周所留空隙（上左下右）
		gridBagConstraints_5.fill = 	
			GridBagConstraints.HORIZONTAL;						//在水平方向上使充满
		gridBagConstraints_5.gridwidth = 3;						//水平宽度（所占列数）
		gridBagConstraints_5.gridy = 4;							//纵向起始单元格
		gridBagConstraints_5.gridx = 1;							//横向起始单元格
		getContentPane().add(newPass1, gridBagConstraints_5);	//将组件添加到窗体上，并设置约束

		/*确认密码*/
		final JLabel label_3 = new JLabel();					//确认密码标签
		label_3.setFont(new Font("", Font.PLAIN, 14));			//设置字体
		label_3.setText("确认新密码：");							//设置文本
		final GridBagConstraints gridBagConstraints_6 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_6.gridy = 5;							//纵向起始单元格
		gridBagConstraints_6.gridx = 0;							//横向起始单元格
		getContentPane().add(label_3, gridBagConstraints_6);	//将组件添加到窗体上，并设置约束

		newPass2 = new JPasswordField();						//创建文本框
		newPass2.setDocument(new MyDocument(6));				//设置文本框长度
		newPass2.setFont(new Font("", Font.PLAIN, 14));			//设置字体
		final GridBagConstraints gridBagConstraints_7 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_7.weighty = 1.0;						//列权重，设置如何分配列剩余空间
		gridBagConstraints_7.ipadx = 30;						//组件间横向间距
		gridBagConstraints_7.insets = new Insets(0, 0, 0, 10);	//设置组件四周所留空隙（上左下右）
		gridBagConstraints_7.fill = 
			GridBagConstraints.HORIZONTAL;						//设置水平填满
		gridBagConstraints_7.weightx = 1.0;						//行权重，设置如何分配行剩余空间
		gridBagConstraints_7.gridwidth = 3;						//设置宽度（所占列数）
		gridBagConstraints_7.gridy = 5;							//设置纵向起始单元格
		gridBagConstraints_7.gridx = 1;							//设置横向起始单元格
		getContentPane().add(newPass2, gridBagConstraints_7);	//将组件添加到窗体上，并设置约束

		/*确认按钮*/
		final JButton button = new JButton();								//创建按钮
		button.addActionListener(new ActionListener() {						//给按钮添加单击事件
			public void actionPerformed(final ActionEvent e) {				//未实现的方法
				if (oldPass.getText().equals(user.getPassword())) {			//判断旧密码输入是否正确
					if (newPass1.getText().equals(newPass2.getText())) {	//判断两次输入的新密码是否一样
						user.setPassword(newPass1.getText());				//设置user中的password字段值为新的密码值
						Dao.Updatepass(user.getPassword(),user.getName());	//更新用户密码
						/*文本框全部设置为空*/
						oldPass.setText(null);								
						newPass1.setText(null);								
						newPass2.setText(null);								
						JOptionPane.showMessageDialog(getContentPane(), 
								"密码修改成功。");						//修改完成后弹出提示框
						doDefaultCloseAction();							//关闭窗体
					}else {
						JOptionPane.showMessageDialog(getContentPane(), 
								"两次输入的密码不一致，请重新输入。");		//新密码输入不一致的时候弹出提示框
					}
				}else {
					JOptionPane.showMessageDialog(getContentPane(), 
							"旧密码输入错误，请确认密码。");				//旧密码输入不正确时弹出提示框
				}
			}
		});
		button.setText("确认");									//设置按钮文本
		final GridBagConstraints gridBagConstraints_8 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_8.weighty = 1.0;						//列权重，设置如何分配列剩余空间
		gridBagConstraints_8.gridy = 6;							//设置纵向的起始单元格
		gridBagConstraints_8.gridx = 1;							//设置横向的起始单元格
		getContentPane().add(button, gridBagConstraints_8);		//将组件添加到窗体上，并设置约束

		/*重置按钮*/
		final JButton button_1 = new JButton();					//创建按钮	
		button_1.addActionListener(new ActionListener() {		//给按钮添加监听器
			public void actionPerformed(final ActionEvent e) {
				oldPass.setText(null);
				newPass1.setText(null);
				newPass2.setText(null);
			}
		});
		button_1.setText("重置");								//设置按钮文本
		final GridBagConstraints gridBagConstraints_9 
							= new GridBagConstraints();			//创建约束
		gridBagConstraints_9.gridwidth = 2;						//设置宽度（所占列数）
		gridBagConstraints_9.weighty = 1.0;						//列权重，设置如何分配剩余空间
		gridBagConstraints_9.gridy = 6;							//设置纵向起始单元格
		gridBagConstraints_9.gridx = 2;							//设置横向起始单元格
		getContentPane().add(button_1, gridBagConstraints_9);	//将组件添加到窗体上，并设置约束
		
		
		setVisible(true);	//设置窗体可见
	}
}
