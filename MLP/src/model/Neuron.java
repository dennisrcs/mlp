package model;

// represents the neuron of the neural network
public class Neuron
{
	// members
	private double input;
	private double output;
	private int id;
	
	public Neuron(int id)
	{
		this.setId(id);
	}
	
	// getters and setters
	public double getInput() {
		return input;
	}

	public void setInput(double value) {
		this.input = value;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double output) {
		this.output = output;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
