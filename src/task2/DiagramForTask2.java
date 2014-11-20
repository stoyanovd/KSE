package task2;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DiagramForTask2 extends JPanel {

    private static final int NUMBER_OF_ITERATION = 50;
    private Graphics2D g2d;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static double r = 1;

    public DiagramForTask2() {
        setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        g2d = buffer.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawHell();
        g.drawImage(buffer, 0, 0, this);
    }


    private static double f(double r, double x) {
        return r * x * (1 - x);
    }

    private void printAxis(double left, double right, double bottom, double top, int startCoordinate_x, int startCoordinate_y) {
        g2d.setColor(Color.green);

        g2d.drawLine(0, startCoordinate_x, WIDTH, startCoordinate_x);
        for (int i = 1; i <= 10; i++) {
            g2d.drawLine(startCoordinate_x - 5, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10, startCoordinate_x + 5, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10);
            String s = String.format("%.2f",i * (top - bottom) / 10.0);
            g2d.drawString(s, startCoordinate_x / 2, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10 + 5);
        }

        g2d.drawLine(startCoordinate_y, 0, startCoordinate_y, HEIGHT);

        for (int i = 1; i <= 10; i++) {
            g2d.drawLine(startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10, startCoordinate_y - 5, startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10, HEIGHT);
            String s = String.format("%.2f",i * (right - left) / 10.0);
            g2d.drawString(s, startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10 - s.length() * 3, startCoordinate_y / 2);
        }
    }

    // x=rx(1-x) simple iteration method
    private void drawHell() {

        double indent_x = 50;
        double indent_y = 50;
        g2d.setFont(new Font("Tahoma", 0, 12));
        printAxis(0, NUMBER_OF_ITERATION, 0, 1, (int) indent_x, (int) indent_y);
        g2d.translate(indent_x, indent_y);

        g2d.setColor(Color.blue);
        double x = 0.2;
        int previous_x=0;
        int previous_y=(int)Math.round(x * (HEIGHT - indent_y));
        for (int i = 0; i < NUMBER_OF_ITERATION; i++) {
            double x1 = Math.round(i * (WIDTH - indent_x) / (NUMBER_OF_ITERATION));
            double y1 = Math.round(x * (HEIGHT - indent_y));
            g2d.drawLine(previous_x, previous_y, (int) x1, (int) y1);
            previous_x=(int)x1;
            previous_y=(int)y1;
            x = f(r, x);
        }
        g2d.translate(-indent_x, -indent_y);
    }
}
