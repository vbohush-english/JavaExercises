package net.bohush.exercises.chapter18;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Exercise07 extends JFrame {
	private int[][] brackets = new int[][] {{ 27050, 65550, 136750, 297350 },
											{ 45200, 109250, 166500, 297350 },
											{ 22600, 54625, 83250, 148675 },
											{ 36250, 93650, 151650, 297350 } };
	private double[] rates = new double[] { 0.15, 0.275, 0.305, 0.355, 0.391 };
	private int group = 0;
	private JLabel[] labels = new JLabel[7];
	private JRadioButton jRadioButton1 = new JRadioButton("Single filers", true);
	private JRadioButton jRadioButton2 = new JRadioButton("Married filing jointly or qualifying widow(er)");
	private JRadioButton jRadioButton3 = new JRadioButton("Married filing separately");
	private JRadioButton jRadioButton4 = new JRadioButton("Head of household");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();

	private static final long serialVersionUID = 1L;

	public Exercise07() {
		JPanel panel1 = new JPanel(new GridLayout(1, 2));
		panel1.setBorder(new TitledBorder("Select Tax Status"));
		JPanel panel2 = new JPanel(new GridLayout(4, 1));

		ButtonGroup buttonGroup = new ButtonGroup();
		jRadioButton1.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintLabels(0, ((JRadioButton) (e.getSource())).getText());
			}
		});
		jRadioButton2.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintLabels(1, ((JRadioButton) (e.getSource())).getText());
			}
		});
		jRadioButton3.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintLabels(2, ((JRadioButton) (e.getSource())).getText());
			}
		});
		jRadioButton4.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintLabels(3, ((JRadioButton) (e.getSource())).getText());
			}
		});
		buttonGroup.add(jRadioButton1);
		buttonGroup.add(jRadioButton2);
		buttonGroup.add(jRadioButton3);
		buttonGroup.add(jRadioButton4);
		panel2.add(jRadioButton1);
		panel2.add(jRadioButton2);
		panel2.add(jRadioButton3);
		panel2.add(jRadioButton4);
		panel1.add(panel2);
		JPanel panel3 = new JPanel(new GridLayout(7, 1));
		for (int i = 0; i < labels.length; i++) {
			labels[i] = new JLabel(i + "");
			panel3.add(labels[i], BorderLayout.CENTER);
		}
		panel1.add(panel3);
		add(panel1, BorderLayout.NORTH);
		paintLabels(group, jRadioButton1.getText());
		
		JPanel panel4 = new JPanel(new GridLayout(2, 2, 5, 5));
		panel4.add(new JLabel("Taxable Income"));
		panel4.add(jTextField1);
		panel4.add(new JLabel("Tax"));
		jTextField2.setEditable(false);
		panel4.add(jTextField2);
		add(panel4, BorderLayout.CENTER);
		
		JPanel panel5 = new JPanel(new BorderLayout());
		JButton button = new JButton("Compute Tax");
		button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double taxableIncome = Double.parseDouble(jTextField1.getText());
					Tax tax = new Tax(group, brackets, rates, taxableIncome);
					jTextField2.setText(String.format("%.2f", tax.getTax()));
				} catch (NumberFormatException e2) {
				}

			}
		});
		panel5.add(button, BorderLayout.EAST);
		add(panel5, BorderLayout.SOUTH);
		
		

	}

	class ChangeRadioButton implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}		
	}
	
	public void paintLabels(int taxGroup, String groupText) {
		group = taxGroup;
		labels[0].setText(groupText);
		labels[1].setText("Taxable Income");
		labels[2].setText("Up to $" + brackets[group][0]);
		labels[3].setText("$" + (brackets[group][0] + 1) + " - $" + brackets[group][1]);
		labels[4].setText("$" + (brackets[group][1] + 1) + " - $" + brackets[group][2]);
		labels[5].setText("$" + (brackets[group][2] + 1) + " - $" + brackets[group][3]);
		labels[6].setText("$" + (brackets[group][3] + 1) + " or more");
	}

	public static void main(String[] args) {

		Exercise07 frame = new Exercise07();
		frame.setTitle("Exercise07");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.pack();
		frame.setMinimumSize(new Dimension(frame.getWidth(), frame.getHeight()));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class Tax {
	private int filingStatus;
	public final static int SINGLE_FILER = 0;
	public final static int MARRIED_JOINTLY_OR_QUALIFYING_WIDOW = 1;
	public final static int MARRIED_SEPARATELY = 2;
	public final static int HEAD_OF_HOUSEHOLD = 3;
	private int[][] brackets;
	private double[] rates;
	private double taxableIncome;

	Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome) {
		this.filingStatus = filingStatus;
		this.brackets = brackets;
		this.rates = rates;
		this.taxableIncome = taxableIncome;
	}

	Tax() {
		this.filingStatus = SINGLE_FILER;
		this.brackets = new int[][] { { 8350, 33950, 82250, 171550, 372950 },
				{ 16700, 67900, 137050, 20885, 372950 },
				{ 8350, 33950, 68525, 104425, 186475 },
				{ 11950, 45500, 117450, 190200, 372950 } };
		this.rates = new double[] { 0.10, 0.15, 0.25, 0.28, 0.33, 0.35 };
		this.taxableIncome = 0;
	}

	public int getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(int filingStatus) {
		this.filingStatus = filingStatus;
	}

	public int[][] getBrackets() {
		return brackets;
	}

	public void setBrackets(int[][] brackets) {
		this.brackets = brackets;
	}

	public double[] getRates() {
		return rates;
	}

	public void setRates(double[] rates) {
		this.rates = rates;
	}

	public double getTaxableIncome() {
		return taxableIncome;
	}

	public void setTaxableIncome(double taxableIncome) {
		this.taxableIncome = taxableIncome;
	}

	public double getTax() {
		int i = 0;
		double tax = 0;
		while ((i < (brackets[filingStatus].length - 1)) && (brackets[filingStatus][i] < taxableIncome)) {
			if (i == 0) {
				tax += brackets[filingStatus][i] * rates[i];
			} else {
				tax += (brackets[filingStatus][i] - brackets[filingStatus][i - 1])
						* rates[i];
			}
			i++;
		}
		if (i == 0) {
			tax += taxableIncome * rates[i];
		} else {
			tax += (taxableIncome - brackets[filingStatus][i - 1]) * rates[i];
		}
		return tax;
	}

	public void printTable(int year, int startIncome, int finishIncome,
			int deltaIncome) {
		System.out.println("                                      " + year + "                                     ");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Taxable\t\tSingle\t\tMarried Joint\tMarried\t\tHead of");
		System.out.println("Income\t\t\t\tor Qualifying\tSeparate\ta House");
		System.out.println("\t\t\t\tWidow(er)");
		System.out.println("-------------------------------------------------------------------------------");
		for (int income = startIncome; income <= finishIncome; income += deltaIncome) {
			System.out.print(income);
			for (int status = 0; status <= 3; status++) {
				this.setFilingStatus(status);
				this.setTaxableIncome(income);
				;
				// System.out.print("\t\t" + this.getTax());
				System.out.printf("%17.2f", this.getTax());
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------------");
	}
}