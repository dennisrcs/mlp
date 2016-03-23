package training;

import java.util.List;

import model.Configuration;
import model.Example;
import model.Network;

public class Trainer
{
	// member
	private Network neural_net;

	// constructor
	public Trainer(Configuration config)
	{
		this.neural_net = new Network(config);
	}
	
	public void train(List<Example> examples)
	{
		// initializing neural network
		this.neural_net.initialize();
	
		for (int i = 0; i < 10000; i++)
		{
			for (Example ex : examples)
			{
				FeedForwarder.fromInputToFirstLayer(this.neural_net, ex);
				
				List<Double> outputs = FeedForwarder.execute(this.neural_net, ex);
				
				Backpropagator.calculateErrorInOutput(this.neural_net, outputs, ex.getTarget());
			
				Backpropagator.calculateErrorInHiddenUnits(this.neural_net);
				
				Backpropagator.updateWeights(this.neural_net);
			}
		}
	}
	
}
