package prob144;

public class LaserRefl {
	public static void main(String[] args) {
		double x0 = 0, y0 = 10.1, xR = 1.4, yR = -9.6;
		int n = 1;
		System.out.println("Bounce number "+n+" from ("+x0+","+y0+") to ("+xR+","+yR+")");
		
		while ((Math.abs(xR-0.01)>0.001)&&(Math.abs(yR-10)>0.001) && n <= 2) {
			n++;
			
			double mT = -4*xR/yR, mN = -1/mT;
			double bN = yR - mN*xR, b0 = y0 - mT*x0;
			
			double xI = (b0 - bN)/(mN - mT);
			
			double xF = 2*xI - x0;
			double yF = mT*xF + b0;
			
			x0 = xR; y0 = yR; xR = xF; yR = yF;
			System.out.println("Bounce number "+n+" from ("+x0+","+y0+") to ("+xR+","+yR+")");
		}
		
		System.out.println("Bounced out after "+n+" bounces.");
	}
}