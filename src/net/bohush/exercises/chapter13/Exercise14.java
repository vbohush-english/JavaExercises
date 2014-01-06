package net.bohush.exercises.chapter13;

import java.awt.*;

import javax.swing.*;

public class Exercise14 extends JFrame{

	private static final long serialVersionUID = 1L;

	public Exercise14() {
		Grade[] grades = new Grade[4];
		grades[0] = new Grade("Projects", 20, Color.RED);
		grades[1] = new Grade("Quizzes", 10, Color.BLUE);
		grades[2] = new Grade("Midterm", 30, Color.GREEN);
		grades[3] = new Grade("Final", 40, Color.ORANGE);
		add(new DrawBarChart(grades));
	}
	
	public static void main(String[] args) {
		Exercise14 frame = new Exercise14();
		frame.setSize(400, 400);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setTitle("Exercise14");
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class DrawBarChart extends JPanel {
	private Grade[] grades;
	
	public DrawBarChart(Grade[] grades) {
		this.grades = grades;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int step = width / grades.length;
		for (int i = 0; i < grades.length; i++) {
			g.setColor(grades[i].getColor());
			g.fillRect(step * i, height - (int)((height / 100.0) * grades[i].getGrade()), step, (int)((height / 100.0) * grades[i].getGrade()));
			g.setColor(Color.BLACK);
			g.drawString(grades[i].getName() + " -- " + grades[i].getGrade(), step * i, height - (int)((height / 100.0) * grades[i].getGrade()));
		}
	}

}

class Grade {
	private String name;
	private Color color;
	private int grade;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public Grade(String name, int grade, Color color) {
		super();
		this.name = name;
		this.color = color;
		this.grade = grade;
	}
	
}