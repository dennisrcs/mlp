package preprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DataUtil;

// convert from categorical to numeric data
public class NumericDataHandler
{
	// convert from categorical to numeric data
	public void convertToNumericData(List<List<String>> data, List<String> numericDataColumns, List<String> targetColumns)
	{
		// get the titles of the data set
		List<String> firstRow = DataUtil.getTableTitles(data);
		
		// only handle non-numeric columns
		for (String column : firstRow)
			if (!numericDataColumns.contains(column))
				convertColumnToNumericData(data, targetColumns, column);
	}

	private void convertColumnToNumericData(List<List<String>> data, List<String> targetColumns, String column)
	{
		List<String> values = DataUtil.uniqueAttributeValues(data, column);
		
		Map<String, List<String>> oldToNewValueMap = new HashMap<String, List<String>>();
		createNewValuesMap(values, oldToNewValueMap);

		// finds index of the column at sets a reference to the start of the new data
		int colIdx = DataUtil.findIndex(data, column);
		int firstNewColRef = colIdx + 1;
		
		String newTitle;
		List<String> newTitleSet = new ArrayList<String>();
		
		// checks if the current column is the target column
		boolean isTargetColumn = (colIdx == data.get(0).size() - 1);
		
		// new titles for the data table
		for (int i = 0; i < values.size(); i++)
		{
			newTitle = column + i;
			newTitleSet.add(newTitle);
			data.get(0).add(firstNewColRef + i, newTitle);
		}
		
		// adding |values| new values into the data set
		for (int i = 1; i < data.size(); i++)
		{
			String currentValue = data.get(i).get(colIdx);
			List<String> bitVector = oldToNewValueMap.get(currentValue);
			
			for (int j = 0; j < bitVector.size(); j++)
				data.get(i).add(firstNewColRef + j, bitVector.get(j));
		}
		
		// removes the column indicating in colIdx from the data set
		DataUtil.removeColumn(data, colIdx);
		
		// if we are working on the target column, we need to
		// update the collection of titles related to the target
		if (isTargetColumn)
		{
			targetColumns.remove(0);
			targetColumns.addAll(newTitleSet);
		}
	}

	// maps a vector of bits for each value in values
	private void createNewValuesMap(List<String> values, Map<String, List<String>> map)
	{
		// variables
		int activeBit = 0;
		int currentValue = 0;
		
		for (String value : values)
		{
			List<String> bitsList = new ArrayList<String>();
			
			// for each value in values create a vector of size |values| with only one active bit
			for (int i = 0; i < values.size(); i++)
			{
				currentValue = (i == activeBit) ? 1 : 0;
				bitsList.add(String.valueOf(currentValue));
			}
			
			map.put(value, bitsList);
			activeBit += 1;
		}
	}
}
