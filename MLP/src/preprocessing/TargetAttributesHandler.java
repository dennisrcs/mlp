package preprocessing;

import java.util.List;

import util.DataUtil;

public class TargetAttributesHandler
{
	// moves the target attribute to the last column
	public void moveItToLastColumn(List<List<String>> data, List<String> targetAttributeColumns)
	{
		int colIdx = DataUtil.findIndex(data, targetAttributeColumns.get(0));
				
		// if the target attribute is not in the last column, make it
		if (colIdx != data.get(0).size() - 1)
		{
			for (int i = 0; i < data.size(); i++)
			{
				String value = data.get(i).remove(colIdx);
				data.get(i).add(value);
			}
		}
	}
}
