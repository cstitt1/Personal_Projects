package ddlc;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BinarySquare {
	public BinarySquare() {}
	
	public File toSquare(File file, String name) {
		File out = new File("C:\\Users\\cstit\\Desktop\\"+name+".jpg");
		InputStream stream = null;
		try {
			stream = new FileInputStream(file);
		} catch (Exception e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(stream)));
		
		ArrayList<String> bins = new ArrayList<>();
		bins.add("");
		while (in.hasNextLine()) {
			String line = in.nextLine();
			for (int i=0; i<line.length(); i++) {
				bins.set(bins.size()-1, bins.get(bins.size()-1)+toBinN(line.charAt(i),7));
				if (bins.get(bins.size()-1).length()==1400) {
					bins.add("");
				}
			}
		}
		in.close();
		
		int area = 1400*(bins.size()-1) + bins.get(bins.size()-1).length(), len=area/7, wid=7;
		for (int i=8; i<area/7; i++) {
			if (area%i==0) {
				len = area/i;
				wid = i;
			}
		}
		
		int small = wid < len ? wid : len;
		int large = area/small;
		
		Picture pic = new Picture(small, large);
		int x = 0, y = 0;
		for (String bin : bins) {
			for (char digit : bin.toCharArray()) {
				int val = digit - '0';
				pic.getPixel(x, y).setColor(new Color(255*val, 255*val, 255*val));
				x++;
				if (x==pic.getWidth()) {
					x=0;
					y++;
				}
			}
		}
		
		pic.write(out.getAbsolutePath());
		file.delete();
		return out;
	}
	
	public File fromSquare(File file) {
		File out = new File("C:\\Users\\cstit\\Desktop\\SquareDecoded.txt");
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter(out));
		} catch (Exception e) {
			System.out.println("File not found");
		}
		
		ArrayList<String> bins = new ArrayList<>();
		bins.add("");
		Picture pic = new Picture(file.getAbsolutePath());
		for (Pixel[] pixels : pic.getPixels2D()) {
			for (Pixel pixel : pixels) {
				int val = pixel.colorDistance(new Color(235,235,235))<35 ? 1 : 0;
				bins.set(bins.size()-1, bins.get(bins.size()-1)+val);
				if (bins.get(bins.size()-1).length() == 1400) {
					bins.add("");
				}
			}
		}
		
		String line = "";
		for (String bin : bins) {
			for (int i=0; i<bin.length(); i+=7) {
				line = line + (new Character((char) fromBinN(bin.substring(i, i+7), 7))).toString();
				if (line.length()==1000) {
					try {
						write.write(line);
						write.newLine();
						line = "";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if (line.length() != 1000) {
			try {
				write.write(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	private String toBinN(int num, int n) {
		String bits = "";
		while (num > 0) {
			bits = num%2 + bits;
			num = num/2;
		}
		
		while (bits.length()<n) {
			bits = "0" + bits;
		}
		
		return bits;
	}
	
	private int fromBinN(String bin, int n) {
		int sum = 0;
		for (int i=0; i<bin.length(); i++) {
			sum = sum + Integer.parseInt(bin.substring(i, i+1))*((int) Math.pow(2, n-(i+1)));
		}
		return sum;
	}
}