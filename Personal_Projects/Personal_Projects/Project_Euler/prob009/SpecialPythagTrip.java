package prob009;

public class SpecialPythagTrip {

	public static void main(String[] args) {
		for (double a=1; a<334; a++) {
			double b = 1000*((a-500)/(a-1000));
			
			if (b%1!=0) {
				continue;
			}
			
			double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
			
			if (c%1!=0) {
				continue;
			}
			
			System.out.println(a*b*c);
		}
	}

}
