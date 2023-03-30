package prob206;

public class ConSq {
	public static void main(String[] args) {
		//                 0000000000111111111
		//                 0123456789012345678
		//String square = "1_2_3_4_5_6_7_8_9_0";
		for (long r = Math.round(Math.sqrt(1020304050607080900l))-1; r<=Math.round(Math.sqrt(1929394959697989990l))+1; r++) {
			String sq = ""+(r*r);
			if (sq.charAt(0)=='1' && sq.charAt(2)=='2' && sq.charAt(4)=='3' && sq.charAt(6)=='4' && sq.charAt(8)=='5' && sq.charAt(10)=='6') {
				if (sq.charAt(12)=='7' && sq.charAt(14)=='8' && sq.charAt(16)=='9' && sq.charAt(18)=='0') {
					System.out.println(r);
					break;
				}
			}
		}
	}
}