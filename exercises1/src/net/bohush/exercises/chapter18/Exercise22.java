package net.bohush.exercises.chapter18;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Exercise22 extends JApplet{
	private AudioClip audioClip;
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField("100");
	private JTextField jTextField2 = new JTextField("L");
	private JTextField jTextField3 = new JTextField("52");
	private JTextField jTextField4 = new JTextField("ticker.au");
	private JLabel jLabel = new JLabel();
	private int currentImage = 1;
	private Timer timer;
	
	public Exercise22() {
		setLayout(new BorderLayout(5, 5));
		JPanel panel1 = new JPanel(new GridLayout(4, 2));
		panel1.setBorder(new TitledBorder("Enter information for animation"));

		panel1.add(new JLabel("Animation speed in milliseconds "));
		panel1.add(jTextField1);
		panel1.add(new JLabel("Image file prefix"));
		panel1.add(jTextField2);
		panel1.add(new JLabel("Number of images"));
		panel1.add(jTextField3);
		panel1.add(new JLabel("Audio file"));
		panel1.add(jTextField4);
		add(panel1, BorderLayout.SOUTH);
		
		JPanel panel2 = new JPanel(new BorderLayout());
		JButton jButton = new JButton("Satrt Animation");
		panel2.add(jButton, BorderLayout.EAST);
		add(panel2, BorderLayout.NORTH);
		
		JPanel panel3 = new JPanel(new BorderLayout());
		panel3.setBorder(new LineBorder(Color.BLACK));
		setImage(currentImage);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		jLabel.setVerticalAlignment(JLabel.CENTER);
		panel3.add(jLabel);
		int tmp = 500;
		panel3.setMinimumSize(new Dimension(tmp, tmp));
		jLabel.setMinimumSize(new Dimension(tmp, tmp));
		add(panel3, BorderLayout.CENTER);
		
		timer = new Timer(Integer.parseInt(jTextField1.getText()), new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				currentImage++;
				if (currentImage > Integer.parseInt(jTextField3.getText())) {
					currentImage = 1;
				}
				setImage(currentImage);
			}
		});
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();	
				timer.setDelay(Integer.parseInt(jTextField1.getText()));
				timer.start();	
				if (audioClip != null) {
					audioClip.stop();
				}
				audioClip = Applet.newAudioClip(this.getClass().getResource(jTextField4.getText()));
				audioClip.loop();
			}
		});
	}
	
	public void setImage(int numberOfImage) {
		ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("image/" + jTextField2.getText() + numberOfImage + ".gif"));
		jLabel.setIcon(imageIcon);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise22());
		frame.setTitle("Exercise22");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(frame.getWidth(), frame.getHeight() + 400);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
