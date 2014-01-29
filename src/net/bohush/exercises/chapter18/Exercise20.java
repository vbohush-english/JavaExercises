package net.bohush.exercises.chapter18;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Exercise20 extends JApplet{
	private AudioClip audioClip = Applet.newAudioClip(this.getClass().getResource("alarmSound.au"));
	private static final long serialVersionUID = 1L;

	public Exercise20() {
		JPanel panel1 = new JPanel();
		JButton jButton1 = new JButton("Play");
		JButton jButton2 = new JButton("Loop");
		JButton jButton3 = new JButton("Stop");
		jButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.play();
				
			}
		});
		jButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.loop();
				
			}
		});
		jButton3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.stop();
				
			}
		});
		panel1.add(jButton1);
		panel1.add(jButton2);
		panel1.add(jButton3);
		add(panel1, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise20());
		frame.setTitle("Exercise20");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
