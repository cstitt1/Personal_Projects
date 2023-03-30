package prob033;

import java.util.ArrayList;

public class DigCanFrac {
	public static void main(String[] args) {
		ArrayList<String> fracs = new ArrayList<>();
		for (int d=11; d<=99; d++) {
			for (int n=10; n<d; n++) {
				//            01   2    34
				String frac = n + "/" + d;
				String bad = "/";
				if (frac.charAt(1)==frac.charAt(3)) {
					bad = frac.substring(0,1) + bad + frac.substring(4);
				}
				
				if (!bad.equals("/")) {
					double decF = Double.parseDouble(frac.split("/")[0])/Double.parseDouble(frac.split("/")[1]);
					double decB = Double.parseDouble(bad.split("/")[0])/Double.parseDouble(bad.split("/")[1]);
					if (decF==decB) {
						fracs.add(frac);
					}
				}
			}
		}
		
		System.out.print("[");
		for (int i=0; i<fracs.size()-1; i++) {
			System.out.print(fracs.get(i)+",");
		}
		System.out.println(fracs.get(fracs.size()-1)+"]");
		
		int prodN=1, prodD=1;
		String eqN="", eqD="";
		for (String frac : fracs) {
			int n = Integer.parseInt(frac.split("/")[0]);
			int d = Integer.parseInt(frac.split("/")[1]);
			
			prodN = prodN * n;
			prodD = prodD * d;
			
			eqN = eqN + (n+"*");
			eqD = eqD + (d+"*");
		}
		System.out.println(eqN.substring(0,eqN.length()-1)+"="+prodN);
		System.out.println(eqD.substring(0,eqD.length()-1)+"="+prodD);
	}
}