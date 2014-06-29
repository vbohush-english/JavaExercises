package net.bohush.exercises.chapter08;

public class Exercise02 {

	public static void main(String[] args) {
		Stock stock = new Stock("ORCL", "Oracle corporation");
		stock.previousClosingPrice = 34.5;
		stock.currentPrice = 34.35;
		System.out.printf("%.2f%%", stock.getChangePercent());
	}

}

class Stock {
	String symbol;
	String name;
	double previousClosingPrice;
	double currentPrice;
	
	Stock(String symbol, String name) {
		this.symbol = symbol;
		this.name = name;
	}
	
	double getChangePercent() {
		return ((currentPrice * 100) / previousClosingPrice) - 100;
	}
}
