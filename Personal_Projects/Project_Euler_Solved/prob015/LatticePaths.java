package prob015;

import java.util.ArrayList;

public class LatticePaths {

	public static void main(String[] args) {
		ArrayList<ArrayList<Point>> lists = new ArrayList<>();
		lists.add(new ArrayList<Point>());
		int ind = 0;
		lists.get(ind).add(new Point(0,0));
		double count = 1;
		
		for (int j = 1; j < 21; j++) {
			count=1;
			ind=0;
			lists = new ArrayList<>();
			lists.add(new ArrayList<Point>());
			lists.get(ind).add(new Point(0,0));
			
			for (int k = 0; k <= ind; k++) {
				while (true) {
					boolean done = true;
					for (Point point : lists.get(k)) {
						if (point.getX() != j || point.getY() != j) {
							done = false;
						}
					}

					if (done) {
						break;
					}

					for (int i = 0; i < lists.get(k).size(); i++) {
						int x = lists.get(k).get(i).getX();
						int y = lists.get(k).get(i).getY();

						if (x == j && y == j) {
							continue;
						}

						if (x == j) {
							lists.get(k).get(i).setY(y + 1);
						} else {
							lists.get(k).get(i).setX(x + 1);
							if (y != j) {
								try {
									lists.get(k).add(new Point(x, y + 1));
								} catch (Exception e) {
									try {
										lists.get(ind).add(new Point(x, y+1));
									} catch (Exception ePrime) {
										lists.add(new ArrayList<Point>());
										ind = ind + 1;
										lists.get(ind).add(new Point(x, y+1));
									}
								}
								count = count + 1;
							}
						}
					}
				}
			}
			System.out.println(j + ": " + count);
		}
	}
}
