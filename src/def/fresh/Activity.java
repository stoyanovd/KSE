package def.fresh;

import javax.swing.*;
import java.awt.*;

/**
 * Created  by dima  on 30.10.14.
 */
public class Activity extends JFrame{

	public Activity()
	{
		super("КСЕ");
		Container container = getContentPane();
		container.setLayout(new BorderLayout()); // установка менеджера размещения

		CoordinateSystem coordinateSystem = new CoordinateSystem(); // инициализация класса построения графика функции

		container.add(coordinateSystem, BorderLayout.CENTER); // задание размещения
		/*bp = new (pg); // инициализация класса кнопок масштаба
		c.add(bp, BorderLayout.WEST);
		rp = new (pg);
		c.add(rp, BorderLayout.NORTH);// инициализация класса выбора графика функции*/
		setSize(800, 600); // задание размеров
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // задание параметров
		// главного окна при закрытии
		setVisible(true);
	}

	public static void main(String[] args) {
		new Activity();
	}
}
