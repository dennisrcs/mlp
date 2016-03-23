package main;

import experimentation.Experimenter;
import input.UserInputReader;

public class Main
{
	// main method
	public static void main(String[] args)
	{
		// reads the input provided by the user
		UserInputReader inputReader = new UserInputReader();
		inputReader.read(args);
		
		// neural network experimenter
		Experimenter exp = new Experimenter();
		exp.execute(inputReader);
	}
}
