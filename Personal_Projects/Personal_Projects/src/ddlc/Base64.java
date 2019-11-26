package ddlc;

import java.io.*;
import java.util.*;

public class Base64 {
	private HashMap<Integer, String> coded = new HashMap<>();
	private HashMap<String, Integer> rev = new HashMap<>();
	
	public Base64() {
		String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		for (int i=0; i<letters.length; i++) {
			coded.put(i, letters[i]);
			rev.put(letters[i], i);
			
			coded.put(i+26, letters[i].toLowerCase());
			rev.put(letters[i].toLowerCase(), i+26);
			
			if (i<=9) {
				coded.put(i + 52, "" + i);
				rev.put("" + i, i + 52);
			}
		}
		
		coded.put(62, "+");
		coded.put(63, "/");
		rev.put("+", 62);
		rev.put("/", 63);
	}
	
	public File encode(File decoded) {
		File file = new File("C:\\Users\\cstit\\Desktop\\Base64encoded.txt");
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream(decoded);
			out = new BufferedWriter(new FileWriter(file));
		} catch (Exception e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		ArrayList<String> linesT = new ArrayList<>();
		ArrayList<int[]> breaks = new ArrayList<>();
		linesT.add("");
		int ind = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (linesT.get(ind).length() + line.length() > 999) {
				int split = 999 - linesT.get(ind).length(); //(0,split) and (split)
				breaks.add(new int[] {ind, linesT.get(ind).length()});
				linesT.set(ind, linesT.get(ind)+line.substring(0, split));
				ind++;
				linesT.add(line.substring(split));
			} else {
				if (!(ind==0 && linesT.get(ind).length()==0)) {
					breaks.add(new int[] { ind, linesT.get(ind).length() });
				}
				linesT.set(ind, linesT.get(ind)+line);
			}
		}
		in.close();
		
		ArrayList<String> linesB64 = new ArrayList<>();
		linesB64.add("");
		ind = 0;
		int bInd = 0;
		ArrayList<Character> chars = new ArrayList<>();
		for (int i=0; i<linesT.size(); i++) {
			String lineT = linesT.get(i);
			for (int j=0; j<lineT.length(); j++) {
				if (chars.size()==3) {
					String bin = "";
					while (!chars.isEmpty()) {
						bin = bin + toBinN(chars.remove(0),8);
					}
					for (int k=0; k<bin.length(); k+=6) {
						linesB64.set(ind, linesB64.get(ind)+coded.get(fromBinN(bin.substring(k, k+6), 6)));
					}
					if (linesB64.get(ind).length()==1000) {
						linesB64.add("");
						ind++;
					}
				}
				
				if (breaks.size()>bInd) {
					int[] bNums = breaks.get(bInd);
					if (bNums[0]==i && bNums[1]==j) {
						chars.add('\n');
						j--;
						bInd++;
						continue;
					}
				}
				
				chars.add(lineT.charAt(j));
			}
		}
		
		if (!chars.isEmpty()) {
			String bin = "";
			int i;
			for (i=0; i<chars.size(); i++) {
				bin = bin + toBinN(chars.get(i),8);
			}
			
			while (bin.length()%6!=0) {
				bin = bin + "0";
			}
			
			for (int k=0; k<bin.length(); k+=6) {
				linesB64.set(ind, linesB64.get(ind)+coded.get(fromBinN(bin.substring(k, k+6), 6)));
			}
			
			for (int j=0; j<(3-i); j++) {
				linesB64.set(ind, linesB64.get(ind)+"=");
			}
		}
		
		for (int i=0; i<linesB64.size(); i++) {
			try {
				out.write(linesB64.get(i));
				if (i!=linesB64.size()-1) {
					out.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public File decode(File encoded, String name) {
		File file = new File("C:\\Users\\cstit\\Desktop\\"+name+".txt");
		InputStream f = null;
		BufferedWriter out = null;
		try {
			f = new FileInputStream(encoded);
			out = new BufferedWriter(new FileWriter(file));
		} catch (Exception e) {
			System.out.println("File not found");
		}
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(f)));
		
		ArrayList<String> linesT = new ArrayList<>();
		while (in.hasNextLine()) {
			linesT.add(in.nextLine());
		}
		in.close();
		
		ArrayList<String> bins = new ArrayList<>();
		for (String line : linesT) {
			for (int i=0; i<line.length(); i+=4) {
				String bin = "";
				String[] chars = line.substring(i,i+4).split("");
				for (int j=0; j<arrayIndexOf(chars, "=", 4); j++) {
					bin = bin + toBinN(rev.get(chars[j]),6);
				}
				
				while (bin.length()%8!=0) {
					bin = bin.substring(0, bin.length()-1);
				}
				
				bins.add(bin);
			}
		}
		
		for (String bin : bins) {
			for (int i=0; i<bin.length(); i+=8) {
				String character = (new Character((char) fromBinN(bin.substring(i,i+8),8))).toString();
				try {
					if (!character.equals("\n")) {
						out.write(character);
					} else {
						out.newLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		encoded.delete();
		return file;
	}
	
	private int arrayIndexOf(String[] chars, String search, int def) {
		for (int i=0; i<chars.length; i++) {
			if (search.equals(chars[i])) {
				return i;
			}
		}
		
		return def;
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