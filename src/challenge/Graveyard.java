package challenge;

import java.awt.Point;
import java.util.Vector;

import challenge.utils.Constants;

public class Graveyard {
	
	private int maxZombies;
	Vector<Zombie> zombies;
	
	public Graveyard() {
	}

	public void addZombie(Zombie z){
		zombies.add(z);
	}
	
	public int getMaxZombies() {
		return maxZombies;
	}
	
	public void getZombies(Vector<Zombie> zombies, Vector<Zombie> sol, int time, Point position, int lastRechargedTime) {
		if (isSolution(zombies, time, position)){
			//System.out.println("time " + time + " " + sol.toString());
			if (sol.size() > maxZombies){
				maxZombies = sol.size();
			}
		}
		else{
			for (int i = 0; i < zombies.size(); i++) {
				Zombie z = zombies.get(i);
				Vector<Zombie> v = (Vector<Zombie>) zombies.clone();
				int distance = z.getDistance(position);
				if (onTime(time, z, distance, lastRechargedTime)){
					
					position = z.getPoint();
					v.remove(z);
					sol.add(z);
					getZombies(v, sol, time + Constants.transitionTime * distance, position, time + Constants.transitionTime * distance);
						
					if (!sol.isEmpty()){
						sol.removeElementAt(sol.size()-1);
						if (sol.isEmpty())
							position = new Point(0, 0);
						else
							position = sol.elementAt(sol.size()-1).getPoint();
					}
					else
						position = new Point(0, 0);
				}
				else{
					// means that the zombie is not visible yet
					getZombies(v, sol, 
							distance* Constants.transitionTime > Constants.rechargeTime ? time + distance* Constants.transitionTime : 
								time + Constants.rechargeTime-distance* Constants.transitionTime, 
									position, lastRechargedTime);
						
				}	
				
			}
		}
	}
	
	private boolean onTime(int time, Zombie z, int distance, int lastRechargedTime) {
		int nextTime = time + Constants.transitionTime * distance;
		if (nextTime >= z.getStartTime() && 
		   (nextTime <= z.getEndTime()) &&
		   ((lastRechargedTime == 0) || (nextTime >= lastRechargedTime + Constants.rechargeTime)))
		
			return true;
		return false;
	}

	public boolean isSolution(Vector<Zombie> zombies, int time, Point position){
		for (int i = 0; i < zombies.size(); i++) {
			Zombie z = zombies.get(i);
			int distance = z.getDistance(position);
			int timeToPoint = distance * Constants.transitionTime;
			if (time+timeToPoint <= z.getEndTime())
			
				return false;
			
		}
		return true;
	}
	
}
