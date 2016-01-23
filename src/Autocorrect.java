/* Author: Ziting Shen & Rachel Xu
 * Date: 01/23/2016
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;

public class Autocorrect {
	public static void main(String[] args) {
		try {
			Scanner words = new Scanner(new FileInputStream("../data/1gram.txt"));
			ArrayList<String> wordList = new ArrayList<String>();
			while (words.hasNextLine()) {
				wordList.add(words.nextLine());
			}
			words = new Scanner(new FileInputStream("../data/2gramOutput.txt"));
			HashMap<String, String> map = new HashMap<String, String>();
			while (words.hasNextLine()) {
				String[] twoWords = words.nextLine().split(" ");
				map.put(twoWords[0], twoWords[1]);
			}
			System.out.println(map.size());
 		}
 		catch (FileNotFoundException e) {
 			System.out.println("File not found");
 		}
	}
}