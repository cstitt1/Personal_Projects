package prob015;

public class RecursiveLattice {
	public static void main(String[] args) {
		for (int s=1; s<=20; s++) {
			System.out.println(s+": "+(turn(s,0,0)));
		}
	}
	
	private static double turn(int size, int r, int d) {
		if (r==size && d==size) {
			return 1;
		} else if (r==size) {
			return turn(size, r, d+1);
		} else if (d==size) {
			return turn(size, r+1, d);
		} else {
			return turn(size, r+1, d) + turn(size, r, d+1);
		}
	}
}