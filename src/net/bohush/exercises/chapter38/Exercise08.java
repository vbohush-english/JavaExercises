package net.bohush.exercises.chapter38;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Exercise08 extends JFrame{
	private static final long serialVersionUID = 1L;
	private AudioClip audioClip = null;
	private JButton jButton2 = new JButton("Play");
	private JButton jButton3 = new JButton("Loop");
	private JButton jButton4 = new JButton("Stop");
	
	public Exercise08() {
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		JButton jButton1 = new JButton("Choose an Audio File");
		jPanel2.add(jButton1, BorderLayout.NORTH);
		jButton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						if(audioClip!=null) {
							audioClip.stop();
						}
						audioClip = Applet.newAudioClip(new File(fileChooser.getSelectedFile().getAbsolutePath()).toURI().toURL());
						jButton2.setEnabled(true);
						jButton3.setEnabled(true);
						jButton4.setEnabled(true);
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}		
			}
		});
		JPanel jPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		jButton2.setEnabled(false);
		jButton3.setEnabled(false);
		jButton4.setEnabled(false);
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.play();
			}
		});
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.loop();
			}
		});
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				audioClip.stop();
			}
		});
		jPanel.add(jButton2);
		jPanel.add(jButton3);
		jPanel.add(jButton4);
		jPanel2.add(jPanel, BorderLayout.SOUTH);
		add(jPanel2, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Exercise08 frame = new Exercise08();
		frame.setTitle("Exercise08");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
	