package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise02 extends JFrame{

    public Exercise02() throws HeadlessException {
        setLayout(new BorderLayout());
        RectanglePanel rectanglePanel = new RectanglePanel();        
        add(rectanglePanel, BorderLayout.CENTER);
        rectanglePanel.setFocusable(true);
        rectanglePanel.requestFocus();
    }
    
    class RectanglePanel extends JPanel {

        private int x = -100;
        private int y = -100;
        private int size = 20;
        private Ellipse2D ellipse = new Ellipse2D.Double(x, y, size, size);
        private Rectangle2D ri = new Rectangle(200, 100, 100, 150);
        
        public RectanglePanel() {
            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    x = e.getX() - size;
                    y = e.getY() - size;
                    repaint();
                }                
            });            
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        size += 10;
                        x -= 10;
                        y -= 10;
                        repaint();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (size > 10) {
                            size -= 10;
                            x += 10;
                            y += 10;
                        }
                        repaint();
                    }
                }
            });
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;
            g2d.draw(ri);
            ellipse.setFrame(x, y, size, size);
            g2d.draw(ellipse);
            if (ellipse.contains(ri)) {
                g2d.drawString("The circle contains the rectanlge", x, y);
            } else if (ellipse.intersects(ri)) {
                g2d.drawString("The circle intersects the rectanlge", x, y);
            } else {
                g2d.drawString("The circle is outside the rectanlge", x, y);
            }
            
        }
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise02();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
