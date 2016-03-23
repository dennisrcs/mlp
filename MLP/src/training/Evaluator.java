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
		double sumTarget = 0;
		double sumOutput = 0;
		
		for (int i = 0; i < output.size(); i++)
		{
			sumTarget = 0;
			sumOutput = 0;
			
			// since we can have more than one output neuron
			for (int j = 0; j < output.get(i).size(); j++)
			{
				sumTarget += target.get(i).get(j);
				sumOutput += output.get(i).get(j);
			}
			
			sum += Math.pow(sumTarget - sumOutput, 2);
		}
		
		mse = sum / output.size();
		
		return mse;
	}

}
