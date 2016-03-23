package util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DataUtil
{
	// returns the most common target attribute in the data set
	public static String getMostCommonAttribute(List<List<String>> data, String column)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int colIdx = findIndex(data, column);

		// builds the histogram of each occurrence in the output column
		for (int i = 1; i < data.size(); i++)
		{
			int value;
			String elem = data.get(i).get(colIdx);
			if (map.containsKey(elem))
				value = map.get(elem) + 1;
			else
				value = 1;
			map.put(elem, value);
		}

		String mostCommonAttr = null;
		int maxValue = Integer.MIN_VALUE;
		
		// identifies the most common attribute and return it
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			if (entry.getValue() > maxValue)
			{
				maxValue = entry.getValue();
				mostCommonAttr = entry.getKey();
			}
		}
		
		return mostCommonAttr;
	}
	
	// given a column and a set, returns which index in the set the column is
	public static int findIndex(List<List<String>> set, String column) {
		int colIdx = 0;
		
		for (int i = 0; i < set.get(0).size(); i++)
		{
			String el = set.get(0).get(i).replaceAll("\\s+","");
			if (el.equals(column))
			{
				colIdx = i;
				break;
			}
		}
		
		return colIdx;
	}
	
	// returns the list of unique values found in the given column of the data set
	public static List<String> uniqueAttributeValues(List<List<String>> data, String column)
	{
		List<String> result = new ArrayList<String>();
		int col = findIndex(data, column);
		
		// building map
		for (int i = 1; i < data.size(); i++)
		{
			String newValue = data.get(i).get(col);

			if (!result.contains(newValue))
				result.add(newValue);
		}
		
		return result;
	}
	
	// returns the first row of the table
	public static List<String> getTableTitles(List<List<String>> input)
	{
		List<String> firstRow = new ArrayList<String>();
		
		for (int i = 0; i < input.get(0).size(); i++)
			firstRow.add(input.get(0).get(i));
		
		return firstRow;
	}

	// removes the column colIdx from the data set
	public static void removeColumn(List<List<String>> data, int colIdx)
	{
		for (int i = 0; i < data.size(); i++)
			data.get(i).remove(colIdx);
	}
	
	public static void printData(List<List<String>> data)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		Pattern pattern = Pattern.compile("(([0-9]+.[0-9]*)|([0-9]*.[0-9]+)|([0-9]+))");
		
		// printing the data set
		for (int i = 0; i < data.size(); i++)
		{
			int rowSize = data.get(i).size();
			for (int j = 0; j < rowSize; j++)
			{
				String cellValue = data.get(i).get(j);
				if (pattern.matcher(cellValue).matches())
				{
					double value = Double.parseDouble(cellValue);
					System.out.print(df.format(value) + ", ");
				}
				else
					System.out.print(cellValue + ", ");
			}
			System.out.println();
		}
			
			
	}
}
