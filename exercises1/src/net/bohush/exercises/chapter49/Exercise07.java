package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise07 extends JFrame{

    public Exercise07() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new TextPanel(), BorderLayout.CENTER);
    }
    
    class TextPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setFont(new Font(Font.SERIF, Font.BOLD, 50));
            String text = "WELCOME TO JAVA ";
            g2d.translate(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < text.length(); i++) {
                g2d.drawString(text.charAt(i) + "", 0 , -200); 
                g2d.rotate(Math.PI / (text.length() / 2.0));
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise07();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
