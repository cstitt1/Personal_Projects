package prob012;

public class TriNumDiv {

	public static void main(String[] args) {
		int tri = 1;
		int delta = 1;
		int count=2;
		while (count<=500) {
			count=2;
			delta = delta + 1;
			tri = tri + delta;
			
			for (int div=2; div<(tri/2) + 1; div++) {
				if (tri%div==0) {
					count++;
				}
			}
			System.out.println(tri + " has " + count + " divisors.");
		}
		
		System.out.println(tri);
	}

}
