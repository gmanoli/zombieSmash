package challenge.main;

import java.awt.Point;
import java.io.IOException;
import java.util.Vector;

import challenge.Graveyard;
import challenge.Zombie;
import challenge.utils.InputParser;

public class Challenge {
	
	public static void main(String[] args) {
		//relative workspace path
		String inputPath = "input\\input.txt";
		Vector<Vector> zombies = null;
		try {
			zombies = InputParser.loadInput(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("zombies: " + zombies.toString());
		
		for (int i = 0; i < zombies.size(); i++) {
			Vector<Zombie> testCase = zombies.elementAt(i);
			Vector solution = new Vector();
			
			Point initPosition = new Point(0, 0);
			
			Graveyard graveyard = new Graveyard(); 
			graveyard.getZombies(testCase, solution, 0, initPosition, 0);
			System.out.println("Case #" + (i+1) + ": " + graveyard.getMaxZombies());
			
		}
	}

}
