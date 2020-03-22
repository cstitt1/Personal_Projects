package prob082;

import java.io.*;
import java.util.*;

public class MinPath3Ways {

	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Documents\\PE082.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		long[][] matrix = new long[80][80];
		for (int i=0; i<80; i++) {
			String[] line = (in.nextLine()).split(",");
			for (int j=0; j<80; j++) {
				matrix[i][j] = Integer.parseInt(line[j]);
				
			}
		}
		in.close();
		
		long min = -1;
		
		for (int row = 0; row < 80; row++) {
			long base = matrix[row][0];
			long[] costs = new long[80];
			HashMap<int[], Long> verts = new HashMap<>();
			long minNex = Long.MAX_VALUE, minRow = -1, minCol = -1;
			
			verts.put(new int[] {row,1}, matrix[row][1]);
			
			while (true) {
				for (int[] pt : verts.keySet()) {
					int nR, nC;
					if (pt[0] > 0) {
						nR = pt[0]-1;
						nC = pt[1];
						if (!verts.keySet().contains(new int[] {nR,nC}) && matrix[nR][nC] < minNex) {
							
						}
					}
				}
			}
		}
	}
}