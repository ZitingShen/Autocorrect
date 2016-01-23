/* Author: Ziting Shen & Rachel Xu
 * Date: 01/23/2016
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Autocorrect {
	public static void main(String[] args) {
		try {
			Scanner words = new Scanner(new FileInputStream("../data/Words.txt"));
			HashMap<String, Integer> wordList = new HashMap<String, Integer>();
			while (words.hasNextLine()) {
				String[] pieces = words.nextLine().split(" ");
				wordList.put(pieces[0].toLowerCase(), Integer.parseInt(pieces[1]));
			}

			// words = new Scanner(new FileInputStream("../data/2gramOutput.txt"));
			// HashMap<String, String> map = new HashMap<String, String>();
			// while (words.hasNextLine()) {
			// 	String[] twoWords = words.nextLine().split(" ");
			// 	map.put(twoWords[0], twoWords[1]);
			// }

			Scanner scanner = new Scanner(new FileInputStream("../data/dictionary.txt"));
			ArrayList<String> dic = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				dic.add(scanner.nextLine());
			}

			HashMap<String, ArrayList<String>> newDic = new HashMap<String, ArrayList<String>>();
			for (String s : dic) {
				HashSet<String> e1 = deleteChars(s);
				HashSet<String> e2 = new HashSet<String>();
				for (String str : e1) {
					e2.addAll(deleteChars(str));
				}
				e1.addAll(e2);
				for (String str : e1) {
					ArrayList<String> list = newDic.get(str);
					if (list != null) {
						list.add(s);
						newDic.put(str, list);
					} else {
						ArrayList<String> newList = new ArrayList<String>();
						newList.add(s);
						newDic.put(str, newList);
					}
				}
			}
			// for (String key : newDic.keySet()) {
			// 	System.out.println(key + ": " + newDic.get(key));
			// }
			// System.out.println(newDic.size());

			words = new Scanner(new FileInputStream("../data/input.txt"));
			FileWriter writer = new FileWriter(new File("../data/output.txt"));
			while (words.hasNextLine()) {
				String[] text = words.nextLine().split(" +");
				for (int i = 0; i < text.length; i++) {
					if (wordList.containsKey(text[i].toLowerCase())) {
						writer.write(text[i] + " ");
					} else {
						HashSet<String> hs = deleteChars(text[i]);
						HashSet<String> hs2 = new HashSet<String>();
						for (String s : hs) {
							hs2.addAll(deleteChars(s));
						}
						hs.addAll(hs2);
						HashSet<String> options = new HashSet<String>();
						for (String s : hs) {
							ArrayList<String> res = newDic.get(s);
							if (res != null) {
								options.addAll(res);
							}
						}

						int maxFreq = 0;
						String finalChoice = null;
						for (String s: options) {
							int newFreq = wordList.get(s);
							if (newFreq > maxFreq) {
								maxFreq = newFreq;
								finalChoice = s;
							}
						}
						if (finalChoice == null) finalChoice = text[i];

						writer.write(finalChoice + " ");
					}
				}
				writer.write("\n");
			}
			writer.close();
 		}
 		catch (FileNotFoundException e) {
 			System.out.println("File not found");
 		} 
 	 	catch (IOException e) {
		 	e.printStackTrace();
		}
	}

	private static HashSet<String> deleteChars(String s) {
		HashSet<String> result = new HashSet<String>();
		for (int i = 0; i < s.length(); i++) {
			StringBuilder sb = new StringBuilder(s);
			sb.deleteCharAt(i);	
			result.add(sb.toString());
		}
		return result;
	}
}