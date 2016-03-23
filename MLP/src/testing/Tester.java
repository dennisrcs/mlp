package testing;

import java.util.ArrayList;
import java.util.List;

import model.Example;
import model.Network;
import training.Evaluator;
import training.FeedForwarder;

// test a given neural network using the testing data
public class Tester
{
	// members
	private Network network;
	
	// constructor
	public Tester(Network network)
	{
		this.network = network;
	}
	
	// tests the given neural network with the testing data
	public double test(List<Example> testingData)
	{
		List<List<Double>> outputList = new ArrayList<List<Double>>();
		List<List<Double>> targetList = new ArrayList<List<Double>>();
		
		for (Example ex : testingData)
		{
			FeedForwarder.fromInputToFirstLayer(this.network, ex);
			List<Double> outputs = FeedForwarder.execute(this.network, ex);
			outputList.add(outputs);;
			targetList.add(ex.getTarget());
		}
		
		return Evaluator.calculateAccuracy(outputList, targetList);
		
	}
}
