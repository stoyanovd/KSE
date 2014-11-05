package def.site1;

import javax.swing.*;
import java.awt.*;

public class PaintGraph extends JPanel {
	private int ny, nx, oyn, oyk, oyx, oxn, oxk, oxy, ly, lx, sw, xln, l2;
	private float xng, kx, ky, hx, yg, xk;

	public PaintGraph() {
		ny = 10;// цена деления  по шкалам
		ky = (float) 0.5; // коэф шкалы по у
		kx = (float) 0.5; // коэф шкалы по x
		oyn = 50; // начальный отступ по y
		oxn = 50; //начальный отступ по х
		ly = 400; // длина оси у
		lx = 600; // длина оси х
		// по умолчанию в начале на экран выводится график y=x
		sw = 1; // свич для переключения графика функции
		hx = (float) 0.011;//шаг табуляции
	}

	public void paint(Graphics g) {
		super.paint(g);
		//Разбиваем каждую ось на две части для удобства переноса центра координат
		// Ось Y
		g.drawLine((int) (lx * kx + oxn), oyn,
				(int) (lx * kx + oxn), ly + oyn);
		// Стрелки
		g.drawLine((int) (lx * kx + oxn), oyn,
				(int) (lx * kx + oxn) - 3, oyn + 10);
		g.drawLine((int) (lx * kx + oxn), oyn,
				(int) (lx * kx + oxn) + 3, oyn + 10);
		// Надпись
		g.drawString("Y", (int) (lx * kx + oxn) - 10, oyn + 10);
		g.drawString("0", (int) (lx * kx + oxn) - 10, (int) (ly * ky + oyn) + 10);
		//Деления
		int l1 = (int) (ly * ky);
		l2 = ly - l1;
		int k1 = (int) l1 / ny;
		int k2 = (int) l2 / ny;
		for (int i = 1; i < k1 + 1; i++) {
			g.drawLine((int) (lx * kx - 2 + oxn), l1 - ny + oyn,
					(int) (lx * kx + 2 + oxn), l1 - ny + oyn);
			l1 = l1 - ny;
		}
		l1 = ly - l2;
		for (int i = 1; i < k2 + 1; i++) {
			g.drawLine((int) (lx * kx - 2 + oxn), l1 + ny + oyn,
					(int) (lx * kx + 2 + oxn), l1 + ny + oyn);
			l1 = l1 + ny;
		}
		// Ось Х
		g.drawLine(oxn, (int) (ly * ky + oyn), lx + oxn, (int) (ly * ky + oyn));
		g.drawLine(lx + oxn, (int) (ly * ky + oyn), lx + oxn - 10,
				(int) (ly * ky + oyn) - 3);
		g.drawLine(lx + oxn, (int) (ly * ky + oyn), lx + oxn - 10,
				(int) (ly * ky + oyn) + 3);
		// Надпись
		g.drawString("Х", lx + oyn - 10, (int) (ly * ky + oyn) - 10);
		// Деления
		l1 = (int) (lx * kx);
		l2 = lx - l1;
		k1 = (int) l1 / ny;
		k2 = (int) l2 / ny;
		for (int i = 1; i < k1 + 1; i++) {
			g.drawLine(l1 - ny + oxn, (int) (ly * ky - 2 + oyn),
					l1 - ny + oxn, (int) (ly * ky + 2 + oyn));
			l1 = l1 - ny;
		}
		l1 = lx - l2;
		double xl = l1 / ny;
		double xl1 = l2 / ny;
		for (int i = 1; i < k2 + 1; i++) {
			g.drawLine(l1 + ny + oxn, (int) (ly * ky - 2 + oyn),
					l1 + ny + oxn, (int) (ly * ky + 2 + oyn));
			l1 = l1 + ny;
		}
		// Выбор метода для рисования функции
		switch (sw) {
			case 1:
				funcLine(g);
				break;

			case 2:
				funcPar(g);
				break;

			case 3:
				funcGip(g);
				break;
		}
	}

	// группа методов рисующих графики функций
	// Метод рисующий линию

