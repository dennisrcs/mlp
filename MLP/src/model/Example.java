package model;

import java.util.List;

// wraps up a training example
public class Example
{
	// members
	private List<Double> input;
	private List<Double> target;
	
	// constructor
	public Example(List<Double> input, List<Double> target)
	{
		this.input = input;
		this.target = target;
	}
	
	// getters and setters
	public List<Double> getInput() {
		return input;
	}
	public void setInput(List<Double> input) {
		this.input = input;
	}
	public List<Double> getTarget() {
		return target;
	}
	public void setTarget(List<Double> target) {
		this.target = target;
	}
}
