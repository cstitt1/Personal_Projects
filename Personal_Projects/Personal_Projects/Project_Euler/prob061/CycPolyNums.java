package prob061;

import java.util.ArrayList;

public class CycPolyNums {

	public static void main(String[] args) {
		ArrayList<Long> tri = new ArrayList<>(), sq = new ArrayList<>(), pent = new ArrayList<>();
		ArrayList<Long> hex = new ArrayList<>(), hept = new ArrayList<>(), oct = new ArrayList<>();
		for (long i = 1000; i <= 9999; i++) {
			long tn = Math.round((-1 + Math.sqrt(1 + 8*i))/2);
			if ((tn*(tn+1))/2 == i) {
				tri.add(i);
			}
			
			long sn = Math.round(Math.sqrt(i));
			if (sn*sn == i) {
				sq.add(i);
			}
			
			long pn = Math.round((1 + Math.sqrt(1 + 24*i))/6);
			if ((pn*(3*pn-1))/2 == i) {
				pent.add(i);
			}
			
			long xn = Math.round((1 + Math.sqrt(1 + 8*i))/4);
			if (xn*(2*xn-1) == i) {
				hex.add(i);
			}
			
			long hn = Math.round((3 + Math.sqrt(9 + 40*i))/10);
			if ((hn*(5*hn-3))/2 == i) {
				hept.add(i);
			}
			
			long cn = Math.round((2 + Math.sqrt(4 + 12*i))/6);
			if (cn*(3*cn-2) == i) {
				oct.add(i);
			}
		}
		System.out.print(tri.size() + " " + sq.size() + " " + pent.size() + " ");
		System.out.println(hex.size() + " " + hept.size() + " " + oct.size());
		
		ArrayList<ArrayList<Long>> polys = new ArrayList<>(); polys.add(tri);
		polys.add(sq); polys.add(pent); polys.add(hex); polys.add(hept); polys.add(oct);
		for (int i = 0; i < polys.size(); i++) {
			for (int j = 0; j < polys.get(i).size(); j++) {
				if ((polys.get(i).get(j)%100)/10 == 0) {
					polys.get(i).remove(j);
					j--;
				}
			}
			System.out.print(polys.get(i).size()+" ");
		}
		System.out.println();
		
		long sum = 0;
		long[] nums = new long[6];
		for (long t : tri) {
			for (long s : sq) {
				for (long p : pent) {
					for (long x : hex) {
						for (long h : hept) {
							for (long c : oct) {
								nums = new long[] {t,s,p,x,h,c};
								if (cyclic(nums)) {
									sum = t+s+p+x+h+c;
									break;
								}
							}
						}
					}
				}
			}
			System.out.println(t);
		}
		
		System.out.println(sum);
	}
	
	private static boolean cyclic(long[] nums) {
		long temp;
		
		for (int i = 0; i < nums.length-1; i++) {
			boolean fnd = false;
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i]%100 == nums[j]/100) {
					fnd = true;
					temp = nums[j];
					nums[j] = nums[i+1];
					nums[i+1] = temp;
				}
			}
			
			if (!fnd) {
				return fnd;
			}
		}
		
		return nums[5]%100 == nums[0]/100;
	}
}