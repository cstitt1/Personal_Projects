package prob017;

public class NumLetterCount {
	private final static String[] ONES = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private final static String[] TEEN = {null, "eleven", "twelve", "thir", null, "fif", null, null, "eigh", null};
	private final static String[] TENS = {null, "ten", "twenty", null, "forty", null, null, null, null, null};
	
	public static void main(String[] args) {
		int sum=("onethousand").length();
		for (int i=1; i<1000; i++) {
			int count = 0;
			int j=i;
			int places=(""+j).length();
			
			if (places==3) {
				count = count + (ONES[j/100]+"hundred").length();
				if (j%100!=0) {
					count = count + ("and").length();
					j = j%100;
					places=(""+j).length();
				}
			}
			
			if (places==2) {
				if (j<20 && j>10) {
					if (j==11 || j==12) {
						count = count + TEEN[j%10].length();
					} else {
						if (TEEN[j%10]==null) {
							count = count + (ONES[j%10] + "teen").length();
						} else {
							count = count + (TEEN[j%10] + "teen").length();
						}
					}
				} else {
					if (TENS[j/10]==null) {
						if (TEEN[j/10]==null) {
							count = count + (ONES[j/10]+"ty").length();
						} else {
							count = count + (TEEN[j/10]+"ty").length();
						}
					} else {
						count = count + TENS[j/10].length();
					}
					
					j = j%10;
					places=(""+j).length();
				}
			}
			
			if (places==1) {
				count = count + ONES[j].length();
			}
			
			System.out.println(i+": "+count);
			sum = sum + count;
		}
		
		System.out.println(1000+": "+("onethousand".length()));
		System.out.println(sum);
	}
}