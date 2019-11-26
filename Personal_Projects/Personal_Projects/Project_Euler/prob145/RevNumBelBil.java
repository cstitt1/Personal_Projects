package prob145;

public class RevNumBelBil {
	public static void main(String[] args) {
		int count = 0;
		
		for (int n = 1; n<1000000000; n++) {
			int rev = reverse(n);
			if (rev == -1) {
				continue;
			}
			
			String sum = ""+(n+rev);
			boolean odd = true;
			for (int i=0; i<sum.length(); i++) {
				if (Integer.parseInt(sum.substring(i, i+1))%2==0) {
					odd=false;
					break;
				}
			}
			
			if (odd) {
				count++;
				System.out.println(n + " -- count="+count);
			}
		}
	}
	
	private static int reverse(int n) {
		String str = ""+n;
		String rev = "";
		
		if (str.charAt(str.length()-1)=='0' || str.length()==1) {
			return -1;
		}
		
		for (int i = str.length()-1; i >= 0; i--) {
			rev = rev + str.substring(i, i+1);
		}
		
		return Integer.parseInt(rev);
	}
}