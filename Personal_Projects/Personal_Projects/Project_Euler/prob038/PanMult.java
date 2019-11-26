package prob038;

public class PanMult {
	public static void main(String[] args) {
		int max = 0;
		for (int i=1; i<=Integer.MAX_VALUE; i++) {
			String res="";
			int n=1;
			boolean valid = true;
			while (!is9Pandigital(res)) {
				if (res.length()>=9) {
					valid = false;
					break;
				}
				
				res = res + (n*i);
				n++;
			}
			
			if (valid && Integer.parseInt(res)>max) {
				max = Integer.parseInt(res);
				System.out.println("Max: "+max);
			}
		}
		
		System.out.println(max);
	}
	
	private static boolean is9Pandigital(String str) {
		if (str.contains("0") || str.length()!=9) {
			return false;
		}
		
		for (int i=1; i<=9; i++) {
			if (!(str.contains(""+i) && str.indexOf(""+i)==str.lastIndexOf(""+i))) {
				return false;
			}
		}
		
		return true;
	}
}