package net.bohush.exercises.chapter10;

public class Exercise08 {

	public static void main(String[] args) {
		Tax tax1 = new Tax();
		tax1.printTable(2009, 50000, 60000, 1000);	
		
		int[][] brackets  = new int[][]{{27050, 65550, 136750, 297350},
										{45200, 109250, 166500, 297350},
										{22600, 54625, 83250, 148675},
										{36250, 93650, 151650, 297350}};
		double[] rates  = new double[]{0.15, 0.275, 0.305, 0.355, 0.391};
		Tax tax2 = new Tax(Tax.SINGLE_FILER, brackets, rates, 0);
		tax2.printTable(2001, 50000, 60000, 1000);		
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
		this.filingStatus  = filingStatus;
		this.brackets  = brackets;
		this.rates  = rates;
		this.taxableIncome  = taxableIncome;
	}
	
	Tax() {
		this.filingStatus  = SINGLE_FILER;
		this.brackets = new int[][]{{8350, 33950, 82250, 171550, 372950},
									{16700, 67900, 137050, 20885, 372950},
									{8350, 33950, 68525, 104425, 186475},
									{11950, 45500, 117450, 190200, 372950}};
		this.rates  = new double[]{0.10, 0.15, 0.25, 0.28, 0.33, 0.35};
		this.taxableIncome  = 0;	
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
		while ((i < 5) && (brackets[filingStatus][i] < taxableIncome)) {
			if (i == 0) {
				tax += brackets[filingStatus][i] * rates[i];
			} else {
				tax += (brackets[filingStatus][i] - brackets[filingStatus][i - 1]) * rates[i];				
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
	
	public void printTable(int year, int startIncome, int finishIncome, int deltaIncome) {
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
				this.setTaxableIncome(income);;
				//System.out.print("\t\t" + this.getTax());
				System.out.printf("%17.2f", this.getTax());
			}
			System.out.println();
		}
		System.out.println("-------------------------------------------------------------------------------");
	}
}