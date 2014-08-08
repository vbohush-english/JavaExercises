package net.bohush.exercises.chapter49;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise12 extends JFrame{

    public Exercise12() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new SunPanel(), BorderLayout.CENTER);
    }
    
    class SunPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setPaint(new GradientPaint(10, 10, Color.RED, 40, 40, Color.YELLOW, true));
            
            //sun
            int radius = Math.min(getWidth(), getHeight()) / 4;
            g2d.fillOval(getWidth() / 2 - radius / 2, getHeight() / 2 - radius / 2, radius, radius);
            
            //rays
            int rays = 20;
            g2d.setStroke(new BasicStroke(8.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f, new float[]{16}, 0));
            g2d.translate(getWidth() / 2, getHeight() / 2);
            for (int i = 0; i < rays; i++) {
                g2d.drawLine(0, - radius / 2 - 10, 0, - 2 * radius);
                g2d.rotate(Math.PI / (rays / 2.0));
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise12();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
