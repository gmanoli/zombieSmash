package challenge.utils;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import challenge.Zombie;

/**
 * Class in charge of parsing the input file provided
 * 
 * @author gmanoli
 *
 */
public class InputParser {
	
	/**
	 * 
	 * @param inputPath		path of the input file, relative to the workspace
	 * 
	 * @return				a Vector with the different test cases loaded, each of them with their zombies in place.
	 * @throws IOException
	 */
	public static Vector<Vector> loadInput(String inputPath) throws IOException{
		BufferedReader bReader = new BufferedReader(new FileReader(inputPath));
		String s;
		String[] line = null;
		int testCases = 0;
		int zombies = 0;

		// read the amount of test cases
		if ((s = bReader.readLine()) != null){
			testCases = Integer.parseInt(s);
		}
		int n = 0;
		Vector<Vector> testCase = new Vector<Vector>(testCases);
		while ((s = bReader.readLine()) != null && n < testCases) {
			//read the amount of zombies in the test case
			zombies = Integer.parseInt(s);
			int nZombies = 0;
			Vector<Zombie> vZombies = new Vector<Zombie>(zombies);
			while (nZombies < zombies && (s = bReader.readLine()) != null) {
				//split the values by an empty space: X coordinate, Y coordinate and the time at the zombie appears
				line = s.split(" ");
				Zombie z = new Zombie(new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1])), Integer.parseInt(line[2]));
				vZombies.add(z);
				nZombies++;
			}
			n++;
			//add zombies to the test case...
			testCase.add(vZombies);
		}
		return testCase;
	}

}
