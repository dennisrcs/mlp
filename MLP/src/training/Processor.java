package training;

import java.util.List;
import java.util.Map;

import model.Configuration;
import model.Example;
import parser.DataSplitter;

// wraps the main process execution phase
public class Processor
{
	// member
	private DataSplitter dataSplitter;
	
	// constructor
	public Processor(List<Example> examples)
	{
		this.dataSplitter = new DataSplitter(examples);
	}

	public void execute(Map<Integer, Integer> neuronsPerLayer)
	{
		Configuration config = new Configuration(dataSplitter.getInputSize(), dataSplitter.getOutputSize(), neuronsPerLayer);
		Trainer trainer = new Trainer(config);
		trainer.train(dataSplitter.getTrainingData(), dataSplitter.getValidationData());
	}

}
