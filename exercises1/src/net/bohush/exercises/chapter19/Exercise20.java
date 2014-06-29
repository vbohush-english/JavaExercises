package net.bohush.exercises.chapter19;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;


public class Exercise20 extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField = new JTextField("tmp/Exercise19_20.java");
	private JTextArea jTextArea = new JTextArea(10, 30);
	private JButton jButton = new JButton("Save the bits to the file");
	
	public Exercise20() {
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
						result.append(getBits(r));
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
				try {
					BitOutputStream output;
					output = new BitOutputStream(new File(jTextField.getText()));
					StringBuilder builder = new StringBuilder("");
					String text = jTextArea.getText();
					for (int i = 0; i < text.length(); i++) {
						if ((text.charAt(i) == '0') || (text.charAt(i) == '1')) {
							builder.append(text.charAt(i));
						}
					}
					output.writeBit(builder.toString());
					output.close();	
					JOptionPane.showMessageDialog(null, "File saved", "Exercise20", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	
			}
		});
	}
	
	class BitOutputStream {
		private ArrayList<Integer> bits = new ArrayList<>();
		private DataOutputStream output;
		
		public BitOutputStream(File file) throws FileNotFoundException {
			output = new DataOutputStream(new FileOutputStream(file));
		}

		public void writeBit(char bit) throws IOException {
			if (bit == '0') {
				bits.add(0);	
			} else {
				bits.add(1);
			}
			
			if (bits.size() == 8) {
				output.writeByte(getByte());
				bits.clear();
			}
		}

		public void writeBit(String bit) throws IOException {
			for (int i = 0; i < bit.length(); i++) {
				writeBit(bit.charAt(i));
			}
		}

		public void close() throws IOException {
			while(bits.size() != 0) {
				writeBit('0');
			}
			output.close();
		}
		
		private byte getByte() {
			int sum = 0;
			for (int i = 7, number = 1; i >= 0; i--, number*= 2) {
				sum += bits.get(i) * number;
			}
			return (byte)sum;
		}
	}
	
	public static String getBits(int value) {
		value = value % 256;
		String binaryInteger = "";
		int i = 0;
		int tmp = value >> i;
		for (int j = 0; j < 8; j++) {
			binaryInteger = (tmp & 1) + binaryInteger;
			i++;
			tmp = value >> i;
		} 
		return binaryInteger;
	}
	
	public static void main(String[] args) throws IOException {
		Exercise20 frame = new Exercise20();
		frame.setTitle("Exercise20");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
