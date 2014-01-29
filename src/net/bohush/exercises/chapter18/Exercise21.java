package net.bohush.exercises.chapter18;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Exercise21 extends JApplet{
	private static final long serialVersionUID = 1L;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	
	private SetAlarmFrame setAlarmFrame = new SetAlarmFrame();
	private Calendar calendar = new GregorianCalendar();
	private JCheckBox jCheckBox = new JCheckBox("Alarm");
	
	public Exercise21() {
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setBorder(new LineBorder(Color.BLACK));
		add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel(new GridLayout(1, 3));
		JLabel jLabel1 = new JLabel("Hour");
		JLabel jLabel2 = new JLabel("Minute");
		JLabel jLabel3 = new JLabel("Second");
		jLabel1.setHorizontalAlignment(JLabel.CENTER);
		jLabel2.setHorizontalAlignment(JLabel.CENTER);
		jLabel3.setHorizontalAlignment(JLabel.CENTER);
		panel2.add(jLabel1);
		panel2.add(jLabel2);
		panel2.add(jLabel3);
		panel1.add(panel2, BorderLayout.NORTH);
		
		JPanel panel3 = new JPanel(new GridLayout(1, 3));
		jLabel4 = new JLabel(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
		jLabel5 = new JLabel(String.format("%02d", calendar.get(Calendar.MINUTE)));
		jLabel6 = new JLabel(String.format("%02d", calendar.get(Calendar.SECOND)));
		jLabel4.setFont(new Font("Serif", Font.BOLD, 26));
		jLabel5.setFont(new Font("Serif", Font.BOLD, 26));
		jLabel6.setFont(new Font("Serif", Font.BOLD, 26));
		jLabel4.setHorizontalAlignment(JLabel.CENTER);
		jLabel5.setHorizontalAlignment(JLabel.CENTER);
		jLabel6.setHorizontalAlignment(JLabel.CENTER);
		panel3.add(jLabel4);
		panel3.add(jLabel5);
		panel3.add(jLabel6);
		panel1.add(panel3, BorderLayout.CENTER);
		
		JPanel panel4 = new JPanel();
		JButton jButton = new JButton("Set Alarm");
		panel4.add(jCheckBox);
		panel4.add(jButton);
		add(panel4, BorderLayout.SOUTH);
		
		Timer timer = new Timer(1000, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				calendar.add(Calendar.SECOND, 1);
				jLabel4.setText(String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY)));
				jLabel5.setText(String.format("%02d", calendar.get(Calendar.MINUTE)));
				jLabel6.setText(String.format("%02d", calendar.get(Calendar.SECOND)));
				if (jCheckBox.isSelected()) {
						if (setAlarmFrame.getHour() == Integer.parseInt(jLabel4.getText()) &&
							setAlarmFrame.getMinute() == Integer.parseInt(jLabel5.getText()) &&
							setAlarmFrame.getSecond() == Integer.parseInt(jLabel6.getText())) {
							AudioClip audioClip = Applet.newAudioClip(this.getClass().getResource("alarmSound.au"));
							audioClip.play();
					}
				}
			}
			
		});
		timer.start();
		
		jButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setAlarmFrame.setTime(calendar);
				setAlarmFrame.setVisible(true);
			}
		});
				
		setAlarmFrame.setSize(220, 170);
		setAlarmFrame.setTitle("Set Alarm");
		setAlarmFrame.setMinimumSize(new Dimension(setAlarmFrame.getWidth(), setAlarmFrame.getHeight()));
		setAlarmFrame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new Exercise21());
		frame.setTitle("Exercise21");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(220, 170);
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class SetAlarmFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private int hour;
	private int minute;
	private int second;
	private boolean firstRun = true;
	
	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public SetAlarmFrame() {
		setLayout(new BorderLayout());
		JPanel panel1 = new JPanel(new GridLayout(3, 2));
		panel1.setBorder(new TitledBorder("Enter Hour, Minute, and Second"));
		add(panel1, BorderLayout.CENTER);
		panel1.add(new JLabel("Hour  "));
		panel1.add(jTextField1);
		panel1.add(new JLabel("Minute  "));
		panel1.add(jTextField2);
		panel1.add(new JLabel("Second  "));
		panel1.add(jTextField3);
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton jButton1 = new JButton("Ok");
		JButton jButton2 = new JButton("Cancel");
		panel2.add(jButton1);
		panel2.add(jButton2);
		add(panel2, BorderLayout.SOUTH);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int tmpHour = Integer.parseInt(jTextField1.getText());
					int tmpMinute = Integer.parseInt(jTextField2.getText());
					int tmpSecond = Integer.parseInt(jTextField3.getText());
					hour = tmpHour;
					minute = tmpMinute;
					second = tmpSecond;
					SetAlarmFrame.this.setVisible(false);	
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wrong input data!", "Error", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextField1.setText(String.format("%02d", hour));
				jTextField2.setText(String.format("%02d", minute));
				jTextField3.setText(String.format("%02d", second));
				SetAlarmFrame.this.setVisible(false);
			}
		});
	}
	
	public void setTime(Calendar calendar) {
		if (firstRun) {
			hour = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
			firstRun = false;
		}
		jTextField1.setText(String.format("%02d", hour));
		jTextField2.setText(String.format("%02d", minute));
		jTextField3.setText(String.format("%02d", second));
	}
}