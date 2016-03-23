package experimentation;

import java.util.ArrayList;
import java.util.List;

import input.UserInputReader;
import model.Network;
import parser.DataSplitter;
import testing.StatisticsCalculator;

// wraps all execution phases (preprocess, training, and testing)
public class Experimenter
{
	// constant
	private static final int K_FOLD = 10;
	
	// executes the experiment
	public void execute(UserInputReader userInput)
	{
		List<Double> accuracies = new ArrayList<Double>();
		
		for (int iteration = 0; iteration < K_FOLD; iteration++)
		{
			// wraps the neural network execution
			NeuralNetworkExecutor executor = new NeuralNetworkExecutor();
			
			// preprocess phase
			DataSplitter data = executor.preprocess(userInput.getFilename(), userInput.getMissingDataSymbol(), iteration);
			
			// training phase
			Network network = executor.process(data.getTrainingData(), data.getValidationData(), userInput.getNeuronsPerLayer());
			
			// testing phase
			double accuracy = executor.evaluate(network, data.getTestingData());
			accuracies.add(accuracy);
			
			System.out.println("acc: " + accuracy);
		}
		
		StatisticsCalculator calc = new StatisticsCalculator();
		calc.performStatistics(accuracies);
	}
}
