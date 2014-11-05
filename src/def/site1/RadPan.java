package def.site1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RadPan extends JPanel implements ActionListener {
	private JRadioButton jrb1, jrb2, jrb3;
	private ButtonGroup bg;
	PaintGraph pg;

	public void actionPerformed(ActionEvent e) {
		// Проверяется источник генерации события, и в зависимости от него задается вид
		// графика функции
		if (e.getSource().equals(jrb1)) {
			pg.setSw(1);
			pg.repaint();// перерисовка графика функции
		}
		if (e.getSource().equals(jrb2)) {
			pg.setSw(2);
			pg.repaint();
		}
		if (e.getSource().equals(jrb3)) {
			pg.setSw(3);
			pg.repaint();
		}
	}

	public RadPan(PaintGraph p) {
		setLayout(new FlowLayout());
		pg = p;
		jrb1 = new JRadioButton("Линия", true);
		jrb2 = new JRadioButton("Парабола", false);
		jrb3 = new JRadioButton("Гипербола", false);
		jrb1.addActionListener(this);
		jrb2.addActionListener(this);
		jrb3.addActionListener(this);
		bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
		add(jrb1);
		add(jrb2);
		add(jrb3);
	}
}
