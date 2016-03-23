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
	
	public void train(List<Example> trainingData, List<Example> validationData)
	{
		// initializing neural network
		this.neural_net.initialize();
		
		// training variables
		List<List<Double>> trainingOutputs;
		List<List<Double>> trainingTargets;
		double trainingMSE;

		// validation variables
		List<List<Double>> validationOutputs;
		List<List<Double>> validationTargets;
		double validationMSE;
	
		for (int i = 0; i < 1000000; i++)
		{
			trainingOutputs = new ArrayList<List<Double>>();
			trainingTargets = new ArrayList<List<Double>>();
			validationOutputs = new ArrayList<List<Double>>();
			validationTargets = new ArrayList<List<Double>>();
			
			// train the network using the training data set
			for (Example ex : trainingData)
			{
				FeedForwarder.fromInputToFirstLayer(this.neural_net, ex);
				List<Double> outputs = FeedForwarder.execute(this.neural_net, ex);
				
				trainingOutputs.add(outputs);
				trainingTargets.add(ex.getTarget());
				
				Backpropagator.calculateErrorInOutput(this.neural_net, outputs, ex.getTarget());
				Backpropagator.calculateErrorInHiddenUnits(this.neural_net);
				Backpropagator.updateWeights(this.neural_net);
			}
			
			// calculating MSE for the training data
			trainingMSE = Evaluator.calculateMSE(trainingOutputs, trainingTargets);
			
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
			
			System.out.println("MSE (T): " + trainingMSE);
			System.out.println(validationMSE);
		}
	}
	
}
