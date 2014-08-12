package net.bohush.exercises.chapter15;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Exercise20 extends JFrame {

    private static final long serialVersionUID = 1L;

    public Exercise20() {
        add(new MandelbrotCanvas());
    }

    public static void main(String[] args) {
        Exercise20 frame = new Exercise20();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Exercise20");
        frame.setVisible(true);
    }

}

class MandelbrotCanvas extends JPanel {

    private static final long serialVersionUID = 1L;
    final static int COUNT_LIMIT = 60;
    private final int COLOR_R = 77;
    private final int COLOR_G = 58;
    private final int COLOR_B = 159;
        
    @Override
    /**
     * Paint a Mandelbrot image
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (double x = -2.0; x < 2.0; x += 0.005) {
            for (double y = -2.0; y < 2.0; y += 0.005) {
                int c = count(new Complex(x, y));
                if (c == COUNT_LIMIT) {
                    g.setColor(Color.BLACK); // c is in a Mandelbrot set
                } else {
                    g.setColor(new Color(c * COLOR_R % 256, c * COLOR_G % 256, c * COLOR_B % 256));
                }
                g.drawRect((int) (x * 200) + 400, (int) (y * 200) + 400, 1, 1); // Fill a tiny rectangle with the specified color
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 800);
    }
    
    /**
     * Return the iteration count
     */
    static int count(Complex c) {
        Complex z = new Complex(0, 0);
        z = z.multiply(z).add(c);
        for (int i = 0; i < COUNT_LIMIT; i++) {
            z = z.multiply(z).add(c);
            if (z.abs() > 2) {
                return i;
            }
        }
        return COUNT_LIMIT;
    }

}
