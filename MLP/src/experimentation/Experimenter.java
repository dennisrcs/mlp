package experimentation;

import java.util.List;

import parser.DataInput;
import parser.DataParser;
import preprocessing.PreprocessExecutor;

public class Experimenter
{
	public void execute(String filename, String missingDataSymbol)
	{
		preprocess(filename, missingDataSymbol);
	}

	private List<List<String>> preprocess(String filename, String missingDataSymbol)
	{
		DataParser parser = new DataParser();
		List<List<String>> data = parser.parse(filename);
		
		DataInput inputData = new DataInput(data, missingDataSymbol);
		PreprocessExecutor.Execute(inputData);
		
		// returns handled data
		return inputData.getData();
	}
}
