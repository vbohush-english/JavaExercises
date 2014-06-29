package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise15 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise15() {
		Grade[] grades = new Grade[4];
		grades[0] = new Grade("Projects", 20, Color.RED);
		grades[1] = new Grade("Quizzes", 10, Color.BLUE);
		grades[2] = new Grade("Midterm", 30, Color.GREEN);
		grades[3] = new Grade("Final", 40, Color.ORANGE);
		add(new DrawPieChart(grades));
	}
	
	public static void main(String[] args) {
		Exercise15 frame = new Exercise15();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise15");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawPieChart extends JPanel {
	private Grade[] grades;
	
	public DrawPieChart(Grade[] grades) {
		this.grades = grades;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int minLen = (width < height ? width : height);
		int startAngle = 0;
		for (int i = 0; i < grades.length; i++) {
			g.setColor(grades[i].getColor());			
			g.fillArc((width - minLen) / 2 + 5, (height - minLen) / 2 + 5, minLen - 10, minLen - 10, startAngle, (int)((360 / 100.0) * grades[i].getGrade()));
			startAngle = startAngle + (int)((360 / 100.0) * grades[i].getGrade());
			g.setColor(Color.BLACK);
			g.drawString(grades[i].getName() + " -- " + grades[i].getGrade(), 10, 15 + i * 15);
		}
		
		
	}

}

