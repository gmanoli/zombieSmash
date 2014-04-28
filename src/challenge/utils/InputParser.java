package challenge.utils;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import challenge.Zombie;

public class InputParser {
	
	public static Vector<Vector> loadInput(String inputPath) throws IOException{
		BufferedReader bReader = new BufferedReader(new FileReader(inputPath));
		String s;
		String[] line = null;
		int testCases = 0;
		int zombies = 0;
		// test cases
		if ((s = bReader.readLine()) != null){
			testCases = Integer.parseInt(s);
		}
		int n = 0;
		Vector<Vector> testCase = new Vector<Vector>(testCases);
		while ((s = bReader.readLine()) != null && n < testCases) {
			zombies = Integer.parseInt(s);
			int nZombies = 0;
			Vector<Zombie> vZombies = new Vector<Zombie>(zombies);
			while (nZombies < zombies && (s = bReader.readLine()) != null) {
				line = s.split(" ");
				Zombie z = new Zombie(new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1])), Integer.parseInt(line[2]));
				vZombies.add(z);
				nZombies++;
			}
			n++;
			testCase.add(vZombies);
		}
		return testCase;
	}

}
