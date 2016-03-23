package input;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

// reads the input provided by the user
public class UserInputReader
{
	// members
	private String filename;
	private Map<Integer, Integer> neuronsPerLayer;
	private String missingDataSymbol;
	
	// reads the input provided by the user
	public void read(String[] args)
	{
		// at least one attribute (filename)
		if (args.length >= 2)
		{
			this.setFilename(args[0]);
			
			this.setNeuronsPerLayer(new HashMap<Integer, Integer>());
			this.buildNeuronsPerLayerMap(args[1], this.getNeuronsPerLayer());
			
			// if only one attribute, then missing data symbol is assigned its default value "?"
			if (args.length == 2)
				this.setMissingDataSymbol("?");
			else if (args.length == 3)
				this.setMissingDataSymbol(args[2]);
			else
				throw new IllegalArgumentException("Incorret number of arguments.");
		}
		else
			throw new IllegalArgumentException("Please provide data file and configuration file");
	}

	// builds the map that stores the number of neurons per layer
	private void buildNeuronsPerLayerMap(String configFilename, Map<Integer, Integer> neuronsPerLayer)
	{
		List<String> configFile = DataReader.readData(configFilename);
		
		for (String confLine : configFile)
		{
			String[] line = confLine.split(",");
			
			if (line == null || line.length == 0)
				throw new NullPointerException("configuration file line is null, empty, or poorly formatted");
			else
			{
				if (line.length == 2)
					neuronsPerLayer.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
				else
					throw new IllegalArgumentException("please only provide 2 numbers per line in the configuration file");
			}
		}
		
	}

	// getters and setters
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMissingDataSymbol() {
		return missingDataSymbol;
	}

	public void setMissingDataSymbol(String missingDataSymbol) {
		this.missingDataSymbol = missingDataSymbol;
	}

	public Map<Integer, Integer> getNeuronsPerLayer() {
		return neuronsPerLayer;
	}

	public void setNeuronsPerLayer(Map<Integer, Integer> neuronsPerLayer) {
		this.neuronsPerLayer = neuronsPerLayer;
	}

}
