package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise15 extends JFrame{

    public Exercise15() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new CylinderPanel(), BorderLayout.CENTER);
    }
    
    class CylinderPanel extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            
            
            g2d.translate(50, 0);
            Path2D path = new Path2D.Double();
            path.moveTo(100, 100);
            path.curveTo(50, 150, 150, 250, 100, 300);
            path.moveTo(100, 100);
            path.curveTo(150, 150, 50, 250, 100, 300);
            g2d.draw(path);
            g2d.draw(new Ellipse2D.Double(0, 100, 200, 200));

            g2d.translate(250, 0);       
            
            Area area1 = new Area(path);
            Area area2 = new Area(new Ellipse2D.Double(0, 100, 200, 200));
            
            area2.subtract(area1);
            g2d.fill(area2);
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise15();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
