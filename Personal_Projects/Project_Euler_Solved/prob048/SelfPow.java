package prob048;

import java.util.ArrayList;

public class SelfPow {
	public static void main(String[] args) {
		ArrayList<Integer> series = new ArrayList<>();
		series.add(1);
		
		for (int n=2; n<=1000; n++) {
			ArrayList<Integer> exp = new ArrayList<>();
			exp.add(1);
			
			for (int i=1; i<=n; i++) {
				for (int j = 0; j<exp.size(); j++) {
					exp.set(j,exp.get(j)*n);
				}
				
				for (int j=0; j<exp.size(); j++) {
					while (exp.get(j)>=10) {
						if (j==exp.size()-1) {
							exp.add(1);
						} else {
							exp.set(j+1, exp.get(j+1)+1);
						}
						exp.set(j, exp.get(j)-10);
					}
				}
				
				while (exp.size()>11) {
					exp.remove(11);
				}
			}
			
			for (int k=0; k<exp.size(); k++) {
				if (k>=series.size()) {
					series.add(exp.get(k));
				} else {
					series.set(k, series.get(k)+exp.get(k));
				}
			}
			
			for (int k=0; k<series.size(); k++) {
				while (series.get(k)>=10) {
					if (k==series.size()-1) {
						series.add(1);
					} else {
						series.set(k+1, series.get(k+1)+1);
					}
					series.set(k, series.get(k)-10);
				}
			}
			
			while (series.size()>11) {
				series.remove(11);
			}
			
			System.out.println(n+"^"+n+" is done");
		}
		
		String str = "";
		for (int d = series.size()-1; d>=0; d--) {
			str = str + series.get(d);
		}
		System.out.println(str);
	}
}