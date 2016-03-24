package experimentation;

import java.util.List;
import java.util.Map;

import model.Example;
import model.Network;
import parser.DataSplitter;
import preprocessing.PreprocessTaskExecutor;
import testing.Tester;
import training.Processor;

// neural network executor wrapper
public class NeuralNetworkExecutor
{
	// testing phase
	public double evaluate(Network network, List<Example> testingData)
	{
		Tester tester = new Tester(network);
		return tester.test(testingData);
	}

	// main training phase
	public Network process(List<Example> trainingData, List<Example> validationData, Map<Integer, Integer> neuronsPerLayer)
	{
		Processor process = new Processor();
		return process.execute(trainingData, validationData, neuronsPerLayer);
	}

	// preprocessing phase
	public DataSplitter preprocess(String filename, String missingDataSymbol, int iteration)
	{
		PreprocessTaskExecutor executor = new PreprocessTaskExecutor();
		return executor.execute(filename, missingDataSymbol, iteration);
	}
}
