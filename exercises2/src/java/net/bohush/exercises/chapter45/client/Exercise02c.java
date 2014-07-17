package net.bohush.exercises.chapter45.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import net.bohush.exercises.chapter45.exercise02.Exercise02;
import net.bohush.exercises.chapter45.exercise02.Exercise4502;

public class Exercise02c extends JApplet {
// Declare a service object and a proxy object

    private Exercise4502 exercise4501 = new Exercise4502();
    private Exercise02 proxy = exercise4501.getExercise02Port();
    private JButton jbtGetScore = new JButton("Get Score");
    private JTextField jtfName = new JTextField();
    private JTextField jtfScore = new JTextField();
    private JTextField jtfPermission = new JTextField();

    @Override
    public void init() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(3, 2));
        jPanel1.add(new JLabel("Name"));
        jPanel1.add(jtfName);
        jPanel1.add(new JLabel("Permission"));
        jPanel1.add(jtfPermission);
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
            int jpermission = proxy.getPermission(jtfName.getText().trim());
            jtfPermission.setText(jpermission + "");
            if (jpermission >= 0) {
                jtfScore.setText(Double.toString(score));
            } else {                
                jtfScore.setText("Not found");
            }
        } catch (Exception ex) {
        }
    }
}
