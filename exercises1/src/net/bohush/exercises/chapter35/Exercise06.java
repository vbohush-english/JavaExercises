package net.bohush.exercises.chapter35;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;

public class Exercise06 extends JApplet {

	private static final long serialVersionUID = 1L;
	private JTextField jTextField1 = new JTextField(10);
	private JTextField jTextField2 = new JTextField("0.08360644", 10);
	private JTextField jTextField3 = new JTextField(10);
	private JTextField jTextField4 = new JTextField("0.05932288", 10);
	private JTextField jTextField5 = new JTextField(10);
	private JTextField jTextField6 = new JTextField("2.90107339", 10);
	private JTextField jTextField7 = new JTextField(10);
	
	
	public Exercise06() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
		JPanel jPanel1 = new JPanel(new BorderLayout(5, 5));
		jPanel1.setBorder(new TitledBorder("Enter Dollar Amount"));
		jPanel1.add(new JLabel("Ukraine Hryvnia"), BorderLayout.WEST);
		
		jPanel1.add(jTextField1, BorderLayout.CENTER);
		mainPanel.add(jPanel1, BorderLayout.NORTH);
		
		jTextField1.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					double uah = Double.parseDouble(jTextField1.getText());
					
					double usd = Double.parseDouble(jTextField2.getText());
					NumberFormat currencyFormat1 = NumberFormat.getCurrencyInstance(Locale.US);
					jTextField3.setText(currencyFormat1.format(uah * usd));
					
					double eur = Double.parseDouble(jTextField4.getText());
					NumberFormat currencyFormat2 = NumberFormat.getCurrencyInstance(Locale.FRANCE);
					jTextField5.setText(currencyFormat2.format(uah * eur));
					
					double rub = Double.parseDouble(jTextField6.getText());
					NumberFormat currencyFormat3 = NumberFormat.getCurrencyInstance(new Locale("ru", "RU"));
					jTextField7.setText(currencyFormat3.format(uah * rub));
					
				} catch (NumberFormatException e2) {
					jTextField3.setText("");
					jTextField5.setText("");
					jTextField7.setText("");
				}
			}
		});
		
		JPanel jPanel2 = new JPanel(new GridLayout(4, 3, 5, 5));
		jPanel2.add(new JLabel(" "));
		jPanel2.add(new JLabel("Exchange Rate"));
		jPanel2.add(new JLabel("Converted Rate"));

		jPanel2.add(new JLabel("US Dollars"));
		jPanel2.add(jTextField2);		
		jTextField3.setEditable(false);
		jPanel2.add(jTextField3);
		

		jPanel2.add(new JLabel("Euro"));		
		jPanel2.add(jTextField4);
		jTextField5.setEditable(false);
		jPanel2.add(jTextField5);
			

		jPanel2.add(new JLabel("Russian Ruble"));		
		jPanel2.add(jTextField6);
		jTextField7.setEditable(false);
		jPanel2.add(jTextField7);
		
		mainPanel.add(jPanel2, BorderLayout.CENTER);
		
		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise06");
		JApplet applet = new Exercise06();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}