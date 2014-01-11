package net.bohush.exercises.chapter14;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Exercise21 extends JFrame {

	private static final long serialVersionUID = 1L;

	public Exercise21() {	
		String address = JOptionPane.showInputDialog(null, "Enter URL or file name:", "Input Dialog", JOptionPane.QUESTION_MESSAGE);

		String graphString = "";
		try {
			Scanner input;
			if (address.startsWith("http://")) {
				input = new Scanner(new java.net.URL(address).openStream());
			} else {
				input = new Scanner(new File(address));
			}			
			while(input.hasNextLine()) {
				graphString += input.nextLine() + '\n';
			}
			add(new Graph(graphString));
			input.close();
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(null, "Wrong URL", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		} catch (java.io.IOException e) {
			JOptionPane.showMessageDialog(null, "Error reading \"" + address + "\"", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}
		
	}

	public static void main(String[] args) {
		Exercise21 frame = new Exercise21();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);;
		frame.setTitle("Exercise21");
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 300);
		frame.setVisible(true);		
	}

}

class Graph extends JPanel {
	private String graphString;
	
	public Graph(String graphString) {
		this.graphString = graphString;
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		Scanner input = new Scanner(graphString);
		int pointsCount = input.nextInt();
		input.nextLine();
		Point[] points = new Point[pointsCount];
		
		for (int i = 0; i < pointsCount; i++) {
			Scanner tmp = new Scanner(input.nextLine());
			if(i == tmp.nextInt()) {
				points[i] = new Point(tmp.nextInt(), tmp.nextInt());
			}
			tmp.close();
		}
		input.close();
		
		for (int i = 0; i < points.length; i++) {
			int diametr = 10;
			g.fillOval(points[i].x - diametr / 2, points[i].y - diametr / 2, diametr, diametr);
			g.drawString(i + "", points[i].x - diametr, points[i].y - diametr);
		}
		
		input = new Scanner(graphString);
		input.nextLine();
		for (int i = 0; i < pointsCount; i++) {
			Scanner tmp = new Scanner(input.nextLine());
			tmp.nextInt(); tmp.nextInt(); tmp.nextInt(); 
			while(tmp.hasNext()) {
				int nextPoint = tmp.nextInt();
				g.drawLine(points[i].x, points[i].y, points[nextPoint].x, points[nextPoint].y);
			}
			tmp.close();
		}
		input.close();
	}
}