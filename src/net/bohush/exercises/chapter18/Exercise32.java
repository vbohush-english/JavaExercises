package net.bohush.exercises.chapter18;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise32 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JTextField jTextField = new JTextField(4);
	private AudioClip audioClip = Applet.newAudioClip(this.getClass().getResource("alarmSound.au"));
	Timer timer;
	
	public Exercise32() {
		jTextField.setFont(new Font("Monospaced", Font.BOLD, 100));
		jTextField.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.start();	
				audioClip.stop();
			}
		});
		timer = new Timer(1000, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int value = Integer.parseInt(jTextField.getText());
					jTextField.setText(--value + "");
					if (value <= 0) {
						timer.stop();
						audioClip.loop();
					}					
				} catch (NumberFormatException e2) {					
				}
			}
		});
		add(jTextField, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise32());
		frame.setTitle("Exercise32");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
