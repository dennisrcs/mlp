package training;

import java.util.List;
import java.util.Map;

import model.Configuration;
import model.Network;
import model.Neuron;
import model.Weight;

// provides methods for back propagating the error in the network
public class Backpropagator
{
	// learning rate
	private static final double LR = 0.05;
	
	// calculate error in hidden units wrapper
	public static void calculateErrorInHiddenUnits(Network network)
	{
		List<List<Neuron>> neurons = network.getNeurons();
		List<List<List<Weight>>> weights = network.getWeights();
		
		Configuration config = network.getConfiguration();
		Map<Integer, Integer> nodesPerLayer = config.getNodesPerLayer();
		
		int layersNum = config.getLayersNumber();
		
		backpropagateError(neurons, weights, nodesPerLayer, layersNum);
	}

	// back propagates the error from the output units to the hidden units
	private static void backpropagateError(List<List<Neuron>> neurons, List<List<List<Weight>>> weights,
			Map<Integer, Integer> nodesPerLayer, int layersNum) 
	{
		for (int i = layersNum - 1; i > 0; i--)
		{
			List<Neuron> layer = neurons.get(i-1);
			for (int j = 0; j < layer.size(); j++)
			{
				// getting neuron and its output
				Neuron neuron = layer.get(j);
				double output = neuron.getOutput();
				double delta_h = 0;
				
				// retrieves the number of neurons in the following layer
				int neuronsNextLayer = nodesPerLayer.get(i);
				
				// if not last layer, adds 1 for taking bias into consideration
				neuronsNextLayer = (i == layersNum - 1) ? neuronsNextLayer : neuronsNextLayer+1;
				
				// computes delta h.
				for (int k = 0; k < neuronsNextLayer; k++)
				{
					Neuron nextLayerNeuron = neurons.get(i).get(k);
					if (!nextLayerNeuron.isBias())
					{
						// this line is needed because for the layers that are not output layers
						// we only have weights connecting neurons to non-bias neurons
						int weightNextLayerIndex = (i == layersNum - 1) ? k : k-1; 
						
						double weight = weights.get(i-1).get(j).get(weightNextLayerIndex).getValue();
						delta_h += nextLayerNeuron.getError() * weight;	
					}
				}
				
				// sets the error within the neuron object
				delta_h = delta_h * (output * (1 - output));
				neuron.setError(delta_h);
			}
		}
	}

	public static void updateWeights(Network network)
	{
		// retrieving neurons and weights of the network
		List<List<Neuron>> neurons = network.getNeurons();
		List<List<List<Weight>>> weights = network.getWeights();

		// variables
		Weight weight;
		int nextLayerNeuron;
		double neuronOutput;
		double neuronError;
		double d_weight;
		
		for (int layer = 0; layer < weights.size(); layer++)
		{
			for (int j = 0; j < weights.get(layer).size(); j++)
			{
				for (int k = 0; k < weights.get(layer).get(j).size(); k++)
				{
					// retrieving neuron output
					neuronOutput = neurons.get(layer).get(j).getOutput();
					
					// getting error in the neuron of the following layer
					nextLayerNeuron = (layer == weights.size() - 1) ? k : (k+1); 
					neuronError = neurons.get(layer+1).get(nextLayerNeuron).getError();
					
					// calculating delta weight and updating weight
					d_weight = LR * neuronOutput * neuronError;
					weight = weights.get(layer).get(j).get(k);
					weight.setValue(weight.getValue() + d_weight);
				}
			}
		}
		
	}

	// calculates the error in the output layer
	public static void calculateErrorInOutput(Network network, List<Double> outputs, List<Double> targets)
	{	
		Configuration config = network.getConfiguration();
		List<List<Neuron>> neurons = network.getNeurons();
		int layerNum = config.getLayersNumber();
		
		for (int i = 0; i < outputs.size(); i++)
		{
			double output = outputs.get(i);
			double target = targets.get(i);
			// o * (1 - o) is the derivative of the sigmoid function
			double err = output * (1 - output) * (target - output);
			neurons.get(layerNum-1).get(i).setError(err);
		}
	}
}
