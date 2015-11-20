package schwimmer.pi;

public class CalculatePi {
	
	public static void main(String[] args) {
		PiCalculator calc = new PiCalculator();
		
		System.out.println(calc.calculate(100000000000L));
	}

}
