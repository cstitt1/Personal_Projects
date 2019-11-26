package fnaf;

public class HRY223 {
	public static void main(String[] args) {
		String base4 = "223332333332323323223222223";
		double sum4 = 0, sum2=0;
		String[] letters = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		for (int i=0; i<base4.length(); i++) {
			sum4 = sum4 + Integer.parseInt(base4.substring(base4.length()-(i+1), base4.length()-i))*Math.pow(4, i);
			sum2 = sum2 + (Integer.parseInt(base4.substring(base4.length()-(i+1), base4.length()-i))-2)*Math.pow(2, i);
		}
		
		String num4 = (""+sum4), num2 = (""+sum2);
		num4 = num4.substring(0,num4.indexOf(".")) + num4.substring(num4.indexOf(".")+1,num4.indexOf("E"));
		num2 = num2.substring(0,num2.indexOf(".")) + num2.substring(num2.indexOf(".")+1,num2.indexOf("E"));
		
		String[] words = {"",""};
		String[] nums = {num4, num2};
		for (int i=0; i<nums.length; i++) {
			for (int j=0; j<nums[i].length(); j++) {
				words[i] = words[i] + letters[Integer.parseInt(nums[i].substring(j, j+1))];
			}
		}
		
		System.out.println(sum4 + " -- " + words[0]);
		System.out.println(sum2 + " -- " + words[1]);
	}
}