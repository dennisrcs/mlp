package preprocessing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DataUtil;

public class MissingDataHandler
{
	// replaces missing data with most common value
	public void handleMissingData(List<List<String>> data, List<String> numericColumns, String symbol)
	{
		Map<Integer,String> mostCommonValues = new HashMap<Integer,String>();
		
		// fill the map with most common attribute for each attribute in the data set
		for (int i = 0; i < data.get(0).size(); i++)
		{
			if (numericColumns.contains(data.get(0).get(i)))
				BuildMostCommonValueMapNumericData(data, data.get(0).get(i), mostCommonValues, symbol);
			else
				BuildMostCommonValueMap(data, data.get(0).get(i), mostCommonValues);
		}

		// if missing data, replace with most common attribute
		for (int i = 0; i < data.size(); i++)
		{
			for (int j = 0; j < data.get(i).size(); j++)
			{
				String value = data.get(i).get(j);
				if (value.equals(symbol))
					data.get(i).set(j, mostCommonValues.get(j));
			}
		}
	}
	
	// replaces missing data with the most common value for that attribute
	private void BuildMostCommonValueMap(List<List<String>> data, String column,
			Map<Integer, String> mostCommonValues)
	{
		String mostCommonAttr = DataUtil.getMostCommonAttribute(data, column);
		int colIdx = DataUtil.findIndex(data, column);
		mostCommonValues.put(colIdx, mostCommonAttr);
	}

	// replaces missing data for the average value in that column
	private void BuildMostCommonValueMapNumericData(List<List<String>> data, String column,
			Map<Integer, String> mostCommonValues, String symbol)
	{
		int columnIdx = DataUtil.findIndex(data, column);
		double columnSum = 0;
		
		for (int i = 1; i < data.size(); i++)
			if (!data.get(i).get(columnIdx).equals(symbol))
				columnSum += Double.parseDouble(data.get(i).get(columnIdx));
		
		double average = columnSum / (double) data.size();
		
		mostCommonValues.put(columnIdx, String.valueOf(average));
	}
}
