package net.bohush.exercises.chapter35;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.applet.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Exercise10 extends JApplet {
	private static final long serialVersionUID = 1L;
	private final static int NUMBER_OF_NATIONS = 7;
	private int current = 0;
	private ImageIcon[] icons = new ImageIcon[NUMBER_OF_NATIONS];
	private AudioClip[] audioClips = new AudioClip[NUMBER_OF_NATIONS];
	private AudioClip currentAudioClip;

	private int[] delays = { 48000, 54000, 59000, 54000, 59000, 31000, 68000 };
	private Timer timer = new Timer(delays[0], new TimerListener());

	private JLabel jlblImageLabel = new JLabel();
	private JButton jbtResume = new JButton("Resume");
	private JButton jbtSuspend = new JButton("Suspend");
	private Locale[] countries = {new Locale("da"), new Locale("de"), new Locale("gb"), new Locale("hi"), new Locale("no"), new Locale("us"), new Locale("zh")};
	private JComboBox<String> jcboNations;

	public Exercise10() {
		String[] names = new String[countries.length];
		for (int i = 0; i < countries.length; i++) {
			ResourceBundle res = ResourceBundle.getBundle("net.bohush.exercises.chapter35.Exercise10Countries", countries[i]);
			names[i] = (String)res.getObject("name");
			icons[i] = new ImageIcon(getClass().getResource((String)res.getObject("nationalFlag")));
			audioClips[i] = Applet.newAudioClip(getClass().getResource((String)res.getObject("nationalAnthem")));
		}
		
		jcboNations = new JComboBox<>(names);
		

		JPanel panel = new JPanel();
		panel.add(jbtResume);
		panel.add(jbtSuspend);
		panel.add(new JLabel("Select"));
		panel.add(jcboNations);
		add(jlblImageLabel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		jbtResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		jbtSuspend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});
		jcboNations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
				current = jcboNations.getSelectedIndex();
				presentNation(current);
				timer.start();
			}
		});

		timer.start();
		jlblImageLabel.setIcon(icons[0]);
		jlblImageLabel.setHorizontalAlignment(JLabel.CENTER);
		currentAudioClip = audioClips[0];
		currentAudioClip.play();
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			current = (current + 1) % NUMBER_OF_NATIONS;
			presentNation(current);
		}
	}

	private void presentNation(int index) {
		jlblImageLabel.setIcon(icons[index]);
		jcboNations.setSelectedIndex(index);
		currentAudioClip = audioClips[index];
		currentAudioClip.play();
		timer.setDelay(delays[index]);
	}

	public void start() {
		timer.start();
		currentAudioClip.play();
	}

	public void stop() {
		timer.stop();
		currentAudioClip.stop();
	}

	/** Main method */
	public static void main(String[] args) {
		// Create a frame
		JFrame frame = new JFrame("Exercise10");

		// Create an instance of the applet
		Exercise10 applet = new Exercise10();
		applet.init();

		// Add the applet instance to the frame
		frame.getContentPane().add(applet, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Display the frame
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
}