package util;

import java.util.List;

// math utilities
public class MathFunctions
{
	// constant sigmoid function alpha
	private static final double ALPHA = 1.0;
	
	// sigmoid function
	public static double sigmoidFunction(double x)
	{
		return 1 / (1 + Math.pow(Math.E, -x * ALPHA));
	}
	
	// find minimum value within a data collection
	public static double findMin(List<Double> data)
	{
		double min = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < data.size(); i++)
			if (data.get(i) < min)
				min = data.get(i);

		return min;
	}

	// find maximum value within a data collection
	public static double findMax(List<Double> data)
	{
		double max = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < data.size(); i++)
			if (data.get(i) > max)
				max = data.get(i);

		return max;
	}
}
