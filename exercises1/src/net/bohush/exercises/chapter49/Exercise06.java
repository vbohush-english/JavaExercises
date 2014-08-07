package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise06 extends JFrame{

    public Exercise06() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new TextPanel(), BorderLayout.CENTER);
    }
    
    class TextPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, 80));
            g2d.rotate(Math.toRadians(90), 200, 200);
            g2d.drawString("Java", 50 , 50);
            g2d.drawString("Java", 50 , 150);
            g2d.drawString("Java", 50 , 250);
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise06();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
