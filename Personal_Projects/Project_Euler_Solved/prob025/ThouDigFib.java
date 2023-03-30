package prob025;

import java.util.ArrayList;

public class ThouDigFib {
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> fibs = new ArrayList<>();
		fibs.add(new ArrayList<>());
		fibs.get(0).add(1);
		fibs.add(new ArrayList<>());
		fibs.get(1).add(1);
		fibs.add(new ArrayList<>());
		int ind = 2;
		
		while (fibs.get(1).size()<1000) {
			int i;
			for (i=0; i<fibs.get(0).size(); i++) {
				fibs.get(2).add(fibs.get(0).get(i)+fibs.get(1).get(i));
			}
			while (i<fibs.get(1).size()) {
				fibs.get(2).add(fibs.get(1).get(i));
				i++;
			}
			
			for (int j=0; j<fibs.get(2).size(); j++) {
				while (fibs.get(2).get(j)>=10) {
					if (j==fibs.get(2).size()-1) {
						fibs.get(2).add(1);
					} else {
						fibs.get(2).set(j+1, fibs.get(2).get(j+1)+1);
					}
					fibs.get(2).set(j, fibs.get(2).get(j)-10);
				}
			}
			
			ind++;
			System.out.println(ind+": "+fibs.get(2).size());
			fibs.set(0, fibs.get(1));
			fibs.set(1, fibs.get(2));
			fibs.set(2, new ArrayList<>());
		}
	}
}