package prob041;

public class PanPri {
	public static void main(String[] args) {
		int max = 0;
		for (int i=3; i<=999999999; i+=2) {
			if (isNPandigital((""+i), (""+i).length())) {
				if (isPrime(i)) {
					if (i > max) {
						max = i;
						System.out.println(max);
					}
				}
			}
		}
	}
	
	private static boolean isPrime(int i) {
		if (i==1) {
			return false;
		}
		
		for (int x=2; x<i; x++)
			if (i%x==0)
				return false;
		return true;
	}
	
	private static boolean isNPandigital(String str, int n) {
		if (str.contains("0") || str.length()!=n) {
			return false;
		}
		
		for (int i=1; i<=n; i++) {
			if (!(str.contains(""+i) && str.indexOf(""+i)==str.lastIndexOf(""+i))) {
				return false;
			}
		}
		
		return true;
	}
}