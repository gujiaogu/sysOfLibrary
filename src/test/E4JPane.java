package test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cdu.gujiao.util.CreateIcon;

public class E4JPane extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		setOpaque(true);
		super.paintComponent(g);
		ImageIcon icon = CreateIcon.add("tiane_.jpg");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, null, this);
	}
	
}
