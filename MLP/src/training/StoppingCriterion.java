package training;

import model.Network;

// checks to see if the execution should stop
public class StoppingCriterion
{
	// constants
	private final int MIN_ITERATIONS = 5000;
	
	// members
	private Network bestNetwork;
	private double minimumMSE;
	private int lastIterationMSEUpdate;
	
	// constructor
	public StoppingCriterion()
	{
		this.minimumMSE = Double.POSITIVE_INFINITY;
	}

	public boolean isDone(int iteration)
	{
		// should iterate at least MIN_ITERATIONS times
		if (iteration < MIN_ITERATIONS)
			return false;
		
		if (iteration < this.getLastIterationMSEUpdate() * 2)
			return false;
		
		return true;
	}

	// tries to update the minimumMSE
	public void tryUpdateMSE(int iteration, double validationMSE, Network network)
	{
		if (validationMSE < this.minimumMSE)
		{
			this.minimumMSE = validationMSE;
			this.setLastIterationMSEUpdate(iteration);
			this.bestNetwork = (Network)network.clone();
		}
	}

	public int getLastIterationMSEUpdate() {
		return lastIterationMSEUpdate;
	}

	public void setLastIterationMSEUpdate(int lastIterationMSEUpdate) {
		this.lastIterationMSEUpdate = lastIterationMSEUpdate;
	}

	public Network getBestNetwork() {
		return bestNetwork;
	}

	public void setBestNetwork(Network bestNetwork) {
		this.bestNetwork = bestNetwork;
	}
}
