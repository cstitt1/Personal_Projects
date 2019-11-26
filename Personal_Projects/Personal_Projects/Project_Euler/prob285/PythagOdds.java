package prob285;

public class PythagOdds {
	public static void main(String[] args) {
		double min = Double.MAX_VALUE, k = 9, max = Double.MIN_VALUE;
		
		while (true) {
			double a = Math.random(), b = Math.random();
			double l = (k*a + 1), r = (k*b + 1);
			double p = Math.sqrt(l*l + r*r);
			
			if (p > max) {
				max = p;
			}
			
			if (p < min) {
				min = p;
			}
			
			System.out.println("Range: "+min+" to "+max);
		}
	}
}