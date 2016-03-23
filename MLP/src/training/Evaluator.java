package training;

import java.util.List;

// provides some methods for evaluating the neural network performance
public class Evaluator
{
	// calculate mean squared error for the observed and expected value
	public static Double calculateMSE(List<List<Double>> output, List<List<Double>> target)
	{
		// variables
		double mse = 0;
		double sum = 0;
		double mseExample = 0;
		
		for (int i = 0; i < output.size(); i++)
		{
			mseExample = 0;
			
			// since we can have more than one output neuron
			for (int j = 0; j < output.get(i).size(); j++)
				mseExample += Math.pow(output.get(i).get(j) - target.get(i).get(j), 2);
			
			sum += mseExample;
		}
		
		mse = sum / output.size();
		
		return mse;
	}
	
	// calculates the accuracy given a list of outputs and target values
	public static double calculateAccuracy(List<List<Double>> output, List<List<Double>> target)
	{
		// variables
		double acc = 0;
		double sum = 0;
		int activeBitOutput = 0;
		int activeBitTarget = 0;
		
		for (int i = 0; i < output.size(); i++)
		{
			activeBitOutput = getActiveBitIndex(output.get(i));
			activeBitTarget = getActiveBitIndex(target.get(i));
			
			if (activeBitOutput == activeBitTarget)
				sum++;
		}
		
		acc = sum / output.size();
		return acc;
	}

	// returns the active bit in a given set
	private static int getActiveBitIndex(List<Double> list)
	{
		double max = Double.NEGATIVE_INFINITY;
		int index = 0;
		
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) > max)
			{
				max = list.get(i);
				index = i;
			}
		}
			
		return index;
	}
	

}
