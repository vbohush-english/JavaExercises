package net.bohush.exercises.chapter38;

import javax.swing.*;

import java.awt.*;

public class Exercise03 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JButton jButton1 = new JButton(new ImageIcon(getClass().getResource("image/1.gif")));
	private JButton jButton2 = new JButton(new ImageIcon(getClass().getResource("image/2.gif")));
	private JButton jButton3 = new JButton(new ImageIcon(getClass().getResource("image/3.gif")));
	private JButton jButton4 = new JButton("A");
	private DrawPanel drawPanel = new DrawPanel();
	public Exercise03() {
		setLayout(new BorderLayout(5, 5));
		JToolBar jToolBar1 = new JToolBar("My Tool Bar");
		Color color = new Color(0, 255, 255);
		jToolBar1.setBackground(color);
		jToolBar1.setFloatable(false);
		jButton1.setBackground(color);
		jButton2.setBackground(color);
		jButton3.setBackground(color);
		jButton4.setBackground(color);
		jButton4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
		jButton4.setForeground(Color.BLACK);
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
		
		public void setMode(int mode) {
			if((mode < NONE) || (mode > TEXT)) {
				throw new IllegalArgumentException();
			}
			this.mode = mode;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
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

