package a2g;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.geom.*;
@SuppressWarnings("serial")
public class Grapher extends JComponent{
	private JFrame tst;
	private Graphics2D outline;
	private double x=0,y=0;
	//private String equation="";
	
	public Grapher(double xLE, double yLE) {
		tst = new JFrame();
		tst.setSize(650,650);
		tst.setTitle("Your Graph");
		tst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tst.setBackground(Color.WHITE);
		x=xLE;
		y=yLE;
	}
	
	public Grapher(String eq) {
		//equation = eq;
		//String array with operations as methods if operation sign detected
	}
	
	public void GraphEquation() {
		Grapher linComp = new Grapher(x,y);
		tst.add(linComp);
		tst.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		outline = (Graphics2D) g;
		outline.setColor(Color.BLACK);
		for (int i=0; i<=600; i+=30) {
			Line2D.Double ydraw = new Line2D.Double(i,0,i,600);
			Line2D.Double xdraw = new Line2D.Double(0,i,600,i);
			outline.draw(xdraw);
			outline.draw(ydraw);
		}
		
		outline.setColor(Color.BLUE);
		Line2D.Double xyLine = new Line2D.Double(300,300,300+30*x,300-30*y);
		outline.draw(xyLine);
	}
}