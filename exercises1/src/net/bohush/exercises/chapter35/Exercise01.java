package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise01 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField = new JTextField(5);
	private JTextArea jTextArea = new JTextArea();
	
	public Exercise01() {
		jTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int value = Integer.parseInt(jTextField.getText(), 16);
				int maxValue = 65536;
				StringBuilder stringBuilder = new StringBuilder();
				for (int i = 0; i < 20; i++) {
					stringBuilder.append(Integer.toHexString(value) + '\t');
					for (int j = 0; j < 16; j++) {
						if(value >= maxValue) {
							break;
						}
						stringBuilder.append((char)(value) + "\t");
						value++;
					}
					stringBuilder.append("\n");
					if(value >= maxValue) {
						break;
					}
				}
				jTextArea.setText(stringBuilder.toString());
				jTextField.setText("");
				jTextField.requestFocus();
			}
		});
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel jPanel1 = new JPanel(new FlowLayout());
		jPanel1.setBorder(new TitledBorder("Specify Unicode"));
		jPanel1.add(jTextField);
		mainPanel.add(jPanel1, BorderLayout.NORTH);
		
		jTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		jTextArea.setEditable(false);
		JScrollPane jScrollPane = new JScrollPane(jTextArea);
		mainPanel.add(jScrollPane, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise01");
		JApplet applet = new Exercise01();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}