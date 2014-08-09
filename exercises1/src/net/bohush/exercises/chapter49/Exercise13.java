package net.bohush.exercises.chapter49;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise13 extends JFrame{

    public Exercise13() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new CylinderPanel(), BorderLayout.CENTER);
    }
    
    class CylinderPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            g2d.translate(getWidth() / 2, getHeight() / 2);
            
            int hSize = getHeight() / 4;
            int wSize = getWidth() / 4;
            
            g2d.drawLine(- wSize, - hSize, - wSize, hSize);
            g2d.drawLine(wSize, - hSize, wSize, hSize);
            
            g2d.drawOval(- wSize, - hSize - (wSize / 4), wSize * 2, wSize / 2);
            g2d.drawArc(- wSize, hSize - (wSize / 4), wSize * 2, wSize / 2, 180, 180);
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 1.0f, new float[]{10}, 0));
            g2d.drawArc(- wSize, hSize - (wSize / 4), wSize * 2, wSize / 2, 0, 180);

        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise13();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
