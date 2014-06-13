package net.bohush.exercises.chapter38;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise07 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField jtfFileName = new JTextField(30);
	private JTextArea jtaNote = new JTextArea("", 10, 20);
	
	public Exercise07() {

		//control panel
		setLayout(new BorderLayout(5,  5));
		JPanel choseFilePanel = new JPanel(new BorderLayout(5,  5));
		JLabel jlblFileName = new JLabel("Filename");
		JButton jbtBrowse = new JButton("Browse");
		jbtBrowse.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					jtfFileName.setText(fileChooser.getSelectedFile().getAbsolutePath());
					loadFile();
				}	
			}
		});

		jbtBrowse.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		jtfFileName.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFile();
			}
		});
		
		choseFilePanel.add(jlblFileName, BorderLayout.WEST);
		choseFilePanel.add(jtfFileName, BorderLayout.CENTER);
		choseFilePanel.add(jbtBrowse, BorderLayout.EAST);
		add(choseFilePanel, BorderLayout.SOUTH);
		
		//view file panel
		jtaNote.setWrapStyleWord(true);
		JScrollPane viewFilePanel = new JScrollPane(jtaNote);
		add(viewFilePanel, BorderLayout.CENTER);
	}
	
	private void loadFile() {
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
		}
	}

	public static void main(String[] args) {
		Exercise07 frame = new Exercise07();
		frame.setTitle("Exercise07");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
