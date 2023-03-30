package prob107;

import java.io.*;
import java.util.*;

public class MinNet {
	public static void main(String[] args) throws IOException {
		String[][] matrix = new String[40][40];
		String file = "C:\\Users\\cstit\\Desktop\\p107_network.txt";
		Scanner inp = new Scanner(new FileReader(file));
		HashMap<int[],Integer> edges = new HashMap<>(); //map of vert A to B to edge weight; verts labeled 0 to 40.
		
		for (int i = 0; i < 40; i++) {
			matrix[i] = inp.nextLine().split(",");
		}
		
		long maxSum = 0;
		for (int i = 0; i < 40; i++) {
			for (int j = i+1; j < 40; j++) {
				if (!matrix[i][j].equals("-")) {
					edges.put(new int[] {i,j}, Integer.parseInt(matrix[i][j]));
					edges.put(new int[] {j,i}, Integer.parseInt(matrix[i][j])); //needed both for low-numbered vertices
					maxSum += Integer.parseInt(matrix[i][j]);
				}
			}
		}
		
		//using prim's algorithm
		long sum = 0;
		ArrayList<Integer> verts = new ArrayList<>();
		verts.add(0);
		ArrayList<int[]> min = new ArrayList<>();
		ArrayList<Integer> wghts = new ArrayList<>();
		while (verts.size() < 40) {
			int[] mEdge = {-1,-1};
			int mWght = 1000000;
			for (int[] key : edges.keySet()) {
				if (verts.contains(key[0]) && !verts.contains(key[1]) && edges.get(key) < mWght) {
					mEdge = key;
					mWght = edges.get(key);
				}
			}
			verts.add(mEdge[1]);
			sum += mWght;
			min.add(mEdge);
			wghts.add(mWght);
		}
		
		//verts.sort(null);
		System.out.println(verts.toString());
		
		System.out.print("<");
		for (int[] key : min) {
			System.out.print("["+key[0]+","+key[1]+"], ");
		}
		System.out.println(">");
		
		wghts.sort(null);
		System.out.println(wghts.toString());
		ArrayList<Integer> vals = new ArrayList<>();
		vals.addAll(edges.values());
		vals.sort(null);
		while (vals.contains(0)) {
			vals.remove(0);
		}
		System.out.println(vals.toString());
		
		for (int[] key : edges.keySet()) {
			if (edges.get(key) == 6) {
				System.out.print("["+key[0]+","+key[1]+"], ");
			}
		}
		System.out.println();
		
		System.out.println(maxSum - sum);
		inp.close();
	}
}