package training;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Configuration;
import model.Example;
import model.Network;
import model.Neuron;
import model.Weight;

// provides methods to forwardly feed the neural net
public class FeedForwarder
{
	// transfer data from input to first layer
	public static void fromInputToFirstLayer(Network net, Example example)
	{
		List<List<Neuron>> neurons = net.getNeurons();
		List<Double> input = example.getInput();

		for (int i = 0; i < input.size(); i++)
			neurons.get(0).get(i+1).setOutput(input.get(i));
	}
	
	// feed the net forward and calculate its output
	public static List<Double> feed(Network net, Example example)
	{
		// variables
		int currLayerSize;
		int nextLayerSize;
		int nextLayerNeuronId;
		double inputSum;
		
		List<Double> result = new ArrayList<Double>();
		
		// retrieving configuration input parameters
		Configuration conf = net.getConfiguration();
		Map<Integer, Integer> neuronPerLayer = conf.getNodesPerLayer();
		
		// retrieving neural net attributes
		List<List<Neuron>> neurons = net.getNeurons();
		List<List<List<Weight>>> weights = net.getWeights();
		
		int numLayers = conf.getLayersNumber();
		for (int i = 0; i < numLayers - 1; i++)
		{
			nextLayerSize = neuronPerLayer.get(i+1);
			for (int j = 0; j < nextLayerSize; j++)
			{
				currLayerSize = neuronPerLayer.get(i);
				inputSum = 0;
				
				// +1 for including bias
				for (int k = 0; k < currLayerSize + 1; k++)
				{
					Neuron neuronCurrLayer = neurons.get(i).get(k);
					Weight weight = weights.get(i).get(k).get(j);
					inputSum += neuronCurrLayer.getOutput() * weight.getValue();
				}
				
				// necessary because the last layer has no bias
				nextLayerNeuronId = ((i-2) == numLayers) ? j : (j+1);
				neurons.get(i+1).get(nextLayerNeuronId).calculateOutput(inputSum);
			}
		}

		// copy data from neurons output to vector result
		for (int i = 0; i < neurons.get(numLayers-1).size(); i++)
		{
			double output = neurons.get(numLayers-1).get(i).getOutput();
			result.add(output);
		}
		
		return result;
	}

}
