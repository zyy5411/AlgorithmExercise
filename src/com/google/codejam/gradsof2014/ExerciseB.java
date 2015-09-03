package com.google.codejam.gradsof2014;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ExerciseB {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;

	public ExerciseB(String inputFile, String outputFile) throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {
			String line = scanner.nextLine();
			System.out.println(line);
		}
		fileWriter.close();
	}

	void writeLine(String str) throws IOException {
		fileWriter.write("Case #" + index + ": " + str);
		index++;
	}

	public static void main(String[] args) throws IOException {
		ExerciseB exe = new ExerciseB("B-small-practice.in",
				"B-small-practice.in");
		exe.run();
	}

}
