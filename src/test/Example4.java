package test;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Example4 extends JFrame{
	
	public static void main(String args[]){
		Example4 ex = new Example4();
	}
	
	public Example4(){
		super();
		setTitle("±³¾°Àý³Ì");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(550, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		
		E4JPane ej = new E4JPane();
		ej.setLayout(new GridLayout());
		getContentPane().add(ej);
		JLabel jl1 = new JLabel("46546546");
		JLabel jl2 = new JLabel("46546546");
		JLabel jl3 = new JLabel("46546546");
		JLabel jl4 = new JLabel("46546546");
		JLabel jl5 = new JLabel("46546546");
		JLabel jl6 = new JLabel("46546546");
		JLabel jl7 = new JLabel("46546546");
		JLabel jl8 = new JLabel("46546546");
		ej.add(jl1);
		ej.add(jl2);
		ej.add(jl3);
		ej.add(jl4);
		ej.add(jl5);
		ej.add(jl6);
		ej.add(jl7);
		ej.add(jl8);
		
	}
}
