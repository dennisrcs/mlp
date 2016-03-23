package preprocessing;

import java.util.List;
import util.DataUtil;

public class UselessDataHandler
{
	// deletes a useless column in the data set
	public void removeUselessColumn(List<List<String>> data, List<String> columns)
	{
		int colIdx;
		if (columns != null && columns.size() > 0)
		{
			for (String column : columns)
			{
				colIdx = DataUtil.findIndex(data, column);
				for (int i = 0; i < data.size(); i++)
					data.get(i).remove(colIdx);		
			}
		}
	}
}
