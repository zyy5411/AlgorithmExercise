package com.google.codejam;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Africa2010_Reverse_Words {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;

	public Africa2010_Reverse_Words(String inputFile, String outputFile)
			throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
	}

	String getReverse(String str) {
		String[] arr = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			sb.append(arr[i]);
			if (i != 0)
				sb.append(" ");
		}
		return sb.toString();
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {
			String line = scanner.nextLine();
			System.out.println(line);
			String reverseStr = getReverse(line);
			writeLine(reverseStr);
		}
		fileWriter.close();
	}

	void writeLine(String str) throws IOException {
		fileWriter.write("Case #" + index + ": " + str);
		index++;
	}

	public static void main(String[] args) throws IOException {
		Africa2010_Reverse_Words a = new Africa2010_Reverse_Words(
				"B-large-practice.in", "B-large-practice.in");
		a.run();

	}
}
