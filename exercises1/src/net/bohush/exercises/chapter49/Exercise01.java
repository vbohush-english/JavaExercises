package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise01 extends JFrame{

    public Exercise01() throws HeadlessException {
        setLayout(new BorderLayout());
        add(new RectanglePanel(), BorderLayout.CENTER);
    }
    
    class RectanglePanel extends JPanel {

        private Point2D point = new Point2D.Double(-100, -100);
        private Rectangle2D ri = new Rectangle(20, 20, 100, 100);
        
        public RectanglePanel() {
            addMouseMotionListener(new MouseMotionAdapter() {

                @Override
                public void mouseMoved(MouseEvent e) {
                    point.setLocation(e.getX(), e.getY());
                    repaint();
                }
                
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.draw(ri);
            if (ri.contains(point)) {
                g2d.drawString("Inside", (int)point.getX(), (int)point.getY());
            } else {
                g2d.drawString("Outside", (int)point.getX(), (int)point.getY());
            }
            
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise01();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
