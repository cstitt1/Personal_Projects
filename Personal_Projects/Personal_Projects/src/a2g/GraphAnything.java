package a2g;
import java.util.Scanner;
public class GraphAnything {
	public static void main(String[] args) {
		Scanner eqtype = new Scanner(System.in);
		String eqFull = eqtype.nextLine();
		Grapher eqGraph = new Grapher(eqFull);
		eqGraph.GraphEquation();
		eqtype.close();
	}
}