package challenge;

import java.awt.Point;
import java.util.Vector;

import challenge.utils.Constants;

/**
 * This class represents the graveyard where the zombies will be located.
 * 
 * @author gmanoli
 *
 */
public class Graveyard {
	
	/**
	 * this variable keeps the amount of zombies you could have smashed if you had played optimally
	 */
	private int maxZombies;
	
	public Graveyard() {
	}

	public int getMaxZombies() {
		return maxZombies;
	}
	
	/**
	 * The backtracking takes place here.  While there is no a valid solution, the method continues evaluating another solution
	 * taking into account the appearance and recharged time of the zombies
	 * 
	 * @param zombies				the zombies in the graveyard
	 * @param sol					vector which contains the partial zombies solution 
	 * @param time					partial time depending on the movements
	 * @param position				the position depending on the movements
	 * @param lastRechargedTime		the last time when the smash was recharged
	 */
	public void getZombies(Vector<Zombie> zombies, Vector<Zombie> sol, int time, Point position, int lastRechargedTime) {
		//verify if it is a solution
		if (isSolution(zombies, time, position)){
			//verify if the actual solution is is better than the previous
			if (sol.size() > maxZombies){
				maxZombies = sol.size();
			}
		}
		else{
			//continue trying with the rest of the zombies
			for (int i = 0; i < zombies.size(); i++) {
				Zombie z = zombies.get(i);
				Vector<Zombie> v = (Vector<Zombie>) zombies.clone();
				int distance = z.getDistance(position);
				
				//if visible and ready to smash in the period of time
				if (onTime(time, z, distance, lastRechargedTime)){
					
					position = z.getPoint();
					v.remove(z);
					sol.add(z);
					
					//increment the time, update the position and add the zombie to the partial solution, then continue with
					//the other zombies
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
					// means that the zombie is not visible yet, so we increment time depending on the distance or recharge time
					getZombies(v, sol, 
							distance* Constants.transitionTime > Constants.rechargeTime ? time + distance* Constants.transitionTime : 
								time + Constants.rechargeTime-distance* Constants.transitionTime, 
									position, lastRechargedTime);
						
				}	
				
			}
		}
	}
	
	/**
	 * Checks if the time is valid to smash a zombie
	 * 
	 * @param time					actual time
	 * @param zombie				the zombie we try to smash		
	 * @param distance				the distance from the actual position to the zombie
	 * @param lastRechargedTime		the last time when the smash was recharged
	 * @return
	 */
	private boolean onTime(int time, Zombie zombie, int distance, int lastRechargedTime) {
		int nextTime = time + Constants.transitionTime * distance;
		if (nextTime >= zombie.getStartTime() && 
		   (nextTime <= zombie.getEndTime()) &&
		   ((lastRechargedTime == 0) || (nextTime >= lastRechargedTime + Constants.rechargeTime)))
		
			return true;
		return false;
	}

	/**
	 * Checks if the vector of zombies is a valid solution, depending on the time and the position.
	 * 
	 * @param zombies	vector of zombies to verify
	 * @param time		actual time
	 * @param position	actual position
	 * @return			true	the vector of zombies is a valid solution
	 * 					false	the vector of zombies is a valid solution
	 */
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
