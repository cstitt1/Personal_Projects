package prob058;

public class SpiPri {
	public static void main(String[] args) {
		long num = 0, dem = 1, by1;
		
		for (by1 = 2; by1 > 1; by1+=2) {
			dem+=4;
			long pow = (by1*by1)+1;
			
			num += ((prime(pow))?1:0) + ((prime(pow+by1))?1:0) + ((prime(pow-by1))?1:0);
			
			System.out.println(num + "/" + dem + " -- " + (by1+1) + " -- " + (100*num)/dem + "+" + (100*num)%dem + "/" + dem);
			if (10*num < dem) {
				break;
			}
		}
	}
	
	private static boolean prime(long pc) {
		if (pc == 2) {
			return true;
		}
		
		for (long i = 2; i <= Math.round(Math.ceil(Math.sqrt(pc))); i+=2) {
			if (pc%i == 0) {
				return false;
			}
			
			if (i == 2) {
				i--;
			}
		}
		
		return true;
	}
}