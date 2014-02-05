package net.bohush.exercises.chapter19;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class Exercise11 extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton button1 = new JButton("Browse");
	private JButton button2 = new JButton("Start");
	private JTextField jTextField1 = new JTextField("temp.txt");
	private JTextField jTextField2 = new JTextField("3");
	
	public Exercise11() {
		setLayout(new GridLayout(4, 1, 5, 5));
		JTextArea jTextArea = new JTextArea("If you split a file named temp.txt into 3 smaller files,"
				+ " the\nthree smalles files are temp.txt.1, temp.txt.2, amd temp.txt.3");
		jTextArea.setEditable(false);
		add(jTextArea);
		
		JPanel jPanel1 = new JPanel(new GridLayout(1, 3, 5, 5));
		jPanel1.add(new JLabel("Enter or choose a file:"));
		jPanel1.add(jTextField1);
		jPanel1.add(button1);
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

				File sourceFile = new File(jTextField1.getText());
				if (!sourceFile.exists()) {
					JOptionPane.showMessageDialog(null, "Source file \"" + jTextField1.getText() + "\" does not exist", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				button1.setEnabled(false);
				button2.setEnabled(false);
				jTextField1.setEnabled(false);
				jTextField2.setEnabled(false);
				
				try {
					splitFiles(sourceFile, jTextField1.getText(), numberOfPieces);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				button1.setEnabled(true);
				button2.setEnabled(true);
				jTextField1.setEnabled(true);
				jTextField2.setEnabled(true);
			}
		});
		
		button1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					jTextField1.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
	}
	public static void main(String[] args) throws IOException {
		Exercise11 frame = new Exercise11();
		frame.setTitle("Exercise11");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void splitFiles(File sourceFile, String sourceFileName, int numberOfPieces) throws IOException {
		
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile), 1024 * 1024);
		
		int inputFileSize = input.available();
		int fileSize = inputFileSize / numberOfPieces + 1;
		int lastFileSize = inputFileSize - (fileSize * (numberOfPieces - 1));
		
		for (int i = 1; i < numberOfPieces; i++) {
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(sourceFileName + "." + i), 1024 * 1024);
			for (int j = 0; j < fileSize; j++) {
				output.write((byte) (input.read()));				
			}
			output.close();
		}
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(sourceFileName + "." + numberOfPieces), 1024 * 1024);
		for (int j = 0; j < lastFileSize; j++) {
			output.write((byte) (input.read()));
		}
		output.close();
		input.close();
		JOptionPane.showMessageDialog(null, inputFileSize + " bytes copied in " + numberOfPieces + " files", "Exercise11", JOptionPane.INFORMATION_MESSAGE);

	}
}
