package preprocessing;

import parser.DataInput;

public class PreprocessingHelper
{
	// members
	private DataInput data;
	
	// constructor
	public PreprocessingHelper(DataInput data)
	{
		this.data = data;
	}
	
	// replaces missing data with most common value
	public void handleMissingData()
	{
		MissingDataHandler missingDataHandler = new MissingDataHandler();
		missingDataHandler.handleMissingData(data.getData(), data.getNumericDataColumns(), data.getMissingDataSymbol());
	}

	// moves the target attribute to the last column
	public void handleTargetAttribute()
	{
		TargetAttributesHandler targetHandler = new TargetAttributesHandler();
		targetHandler.moveItToLastColumn(data.getData(), data.getTargetAttributeColumns());
	}
	
	// converts from categorical to numeric data
	public void fromCategoricalToNumericData()
	{
		NumericDataHandler numericDataHandler = new NumericDataHandler();
		numericDataHandler.convertToNumericData(data.getData(), data.getNumericDataColumns(), data.getTargetAttributeColumns());
	}
	
	// normalize numeric data
	public void normalizeData()
	{
		NormalizationHandler normalizationHandler = new NormalizationHandler();
		normalizationHandler.normalizeNumericColumn(data.getData(), data.getNumericDataColumns());
	}
	
	// removes useless data from the database
	public void removeUselessData()
	{
		UselessDataHandler uselessDataHandler = new UselessDataHandler();
		uselessDataHandler.removeUselessColumn(data.getData(), data.getUselessColumn());
	}

}
