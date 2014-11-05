package def.site1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButPan extends JPanel implements ActionListener {
	private JButton jbt1, jbt2, jbt3, jbt4, jbt5, jbt6, jbt7, jbt8;
	PaintGraph pg;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jbt1)) {
			if (pg.getNy() == 5) {
				jbt2.setEnabled(true);
			}
			pg.setNy(pg.getNy() + 5);
			pg.repaint();
		}
		if (e.getSource().equals(jbt2)) {
			pg.setNy(pg.getNy() - 5);
			pg.repaint();
			if (pg.getNy() == 5) {
				jbt2.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt3)) {
			if (pg.getKy() < 1) {
				jbt4.setEnabled(true);
			}
			pg.setKy(pg.getKy() - (float) 0.1);
			pg.repaint();
			if (pg.getKy() <= 0.05) {
				jbt3.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt4)) {
			if (pg.getKy() >= 0) {
				jbt3.setEnabled(true);
			}
			pg.setKy(pg.getKy() + (float) 0.1);
			pg.repaint();
			if (pg.getKy() >= 0.95) {
				jbt4.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt5)) {
			if (pg.getKx() < 1) {
				jbt6.setEnabled(true);
			}
			pg.setKx(pg.getKx() - (float) 0.01);
			pg.repaint();
			if (pg.getKx() <= 0.05) {
				jbt5.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt6)) {
			if (pg.getKx() > 0) {
				jbt5.setEnabled(true);
			}
			pg.setKx(pg.getKx() + (float) 0.01);
			pg.repaint();
			if (pg.getKx() >= 0.99) {
				jbt6.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt7)) {
			if (pg.getHx() >= 0.01) {
				jbt8.setEnabled(true);
			}
			pg.setHx(pg.getHx() + (float) 0.01);
			pg.repaint();
			if (pg.getHx() >= 1) {
				jbt7.setEnabled(false);
			}
		}
		if (e.getSource().equals(jbt8)) {
			if (pg.getHx() <= 1) {
				jbt7.setEnabled(true);
			}
			pg.setHx(pg.getHx() - (float) 0.01);
			pg.repaint();
			if (pg.getHx() <= 0.01) {
				jbt8.setEnabled(false);
			}
		}
	}

	public ButPan(PaintGraph p) {
		setLayout(new GridLayout(8, 1));// Установка табличного менеджера размещения
		pg = p;
		jbt1 = new JButton("Scale -");
		jbt2 = new JButton("Scale +");
		jbt3 = new JButton("|^");
		jbt4 = new JButton("|_");
		jbt5 = new JButton("<—");
		jbt6 = new JButton("—>");
		jbt7 = new JButton("hx+");
		jbt8 = new JButton("hx-");
		jbt1.addActionListener(this);
		jbt2.addActionListener(this);
		jbt3.addActionListener(this);
		jbt4.addActionListener(this);
		jbt5.addActionListener(this);
		jbt6.addActionListener(this);
		jbt7.addActionListener(this);
		jbt8.addActionListener(this);
		add(jbt1);
		add(jbt2);
		add(jbt3);
		add(jbt4);
		add(jbt5);
		add(jbt6);
		add(jbt7);
		add(jbt8);
	}
}