package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise04 extends JFrame{

    private JTextField jTextField1 = new JTextField("5", 7);
    private RectanglePanel rectanglePanel = new RectanglePanel();
    
    public Exercise04() throws HeadlessException {
        setLayout(new BorderLayout());
        add(rectanglePanel, BorderLayout.CENTER);
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel1.add(new JLabel("Angle:"));
        jPanel1.add(jTextField1);
        JButton jButton1 = new JButton("Rotate");
        jPanel1.add(jButton1);
        add(jPanel1, BorderLayout.SOUTH);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rectanglePanel.rotate(Integer.parseInt(jTextField1.getText()));   
                } catch (NumberFormatException ex) {
                }
            }
        });
    }
    
    class RectanglePanel extends JPanel {

        private Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 40, 60);
        private int angle = 0;
        
        public void rotate(int angle) {
            this.angle += angle;
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(100, 70);
            g2d.rotate(Math.toRadians(angle));
            g2d.draw(ellipse);
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise04();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
