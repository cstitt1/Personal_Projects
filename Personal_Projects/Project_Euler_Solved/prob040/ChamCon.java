package prob040;

public class ChamCon {
	public static void main(String[] args) {
		int prod = 1;
		int i=1, n=0;
		
		while (n<=1000000) {
			for (int k=0; k<(""+i).length(); k++) {
				n++;
				if (n==1 || n==10 || n==100 || n==1000 || n==10000 || n==100000 || n==1000000) {
					prod = prod * Integer.parseInt((""+i).substring(k, k+1));
				}
			}
			
			System.out.println(i+": "+n);
			i++;
		}
		System.out.println(prod);
	}
}