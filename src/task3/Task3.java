package task3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Task3 extends JFrame {
    public Task3() {
        super("Task3");

        JPanel cp = new JPanel(new BorderLayout());
        setContentPane(cp);
        cp.add(new DiagramForTask3(), BorderLayout.CENTER);

        final JTextField textFieldR=new JTextField(20);
        textFieldR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textFieldR.getText().replace(',','.');
                DiagramForTask3.r=Double.parseDouble(s);
                update(getGraphics());
            }
        });

        cp.add(textFieldR,BorderLayout.NORTH);
        cp.setBackground(Color.black);
        setSize(DiagramForTask3.WIDTH+50, DiagramForTask3.HEIGHT+50);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Task3 m=new Task3();
        m.setVisible(true);
    }
}
