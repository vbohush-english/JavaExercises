package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise04 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField jtfFileName = new JTextField(30);
	private JTextArea jtaNote = new JTextArea("", 10, 20);
	
	public Exercise04() {
		//control panel
		JPanel choseFilePanel = new JPanel();
		JLabel jlblFileName = new JLabel("Filename");
		JButton jbtView = new JButton("View");
		jbtView.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfFileName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter filename.", "Error", JOptionPane.INFORMATION_MESSAGE);	
					return;
				}
				File file = new File(jtfFileName.getText());
				if (!file.exists()) {
					JOptionPane.showMessageDialog(null, "File \"" + jtfFileName.getText() + "\" doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (file.isDirectory()) {
					JOptionPane.showMessageDialog(null, "\"" + jtfFileName.getText() + "\" is a directory!\nPlease enter filename.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Scanner input = new Scanner(file);
					StringBuilder data = new StringBuilder("");
					while (input.hasNextLine()) {
						data.append(input.nextLine());
						data.append("\n");
					}
					jtaNote.setText(data.toString());
					input.close();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "File \"" + jtfFileName.getText() + "\" not found!", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		choseFilePanel.add(jlblFileName);
		choseFilePanel.add(jtfFileName);
		choseFilePanel.add(jbtView);
		add(choseFilePanel, BorderLayout.SOUTH);
		
		//view file panel
		jtaNote.setWrapStyleWord(true);
		JScrollPane viewFilePanel = new JScrollPane(jtaNote);
		add(viewFilePanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Exercise04 frame = new Exercise04();
		frame.setTitle("Exercise04");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