	void funcLine(Graphics g) {
		xln = (lx - l2);
		xk = 0;
		yg = 0;
		while ((xk + hx) * ny < xln && (xk + hx) * ny < ly - ly * ky) {
			yg = xk;
			g.drawLine((int) (xln - xk * ny + oxn),
					(int) (ly * ky + yg * ny + oyn),
					(int) (xln - (xk + hx) * ny + oxn),
					(int) (ly * ky + (xk + hx) * ny) + oyn);
			xk = xk + hx;
		}
		xk = 0;
		yg = 0;
		while ((xk + hx) * ny < l2 && (xk + hx) * ny < ly * ky) {
			yg = xk;
			g.drawLine((int) (xln + xk * ny + oxn),
					(int) (ly * ky - yg * ny + oyn),
					(int) (xln + (xk + hx) * ny + oxn),
					(int) (ly * ky - (xk + hx) * ny) + oyn);
			xk = xk + hx;
		}
	}

	// Метод рисующий параболу
	void funcPar(Graphics g) {
		xln = (lx - l2);
		xk = 0;
		yg = 0;
		int kp = 0;
		while ((xk + hx) * ny < xln && Math.pow(xk + hx, 2) * ny < ly * ky) {
			g.drawLine((int) (xln - xk * ny + oxn),
					(int) (ly * ky - yg * ny + oyn),
					(int) (xln - (xk + hx) * ny + oxn),
					(int) (ly * ky - Math.pow(xk + hx, 2) * ny + oyn));
			xk = xk + hx;
			yg = (float) Math.pow(xk, 2);
			kp++;
		}
		xk = 0;
		yg = 0;
		while ((xk + hx) * ny < l2 && Math.pow(xk + hx, 2) * ny < ly * ky) {
			yg = (float) Math.pow(xk, 2);
			g.drawLine((int) (xln + xk * ny + oxn),
					(int) (ly * ky - yg * ny + oyn),
					(int) (xln + (xk + hx) * ny + oxn),
					(int) (ly * ky - Math.pow(xk + hx, 2) * ny) + oyn);
			xk = xk + hx;
		}
	}

	// Метод рисующий гиперболу
	void funcGip(Graphics g) {
		xk = 0;
		yg = 0;
		xln = (lx - l2);
		while ((xk + hx) * ny < xln && Math.pow(xk + hx, 3) * ny < ly - ly * ky) {
			yg = (float) Math.pow(xk, 3);
			g.drawLine((int) (xln - xk * ny + oxn),
					(int) (ly * ky + yg * ny + oyn),
					(int) (xln - (xk + hx) * ny + oxn),
					(int) (ly * ky + Math.pow(xk + hx, 3) * ny) + oyn);
			xk = xk + hx;
		}
		xk = 0;
		yg = 0;
		while ((xk + hx) * ny < l2 && Math.pow(xk + hx, 3) * ny < ly * ky) {
			yg = (float) Math.pow(xk, 3);
			g.drawLine((int) (xln + xk * ny + oxn),
					(int) (ly * ky - yg * ny + oyn),
					(int) (xln + (xk + hx) * ny + oxn),
					(int) (ly * ky - Math.pow(xk + hx, 3) * ny) + oyn);
			xk = xk + hx;
		}
	}

	// группа getXXX(), setXXX() - методов
	public int getNx() {
		return nx;
	}

	public void setNx(int nx) {
		this.nx = nx;
	}

	public int getNy() {
		return ny;
	}

	public void setNy(int ny) {
		this.ny = ny;
	}

	public float getKy() {
		return ky;
	}

	public void setKy(float ky) {
		this.ky = ky;
	}

	public float getKx() {
		return kx;
	}

	public void setKx(float kx) {
		this.kx = kx;
	}

	public float getHx() {
		return hx;
	}

	public void setHx(float hx) {
		this.hx = hx;
	}

	public int getLx() {
		return lx;
	}

	public void setLx(int lx) {
		this.lx = lx;
	}

	public int getLy() {
		return ly;
	}

	public void setLy(int ly) {
		this.ly = ly;
	}

	public int getSw() {
		return sw;
	}

	public void setSw(int sw) {
		this.sw = sw;
	}

	public int getOyn() {
		return oyn;
	}

	public void setOyn(int oyn) {
		this.oyn = oyn;
	}

	public int getOxn() {
		return oxn;
	}

	public void setOxn(int oxn) {
		this.oxn = oxn;
	}
}