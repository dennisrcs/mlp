package model;

import util.MathHelper;

// represents the neuron of the neural network
public class Neuron
{
	// members
	private double input;
	private double output;
	private double error;
	private boolean isBias;
	private int id;
	
	// constructor
	public Neuron(int id, boolean isBias)
	{
		this.setId(id);
		this.setBias(isBias);
	}
	
	public double calculateOutput(double input)
	{
		this.input = input;
		this.output = MathHelper.sigmoidFunction(this.input);
		return this.output;
	}
	
	// getters and setters
	public double getInput() {
		return input;
	}

	public void setInput(double value) {
		if (!isBias) this.input = value;
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

	public boolean isBias() {
		return isBias;
	}

	public void setBias(boolean isBias) {
		this.isBias = isBias;
		this.setOutput(1.0);
	}

	public double getError() {
		return error;
	}

	public void setError(double error) {
		this.error = error;
	}
}
