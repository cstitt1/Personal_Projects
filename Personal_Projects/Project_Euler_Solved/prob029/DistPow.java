package prob029;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DistPow {

	public static void main(String[] args) {
		ArrayList<BigDecimal> list = new ArrayList<>();
		BigDecimal num;
		
		for (int a = 2; a<=100; a++) {
			for (int b = 2; b<=100; b++) {
				num = new BigDecimal(Math.pow(a, b));
				if (list.contains(num)==false) {
					try {
						list.add(num);
					} catch (Exception e) {
						System.out.println(list.size());
						e.printStackTrace();
					}
				}
			}
		}
		
		System.out.println(list.size());
	}

}
