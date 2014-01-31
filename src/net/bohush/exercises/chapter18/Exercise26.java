package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise26 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JButton jButton1 = new JButton("Start");
	private JButton jButton2 = new JButton("Stop");
	private JTextField jTextField = new JTextField();
	private JLabel jLabel1 = new JLabel("Correct count will be shown");
	private JLabel jLabel2 = new JLabel("Time spent will be shown");
	private JRadioButton jRadioButton1 = new JRadioButton("Add", true);
	private JRadioButton jRadioButton2 = new JRadioButton("Subtract", false);
	private JRadioButton jRadioButton3 = new JRadioButton("Multiply", false);
	private JRadioButton jRadioButton4 = new JRadioButton("Divide", false);
	private JRadioButton jRadioButton5 = new JRadioButton("Numbers from 0 to 5", true);
	private JRadioButton jRadioButton6 = new JRadioButton("Numbers from 3 to 9", false);
	private JRadioButton jRadioButton7 = new JRadioButton("Numbers from 0 to 20", false);
	private JRadioButton jRadioButton8 = new JRadioButton("Any two digits", false);
	private JLabel jLabel3 = new JLabel("Question will be shown ");
	private Timer timer;
	private int seconds;
	private int correctCount;
	private int result;
	
	public Exercise26() {
		setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel(new GridLayout(1, 2, 5, 5));
		
		JPanel panel2 = new JPanel(new GridLayout(4, 1, 5, 5));
		panel2.setBorder(new TitledBorder("Choose a type"));
		panel2.add(jRadioButton1);
		panel2.add(jRadioButton2);
		panel2.add(jRadioButton3);
		panel2.add(jRadioButton4);
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(jRadioButton1);
		buttonGroup1.add(jRadioButton2);
		buttonGroup1.add(jRadioButton3);
		buttonGroup1.add(jRadioButton4);	
		
		JPanel panel3 = new JPanel(new GridLayout(4, 1, 5, 5));
		panel3.setBorder(new TitledBorder("Choose a level"));
		panel3.add(jRadioButton5);
		panel3.add(jRadioButton6);
		panel3.add(jRadioButton7);
		panel3.add(jRadioButton8);
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(jRadioButton5);
		buttonGroup2.add(jRadioButton6);
		buttonGroup2.add(jRadioButton7);
		buttonGroup2.add(jRadioButton8);
		
		panel1.add(panel2);
		panel1.add(panel3);
		
		add(panel1, BorderLayout.NORTH);
		
		
		JPanel panel4 = new JPanel(new GridLayout(1, 2, 5, 5));
		
		JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel5.setBorder(new TitledBorder("Correct Count"));
		panel5.add(jLabel1);
		JPanel panel6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		panel6.setBorder(new TitledBorder("Time Spent"));
		panel6.add(jLabel2);
		
		panel4.add(panel5);
		panel4.add(panel6);
		
		add(panel4, BorderLayout.SOUTH);		
		
		
		JPanel panel7 = new JPanel(new GridLayout(2, 2, 5, 5));
		panel7.add(new JLabel("Question:"));
		panel7.add(new JLabel("Answer:"));
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		panel7.add(jLabel3);
		jTextField.setEditable(false);
		panel7.add(jTextField);


		JPanel panel8 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		jButton2.setEnabled(false);
		panel8.add(jButton1);
		panel8.add(jButton2);
		
		JPanel panel9 = new JPanel(new BorderLayout());
		panel9.add(panel7, BorderLayout.NORTH);
		panel9.add(panel8, BorderLayout.SOUTH);
		add(panel9, BorderLayout.CENTER);
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(false);
				jButton2.setEnabled(true);
				jTextField.setEditable(true);
				jTextField.requestFocus();
				seconds = 0;
				jLabel2.setText(seconds + " seconds");
				timer.start();
				correctCount = 0;
				jLabel1.setText(correctCount + "");
				askQuestion();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jButton1.setEnabled(true);
				jButton2.setEnabled(false);
				jTextField.setEditable(false);
				timer.stop();
				jTextField.setText("");
			}
		});
		
		jTextField.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int userResult = Integer.parseInt(jTextField.getText());
					if (userResult == result) {
						correctCount++;
						jLabel1.setText(correctCount + "");						
					}
				} catch (NumberFormatException e2) {

				}
				askQuestion();
				jTextField.setText("");
			}
		});
		timer = new Timer(1000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				seconds++;
				jLabel2.setText(seconds + " seconds");
			}
		});
	}
	
	private void askQuestion() {
		int number1 = 0;
		int number2 = 0;
		if (jRadioButton5.isSelected()) {
			number1 = (int) (Math.random() * 5);
			number2 = (int) (Math.random() * 5);
		} else if (jRadioButton6.isSelected()) {
			number1 = 3 + (int) (Math.random() * 6);
			number2 = 3 + (int) (Math.random() * 6);
		} else if (jRadioButton7.isSelected()) {
			number1 = (int) (Math.random() * 20);
			number2 = (int) (Math.random() * 20);
		} else if (jRadioButton8.isSelected()) {
			number1 = (int) (Math.random() * 10);
			number2 = (int) (Math.random() * 10);
		}
		if (number1 < number2) {
			int temp = number1;
			number1 = number2;
			number2 = temp;
		}
		char operation = ' ';
		if (jRadioButton1.isSelected()) {
			result = number1 + number2;
			operation = '+';
		} else if (jRadioButton2.isSelected()) {
			result = number1 - number2;
			operation = '-';
		} else if (jRadioButton3.isSelected()) {
			result = number1 * number2;
			operation = '*';
		} else if (jRadioButton4.isSelected()) {
			if (number2 == 0) {
				number2 = 1;
			}
			result = number1 / number2;
			operation = '/';
		}
		
		jLabel3.setText(number1 + " " + operation + " " + number2 + " = ");
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise26());
		frame.setTitle("Exercise26");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
