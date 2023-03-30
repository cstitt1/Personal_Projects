package prob101;

//import java.util.Arrays;

public class OptPoly {
	/*public static void main(String[] args) {
		//u(n) = 1 - n + n^2 - n^3 + n^4 - n^5 + n^6 - n^7 + n^8 - n^9 + n^10
		//            0 1   2     3      4       5        6         7         8           9           10           11
		long[] seq = {1,683,44287,838861,8138021,51828151,247165843,954437177,3138105961l,9090909091l,23775972551l,57154490053l};
		long sum = 1;
		String res = "1 + ";
		for (int k = 2; k < 12; k++) {
			long[][] coeff = new long[k][2];
			coeff = diff(Arrays.copyOf(seq, k),coeff);
		}
	}
	
	private static long[][] diff(long[] arr, long[][] res) {
		return res; //IDK WE NEED GAUSS-JORDAN I THINK. AHHHH
	}
	
	private static int fact(int n) {
		if (n <= 1) {
			return 1;
		} else {
			return fact(n-1) * n;
		}
	}*/
	
	/*	u(n) = n10 - n9 + n8 - n7 + n6 - n5 + n4 - n3 + n2 - n + 1
	u(n) = n10 - n9 + n8 - n7 + n6 - n5 + n4 - n3 + n(n - 1) + 1
	u(n) = n10 - n9 + n8 - n7 + n6 - n5 + n4 - n(n2 + n - 1) + 1
	u(n) = n10 - n9 + n8 - n7 + n6 - n5 + n(n3 - n2 + n - 1) + 1
	u(n) = n10 - n9 + n8 - n7 + n6 - n(n4 + n3 - n2 + n - 1) + 1
	u(n) = n10 - n9 + n8 - n7 + n(n5 - n4 + n3 - n2 + n - 1) + 1
	u(n) = n10 - n9 + n8 - n(n6 + n5 - n4 + n3 - n2 + n - 1) + 1
	u(n) = n10 - n9 + n(n7 - n6 + n5 - n4 + n3 - n2 + n - 1) + 1
	u(n) = n10 - n(n8 + n7 - n6 + n5 - n4 + n3 - n2 + n - 1) + 1
	u(n) = n(n9 - n8 + n7 - n6 + n5 - n4 + n3 - n2 + n - 1) + 1
	u(n) = n(n(n8 - n7 + n6 - n5 + n4 - n3 + n2 - n + 1) - 1) + 1
	u(n) = n(n(n(n7 - n6 + n5 - n4 + n3 - n2 + n - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n6 - n5 + n4 - n3 + n2 - n + 1) - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n(n5 - n4 + n3 - n2 + n - 1) + 1) - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n(n(n4 - n3 + n2 - n + 1) - 1) + 1) - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n(n(n(n3 - n2 + n - 1) + 1) - 1) + 1) - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n(n(n(n(n2 - n + 1) - 1) + 1) - 1) + 1) - 1) + 1) - 1) + 1
	u(n) = n(n(n(n(n(n(n(n(n(n - 1) + 1) - 1) + 1) - 1) + 1) - 1) + 1) - 1) + 1
	u is n - 1 * n + 1 * n - 1 * n + 1 * n - 1 * n + 1 * n - 1 * n + 1 * n - 1 * n + 1 */
	
