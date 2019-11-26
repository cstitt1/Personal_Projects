package prob078;

public class CoinPart {

	public static void main(String[] args) {
		for (int n = 2; n < 6; n++) {
			long count = 3;
			for (int spt = 2; spt < n-1; spt++) {
				
			}
			
			if (count%1000000 == 0) {
				System.out.println(n);
				break;
			} else {
				System.out.println(n + " -- " + count);
			}
		}
	}
	
	private static long nCr(int n, int r) {
		long rfact=1, nfact=1, nrfact=1,temp1 = n-r ,temp2 = r;
		if(r>n-r)
		{
			temp1 =r;
			temp2 =n-r;
		}
		for(long i=1;i<=n;i++)
		{
			if(i<=temp2)
			{
				rfact *= i;
				nrfact *= i;
			}
			else if(i<=temp1)
			{
				nrfact *= i;
			}
			nfact *= i;
		}
		return nfact/(rfact*nrfact);
	}
}