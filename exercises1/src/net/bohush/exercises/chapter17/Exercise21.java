package net.bohush.exercises.chapter17;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

import javax.swing.*;

public class Exercise21 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtfFileName = new JTextField(30);
	private JTextArea jtaNote = new JTextArea("", 10, 20);
	private JLabel jlblStatus = new JLabel(" ");

	public Exercise21() {
		// control panel
		JPanel choseFilePanel = new JPanel();
		JLabel jlblFileName = new JLabel("Filename");
		JButton jbtView = new JButton("View");
		jbtView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtfFileName.getText().equals("")) {
					jlblStatus.setText("Please enter filename");
					return;
				}

				Scanner input;
				try {
					if (jtfFileName.getText().startsWith("http://")) {
						input = new Scanner(new java.net.URL(jtfFileName.getText()).openStream());
					} else {
						input = new Scanner(new File(jtfFileName.getText()));
					}
					StringBuilder data = new StringBuilder("");
					while (input.hasNextLine()) {
						data.append(input.nextLine());
						data.append("\n");
					}
					jtaNote.setText(data.toString());
					input.close();
					jlblStatus.setText("File loaded succesfully");
				} catch (FileNotFoundException e1) {
					jlblStatus.setText("File \"" + jtfFileName.getText() + "\" not found!");
				} catch (MalformedURLException e1) {
					jlblStatus.setText("MalformedURLException!");
				} catch (IOException e1) {
					jlblStatus.setText("IOException!");
				}
			}
		});
		choseFilePanel.add(jlblFileName);
		choseFilePanel.add(jtfFileName);
		choseFilePanel.add(jbtView);
		add(choseFilePanel, BorderLayout.NORTH);

		// view file panel
		jtaNote.setWrapStyleWord(true);
		JScrollPane viewFilePanel = new JScrollPane(jtaNote);
		add(viewFilePanel, BorderLayout.CENTER);

		// status
		add(jlblStatus, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		Exercise21 frame = new Exercise21();
		frame.setTitle("Exercise21");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
