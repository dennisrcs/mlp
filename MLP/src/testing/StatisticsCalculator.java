package testing;

import java.text.DecimalFormat;
import java.util.List;

public class StatisticsCalculator
{
	// hardcoded Tdofalpha for k = 10 and alpha = 0.05
	private static final double T_DoF_Alpha = 2.262;
	private DecimalFormat format;
	
	public StatisticsCalculator()
	{
		this.format = new DecimalFormat("#.###");
	}
	
	// calculates and prints out mean and confidence interval
	private void CalculateMeanAndCI(List<Double> values, String metric)
	{
		double mean = 0;
		double CI = 0;
		double standardDev = 0;
		double stdSampleSize = 0;
		
		mean = CalculateMean(values);

		standardDev = CalculateStandardDeviation(values, mean);
		
		stdSampleSize = standardDev / Math.sqrt(values.size());
		CI = T_DoF_Alpha * stdSampleSize;
		
		System.out.println(metric + ": " + format.format(mean) + " +-" + format.format(CI));
	}

	// calculates the mean of a collection
	private double CalculateMean(List<Double> values)
	{
		double sum = 0;
		double mean;
		
		for (int i = 0; i < values.size(); i++)
			sum += values.get(i);
		
		mean = sum / (double)values.size();
		
		return mean;
	}

	private double CalculateStandardDeviation(List<Double> values, double mean)
	{
		// confidence interval calculation
		double standardDev = 0;
		for (int i = 0; i < values.size(); i++)
			standardDev += Math.pow(values.get(i) - mean, 2);
		
		standardDev = standardDev / values.size();
		standardDev = Math.sqrt(standardDev);
		
		return standardDev;
	}

	public void performStatistics(List<Double> data)
	{
		CalculateMeanAndCI(data, "Accuracy");
	}
}
