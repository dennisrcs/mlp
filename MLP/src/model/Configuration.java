package model;

import java.util.Map;

// neural network configuration
public class Configuration
{
	// members
	private int layersNumber;
	private int inputNumber;
	private int outputNumber;
	private Map<Integer, Integer> nodesPerLayer;
	
	// constructor
	public Configuration(int inputSize, int outputSize, Map<Integer, Integer> nodesPerLayer)
	{
		this.inputNumber = inputSize;
		this.nodesPerLayer = nodesPerLayer;
		this.nodesPerLayer.put(0, inputSize);
		
		int mapSize = this.nodesPerLayer.size();
		this.nodesPerLayer.put(mapSize, outputSize);
		
		this.layersNumber = this.nodesPerLayer.size();
	}
	
	// getters and setters
	public int getLayersNumber() {
		return layersNumber;
	}
	public void setLayersNumber(int layers_number) {
		this.layersNumber = layers_number;
	}
	public Map<Integer, Integer> getNodesPerLayer() {
		return nodesPerLayer;
	}
	public void setNodesPerLayer(Map<Integer, Integer> nodes_per_layer) {
		this.nodesPerLayer = nodes_per_layer;
	}
	public int getInputNumber() {
		return inputNumber;
	}
	public void setInputNumber(int input_number) {
		this.inputNumber = input_number;
	}

	public int getOutputNumber() {
		return outputNumber;
	}

	public void setOutputNumber(int outputNumber) {
		this.outputNumber = outputNumber;
	}
}
