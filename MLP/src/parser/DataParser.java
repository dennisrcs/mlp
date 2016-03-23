package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import input.DataReader;
import util.Util;

// data parser utilities
public class DataParser
{
	// members
	private ArrayList<ArrayList<String>> data;
	
	// constructor
	public DataParser()
	{
		data = new ArrayList<ArrayList<String>>();
	}
	
	// parses data
	public List<List<String>> parse(String filename)
	{
		List<List<String>> result = new ArrayList<List<String>>();
		
		List<String> lines = DataReader.readData(filename);
		
		// shuffling input data
		Util.Shuffle(lines);
		
		for (String line : lines)
		{
			if (!line.isEmpty())
			{
				List<String> elems = Arrays.asList(line.split(","));
				
				if (elems.size() <= 0)
					throw new IllegalStateException("empty line in the input file");
				
				ArrayList<String> lineElems = new ArrayList<String>();
				lineElems.addAll(elems);
				
				result.add(lineElems);	
			}
		}

		return result;
	}

	public ArrayList<ArrayList<String>> getData() {
		return data;
	}

	public void setData(ArrayList<ArrayList<String>> data) {
		this.data = data;
	}
}
