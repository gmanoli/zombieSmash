package challenge;

import java.awt.Point;

import challenge.utils.Constants;

public class Zombie {
	
	private Point point;
	private int startTime;
	
	public Zombie (Point point, int time){
		this.point = point;
		startTime = time;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	
	public int getEndTime() {
		return startTime + Constants.appearanceTime;
	}

	public int getDistance(Zombie z) {
		int distance1 = (int) Math.abs(this.getPoint().getX() - z.getPoint().getX());
		int distance2 = (int) Math.abs(this.getPoint().getY() - z.getPoint().getY());
		return Math.max(distance1, distance2);
	}
	
	public int getDistance(Point p) {
		int distance1 = (int) Math.abs(this.getPoint().getX() - p.getX());
		int distance2 = (int) Math.abs(this.getPoint().getY() - p.getY());
		return Math.max(distance1, distance2);
	}
	
	@Override
	public String toString() {
		return "(" + (int)point.getX() + "," + (int)point.getY() + ") " + 
				"[" + startTime + "," + getEndTime() + "]";
	}
}
