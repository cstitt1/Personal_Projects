package drawtester;
import java.awt.*;
import java.awt.geom.*;

import javax.swing.JComponent;
@SuppressWarnings("serial")

/**
 * A class to test drawing things.
 * @author cstit
 */
public class DrawComponent extends JComponent {
	/**
	 * A method to draw things.
	 * @param g A Graphics object
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Rectangle box = new Rectangle(5,10,20,30);
		Ellipse2D.Double ell = new Ellipse2D.Double(5,10,20,30);
		Ellipse2D.Double cir = new Ellipse2D.Double(5,10,20,20);
		Line2D.Double seg = new Line2D.Double(15,20,15,40);
		Point2D.Double from = new Point2D.Double(5, 20);
		Point2D.Double to = new Point2D.Double(25, 20);
		Line2D.Double segment = new Line2D.Double(from, to);
		g2d.draw(box);
		g2d.draw(ell);
		g2d.draw(cir);
		g2d.setColor(Color.GREEN);
		g2d.fill(cir);
		g2d.setColor(Color.RED);
		g2d.draw(seg);
		g2d.draw(segment);
		g2d.drawString("Message", 25, 25);
	}
}