package prob019;

public class SunCount {
	
	public static void main(String[] args) {
		int month=1;
		int date = 1;
		int year = 1900;
		int day = 2; //Monday
		int count=0;
		
		while (year != 2000 || month != 12 || date != 31) {
			if (date==1 && year > 1900 && day==1) {
				count++;
			}
			
			if (day==7) {
				day=1;
			} else {
				day++;
			}
			
			if (date==30 && (month==9 || month==4 || month==6 || month==11)) {
				month++;
				date=1;
			} else if (month==2 && date>=28) {
				if (year%4==0 && date!=29) {
					date++;
				} else {
					month++;
					date=1;
				}
			} else if (date==31) {
				if (month==12) {
					year++;
					month = 1;
					date = 1;
					System.out.println(year-1 + " is over");
				} else {
					month++;
					date=1;
				}
			} else {
				date++;
			}
		}
		
		System.out.println(count);
	}

}
