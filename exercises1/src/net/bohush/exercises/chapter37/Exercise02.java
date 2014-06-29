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

public class Exercise02 extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private JTextField jTextField1 = new JTextField("5");
	private JTextField jTextField2 = new JTextField("5");
	private JTextField jTextField3 = new JTextField("3");
	private JTextField jTextField4 = new JTextField("5");
	
	private JPanel jPanel1 = new JPanel();
	private GridLayout gridLayout = new GridLayout(5, 3, 5, 5);
	
	public Exercise02() {
		setLayout(new BorderLayout(5, 5));
		jPanel1.setLayout(gridLayout);
		for (int i = 0; i <= 14; i++) {
			jPanel1.add(new JButton("Component " + i));
		}
		jPanel1.setBorder(new TitledBorder("A Conteiner of GridLayout"));
		add(jPanel1, BorderLayout.CENTER);
		JPanel jPanel2 = new JPanel(new GridLayout(2, 2, 10, 10));
		
		jPanel2.setBorder(new TitledBorder("GridLayout Properties"));
		
		JPanel jPanel3 = new JPanel(new BorderLayout(10, 10)); 
		jPanel3.add(new JLabel("Rows"), BorderLayout.WEST);
		jPanel3.add(jTextField1, BorderLayout.CENTER);
		
		JPanel jPanel4 = new JPanel(new BorderLayout(10, 10)); 
		jPanel4.add(new JLabel("HGap"), BorderLayout.WEST);		
		jPanel4.add(jTextField2, BorderLayout.CENTER);
		
		JPanel jPanel5 = new JPanel(new BorderLayout(10, 10)); 
		jPanel5.add(new JLabel("Columns"), BorderLayout.WEST);
		jPanel5.add(jTextField3, BorderLayout.CENTER);
		
		JPanel jPanel6 = new JPanel(new BorderLayout(10, 10)); 
		jPanel6.add(new JLabel("VGap"), BorderLayout.WEST);		
		jPanel6.add(jTextField4, BorderLayout.CENTER);
		
		jPanel2.add(jPanel3);
		jPanel2.add(jPanel4);
		jPanel2.add(jPanel5);
		jPanel2.add(jPanel6);
		
		add(jPanel2, BorderLayout.SOUTH);
		

		jTextField1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					gridLayout.setRows(Integer.parseInt(jTextField1.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
		jTextField2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					gridLayout.setHgap(Integer.parseInt(jTextField2.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
		jTextField3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					gridLayout.setColumns(Integer.parseInt(jTextField3.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
		jTextField4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					gridLayout.setVgap(Integer.parseInt(jTextField4.getText()));
					jPanel1.revalidate();
				} catch (NumberFormatException e2) {}				
			}
		});
	}
	
	public static void main(String[] args){
		Exercise02 frame = new Exercise02();
		frame.setTitle("Exercise02	");
		frame.setSize(700, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
