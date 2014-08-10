package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise16 extends JFrame{

    public Exercise16() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new CylinderPanel(), BorderLayout.CENTER);
    }
    
    class CylinderPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            Area area1 = new Area(new Ellipse2D.Double(10, 10, getWidth() - 20, getHeight() - 20));
            Area area2 = new Area(new Ellipse2D.Double(getWidth() / 5, getHeight() / 3, getWidth() / 5, getHeight() / 6));
            Area area3 = new Area(new Ellipse2D.Double((getWidth() / 5) * 3, getHeight() / 3, getWidth() / 5, getHeight() / 6));
            
            area1.subtract(area2);
            area1.subtract(area3);
            g2d.fill(area1);
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise16();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
