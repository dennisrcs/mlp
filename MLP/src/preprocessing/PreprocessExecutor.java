package preprocessing;

import parser.DataInput;

public class PreprocessExecutor
{
	// executes the preprocess phase
	public static void Execute(DataInput inputData)
	{
		PreprocessingHelper helper = new PreprocessingHelper(inputData);

		// executes all preprocessing tasks
		helper.removeUselessData();
		helper.handleMissingData();
		helper.handleTargetAttribute();
		helper.normalizeData();
		helper.fromCategoricalToNumericData();
	}
}
