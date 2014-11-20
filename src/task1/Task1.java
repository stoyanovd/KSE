package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task1 extends JFrame {
    public Task1() {
        super("Task1");

        JPanel cp = new JPanel(new BorderLayout());
        setContentPane(cp);
        cp.add(new DiagramForTask1(), BorderLayout.CENTER);

        final JTextField textFieldFrom=new JTextField(20);
        final JTextField textFieldTo=new JTextField(20);
        textFieldFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=textFieldTo.getText().replace(',','.');
                String s=textFieldFrom.getText().replace(',','.');
                if (s.length()==0)s="0";
                if (s1.length()==0)s1="5";
                DiagramForTask1.left_r=Math.max(0, Double.parseDouble(s));
                DiagramForTask1.right_r=Math.min(5, Double.parseDouble(s1));
                if (DiagramForTask1.left_r- DiagramForTask1.right_r==0) DiagramForTask1.right_r+=0.0000001;
                update(getGraphics());
            }
        });
        textFieldTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1=textFieldTo.getText().replace(',','.');
                String s=textFieldFrom.getText().replace(',','.');
                if (s.length()==0)s="0";
                if (s1.length()==0)s1="5";
                DiagramForTask1.left_r=Math.max(0, Double.parseDouble(s));
                DiagramForTask1.right_r=Math.min(5, Double.parseDouble(s1));
                if (DiagramForTask1.left_r- DiagramForTask1.right_r==0) DiagramForTask1.right_r+=0.0000001;

                update(getGraphics());
            }
        });

        cp.add(textFieldFrom,BorderLayout.NORTH);
        cp.add(textFieldTo,BorderLayout.SOUTH);
        cp.setBackground(Color.black);
        setSize(DiagramForTask1.WIDTH+50, DiagramForTask1.HEIGHT+90);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Task1 m=new Task1();
        m.setVisible(true);
    }
}
