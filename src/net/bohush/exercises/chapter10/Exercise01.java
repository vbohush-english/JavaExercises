package net.bohush.exercises.chapter10;

public class Exercise01 {

	public static void main(String[] args) {
		System.out.println(new Time());
		System.out.println(new Time(555550000));
		System.out.println(new Time(12, 1, 2));
	}

}

class Time {
	int hour;
	int minute;
	int second;
	
	public Time(long milliseconds) {
		setTime(milliseconds);
	}
	
	public Time() {
		setTime(System.currentTimeMillis());
	}
	
	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHout() {
		return hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public int getSecond() {
		return second;
	}
	
	public void setTime(long elapseTime) {
	    long totalSeconds = elapseTime / 1000;
	    second = (int)(totalSeconds % 60);
	    long totalMinutes = totalSeconds / 60;
	    minute = (int)(totalMinutes % 60);
	    long totalHours = totalMinutes / 60;
	    hour = (int)(totalHours % 24);
	}
	
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}