package training;

import java.util.ArrayList;
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
	
	public Network train(List<Example> trainingData, List<Example> validationData)
	{
		// initializing neural network
		this.neural_net.initialize();
		
		// initializing stopping criterion
		StoppingCriterion criterion = new StoppingCriterion();
		
		// validation variables
		List<List<Double>> validationOutputs;
		List<List<Double>> validationTargets;
		double validationMSE;
		
		int iteration;
		for (iteration = 0; !criterion.isDone(iteration); iteration++)
		{
			validationOutputs = new ArrayList<List<Double>>();
			validationTargets = new ArrayList<List<Double>>();
			
			// train the network using the training data set
			for (Example ex : trainingData)
			{
				FeedForwarder.fromInputToFirstLayer(this.neural_net, ex);
				List<Double> outputs = FeedForwarder.execute(this.neural_net, ex);
				Backpropagator.calculateErrorInOutput(this.neural_net, outputs, ex.getTarget());
				Backpropagator.calculateErrorInHiddenUnits(this.neural_net);
				Backpropagator.updateWeights(this.neural_net);
			}
			
			// computing MSE for validation set
			for (Example ex : validationData)
			{
				FeedForwarder.fromInputToFirstLayer(this.neural_net, ex);
				List<Double> outputs = FeedForwarder.execute(this.neural_net, ex);
				validationOutputs.add(outputs);
				validationTargets.add(ex.getTarget());
			}
			
			// calculating MSE for the training data
			validationMSE = Evaluator.calculateMSE(validationOutputs, validationTargets);
			criterion.tryUpdateMSE(iteration, validationMSE, this.neural_net);
		}
		return criterion.getBestNetwork();
	}
}
