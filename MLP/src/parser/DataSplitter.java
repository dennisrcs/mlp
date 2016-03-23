package parser;

import java.util.ArrayList;
import java.util.List;

import model.Example;

// splits the data between training, validation, and testing data
public class DataSplitter
{
	// constant
	private final double TRAINING_PERCENTAGE = 2.0/3.0;
	
	// members
	private int inputSize;
	private int outputSize;
	private List<Example> trainingData;
	private List<Example> validationData;
	
	// constructor
	public DataSplitter(List<Example> examples)
	{
		this.trainingData = new ArrayList<Example>();
		this.validationData = new ArrayList<Example>();
		
		if (examples != null && examples.size() > 0)
		{
			this.inputSize = examples.get(0).getInputSize();
			this.outputSize = examples.get(0).getOutputSize();
		}
		
		split(examples);
	}

	// splits the data between training and validation data
	private void split(List<Example> examples)
	{
		int dataSize = examples.size();
		int trainingDataSize = (int) Math.round(dataSize * TRAINING_PERCENTAGE);
		
		for (int i = 0; i < dataSize; i++)
			if (i < trainingDataSize)
				this.trainingData.add(examples.get(i));
			else
				this.validationData.add(examples.get(i));
		
	}

	// getters and setters
	public int getInputSize() {
		return inputSize;
	}

	public void setInputSize(int inputSize) {
		this.inputSize = inputSize;
	}

	public int getOutputSize() {
		return outputSize;
	}

	public void setOutputSize(int outputSize) {
		this.outputSize = outputSize;
	}

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

}
