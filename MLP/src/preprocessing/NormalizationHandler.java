package preprocessing;

import java.util.ArrayList;
import java.util.List;

import util.DataUtil;
import util.MathHelper;

public class NormalizationHandler
{
	// deletes a useless column in the data set
	public void normalizeNumericColumn(List<List<String>> data, List<String> columns)
	{
		if (columns != null && columns.size() > 0)
			for (String column : columns)
				normalizeData(data, column);
	}

	private void normalizeData(List<List<String>> data, String column)
	{
		int colIdx = DataUtil.findIndex(data, column);
		List<Double> numericData = new ArrayList<Double>();
		
		// variables
		double cellDataDouble;
		String cellData;
		double min;
		double max;
		double newValue;
		
		// creates a vector of doubles for that column
		for (int i = 1; i < data.size(); i++)
		{
			cellData = data.get(i).get(colIdx);
			cellDataDouble = Double.parseDouble(cellData);
			numericData.add(cellDataDouble);
		}
		
		// finds the minimum and maximum value in the collection
		min = MathHelper.findMin(numericData);
		max = MathHelper.findMax(numericData);
		
		// normalize the data
		for (int i = 1; i < data.size(); i++)
		{
			cellData = data.get(i).get(colIdx);
			cellDataDouble = Double.parseDouble(cellData);
			
			newValue = (cellDataDouble - min) / (max - min);
			data.get(i).set(colIdx, String.valueOf(newValue));
		}
	}
}
