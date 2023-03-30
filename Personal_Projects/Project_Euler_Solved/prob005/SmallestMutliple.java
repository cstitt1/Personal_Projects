package prob005;
public class SmallestMutliple {
	public static void main(String[] args) {
		int smol = 0;
		int ind = 38798760;
		boolean div = true;
		while (smol == 0) {
			div = true;
			for (int i=2; i<=20; i++)
				if (ind%i != 0)
					div = false;
			
			if (div)
				smol = ind;
			else
				ind++;
		}
		
		System.out.println(smol);
	}
}