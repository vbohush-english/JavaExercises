package net.bohush.exercises.chapter37;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Exercise09 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, false, new JButton("left button"), new JButton("right button"));
	private JTextField jTextField = new JTextField("20");
	
	public Exercise09() {
		setLayout(new BorderLayout());
		jSplitPane.setDividerSize(20);
		add(jSplitPane, BorderLayout.CENTER);
		
		JPanel jPanel1 = new JPanel(new GridLayout(2, 1, 5, 5));
		JRadioButton jRadioButton1 = new JRadioButton("Horizontal", true);
		JRadioButton jRadioButton2 = new JRadioButton("Vertical");
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(jRadioButton1);
		buttonGroup.add(jRadioButton2);
		jPanel1.add(jRadioButton1);
		jPanel1.add(jRadioButton2);
		jPanel1.setBorder(new TitledBorder("Orientation"));
		
		
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.add(jPanel1, BorderLayout.WEST);
		
		JPanel jPanel3 = new JPanel(new BorderLayout(5, 5));
		JCheckBox jCheckBox = new JCheckBox("Continous Layout");
		jPanel3.add(jCheckBox, BorderLayout.NORTH);
		JPanel jPanel4 = new JPanel(new BorderLayout(5, 5));
		jPanel4.add(new JLabel("Divider Size "), BorderLayout.WEST);
		jPanel4.add(jTextField, BorderLayout.CENTER);
		jPanel3.add(jCheckBox, BorderLayout.NORTH);
		jPanel3.add(jPanel4, BorderLayout.CENTER);
		jPanel2.add(jPanel3, BorderLayout.CENTER);
		
		add(jPanel2, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args){
		Exercise09 frame = new Exercise09();
		frame.setTitle("Exercise09");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
