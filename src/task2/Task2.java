package task2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Task2 extends JFrame {
    public Task2() {
        super("Task2");

        JPanel cp = new JPanel(new BorderLayout());
        setContentPane(cp);
        cp.add(new DiagramForTask2(), BorderLayout.CENTER);

        final JTextField textFieldR=new JTextField(20);
        textFieldR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=textFieldR.getText().replace(',','.');
                DiagramForTask2.r=Double.parseDouble(s);
                update(getGraphics());
            }
        });

        cp.add(textFieldR,BorderLayout.NORTH);
        cp.setBackground(Color.black);
        setSize(DiagramForTask2.WIDTH+50, DiagramForTask2.HEIGHT+50);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Task2 m=new Task2();
        m.setVisible(true);
    }
}