	public static void main(String[] args) {
		long[] uArr = new long[13];
		for (int n = 1; n <= 13; n++) {
			long u = 1;
			u = u * n - 1; u = u * n + 1;
			u = u * n - 1; u = u * n + 1;
			u = u * n - 1; u = u * n + 1;
			u = u * n - 1; u = u * n + 1;
			u = u * n - 1; u = u * n + 1;
			uArr[n-1] = u;
		}
		
		//TODO: EXTRANEOUS
		System.out.print("[1: " + uArr[0]); for (int i = 1; i < uArr.length; i++) {System.out.print("," + (i+1) + ": " + uArr[i]);} System.out.println("]\n");
		
		
		// OP = 1 --> poly order = 0 --> u' = 1,... --> u' = 1 --> u' = 1,1,1,1,1,... vs u = 1,683,... --> BOP = 1
		// OP = 2 --> poly order = 1 --> u' = 1,683,... --> u' = ax+b ST a+b=1 & 2a+b=683
		long sumBOP = 1;
		for (int op = 2; op <= 11; op++) {
			// Setup needed variables
			long[][] nMat = new long[op][op];
			double[][] invMat = new double[op][op];
			long[] uMat = new long[op], coeffMat = new long[op];
			long valBOP = 0;
			
			// Initialize nMat and uMat
			for (int i = 0; i < op; i++) { //since op = nMat len
				int nVal = i + 1;
				
				for (int j = 0; j < op; j++) { // since op = nMat[i] len
					long power = op - (j + 1);
					nMat[i][j] = Math.round(Math.pow(nVal, power));
				}
				
				uMat[i] = uArr[i];
			}
			
			if (!hasInverse(nMat, invMat)) {System.out.println("Error"); return;}
			
			for (int i = 0; i < op; i++) {
				double coeffMatVal = 0;
				
				for (int j = 0; j < op; j++) {
					coeffMatVal += (invMat[i][j] * uMat[j]);
				}
				
				coeffMat[i] = Math.round(coeffMatVal);
			}
			
			//TODO: EXTRANEOUS
			System.out.print(op + ": ["); for (int i = 0; i < op; i++) {System.out.print((i==0?"":",") + coeffMat[i]);} System.out.print("] -- ");
			
			for (int n = 1; n > 0; n++) {
				long uVal = 0;
				
				for (int j = 0; j < op; j++) {
					long power = op - (j + 1);
					uVal += (coeffMat[j] * Math.round(Math.pow(n, power)));
				}
				
				if (uVal != uArr[n-1]) {valBOP = uVal; break;}
			}
			
			sumBOP += valBOP;
			
			//TODO: EXTRANEOUS
			System.out.println("BOP = " + valBOP);
		}
		
		System.out.print("\nSum of BOPs = " + sumBOP);
	}
	
	private static boolean hasInverse(long aMat[][], double inverse[][]) {
		int matSize = aMat.length, rowSize = aMat[0].length;
		if (matSize == 0 || rowSize == 0) {return false;}
		
		long[][] aAdjt = new long[matSize][rowSize];
		getAdjoint(aMat, aAdjt);
		
		long aDet = determinant(aMat, matSize);
		if (aDet == 0) {
			return false;
		}
		
		for (int i = 0; i < matSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				inverse[i][j] = aAdjt[i][j]/((double)aDet);
			}
		}
		
		return true;
	}
	
	private static void getAdjoint(long aMat[][], long aAdjt[][]) {
		int matSize = aMat.length, rowSize = aMat[0].length;
		
		if (matSize == 1 && rowSize == 1) {aAdjt[0][0] = 1; return;}
		
		for (int i = 0; i < matSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				int sign = ((i + j) % 2 == 0) ? 1 : -1;
				long[][] temp = new long[matSize][rowSize];
				
				getCofactor(aMat, temp, i, j, matSize);
				aAdjt[j][i] = sign * determinant(temp, matSize - 1);
			}
		}
	}
	
	private static long determinant(long aMat[][], int size) {
		if (size == 1) {return aMat[0][0];}
		
		long matDet = 0, sign = 1;
		long[][] temp = new long[size][size];
		
		for (int elem = 0; elem < size; elem++) {
			getCofactor(aMat, temp, 0, elem, size);
			matDet += sign * aMat[0][elem] * determinant(temp, size - 1);
			sign = -1 * sign;
		}
		
		return matDet;
	}
	
	private static void getCofactor(long aMat[][], long temp[][], int p, int q, int size) {
		int i = 0, j = 0;
		
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (r != p && c != q) {
					temp[i][j++] = aMat[r][c];
					if (j == size-1) {j = 0; i++;}
				}
			}
		}
	}
}













