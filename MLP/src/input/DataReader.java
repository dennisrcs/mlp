package input;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// data reader
public class DataReader
{
	// reads data as string in file 'filename'
	public static ArrayList<String> readData(String filename)
	{
		ArrayList<String> result = null;
		
		try {
			List<String> lines = (ArrayList<String>) Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
			result = new ArrayList<String>(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
