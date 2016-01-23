import java.util.Arrays;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class DataProcess2 {
	public static void main(String[] args) {
		try {
			Scanner n_grams = new Scanner(new FileInputStream("../data/3gram.txt"));
			HashMap<String[], WordFreq> map = new HashMap<String[], WordFreq>();
			
			while (n_grams.hasNextLine()) {
				String[] params = n_grams.nextLine().split("\t+");
				//System.out.println(Arrays.toString(params));
				int freq = Integer.parseInt(params[0]);
				String[] prev = Arrays.copyOfRange(params, 1, params.length-1);
				String next = params[params.length-1];

				if (map.containsKey(prev)) {
					if (freq > map.get(prev).freq) {
						map.put(prev, new WordFreq(next, freq));
					}
				} else {
					map.put(prev, new WordFreq(next, freq));
				}
				
			}

			FileWriter writer = new FileWriter(new File("../data/3gramOutput.txt"));
			for (String[] key : map.keySet()) {
				for (String word: key) 
					writer.write(word + " ");
				writer.write(map.get(key).word + "\n");
			}
			writer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}