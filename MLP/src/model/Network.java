package model;

import java.util.ArrayList;
import java.util.List;

import util.Util;

// stores the neurons and set of weights linking neurons
public class Network
{
	// members
	private List<List<Neuron>> neurons;
	private List<List<List<Weight>>> weights;

	// constructor
	public Network(Configuration config)
	{
		this.neurons = new ArrayList<List<Neuron>>();
		this.weights = new ArrayList<List<List<Weight>>>();
		
		initializeNeurons(config);
		initializeWeights(config);
	}

	// initialize the network neurons according to config
	private void initializeNeurons(Configuration config)
	{
		int neuronId = 0;
		
		for (int i = 1; i < config.getLayersNumber(); i++)
		{
			int numNeurons = config.getNodesPerLayer().get(i);
			ArrayList<Neuron> layer = new ArrayList<Neuron>();
			
			for (int j = 0; j < numNeurons; j++)
			{
				Neuron neuron = new Neuron(neuronId++);
				layer.add(neuron);
			}
			
			// adds new layer to set of neurons
			this.neurons.add(layer);
		}
	}
	
	// initialize the weights of the network
	private void initializeWeights(Configuration config)
	{
		for (int i = 0; i < config.getLayersNumber() - 1; i++)
		{
			int previousLayerSize = config.getNodesPerLayer().get(i);
			List<List<Weight>> layerWeights = new ArrayList<List<Weight>>();

			// +1 for bias
			for (int j = 0; j < previousLayerSize + 1; j++)
			{
				int nextLayerSize = config.getNodesPerLayer().get(i+1);
				List<Weight> weightsAux = new ArrayList<Weight>();
				
				for (int k = 0; k < nextLayerSize; k++)
					addToWeightSet(weightsAux);

				layerWeights.add(weightsAux);
			}
			
			// adds weights connecting layers to the set of weights
			this.weights.add(layerWeights);
		}
	}

	// adds a random generated weight to the collection of weights
	private void addToWeightSet(List<Weight> weights)
	{
		double value = Util.GenerateRandomWeight();
		Weight weight = new Weight(value);
		weights.add(weight);
	}

	public void printNeurons()
	{
		for (int i = 0; i < neurons.size(); i++)
		{
			System.out.println("Layer " + (i+1) + ":");
			for (int j = 0; j < neurons.get(i).size(); j++)
				System.out.print(neurons.get(i).get(j).getId() + ", ");
			System.out.println();
		}
	}
	
	public void printWeights()
	{
		for (int i = 0; i < weights.size(); i++)
		{
			System.out.println("Weights Layers: " + i + "->" + (i+1));

			for (int j = 0; j < weights.get(i).size(); j++)
				for (int k = 0; k < weights.get(i).get(j).size(); k++)
					System.out.println("Weight " + j + "," + k + ": " + weights.get(i).get(j).get(k).getValue());
		}
	}

}
