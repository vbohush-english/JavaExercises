package net.bohush.exercises.chapter49;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class Exercise17 extends JFrame {

    private static final long serialVersionUID = 1L;

    public Exercise17(Polygon points) {
        add(new CheckPoligon(points));
    }

    public static void main(String[] args) {

        if ((args.length == 0) || (args.length / 2 == 1)) {
            JOptionPane.showMessageDialog(null, "Execute: java Exercise27 x1 y1 x2 y2 x3 y3 . . .", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        Polygon points = new Polygon();
        for (int i = 0; i < args.length; i += 2) {
            points.addPoint(Integer.parseInt(args[i]), Integer.parseInt(args[i + 1]));
        }

        JFrame frame = new Exercise17(points);
        frame.setSize(250, 150);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setTitle(frame.getClass().getName());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

class CheckPoligon extends JPanel {

    private Polygon p;
    private int diametr = 10;
    
    private boolean isMove = false;
    private int movePoint = 0;
    private int dX = 0;
    private int dY = 0;
    
    public CheckPoligon(Polygon p) {
        this.p = p;
        
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < CheckPoligon.this.p.npoints; i++) {
                    if(getDistance(e.getX(), e.getY(), CheckPoligon.this.p.xpoints[i], CheckPoligon.this.p.ypoints[i]) < diametr) {
                        isMove = true;
                        movePoint = i;
                        dX = e.getX() - CheckPoligon.this.p.xpoints[i];
                        dY = e.getY() - CheckPoligon.this.p.ypoints[i];
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMove = false;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMove = false;
            }
                        
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if(isMove) {
                    CheckPoligon.this.p.xpoints[movePoint] = e.getX() - dX;
                    CheckPoligon.this.p.ypoints[movePoint] = e.getY() - dY;
                    CheckPoligon.this.p.invalidate();
                    repaint();
                }
            }
        });
    }

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawPolygon(p);
        for (int i = 0; i < p.npoints; i++) {
            g.fillOval(p.xpoints[i] - diametr / 2, p.ypoints[i] - diametr / 2, diametr, diametr);
            g.drawString("(" + p.xpoints[i] + ", " + p.ypoints[i] + ")", p.xpoints[i] - diametr / 2, p.ypoints[i] - diametr / 2);
        }

        int minX = p.xpoints[0];
        int maxX = p.xpoints[0];
        int minY = p.ypoints[0];
        int maxY = p.ypoints[0];

        
        for (int i = 0; i < p.npoints; i++) {
            if (minX > p.xpoints[i]) {
                minX = p.xpoints[i];
            }
            if (minY > p.ypoints[i]) {
                minY = p.ypoints[i];
            }
            if (maxX < p.xpoints[i]) {
                maxX = p.xpoints[i];
            }
            if (maxY < p.ypoints[i]) {
                maxY = p.ypoints[i];
            }
        }    
        
        int strategicX = p.xpoints[0];
        int strategicY = p.ypoints[0];
        double minDistance = getTotalDistance(strategicX, strategicY);
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (p.contains(i, j) && (minDistance > getTotalDistance(i, j))) {
                    minDistance = getTotalDistance(i, j);
                    strategicX = i;
                    strategicY = j;
                }
            }
        }
        g.setColor(Color.RED);
        g.drawOval(strategicX - diametr / 2, strategicY - diametr / 2, diametr, diametr);
        g.drawString("(" + strategicX + ", " + strategicY + ")", strategicX - diametr / 2, strategicY - diametr / 2);
    }

    public double getTotalDistance(int x, int y) {
        double result = 0;
        for (int i = 0; i < p.npoints; i++) {
            result += getDistance(p.xpoints[i], p.ypoints[i], x, y);
        }
        return result;
    }   
    
    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
    
}
