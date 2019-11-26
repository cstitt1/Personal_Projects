package prob112;

public class BounNum {
	public static void main(String[] args) {
		int count = 19602;
		double percent = 90;
		
		for (int i=21781; i<=Integer.MAX_VALUE; i++) {
			boolean inc = true, dec = true;
			char[] chars = (""+i).toCharArray();
			
			for (int j=0; j<chars.length-1; j++) {
				if (chars[j] > chars[j+1]) {
					dec = false;
				} else if (chars[j] < chars[j+1]) {
					inc = false;
				}
				
				if (inc==false && dec==false) {
					count++;
					percent = getPercent(count, i);
					System.out.println(count + " of " + i + " -- " + percent);
					break;
				}
			}
			
			if (percent>99) {
				System.out.println(count + " of " + i + " -- " + percent);
				break;
			}
		}
	}
	
	private static double getPercent(int bouncy, int total) {
		double perc = (((double) bouncy)/((double) total))*100;
		return perc;
	}
}