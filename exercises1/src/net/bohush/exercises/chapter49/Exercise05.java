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

public class Exercise05 extends JFrame{

    private JTextField jTextField1 = new JTextField("1", 7);
    private JTextField jTextField2 = new JTextField("1", 7);
    private RectanglePanel rectanglePanel = new RectanglePanel();
    
    public Exercise05() throws HeadlessException {
        setLayout(new BorderLayout());
        add(rectanglePanel, BorderLayout.CENTER);
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel1.add(new JLabel("Scale factor for x:"));
        jPanel1.add(jTextField1);
        jPanel1.add(new JLabel("y:"));
        jPanel1.add(jTextField2);
        JButton jButton1 = new JButton("Scale");
        jPanel1.add(jButton1);
        add(jPanel1, BorderLayout.SOUTH);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rectanglePanel.scale(Double.parseDouble(jTextField1.getText()), Double.parseDouble(jTextField2.getText()));   
                } catch (NumberFormatException ex) {
                }
            }
        });
    }
    
    class RectanglePanel extends JPanel {

        private Ellipse2D ellipse = new Ellipse2D.Double(0, 0, 40, 60);
        private double x = 1;
        private double y = 1;
        
        public void scale(double x, double y) {
            this.x *= x;
            this.y *= y;
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(150, 50);
            g2d.scale(x, y);
            g2d.draw(ellipse);
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise05();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
