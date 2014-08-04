package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise03 extends JFrame{

    private JTextField jTextField1 = new JTextField("5", 7);
    private JTextField jTextField2 = new JTextField("5", 7);
    private RectanglePanel rectanglePanel = new RectanglePanel();
    
    public Exercise03() throws HeadlessException {
        setLayout(new BorderLayout());
        add(rectanglePanel, BorderLayout.CENTER);
        JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jPanel1.add(new JLabel("X:"));
        jPanel1.add(jTextField1);
        jPanel1.add(new JLabel("Y:"));
        jPanel1.add(jTextField2);
        JButton jButton1 = new JButton("Translate");
        jPanel1.add(jButton1);
        add(jPanel1, BorderLayout.SOUTH);
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rectanglePanel.transleta(Integer.parseInt(jTextField1.getText()), Integer.parseInt(jTextField2.getText()));   
                } catch (NumberFormatException ex) {
                }
            }
        });
    }
    
    class RectanglePanel extends JPanel {

        private Rectangle2D ri = new Rectangle(40, 40, 50, 40);
        private int x = 0;
        private int y = 0;
        
        public void transleta(int x, int y) {
            this.x += x;
            this.y += y;
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.translate(x, y);
            g2d.draw(ri);
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise03();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
