package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Exercise11Reader extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("tmp/Exercise11.txt", 10);
	private JTextField jTextField2 = new JTextField("GBK", 10);
	private JTextArea jTextArea = new JTextArea();
	
	public Exercise11Reader() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel jPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel1.add(new JLabel("Enter a file name:"));
		jPanel1.add(jTextField1);
		jPanel1.add(new JLabel("Enter the encoding scheme:"));
		jPanel1.add(jTextField2);
		JButton button = new JButton("View File");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				Scanner input;
				try {
					input = new Scanner(new File(jTextField1.getText()), jTextField2.getText());
					
					StringBuilder stringBuilder = new StringBuilder();
					while(input.hasNextLine()) {
						stringBuilder.append(input.nextLine() + "\n");
					}
					jTextArea.setText(stringBuilder.toString());
					input.close();
				} catch (FileNotFoundException | IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		
		jPanel1.add(button);
		mainPanel.add(jPanel1, BorderLayout.NORTH);
		jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise11Reader");
		frame.add(new Exercise11Reader());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}