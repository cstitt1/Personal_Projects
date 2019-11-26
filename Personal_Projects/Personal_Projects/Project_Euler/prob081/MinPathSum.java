package prob081;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MinPathSum {
	public static void main(String[] args) {
		InputStream f = null;
		try {
			f = new FileInputStream("C:\\Users\\cstit\\Documents\\PE081.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		int[][] matrix = new int[80][80];
		for (int i=0; i<80; i++) {
			String[] line = (in.nextLine()).split(",");
			for (int j=0; j<80; j++) {
				matrix[i][j] = Integer.parseInt(line[j]);
			}
		}
		in.close();
		
//		int[][] matrix = {{131,673,234,103,18},
//						  {201,96,342,965,150},
//						  {630,803,746,422,111},
//						  {537,699,497,121,956},
//						  {805,732,524,37,331}};
		
		System.out.println(minPath(matrix,matrix.length-1, matrix[matrix.length-1].length-1));
	}
	
	private static int minPath(int cost[][], int row, int col)
    {
        int i, j;
        int tc[][]=new int[row+1][col+1];
 
        tc[0][0] = cost[0][0];
 
        /* Initialize first column of total cost(tc) array */
        for (i = 1; i <= row; i++)
            tc[i][0] = tc[i-1][0] + cost[i][0];
 
        /* Initialize first row of tc array */
        for (j = 1; j <= col; j++)
            tc[0][j] = tc[0][j-1] + cost[0][j];
 
        /* Construct rest of the tc array */
        for (i = 1; i <= row; i++)
            for (j = 1; j <= col; j++)
                tc[i][j] = (tc[i-1][j]<tc[i][j-1]?tc[i-1][j]:tc[i][j-1]) + cost[i][j];
 
        return tc[row][col];
    }
}