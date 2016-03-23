package model;

// represents the weight over to neurons
public class Weight implements Cloneable
{
	// members
	private double value;

	// constructor
	public Weight(double value)
	{
		this.value = value;
	}
	
	// clones this object
	public Object clone()
	{
		Weight newWeight = new Weight(this.value);
		return newWeight;
	}
	
	// getters and setters
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
