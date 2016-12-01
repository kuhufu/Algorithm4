import edu.princeton.cs.algs4.Date;

public class SmartDate{	
	private final int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	private int day;
	private int month;
	private int year;
	
	public SmartDate(int month, int day, int year) {
		if(!isValid(month, day, year)) throw new IllegalArgumentException("invalid date");
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public int year(){
		return this.year;
	}
	
	public int month(){
		return this.month;
	}
	
	public int day(){
		return this.day;
	}
	
	private boolean isValid(int month, int day, int year){
		if (month < 1 || month > 12) return false;
		if (day < 1 || day > DAYS[month]) return false;
		if (day == 29 && !isLeapYear(year)) return false;
		return true;
	}

	private boolean isLeapYear(int year) {
		if (year % 400 == 0) return true;
		if (year % 100 == 0) return false;
		
		return year % 4 == 0;
	}
	
	

}
