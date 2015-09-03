package com.google.codejam.gradsof2014;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import util.Tools;

public class ExerciseA {

	String basepath = "C:\\Users\\Administrator\\Desktop\\google\\";
	Scanner scanner;
	FileWriter fileWriter;
	int index = 1;
	String[] anounce = { " ", "double", "triple", "quadruple", "quintuple",
			"sextuple", "septuple", "octuple", "nonuple", "decuple" };
	String[] numbers = { "zero", "one", "two", "three", "four", "five", "six",
			"seven", "eight", "nine" };

	public ExerciseA(String inputFile, String outputFile) throws IOException {
		scanner = new Scanner(new File(basepath + inputFile));
		fileWriter = new FileWriter(new File(basepath + outputFile));
	}

	String[] splitByFormat(String pNumber, String format) {
		String[] formats = format.split("-");
		String[] strArr = new String[formats.length];
		int index = 0;
		for (int i = 0; i < formats.length; i++) {
			int len = Integer.parseInt(formats[i]);
			strArr[i] = pNumber.substring(index, index + len);
			System.out.println(strArr[i]);
			index += len;
		}
		return strArr;
	}

	String anouceNumber(String number) {
		char currentChar, nextChar;
		int index = 0, count = 1;
		StringBuilder sb = new StringBuilder();
		while (index < number.length()) {
			currentChar = number.charAt(index++);
			if (index >= number.length())
				nextChar = ' ';
			else
				nextChar = number.charAt(index);
			if (currentChar == nextChar) {
				count++;
			} else {
				if (count > 10) {
					while (count-- > 0) {
						sb.append(numbers[currentChar - '0']);
						sb.append(" ");
					}
				} else {
					sb.append(count == 1 ? "" : anounce[count - 1] + " ");
					sb.append(numbers[currentChar - '0']);
					sb.append(" ");
				}
				count = 1;
			}
		}
		return sb.toString();
	}

	String toFormat(String pNumber, String format) {
		String[] splitNumber = splitByFormat(pNumber, format);
		//		Tools.print(splitNumber);
		StringBuilder sb = new StringBuilder();
		for (String s : splitNumber) {
			sb.append(anouceNumber(s));
		}
		sb.replace(sb.length() - 1, sb.length(), "");

		Tools.println(sb.toString());
		return sb.toString();
	}

	void run() throws IOException {
		int n = Integer.parseInt(scanner.nextLine());
		while (n-- > 0 && scanner.hasNext()) {
			String pnum = scanner.next();
			String format = scanner.next();
			writeLine(toFormat(pnum, format));
		}
		fileWriter.close();
	}

	void writeLine(String str) throws IOException {
		fileWriter.write("Case #" + index + ": " + str + "\n");
		index++;
	}

	public static void main(String[] args) throws IOException {
		ExerciseA exe = new ExerciseA("A-large.in", "A-large-practice.out");
		exe.run();
	}

}
