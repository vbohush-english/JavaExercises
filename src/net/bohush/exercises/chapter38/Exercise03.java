package net.bohush.exercises.chapter38;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Exercise03 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JButton jButton1 = new JButton1();
	private JButton jButton2 = new JButton2();
	private JButton jButton3 = new JButton3();
	private JButton jButton4 = new JButton4();
	private DrawPanel drawPanel = new DrawPanel();
	private Color color = new Color(0, 255, 255);
	private Color usedColor = new Color(255, 0, 128);
	
	public Exercise03() {
		setLayout(new BorderLayout(5, 5));
		JToolBar jToolBar1 = new JToolBar("My Tool Bar");
		
		jToolBar1.setBackground(color);
		jToolBar1.setFloatable(false);
		jButton1.setMinimumSize(new Dimension(30, 30));
		jButton1.setBackground(color);
		jButton2.setBackground(color);
		jButton3.setBackground(color);
		jButton4.setBackground(color);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setMode(DrawPanel.LINE);
				jButton1.setBackground(usedColor);
				jButton2.setBackground(color);
				jButton3.setBackground(color);
				jButton4.setBackground(color);
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setMode(DrawPanel.RECT);
				jButton1.setBackground(color);
				jButton2.setBackground(usedColor);
				jButton3.setBackground(color);
				jButton4.setBackground(color);
			}
		});
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setMode(DrawPanel.OVAL);
				jButton1.setBackground(color);
				jButton2.setBackground(color);
				jButton3.setBackground(usedColor);
				jButton4.setBackground(color);
			}
		});
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				drawPanel.setMode(DrawPanel.TEXT);
				jButton1.setBackground(color);
				jButton2.setBackground(color);
				jButton3.setBackground(color);
				jButton4.setBackground(usedColor);
			}
		});
		jToolBar1.add(jButton1);
		jToolBar1.add(jButton2);
		jToolBar1.add(jButton3);
		jToolBar1.add(jButton4);


		jButton1.setBorderPainted(false);
		jButton2.setBorderPainted(false);
		jButton3.setBorderPainted(false);
		jButton4.setBorderPainted(false);
		
		add(jToolBar1, BorderLayout.NORTH);
		add(drawPanel, BorderLayout.CENTER);
	}
	
	class DrawPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		public final static int NONE = 0;
		public final static int LINE = 1;
		public final static int RECT = 2;
		public final static int OVAL = 3;
		public final static int TEXT = 4;
		
		private int mode = NONE;
		private int startX = -1;
		private int startY = -1;
		private int finX = -1;
		private int finY = -1;
		private String text = "";
		
		public DrawPanel() {
			addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					repaint();
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					startX = e.getX();
					startY = e.getY();	
					finX = e.getX();
					finY = e.getY();
					if(mode == TEXT) {
						text = "";
						DrawPanel.this.requestFocus();	
					}					
					repaint();
					
				}				
			});
			
			addMouseMotionListener(new MouseMotionAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					finX = e.getX();
					finY = e.getY();
					repaint();
				}
			});
			
			addKeyListener(new KeyAdapter() {
				
				@Override
				public void keyReleased(KeyEvent e) {
					if(mode == TEXT) {
						text += e.getKeyChar();
						repaint();
					}
				}

			});
		}
		
		public void setMode(int mode) {
			if((mode < NONE) || (mode > TEXT)) {
				throw new IllegalArgumentException();
			}
			this.mode = mode;
		}
		
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
				switch (mode) {
				case LINE:
					g.drawLine(startX, startY, finX, finY);
					break;
				case RECT:
					if((startX <= finX)&&(startY <= finY)) {
						g.drawRect(startX, startY, finX - startX, finY - startY);	
					} else if((startX >= finX)&&(startY >= finY)) {
						g.drawRect(finX, finY, startX - finX, startY - finY);	
					} else if((startX > finX)&&(startY < finY)) {
						g.drawRect(finX, startY, startX - finX, finY - startY);	
					} else if((startX < finX)&&(startY > finY)) {
						g.drawRect(startX, finY, finX - startX, startY - finY);	
					}					
					break;
				case OVAL:
					if((startX <= finX)&&(startY <= finY)) {
						g.drawOval(startX, startY, finX - startX, finY - startY);	
					} else if((startX >= finX)&&(startY >= finY)) {
						g.drawOval(finX, finY, startX - finX, startY - finY);	
					} else if((startX > finX)&&(startY < finY)) {
						g.drawOval(finX, startY, startX - finX, finY - startY);	
					} else if((startX < finX)&&(startY > finY)) {
						g.drawOval(startX, finY, finX - startX, startY - finY);	
					}	
					break;
				case TEXT:
					g.fillOval(startX - 2, startY - 2, 4, 4);
					g.drawString(text, startX + 2, startY - 2);
					break;
				default:
					break;
				}
		}
	}
	
	class JButton1 extends JButton {
		private static final long serialVersionUID = 1L;
		
		public JButton1() {
			super("    ");
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(3, getHeight() - 3, getWidth() - 3, 3);
		}
		
	}
	
	class JButton2 extends JButton {
		private static final long serialVersionUID = 1L;
		
		public JButton2() {
			super("    ");
		}
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawRect(3, 3, getWidth() - 6, getHeight() - 6);
		}
		
	}
	
	class JButton3 extends JButton {
		private static final long serialVersionUID = 1L;
		
		public JButton3() {
			super("    ");
		}
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawOval(3, 3, getWidth() - 6, getHeight() - 6);
		}		
	}
	
	class JButton4 extends JButton {
		private static final long serialVersionUID = 1L;
		
		public JButton4() {
			super("    ");
		}
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 28));
			g.setColor(Color.BLACK);
			g.drawString("A", 3, getHeight() - 3);
		}		
	}
	public static void main(String[] args) {
		Exercise03 applet = new Exercise03();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise03");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 320);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

