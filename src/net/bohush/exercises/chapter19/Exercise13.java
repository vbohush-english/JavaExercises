package net.bohush.exercises.chapter19;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Exercise13 extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton button2 = new JButton("Start");
	private JTextField jTextField1 = new JTextField("temp.txt");
	private JTextField jTextField2 = new JTextField("3");
	
	public Exercise13() {
		setLayout(new GridLayout(4, 1, 5, 5));
		JTextArea jTextArea = new JTextArea("If the base file is named temp.txt with three pieces, temp.txt.1,\n"
											+ "temp.txt.2, amd temp.txt.3 are combined into temp.txt");
		jTextArea.setEditable(false);
		add(jTextArea);
		
		JPanel jPanel1 = new JPanel(new GridLayout(1, 3, 5, 5));
		jPanel1.add(new JLabel("Enter a base file:"));
		jPanel1.add(jTextField1);

		add(jPanel1);
		
		JPanel jPanel2 = new JPanel(new GridLayout(1, 3, 5, 5));
		jPanel2.add(new JLabel("Specify the number of smaller files: "));
		jPanel2.add(jTextField2);
		add(jPanel2);
		
		add(button2);
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int numberOfPieces = 0;
				try {
					numberOfPieces = Integer.parseInt(jTextField2.getText());	
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "\"" + jTextField2.getText() + "\" is not correct number of files", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				File targetFile = new File(jTextField1.getText());
				if (targetFile.exists()) {
					JOptionPane.showMessageDialog(null, "Target file \"" + jTextField1.getText() + "\" exists", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				button2.setEnabled(false);
				jTextField1.setEnabled(false);
				jTextField2.setEnabled(false);
				
				try {
					margeFiles(targetFile, jTextField1.getText(), numberOfPieces);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				button2.setEnabled(true);
				jTextField1.setEnabled(true);
				jTextField2.setEnabled(true);
			}
		});
	}
	public static void main(String[] args) throws IOException {
		Exercise13 frame = new Exercise13();
		frame.setTitle("Exercise13");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void margeFiles(File targetFile, String sourceFileNames, int numberOfPieces) throws IOException {

		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(targetFile), 1024 * 1024);
		
		for (int i = 1; i <= numberOfPieces; i++) {
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFileNames + "." + i), 1024 * 1024);
		    int r = 0;
		    while ((r = input.read()) != -1) {
		      output.write((byte)r);
		    }
		    input.close();
		}
		output.close();
		JOptionPane.showMessageDialog(null, numberOfPieces + " files marged to file \"" + sourceFileNames + "\"", "Exercise13", JOptionPane.INFORMATION_MESSAGE);
		
	}
}
