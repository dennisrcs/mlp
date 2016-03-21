package training;

import java.util.ArrayList;
import java.util.List;

import model.Configuration;
import model.Example;
import model.Network;

public class Backpropagation
{
	// learning rate
	private static final double LR = 0.05;
	private Network neural_net;

	// constructor
	public Backpropagation(Configuration config)
	{
		this.neural_net = new Network(config);
	}
	
	public void train(List<Example> examples)
	{
		// initializing neural network
		this.neural_net.initialize();
	
		for (Example ex : examples)
		{
			FeedForwarder.fromInputToFirstLayer(neural_net, ex);
			List<Double> outputs = FeedForwarder.feed(this.neural_net, ex);
			
			List<Double> delta_k = this.calculateErrorInOutput(outputs, ex.getTarget());
		}
		
	}

	private List<Double> calculateErrorInOutput(List<Double> outputs, List<Double> targets)
	{
		List<Double> result = new ArrayList<Double>();
		
		for (int i = 0; i < outputs.size(); i++)
		{
			double output = outputs.get(i);
			double target = targets.get(i);
			double err = output * (1 - output) * (target - output);
			result.add(err);
		}
		
		return result;
	}
	
}
