package net.bohush.exercises.chapter49;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise18 extends JFrame{

    public Exercise18() throws HeadlessException {
        setLayout(new BorderLayout());
        StopPanel stopPanel = new StopPanel();
        stopPanel.setFocusable(true);
        stopPanel.requestFocus();
        add(stopPanel, BorderLayout.CENTER);

    }
    
    class StopPanel extends JPanel {

        private int radius = 50;
        private int rotate = 0;
        
        public StopPanel() {
            addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                        if(e.getKeyCode() == KeyEvent.VK_UP) {
                            radius += 10;
                            repaint();
                        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if(radius > 10) {
                                radius -= 10;
                                repaint();                        
                            }                        
                        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                            rotate -= 9;
                            repaint();              
                        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            rotate += 9;
                            repaint();                    
                        }
                    }
                }
                
            });
        }
        
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D)g;

            int xCenter = getWidth() / 2;
            int yCenter = getHeight() / 2;

            
            g2d.translate(xCenter, yCenter);
            g2d.rotate(Math.toRadians(rotate));
            
            Polygon polygon = new Polygon();

            for (int i = 1; i <= 16; i += 2) {
                polygon.addPoint((int) (radius * Math.cos(i * 2 * (Math.PI / 16))), (int) (radius * Math.sin(i * 2 * (Math.PI / 16))));
            }

            g2d.setColor(Color.RED);
            g2d.fill(polygon);
            g2d.setColor(Color.WHITE);
            
            String text = " STOP ";
            for (int i = 2; i < 1000; i++) {
                FontMetrics fm = g.getFontMetrics(new Font("SansSerif", Font.BOLD, i));
                if(fm.stringWidth(text) > radius * 2) {
                    g.setFont(new Font("SansSerif", Font.BOLD, i - 1));
                    g.drawString("STOP", - fm.stringWidth("STOP") / 2, fm.getAscent() / 2);
                    break;
                }
            }            
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new Exercise18();
        jFrame.setTitle(jFrame.getClass().getName());
        jFrame.setMinimumSize(new Dimension(200, 200));
        jFrame.setSize(600, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    
}
