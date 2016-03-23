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
	private Configuration configuration;

	// constructor
	public Network(Configuration config)
	{
		this.setNeurons(new ArrayList<List<Neuron>>());
		this.setWeights(new ArrayList<List<List<Weight>>>());
		this.setConfiguration(config);
	}
	
	// create neurons and initialize weights
	public void initialize()
	{
		initializeNeurons();
		initializeWeights();		
	}

	// initialize the network neurons according to config
	private void initializeNeurons()
	{
		int neuronId = 0;
		int layerNum = getConfiguration().getLayersNumber();
		
		for (int i = 0; i < layerNum; i++)
		{
			int numNeurons = getConfiguration().getNodesPerLayer().get(i);
			ArrayList<Neuron> layer = new ArrayList<Neuron>();
			
			// every layer but the last has bias
			numNeurons = (i == layerNum - 1) ? numNeurons : (numNeurons+1);
			
			for (int j = 0; j < numNeurons; j++)
			{
				Neuron neuron;
				if (i == layerNum - 1)
					neuron = new Neuron(neuronId++, false);
				else
					neuron = new Neuron(neuronId++, j == 0);
					
				layer.add(neuron);
			}
			
			// adds new layer to set of neurons
			this.getNeurons().add(layer);
		}
	}
	
	// initialize the weights of the network
	private void initializeWeights()
	{
		for (int i = 0; i < getConfiguration().getLayersNumber() - 1; i++)
		{
			int previousLayerSize = getConfiguration().getNodesPerLayer().get(i);
			List<List<Weight>> layerWeights = new ArrayList<List<Weight>>();

			// +1 for bias
			for (int j = 0; j < previousLayerSize + 1; j++)
			{
				int nextLayerSize = getConfiguration().getNodesPerLayer().get(i+1);
				List<Weight> weightsAux = new ArrayList<Weight>();
				
				for (int k = 0; k < nextLayerSize; k++)
					addToWeightSet(weightsAux);

				layerWeights.add(weightsAux);
			}
			
			// adds weights connecting layers to the set of weights
			this.getWeights().add(layerWeights);
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
		for (int i = 0; i < getNeurons().size(); i++)
		{
			System.out.println("Layer " + (i+1) + ":");
			for (int j = 0; j < getNeurons().get(i).size(); j++)
				System.out.print(getNeurons().get(i).get(j).getId() + ", ");
			System.out.println();
		}
	}
	
	public void printWeights()
	{
		for (int i = 0; i < getWeights().size(); i++)
		{
			System.out.println("Weights Layers: " + i + "->" + (i+1));

			for (int j = 0; j < getWeights().get(i).size(); j++)
				for (int k = 0; k < getWeights().get(i).get(j).size(); k++)
					System.out.println("Weight " + j + "," + k + ": " + getWeights().get(i).get(j).get(k).getValue());
		}
	}

	public List<List<Neuron>> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<List<Neuron>> neurons) {
		this.neurons = neurons;
	}

	public List<List<List<Weight>>> getWeights() {
		return weights;
	}

	public void setWeights(List<List<List<Weight>>> weights) {
		this.weights = weights;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
