package training;

import java.util.List;
import java.util.Map;

import model.Configuration;
import model.Example;
import model.Network;

// wraps the main process execution phase
public class Processor
{
	public Network execute(List<Example> trainingData, List<Example> validationData, Map<Integer, Integer> neuronsPerLayer)
	{
		Configuration config = new Configuration(getInputSize(trainingData), getOutputSize(trainingData), neuronsPerLayer);
		Trainer trainer = new Trainer(config);
		return trainer.train(trainingData, validationData);
	}
	
	private int getInputSize(List<Example> data)
	{
		if (data != null && data.size() > 0)
			return data.get(0).getInputSize();
		else
			throw new NullPointerException("Data is either null or empty");
	}
	
	private int getOutputSize(List<Example> data)
	{
		if (data != null && data.size() > 0)
			return data.get(0).getOutputSize();
		else
			throw new NullPointerException("Data is either null or empty");
	}

}
