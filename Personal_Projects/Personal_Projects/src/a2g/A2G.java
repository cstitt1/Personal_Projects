package a2g;
import java.util.Scanner;
import java.lang.Math;
/**
 *
 * @author cstit
 */
public class A2G {
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        System.out.println("What is the degrees measurement of the reference angle?");
        Scanner deg = new Scanner(System.in);
        double angle = deg.nextDouble();
        angle*=((Math.PI)/180);
        System.out.println("What is the total distance or hypotenuse of the triangle?");
        Scanner cval = new Scanner(System.in);
        double dist = cval.nextDouble();
        double cosa = Math.cos(angle);
        double sina = Math.sin(angle);
        double x = cosa*dist;
        double y = sina*dist;
        System.out.println("About "+x+" x-units and");
        System.out.println(y+" y-units away from the origin.");
        Grapher ln = new Grapher(x,y);
        ln.GraphEquation();
    }
}