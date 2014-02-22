package net.bohush.exercises.chapter22;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Exercise02 extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jTextField = new JTextField(10);
	private LinkedList<Long> numbers = new LinkedList<>();
	private JTextArea jTextArea = new JTextArea(10, 10);
	
	public Exercise02() {
		JPanel jPanel1 = new JPanel();
		JButton jButton1 = new JButton("Sort");
		JButton jButton2 = new JButton("Shuffle");
		JButton jButton3 = new JButton("Reverse");
		jPanel1.add(jButton1);
		jPanel1.add(jButton2);
		jPanel1.add(jButton3);
		add(jPanel1, BorderLayout.SOUTH);
		
		JPanel jPanel2 = new JPanel();
		jPanel2.add(new JLabel("Enter a number: "));
		jPanel2.add(jTextField);
		add(jPanel2, BorderLayout.NORTH);
		
		jTextArea.setEditable(false);
		jTextArea.setBorder(new LineBorder(Color.BLACK));
		jTextArea.setLineWrap(true);
		jTextArea.setWrapStyleWord(true);
		add(jTextArea, BorderLayout.CENTER);
		
		jTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					long number = Long.parseLong(jTextField.getText());
					numbers.add(number);
					updateNumbers();	
					jTextField.setText("");
				} catch (NumberFormatException e2) {					
				}
			}
		});
		
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.sort(numbers);
				updateNumbers();
			}
		});
		
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.shuffle(numbers);
				updateNumbers();
			}
		});
		
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Collections.reverse(numbers);
				updateNumbers();
			}
		});
	}
	
	private void updateNumbers() {
		StringBuilder text = new StringBuilder();
		for (Long number : numbers) {
			text.append(number + " ");
		}
		jTextArea.setText(text.toString());		
	}
	
	public static void main(String[] args) {
		Exercise02 frame = new Exercise02();
		frame.setTitle("Exercise02");
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
