package test;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Example3 {
	 public static void main(String[] args){
		  MyFrame frame = new MyFrame();
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.setVisible(true);
		 }
		}

		class MyFrame extends JFrame{
		 public MyFrame(){
		  setTitle("DrawTest");
		  setSize(W,H);
		  MyPanel panel = new MyPanel();
		  Container contentPane = getContentPane();
		  contentPane.add(panel);
		 }
		 public static final int W=400;
		 public static final int H=400;
		}

		class MyPanel extends JPanel implements MouseListener{
		 int x1,x2;
		 int y1,y2;
		 MyPanel(){
		  addMouseListener(this);
		 }
		 
		 public void mouseClicked(MouseEvent e){
		 }
		 
		 public void mousePressed(MouseEvent e){
		  x1=e.getX();
		  y1=e.getY();
		 }
		 
		 public void mouseReleased(MouseEvent e){
		  x2=e.getX();
		  y2=e.getY();
		  repaint();
		 }
		 
		 public void mouseEntered(MouseEvent e){
		 }
		 
		 public void mouseExited(MouseEvent e){
		 }
		 
		 public void paintComponent(Graphics g){
//		  super.paintComponent(g);
		  g.drawLine(x1,y1,x2,y2);
		 }

}
