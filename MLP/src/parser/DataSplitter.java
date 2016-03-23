package parser;

import java.util.ArrayList;
import java.util.List;

import model.Example;

// splits the data between training, validation, and testing data
public class DataSplitter
{
	// constants
	private final double TRAINING_DATA_PROPORTION = 2.0/3.0;
	private final double TRAINING_TESTING_PROPORTION = 0.9;
	
	// members
	private List<Example> trainingData;
	private List<Example> validationData;
	private List<Example> testingData;
	
	// constructor
	public DataSplitter()
	{
		this.trainingData = new ArrayList<Example>();
		this.validationData = new ArrayList<Example>();
		this.testingData = new ArrayList<Example>();
	}
	
	// splits the data between training and validation data
	public void split(List<Example> data, int cv_iteration)
	{
		List<Example> trainingAndValidationData = new ArrayList<Example>();
		int dataSize = data.size();
		int testingDataSize = dataSize - (int)Math.floor(dataSize * TRAINING_TESTING_PROPORTION);
		
		int startingIndex = testingDataSize * cv_iteration + 1;
		int endIndx = testingDataSize*(cv_iteration+1) + 1;
		
		for (int i = 0; i < dataSize; i++)
		{
			Example aux = data.remove(0);
			if (i >= startingIndex && i < endIndx)
				this.testingData.add(aux);
			else
				trainingAndValidationData.add(aux);	
		}

		SplitTrainingAndValidationData(trainingAndValidationData);
	}

	// splits data given between training and validation data
	private void SplitTrainingAndValidationData(List<Example> data)
	{
		int dataSize = data.size();
		int trainingDatSize = (int)Math.floor(dataSize * TRAINING_DATA_PROPORTION);
		
		for (int i = 0; i < dataSize; i++)
		{
			Example line = data.remove(0);
			if (i < trainingDatSize + 1)
				this.trainingData.add(line);
			else
				this.validationData.add(line);
		}
	}

	// getters and setters
	public List<Example> getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(List<Example> trainingData) {
		this.trainingData = trainingData;
	}

	public List<Example> getValidationData() {
		return validationData;
	}

	public void setValidationData(List<Example> validationData) {
		this.validationData = validationData;
	}

	public List<Example> getTestingData() {
		return testingData;
	}

	public void setTestingData(List<Example> testingData) {
		this.testingData = testingData;
	}
}
