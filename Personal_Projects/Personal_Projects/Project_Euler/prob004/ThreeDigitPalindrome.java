package prob004;

public class ThreeDigitPalindrome {

	public static void main(String[] args) {
		int max = 0;
		boolean pal = true;
		
		for (int x = 1000; x >= 100; x--) {
			for (int y=1000; y>=100; y--) {
				pal = true;
				int z = x*y;
				String zTest = Integer.toString(z);
				for (int c=0; c<zTest.length()/2; c++)
					if (zTest.charAt(c) != zTest.charAt(zTest.length()-(c+1)))
						pal = false;
				
				if (pal && z > max)
					max = z;
			}
		}
		
		System.out.println(max);
	}

}
