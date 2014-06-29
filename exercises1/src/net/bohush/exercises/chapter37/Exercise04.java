package net.bohush.exercises.chapter37;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Exercise04 extends JApplet {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> jComboBox = new JComboBox<>(new String[]{"Integer Operation", "Rational Operation"}); 
	
	private JTextField jTextField1 = new JTextField(5);
	private JTextField jTextField2 = new JTextField(5);
	private JTextField jTextField3 = new JTextField(5);

	private JTextField jTextField4 = new JTextField(5);
	private JTextField jTextField5 = new JTextField(5);
	private JTextField jTextField6 = new JTextField(5);
	
	private CardLayout cardLayout = new CardLayout();
	private JPanel jPanel9 = new JPanel(cardLayout);
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Exercise04");
		Exercise04 applet = new Exercise04();
		frame.add(applet, java.awt.BorderLayout.CENTER);
		applet.init();
		applet.start();
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	@Override
	public void init() {
		setLayout(new BorderLayout());
		add(jComboBox, BorderLayout.NORTH);
		
		JPanel jPanel2 = new JPanel(new BorderLayout());
		jPanel2.add(new JLabel("Integer Calculation", JLabel.CENTER));
		JPanel jPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel3.add(new JLabel("Number 1"));
		jPanel3.add(jTextField1);
		jPanel3.add(new JLabel("Number 2"));
		jPanel3.add(jTextField2);
		jPanel3.add(new JLabel("Result"));
		jTextField3.setEditable(false);
		jPanel3.add(jTextField3);
		JPanel jPanel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton jButton1 = new JButton("Add");
		jPanel4.add(jButton1);
		JButton jButton2 = new JButton("Subtract");
		jPanel4.add(jButton2);
		JButton jButton3 = new JButton("Multiply");
		jPanel4.add(jButton3);
		JButton jButton4 = new JButton("Divide");
		jPanel4.add(jButton4);
		jButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jTextField3.setText("" + (Integer.parseInt(jTextField1.getText()) + Integer.parseInt(jTextField2.getText())));					
				} catch (NumberFormatException e2) {}
			}
		});
		jButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jTextField3.setText("" + (Integer.parseInt(jTextField1.getText()) - Integer.parseInt(jTextField2.getText())));					
				} catch (NumberFormatException e2) {}
			}
		});
		jButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jTextField3.setText("" + (Integer.parseInt(jTextField1.getText()) * Integer.parseInt(jTextField2.getText())));					
				} catch (NumberFormatException e2) {}
			}
		});
		jButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					jTextField3.setText("" + (Integer.parseInt(jTextField1.getText()) / Integer.parseInt(jTextField2.getText())));					
				} catch (NumberFormatException e2) {}
			}
		});
		JPanel jPanel1 = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel1.add(jPanel2);
		jPanel1.add(jPanel3);
		jPanel1.add(jPanel4);
		
		
		
		JPanel jPanel5 = new JPanel(new BorderLayout());
		jPanel5.add(new JLabel("Rational Calculation", JLabel.CENTER));
		JPanel jPanel6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jPanel6.add(new JLabel("Number 1"));
		jPanel6.add(jTextField4);
		jPanel6.add(new JLabel("Number 2"));
		jPanel6.add(jTextField5);
		jPanel6.add(new JLabel("Result"));
		jTextField6.setEditable(false);
		jPanel6.add(jTextField6);
		JPanel jPanel7 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton jButton5 = new JButton("Add");
		jPanel7.add(jButton5);
		JButton jButton6 = new JButton("Subtract");
		jPanel7.add(jButton6);
		JButton jButton7 = new JButton("Multiply");
		jPanel7.add(jButton7);
		JButton jButton8 = new JButton("Divide");
		jPanel7.add(jButton8);
		jButton5.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Rational number1 = getRationalNumberFromString(jTextField4.getText());
					Rational number2 = getRationalNumberFromString(jTextField5.getText());
					if((number1 != null)&&(number2 != null)) {
						jTextField6.setText(number1.add(number2).toString());
					}
				} catch (NumberFormatException e2) {}
			}
		});
		jButton6.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Rational number1 = getRationalNumberFromString(jTextField4.getText());
					Rational number2 = getRationalNumberFromString(jTextField5.getText());
					if((number1 != null)&&(number2 != null)) {
						jTextField6.setText(number1.subtract(number2).toString());
					}
				} catch (NumberFormatException e2) {}
			}
		});
		jButton7.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Rational number1 = getRationalNumberFromString(jTextField4.getText());
					Rational number2 = getRationalNumberFromString(jTextField5.getText());
					if((number1 != null)&&(number2 != null)) {
						jTextField6.setText(number1.multiply(number2).toString());
					}
				} catch (NumberFormatException e2) {}
			}
		});
		jButton8.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Rational number1 = getRationalNumberFromString(jTextField4.getText());
					Rational number2 = getRationalNumberFromString(jTextField5.getText());
					if((number1 != null)&&(number2 != null)) {
						jTextField6.setText(number1.divide(number2).toString());
					}
				} catch (NumberFormatException e2) {}
			}
		});
		JPanel jPanel8 = new JPanel(new GridLayout(3, 1, 5, 5));
		jPanel8.add(jPanel5);
		jPanel8.add(jPanel6);
		jPanel8.add(jPanel7);
		
		jPanel9.add(jPanel1, jComboBox.getItemAt(0));
		jPanel9.add(jPanel8, jComboBox.getItemAt(1));
		
		add(jPanel9, BorderLayout.CENTER);
		jComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(jPanel9, jComboBox.getSelectedItem().toString());
			}
		});
		
	}
	
	static private Rational getRationalNumberFromString(String number) {
		String[] numbers = number.split("\\/");
		if(numbers.length == 2) {
			try {
				long numerator = Long.parseLong(numbers[0]);
				long denominator = Long.parseLong(numbers[1]);
				return new Rational(numerator, denominator);
			} catch (NumberFormatException e) {
				return null;
			}
		} else if(numbers.length == 1) {
			try {
				long numerator = Long.parseLong(numbers[0]);
				long denominator = 1;
				return new Rational(numerator, denominator);
			} catch (NumberFormatException e) {
				return null;
			}
		} else {
			return null;
		}

	}
	
	static class Rational extends Number implements Comparable<Rational> {
		private static final long serialVersionUID = 1L;
		// Data fields for numerator and denominator
		private long numerator = 0;
		private long denominator = 1;

		/** Construct a rational with default properties */
		public Rational() {
			this(0, 1);
		}

		/** Construct a rational with specified numerator and denominator */
		public Rational(long numerator, long denominator) {
			long gcd = gcd(numerator, denominator);
			this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
			this.denominator = Math.abs(denominator) / gcd;
		}
		
		/** Find GCD of two numbers */
		private static long gcd(long n, long d) {
			long n1 = Math.abs(n);
			long n2 = Math.abs(d);
			int gcd = 1;

			for (int k = 1; k <= n1 && k <= n2; k++) {
				if (n1 % k == 0 && n2 % k == 0)
					gcd = k;
			}

			return gcd;
		}

		/** Return numerator */
		public long getNumerator() {
			return numerator;
		}

		/** Return denominator */
		public long getDenominator() {
			return denominator;
		}

		/** Add a rational number to this rational */
		public Rational add(Rational secondRational) {
			long n = numerator * secondRational.getDenominator() + denominator
					* secondRational.getNumerator();
			long d = denominator * secondRational.getDenominator();
			return new Rational(n, d);
		}

		/** Subtract a rational number from this rational */
		public Rational subtract(Rational secondRational) {
			long n = numerator * secondRational.getDenominator() - denominator
					* secondRational.getNumerator();
			long d = denominator * secondRational.getDenominator();
			return new Rational(n, d);
		}

		/** Multiply a rational number to this rational */
		public Rational multiply(Rational secondRational) {
			long n = numerator * secondRational.getNumerator();
			long d = denominator * secondRational.getDenominator();
			return new Rational(n, d);
		}

		/** Divide a rational number from this rational */
		public Rational divide(Rational secondRational) {
			long n = numerator * secondRational.getDenominator();
			long d = denominator * secondRational.numerator;
			return new Rational(n, d);
		}

		@Override
		public String toString() {
			if (denominator == 1)
				return numerator + "";
			else
				return numerator + "/" + denominator;
		}

		@Override
		// Override the equals method in the Object class
		public boolean equals(Object other) {
			if ((this.subtract((Rational) (other))).getNumerator() == 0)
				return true;
			else
				return false;
		}

		@Override
		// Implement the abstract intValue method in Number
		public int intValue() {
			return (int) doubleValue();
		}

		@Override
		// Implement the abstract floatValue method in Number
		public float floatValue() {
			return (float) doubleValue();
		}

		@Override
		// Implement the doubleValue method in Number
		public double doubleValue() {
			return numerator * 1.0 / denominator;
		}

		@Override
		// Implement the abstract longValue method in Number
		public long longValue() {
			return (long) doubleValue();
		}

		@Override
		// Implement the compareTo method in Comparable
		public int compareTo(Rational o) {
			if (this.subtract(o).getNumerator() > 0)
				return 1;
			else if (this.subtract(o).getNumerator() < 0)
				return -1;
			else
				return 0;
		}
	}

}