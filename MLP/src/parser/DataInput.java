package parser;

import java.util.ArrayList;
import java.util.List;

// stores the data provided by input
public class DataInput
{
	// members
	private List<List<String>> data;
	private List<String> numericDataColumns;
	private List<String> targetAttributeColumns;
	private List<String> uselessColumns;
	private String missingDataSymbol;
	
	// constructor
	public DataInput(List<List<String>> inputData, String missingDataSymbol)
	{
		this.setNumericDataColumns(RetrieveNumericData(inputData));
		this.setTargetAttributeColumns(RetrieveTargetAttribute(inputData));
		this.setUselessColumn(RetrieveUselessColumn(inputData));
		this.setMissingDataSymbol(missingDataSymbol);
		this.RemoveConfigInfoAndSetData(inputData);
	}

	private List<String> RetrieveUselessColumn(List<List<String>> inputData)
	{
		List<String> target = new ArrayList<String>();
		
		for (int i = 0; i < inputData.get(0).size(); i++)
			if (inputData.get(0).get(i).equals("useless"))
				target.add(inputData.get(1).get(i));
		
		return target;
	}

	// removes the header and set data
	private void RemoveConfigInfoAndSetData(List<List<String>> inputData)
	{
		inputData.remove(0);
		setData(inputData);
	}

	// returns the target attribute in the data set
	private List<String> RetrieveTargetAttribute(List<List<String>> inputData)
	{
		List<String> target = new ArrayList<String>();
		
		for (int i = 0; i < inputData.get(0).size(); i++)
			if (inputData.get(0).get(i).equals("target"))
				target.add(inputData.get(1).get(i));
		
		return target;
	}

	// returns the numeric columns in the data set
	private List<String> RetrieveNumericData(List<List<String>> inputData)
	{
		List<String> numericDataColumns = new ArrayList<String>();
		
		for (int i = 0; i < inputData.get(0).size(); i++)
			if (inputData.get(0).get(i).equals("numeric"))
				numericDataColumns.add(inputData.get(1).get(i));

		return numericDataColumns;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

	public List<String> getNumericDataColumns() {
		return numericDataColumns;
	}

	public void setNumericDataColumns(List<String> numericDataColumns) {
		this.numericDataColumns = numericDataColumns;
	}

	public List<String> getTargetAttributeColumns() {
		return targetAttributeColumns;
	}

	public void setTargetAttributeColumns(List<String> targetAttributeColumn) {
		this.targetAttributeColumns = targetAttributeColumn;
	}

	public String getMissingDataSymbol() {
		return missingDataSymbol;
	}

	public void setMissingDataSymbol(String missingDataSymbol) {
		this.missingDataSymbol = missingDataSymbol;
	}

	public List<String> getUselessColumn() {
		return uselessColumns;
	}

	public void setUselessColumn(List<String> uselessColumnData) {
		this.uselessColumns = uselessColumnData;
	}

}
