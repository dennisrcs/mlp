package util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Util
{
	// shuffle the data but keeps the first titles in order
	public static void Shuffle(List<String> data)
	{
		long seed = System.nanoTime();
		String firstLine = data.remove(0);
		String secondLine = data.remove(0);
		Collections.shuffle(data, new Random(seed));
		data.add(0, secondLine);
		data.add(0, firstLine);
	}
}
