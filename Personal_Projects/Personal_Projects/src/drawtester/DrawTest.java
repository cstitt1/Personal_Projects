package drawtester;
import javax.swing.JFrame;
public class DrawTest {
	/**
	 * @author cstit
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame tst = new JFrame();
		tst.setSize(300,400);
		tst.setTitle("Test Frame");
		tst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tst.setVisible(true);
		DrawComponent shapes = new DrawComponent();
		tst.add(shapes);
	}
}