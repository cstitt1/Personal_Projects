package game2;

import processing.core.*;

@SuppressWarnings("serial")
class PieceDraw extends PApplet{
	private int dim = 79;
	private int[] pix;
	
	PieceDraw(int[] pix) {
		this.pix = pix;
	}
	
	private int locDecode(int coor) {
		int loc = 0;
		loc += dim*((coor/10)%10-1);
		loc += dim*dim*8*(8-coor%10);
		return loc;
	}
	
	public int[] pawn(int col, int coor) {
		//Singles
		double[] lim1 = {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,20/3.0,19/3.0,6,17/3.0,16/3.0,16/3.0,16/3.0,16/3.0,16/3.0};
		double[] lim2 = {4.5,11/3.0,3.5,3.4,3.3,3.2,3.1,3.05,3,2.9,2.85,2.8,2.75,2.7,2.65,2.6,2.55,2.5,4,4,4,4,4,4,2.85,2.15,3};
		double[] lim3 = {3.4,3.75,4,4.1,4.3,4.5,4.65,4.8,4.95,4.97,5,5,5,5,4.97,4.8,4.65,4.5,4.3,4.1,4,3.75,3.4,3,2,1,0};
		double[][] lims = {lim1,lim2,lim3};
		
		int s1 = lim1.length, s2 = lim2.length, s3 = lim3.length, s, off;
		int start = locDecode(coor);
		for (int i = s1+s2+s3-1; i >= 0; i--) {
			s = 2; off = s1+s2;
			if (i < s1+s2) {s--; off -= s2; }
			if (i < s1) {s--; off -= s1;}
			
			int lim = (int) ((dim/14.0*(7-lims[s][i-off]))+0.5);
			int mirror = (int) ((dim/14.0*(7+lims[s][i-off]))+0.5);
			
			for (int j = lim; j < mirror; j++) {
				pix[start+j] = color(col,col,col);
			}
			
			start += dim*8;
		}
		
		return pix;
	}
	
	public int[] knight(int col, int coor) {
		//Pairs in 1D arrays
		double[][] lim1 = {{1.5,14.5},{1.5,14.5},{1.5,14.5},{1.5,14.5},{1.5,14.5},{1.5,14.5},{1.5,14.5},{1.75,14.25},{1.25,14.75},{1,15},{1,15},{1,15},{1,15},{1,15},{1,15}};
		double[][] lim2 = {{4/3.0,44/3.0},{5/3.0,43/3.0},{2,14},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5},{2.5,13.5}};
		double[][] lim3 = {{2.75,13.5},{2.9,13.5},{3,13.5},{3.15,13.5},{3.35,13.25},{3.5,13.2},{3.5,13.1},{3.5,13},{3,13},{2.8,12.8},{2.4,12.4},{2,12}};
		double[][] lim4 = {{1.85,11.5},{1.65,11},{1.5,10.5,13,14},{1.35,10.5,12.3,14.5},{1.15,10.5,11.6,14.9},{1,10.5,11,15.1},{0.85,15.25},{0.65,15.5},{0.5,15.5}};
		double[][] lim5 = {{0.4,15.5},{0.3,15.8},{0.2,16},{0.1,16},{0.05,16},{0,15.6},{0,15.5},{0,15.2},{0,15},{0.05,14.5},{0.1,13.9},{0.2,13.2},{0.3,12.8},{0.6,12.2}};
		double[][] lim6 = {{1,11.8},{1,11.6},{1.05,11.3},{1.1,11},{1.2,10.6},{1.5,10.3},{2,10},{2.3,9.8},{2.7,9.3},{3,9},{3.5,8.5},{4,8},{5,7}};//extra on front from above
		double[][][] lims = {lim1,lim2,lim3,lim4,lim5,lim6};
		
