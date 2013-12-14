package ua.pp.viktor.chapter05;

public class Exercise09 {

	public static void main(String[] args) {
		System.out.println("Feet\t\tMeters\t\t|\tMeters\t\t  Feet");
		for (int foot = 1, meter = 20; foot <= 10; foot++, meter += 5) {
			System.out.printf("%4.1f\t\t%5.3f\t\t|\t%3.1f\t\t%7.3f\n", 
					(double)foot, footToMeter(foot), (double)meter, meterToFoot(meter));	
		}
	}
	
	public static double footToMeter(double foot) {
		return 0.3048 * foot;
	}
	
	public static double meterToFoot(double meter) {
		return 3.2808399 * meter;
	}

}
