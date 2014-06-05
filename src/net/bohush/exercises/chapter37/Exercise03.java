package net.bohush.exercises.chapter37;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class Exercise03 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField jTextField1 = new JTextField("5");
	private JTextField jTextField2 = new JTextField("5");
	
	private JPanel jPanel1 = new JPanel();
	private BorderLayout borderLayout = new BorderLayout(5, 5);
	
	public Exercise03() {
		setLayout(new BorderLayout(5, 5));
		jPanel1.setLayout(borderLayout);
		jPanel1.add(new JButton("NORTH"), BorderLayout.NORTH);
		jPanel1.add(new JButton("SOUTH"), BorderLayout.SOUTH);
		jPanel1.add(new JButton("CENTER"), BorderLayout.CENTER);
		jPanel1.add(new JButton("EAST"), BorderLayout.EAST);
		jPanel1.add(new JButton("WEST"), BorderLayout.WEST);
		 
		jPanel1.setBorder(new TitledBorder("A Conteiner of BorderLayout"));
		add(jPanel1, BorderLayout.CENTER);
		JPanel jPanel2 = new JPanel(new BorderLayout(5, 5));
		jPanel2.setBorder(new TitledBorder("BorderLayout Properties"));
		JPanel jPanel3 = new JPanel(new GridLayout(2, 1, 5, 5));
		jPanel3.add(new JLabel("Alignment"));
		jPanel3.add(new JLabel("HGap"));
		jPanel2.add(jPanel3, BorderLayout.WEST);
		JPanel jPanel4 = new JPanel(new GridLayout(2, 1, 5, 5));

		jPanel4.add(jTextField1);
		jPanel4.add(jTextField2);
		jPanel2.add(jPanel4, BorderLayout.CENTER);
		add(jPanel2, BorderLayout.SOUTH);
		
		jTextField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					borderLayout.setHgap(Integer.parseInt(jTextField1.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
		jTextField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					borderLayout.setVgap(Integer.parseInt(jTextField2.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
	}
	
	public static void main(String[] args){
		Exercise03 frame = new Exercise03();
		frame.setTitle("Exercise03");
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
