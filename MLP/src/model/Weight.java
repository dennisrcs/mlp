package model;

// represents the weight over to neurons
public class Weight
{
	// members
	private double value;

	// constructor
	public Weight(double value)
	{
		this.value = value;
	}
	
	// getters and setters
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
