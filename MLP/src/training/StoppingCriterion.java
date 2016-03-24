package training;

import model.Network;

// checks to see if the execution should stop
public class StoppingCriterion
{
	// constants
	private final int MIN_ITERATIONS = 5000;
	private final int MAX_ITERATIONS = 100000;
	
	// members
	private Network bestNetwork;
	private double minimumMSE;
	private int lastIterationMSEUpdate;
	
	// constructor
	public StoppingCriterion()
	{
		this.minimumMSE = Double.POSITIVE_INFINITY;
	}

	// checks if the training algorithm is done
	public boolean isDone(int iteration)
	{
		// should iterate at least MIN_ITERATIONS times
		if (iteration < MIN_ITERATIONS)
			return false;
		
		// if equal or higher than MAX_ITERATIONS, then stop
		if (iteration >= MAX_ITERATIONS)
			return true;
		
		// checks if current iteration is at least 2 times
		// the last iteration the MSE was updated
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
			this.lastIterationMSEUpdate = iteration;
			this.bestNetwork = (Network)network.clone();
		}
	}

	// getters and setters
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
