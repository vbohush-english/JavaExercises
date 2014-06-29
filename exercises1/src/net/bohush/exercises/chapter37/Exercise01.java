package net.bohush.exercises.chapter37;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Exercise01 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private String[] items = {"LEFT", "CENTER", "RIGHT", "LEADING", "TRAILING"};
	private JComboBox<String> jComboBox = new JComboBox<String>(items);
	private JTextField jTextField1 = new JTextField("5");
	private JTextField jTextField2 = new JTextField("5");
	
	private JPanel jPanel1 = new JPanel();
	private FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 5, 5);
	
	public Exercise01() {
		setLayout(new BorderLayout(5, 5));
		jPanel1.setLayout(flowLayout);
		for (int i = 0; i <= 14; i++) {
			jPanel1.add(new JButton("Component " + i));
		}
		jPanel1.setBorder(new TitledBorder("A Conteiner of FlowLayout"));
		add(jPanel1, BorderLayout.CENTER);
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.setBorder(new TitledBorder("FlowLayout Properties"));
		JPanel jPanel3 = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel3.add(new JLabel("Alignment"));
		jPanel3.add(new JLabel("HGap"));
		jPanel3.add(new JLabel("VGap"));
		jPanel2.add(jPanel3, BorderLayout.WEST);
		JPanel jPanel4 = new JPanel(new GridLayout(3, 1, 5, 5));
		jComboBox.setSelectedIndex(1);
		jPanel4.add(jComboBox);
		jPanel4.add(jTextField1);
		jPanel4.add(jTextField2);
		jPanel2.add(jPanel4, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);
		
		jComboBox.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				flowLayout.setAlignment(jComboBox.getSelectedIndex());
				jPanel1.revalidate();
			}
		});
		jTextField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					flowLayout.setHgap(Integer.parseInt(jTextField1.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
		jTextField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					flowLayout.setVgap(Integer.parseInt(jTextField2.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
	}
	
	public static void main(String[] args){
		Exercise01 frame = new Exercise01();
		frame.setTitle("Exercise01");
		frame.setSize(600, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
