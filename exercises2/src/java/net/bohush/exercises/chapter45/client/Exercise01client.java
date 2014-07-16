package net.bohush.exercises.chapter45.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Exercise01client extends JApplet {
// Declare a service object and a proxy object

    private Exercise4501 exercise4501 = new Exercise4501();
    private Exercise01 proxy = exercise4501.getExercise01Port();
    private JButton jbtGetScore = new JButton("Get Score");
    private JTextField jtfName = new JTextField();
    private JTextField jtfScore = new JTextField();

    @Override
    public void init() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(2, 2));
        jPanel1.add(new JLabel("Name"));
        jPanel1.add(jtfName);
        jPanel1.add(new JLabel("Score"));
        jPanel1.add(jtfScore);
        add(jbtGetScore, BorderLayout.SOUTH);
        add(jPanel1, BorderLayout.CENTER);
        jbtGetScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                getScore();
            }
        });
    }

    private void getScore() {
        try {
            double score = proxy.findScore(jtfName.getText().trim());
            if (score < 0) {
                jtfScore.setText("Not found");
            } else {
                jtfScore.setText(Double.toString(score));
            }
        } catch (Exception ex) {
        }
    }
}
