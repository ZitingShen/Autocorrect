/* Author: Ziting Shen & Rachel Xu
 * Date: 01/23/2016
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Autocorrect {
	public static void main(String[] args) {
		try {
			Scanner words = new Scanner(new File("../data/1gram.txt"));
			ArrayList<String> wordList = new ArrayList<String>();
			while (words.hasNextLine()) {
				wordList.add(words.nextLine());
			}
			System.out.println(wordList.size());
 		}
 		catch (FileNotFoundException e) {
 			System.out.println("File not found");
 		}
	}
}