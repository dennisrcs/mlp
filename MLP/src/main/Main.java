package main;

import java.util.HashMap;
import java.util.Map;

import model.Configuration;
import model.Network;

public class Main
{
	public static void main(String[] args)
	{
		Map<Integer, Integer> nodesPerLayer = new HashMap<Integer, Integer>();
		nodesPerLayer.put(1, 4);
		nodesPerLayer.put(2, 2);
		nodesPerLayer.put(3, 1);
		
		Configuration config = new Configuration(3, nodesPerLayer);
		Network neuralNet = new Network(config);
		neuralNet.printWeights();
		
	}
}
