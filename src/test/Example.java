package test;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import cdu.gujiao.iframe.BookLoginIFrame;
import cdu.gujiao.model.Operater;

public class Example extends JFrame{
	
	private JTextField username = new JTextField(20);
	public static String s = null;
	
	public Example(){
		super("dfsaf");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100,100,100,100);
		add(username);
		username.addKeyListener(new myKeyAdapter());
		setVisible(true);
		Operater ooo = BookLoginIFrame.getUser();
		s = ooo.getName();
	}
	
	public static void main(String args[]){
		Example ex = new Example();
		String ccc = s;
	}
	
	class myKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 8){
				e.consume();
			}
			System.out.println(e.getKeyCode()) ;
		}
	}
}
