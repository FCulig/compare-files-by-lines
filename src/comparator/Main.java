package comparator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static String firstFileName, secondFileName;

	public static void main(String[] args) {
		firstFileName = extractFileNameFromPath(args[0]);
		List<String> firstFileLines = getLinesListFromDocument(args[0]);
		
		for (int i = 1; i < args.length; i++) {
			secondFileName = extractFileNameFromPath(args[i]);
			printFileLineDifferences(firstFileLines, firstFileName, getLinesListFromDocument(args[i]), secondFileName);
			System.out.println("--------------------------------------------------------------------------");
		}
	}

	public static String extractFileNameFromPath(String path) {
		return path.substring(path.lastIndexOf("/") + 1);
	}

	public static List<String> getLinesListFromDocument(String filePath) {
		BufferedReader reader;
		List<String> documentLines = new ArrayList<>();

		try {
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {
				documentLines.add(line.replaceAll("\\s+", ""));
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return documentLines;
	}

	public static void printFileLineDifferences(List<String> l1, String firstFileName, List<String> l2,
			String secondFileName) {
		for (String line : l1) {
			if (!l2.contains(line)) {
				System.out.println(secondFileName + " is missing " + line);
			}
		}

		for (String line : l2) {
			if (!l1.contains(line)) {
				System.out.println(firstFileName + " is missing " + line);
			}
		}
	}

}
