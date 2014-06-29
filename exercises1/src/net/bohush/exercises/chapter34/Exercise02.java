package net.bohush.exercises.chapter34;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.util.ArrayList;
import java.awt.*;

public class Exercise02 extends JApplet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Students> list = new ArrayList<>();
	
	public void init() {
		initializeDB();
		JPanel jPanel1 = new JPanel(new GridLayout(1, 2, 5, 5));
		jPanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		jPanel1.add(new DrawPieChart());
		jPanel1.add(new DrawBarChart());
		setLayout(new BorderLayout());
		add(jPanel1);

	}

	private void initializeDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
			Statement statement = connection.createStatement();
			ResultSet rset = statement.executeQuery("select deptId, count(*) from Student where deptId is not null group by deptId;");
			while(rset.next()) {
				list.add(new Students(rset.getString(1), Integer.parseInt(rset.getString(2))));
			}
			System.out.println(list);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	class DrawPieChart extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			int minLen = (width < height ? width : height);
			int startAngle = 0;
			double sum = 0;
			for (int i = 0; i < list.size(); i++) {
				sum += list.get(i).getCount();
			}
			
			for (int i = 0; i < list.size(); i++) {
				
				System.out.println(startAngle);
				g.setColor(list.get(i).getColor());
				g.fillArc((width - minLen) / 2 + 10, (height - minLen) / 2 + 10, minLen - 20, minLen - 20, startAngle, (int)Math.ceil((360 / sum) * list.get(i).getCount()));
				double textAngle = Math.toRadians(360 - (startAngle + startAngle + (int)Math.ceil((360 / sum) * list.get(i).getCount())) / 2);
				startAngle = startAngle + (int)Math.ceil((360 / sum) * list.get(i).getCount());
				g.setColor(Color.BLACK);

				int x2 = (int)((width / 2) + Math.cos(textAngle) * (minLen / 2 - 15));
				int y2 = (int)((height / 2) + Math.sin(textAngle) * (minLen / 2 - 15));						
				g.drawString(list.get(i).getName(), x2, y2);
				
			}
			
			
		}

	}
	
	class DrawBarChart extends JPanel {

		private static final long serialVersionUID = 1L;

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(300, 300);
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int width = getWidth();
			int height = getHeight();
			int step = width / list.size();
			int widthStep = step - 10;
			int heightStep = list.get(0).getCount();
			for (int i = 1; i < list.size(); i++) {
				if(list.get(i).getCount() > heightStep) {
					heightStep = list.get(i).getCount();
				}
			}
			heightStep = (int)((height - 20.0) / heightStep);
			for (int i = 0; i < list.size(); i++) {
				g.setColor(list.get(i).getColor());
				g.fillRect(5 + step * i, height - (heightStep * list.get(i).getCount()) - 5, widthStep, (heightStep * list.get(i).getCount()) - 5);
				g.setColor(Color.BLACK);
				g.drawString(list.get(i).getName(), 5 + step * i, height - (heightStep * list.get(i).getCount()) - 10);
			}
			g.drawLine(0, height - 10, getWidth(), height - 10);
		}

	}

	class Students {
		private String name;
		private int count;
		private Color color;
		
		public Students(String name, int count) {
			this.name = name;
			this.count = count;
			this.color = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		public String toString() {
			return name + ":" + count;
		}
		
	}
	
	/** Main method */
	public static void main(String[] args) {
		Exercise02 applet = new Exercise02();
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Exercise02");
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}