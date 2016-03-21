package util;

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
}
