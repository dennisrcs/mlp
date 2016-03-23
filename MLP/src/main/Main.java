package main;

import experimentation.Experimenter;
import input.UserInputReader;

public class Main
{
	public static void main(String[] args)
	{
		UserInputReader inputReader = new UserInputReader();
		inputReader.read(args);
		
		Experimenter exp = new Experimenter();
		exp.execute(inputReader);
	}
}
