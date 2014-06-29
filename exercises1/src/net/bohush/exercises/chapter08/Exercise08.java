package net.bohush.exercises.chapter08;

public class Exercise08 {

	public static void main(String[] args) {
		Fan fan1 = new Fan();
		fan1.setSpeed(Fan.FAST);
		fan1.setRadius(10.0);
		fan1.setColor("yellow");
		fan1.setOn(true);
		Fan fan2 = new Fan();
		fan2.setSpeed(Fan.MEDIUM);
		fan2.setRadius(5.0);
		fan2.setOn(false);
		System.out.println(fan1.toString());
		System.out.println(fan2.toString());
	}

}

class Fan {
	public final static int SLOW = 1;
	public final static int MEDIUM = 2;
	public final static int FAST = 3;
	private int speed = SLOW;
	private boolean on = false;
	private double radius = 5.0;
	private String color = "blue";
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public Fan() {		
	}
	
	public String toString() {
		if (on) {
			return "Speed = " + speed + ", color = " + color + ", radius = " + radius;
		} else {
			return "Color = " + color + ", radius = " + radius + ", fan is off";
		}
	}
}
