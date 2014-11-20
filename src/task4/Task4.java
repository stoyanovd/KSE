package task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Task4 extends JFrame implements MouseListener {
    private JPanel cp;

    public Task4() {
        super("Task4");

        cp = new JPanel(new BorderLayout());
        setContentPane(cp);
        cp.add(new DiagramForTask4(), BorderLayout.CENTER);
        cp.addMouseListener(this);

        cp.setBackground(Color.black);
        setSize(DiagramForTask4.WIDTH , DiagramForTask4.HEIGHT );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Task4 m = new Task4();
        m.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DiagramForTask4.clicked_x=e.getX();
        DiagramForTask4.clicked_y=e.getY();
        update(getGraphics());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
