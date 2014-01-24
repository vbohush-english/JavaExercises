package net.bohush.exercises.chapter18;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise03 extends JFrame {
	private int totalCount = 0;
	private int correctCount = 0;
	private int correctQuestionNumber = -1;
	private static final long serialVersionUID = 1L;
	private JLabel label1 = new JLabel("0 / 0");
	private JRadioButton jRadioButton1 = new JRadioButton("State", true);
	private JRadioButton jRadioButton2 = new JRadioButton("Capital", false);
	private JRadioButton jRadioButton3 = new JRadioButton("Sequential", true);
	private JRadioButton jRadioButton4 = new JRadioButton("Random", false);
	private JLabel label2 = new JLabel(" ");
	private JTextField jTextField = new JTextField(40);
	private String[][] states = new String[][] {{"Alabama", "Montgomery"}, 
			{"Alaska", "Juneau"}, 
			{"Arizona", "Phoenix"}, 
			{"Arkansas", "Littlerock"}, 
			{"California", "Sacramento"}, 
			{"Colorado", "Denver"}, 
			{"Connecticut", "Hartford"}, 
			{"Delaware", "Dover"}, 
			{"Florida", "Tallahassee"}, 
			{"Georgia", "Atlanta"}, 
			{"Hawaii", "Honolulu"}, 
			{"Idaho", "Boise"}, 
			{"Illinois", "Springfield"}, 
			{"Indiana", "Indianapolis"}, 
			{"Iowa", "Des Moines"}, 
			{"Kansas", "Topeka"}, 
			{"Kentucky", "Frankfort"}, 
			{"Louisiana", "Baton Rouge"}, 
			{"Maine", "Augusta"}, 
			{"Maryland", "Annapolis"}, 
			{"Massachusetts", "Boston"}, 
			{"Michigan", "Lansing"}, 
			{"Minnesota", "St. Paul"}, 
			{"Mississippi", "Jackson"}, 
			{"Missouri", "Jefferson City"}, 
			{"Montana", "Helena"}, 
			{"Nebraska", "Lincoln"}, 
			{"Nevada", "Carson City"}, 
			{"New Hampshire", "Concord"}, 
			{"New Jersey", "Trenton"}, 
			{"New Mexico", "SantaFe"}, 
			{"New York", "Albany"}, 
			{"North Carolina", "Raleigh"}, 
			{"North Dakota", "Bismarck"}, 
			{"Ohio", "Columbus"}, 
			{"Oklahoma", "Oklahoma City"}, 
			{"Oregon", "Salem"}, 
			{"Pennsylvania", "Harrisburg"}, 
			{"Rhode Island", "Providence"}, 
			{"South Carolina", "Columbia"}, 
			{"South Dakota", "Pierre"}, 
			{"Tennessee", "Nashville"}, 
			{"Texas", "Austin"}, 
			{"Utah", "Salt Lake City"}, 
			{"Vermont", "Montpelier"}, 
			{"Virginia", "Richmond"}, 
			{"Washington", "Olympia"}, 
			{"WestVirginia", "Charleston"}, 
			{"Wisconsin", "Madison"}, 
			{"Wyoming", "Cheyenne"}};
	
	public static void main(String[] args) {
		Exercise03 frame = new Exercise03();
		frame.setTitle("Exercise03");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void showQuestion() {
		String question = "What is the ";
		
		if (jRadioButton3.isSelected()) {
			correctQuestionNumber++;
			if (correctQuestionNumber >= states.length) {
				correctQuestionNumber = 0;
			}
		} else {
			correctQuestionNumber = (int)(Math.random() * states.length);
		}	
		
		if (jRadioButton1.isSelected()) {
			question += " state of " + states[correctQuestionNumber][1] + "?";
		} else {
			question += " capital of " + states[correctQuestionNumber][0] + "?";
		}
		
		label2.setText(question);	
		label1.setText(correctCount + " / " + totalCount);
		jTextField.setText("");
	}


	public Exercise03() {
		JPanel panel1 = new JPanel(new GridLayout(1, 3));
		JPanel panel2 = new JPanel();
		panel2.setBorder(new TitledBorder("correct / total count"));
		panel2.add(label1);
		panel1.add(panel2);
		
		JPanel panel3 = new JPanel(new GridLayout(2, 1));
		panel3.setBorder(new TitledBorder("Guess state of capital?"));
		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(jRadioButton1);
		buttonGroup1.add(jRadioButton2);
		panel3.add(jRadioButton1);
		panel3.add(jRadioButton2);
		panel1.add(panel3);
		
		JPanel panel4 = new JPanel(new GridLayout(2, 1));
		panel4.setBorder(new TitledBorder("Order of questions?"));
		ButtonGroup buttonGroup2 = new ButtonGroup();
		buttonGroup2.add(jRadioButton3);
		buttonGroup2.add(jRadioButton4);
		panel4.add(jRadioButton3);
		panel4.add(jRadioButton4);
		panel1.add(panel4);
		jRadioButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuestion();			
			}
		});
		jRadioButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				showQuestion();			
			}
		});
		add(panel1, BorderLayout.NORTH);
		
		add(label2, BorderLayout.CENTER);
		
		JPanel panel5 = new JPanel();
		JButton jButton = new JButton("Answer");
		panel5.add(jTextField);
		panel5.add(jButton);
		add(panel5, BorderLayout.SOUTH);
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				totalCount++;
				String correctAnswer;
				if (jRadioButton1.isSelected()) {
					correctAnswer = states[correctQuestionNumber][0];
				} else {
					correctAnswer = states[correctQuestionNumber][1];
				}
				if (jTextField.getText().equals(correctAnswer)) {
					correctCount++;	
					JOptionPane.showMessageDialog(null, "Your answer is correct");
				} else {
					if (jRadioButton1.isSelected()) {
						JOptionPane.showMessageDialog(null, "The state of " + states[correctQuestionNumber][1] + " should be " + states[correctQuestionNumber][0]);						
					} else {
						JOptionPane.showMessageDialog(null, "The capital of " + states[correctQuestionNumber][0] + " should be " + states[correctQuestionNumber][1]);
					}

				}
				showQuestion();
			}
		});
		
		showQuestion();
	}

}
