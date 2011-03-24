package cdu.gujiao.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import cdu.gujiao.iframe.BookLoginIFrame;
import cdu.gujiao.util.CreateIcon;


/**
 * @(#)						
 * ��Ȩ��		�ɶ���ѧ��ҵ���		 	
 * ������		������
 *		
 * @author		����
 * @version		final		
 * @createDate	2011-2-15	 
 * @see 				
 */
public class Library extends JFrame {
	
	/**���洰��*/
	private static final JDesktopPane 
				DESKTOP_PANE = new JDesktopPane();	
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());	//����ϵͳ���
			new BookLoginIFrame();								//��¼����
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * @description		�����洰��������Ӵ��壨JInternalFrame��
	 * @author			����	
	 * @createDate		2011-3-15
	 * @param			
	 * @return					
	 * @see						
	 */
	public static void addIFame(JInternalFrame iframe) { 		//����Ӵ���ķ���
		DESKTOP_PANE.add(iframe);								//�����Ӵ���
	}
	
	/**
	 * ���캯��
	 */
	public Library() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���ùرհ�ť�����¼�
		Toolkit tool = Toolkit.getDefaultToolkit();				//���Ĭ�ϵĹ�����
		Dimension screenSize = tool.getScreenSize();			//�����Ļ�Ĵ�С
		setSize(800, 600);										//���ô����С
		setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);			//���ô���λ��
		setTitle("ͼ��ݹ���ϵͳ");								//���ô������
		JMenuBar menuBar = createMenu(); 						//���ô����˵����ķ���
		setJMenuBar(menuBar);									//���ò˵���
		final JLabel label = new JLabel();								//����һ����ǩ����ʾͼƬ
		label.setBounds(0, 0, 0, 0);									//���ô���Ĵ�С��λ��
		label.setIcon(null); 											// ���屳��
		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();			//��������С
				label.setSize(size);									//���ñ�ǩ��С
				label.setText("<html><img width=" + size.width + " height="
						+ size.height + " src='"
						+ this.getClass().getResource("/lib.jpg")
						+ "'></html>");									//���ñ�ǩ�ı�
			}
		});
		DESKTOP_PANE.add(label,new Integer(Integer.MIN_VALUE));			//����һ��������ʾlabel��JDesktoPane�����һ�������Ҳ����˵label��Զ����ײ�
		getContentPane().add(DESKTOP_PANE);								//�����洰����ӵ���������
	}
	
	/**
	 * @description		�����˵���
	 * @author			����	
	 * @createDate		2011-2-15
	 * @param	
	 * @return					
	 *	
	 * @see						
	 */
	private JMenuBar createMenu() { 							//�����˵����ķ���
		JMenuBar menuBar = new JMenuBar();						//����������
		
		/*������Ϣ����*/
		JMenu readerManagerMItem = new JMenu("������Ϣ����");	//����������Ϣ�����Ӳ˵�
		readerManagerMItem.add(MenuActions.READER_ADD);			//��Ӷ�����Ϣ��Ӳ˵���
		readerManagerMItem.add(MenuActions.READER_MODIFY);		//��Ӷ�����Ϣ��Ӳ˵���
		
		/*ͼ�������Ϣ����*/
		JMenu bookTypeManageMItem = new JMenu("ͼ��������");	//����ͼ���������Ӳ˵�
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);		//���ͼ��������Ӳ˵���
		bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);	//���ͼ�������޸Ĳ˵���
		
		/*ͼ����Ϣ����*/
		JMenu menu = new JMenu("ͼ����Ϣ����");					//����ͼ����Ϣ�����Ӳ˵�
		menu.add(MenuActions.BOOK_ADD);							//���ͼ����Ϣ��Ӳ˵���
		menu.add(MenuActions.BOOK_MODIFY);						//���ͼ����Ϣ�޸Ĳ˵���
		
		/*���鶩������*/
		JMenu bookOrderMenu = new JMenu("���鶩������"); 		//��ʼ�����鶩������˵�
		bookOrderMenu.add(MenuActions.NEWBOOK_ORDER);			//������鶨���˵���
		bookOrderMenu.add(MenuActions.NEWBOOK_CHECK_ACCEPT);	//�����������˵���
		
		/*���Ĺ���*/
		JMenu borrowManageMenu = new JMenu("���Ĺ���"); 			//���Ĺ���
		borrowManageMenu.add(MenuActions.BORROW); 				//��ӽ��Ĳ˵���
		borrowManageMenu.add(MenuActions.GIVE_BACK); 			//��ӹ黹�˵���
		borrowManageMenu.add(MenuActions.BOOK_SEARCH); 			//��������˵���
		
		/*�޸�����*/
		JMenu sysManageMenu = new JMenu("�޸�����"); 			//����Ա����
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);			//��Ӹ��Ĳ˵���
		
		/*�ڹ���������Ӳ˵�*/
		menuBar.add(readerManagerMItem); 						//��Ӷ�����Ϣ�˵����˵���
		menuBar.add(bookTypeManageMItem); 						//��ӻ�������ά���˵����˵���
		menuBar.add(menu); 										//��ӻ�������ά���˵����˵���
		menuBar.add(bookOrderMenu); 							//������鶩������˵����˵���
		menuBar.add(borrowManageMenu); 							//��ӽ��Ĺ���˵����˵���
		menuBar.add(sysManageMenu); 							//���ϵͳά���˵����˵���
		return menuBar;
	}
}
