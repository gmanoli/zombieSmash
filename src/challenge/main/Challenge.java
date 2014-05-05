package challenge.main;

import java.awt.Point;
import java.io.IOException;
import java.util.Vector;

import challenge.Graveyard;
import challenge.Zombie;
import challenge.utils.InputParser;

/**
 * This class contains the main method to execute the challenge, with an input file given.
 * 
 * @author gmanoli
 *
 */
public class Challenge {
	
	/**
	 * Main method which prints the expected output
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//relative workspace path
		String inputPath = "input\\input.txt";
		Vector<Vector> zombies = null;
		try {
			zombies = InputParser.loadInput(inputPath);
			
			//iterate over the test cases and look for the optimized amount of zombies
			for (int i = 0; i < zombies.size(); i++) {
				Vector<Zombie> testCase = zombies.elementAt(i);
				Vector solution = new Vector();
				
				Point initPosition = new Point(0, 0);
				
				Graveyard graveyard = new Graveyard(); 
				graveyard.getZombies(testCase, solution, 0, initPosition, 0);
				System.out.println("Case #" + (i+1) + ": " + graveyard.getMaxZombies());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