		int[] ss = {lim1.length,lim2.length,lim3.length,lim4.length,lim5.length,lim6.length};
		int sum = ss[0]+ss[1]+ss[2]+ss[3]+ss[4]+ss[5], s, off;
		int start = locDecode(coor);
		for (int i = sum-1; i >= 0; i--) {
			s = 5; off = sum-ss[5];
			if (i < ss[0]+ss[1]+ss[2]+ss[3]+ss[4]) {s--; off -= ss[4];}
			if (i < ss[0]+ss[1]+ss[2]+ss[3]) {s--; off -= ss[3];}
			if (i < ss[0]+ss[1]+ss[2]) {s--; off -= ss[2];}
			if (i < ss[0]+ss[1]) {s--; off -= ss[1];}
			if (i < ss[0]) {s--; off -= ss[0];}
			
			for (int j = 0; j < lims[s][i-off].length; j+=2) {
				int lim = (int) ((dim/16.0*(lims[s][i-off][j]))+0.5);
				int mirror = (int) ((dim/16.0*(lims[s][i-off][j+1]))+0.5);
				
				for (int k = lim; k < mirror; k++) {pix[start+k] = color(col,col,col);}
			}
			
			start += dim*8;
		}
		return pix;
	}
	
	public int[] rook(int col, int coor) {
		//Singles
		double[] lim1 = {7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,6.75,6.5,6.25,6,5.5,5.5,5.5,5.5,5.5,5.5,4.2,4,3.9,3.8,3.7,3.6,3.55,3.5,3.5,3.5,3.5};
		double[] lim2 = {3.5,3.5,3.5,3.5,3.5,3.5,3.5,3.5,3.5,3.75,4,4.25,4.25,4.25,4.25,4.5,4.75,5,5.3,5.7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5.75,5.25,5};
		double[][] lims = {lim1,lim2};
		
		int s1 = lim1.length, s2 = lim2.length, s, off;
		int start = locDecode(coor);
		for (int i = s1+s2-1; i >= 0; i--) {
			s = 1; off = s1;
			if (i < s1) {s--; off -= s1;}
			
			int lim = (int) ((dim/14.0*(7-lims[s][i-off]))+0.5);
			int mirror = (int) ((dim/14.0*(7+lims[s][i-off]))+0.5);
			
			for (int j = lim; j < mirror; j++) {
				pix[start+j] = color(col,col,col);
			}
			
			start += dim*8;
		}
		
		return pix;
	}
	
	public int[] bishop(int col, int coor) {
		//Singles
		double[] lim1 = {5,5,5,5,5,5,5,5,5,5,5,5,4.75,4.25,4,4,4,3.5,3.2,3.1,3,26/9.0,25/9.0,24/9.0,23/9.0,22/9.0,21/9.0,20/9.0,19/9.0,2,2,2,2};
		double[] lim2 = {2,2,2,2,2,3,3.1,3.3,3.3,3.1,3,3,2.8,2.4,2,2.3,2.6,2.7,2.8,2.95,3,3,3,3,3,3,3,3,3,3,2.95,2.9,2.8,2.5,2.2,2,1.9,1.4,1,1,1,1,0.75,0.6,0.5};
		double[][] lims = {lim1,lim2};
		
		int s1 = lim1.length, s2 = lim2.length, s, off;
		int start = locDecode(coor);
		for (int i = s1+s2-1; i >= 0; i--) {
			s = 1; off = s1;
			if (i < s1) {s--; off -= s1;}
			
			int lim = (int) ((dim/10.0*(5-lims[s][i-off]))+0.5);
			int mirror = (int) ((dim/10.0*(5+lims[s][i-off]))+0.5);
			
			for (int j = lim; j < mirror; j++) {
				pix[start+j] = color(col,col,col);
			}
			
			start += dim*8;
		}
		
		return pix;
	}
	
	public int[] queen(int col, int coor) {
		//Singles
		double[] lim1 = {5,5,5,5,5,5,5,5,5,4.9,4.75,4.5,4.25,4.1,4,4,4,4,3.5,3,3,26/9.0,25/9.0,24/9.0,23/9.0,22/9.0,21/9.0,20/9.0,19/9.0,2,2,2,2,2,2,2};
		double[] lim2 = {2,2,2,2,2,2,2,2,2,2.5,3,2.75,2.6,2.55,2.5,2.5,2.5,2.5,2.5,2,2.2,2.4,2.6,2.8,2.9,3,3,2.9,2.6,2.3,2,1.5,1.5,1.5,1.25,1,0.5,0.5,0.5,0.5,0.25,0.05};
		double[][] lims = {lim1,lim2};
		
		int s1 = lim1.length, s2 = lim2.length, s, off;
		int start = locDecode(coor);
		for (int i = s1+s2-1; i >= 0; i--) {
			s = 1; off = s1;
			if (i < s1) {s--; off -= s1;}
			
			int lim = (int) ((dim/10.0*(5-lims[s][i-off]))+0.5);
			int mirror = (int) ((dim/10.0*(5+lims[s][i-off]))+0.5);
			
			for (int j = lim; j < mirror; j++) {
				pix[start+j] = color(col,col,col);
			}
			
			start += dim*8;
		}
		
		return pix;
	}
	
	public int[] king(int col, int coor) {
		//Singles
		double[] lim1 = {5,5,5,5,5,5,5,5,5,4.8,4.4,4.2,4,3.75,3.75,3.75,3.75,3,3,3,3,2.5,2.35,2.2,2.1,2.05,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3.2,3.3,3.2,3};
		double[] lim2 = {2.5,2.5,2.5,2.5,2.5,2.5,2.65,2.85,3,3.15,3.35,3.5,3.5,3.5,3.5,3.5,3.25,3,2.5,1.5,1,3,3,3,3,3,3,1,1,1,1,1,1};
		double[][] lims = {lim1,lim2};
		
		int s1 = lim1.length, s2 = lim2.length, s, off;
		int start = locDecode(coor);
		for (int i = s1+s2-1; i >= 0; i--) {
			s = 1; off = s1;
			if (i < s1) {s--; off -= s1;}
			
			int lim = (int) ((dim/10.0*(5-lims[s][i-off]))+0.5);
			int mirror = (int) ((dim/10.0*(5+lims[s][i-off]))+0.5);
			
			for (int j = lim; j < mirror; j++) {
				pix[start+j] = color(col,col,col);
			}
			
			start += dim*8;
		}
		
		return pix;
	}
}