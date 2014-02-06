package net.bohush.exercises.chapter19;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;


public class Exercise21 extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField = new JTextField("tmp/Exercise19_21.java");
	private JTextArea jTextArea = new JTextArea(10, 30);
	private JButton jButton = new JButton("Save the bits to the file");
	
	public Exercise21() {
		add(jButton, BorderLayout.SOUTH);
		
		JPanel jPanel1 = new JPanel(new BorderLayout());
		jPanel1.add(new JLabel("Enter a file: "), BorderLayout.WEST);
		jPanel1.add(jTextField, BorderLayout.CENTER);
		add(jPanel1, BorderLayout.NORTH);
		
		jTextArea.setLineWrap(true);
		JScrollPane viewFilePanel = new JScrollPane(jTextArea);
		add(viewFilePanel, BorderLayout.CENTER);

		jTextField.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sourceFileName = jTextField.getText();

				File sourceFile = new File(sourceFileName);
				if (!sourceFile.exists()) {
					JOptionPane.showMessageDialog(null, "Source file\"" + sourceFileName + "\" does not exist", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					BufferedInputStream input = new BufferedInputStream(new FileInputStream(sourceFile));
					
					int r = 0;
					StringBuilder result = new StringBuilder("");
					while ((r = input.read()) != -1) {
						result.append(getHex(r));
					}
					input.close();			
					jTextArea.setText(result.toString());
					
				} catch (IOException e2) {
				}
			}
		});
		
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {				

					StringBuilder builder = new StringBuilder("");
					String text = jTextArea.getText();
					for (int i = 0; i < text.length(); i++) {
						char tmp = Character.toLowerCase(text.charAt(i));
						if (((tmp >= '0') && (tmp <= '9')) || ((tmp >= 'a') && (tmp <= 'f'))) {
							builder.append(tmp);
						}
					}
					printHex(builder.toString(), jTextField.getText());
					JOptionPane.showMessageDialog(null, "File saved", "Exercise21", JOptionPane.INFORMATION_MESSAGE);

	
			}
		});
	}
	
	public static void printHex(String data, String fileName) {
		try {
			if (data.length() % 2 == 1) {
				data += '0';
			}
			System.out.println(data);
			DataOutputStream output = new DataOutputStream(new FileOutputStream(new File(fileName)));

			for (int i = 0; i < data.length(); i+=2) {
				String character = data.charAt(i) + "" + data.charAt(i + 1);
				output.writeByte(Integer.parseInt(character, 16));
			}
			
			output.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public static String getHex(int value) {
		String hesInteger = "";
		while (value > 0) {
			if ((value % 16) < 10) {
				hesInteger = value % 16 + hesInteger;	
			} else {
				hesInteger = (char)('A' + (value % 16 - 10)) + hesInteger;
			}
			value = value / 16;
		}
		if (hesInteger.length() == 1) {
			hesInteger = '0' + hesInteger;
		}
		return hesInteger;
	}
	
	public static void main(String[] args) throws IOException {
		Exercise21 frame = new Exercise21();
		frame.setTitle("Exercise21");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
