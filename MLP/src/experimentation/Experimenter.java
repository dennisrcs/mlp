package experimentation;

import java.util.List;
import java.util.Map;

import input.UserInputReader;
import model.Example;
import preprocessing.PreprocessTaskExecutor;
import training.Processor;

// wraps all execution phases (preprocess, process and postprocess)
public class Experimenter
{
	// executes the experiment
	public void execute(UserInputReader userInput)
	{
		List<Example> examples = preprocess(userInput.getFilename(), userInput.getMissingDataSymbol());
		process(examples, userInput.getNeuronsPerLayer());
	}

	private void process(List<Example> examples, Map<Integer, Integer> neuronsPerLayer)
	{
		Processor process = new Processor(examples);
		process.execute(neuronsPerLayer);
	}

	private List<Example> preprocess(String filename, String missingDataSymbol)
	{
		PreprocessTaskExecutor executor = new PreprocessTaskExecutor();
		return executor.execute(filename, missingDataSymbol);
	}
}
