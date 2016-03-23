package preprocessing;

import java.util.ArrayList;
import java.util.List;

import model.Example;
import parser.DataInput;
import parser.DataParser;
import parser.DataSplitter;
import util.DataUtil;

// Preprocess phase executor
public class PreprocessTaskExecutor
{
	// members
	private DataParser parser;
	
	// constructor
	public PreprocessTaskExecutor()
	{
		this.parser = new DataParser();
	}
	
	// executes the preprocess phase
	public DataSplitter execute(String filename, String missingDataSymbol, int iteration)
	{
		DataSplitter dataSplitter = new DataSplitter();
		List<List<String>> parsedData = parser.parse(filename);
		DataInput inputData = new DataInput(parsedData, missingDataSymbol);
		List<Example> examples = new ArrayList<Example>();

		PreprocessingHelper helper = new PreprocessingHelper(inputData);

		// executes all preprocessing tasks
		helper.removeUselessData();
		helper.handleMissingData();
		helper.handleTargetAttribute();
		helper.normalizeData();
		helper.fromCategoricalToNumericData();

		// populates example and split data accordingly
		populateExamples(examples, inputData);
		dataSplitter.split(examples, iteration);
		
		return dataSplitter;
	}
	
	// populate examples according to the data set
	private void populateExamples(List<Example> examples, DataInput inputData)
	{
		List<List<String>> data = inputData.getData();
		List<String> targetColumns = inputData.getTargetAttributeColumns();
		List<Integer> targetColIdx = new ArrayList<Integer>();
		
		int auxColIdx;
		for (int i = 0; i < targetColumns.size(); i++)
		{
			auxColIdx = DataUtil.findIndex(data, targetColumns.get(i));
			targetColIdx.add(auxColIdx);
		}
		
		// sets the data to the correct portion (splits between input and target)
		double doubleValue;
		for (int i = 1; i < data.size(); i++)
		{
			List<Double> inputValues = new ArrayList<Double>();
			List<Double> targetValues = new ArrayList<Double>();
			
			for (int j = 0; j < data.get(i).size(); j++)
			{
				doubleValue = Double.parseDouble(data.get(i).get(j));
				if (targetColIdx.contains(j))
					targetValues.add(doubleValue);
				else
					inputValues.add(doubleValue);
			}
			
			Example ex = new Example(inputValues, targetValues);
			examples.add(ex);
		}
	}
}
