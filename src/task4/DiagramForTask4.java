package task4;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DiagramForTask4 extends JPanel {

    private static final int NUMBER_OF_ITERATION = 50;
    private static final double EPS = 0.000001;
    private Graphics2D g2d;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static int clicked_x = -1;
    public static int clicked_y = 1;


    public DiagramForTask4() {
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


    private Complex f(Complex x) {
        return x.mul(x).mul(x).minus(new Complex(1, 0));
    }

    private Complex derivativeF(Complex x) {
        return x.mul(x).mul(new Complex(3, 0));
    }

    private void printAxis(double left, double right, double bottom, double top, int startCoordinate_x, int startCoordinate_y) {


        g2d.setColor(Color.green);

        g2d.drawLine(0, startCoordinate_x, WIDTH, startCoordinate_x);
        for (int i = 1; i <= 10; i++) {
            g2d.drawLine(startCoordinate_x - 5, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10, startCoordinate_x + 5, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10);

            String s = String.format("%.2f",(i * (top - bottom) / 10.0 + bottom));
            g2d.drawString(s, startCoordinate_x / 2, startCoordinate_x + i * (HEIGHT - startCoordinate_y) / 10 + 5);
        }

        g2d.drawLine(startCoordinate_y, 0, startCoordinate_y, HEIGHT);

        for (int i = 1; i <= 10; i++) {
            g2d.drawLine(startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10, startCoordinate_y - 5, startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10, HEIGHT);
            String s=String.format(("%.2f"),(i * (right - left) / 10.0 + left));
            g2d.drawString(s, startCoordinate_y + i * (WIDTH - startCoordinate_x) / 10 - s.length() * 3, startCoordinate_y / 2);
        }
    }

    private class Complex {
        private double x;
        private double y;

        public Complex(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Complex mul(Complex z) {
            return new Complex(x * z.x - z.y * y, y * z.x + x * z.y);
        }

        public Complex minus(Complex z) {
            return new Complex(x - z.x, y - z.y);
        }

        public Complex div(Complex z) {
            return new Complex((x * z.x + y * z.y) / (z.x * z.x + z.y * z.y), (y * z.x - x * z.y) / (z.x * z.x + z.y * z.y));
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Complex && Math.abs(((Complex) o).x - x) + Math.abs(((Complex) o).y - y) < EPS;
        }
    }

    private void drawHell() {

        int indent_x = 50;
        int indent_y = 50;
        g2d.setFont(new Font("Tahoma", 0, 12));
        double square = 2;
        double left = -square;
        double right = square;
        double bottom = -square;
        double top = square;
        printAxis(left, right, bottom, top, indent_x, indent_y);
        g2d.translate(indent_x, indent_y);

        Complex root1 = null, root2 = null, root3 = null;

        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                Complex x = new Complex(i * (right - left) / (WIDTH - indent_x) + left, j * (top - bottom) / (HEIGHT - indent_y) + bottom);

                for (int k = 0; k < NUMBER_OF_ITERATION; k++) {
                    x = x.minus(f(x).div(derivativeF(x)));
                }


                g2d.setColor(Color.blue);
                if (root1 != null) {
                    if (root1.equals(x)) {
                        g2d.drawLine(i, j, i, j);
                        continue;
                    }
                } else {
                    root1 = x;
                    g2d.drawLine(i, j, i, j);
                    continue;
                }


                g2d.setColor(Color.red);
                if (root2 != null) {
                    if (root2.equals(x)) {
                        g2d.drawLine(i, j, i, j);
                        continue;
                    }
                } else {
                    root2 = x;
                    g2d.drawLine(i, j, i, j);
                    continue;
                }

                g2d.setColor(Color.white);
                if (root3 != null) {
                    if (root3.equals(x)) {
                        g2d.drawLine(i, j, i, j);

                    }
                } else {
                    root3 = x;
                    g2d.drawLine(i, j, i, j);

                }

            }
        g2d.setColor(Color.green);
        for (double x = -1; x <= 1; x += 0.0005) {
            double y = Math.sqrt(1 - x * x);
            int x1 = (int) ((x - left) * (WIDTH - indent_x) / (right-left));
            int y1 = (int) ((y - bottom) * (HEIGHT - indent_y) / (top-bottom));
            int y2 = (int) ((-y - bottom) * (HEIGHT - indent_y) / (top-bottom));
            g2d.drawLine(x1, y1, x1, y1);
            g2d.drawLine(x1, y2, x1, y2);

        }
        Complex x = new Complex((clicked_x-indent_x) * (right - left) / (WIDTH - indent_x) + left, (clicked_y-indent_y) * (top - bottom) / (HEIGHT - indent_y) + bottom);
        int previous_x = clicked_x-indent_x;
        int previous_y = clicked_y-indent_y;
        if (clicked_x!=-1)
        for (int k = 0; k < NUMBER_OF_ITERATION; k++) {
            x = x.minus(f(x).div(derivativeF(x)));

            g2d.drawLine(previous_x, previous_y, (int) ((x.x - left) * (WIDTH - indent_x) / (right-left)),(int) ((x.y - bottom) * (HEIGHT - indent_y) / (top-bottom)));
            previous_x = (int) ((x.x - left) * (WIDTH - indent_x) / (right-left));
            previous_y = (int) ((x.y - bottom) * (HEIGHT - indent_y) / (top-bottom));
        }


        g2d.translate(-indent_x, -indent_y);
    }



}
