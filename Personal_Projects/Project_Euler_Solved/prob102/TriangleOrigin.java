package prob102;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TriangleOrigin {

	public static void main(String[] args) throws FileNotFoundException {
		String file = "C:\\Users\\cstit\\OneDrive\\Desktop\\p102_triangles.txt";
		Scanner inp = new Scanner(new FileReader(file));
		
		final int planePoints = 3; final int numAxes = 2;
		int originCount = 0;
		
		while(inp.hasNextLine()) {
			String[] csvArr = inp.nextLine().split(","); //len 6
			int[][] shapePoints = new int[planePoints][numAxes]; //0 -- 00, 1 -- 01, 2 -- 10, 3 -- 11, 4 -- 20, 5 -- 21
			
			int csvInd = 0;
			for (int i = 0; i < planePoints; i++) {
				for (int j = 0; j < numAxes; j++) {
					shapePoints[i][j] = Integer.decode(csvArr[csvInd++]);
				}
			}
			
			double[] axisIntersects = {1,-1,1,-1}; //where y = 0 & x = 0 respectively; 0=left_x, 1=right_x, 2=bottom_y, 3=top_y -- init vals cannot be correct
			for (int i = 0; i < planePoints - 1; i++) {
				for (int j = i + 1; j < planePoints; j++) {
					int[] pointA = shapePoints[i], pointB = shapePoints[j];
					// Each set of 2 points forms a line segment of slope (B_y - A_y)/(B_x - A_x), denoted m
					// y = m * x + b --> b = y' - m * x'
					
					if (pointB[0] == pointA[0]) {
						// vertical line of equation x = x' whereby x' is any and all x points -- pointA[0] = pointB[0] = ...
						// therefore only 1 intercept exists -- on (x', 0)
						int signInd = pointA[0] < 0 ? 0 : 1;
						if ((pointA[1] <= 0 && pointB[1] >= 0) || (pointA[1] >= 0 && pointB[1] <= 0)) {
							axisIntersects[signInd] = pointA[0]; continue;
						}
					}
					
					if (pointB[1] == pointA[1]) {
						// horizontal line of equation y = y' whereby y' is any and all y points -- pointA[1] = pointB[1] = ...
						// therefore only 1 intercept exists -- on (0, y')
						int signInd = pointA[1] < 0 ? 2 : 3;
						if ((pointA[0] <= 0 && pointB[0] >= 0) || (pointA[0] >= 0 && pointB[0] <= 0)) {
							axisIntersects[signInd] = pointA[1]; continue;
						}
					}
					
					double m = (pointB[1] - pointA[1])/(double) (pointB[0] - pointA[0]);
					double bVal = pointA[1] - m * pointA[0];
					
					/*
					 * for all lines,
					 * 		when y = 0, at x-intercept with value x = -b/m denoted xInt -- if + right of y-axis, if - left of y-axis
					 * 		when x = 0, at y-intercept with value y = b denoted yInt -- if + on top of x-axis, if - on bottom of x-axis
					 * 		for (xInt, 0) and (0, yInt) must be between bounds of (xA, yA) and (xB, yB) and if so add to proper spot in array
					 */
					
					double xInt = (-1 * bVal) / m; int xSign = xInt < 0 ? 0 : 1;
					double yInt = bVal; int ySign = yInt < 0 ? 2 : 3;
					
					if ((xInt <= pointA[0] && pointB[0] <= xInt) || (xInt <= pointB[0] && pointA[0] <= xInt)) {axisIntersects[xSign] = xInt;}
					if ((yInt <= pointA[1] && pointB[1] <= yInt) || (yInt <= pointB[1] && pointA[1] <= yInt)) {axisIntersects[ySign] = yInt;}
					
					/*if (mX != 0) {
						double yInt = cVal / (double) mX;
						int signInd = yInt < 0 ? 2 : 3;
						if ((yInt <= pointA[1] && pointB[1] <= yInt) || (yInt <= pointB[1] && pointA[1] <= yInt)) {
							axisIntersects[signInd] = yInt;
						}
					}
					
					if (mY != 0) {
						double xInt = cVal / (double) mY;
						int signInd = xInt < 0 ? 0 : 1;
						if ((xInt <= pointA[0] && pointB[0] <= xInt) || (xInt <= pointB[0] && pointA[0] <= xInt)) {
							axisIntersects[signInd] = xInt;
						}
					}*/
				}
			}
			
			if (axisIntersects[0] <= 0 && 0 <= axisIntersects[1] && axisIntersects[2] <= 0 && 0 <= axisIntersects[3]) {
				System.out.println("Triangle (("+csvArr[0]+","+csvArr[1]+"),("+csvArr[2]+","+csvArr[3]+"),("+csvArr[4]+","+csvArr[5]+")) -- YES");
				originCount++;
			} else {
				System.out.println("Triangle (("+csvArr[0]+","+csvArr[1]+"),("+csvArr[2]+","+csvArr[3]+"),("+csvArr[4]+","+csvArr[5]+")) -- NO");
			}
		}
		
		
		System.out.println(originCount);
		inp.close();
	}
}