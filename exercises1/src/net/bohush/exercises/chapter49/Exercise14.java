package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise14 extends JFrame{

    public Exercise14() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new CylinderPanel(), BorderLayout.CENTER);
    }
    
    class CylinderPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setColor(Color.BLACK);
            g2d.translate(getWidth() / 2, getHeight() / 2);
            
            int hSize = getHeight() / 4;
            int wSize = getWidth() / 4;
            
            Shape shape1 = new Rectangle2D.Double(- wSize, - hSize, wSize * 2, hSize * 2);
            Shape shape2 = new Ellipse2D.Double(- wSize, - hSize - (wSize / 4), wSize * 2, wSize / 2);
            Shape shape3 = new Ellipse2D.Double(- wSize, hSize - (wSize / 4), wSize * 2, wSize / 2);

            g2d.fill(shape3);
            g2d.fill(shape1);
            
            g2d.setColor(g2d.getBackground());
            g2d.fill(shape2);
            g2d.setColor(Color.BLACK);
            g2d.draw(shape2);

        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise14();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setSize(600, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
