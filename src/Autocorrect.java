/* Author: Ziting Shen & Rachel Xu
 * Date: 01/23/2016
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Autocorrect {
	public static void main(String[] args) {
		try {
			Scanner words = new Scanner(new FileInputStream("../data/1gram.txt"));
			ArrayList<String> wordList = new ArrayList<String>();
			while (words.hasNextLine()) {
				wordList.add(words.nextLine());
			}
			String[] allWords = new String[wordList.size()];
			wordList.toArray(allWords);

			words = new Scanner(new FileInputStream("../data/2gramOutput.txt"));
			HashMap<String, String> map = new HashMap<String, String>();
			while (words.hasNextLine()) {
				String[] twoWords = words.nextLine().split(" ");
				map.put(twoWords[0], twoWords[1]);
			}
			
			words = new Scanner(new FileInputStream("../data/input.txt"));
			FileWriter writer = new FileWriter(new File("../data/output.txt"));
			while (words.hasNextLine()) {
				String[] text = words.nextLine().split(" +");
				for (int i = 0; i < text.length; i++) {
					if (Arrays.binarySearch(allWords, text[i].toLowerCase()) 
						>= 0) {
						writer.write(text[i] + " ");
					} else {
						if (i > 0) {
							String correctWord = map.get(text[i-1].toLowerCase());
							if (correctWord == null) correctWord = text[i];
							writer.write(correctWord + " ");
						} else {
							writer.write(text[i] + " ");
						}
					}
				}
				writer.write("\n");
			}
			writer.close();
 		}
 		catch (FileNotFoundException e) {
 			System.out.println("File not found");
 		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}